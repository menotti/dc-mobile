//
//  ApiSocialViewController.m
//  API_Social
//
//  Created by Caio Pegoraro on 27/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "ApiSocialViewController.h"

@interface ApiSocialViewController ()

@end

@implementation ApiSocialViewController

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
    self.twitterField.delegate = self;
    self.facebookField.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}


- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
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
        //[controller addImage:[UIImage imageNamed:@"LogoDC.jpg"]];
        
        
        [self presentViewController:controller animated:YES completion:nil];
    }
}

@end
