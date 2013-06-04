//
//  FirstAppAppDelegate.h
//  FirstApp
//
//  Created by Regis Zangirolami on 08/05/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>

@class FirstAppViewController;

@interface FirstAppAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) FirstAppViewController *viewController;

@property (strong, nonatomic) UINavigationController *navController;

@end
