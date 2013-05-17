//
//  SecondScreenViewController.m
//  FirstApp
//
//  Created by Regis Zangirolami on 15/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "SecondScreenViewController.h"

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
    // Do any additional setup after loading the view from its nib.
    
    self.navigationController.title = @"Tela 2";
    
//    UIBarButtonItem *backButton = [[UIBarButtonItem alloc] initWithTitle:@"Voltar" style:UIBarButtonItemStyleBordered target:self action:@selector(backButtonTouched:)];
//    self.navigationItem.backBarButtonItem = backButton;
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)thirdScreen:(id)sender {
    
    // Cria próxima view
    self.thirdScreen = [[ThirdScreenViewController alloc] initWithNibName:@"ThirdScreenViewController" bundle:nil];
    
    self.thirdScreen.nameLabel.text = self.nameTextField.text;
    
    // Push da próxima view no Navigation Controller
    [self.navigationController pushViewController:self.thirdScreen animated:YES];
}

@end
