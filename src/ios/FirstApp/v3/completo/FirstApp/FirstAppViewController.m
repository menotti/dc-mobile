//
//  FirstAppViewController.m
//  FirstApp
//
//  Created by Regis Zangirolami on 08/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "FirstAppViewController.h"

@interface FirstAppViewController ()

@end

@implementation FirstAppViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    self.navigationItem.title = @"Tela 1";
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)okTouched:(id)sender {
    
    NSString *aux = [[NSString alloc] initWithString:self.exemploLabel.text];
    self.exemploLabel.text = self.okButton.currentTitle;
    [self.okButton setTitle:aux forState:UIControlStateNormal];
}

- (IBAction)secondScreenTouched:(id)sender {
    
    // Cria próxima view
    self.secondScreen = [[SecondScreenViewController alloc] initWithNibName:@"SecondScreenViewController" bundle:nil];
    
    // Push da próxima view no Navigation Controller
    [self.navigationController pushViewController:self.secondScreen animated:YES];
}
@end
