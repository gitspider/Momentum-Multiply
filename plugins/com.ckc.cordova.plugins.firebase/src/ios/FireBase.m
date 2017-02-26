//
//  CDVFirebase.m
//  Hello World
//
//  Created by Simba on 10/6/15.
//
//

#import "FireBase.h"
#import <Firebase/Firebase.h>

@interface CDVFirebase () {
    NSString *appName;
    BOOL isUsed;
}
@end

@implementation CDVFirebase

- (void)init:(CDVInvokedUrlCommand*)command {
    NSString*       callback;
    callback = command.callbackId;
    
    if ( [command.arguments count] >= 1 )
    {
        appName = [command.arguments objectAtIndex:0];
    }
    
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:callback];

}

- (void)setPersistenceEnabled:(CDVInvokedUrlCommand*)command {
    //
    NSString*       callback;
    callback = command.callbackId;
    
    if (isUsed == YES) {
        CDVPluginResult* result = [CDVPluginResult
                                   resultWithStatus: CDVCommandStatus_ERROR
                                   messageAsString: @"Can't modify config objects after they are in use for Firebase references."
                                   ];
        
        [self.commandDelegate sendPluginResult:result callbackId:callback];
        return;
    }
    
    BOOL persistenceEnabled = YES;
    if ( [command.arguments count] >= 1 )
    {
        persistenceEnabled = [[command.arguments objectAtIndex:0] boolValue];
    }
    [[Firebase defaultConfig] setPersistenceEnabled:persistenceEnabled];
    
    CDVPluginResult *result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:callback];
}

- (void)readData:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        object = [command.arguments objectAtIndex:0];
    }
    
//    capabilityError = [self isScanNotPossible];
//    if (capabilityError) {
//        CDVPluginResult* result = [CDVPluginResult
//                                   resultWithStatus: CDVCommandStatus_ERROR
//                                   messageAsString: capabilityError
//                                   ];
//        
//        [self.commandDelegate sendPluginResult:result callbackId:callback];
//        
//        return;
//    }
    
    Firebase *myRootRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Read data and react to changes
    [myRootRef observeEventType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        
    } withCancelBlock:^(NSError *error) {
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}
//Reading Data Once
- (void)readDataOnceWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    //NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        //object = [command.arguments objectAtIndex:0];
        strURL = [command.arguments objectAtIndex:0];
    }
    
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Read data and react to changes
    [myChildRef observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        //[result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];

}
//remove all callbacks at a location
- (void)removeAllCallbacksWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
   if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    
    [myChildRef removeAllObservers];
    
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

// Attach a block to read the data at our posts reference with detailed URL
- (void)readValueTypeEventWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    //NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        //object = [command.arguments objectAtIndex:0];
        strURL = [command.arguments objectAtIndex:0];
    }
    
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Read data and react to changes
    [myChildRef observeEventType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}
// Attach a block to retrieve the data at our reference with detailed URL when child added Evens are triggered
- (void)RetrieveChildAddedEventWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    //NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        //object = [command.arguments objectAtIndex:0];
        strURL = [command.arguments objectAtIndex:0];
    }
    
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Retrieve new posts as they are added to the database
    [myChildRef observeEventType:FEventTypeChildAdded withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"Triggered child added Evet");
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

// Attach a block to retrieve the data at our reference with detailed URL when child changed Event are triggered
- (void)RetrieveChildChangedEventWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    //NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        //object = [command.arguments objectAtIndex:0];
        strURL = [command.arguments objectAtIndex:0];
    }
    
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Retrieve new posts as they are added to the database
    [myChildRef observeEventType:FEventTypeChildChanged withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"Triggered Child Change Event");
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

