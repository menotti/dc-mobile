//
//  ThirdScreenViewController.h
//  FirstApp
//
//  Created by Caio Pegoraro on 14/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol MessageDelegate <NSObject>

-(void)sendMessageFromTextField:(NSString*)message;

@end

@interface ThirdScreenViewController : UIViewController <UITextFieldDelegate>

- (IBAction)sendMessage:(id)sender;

@property (weak, nonatomic) IBOutlet UITextField *textoTerceiraTela;

@property (assign, nonatomic) id <MessageDelegate> delegate;
@property (nonatomic, strong) NSString *textLabel;
@property (weak, nonatomic) IBOutlet UILabel *labelTela3;

@end
