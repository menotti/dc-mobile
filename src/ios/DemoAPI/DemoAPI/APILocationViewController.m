//
//  APILocationViewController.m
//  DemoAPI
//
//  Created by Régis on 01/11/13.
//  Copyright (c) 2013 UFSCar. All rights reserved.
//

#import "APILocationViewController.h"

@interface APILocationViewController ()

@end

@implementation APILocationViewController

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
    
    self.locationManager = [[CLLocationManager alloc] init];
    self.geocoder = [[CLGeocoder alloc] init];
}

- (void)didReceiveMemoryWarning
{
    [super didReceiveMemoryWarning];
    // Dispose of any resources that can be recreated.
}

- (IBAction)getCurrentLocation:(id)sender {
    self.locationManager.delegate = self;
    self.locationManager.desiredAccuracy = kCLLocationAccuracyBest;
    
    [self.locationManager startUpdatingLocation];
}

- (void)locationManager:(CLLocationManager *)manager didFailWithError:(NSError *)error
{
    UIAlertView *errorAlert = [[UIAlertView alloc]
                               initWithTitle:@"Erro" message:@"Não foi possível identificar sua localização" delegate:nil cancelButtonTitle:@"Ok" otherButtonTitles:nil];
    [errorAlert show];
}

- (void)locationManager:(CLLocationManager *)manager didUpdateToLocation:(CLLocation *)newLocation fromLocation:(CLLocation *)oldLocation
{
    CLLocation *currentLocation = newLocation;
    
    if (currentLocation != nil) {
        self.longitudeLabel.text = [NSString stringWithFormat:@"%.8f", currentLocation.coordinate.longitude];
        self.latitudeLabel.text = [NSString stringWithFormat:@"%.8f", currentLocation.coordinate.latitude];
    }
    
    [self.locationManager stopUpdatingLocation];
    
    [self.geocoder reverseGeocodeLocation:currentLocation completionHandler:^(NSArray *placemarks, NSError *error) {
        NSLog(@"Found placemarks: %@, error: %@", placemarks, error);
        if (error == nil && [placemarks count] > 0) {
            self.placemark = [placemarks lastObject];
            self.addressLabel.text = [NSString stringWithFormat:@"%@\n%@\n%@\n%@\n%@",
                                 self.placemark.thoroughfare,
                                 self.placemark.postalCode,
                                 self.placemark.locality,
                                 self.placemark.administrativeArea,
                                 self.placemark.country];
        } else {
            NSLog(@"%@", error.debugDescription);
        }
    } ];
}

@end
