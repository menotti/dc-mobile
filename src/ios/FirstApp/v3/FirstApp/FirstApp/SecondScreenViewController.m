//
//  SecondScreenViewController.m
//  FirstApp
//
//  Created by Caio Pegoraro on 14/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "SecondScreenViewController.h"
#import "ThirdScreenViewController.h"

@interface SecondScreenViewController ()

@end

@implementation SecondScreenViewController

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
    self.navigationItem.title = @"Tela 2";
    self.labelTexto2.delegate = self;
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)ThirdScreenTouched:(id)sender {
    ThirdScreenViewController *ThirdViewController =
    [[ThirdScreenViewController alloc]
     initWithNibName:@"ThirdScreenViewController" bundle:nil];
    
    ThirdViewController.textLabel = self.labelTexto2.text;
    
   //
    ThirdViewController.delegate = self;
    
    [self.navigationController pushViewController:ThirdViewController animated:true];
}

- (BOOL) textFieldShouldReturn:(UITextField *)textField{
    if(textField == self.labelTexto2){
        [textField resignFirstResponder];
    }
    return YES;
    
}

-(void)sendMessageFromTextField:(NSString *)message { [self.navigationController popToViewController:self animated:YES];
    self.textoSegundaTela.text = message;
}
@end
