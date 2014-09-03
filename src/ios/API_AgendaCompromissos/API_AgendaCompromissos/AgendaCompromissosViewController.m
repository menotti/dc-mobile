//
//  AgendaCompromissosViewController.m
//  API_AgendaCompromissos
//
//  Created by Caio Pegoraro on 02/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "AgendaCompromissosViewController.h"
#import "AddCalendarioViewController.h"
#import "AddEventoViewController.h"

@interface AgendaCompromissosViewController ()

@end

@implementation AgendaCompromissosViewController

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
    self.navigationItem.title = @"Tela Inicial";
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)telaCalendario:(id)sender {
                                                  
    AddCalendarioViewController *AddCalendario =
            [[AddCalendarioViewController alloc]
            initWithNibName:@"AddCalendarioViewController" bundle:nil];
    
    [self.navigationController pushViewController: AddCalendario animated:true];
    
}

- (IBAction)telaEvento:(id)sender {
    
    AddEventoViewController *AddEvento =
        [[AddEventoViewController alloc]
         initWithNibName:@"AddEventoViewController" bundle:nil];
    
    [self.navigationController pushViewController: AddEvento animated:true];
}
@end
