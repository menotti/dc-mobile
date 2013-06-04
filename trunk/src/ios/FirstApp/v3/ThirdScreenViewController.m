//
//  ThirdScreenViewController.m
//  FirstApp
//
//  Created by Regis Zangirolami on 15/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "ThirdScreenViewController.h"

@interface ThirdScreenViewController ()

@end

@implementation ThirdScreenViewController

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
    
    if (![self.textLabel isEqualToString:@""]) {
        self.nameLabel.text = self.textLabel;
    } else {
        self.nameLabel.text = @"Sem nome";
    }
    
    self.messageTextField.delegate = self;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    if (textField == self.messageTextField) {
        [textField resignFirstResponder];
    }
    
    return YES;
}

- (IBAction)sendMessage:(id)sender {
    
    [self.delegate sendMessageFromTextField:self.messageTextField.text];
}
@end
