//
//  TableViewController.h
//  Table
//
//  Created by Regis Zangirolami on 05/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import <UIKit/UIKit.h>
#import "DataContato.h"
#import "Detail.h"
#import "WebService.h"

@interface TableViewController : UIViewController <UITableViewDataSource,UITableViewDelegate,UISearchBarDelegate>

@property (weak, nonatomic) IBOutlet UISearchBar *searchBar;
@property (weak, nonatomic) IBOutlet UITableView *table;

@property (nonatomic, retain) NSMutableDictionary *dictionary;
@property (nonatomic, retain) NSMutableArray *keysArray;
@property (nonatomic, retain) NSMutableArray *contatoObjArray;
@property (nonatomic, retain) NSMutableArray *searchList;

- (void) setDictionaryArray;
- (void) sortObjArray:(NSMutableArray *)arrayObj;
- (void) searchTable;

@end