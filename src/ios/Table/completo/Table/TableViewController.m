//
//  TableViewController.m
//  Table
//
//  Created by Regis Zangirolami on 05/03/13.
//  Copyright (c) 2013 Regis Zangirolami. All rights reserved.
//

#import "TableViewController.h"

@implementation TableViewController

- (void)viewDidLoad
{
    [super viewDidLoad];
	// Do any additional setup after loading the view, typically from a nib.
    
    // título da Bar
    self.navigationItem.title = @"Contatos";
    
    // gravar pList em Dictionary
    // cria o path
    NSString *filePath = [[NSBundle mainBundle] pathForResource:@"contatos" ofType:@"plist"];
    
    self.dictionary = [[NSMutableDictionary alloc] initWithContentsOfFile:filePath];
    
    // método para montar Dictionary em Array ordenado
    [self setDictionaryArray];

}


// ORGANIZAÇÃO DA ESTRUTURA DOS CONTATOS

- (void)setDictionaryArray {
    
    NSMutableArray *tmpKey, *tmpContato;
    NSString *firstNameAux, *lastNameAux, *numeroAux;
    
    tmpKey = [[NSMutableArray alloc] init];
    NSSortDescriptor *sort = [NSSortDescriptor sortDescriptorWithKey:nil ascending:YES];
    
    // ler e ordenar Keys
    self.keysArray = [[NSMutableArray alloc] init];
    
    self.keysArray = [[NSMutableArray alloc] initWithArray:[self.dictionary allKeys]];
    [self.keysArray sortUsingDescriptors:[NSArray arrayWithObject:sort]];
    
    int countKeys = [self.keysArray count];

    // criando array de objetos DataContato
    NSMutableArray *arrayObj = [[NSMutableArray alloc] init];
                                
    for (int i=0; i<countKeys; i++)
    {
        // array com todos os contatos de uma letra
        tmpKey = [NSMutableArray arrayWithArray:[self.dictionary objectForKey:[self.keysArray objectAtIndex:i]]];
        
        NSMutableArray *arrayObjAux = [[NSMutableArray alloc] init];
        
        for (int j=0; j<[tmpKey count]; j++)
        {
            // arrays com todos os dados de cada contato
            tmpContato = [[NSMutableArray alloc] initWithArray:[tmpKey objectAtIndex:j]];

            // variáveis auxiliares para cada campo do contato
            firstNameAux = [[NSString alloc] initWithString:[tmpContato objectAtIndex:0]];
            lastNameAux = [[NSString alloc] initWithString:[tmpContato objectAtIndex:1]];
            numeroAux = [[NSString alloc] initWithString:[tmpContato objectAtIndex:2]];
            
            // criando o objeto
            DataContato *a = [[DataContato alloc] initWithFirstName:firstNameAux
                                                           lastName:lastNameAux
                                                             numero:numeroAux];
            
            // adicionando objeto de DataContato ao Array de determinada Key
            [arrayObjAux addObject:a];            
        }
        
        // adicionando Array de determinada Key ao Array maior
        [arrayObj addObject:arrayObjAux];
    }
 
    // método para ordenar os contatos
    [self sortObjArray:arrayObj];
}

- (void) sortObjArray:(NSMutableArray *)arrayObj {
    
    self.contatoObjArray = [[NSMutableArray alloc] init];
    
    // ordenar Nomes
    NSString *LASTNAME = @"lastName";
    NSString *FIRSTNAME = @"firstName";
    
    // Descriptor do sobrenome
    NSSortDescriptor *lastDescriptor =
    [[NSSortDescriptor alloc]
      initWithKey:LASTNAME
      ascending:YES
      selector:@selector(localizedCaseInsensitiveCompare:)];
    
    // Descriptor do nome
    NSSortDescriptor *firstDescriptor =
    [[NSSortDescriptor alloc]
      initWithKey:FIRSTNAME
      ascending:YES
      selector:@selector(localizedCaseInsensitiveCompare:)];
    
    NSArray * descriptors =
    [NSArray arrayWithObjects:firstDescriptor, lastDescriptor, nil];
    
    // adicionando os Arrays ordenados de cada Key ao Array principal    
    for(NSMutableArray* array in arrayObj)
        [self.contatoObjArray addObject:[array sortedArrayUsingDescriptors:descriptors]];

    [_table reloadData];
}


