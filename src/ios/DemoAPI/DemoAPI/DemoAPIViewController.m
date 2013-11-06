//
//  DemoAPIViewController.m
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "DemoAPIViewController.h"

@interface DemoAPIViewController ()

@end

@implementation DemoAPIViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    
    self.count = 9;
    
    [self.scrollView setContentSize:CGSizeMake(self.scrollView.frame.size.width*self.count, 0)];
    [self.scrollView setScrollsToTop:NO];
    
    [self loadCallAPI];
    [self loadSmsAPI];
    [self loadCacheAPI];
    [self loadSoundAPI];
    [self loadCameraAPI];
    [self loadContactsAPI];
    [self loadCalendarAPI];
    [self loadSocialAPI];
    [self loadMotionAPI];
}

- (void)loadCallAPI {
    APICallViewController *myAPIViewController = [[APICallViewController alloc] initWithNibName:@"APICallViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 1;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadSmsAPI {
    APISmsViewController *myAPIViewController = [[APISmsViewController alloc] initWithNibName:@"APISmsViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 2;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadCacheAPI {
    APICacheViewController *myAPIViewController = [[APICacheViewController alloc] initWithNibName:@"APICacheViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 3;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadSoundAPI {
    APISoundViewController *myAPIViewController = [[APISoundViewController alloc] initWithNibName:@"APISoundViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 4;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadCameraAPI {
    APICameraViewController *myAPIViewController = [[APICameraViewController alloc] initWithNibName:@"APICameraViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 5;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadContactsAPI {
    APIContactsViewController *myAPIViewController = [[APIContactsViewController alloc] initWithNibName:@"APIContactsViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 6;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadCalendarAPI {
    APICalendarViewController *myAPIViewController = [[APICalendarViewController alloc] initWithNibName:@"APICalendarViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 7;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadSocialAPI {
    APISocialViewController *myAPIViewController = [[APISocialViewController alloc] initWithNibName:@"APISocialViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 8;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)loadMotionAPI {
    APIMotionViewController *myAPIViewController = [[APIMotionViewController alloc] initWithNibName:@"APIMotionViewController" bundle:nil];
    
    [self addChildViewController:myAPIViewController];
    
    NSInteger apiId = 9;
    CGRect frame = self.scrollView.frame;
    frame.origin.x = frame.size.width*(apiId-1);
    frame.origin.y = 0;
    myAPIViewController.view.frame = frame;
    
    [myAPIViewController.progressLabel setText:[NSString stringWithFormat:@"%d/%d",apiId,self.count]];
    
    [self.scrollView addSubview:myAPIViewController.view];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
