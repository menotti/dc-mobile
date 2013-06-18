//
//  DataContato.m
//  Table
//
//  Created by Regis Zangirolami on 06/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import "DataContato.h"

@implementation DataContato

- (id)init
{
    return [self initWithFirstName:@"N/A"
                          lastName:@"N/A"
                            numero:@"N/A"];
}

- (id)initWithFirstName:(NSString *)aFirstName
               lastName:(NSString *)aLastName
                 numero:(NSString *)aNumero;
{    
    self.firstName = aFirstName;
    self.lastName = aLastName;
    self.numero = aNumero;
    
    return self;
}

@end
