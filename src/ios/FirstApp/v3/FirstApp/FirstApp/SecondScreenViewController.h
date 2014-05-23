//
//  SecondScreenViewController.h
//  FirstApp
//
//  Created by Caio Pegoraro on 14/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "ThirdScreenViewController.h"

@interface SecondScreenViewController : UIViewController
                    <UITextFieldDelegate,MessageDelegate>

- (IBAction)ThirdScreenTouched:(id)sender;

@property (weak, nonatomic) IBOutlet UITextField *labelTexto2;

@property (weak, nonatomic) IBOutlet UILabel *textoSegundaTela;


@end
