//
//  APIAudioViewController.m
//  API_Audio
//
//  Created by Caio Pegoraro on 27/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "APIAudioViewController.h"

@interface APIAudioViewController ()

@end

@implementation APIAudioViewController

- (id)initWithNibName:(NSString *)nibNameOrNil bundle:(NSBundle *)nibBundleOrNil
{
    self = [super initWithNibName:nibNameOrNil bundle:nibBundleOrNil];
    if (self) {
        // Custom initialization
    }
    return self;
}

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    [self configureAudioSession];
    
    [self configureAudioPlayer];
    
    [NSTimer scheduledTimerWithTimeInterval:0.1 target:self selector:@selector(updateSoundMeter) userInfo:nil repeats:YES];
}

- (IBAction)playMusic:(id)sender {
    if (self.audioPlayer.isPlaying) {
        // pause
        [_audioPlayer pause];
    }
    else {
        // play
        [_audioPlayer play];
    }
}

- (void)updateSoundMeter {
    
    if (self.audioPlayer.isPlaying) {
        [self.audioPlayer updateMeters];
        
        float power = 0.0f;
        for (int i = 0; i < [_audioPlayer numberOfChannels]; i++) {
            power += [_audioPlayer averagePowerForChannel:i];
        }
        power /= [_audioPlayer numberOfChannels];
        power = power/-30;
        
        NSLog(@"%f",power);
        
        [self.progressBar setProgress:power animated:YES];
    }
}

- (void)configureAudioSession {
    NSError *error;
    [[AVAudioSession sharedInstance] setCategory:AVAudioSessionCategoryPlayback error:&error];
}

- (void)configureAudioPlayer {
    NSURL *audioFileURL = [[NSBundle mainBundle] URLForResource:@"DemoSong" withExtension:@"m4a"];
    NSError *error;
    self.audioPlayer = [[AVAudioPlayer alloc] initWithContentsOfURL:audioFileURL error:&error];
    if (error) {
        NSLog(@"%@", [error localizedDescription]);
    }
    
    [_audioPlayer setMeteringEnabled:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
