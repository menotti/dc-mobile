//
//  DemoAPIViewController.h
//  DemoAPI
//
//  Created by Régis on 10/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "APICallViewController.h"
#import "APISmsViewController.h"
#import "APICacheViewController.h"
#import "APISoundViewController.h"
#import "APICameraViewController.h"
#import "APIContactsViewController.h"
#import "APICalendarViewController.h"
#import "APISocialViewController.h"
#import "APIMotionViewController.h"

@interface DemoAPIViewController : UIViewController

@property (weak, nonatomic) IBOutlet UIScrollView *scrollView;

@property (nonatomic) NSInteger count;

@end
