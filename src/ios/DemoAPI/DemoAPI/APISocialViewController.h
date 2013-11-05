//
//  APISocialViewController.h
//  DemoAPI
//
//  Created by Régis on 04/11/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Social/Social.h>

@interface APISocialViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *twitterField;
@property (weak, nonatomic) IBOutlet UITextField *facebookField;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

- (IBAction)postTwitter:(id)sender;
- (IBAction)postFacebook:(id)sender;

@end
