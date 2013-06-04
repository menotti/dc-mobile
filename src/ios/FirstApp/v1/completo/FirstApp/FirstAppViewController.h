//
//  FirstAppViewController.h
//  FirstApp
//
//  Created by Regis Zangirolami on 08/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>

@interface FirstAppViewController : UIViewController

@property (strong, nonatomic) IBOutlet UILabel *exemploLabel;

@property (strong, nonatomic) IBOutlet UIButton *okButton;

- (IBAction)okTouched:(id)sender;

@end