// Attach a block to retrieve the data at our reference with detailed URL when child removed Event are triggered
- (void)RetrieveChildRemovedEventWithURL:(CDVInvokedUrlCommand*)command {
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    //NSObject *object;
    if ( [command.arguments count] >= 1 )
    {
        //object = [command.arguments objectAtIndex:0];
        strURL = [command.arguments objectAtIndex:0];
    }
    
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl: strURL];
    if(isUsed != YES)
        isUsed = YES;
    // Retrieve new posts as they are added to the database
    [myChildRef observeEventType:FEventTypeChildRemoved withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"Triggered Child Removed Event");
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [result setKeepCallback:[NSNumber numberWithBool:YES]];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//
- (void)writeData:(CDVInvokedUrlCommand*)command {
    //
    id value = nil;
    if ( [command.arguments count] >= 1 )
    {
        value = [command.arguments objectAtIndex:0];
    }
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    Firebase *myRootRef = [[Firebase alloc] initWithUrl:strURL];
    isUsed = YES;
    
    // Write data to Firebase
    //[myRootRef setValue: value];
    [myRootRef setValue:value withCompletionBlock:^(NSError *error, Firebase *myRootRef) {
        CDVPluginResult *result;
        if (error) {
            //NSLog(@"Data could not be saved.");
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        } else {
            //NSLog(@"Data saved successfully.");
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

- (void)writeValueToURL:(CDVInvokedUrlCommand*)command {
    //
    id value = nil;
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }
    
    Firebase *myChildRef = [[Firebase alloc] initWithUrl:strURL];
    isUsed = YES;
    
    // Write data to Firebase
    //[myChildRef setValue: value];
    [myChildRef setValue:value withCompletionBlock:^(NSError *error, Firebase *myChildRef) {
        CDVPluginResult *result;
        if (error) {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            //NSLog(@"Data could not be saved.");
        } else {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            //NSLog(@"Data saved successfully.");
        }
    }];
}

- (void)writeValueToURLWithAutoID: (CDVInvokedUrlCommand*)command {
    //
    id value = nil;
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }

    Firebase *myChildRef = [[Firebase alloc] initWithUrl:strURL];
    Firebase *autoIDRef = [myChildRef childByAutoId];
    isUsed = YES;
    
    
    // Write data to Firebase
    //[myChildRef setValue: value];
    [autoIDRef setValue:value withCompletionBlock:^(NSError *error, Firebase *autoIDRef) {
        CDVPluginResult *result;
        if (error) {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            //NSLog(@"Data could not be saved.");
        } else {
            NSString *autoId = autoIDRef.key;
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsString:autoId];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            //NSLog(@"Data saved successfully.");
        }
    }];
}

// Child set by Appending Path
-(void)childSet: (CDVInvokedUrlCommand*) command {
    ///
    NSString *path;
    id object = nil;
    if ( [command.arguments count] >= 2 )
    {
        path = [command.arguments objectAtIndex:0];
        object = [command.arguments objectAtIndex:1];
    }

    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    Firebase *myRootRef = [[Firebase alloc] initWithUrl:strURL];
    isUsed = YES;
    
    Firebase *childRef = [myRootRef childByAppendingPath:path];
    [childRef setValue:object];
    
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    
}

//Authenticate using the provided credentials.
- (void)authWithCustomToken:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSString *token = nil;
    if ( [command.arguments count] >= 1 )
    {
        token = [command.arguments objectAtIndex:0];
    }
    Firebase *rootRef = [[Firebase alloc] initWithUrl:strURL];
    
    [rootRef authWithCustomToken:token withCompletionBlock:^( NSError *error , FAuthData *authData ){
        CDVPluginResult *result;
        if (error) {
            // Error
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            // Authenticate
            NSDictionary *authDict = authData.auth;
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:authDict];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

- (void)onDisconnectSetValue:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id value = nil;
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectSetValue:value];
    
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];

}

- (void)onDisconnectSetValueWithCompletionBlock:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id value = nil;
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectSetValue:value withCompletionBlock:^( NSError *error , Firebase *ulrRef ){
        CDVPluginResult *result;
        if (error) {
            // Error
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [result setKeepCallback:[NSNumber numberWithBool:YES]];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

- (void)onDisconnectRemoveValue:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectRemoveValue];
    
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    
}

- (void)onDisconnectRemoveValueWithCompletionBlock:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectRemoveValueWithCompletionBlock:^ ( NSError *error , Firebase *ref ){
        CDVPluginResult *result;
        if (error) {
            // Error
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [result setKeepCallback:[NSNumber numberWithBool:YES]];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

- (void)onDisconnectUpdateChildValues:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSDictionary *value = nil;
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectUpdateChildValues:value];
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
}

