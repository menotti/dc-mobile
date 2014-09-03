//
//  AddCalendarioViewController.h
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 02/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <EventKit/EventKit.h>

@interface AddCalendarioViewController : UIViewController <UITextFieldDelegate, UIPickerViewDelegate>
@property (weak, nonatomic) IBOutlet UITextField *calendarTitle;

- (IBAction)createCalendar:(id)sender;

@end
