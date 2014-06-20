//
//  ApiSmsViewController.m
//  API_SMS
//
//  Created by Caio Pegoraro on 20/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "ApiSmsViewController.h"

@interface ApiSmsViewController ()

@end

@implementation ApiSmsViewController

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
    // Do any additional setup after loading the view from its nib.
    
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