- (void)onDisconnectUpdateChildValuesWithCompletionBlock: (CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSDictionary *value = nil;
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        value = [command.arguments objectAtIndex:1];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef onDisconnectUpdateChildValues:value withCompletionBlock:^( NSError *error , Firebase *ref ){
        CDVPluginResult *result;
        if (error) {
            // Error
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [result setKeepCallback:[NSNumber numberWithBool:YES]];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}
- (void)cancelDisconnectOperations:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *ulrRef = [[Firebase alloc] initWithUrl:strURL];
    
    [ulrRef cancelDisconnectOperations];
    CDVPluginResult *result;
    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
    [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];

}

- (void)cancelDisconnectOperationsWithCompletionBlock:(CDVInvokedUrlCommand*) command
{
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    
    [urlRef cancelDisconnectOperationsWithCompletionBlock:^( NSError *error , Firebase *ref ){
        CDVPluginResult *result;
        if (error) {
            // Error
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
            [result setKeepCallback:[NSNumber numberWithBool:YES]];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}

//----  Querying and limiting

// Query Limite to Number of children
- (void)queryLimitedToNumberOfChildren:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSInteger limit = 0;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        limit = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryLimitedToNumberOfChildren:limit];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Limite to First
- (void)queryLimitedToFirst:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSInteger limit = 0;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        limit = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryLimitedToFirst:limit];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Limite to Last
- (void)queryLimitedToLast:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSInteger limit = 0;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        limit = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryLimitedToLast:limit];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Order By Child
- (void)queryOrderedByChild:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSString *strChild;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        strChild = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryOrderedByChild:strChild];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Order By Key
- (void)queryOrderedByKey:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    
    if ( [command.arguments count] >= 1 )
    {
        strURL = [command.arguments objectAtIndex:0];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryOrderedByKey];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Starting At Value
- (void)queryStartingAtValue:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idStartValue;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idStartValue = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryStartingAtValue:idStartValue];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Starting At Value With Child Key
- (void)queryStartingAtValueChildKey:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idStartValue;
    NSString *strChildKey = nil;
    
    if ( [command.arguments count] >= 3 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idStartValue = [command.arguments objectAtIndex:1];
        strChildKey = [command.arguments objectAtIndex:2];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryStartingAtValue:idStartValue childKey:strChildKey];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Ending At Value
- (void)queryEndingAtValue:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idEndValue;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idEndValue = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryEndingAtValue:idEndValue];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Ending At Value With Child Key
- (void)queryEndingAtValueChildKey:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idEndValue;
    NSString *strChildKey = nil;
    
    if ( [command.arguments count] >= 3 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idEndValue = [command.arguments objectAtIndex:1];
        strChildKey = [command.arguments objectAtIndex:2];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryStartingAtValue:idEndValue childKey:strChildKey];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Equal to Value
