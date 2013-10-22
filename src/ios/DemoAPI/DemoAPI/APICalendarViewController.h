//
//  APICalendarViewController.h
//  DemoAPI
//
//  Created by Régis on 12/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <EventKit/EventKit.h>

@interface APICalendarViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UILabel *progressLabel;
@property (weak, nonatomic) IBOutlet UITextField *timeField;

@property (nonatomic, strong) NSString *calendarIdentifier;

- (IBAction)createCalendar:(id)sender;
- (IBAction)addEvent:(id)sender;

@end
