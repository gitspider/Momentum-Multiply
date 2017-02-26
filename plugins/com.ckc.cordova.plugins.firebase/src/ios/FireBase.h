//
//  CDVFirebase.h
//  Hello World
//
//  Created by Simba on 10/6/15.
//
//

#import <Cordova/CDVPlugin.h>

@interface CDVFirebase : CDVPlugin

- (void)init:(CDVInvokedUrlCommand*)command;

- (void)setPersistenceEnabled:(CDVInvokedUrlCommand*)command;
    // Attach a block to read the data at our reference with AppName based URL
- (void)readData:(CDVInvokedUrlCommand*)command;
    // Attach a block to read the data at our reference with detailed URL
- (void)readValueTypeEventWithURL:(CDVInvokedUrlCommand*)command;
    // Attach a block to retrieve the data at our reference with detailed URL when child added Evens are triggered
- (void)RetrieveChildAddedEventWithURL:(CDVInvokedUrlCommand*)command;
    // Attach a block to retrieve the data at our reference with detailed URL when child changed Event are triggered
- (void)RetrieveChildChangedEventWithURL:(CDVInvokedUrlCommand*)command;
    // Attach a block to retrieve the data at our reference with detailed URL when child removed Event are triggered
- (void)RetrieveChildRemovedEventWithURL:(CDVInvokedUrlCommand*)command;
    //Reading Data Once
- (void)readDataOnceWithURL:(CDVInvokedUrlCommand*)command;
    //remove all callbacks at a location
- (void)removeAllCallbacksWithURL:(CDVInvokedUrlCommand*)command;

    //
- (void)writeData:(CDVInvokedUrlCommand*)command;
    //
- (void)writeValueToURL:(CDVInvokedUrlCommand*)command;
    //retern AutoID NSString
- (void)writeValueToURLWithAutoID: (CDVInvokedUrlCommand*)command;


    //child set by AppendingPath
- (void)childSet:(CDVInvokedUrlCommand*) command;

    //Initializing a Firebase object
//- (void)initWithUrl:(CDVInvokedUrlCommand*)command;

    //Authenticate using the provided credentials.
- (void)authWithCustomToken:(CDVInvokedUrlCommand*) command;

//----  Managing presence

- (void)onDisconnectSetValue:(CDVInvokedUrlCommand*) command;

- (void)onDisconnectSetValueWithCompletionBlock:(CDVInvokedUrlCommand*) command;

- (void)onDisconnectRemoveValue:(CDVInvokedUrlCommand*) command;

- (void)onDisconnectRemoveValueWithCompletionBlock:(CDVInvokedUrlCommand*) command;

- (void)onDisconnectUpdateChildValues:(CDVInvokedUrlCommand*) command;

- (void)onDisconnectUpdateChildValuesWithCompletionBlock:(CDVInvokedUrlCommand*) command;

- (void)cancelDisconnectOperations:(CDVInvokedUrlCommand*) command;

- (void)cancelDisconnectOperationsWithCompletionBlock:(CDVInvokedUrlCommand*) command;

//----  Querying and limiting

typedef enum {
    QSearchAt_EQUAL = 0,
    QSearchAt_STARTING,
    QSearchAt_ENDING
} QSearchType;

typedef enum {
    QOrderBy_NOT = 0,
    QOrderBy_CHILDKEY,
    QOrderBy_CHILDVALUE
} QOrderType;

typedef enum {
    QLimitedTo_NOT = 0,
    QLimitedTo_FIRST,
    QLimitedTo_LAST
} QLimitType;

- (void)queryLimitedToNumberOfChildren:(CDVInvokedUrlCommand*) command;

- (void)queryLimitedToFirst:(CDVInvokedUrlCommand*) command;

- (void)queryLimitedToLast:(CDVInvokedUrlCommand*) command;

- (void)queryOrderedByChild:(CDVInvokedUrlCommand*) command;

- (void)queryOrderedByKey:(CDVInvokedUrlCommand*) command;

- (void)queryStartingAtValue:(CDVInvokedUrlCommand*) command;

- (void)queryStartingAtValueChildKey:(CDVInvokedUrlCommand*) command;

- (void)queryEndingAtValue:(CDVInvokedUrlCommand*) command;

- (void)queryEndingAtValueChildKey:(CDVInvokedUrlCommand*) command;

- (void)queryEqualToValue:(CDVInvokedUrlCommand*) command;

- (void)queryEqualToValueChildKey:(CDVInvokedUrlCommand*) command;

- (void)querySearch:(CDVInvokedUrlCommand*) command;

    //User Login
- (void)userLogin: (CDVInvokedUrlCommand*) command;

//- (void)createUser:(CDVInvokedUrlCommand*)command;
@end
