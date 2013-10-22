//
//  APICacheViewController.h
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APICacheViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *textField;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

@property (nonatomic, strong) NSUserDefaults *userDefaults;

- (IBAction)saveText:(id)sender;
- (IBAction)retrieveText:(id)sender;

@end
