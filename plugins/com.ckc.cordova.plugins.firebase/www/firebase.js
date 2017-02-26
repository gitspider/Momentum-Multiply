/**
 * cordova is available under *either* the terms of the modified BSD license *or* the
 * MIT License (2008). See http://opensource.org/licenses/alphabetical for full text.
 *
 * Copyright (c) LSH 2015
 * Copyright (c) 2015, CKC
 */

var FireBaseLoader = function (require, exports, module) {
var exec = require("cordova/exec");

/**
 * Constructor.
 *
 * @returns {FireBase}
 */

 function FireBase() {
 }

/**
 * Init App name on Firebase.
 *
 * @param {appName} FireBase App Name
 *
 * @param {Function} successCallback
 *
 * @param {Function} errorCallback
 */
FireBase.prototype.init = function (appName, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (successCallback == null) {
        successCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.init failure: failure parameter must be a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.init failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'init', [appName]);
};

//-------------------------------------------------------------------
FireBase.prototype.setPersistenceEnabled = function (persistenceEnabled, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }
    
    if (successCallback == null) {
        successCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.setPersistenceEnabled failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.setPersistenceEnabled failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'setPersistenceEnabled', [persistenceEnabled]);
};

//-------------------------------------------------------------------
FireBase.prototype.readData = function (successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.readData failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.readData failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'readData', []);
};

//-------------------------------------------------------------------
FireBase.prototype.readDataOnceWithURL = function (urlOfReadData, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.readData failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.readData failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'readDataOnceWithURL', [urlOfReadData]);
};

//-------------------------------------------------------------------
FireBase.prototype.removeAllCallbacksWithURL = function (urlOfRemoveCalback, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.removeAllCallbacksWithURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.removeAllCallbacksWithURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'removeAllCallbacksWithURL', [urlOfRemoveCalback]);
};

//-------------------------------------------------------------------
FireBase.prototype.readValueTypeEventWithURL = function (urlOfReadData, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.readValueTypeEventWithURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.readValueTypeEventWithURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'readValueTypeEventWithURL', [urlOfReadData]);
};

//-------------------------------------------------------------------
FireBase.prototype.RetrieveChildAddedEventWithURL = function (urlOfAddedChild, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.RetrieveChildAddedEventWithURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.RetrieveChildAddedEventWithURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'RetrieveChildAddedEventWithURL', [urlOfAddedChild]);
};

//-------------------------------------------------------------------
FireBase.prototype.RetrieveChildChangedEventWithURL = function (urlOfchangedChild, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.RetrieveChildChangedEventWithURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.RetrieveChildChangedEventWithURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'RetrieveChildChangedEventWithURL', [urlOfchangedChild]);
};

//-------------------------------------------------------------------
FireBase.prototype.RetrieveChildRemovedEventWithURL = function (urlOfRemovedChild, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.RetrieveChildRemovedEventWithURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.RetrieveChildRemovedEventWithURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'RetrieveChildRemovedEventWithURL', [urlOfRemovedChild]);
};

//-------------------------------------------------------------------
FireBase.prototype.writeData = function (object, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.writeData failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.writeData failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'writeData', [object]);
};

//-------------------------------------------------------------------
FireBase.prototype.writeValueToURL = function (url, object, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.writeValueToURL failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.writeValueToURL failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'writeValueToURL', [url, object]);
};

//-------------------------------------------------------------------
FireBase.prototype.childSet = function (path, object, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.childSet failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.childSet failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'childSet', [path, object]);
};

