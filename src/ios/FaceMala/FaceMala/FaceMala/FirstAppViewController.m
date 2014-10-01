//
//  FirstAppViewController.m
//  FaceMala
//
//  Created by Caio Pegoraro on 11/05/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "FirstAppViewController.h"

@interface FirstAppViewController ()



-(void)toggleHiddenState:(BOOL)shouldHide;

@end

@implementation FirstAppViewController

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
    
    [self toggleHiddenState:YES];
    
    self.navigationItem.title = @"FACE MALA";
    
    self.loginButton.delegate = self;
    self.loginButton.readPermissions = @[@"public_profile", @"email",@"publish_actions"];
    
    self.showProfile.enabled=false;
    
    // Do any additional setup after loading the view from its nib.
}

- (void)didReceiveMemoryWarning
{
    [FBLoginView class];
    [FBProfilePictureView class];
    
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

-(BOOL)application:(UIApplication *)application openURL:(NSURL *)url sourceApplication:(NSString *)sourceApplication annotation:(id)annotation{
    
    return [FBAppCall handleOpenURL:url
                  sourceApplication:sourceApplication];
}

#pragma mark - Private method implementation

-(void)toggleHiddenState:(BOOL)shouldHide{
    self.lblUsername.hidden = shouldHide;
    self.lblEmail.hidden = shouldHide;
    self.profilePicture.hidden = shouldHide;
}


#pragma mark - FBLoginView Delegate method implementation

-(void)loginViewShowingLoggedInUser:(FBLoginView *)loginView{
    //login ok
    self.showProfile.enabled = true;
    [self toggleHiddenState:NO];
}


-(void)loginViewFetchedUserInfo:(FBLoginView *)loginView user:(id<FBGraphUser>)user{
    NSLog(@"%@", user);
    
    self.profilePicture.profileID = user.objectID;
    self.lblUsername.text = user.name;
    self.lblEmail.text = [user objectForKey:@"email"];
    
}


-(void)loginViewShowingLoggedOutUser:(FBLoginView *)loginView{
    //logoff ok
    self.showProfile.enabled = false;
    [self toggleHiddenState:YES];
}


-(void)loginView:(FBLoginView *)loginView handleError:(NSError *)error{
    NSLog(@"%@", [error localizedDescription]);
}

- (IBAction)showProfileAction:(id)sender {
   SecondScreenViewController *secondScreen = [[SecondScreenViewController alloc] initWithNibName:@"SecondScreenViewController" bundle:nil];
    
    secondScreen.profileName = self.lblUsername.text;
    secondScreen.profileEmail = self.lblEmail.text;
    secondScreen.profilePic = self.profilePicture.profileID;
    
    [self.navigationController pushViewController:secondScreen animated:true];
}
@end
