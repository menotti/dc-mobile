//
//  APICacheViewController.h
//  API_Cache
//
//  Created by Caio Pegoraro on 05/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface APICacheViewController : UIViewController
                            <UITextFieldDelegate>

//Objeto NSUserDefaults
@property (nonatomic, strong) NSUserDefaults *userDefaults;

//Outlet para capturar o texto da textbox
@property (weak, nonatomic) IBOutlet UITextField *textField;

//Actions para gravar e recuperar dados da memória cache
- (IBAction)saveText:(id)sender;
- (IBAction)retrieveText:(id)sender;

//Actions para limpar o texto da textbox quando clicamos para digitar nela e outro para completar com o texto default quando fechamos a caixa de edição sem digitar nada.
- (IBAction)clearBox:(id)sender;
- (IBAction)completeBox:(id)sender;

@end
