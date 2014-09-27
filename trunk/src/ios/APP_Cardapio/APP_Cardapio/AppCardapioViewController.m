//
//  AppCardapioViewController.m
//  APP_Cardapio
//
//  Created by Caio Pegoraro on 30/08/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "AppCardapioViewController.h"

@interface AppCardapioViewController ()

@end

@implementation AppCardapioViewController

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
    [self atualizarCardapio];
    // Do any additional setup after loading the view from its nib.
}

- (IBAction)atualizarCardapio;
{
    NSURL *url = [NSURL URLWithString:@"http://mobile.dc.ufscar.br/backend/"];
    NSURLRequest *request = [NSURLRequest requestWithURL:url];
    [NSURLConnection sendAsynchronousRequest:request
                                       queue:[NSOperationQueue mainQueue]
                           completionHandler:^(NSURLResponse *response,
                                               NSData *data, NSError *connectionError)
     {
         if (data.length > 0 && connectionError == nil)
         {
             NSDictionary *objetoRecebido = [NSJSONSerialization JSONObjectWithData:data
                                                                      options:0
                                                                        error:NULL];
             
             self.principal.text = [objetoRecebido objectForKey:@"principal"];
             self.guarnicao.text = [objetoRecebido objectForKey:@"guarnicao"];
             self.arroz.text = [objetoRecebido objectForKey:@"arroz"];
             self.feijao.text = [objetoRecebido objectForKey:@"feijao"];
             self.saladas.text = [objetoRecebido objectForKey:@"saladas"];
             self.sobremesa.text = [objetoRecebido objectForKey:@"sobremesa"];
             self.bebida.text = [objetoRecebido objectForKey:@"bebida"];
             
         }
     }];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
