//
//  AddEventoViewController.h
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 02/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <EventKit/EventKit.h>

@interface AddEventoViewController : UIViewController <UITextFieldDelegate, UIPickerViewDelegate, UIPickerViewDataSource>
@property (weak, nonatomic) IBOutlet UITextField *eventTitle;

- (IBAction)addEvent:(id)sender;
@property (weak, nonatomic) IBOutlet UIPickerView *calendarList;
@property (weak, nonatomic) IBOutlet UITextField *timeField;

@property (nonatomic, strong) NSString *calendarIdentifier;

@property (weak, nonatomic) IBOutlet UITextField *day;
@property (weak, nonatomic) IBOutlet UITextField *month;
@property (weak, nonatomic) IBOutlet UITextField *year;

@end
