//
//  APIMotionViewController.h
//  DemoAPI
//
//  Created by Régis on 27/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreMotion/CoreMotion.h>

@interface APIMotionViewController : UIViewController

@property (weak, nonatomic) IBOutlet UILabel *accX;
@property (weak, nonatomic) IBOutlet UILabel *accY;
@property (weak, nonatomic) IBOutlet UILabel *accZ;
@property (weak, nonatomic) IBOutlet UILabel *rotX;
@property (weak, nonatomic) IBOutlet UILabel *rotY;
@property (weak, nonatomic) IBOutlet UILabel *rotZ;
@property (weak, nonatomic) IBOutlet UILabel *maxAccX;
@property (weak, nonatomic) IBOutlet UILabel *maxAccY;
@property (weak, nonatomic) IBOutlet UILabel *maxAccZ;
@property (weak, nonatomic) IBOutlet UILabel *maxRotX;
@property (weak, nonatomic) IBOutlet UILabel *maxRotY;
@property (weak, nonatomic) IBOutlet UILabel *maxRotZ;

@property (strong, nonatomic) CMMotionManager *motionManager;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

- (IBAction)resetValues:(id)sender;

@end
