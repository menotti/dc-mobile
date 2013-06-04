//
//  SecondScreenViewController.h
//  FirstApp
//
//  Created by Regis Zangirolami on 15/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ThirdScreenViewController.h"

@interface SecondScreenViewController : UIViewController <UITextFieldDelegate, MessageDelegate>

@property (strong, nonatomic) IBOutlet UIImageView *imageView;
@property (strong, nonatomic) IBOutlet UIButton *thirdScreenButton;
@property (strong, nonatomic) IBOutlet UITextField *nameTextField;

@property (nonatomic, strong) ThirdScreenViewController *thirdScreen;

- (IBAction)thirdScreen:(id)sender;

@end
