//
//  SecondScreenViewController.m
//  FaceMala
//
//  Created by Caio Pegoraro on 30/09/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "SecondScreenViewController.h"
#import "FirstAppViewController.h"

@interface SecondScreenViewController ()

@end

@implementation SecondScreenViewController

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
    self.lblUsername.text = self.profileName;
    self.lblEmail.text = self.profileEmail;
    self.profilePicture.profileID = self.profilePic;
    self.lblProfileId.text = self.profileId;
    
    [self.lblProfileId setFont:[UIFont systemFontOfSize:9]];
    
    self.navigationItem.title = @"User";
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)compartilharAuto:(id)sender {
    NSMutableDictionary *params = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                                   @"Face Mala <auto>", @"name",
                                   @"Testando 123", @"caption",
                                   @"test descricao.", @"description",
                                   @"http://mobile.dc.ufscar.br/", @"link",
                                   @"http://mobile.dc.ufscar.br/img/LogoDC.jpg", @"picture",
                                   nil];
    
    [FBRequestConnection startWithGraphPath:@"/me/feed"
                                 parameters:params
                                 HTTPMethod:@"POST"
                          completionHandler:^(FBRequestConnection *connection, id result, NSError *error) {
                              if (!error) {
                                  
                                  NSLog(@"result: %@", result);
                              } else {
                                  
                                  NSLog(@"%@", error.description);
                              }
                          }];
}

- (IBAction)manualShare:(id)sender {
   
  
    
}

- (IBAction)openSuggestionScreen:(id)sender {
    SuggestionScreenViewController *suggestionScreen = [[SuggestionScreenViewController alloc] initWithNibName:@"SuggestionScreenViewController" bundle:nil];
    
    suggestionScreen.profileId = self.profileId;
    
    [self.navigationController pushViewController:suggestionScreen animated:true];
}

- (NSDictionary*)parseURLParams:(NSString *)query {
    NSArray *pairs = [query componentsSeparatedByString:@"&"];
    NSMutableDictionary *params = [[NSMutableDictionary alloc] init];
    for (NSString *pair in pairs) {
        NSArray *kv = [pair componentsSeparatedByString:@"="];
        NSString *val =
        [kv[1] stringByReplacingPercentEscapesUsingEncoding:NSUTF8StringEncoding];
        params[kv[0]] = val;
    }
    return params;
}
@end
