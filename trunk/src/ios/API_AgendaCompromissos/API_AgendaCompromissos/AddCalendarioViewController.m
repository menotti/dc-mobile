//
//  AddCalendarioViewController.m
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 02/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "AddCalendarioViewController.h"

@interface AddCalendarioViewController ()

@end

@implementation AddCalendarioViewController

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
    self.calendarTitle.delegate = self;
    // Do any additional setup after loading the view from its nib.
    

}

- (IBAction)createCalendar:(id)sender {
    EKEventStore *eventStore = [[EKEventStore alloc] init];
    
    [eventStore requestAccessToEntityType:EKEntityTypeEvent
                               completion:^(BOOL granted,NSError* error){
                                   
                                   
                                   EKCalendar *calendar = [EKCalendar calendarForEntityType:EKEntityTypeEvent eventStore:eventStore];
                                   calendar.title = self.calendarTitle.text;

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
