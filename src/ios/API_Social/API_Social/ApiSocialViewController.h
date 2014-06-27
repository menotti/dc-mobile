//
//  ApiSocialViewController.h
//  API_Social
//
//  Created by Caio Pegoraro on 27/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <Social/Social.h>

@interface ApiSocialViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *twitterField;
@property (weak, nonatomic) IBOutlet UITextField *facebookField;

- (IBAction)postTwitter:(id)sender;
- (IBAction)postFacebook:(id)sender;

@end
