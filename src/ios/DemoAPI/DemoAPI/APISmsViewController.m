//
//  APISmsViewController.m
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APISmsViewController.h"

@interface APISmsViewController ()

@end

@implementation APISmsViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.phoneField.delegate = self;
    self.messageField.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)sendMessage:(id)sender {
    
    NSMutableArray *phoneArray = [[NSMutableArray alloc] initWithObjects:self.phoneField.text, nil];
    
    [self sendSMS:self.messageField.text recipientList:phoneArray];
}

- (void)sendSMS:(NSString *)bodyOfMessage recipientList:(NSArray *)recipients {
    MFMessageComposeViewController *controller =
    [[MFMessageComposeViewController alloc] init];
    
    if([MFMessageComposeViewController canSendText]) {
        controller.body = bodyOfMessage;
        controller.recipients = recipients;
        controller.messageComposeDelegate = self;
        [self presentViewController:controller animated:YES completion:nil];
    }
}

- (void)messageComposeViewController:(MFMessageComposeViewController *)controller didFinishWithResult:(MessageComposeResult)result
{
    [self dismissViewControllerAnimated:YES completion:nil];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

@end
