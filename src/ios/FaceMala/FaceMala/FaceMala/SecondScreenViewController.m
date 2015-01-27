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

-(void)fetchNewDataWithCompletionHandler:(void (^)(UIBackgroundFetchResult))completionHandler{

    //Método para gerar o autopost e publicar via api na página do fb
    NSString *shortUrl = @"http://10.0.2.2/FaceMala/webservice/postGenerator.php?fb_id=";
    NSString *fullUrl  = [shortUrl stringByAppendingString:self.profileId];
    
    NSURL *url = [NSURL URLWithString:fullUrl];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    
    [NSURLConnection sendAsynchronousRequest:request
                                       queue:[NSOperationQueue mainQueue]
                           completionHandler:^(NSURLResponse *response,
                                               NSData *data, NSError *connectionError)
     {
         if (data.length > 0 && connectionError == nil)
         {
             NSDictionary *recieve = [NSJSONSerialization JSONObjectWithData:data
                                                                     options:0
                                                                       error:NULL];
             
             self.params = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                                            [recieve objectForKey:@"post_name"], @"name",
                                            [recieve objectForKey:@"post_caption"], @"caption",
                                            [recieve objectForKey:@"post_description"], @"description",
                                            [recieve objectForKey:@"post_link"], @"link",
                                            [recieve objectForKey:@"post_picture"], @"picture",
                                            nil];
         }
     }];
    
    
    
    [FBRequestConnection startWithGraphPath:@"/me/feed"
                                 parameters:self.params
                                 HTTPMethod:@"POST"
                          completionHandler:^(FBRequestConnection *connection, id result, NSError *error) {
                              if (!error) {
                                  
                                  NSLog(@"result: %@", result);
                              } else {
                                  
                                  NSLog(@"%@", error.description);
                              }
                          }];

            completionHandler(UIBackgroundFetchResultNewData);
            NSLog(@"New data.");
    
}

- (IBAction)manualShare:(id)sender {
   
    // Put together the dialog parameters
    NSMutableDictionary *params = [NSMutableDictionary dictionaryWithObjectsAndKeys:
                                   @"Face Mala <auto>", @"name",
                                   @"Comece a usar facemala tambem!", @"caption",
                                   @"best app ever!!.", @"description",
                                   @"http://mobile.dc.ufscar.br/", @"link",
                                   @"http://mobile.dc.ufscar.br/img/LogoDC.jpg", @"picture",
                                   nil];
    
    // Show the feed dialog
    [FBWebDialogs presentFeedDialogModallyWithSession:nil
             parameters:params handler:^(FBWebDialogResult result, NSURL *resultURL, NSError *error) {
                   if (error) {

                       NSLog(@"Error publishing story: %@", error.description);
                   } else {
                       if (result == FBWebDialogResultDialogNotCompleted) {
                         NSLog(@"User cancelled.");
                       } else {
                        NSDictionary *urlParams = [self parseURLParams:[resultURL query]];
                                                          
                            if (![urlParams valueForKey:@"post_id"]) {
                                 // User cancelled.
                              NSLog(@"User cancelled.");
                                                              
                               } else {
                                 // User clicked the Share button
                                NSString *result = [NSString stringWithFormat: @"Posted story, id: %@", [urlParams valueForKey:@"post_id"]];
                                NSLog(@"result %@", result);
                         }
                       }
                  }
              }];
    
}

- (IBAction)openSuggestionScreen:(id)sender {
    SuggestionScreenViewController *suggestionScreen = [[SuggestionScreenViewController alloc] initWithNibName:@"SuggestionScreenViewController" bundle:nil];
    
    suggestionScreen.profileId = self.profileId;
    
    [self.navigationController pushViewController:suggestionScreen animated:true];
}

- (IBAction)openCategoryScreen:(id)sender {
    CategoryScreenViewController *categoryScreen = [[CategoryScreenViewController alloc] initWithNibName:@"CategoryScreenViewController" bundle:nil];
    
    categoryScreen.profileId = self.profileId;
    
    [self.navigationController pushViewController:categoryScreen animated:true];
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
