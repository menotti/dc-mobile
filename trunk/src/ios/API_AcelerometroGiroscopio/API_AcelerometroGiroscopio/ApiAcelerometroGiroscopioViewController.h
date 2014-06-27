//
//  ApiAcelerometroGiroscopioViewController.h
//  API_AcelerometroGiroscopio
//
//  Created by Caio Pegoraro on 27/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <CoreMotion/CoreMotion.h>

@interface ApiAcelerometroGiroscopioViewController : UIViewController

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

@end
