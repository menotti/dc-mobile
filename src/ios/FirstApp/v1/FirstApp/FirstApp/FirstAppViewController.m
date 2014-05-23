//
//  FirstAppViewController.m
//  FirstApp
//
//  Created by Caio Pegoraro on 11/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "FirstAppViewController.h"

@interface FirstAppViewController ()

@end

@implementation FirstAppViewController

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
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(IBAction)mostrarMsg {
    UIAlertView *AlertaOlaMundo = [[UIAlertView alloc] initWithTitle:@"Meu primeiro app" message:@"Olá mundo!" delegate:nil cancelButtonTitle:@"Fechar" otherButtonTitles:nil];
    
	[AlertaOlaMundo show];
}

@end
