//
//  ThirdScreenViewController.h
//  FirstApp
//
//  Created by Regis Zangirolami on 15/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>

@protocol MessageDelegate <NSObject>

-(void)sendMessageFromTextField:(NSString*)message;

@end

@interface ThirdScreenViewController : UIViewController <UITextFieldDelegate>

@property (strong, nonatomic) IBOutlet UITextField *messageTextField;
@property (strong, nonatomic) IBOutlet UILabel *nameLabel;
@property (strong, nonatomic) IBOutlet UIButton *sendMessageButton;
- (IBAction)sendMessage:(id)sender;
@property (nonatomic, strong) NSString *textLabel;

@property (assign, nonatomic) id <MessageDelegate> delegate;

@end
