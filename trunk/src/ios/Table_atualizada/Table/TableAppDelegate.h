//
//  TableAppDelegate.h
//  Table
//
//  Created by Regis Zangirolami on 05/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import <UIKit/UIKit.h>

@class TableViewController;

@interface TableAppDelegate : UIResponder <UIApplicationDelegate>

@property (strong, nonatomic) UIWindow *window;

@property (strong, nonatomic) TableViewController *viewController;

@property (strong, nonatomic) UINavigationController *navController;

@end
