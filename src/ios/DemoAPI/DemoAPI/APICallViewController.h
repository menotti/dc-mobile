//
//  APICallViewController.h
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APICallViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *phoneField;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

- (IBAction)makeCall:(id)sender;

@end
