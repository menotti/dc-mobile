//
//  APIContactsViewController.m
//  DemoAPI
//
//  Created by Régis on 12/10/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APIContactsViewController.h"

@interface APIContactsViewController ()

@end

@implementation APIContactsViewController

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
    
    self.contactList = [[NSMutableArray alloc] init];
    
    CFErrorRef err;
    ABAddressBookRef m_addressbook = ABAddressBookCreateWithOptions(NULL, &err);
    
    CFArrayRef allPeople = ABAddressBookCopyArrayOfAllPeople(m_addressbook);
    CFIndex nPeople = ABAddressBookGetPersonCount(m_addressbook);
    
    for (int i=0;i<nPeople; i++) {
        NSMutableDictionary *dOfPerson=[NSMutableDictionary dictionary];
        ABRecordRef ref = CFArrayGetValueAtIndex(allPeople,i);
        
        // nome
        ABMultiValueRef phones = ABRecordCopyValue(ref, kABPersonPhoneProperty);
        CFStringRef firstName, lastName;
        firstName = ABRecordCopyValue(ref, kABPersonFirstNameProperty);
        lastName  = ABRecordCopyValue(ref, kABPersonLastNameProperty);
        [dOfPerson setObject:[NSString stringWithFormat:@"%@ %@", firstName, lastName] forKey:@"nome"];
        
        // email
        ABMutableMultiValueRef eMail  = ABRecordCopyValue(ref, kABPersonEmailProperty);
        if(ABMultiValueGetCount(eMail)>0) {
            [dOfPerson setObject:(__bridge NSString *)ABMultiValueCopyValueAtIndex(eMail, 0) forKey:@"email"];
        }
        
        // telefone
        NSString* mobileLabel;
        mobileLabel = (__bridge NSString*)ABMultiValueCopyLabelAtIndex(phones, 0);
        if([mobileLabel isEqualToString:(NSString *)kABPersonPhoneMobileLabel])
        {
            [dOfPerson setObject:(__bridge NSString*)ABMultiValueCopyValueAtIndex(phones, 0) forKey:@"fone"];
        }
        
        [self.contactList addObject:dOfPerson];
        CFRelease(ref);
        CFRelease(firstName);
        CFRelease(lastName);
    }
    
    NSLog(@"Contatos:%@",self.contactList);
}

- (NSInteger)numberOfSectionsInTableView:(UITableView *)tableView
{
    return 1;
}

- (NSInteger)tableView:(UITableView *)tableView
 numberOfRowsInSection:(NSInteger)section
{
    return [self.contactList count];
}

- (NSString*)tableView:(UITableView *)tableView titleForHeaderInSection:(NSInteger)section {
    return @"Contatos";
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
    
    // Set the text of the cell to the row index.
    NSString *texto;
    
    texto = [[self.contactList objectAtIndex:indexPath.row] objectForKey:@"nome"];
    cell.textLabel.text = texto;
    
    return cell;
}

- (void)tableView:(UITableView *)tableView didSelectRowAtIndexPath:(NSIndexPath *)indexPath {
    
    NSString *email = [[self.contactList objectAtIndex:indexPath.row] objectForKey:@"email"];
    NSString *fone = [[self.contactList objectAtIndex:indexPath.row] objectForKey:@"fone"];
    NSString *texto = [NSString stringWithFormat:@"Email: %@ - Telefone: %@",email,fone];
    
    UIAlertView *alert = [[UIAlertView alloc] initWithTitle:@"Contato"
                                                    message:texto
                                                   delegate:nil
                                          cancelButtonTitle:@"OK"
                                          otherButtonTitles: nil];
    
    [alert show];
    
    [tableView deselectRowAtIndexPath:indexPath animated:YES];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

@end
