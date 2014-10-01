//
//  SecondScreenViewController.h
//  FaceMala
//
//  Created by Caio Pegoraro on 30/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <FacebookSDK/FacebookSDK.h>
#import <Social/Social.h>

@interface SecondScreenViewController : UIViewController

@property (nonatomic, strong) NSString *profileName;
@property (nonatomic, strong) NSString *profileEmail;
@property (nonatomic, strong) NSString *profilePic;

@property (weak, nonatomic) IBOutlet UILabel *lblUsername;
@property (weak, nonatomic) IBOutlet UILabel *lblEmail;
@property (weak, nonatomic) IBOutlet FBProfilePictureView *profilePicture;

- (IBAction)compartilharAuto:(id)sender;
- (IBAction)compartilharManual:(id)sender;

@end
