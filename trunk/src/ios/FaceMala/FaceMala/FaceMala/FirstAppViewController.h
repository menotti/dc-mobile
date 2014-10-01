//
//  FirstAppViewController.h
//  FirstApp
//
//  Created by Caio Pegoraro on 11/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <FacebookSDK/FacebookSDK.h>
#import <Social/Social.h>

#import "SecondScreenViewController.h"

@interface FirstAppViewController : UIViewController <FBLoginViewDelegate,UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet FBLoginView *loginButton;
@property (weak, nonatomic) IBOutlet UIButton *showProfile;

- (IBAction)showProfileAction:(id)sender;

@property (weak, nonatomic) IBOutlet UILabel *lblUsername;
@property (weak, nonatomic) IBOutlet UILabel *lblEmail;
@property (weak, nonatomic) IBOutlet FBProfilePictureView *profilePicture;

@end
