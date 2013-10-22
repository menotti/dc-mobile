//
//  DemoAPIAppDelegate.h
//  DemoAPI
//
//  Created by Régis on 14/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "APICallViewController.h"

@class DemoAPIViewController;

@interface DemoAPIAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) DemoAPIViewController *viewController;

@end
