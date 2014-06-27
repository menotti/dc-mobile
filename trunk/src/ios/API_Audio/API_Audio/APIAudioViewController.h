//
//  APIAudioViewController.h
//  API_Audio
//
//  Created by Caio Pegoraro on 27/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

@interface APIAudioViewController : UIViewController

@property (strong, nonatomic) AVAudioPlayer *audioPlayer;
@property (weak, nonatomic) IBOutlet UIProgressView *progressBar;

- (IBAction)playMusic:(id)sender;

@end
