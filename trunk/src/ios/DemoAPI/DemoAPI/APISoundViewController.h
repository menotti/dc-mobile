//
//  APISoundViewController.h
//  DemoAPI
//
//  Created by Régis on 12/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

@interface APISoundViewController : UIViewController

@property (weak, nonatomic) IBOutlet UIProgressView *progressBar;
@property (weak, nonatomic) IBOutlet UILabel *progressLabel;

@property (strong, nonatomic) AVAudioPlayer *audioPlayer;


- (IBAction)playMusic:(id)sender;

@end
