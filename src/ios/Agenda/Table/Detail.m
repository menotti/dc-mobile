//
//  Detail.m
//  Table
//
//  Created by Regis Zangirolami on 06/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import "Detail.h"
#import "DataContato.h"

@implementation Detail

@synthesize contato;

- (void)viewDidLoad
{
    [super viewDidLoad];
    // Do any additional setup after loading the view from its nib.
    
    self.navigationItem.title = @"Descrição";
    
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
        
    return 1;

}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section
{
    return 2;

}

- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    // Identifier for retrieving reusable cells. static NSString
    static NSString *MyIdentifier = @"MyIdentifier";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:MyIdentifier];
    // No cell available - create one.
    if(cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault
                                      reuseIdentifier:MyIdentifier];
    }
    
    // Set the text of the cell to the row index.
    NSString *fullname = [[NSString alloc] init];
    fullname = [contato.firstName stringByAppendingFormat:@" %@",contato.lastName];
    
    NSString *fullnumber = [[NSString alloc] init];
    fullnumber = [@"(" stringByAppendingFormat:@"%@) %@",[contato.numero substringToIndex:2],[contato.numero substringFromIndex:2]];
    
    NSMutableArray *textoArray = [[NSMutableArray alloc] initWithObjects:fullname, fullnumber, nil];

    cell.textLabel.text = [textoArray objectAtIndex:indexPath.row];
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    if (indexPath.row == 1)
    {
        if ([[[UIDevice currentDevice] model] isEqualToString:@"iPhone"])
        {
            NSString *phoneString = [[NSString alloc] initWithFormat:@"tel:%@",(NSString *)contato.numero];
            [[UIApplication sharedApplication] openURL:[NSURL URLWithString:phoneString]];
        }
        
        else
        {
            UIAlertView *alert=[[UIAlertView alloc] initWithTitle:@"Atenção"
                                                          message:@"Seu dispositivo não permite realizar ligações telefônicas."
                                                         delegate:nil
                                                cancelButtonTitle:@"OK"
                                                otherButtonTitles:nil];
            [alert show];
        }
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}
@end
