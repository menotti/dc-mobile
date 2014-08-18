//
//  AgendaCompromissosViewController.m
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 20/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "AgendaCompromissosViewController.h"

@interface AgendaCompromissosViewController ()

@end

@implementation AgendaCompromissosViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
    }
    return self;
}

- (void)viewDidLoad
{
    
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    self.timeField.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)createCalendar:(id)sender {
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    
    [eventStore requestAccessToEntityType:EKEntityTypeEvent
                               completion:^(BOOL granted,NSError* error){
                                   
    
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
    BOOL result = [eventStore saveCalendar:calendar commit:YES error:&error];
    if (result && granted) { //granted eh retornado de acordo com a resposta do usuário ao pedido de permissao de acesso ao calendário
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

 }];
}

- (IBAction)addEvent:(id)sender {
    
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    [eventStore requestAccessToEntityType:EKEntityTypeEvent
                               completion:^(BOOL granted,NSError* error){
                                   
    EKEvent *event = [EKEvent eventWithEventStore:eventStore];
    EKCalendar *calendar = [eventStore calendarWithIdentifier:self.calendarIdentifier];
    event.calendar = calendar;

    NSDate *startDate = [NSDate date];
    event.startDate = startDate;
    float time = [self.timeField.text floatValue]*3600;
    event.endDate = [startDate dateByAddingTimeInterval:time];
    
    // salvando evento
    BOOL result = [eventStore saveEvent:event span:EKSpanThisEvent commit:YES error:&error];
    if (result && granted) {
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
  }];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

@end
