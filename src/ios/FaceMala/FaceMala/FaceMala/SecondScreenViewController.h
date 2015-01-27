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
#import "SuggestionScreenViewController.h"
#import "CategoryScreenViewController.h"
#import "AppDelegate.h"

@interface SecondScreenViewController : UIViewController

@property (nonatomic, strong) NSString *profileName;
@property (nonatomic, strong) NSString *profileEmail;
@property (nonatomic, strong) NSString *profilePic;
@property (nonatomic, strong) NSString *profileId;

@property (nonatomic, strong) NSMutableDictionary *params;

@property (weak, nonatomic) IBOutlet UILabel *lblUsername;
@property (weak, nonatomic) IBOutlet UILabel *lblEmail;
@property (weak, nonatomic) IBOutlet FBProfilePictureView *profilePicture;
@property (weak, nonatomic) IBOutlet UILabel *lblProfileId;

- (IBAction)compartilharAuto:(id)sender;
- (IBAction)manualShare:(id)sender;
- (IBAction)openSuggestionScreen:(id)sender;
- (IBAction)openCategoryScreen:(id)sender;

-(void)fetchNewDataWithCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler;

@end
