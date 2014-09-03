//
//  AddEventoViewController.m
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 02/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "AddEventoViewController.h"

@interface AddEventoViewController ()
{
    NSMutableArray *_pickerData;
    NSMutableArray *_identifierData;
}
@end

@implementation AddEventoViewController


- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    EKEventStore * eventStore = [[EKEventStore alloc] init];
    
    [eventStore requestAccessToEntityType:EKEntityTypeEvent completion:^(BOOL granted,NSError* error){
        
        EKEntityType type = EKEntityTypeEvent;
        NSArray * calendars = [eventStore calendarsForEntityType:type];
        EKCalendar *thisCalendar;
        
        _pickerData = [[NSMutableArray alloc] init];
        _identifierData = [[NSMutableArray alloc]init];
        
        for(thisCalendar in calendars){
            [_pickerData addObject:thisCalendar.title];
            [_identifierData addObject:thisCalendar.calendarIdentifier];
        }
        self.calendarList.dataSource = self;
        self.calendarList.delegate = self;
        
    }];
    
    self.eventTitle.delegate = self;
    self.timeField.delegate = self;
    self.day.delegate = self;
    self.month.delegate = self;
    self.year.delegate = self;
}

-(int)numberOfComponentsInPickerView:(UIPickerView *)pickerView
{
    return 1;
}

-(int)pickerView:(UIPickerView *)pickerView numberOfRowsInComponent:(NSInteger)component{
    return _pickerData.count;
}

-(NSString*)pickerView:(UIPickerView*)pickerView titleForRow:(NSInteger)row forComponent:(NSInteger)component{
    return _pickerData[row];
}

- (void)pickerView:(UIPickerView *)pickerView didSelectRow:(NSInteger)row inComponent:(NSInteger)component{
    self.calendarIdentifier = _identifierData[row];
}

- (IBAction)addEvent:(id)sender {
    
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    [eventStore requestAccessToEntityType:EKEntityTypeEvent
                               completion:^(BOOL granted,NSError* error){
                                   
                                   EKEvent *event = [EKEvent eventWithEventStore:eventStore];
                                   EKCalendar *calendar = [eventStore calendarWithIdentifier:self.calendarIdentifier];
                                   event.calendar = calendar;

                                   NSDateComponents *dateComponents = [[NSDateComponents alloc] init];
                                   dateComponents.day = [self.day.text intValue];
                                   dateComponents.month = [self.month.text intValue];
                                   dateComponents.year = [self.year.text intValue];
                                   NSDate *startDate = [[NSCalendar currentCalendar] dateFromComponents:dateComponents];
                                   event.startDate = startDate;
                                   float time = [self.timeField.text floatValue]*3600;
                                   event.endDate = [startDate dateByAddingTimeInterval:time];
                                   
                                   event.title = self.eventTitle.text;
                                   // salvando evento
                                   BOOL result = [eventStore saveEvent:event span:EKSpanThisEvent commit:YES error:&error];
                                   if (result) {
                                       NSLog(@"Saved event to event store.");
                                       
                                       UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Sucesso"
                                                                                       message:@"Evento salvo."
                                                                                      delegate:nil
                                                                             cancelButtonTitle:@"OK"
                                                                             otherButtonTitles: nil];
                                       
                                       [alert show];
                                   } else {
                                       UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Atenção"
                                                                                       message:@"Não foi possível salvar o evento."                                                       delegate:nil
                                                                             cancelButtonTitle:@"OK"
                                                                             otherButtonTitles: nil];
                                       
                                       [alert show];
                                   }
                               }];
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

@end