- (void)queryEqualToValue:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idValue;
    
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idValue = [command.arguments objectAtIndex:1];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryEqualToValue:idValue];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Equal to Value with Child Key
- (void)queryEqualToValueChildKey:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    id idValue;
    NSString *strChildKey = nil;
    
    if ( [command.arguments count] >= 3 )
    {
        strURL = [command.arguments objectAtIndex:0];
        idValue = [command.arguments objectAtIndex:1];
        strChildKey = [command.arguments objectAtIndex:2];
    }
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    FQuery *queryObj = [urlRef queryEqualToValue:idValue childKey:strChildKey];
    //NSDictionary *resultDict = nil;
    
    [queryObj observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//Qeury Search with combination of order, equalTo/startAt/endAt, limit
/*-------------------------------------------------------------------
 * querySearch: is used to generate a Dictionary reference to a limited, ordered view of the data at selected location.
 * The Dictionary reference returned by querySearch: will respond to events as node with a value
 * equal to / starting at / ending at the suplied argument with a key equal to / starting at / ending at childKey.
 *
 * @param strURL - The full url string for Firebase database
 * @param queryInfo - A dictionary instance of Query.
 * @return  - A Dictionary instance, limited to / ordered by data with the supplied values and the keys.
 *
 * @variable typeofOrder -
 *                       0: QOrderBy_NOT = 0,
 *                       1: QOrderBy_CHILDKEY
 *                       2: QOrderBy_CHILDVALUE
 * @variable strOrderValue - The child value to use in ordering data visible to the returned Dictionary
 * @variable typeofSearch -
 *                       0: QSearchAt_EQUAL = 0,
 *                       1: QSearchAt_STARTING,
 *                       2: QSearchAt_ENDING
 * @variable searchValue - The lower bound, inclusive, for the value of data visible to the returned FQuery
 * @variable strSearchKey -  The lower bound, inclusive, for the key of nodes with value equal to idSearchValue
 *                       If strSearchKey is nil or no length, use queryEqualToValue:/queryStartingAt:/queryEndingAt:
 *                       if not, use queryEqualToValue:childKey:/queryStartingAt:childKey:/queryEndingAt:childKey:
 * @variable typeofLimit -
 *                       0: QLimitedTo_NOT = 0,
 *                       1: QLimitedTo_FIRST,
 *                       2: QLimitedTo_LAST
 * @variable numLimited - The upper bound, inclusive, for the number of child nodes to receive events for
 */
- (void)querySearch:(CDVInvokedUrlCommand*) command
{
    //
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSDictionary *queryInfo;
    
    NSInteger typeofSearch = 0;
    id searchValue;
    NSString *strSearchKey = nil;
    NSInteger typeofOrder = 0;
    NSString *strOrderValue = nil;
    NSInteger typeofLimit = 0;
    NSInteger numLimited = 0;
    
   // NSString *temp;
    if ( [command.arguments count] >= 2 )
    {
        strURL = [command.arguments objectAtIndex:0];
        queryInfo = [command.arguments objectAtIndex:1];
        {
            if ((NSString*)[queryInfo objectForKey:@"search"] == nil){
                typeofSearch = QSearchAt_STARTING;
                searchValue = @"";
                strSearchKey = @"";
            }
            else {
                if ([(NSString*)[[queryInfo objectForKey:@"search"] objectForKey:@"type"]  isEqual: @"starting"])
                    typeofSearch = QSearchAt_STARTING;
                else if ([(NSString*)[[queryInfo objectForKey:@"search"] objectForKey:@"type"]  isEqual: @"ending"])
                    typeofSearch = QSearchAt_ENDING;
                else    //"equal" or nil
                    typeofSearch = QSearchAt_EQUAL;

                searchValue = (NSString*)[[queryInfo objectForKey:@"search"] objectForKey:@"value"];
                if (searchValue == nil)
                    searchValue = @"";
                
                strSearchKey = (NSString*)[[queryInfo objectForKey:@"search"] objectForKey:@"child"];
                if (strSearchKey == nil)
                    strSearchKey = @"";
            }
            
            if ((NSString*)[queryInfo objectForKey:@"order"] == nil){
                typeofOrder = QOrderBy_NOT;
                strOrderValue = @"";
            }
            else {
                if ([(NSString*)[[queryInfo objectForKey:@"order"] objectForKey:@"by"]  isEqual: @"key"] )
                    typeofOrder = QOrderBy_CHILDKEY;
                else if ([(NSString*)[[queryInfo objectForKey:@"order"] objectForKey:@"by"]  isEqual: @"value"])
                    typeofOrder = QOrderBy_CHILDVALUE;
                else
                    typeofOrder = QOrderBy_NOT;
                
                strOrderValue = (NSString*)[[queryInfo objectForKey:@"order"] objectForKey:@"field"];
                if (strOrderValue == nil)
                    strOrderValue = @"";
            }
            
            if ([queryInfo objectForKey:@"limit"] == nil) {
                typeofLimit = QLimitedTo_NOT;
                numLimited = 0;
            }
            else{
                if ([(NSString*)[[queryInfo objectForKey:@"limit"] objectForKey:@"at"]  isEqual: @"first"] )
                    typeofLimit = QLimitedTo_FIRST;
                else if ([(NSString*)[[queryInfo objectForKey:@"limit"] objectForKey:@"at"]  isEqual: @"last"])
                    typeofLimit = QLimitedTo_LAST;
                else
                    typeofLimit = QLimitedTo_NOT;
                
                if ([[queryInfo objectForKey:@"limit"] objectForKey:@"num"] == nil)
                    numLimited = 0;
                else
                    numLimited = [[[queryInfo objectForKey:@"limit"] objectForKey:@"num"] integerValue];
            }
        }
    }
    
    Firebase *urlRef = [[Firebase alloc] initWithUrl:strURL];
    
    FQuery *queryOrder = nil;
    switch (typeofOrder) {
        case QOrderBy_NOT:	
        default:
        case QOrderBy_CHILDKEY:
            queryOrder = [urlRef queryOrderedByKey];
            break;
            
        case QOrderBy_CHILDVALUE:
            queryOrder = [urlRef queryOrderedByChild:strOrderValue];
            break;
    }

    FQuery *queryObj = nil;
    
    switch (typeofSearch) {
        case QSearchAt_EQUAL:
            if(strSearchKey == nil | strSearchKey.length == 0)
                queryObj = [queryOrder queryEqualToValue:searchValue];
            else
                queryObj = [queryOrder queryEqualToValue:searchValue childKey:strSearchKey];
            break;
            
        case QSearchAt_STARTING:
            if(strSearchKey == nil | strSearchKey.length == 0)
                queryObj = [queryOrder queryStartingAtValue:searchValue];
            else
                queryObj = [queryOrder queryStartingAtValue:searchValue childKey:strSearchKey];
            break;
            
        case QSearchAt_ENDING:
            if(strSearchKey == nil | strSearchKey.length == 0)
                queryObj = [queryOrder queryEndingAtValue:searchValue];
            else
                queryObj = [queryOrder queryEndingAtValue:searchValue childKey:strSearchKey];
            break;
            
        default:
            queryObj = [queryOrder queryStartingAtValue:nil];
            break;
    }
    FQuery *queryLimit = nil;
    switch (typeofLimit) {
        case QLimitedTo_NOT:
        default:
            queryLimit = queryObj;
            break;
        case QLimitedTo_FIRST:
            queryLimit = [queryObj queryLimitedToFirst:numLimited];
            break;
        case QLimitedTo_LAST:
            queryLimit = [queryObj queryLimitedToLast:numLimited];
        break;
    }
    
    [queryLimit observeSingleEventOfType:FEventTypeValue withBlock:^(FDataSnapshot *snapshot) {
        NSLog(@"%@ -> %@", snapshot.key, snapshot.value);
        NSDictionary *resultDict = snapshot.value;
        
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:resultDict];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    } withCancelBlock:^(NSError *error){
        NSLog(@"%@", error.description);
        CDVPluginResult *result;
        result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR messageAsString:error.description];
        [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
    }];
}

