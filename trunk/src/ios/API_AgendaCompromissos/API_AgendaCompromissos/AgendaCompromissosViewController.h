//
//  AgendaCompromissosViewController.h
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 20/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <EventKit/EventKit.h>

@interface AgendaCompromissosViewController : UIViewController <UITextFieldDelegate>

@property (nonatomic, strong) NSString *calendarIdentifier;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;
@property (weak, nonatomic) IBOutlet UITextField *timeField;

- (IBAction)createCalendar:(id)sender;
- (IBAction)addEvent:(id)sender;


@end
