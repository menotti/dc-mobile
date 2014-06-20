//
//  ApiSmsViewController.h
//  API_SMS
//
//  Created by Caio Pegoraro on 20/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>
#import <MessageUI/MFMessageComposeViewController.h>

@interface ApiSmsViewController : UIViewController <UITextFieldDelegate, MFMessageComposeViewControllerDelegate>


@property (weak, nonatomic) IBOutlet UITextField *phoneField;
@property (weak, nonatomic) IBOutlet UITextField *messageField;


- (IBAction)sendMessage:(id)sender;

@end