//User Log in
- (void)userLogin:(CDVInvokedUrlCommand *)command{
    
    // Create a reference to a Firebase database URL
    NSString *strURL = [NSString stringWithFormat:@"https://%@.firebaseio.com", appName];
    NSString *userName = nil;
    NSString *password = nil;
    
    if ( [command.arguments count] >= 2 )
    {
        userName = [command.arguments objectAtIndex:0];
        password = [command.arguments objectAtIndex:1];
    }
    Firebase *rootRef = [[Firebase alloc] initWithUrl:strURL];
    [rootRef authUser:userName password:password withCompletionBlock:^(NSError *error, FAuthData *authData) {
        CDVPluginResult *result;
        if (error) {
            // an error occurred while attempting login
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_ERROR];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
            
        } else {
            // user is logged in, check authData for data
            NSDictionary *autoData = authData.auth;
            result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK messageAsDictionary:autoData];
            [self.commandDelegate sendPluginResult:result callbackId:command.callbackId];
        }
    }];
}


//- (void)initWithUrl:(CDVInvokedUrlCommand*) command{
//    //
//
//    NSString*       callback;
//    callback = command.callbackId;
//
//    NSString *url = @"";
//    if ( [command.arguments count] >= 1 )
//    {
//        url = [command.arguments objectAtIndex:0];
//    }
//
//    Firebase *myRootRef = [[Firebase alloc] initWithUrl:url];
//    CDVPluginResult *result;
//    result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//    [self.commandDelegate sendPluginResult:result callbackId:callback];
//}


// - (void)createUser:(CDVInvokedUrlCommand*)command {
//     NSString*       callback;
//     callback = command.callbackId;
    
//     NSString *username = @"", *password = @"";
//     if ( [command.arguments count] >= 2 )
//     {
//         argUsername = [command.arguments objectAtIndex:0];
//         argPassword = [command.arguments objectAtIndex:1];
//     }

//     [myRootRef createUser: argUsername, password:argPassword];

//     CDVPluginResult *result;
//     result = [CDVPluginResult resultWithStatus:CDVCommandStatus_OK];
//     [self.commandDelegate sendPluginResult:result callbackId:callback];
// }

@end
