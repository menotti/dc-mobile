//
//  FirstAppViewController.h
//  FirstApp
//
//  Created by Caio Pegoraro on 11/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FirstAppViewController : UIViewController

-(IBAction)mostrarMsg;

- (IBAction)okTouched:(id)sender;

@property (weak, nonatomic) IBOutlet UIButton *botaoAlteraTexto;

@property (weak, nonatomic) IBOutlet UILabel *labelExemplo;

@end
