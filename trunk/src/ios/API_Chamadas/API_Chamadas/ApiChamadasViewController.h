//
//  ApiChamadasViewController.h
//  API_Chamadas
//
//  Created by Caio Pegoraro on 20/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface ApiChamadasViewController : UIViewController <UITextFieldDelegate>

@property (weak, nonatomic) IBOutlet UITextField *phoneField;

- (IBAction)makeCall:(id)sender;

@end
