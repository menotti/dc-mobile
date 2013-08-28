//
//  Detail.h
//  Table
//
//  Created by Regis Zangirolami on 06/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DataContato.h"

@interface Detail : UIViewController <UITableViewDataSource,UITableViewDelegate,UISearchBarDelegate>

@property (weak, nonatomic) IBOutlet UITableView *tableDetail;

@property (nonatomic, retain) DataContato *contato;

@end
