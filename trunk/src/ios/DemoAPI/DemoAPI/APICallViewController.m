//
//  APICallViewController.m
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APICallViewController.h"

@interface APICallViewController ()

@end

@implementation APICallViewController

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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)makeCall:(id)sender {
    NSString *phoneString = @"tel:";
    
    if (self.phoneField.text !=nil) {
        phoneString = [NSString stringWithFormat:@"tel:%@",self.phoneField.text];
    }
    
    NSLog(@"%@",phoneString);
    
    if ([[[UIDevice currentDevice] model] isEqualToString:@"iPhone"]) {
        [[UIApplication sharedApplication] openURL:[NSURL URLWithString:phoneString]];
    } else {
        UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Atenção"
                                                        message:@"Seu dispositivo não permite realizar ligações telefônicas."
                                                       delegate:nil
                                              cancelButtonTitle:@"OK"
                                              otherButtonTitles:nil];
        [alert show];
    }
    
    [self.phoneField resignFirstResponder];
}

@end
