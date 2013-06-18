//
//  DataContato.h
//  Table
//
//  Created by Regis Zangirolami on 06/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface DataContato : NSObject

@property (nonatomic, retain) NSString *firstName;
@property (nonatomic, retain) NSString *lastName;
@property (nonatomic, retain) NSString *numero;

- (id)initWithFirstName:(NSString *)aFirstName
               lastName:(NSString *)aLastName
                 numero:(NSString *)aNumero;

@end
