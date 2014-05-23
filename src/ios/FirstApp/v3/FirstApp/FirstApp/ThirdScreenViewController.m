//
//  ThirdScreenViewController.m
//  FirstApp
//
//  Created by Caio Pegoraro on 14/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
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
    self.navigationItem.title = @"Tela 3";
    
    if (![self.textLabel isEqualToString:@""]){
        self.labelTela3.text = self.textLabel;
    }
    else {
        self.labelTela3.text = @"Em branco";
    }
    
    self.textoTerceiraTela.delegate = self;
    
    
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)sendMessage:(id)sender {
    [self.delegate sendMessageFromTextField:
     self.textoTerceiraTela.text];
}

- (BOOL)textFieldShouldReturn:(UITextField *)textField { if (textField == self.textoTerceiraTela) {
    [textField resignFirstResponder];
}
    return YES;
}

@end
