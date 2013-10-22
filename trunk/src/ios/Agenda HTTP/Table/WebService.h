//
//  WebService.h
//  Agenda
//
//  Created by Régis on 21/10/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "AFHTTPClient.h"
#import "AFNetworking.h"

@interface WebService : AFHTTPClient

+ (id)sharedInstance;

- (void)connectionWithURL:(NSURL*) url method:(NSString*) method path:(NSString*) path httpClient:(AFHTTPClient*) httpClient parameters:(NSDictionary*) parameters completion:(void (^)(BOOL success, NSInteger message, id data)) completionBlock;

- (void)requestContactsWithCompletion:(void (^)(BOOL success, NSInteger message, id data))completionBlock;


@end
