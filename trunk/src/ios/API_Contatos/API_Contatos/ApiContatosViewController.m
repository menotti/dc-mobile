//
//  ApiContatosViewController.m
//  API_Contatos
//
//  Created by Caio Pegoraro on 19/06/14.
//  Copyright (c) 2014 Ufscar. All rights reserved.
//

#import "ApiContatosViewController.h"
#import <AddressBook/AddressBook.h>
#import "Pessoa.h"

@interface ApiContatosViewController () <UITableViewDataSource, UITableViewDelegate>
@property (nonatomic, strong) NSMutableArray *tabelaPessoas;

@end

@implementation ApiContatosViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
    self.title = @"Contatos";
    self.tabelaPessoas = [[NSMutableArray alloc] init];
    [self listarContatosSalvos];
    
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (void)listarContatosSalvos
{
    CFErrorRef erro = NULL;
    
    ABAddressBookRef contatos = ABAddressBookCreateWithOptions(NULL, &erro);
    
    if (contatos != nil)
    {
        //Existem contatos salvos
        NSArray *todosContatos = (__bridge_transfer NSArray *)ABAddressBookCopyArrayOfAllPeople(contatos);
        
        NSUInteger i = 0;
        for (i = 0; i < [todosContatos count]; i++)
        {
            Pessoa *pessoa = [[Pessoa alloc] init];
            
            ABRecordRef contatoPessoa = (__bridge ABRecordRef)todosContatos[i];
            
            NSString *primeiroNome = (__bridge_transfer NSString *)ABRecordCopyValue(contatoPessoa, kABPersonFirstNameProperty);
            NSString *ultimoNome =  (__bridge_transfer NSString *)ABRecordCopyValue(contatoPessoa, kABPersonLastNameProperty);
            NSString *nomeCompleto = [NSString stringWithFormat:@"%@ %@", primeiroNome, ultimoNome];
            
            pessoa.primeiroNome = primeiroNome;
            pessoa.ultimoNome = ultimoNome;
            pessoa.nomeCompleto = nomeCompleto;
            
            //código para pegar o email cadastrado
            ABMultiValueRef emails = ABRecordCopyValue(contatoPessoa, kABPersonEmailProperty);
            pessoa.email = (__bridge_transfer NSString *)ABMultiValueCopyValueAtIndex(emails, 0);
            
            //código para pegar o celular cadastrado
            ABMultiValueRef celulares = ABRecordCopyValue(contatoPessoa, kABPersonPhoneProperty);
            pessoa.celular = (__bridge_transfer NSString *)ABMultiValueCopyValueAtIndex(celulares, 0);
            
            [self.tabelaPessoas addObject:pessoa];
            
            
        }
    }
    
    CFRelease(contatos);
}

- (NSInteger)tableView:(UITableView *)tableView numberOfRowsInSection:(NSInteger)section
{
    return [self.tabelaPessoas count];
}

- (UITableViewCell *)tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath
{
    static NSString *cellIdentifier = @"Identificador";
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:cellIdentifier];
    if (cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault reuseIdentifier:cellIdentifier];
    }
    
    Pessoa *pessoa = [self.tabelaPessoas objectAtIndex:indexPath.row];
    cell.textLabel.text = pessoa.nomeCompleto;
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath
{
    Pessoa *pessoa = [self.tabelaPessoas objectAtIndex:indexPath.row];
    
    NSString *detalhesContato = [NSString stringWithFormat:@"NOME: %@\n CEL: %@\n EMAIL: %@\n",pessoa.nomeCompleto,pessoa.celular,pessoa.email];
    UIAlertView *detalhesBox = [[UIAlertView alloc] initWithTitle:@"Contato selecionado" message:detalhesContato delegate:self cancelButtonTitle:@"Sair" otherButtonTitles:nil];
    
    [detalhesBox show];
    
}


@end