// VISUALIZAÇÃO DA TABELA

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView {
    
    if ([tableView
         isEqual:self.searchDisplayController.searchResultsTableView])
        return 1;
    else
        return [self.keysArray count];
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section
{
    if ([tableView
         isEqual:self.searchDisplayController.searchResultsTableView])
        return [self.searchList count];
    else
        return [[self.dictionary objectForKey:[self.keysArray objectAtIndex:section]] count];
}

- (NSString *)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    
    if ([tableView
         isEqual:self.searchDisplayController.searchResultsTableView])
        return @"Resultados";
    else
        return [self.keysArray objectAtIndex:section];
}

- (NSArray *)sectionIndexTitlesForTableView:(UITableView *)tableView {
    
    NSArray *keysCopyArray = [self.keysArray mutableCopy];
    
    return keysCopyArray;
}

- (UITableViewCell *) tableView:(UITableView *)tableView cellForRowAtIndexPath:(NSIndexPath *)indexPath {
    
    // Identifier for retrieving reusable cells. static NSString
    static NSString *MyIdentifier = @"MyIdentifier";
    
    UITableViewCell *cell = [tableView dequeueReusableCellWithIdentifier:MyIdentifier];
    // No cell available - create one.
    if(cell == nil) {
        cell = [[UITableViewCell alloc] initWithStyle:UITableViewCellStyleDefault
                                      reuseIdentifier:MyIdentifier];
    }

    if ([tableView isEqual:self.searchDisplayController.searchResultsTableView])
    {
        [self.table reloadData];
        DataContato *contato = [self.searchList objectAtIndex:indexPath.row];
        cell.textLabel.text = contato.firstName;
    }
    
    else
    {
        // Set the text of the cell to the row index.
        NSString *texto;
        DataContato *a;
        a = [[self.contatoObjArray objectAtIndex:indexPath.section] objectAtIndex:indexPath.row];
        texto = a.firstName;
        cell.textLabel.text = texto;
        
        UIImage *cellImage = [UIImage imageNamed:@"apple.png"];
        
        cell.imageView.image = cellImage;
    }
    
    return cell;
}


// AÇÕES NA TABELA

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {

    Detail *detailView = [[Detail alloc] initWithNibName:@"Detail" bundle:nil];
    
    if ([tableView
         isEqual:self.searchDisplayController.searchResultsTableView])
    {
        DataContato *a = [self.searchList objectAtIndex:indexPath.row];
        detailView.contato = a;
    }

    else
    {
        DataContato *a = [[self.contatoObjArray objectAtIndex:indexPath.section] objectAtIndex:indexPath.row];
        detailView.contato = a;
    }
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
    
    [self.navigationController pushViewController:detailView animated:YES];
    
}


// SEARCH BAR

- (BOOL)searchDisplayController:(UISearchDisplayController *)controller shouldReloadTableForSearchString:(NSString *)searchString {
    
    [self filterContentForSearchText:searchString];
    
    // Return YES to cause the search result table view to be reloaded.
    return YES;
}

- (BOOL)searchDisplayController:(UISearchDisplayController *)controller shouldReloadTableForSearchScope:(NSInteger)searchOption {
    
    [self filterContentForSearchText:[self.searchDisplayController.searchBar text]];
    
    // Return YES to cause the search result table view to be reloaded.
    return YES;
}

- (void)filterContentForSearchText:(NSString*)searchText {
    
    [self searchTable];
}

- (void) searchTable {
    
    NSString *searchText = _searchBar.text;
    NSMutableArray *searchArray = [[NSMutableArray alloc] init];
    self.searchList = [[NSMutableArray alloc] init];
    
    // cópia dos nomes em searchArray
    for (NSArray *array in self.contatoObjArray)
        for (DataContato *contato in array)
            [searchArray addObject:contato];
    
    // Range para buscar texto digitado nos nomes
    for (DataContato *contato in searchArray)
    {
        NSRange range = [contato.firstName rangeOfString:searchText options:NSCaseInsensitiveSearch];
        
        // encontrou algo
        if (range.length > 0)
            [self.searchList addObject:contato];
    }
    
}


- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
