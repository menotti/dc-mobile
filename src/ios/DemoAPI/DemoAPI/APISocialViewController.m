//
//  APISocialViewController.m
//  DemoAPI
//
//  Created by Régis on 04/11/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APISocialViewController.h"

@interface APISocialViewController ()

@end

@implementation APISocialViewController

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
    
    self.twitterField.delegate = self;
    self.facebookField.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)postTwitter:(id)sender {
    
    if ([SLComposeViewController isAvailableForServiceType:SLServiceTypeTwitter])
    {
        SLComposeViewController *controller = [SLComposeViewController
                                               composeViewControllerForServiceType:SLServiceTypeTwitter];
        [controller setInitialText:self.twitterField.text];
        
        [self presentViewController:controller animated:YES completion:nil];
    }
}

- (IBAction)postFacebook:(id)sender {
    
    if([SLComposeViewController isAvailableForServiceType:SLServiceTypeFacebook]) {
        SLComposeViewController *controller = [SLComposeViewController composeViewControllerForServiceType:SLServiceTypeFacebook];
        
        [controller setInitialText:self.facebookField.text];
        [controller addURL:[NSURL URLWithString:@"http://mobile.dc.ufscar.br"]];
        [controller addImage:[UIImage imageNamed:@"LogoDC.jpg"]];

        
        [self presentViewController:controller animated:YES completion:nil];
    }
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

@end
