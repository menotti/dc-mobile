//
//  WebService.m
//  Agenda
//
//  Created by Régis on 21/10/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import "WebService.h"

@implementation WebService

- (id)initWithBaseURL:(NSURL *)url
{
    self = [super initWithBaseURL:url];
    if(!self)
        return nil;
    
    [self registerHTTPOperationClass:[AFJSONRequestOperation class]];
    [self setDefaultHeader:@"Accept" value:@"application/json"];
    [self setParameterEncoding:AFJSONParameterEncoding];
    
    [[AFNetworkActivityIndicatorManager sharedManager] setEnabled:YES];
    
    return self;
}

// Get the shared instance and create it if necessary.
+ (WebService *)sharedInstance
{
    static dispatch_once_t pred;
    static WebService *_sharedManager = nil;
    
    dispatch_once(&pred, ^{ _sharedManager = [[self alloc] initWithBaseURL:[NSURL URLWithString:@"https://dl.dropboxusercontent.com"]]; }); // You should probably make this a constant somewhere
    return _sharedManager;
}

//do all the request of service
- (void)connectionWithURL:(NSURL*) url method:(NSString*) method path:(NSString*) path httpClient:(AFHTTPClient*) httpClient parameters:(NSDictionary*) parameters completion:(void (^)(BOOL success, NSInteger message, id data)) completionBlock
{
    
    NSLog(@"WS - Chamando:[%@]", [url absoluteString]);
    
    NSMutableURLRequest *request = [httpClient requestWithMethod:method path:path parameters:parameters];
    
    AFJSONRequestOperation *operation = [AFJSONRequestOperation JSONRequestOperationWithRequest:request
                                                                                        success:^(NSURLRequest *request, NSHTTPURLResponse *response, id JSON){
                                                                                            NSLog(@"WS - Resposta:[%@]", JSON);
                                                                                            completionBlock(YES, 0, JSON);
                                                                                            NSLog(@"Success");
                                                                                            
                                                                                        } failure:^(NSURLRequest *request, NSHTTPURLResponse *response, NSError *error, id JSON) {
                                                                                            completionBlock(NO, response.statusCode, JSON);
                                                                                            NSLog(@"Request Failed with Error: %@, %@", error, error.userInfo);
                                                                                            NSLog(@"Failure");
                                                                                        }];
    
    [AFJSONRequestOperation addAcceptableContentTypes:[NSSet setWithObject:@"text/plain"]];
    
    [operation start];
    [operation waitUntilFinished];
    
}

- (void)requestContactsWithCompletion:(void (^)(BOOL success, NSInteger message, id data))completionBlock
{
    BOOL state;
    state = NO;
    NSURL *url = [[NSURL alloc] initWithString:@"https://dl.dropboxusercontent.com"];
    
    AFHTTPClient *httpClient = [[AFHTTPClient alloc] initWithBaseURL:url];
    [httpClient registerHTTPOperationClass:[AFJSONRequestOperation class]];
    [httpClient setDefaultHeader:@"Accept" value:@"application/json"];
    [httpClient setParameterEncoding:AFFormURLParameterEncoding];
    NSDictionary *parameters = [[NSDictionary alloc] init];
    
    [self connectionWithURL:url method:@"POST" path:@"/u/57270874/contatos.json" httpClient:httpClient parameters:parameters completion:completionBlock];
}


@end
