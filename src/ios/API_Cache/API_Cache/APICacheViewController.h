//
//  APICacheViewController.h
//  API_Cache
//
//  Created by Caio Pegoraro on 05/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APICacheViewController : UIViewController <UITextFieldDelegate>


@property (weak, nonatomic) IBOutlet UITextField *textField;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

//Objeto NSUserDefaults
@property (nonatomic, strong) NSUserDefaults *userDefaults;

- (IBAction)saveText:(id)sender;
- (IBAction)retrieveText:(id)sender;
- (IBAction)clearBox:(id)sender;
- (IBAction)completeBox:(id)sender;

@end