//-------------------------------------------------------------------
FireBase.prototype.authWithCustomToken = function (token, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.authWithCustomToken failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.authWithCustomToken failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'authWithCustomToken', [token]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectSetValue = function (url, object, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectSetValue failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectSetValue failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectSetValue', [url, object]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectSetValueWithCompletionBlock = function (url, object, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectSetValueWithCompletionBlock failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectSetValueWithCompletionBlock failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectSetValueWithCompletionBlock', [url, object]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectRemoveValue = function (url, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectRemoveValue failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectRemoveValue failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectRemoveValue', [url]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectRemoveValueWithCompletionBlock = function (url, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectRemoveValueWithCompletionBlock failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectRemoveValueWithCompletionBlock failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectRemoveValueWithCompletionBlock', [url]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectUpdateChildValues = function (url, valueDictionary, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectUpdateChildValues failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectUpdateChildValues failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectUpdateChildValues', [url]);
};

//-------------------------------------------------------------------
FireBase.prototype.onDisconnectUpdateChildValuesWithCompletionBlock = function (url, valueDictionary, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.onDisconnectUpdateChildValuesWithCompletionBlock failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.onDisconnectUpdateChildValuesWithCompletionBlock failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'onDisconnectUpdateChildValuesWithCompletionBlock', [url, valueDictionary]);
};

//-------------------------------------------------------------------
FireBase.prototype.cancelDisconnectOperations = function (url, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.cancelDisconnectOperations failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.cancelDisconnectOperations failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'cancelDisconnectOperations', [url]);
};

//-------------------------------------------------------------------
FireBase.prototype.cancelDisconnectOperationsWithCompletionBlock = function (url, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.cancelDisconnectOperationsWithCompletionBlock failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.cancelDisconnectOperationsWithCompletionBlock failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'cancelDisconnectOperationsWithCompletionBlock', [url]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryLimitedToNumberOfChildren = function (intLimit, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryLimitedToNumberOfChildren failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryLimitedToNumberOfChildren failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryLimitedToNumberOfChildren', [intLimit]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryLimitedToNumberOfChildren = function (strURL, intLimit, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryLimitedToNumberOfChildren failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryLimitedToNumberOfChildren failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryLimitedToNumberOfChildren', [strURL, intLimit]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryLimitedToFirst = function (strURL, intLimit, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryLimitedToFirst failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryLimitedToFirst failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryLimitedToFirst', [strURL, intLimit]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryLimitedToLast = function (strURL, intLimit, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryLimitedToLast failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryLimitedToLast failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryLimitedToLast', [strURL, intLimit]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryOrderedByChild = function (strURL, strChild, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryOrderedByChild failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryOrderedByChild failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryOrderedByChild', [strURL, strChild]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryOrderedByKey = function (strURL, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryOrderedByKey failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryOrderedByKey failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryOrderedByKey', [strURL]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryStartingAtValue = function (strURL, idStartValue, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryStartingAtValue failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryStartingAtValue failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryStartingAtValue', [strURL], [idStartValue]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryStartingAtValueChildKey = function (strURL, idStartValue, strChildKey, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryStartingAtValueChildKey failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryStartingAtValueChildKey failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryStartingAtValueChildKey', [strURL, idStartValue, strChildKey]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryEndingAtValue = function (strURL, idEndValue, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryEndingAtValue failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryEndingAtValue failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryEndingAtValue', [strURL, idEndValue]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryEndingAtValueChildKey = function (strURL, idEndValue, strChildKey, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryEndingAtValueChildKey failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryEndingAtValueChildKey failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryEndingAtValueChildKey', [strURL, idEndValue, strChildKey]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryEqualToValue = function (strURL, idValue, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryEqualToValue failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryEqualToValue failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryEqualToValue', [strURL, idValue]);
};

//-------------------------------------------------------------------
FireBase.prototype.queryEqualToValueChildKey = function (strURL, idValue, strChildKey, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.queryEqualToValueChildKey failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.queryEqualToValueChildKey failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'queryEqualToValueChildKey', [strURL, idValue, strChildKey]);
};

/*-------------------------------------------------------------------
* querySearch: is used to generate a Dictionary reference to a limited, ordered view of the data at selected location.
* The Dictionary reference returned by querySearch: will respond to events as node with a value 
* equal to / starting at / ending at the suplied argument with a key equal to / starting at / ending at childKey.
* 
* @param strURL - The full url string for Firebase database
* @param queryInfo - A dictionary instance of Query.
* @return  - A Dictionary instance, limited to / ordered by data with the supplied values and the keys.
*
* ex:
*       queryInfo = {
*           order: {            // if this key is nil, not order 
*               by: 'value',          // 'key', 'value' or nil
*               field: 'height'       // if the key "by" is 'key', doesn't use this key but if not, use.
*           },
*           search: {           
*               type: 'starting',   //'starting', 'ending' 'equal' or nil
*               value: 3,           // object
*               child: ''           // string   //if this key is nil or empty, doesn't use.
*           },
*           limit: {            // if this key is nil, no limit.
*               at: 'first',        //'first', last' or nil
*               num: 10             //integer
*           }
*       };
*/
FireBase.prototype.querySearch = function (strURL, queryInfo, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.querySearch failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.querySearch failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'querySearch', [strURL, queryInfo]);
};


//-------------------------------------------------------------------
FireBase.prototype.userLogin = function (strUserName, strPassword, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.userLogin failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.userLogin failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'userLogin', [strUserName, strPassword]);
};

FireBase.prototype.writeValueToURLWithAutoID = function (strURL, queryInfo, successCallback, errorCallback) {
    if (errorCallback == null) {
        errorCallback = function () {
        };
    }

    if (typeof errorCallback != "function") {
        console.log("FireBase.writeValueToURLWithAutoID failure: failure parameter not a function");
        return;
    }

    if (typeof successCallback != "function") {
        console.log("FireBase.writeValueToURLWithAutoID failure: success callback parameter must be a function");
        return;
    }

    exec(successCallback, errorCallback, 'FireBase', 'writeValueToURLWithAutoID', [strURL, queryInfo]);
};


var fireBase = new FireBase();
module.exports = fireBase;

    }

    FireBaseLoader(require, exports, module);

    cordova.define("cordova/plugin/FireBase", FireBaseLoader);
