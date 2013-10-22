//
//  APISmsViewController.h
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <QuartzCore/QuartzCore.h>
#import <MessageUI/MFMessageComposeViewController.h>

@interface APISmsViewController : UIViewController <UITextFieldDelegate, MFMessageComposeViewControllerDelegate>

@property (weak, nonatomic) IBOutlet UITextField *phoneField;
@property (weak, nonatomic) IBOutlet UITextField *messageField;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

- (IBAction)sendMessage:(id)sender;

@end
