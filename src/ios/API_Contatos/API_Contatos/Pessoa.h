//
//  Pessoa.h
//  API_Contatos
//
//  Created by Caio Pegoraro on 04/07/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import <Foundation/Foundation.h>

@interface Pessoa : NSObject

@property (nonatomic, strong) NSString *primeiroNome;
@property (nonatomic, strong) NSString *ultimoNome;
@property (nonatomic, strong) NSString *nomeCompleto;
@property (nonatomic, strong) NSString *email;
@property (nonatomic, strong) NSString *celular;

@end
