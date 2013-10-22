//
//  APICalendarViewController.m
//  DemoAPI
//
//  Created by Régis on 12/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APICalendarViewController.h"

@interface APICalendarViewController ()

@end

@implementation APICalendarViewController

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
    
    self.timeField.delegate = self;
}

- (IBAction)createCalendar:(id)sender {
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    EKCalendar *calendar = [EKCalendar calendarForEntityType:EKEntityTypeEvent eventStore:eventStore];
    calendar.title = @"Meu calendário";
   
    EKSource *theSource = nil;
    for (EKSource *source in eventStore.sources) {
        if (source.sourceType == EKSourceTypeLocal) {
            theSource = source;
            break;
        }
    }
    
    if (theSource) {
        calendar.source = theSource;
    }
    
    // salvando calendário
    NSError *error = nil;
    BOOL result = [eventStore saveCalendar:calendar commit:YES error:&error];
    if (result) {
        NSLog(@"Saved calendar to event store.");
        self.calendarIdentifier = calendar.calendarIdentifier;
        
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Sucesso"
                                                        message:@"Calendário salvo."
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles: nil];
        
        [alert show];
    } else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Atenção"
                                                        message:@"Não foi possível salvar o calendário."
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles: nil];
        
        [alert show];
    }
    
    [self.timeField resignFirstResponder];
}

- (IBAction)addEvent:(id)sender {
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    EKEvent *event = [EKEvent eventWithEventStore:eventStore];
    EKCalendar *calendar = [eventStore calendarWithIdentifier:self.calendarIdentifier];
    event.calendar = calendar;
    
    NSDate *startDate = [NSDate date];
    event.startDate = startDate;
    float time = [self.timeField.text floatValue]*3600;
    event.endDate = [startDate dateByAddingTimeInterval:time];
    
    // salvando evento
    NSError *error = nil;
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
    
    [self.timeField resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
