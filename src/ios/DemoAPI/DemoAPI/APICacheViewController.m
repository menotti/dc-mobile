//
//  APICacheViewController.m
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APICacheViewController.h"

@interface APICacheViewController ()

@end

@implementation APICacheViewController

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
    
    self.textField.delegate = self;
    
    self.userDefaults = [NSUserDefaults standardUserDefaults];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)saveText:(id)sender {
    
    [self.userDefaults setObject:self.textField.text forKey:@"StringTeste"];
    [self.userDefaults synchronize];
    
    [self.textField resignFirstResponder];
}

- (IBAction)retrieveText:(id)sender {
    
    NSString *string = [self.userDefaults stringForKey:@"StringTeste"];
    
    [self.textField setText:string];
    
    [self.textField resignFirstResponder];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}

@end
