//
//  APICacheViewController.m
//  API_Cache
//
//  Created by Caio Pegoraro on 05/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
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
    // Do any additional setup after loading the view from its nib.
    
    self.textField.delegate = self;
    
    //Inicialização do objeto NSUserDefaults
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
    [self.textField setText:@""];
    
    UIAlertView *alertaOk = [[UIAlertView alloc] initWithTitle:@"iOS Cache" message:@"Armazenado no cache com sucesso!" delegate:nil cancelButtonTitle:@"Fechar" otherButtonTitles:nil];
    
    [alertaOk show];
    [self.textField setText:@"Insira seu texto"];
}

- (IBAction)retrieveText:(id)sender {
    NSString *string = [self.userDefaults stringForKey:@"StringTeste"];
    
    [self.textField setText:string];
    [self.textField resignFirstResponder];
}

- (IBAction)clearBox:(id)sender {
    [self.textField setText:@""];
}

- (IBAction)completeBox:(id)sender {
    if([self.textField.text isEqualToString:@""]){
        [self.textField setText:@"Insira seu texto"];
    }
}


- (BOOL)textFieldShouldReturn:(UITextField *)textField {
    
    [textField resignFirstResponder];
    
    return YES;
}



@end
