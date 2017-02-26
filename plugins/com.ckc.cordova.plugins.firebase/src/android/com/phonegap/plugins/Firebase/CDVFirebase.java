//
//  Copyright 2015 Firebase Plugin
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
//

package com.phonegap.plugins.Firebase;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

//import android.app.Activity;
//import android.content.Intent;
//import android.util.Log;



public class CDVFirebase extends CordovaPlugin {
	//public static final int REQUEST_CODE = 0x0abbc0de;
	public static boolean isUsed = false;
	public static String appName = "";

//    public static final String CANCEL = "cancel";
    public static final String INIT = "init";
    public static final String SETPERSISTENCEENABLED = "setPersistenceEnabled";
    public static final String READDATA = "readData";
    public static final String READVALUETYPEEVENTWITHURL = "readValueTypeEventWithURL";
    public static final String RETRIEVECHILDADDEDEVENTWITHURL = "RetrieveChildAddedEventWithURL";
    public static final String RETRIEVECHILDCHANGEDEVENTWITHURL = "RetrieveChildChangedEventWithURL";
    public static final String RETRIEVECHILDREMOVEDEVENTWITHURL = "RetrieveChildRemovedEventWithURL";
    public static final String READDATAONCEWITHURL = "readDataOnceWithURL";
    public static final String REMOVEALLCALLBACKSWITHURL = "removeAllCallbacksWithURL";
    public static final String WRITEDATA = "writeData";
    public static final String WRITEVALUETOURL = "writeValueToURL";
    public static final String WRITEVALUETOURLWITHAUTOID = "writeValueToURLWithAutoID";

    public static final String CHILDSET = "childSet";
    public static final String AUTHWITHCUSTOMTOKEN = "authWithCustomToken";
    public static final String ONDISCONNECTSETVALUE = "onDisconnectSetValue";
    public static final String ONDISCONNECTSETVALUEWITHCOMPLETIONBLOCK = "onDisconnectSetValueWithCompletionBlock";
    public static final String ONDISCONNECTREMOVEVALUE = "onDisconnectRemoveValue";
    public static final String ONDISCONNECTREMOVEVALUEWITHCOMPLETIONBLOCK = "onDisconnectRemoveValueWithCompletionBlock";
    public static final String ONDISCONNECTUPDATECHILDVALUES = "onDisconnectUpdateChildValues";
    public static final String ONDISCONNECTUPDATECHILDVALUESWITHCOMPLETIONBLOCK = "onDisconnectUpdateChildValuesWithCompletionBlock";
    public static final String CANCELDISCONNECTOPERATIONS = "cancelDisconnectOperations";
    public static final String CANCELDISCONNECTOPERATIONSWITHCOMPLETIONBLOCK = "cancelDisconnectOperationsWithCompletionBlock";

    public static final String QUERYLIMITEDTONUMBEROFCHILDREN = "queryLimitedToNumberOfChildren";
    public static final String QUERYLIMITEDTOFIRST = "queryLimitedToFirst";
    public static final String QUERYLIMITEDTOLAST = "queryLimitedToLast";
    public static final String QUERYORDEREDBYCHILD = "queryOrderedByChild";
    public static final String QUERYORDEREDBYKEY = "queryOrderedByKey";
    public static final String QUERYSTARTINGATVALUE = "queryStartingAtValue";
    public static final String QUERYSTARTINGATVALUECHILDKEY = "queryStartingAtValueChildKey";
    public static final String QUERYENDINGATVALUE = "queryEndingAtValue";
    public static final String QUERYENDINGATVALUECHILDKEY = "queryEndingAtValueChildKey";
    public static final String QUERYEQUALTOVALUE = "queryEqualToValue";
    public static final String QUERYEQUALTOVALUECHILDKEY = "queryEqualToValueChildKey";
    public static final String QUERYSEARCH = "querySearch";
    public static final String USERLOGIN = "userLogin";

    private CallbackContext mCallbackContext;

    /**
     * Constructor.
     */
    public CDVFirebase() {

    }

    @Override
    protected void pluginInitialize() {
    	com.firebase.client.Firebase.setAndroidContext(this.cordova.getActivity().getApplicationContext());
    };
    /**
     * Executes the request.
     *
     * This method is called from the Web. To do a non-trivial amount of work, use:
     *     cordova.getThreadPool().execute(runnable);
     *
     *
     * @param action          The action to execute.
     * @param args            The exec() arguments.
     * @param callbackContext The callback context used when calling back into JavaScript.
     * @return                Whether the action was valid.
     *
     * @sa https://github.com/apache/cordova-android/blob/master/framework/src/org/apache/cordova/CordovaPlugin.java
     */
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) {
        PluginResult result = null;

        if (action.equals(INIT)) {
            mCallbackContext = callbackContext;
            init(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(SETPERSISTENCEENABLED)) {
            mCallbackContext = callbackContext;
            setPersistenceEnabled(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(READDATA)) {
            mCallbackContext = callbackContext;
            readData(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(READVALUETYPEEVENTWITHURL)) {
            mCallbackContext = callbackContext;
            readValueTypeEventWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(RETRIEVECHILDADDEDEVENTWITHURL)) {
            mCallbackContext = callbackContext;
            RetrieveChildAddedEventWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(RETRIEVECHILDCHANGEDEVENTWITHURL)) {
            mCallbackContext = callbackContext;
            RetrieveChildChangedEventWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(RETRIEVECHILDREMOVEDEVENTWITHURL)) {
            mCallbackContext = callbackContext;
            RetrieveChildRemovedEventWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(READDATAONCEWITHURL)) {
            mCallbackContext = callbackContext;
            readDataOnceWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(REMOVEALLCALLBACKSWITHURL)) {
            mCallbackContext = callbackContext;
            removeAllCallbacksWithURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(WRITEDATA)) {
            mCallbackContext = callbackContext;
            writeData(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(WRITEVALUETOURL)) {
            mCallbackContext = callbackContext;
            writeValueToURL(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(WRITEVALUETOURLWITHAUTOID)) {
            mCallbackContext = callbackContext;
            writeValueToURLWithAutoID(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }

        else if (action.equals(CHILDSET)) {
            mCallbackContext = callbackContext;
            childSet(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(AUTHWITHCUSTOMTOKEN)) {
            mCallbackContext = callbackContext;
            authWithCustomToken(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTSETVALUE)) {
            mCallbackContext = callbackContext;
            onDisconnectSetValue(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTSETVALUEWITHCOMPLETIONBLOCK)) {
            mCallbackContext = callbackContext;
            onDisconnectSetValueWithCompletionBlock(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTREMOVEVALUE)) {
            mCallbackContext = callbackContext;
            onDisconnectRemoveValue(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTREMOVEVALUEWITHCOMPLETIONBLOCK)) {
            mCallbackContext = callbackContext;
            onDisconnectRemoveValueWithCompletionBlock(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTUPDATECHILDVALUES)) {
            mCallbackContext = callbackContext;
            onDisconnectUpdateChildValues(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(ONDISCONNECTUPDATECHILDVALUESWITHCOMPLETIONBLOCK)) {
            mCallbackContext = callbackContext;
            onDisconnectUpdateChildValuesWithCompletionBlock(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(CANCELDISCONNECTOPERATIONS)) {
            mCallbackContext = callbackContext;
            cancelDisconnectOperations(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(CANCELDISCONNECTOPERATIONSWITHCOMPLETIONBLOCK)) {
            mCallbackContext = callbackContext;
            cancelDisconnectOperationsWithCompletionBlock(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }

        else if (action.equals(QUERYLIMITEDTONUMBEROFCHILDREN)) {
            mCallbackContext = callbackContext;
            queryLimitedToNumberOfChildren(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYLIMITEDTOFIRST)) {
            mCallbackContext = callbackContext;
            queryLimitedToFirst(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYLIMITEDTOLAST)) {
            mCallbackContext = callbackContext;
            queryLimitedToLast(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYORDEREDBYCHILD)) {
            mCallbackContext = callbackContext;
            queryOrderedByChild(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYORDEREDBYKEY)) {
            mCallbackContext = callbackContext;
            queryOrderedByKey(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYSTARTINGATVALUE)) {
            mCallbackContext = callbackContext;
            queryStartingAtValue(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYSTARTINGATVALUECHILDKEY)) {
            mCallbackContext = callbackContext;
            queryStartingAtValueChildKey(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYENDINGATVALUE)) {
            mCallbackContext = callbackContext;
            queryEndingAtValue(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYENDINGATVALUECHILDKEY)) {
            mCallbackContext = callbackContext;
            queryEndingAtValueChildKey(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYEQUALTOVALUE)) {
            mCallbackContext = callbackContext;
            queryEqualToValue(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYEQUALTOVALUECHILDKEY)) {
            mCallbackContext = callbackContext;
            queryEqualToValueChildKey(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(QUERYSEARCH)) {
            mCallbackContext = callbackContext;
            try {
				querySearch(args);
			} catch (JSONException e) {
				e.printStackTrace();
			}
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else if (action.equals(USERLOGIN)) {
            mCallbackContext = callbackContext;
            userLogin(args);
            result = new PluginResult(Status.NO_RESULT);
            result.setKeepCallback(true);
            mCallbackContext.sendPluginResult(result);
            return true;
        }
        else {
            result = new PluginResult(Status.INVALID_ACTION, "No Plugin Function: " + action);
            mCallbackContext.sendPluginResult(result);
            return false;
        }
    }

    private void init(JSONArray data) {
        if ( data.length() >= 1 ) {
            try {
                appName = data.getString(0);
            } catch (JSONException e) {
                PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
                e.printStackTrace();
                return;
            }
        } else{
            PluginResult pluginResult = new PluginResult(Status.ERROR, "init : Parameter Error");
            mCallbackContext.sendPluginResult(pluginResult);
            return;
        }
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void setPersistenceEnabled(JSONArray data) {

        if (isUsed == true) {
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "Can't modify config objects after they are in use for Firebase references.");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        boolean persistenceEnabled = true;
        if ( data.length() >= 1 ) {
            try {
            	persistenceEnabled = data.getBoolean(0);
            } catch (JSONException e) {
                PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
                e.printStackTrace();
                return;
            }
        } else{
            PluginResult pluginResult = new PluginResult(Status.ERROR, "setPersistenceEnabled : Parameter Error");
            mCallbackContext.sendPluginResult(pluginResult);
            return;
        }
        //Firebase.setAndroidContext(this);
        Firebase.getDefaultConfig().setPersistenceEnabled(persistenceEnabled);
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void readData(JSONArray data) {
        //
        String strURL = String.format("https://%s.firebaseio.com", appName);
        final Firebase myRootRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        myRootRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
                try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
                    //myRootRef = new Firebase(strURL);
                    myRootRef.keepSynced(true);
                    PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
                    pluginResult.setKeepCallback(true);
                    mCallbackContext.sendPluginResult(pluginResult);
                } catch (Exception e) {
                    PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
                    mCallbackContext.sendPluginResult(pluginResult);
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The readData(addValueEventListener) failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }
    //Reading Data Once
    private void readDataOnceWithURL(JSONArray data) {

    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
            PluginResult pluginResult = new PluginResult(Status.ERROR, "readDataOnceWithURL : Parameter Error");
            mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase myRootRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        myRootRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
                try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
                    PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
                    //pluginResult.setKeepCallback(true);
                    mCallbackContext.sendPluginResult(pluginResult);
                } catch (Exception e) {
                    PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
                    mCallbackContext.sendPluginResult(pluginResult);
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The readDataOnceWithURL(addListenerForSingleValueEvent) failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "The readDataOnceWithURL failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }
    //remove all callbacks at a location
    private void removeAllCallbacksWithURL(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "removeAllCallbacksWithURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase myChildRef = new Firebase(strURL);
//myChildRef.removeEventListener(listener);
//myChildRef.removeEventListener(listener);//.removeEventListener(myChildRef.);
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    // Attach a block to read the data at our posts reference with detailed URL
    private void readValueTypeEventWithURL(JSONArray data) {
        //
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "readValueTypeEventWithURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase myChildRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        myChildRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
                try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
                    PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
                    //pluginResult.setKeepCallback(true);
                    mCallbackContext.sendPluginResult(pluginResult);
                } catch (Exception e) {
                    PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
                    mCallbackContext.sendPluginResult(pluginResult);
                    e.printStackTrace();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The readValueTypeEventWithURL(addValueEventListener) failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "The readValueTypeEventWithURL failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }
    // Attach a block to retrieve the data at our reference with detailed URL when child added Evens are triggered
    private void RetrieveChildAddedEventWithURL(JSONArray data) {
        //
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "RetrieveChildAddedEventWithURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase myChildRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        // Retrieve new posts as they are added to the database
        myChildRef.addChildEventListener(new ChildEventListener() {
			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
		        //[result setKeepCallback:[NSNumber numberWithBool:YES]];
                System.out.println(arg0.getValue());
                JSONObject resultObj;
				try {
					resultObj = new JSONObject(arg0.getValue().toString());
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (JSONException e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
			}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
    }

    // Attach a block to retrieve the data at our reference with detailed URL when child changed Event are triggered
    private void RetrieveChildChangedEventWithURL(JSONArray data) {
        //
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "RetrieveChildAddedEventWithURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase myChildRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        // Retrieve new posts as they are added to the database
        myChildRef.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				//[result setKeepCallback:[NSNumber numberWithBool:YES]];
                System.out.println(arg0.getValue());
                JSONObject resultObj;
				try {
					resultObj = new JSONObject(arg0.getValue().toString());
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (JSONException e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
			}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				// TODO Auto-generated method stub

			}
		});
    }

    // Attach a block to retrieve the data at our reference with detailed URL when child removed Event are triggered
    private void RetrieveChildRemovedEventWithURL(JSONArray data) {
        //
    	String strURL = String.format("https://%s.firebaseio.com", appName);

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "RetrieveChildAddedEventWithURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase myChildRef = new Firebase(strURL);
        if(isUsed != true)
            isUsed = true;
        // Retrieve new posts as they are added to the database
        myChildRef.addChildEventListener(new ChildEventListener() {

			@Override
			public void onChildRemoved(DataSnapshot arg0) {
				//[result setKeepCallback:[NSNumber numberWithBool:YES]];
                System.out.println(arg0.getValue());
                JSONObject resultObj;
				try {
					resultObj = new JSONObject(arg0.getValue().toString());
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (JSONException e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
			}

			@Override
			public void onCancelled(FirebaseError arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildAdded(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildChanged(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onChildMoved(DataSnapshot arg0, String arg1) {
				// TODO Auto-generated method stub

			}
		});
    }

    //
    private void writeData(JSONArray data) {
    	//
    	Object value;
    	String strURL = String.format("https://%s.firebaseio.com", appName);

        if ( data.length() >= 1 )
        {
        	try {
				value = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "writeData : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase myRootRef = new Firebase(strURL);
        isUsed = true;

        if(value.getClass() == JSONArray.class){
        	myRootRef.setValue((Object)toList((JSONArray)value), new Firebase.CompletionListener() {
    			@Override
    			public void onComplete(FirebaseError arg0, Firebase arg1) {
    				if(arg0 == null){
    					PluginResult pluginResult = new PluginResult(Status.OK);
    					mCallbackContext.sendPluginResult(pluginResult);
    				}else{
    					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
    					mCallbackContext.sendPluginResult(pluginResult);
    				}
    			}
    		});
        }else if (value.getClass() == JSONObject.class){
        	myRootRef.setValue((Object)jsonToMap((JSONObject)value), new Firebase.CompletionListener() {
    			@Override
    			public void onComplete(FirebaseError arg0, Firebase arg1) {
    				if(arg0 == null){
    					PluginResult pluginResult = new PluginResult(Status.OK);
    					mCallbackContext.sendPluginResult(pluginResult);
    				}else{
    					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
    					mCallbackContext.sendPluginResult(pluginResult);
    				}
    			}
    		});
        }
        else{
        	myRootRef.setValue(value, new Firebase.CompletionListener() {
				@Override
				public void onComplete(FirebaseError arg0, Firebase arg1) {
					if(arg0 == null){
						PluginResult pluginResult = new PluginResult(Status.OK);
						mCallbackContext.sendPluginResult(pluginResult);
					}else{
						PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
						mCallbackContext.sendPluginResult(pluginResult);
					}
				}
			});
        }
    }

    public static Map<String, Object> jsonToMap(JSONObject json){
        Map<String, Object> retMap = new HashMap<String, Object>();

        if(json != JSONObject.NULL) {
            retMap = toMap(json);
        }
        return retMap;
    }

    public static Map<String, Object> toMap(JSONObject object){
        Map<String, Object> map = new HashMap<String, Object>();

        Iterator<String> keysItr = object.keys();
        while(keysItr.hasNext()) {
        	try{
	            String key = keysItr.next();
	            Object value = object.get(key);

	            if(value instanceof JSONArray) {
	                value = toList((JSONArray) value);
	            }

	            else if(value instanceof JSONObject) {
	                value = toMap((JSONObject) value);
	            }
	            map.put(key, value);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        return map;
    }

    public static List<Object> toList(JSONArray array){
        List<Object> list = new ArrayList<Object>();
        for(int i = 0; i < array.length(); i++) {
        	try{
	            Object value = array.get(i);
	            if(value instanceof JSONArray) {
	                value = toList((JSONArray) value);
	            }

	            else if(value instanceof JSONObject) {
	                value = toMap((JSONObject) value);
	            }
	            list.add(value);
        	}catch(Exception e){
        		e.printStackTrace();
        	}
        }
        return list;
    }

    private void writeValueToURL(JSONArray data) {
    	//
    	Object value;
    	String strURL = String.format("https://%s.firebaseio.com", appName);

        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
				value = data.get(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "writeValueToURL : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase myChildRef = new Firebase(strURL);
        isUsed = true;
        //value = new JSONObject ("asdf":"asdf", "asdf1":"as");
        if(value.getClass() == JSONArray.class){
        	myChildRef.setValue((Object)toList((JSONArray)value), new Firebase.CompletionListener() {
    			@Override
    			public void onComplete(FirebaseError arg0, Firebase arg1) {
    				if(arg0 == null){
    					PluginResult pluginResult = new PluginResult(Status.OK);
    					mCallbackContext.sendPluginResult(pluginResult);
    				}else{
    					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
    					mCallbackContext.sendPluginResult(pluginResult);
    				}
    			}
    		});
        }else if (value.getClass() == JSONObject.class){
        	myChildRef.setValue((Object)jsonToMap((JSONObject)value), new Firebase.CompletionListener() {
    			@Override
    			public void onComplete(FirebaseError arg0, Firebase arg1) {
    				if(arg0 == null){
    					PluginResult pluginResult = new PluginResult(Status.OK);
    					mCallbackContext.sendPluginResult(pluginResult);
    				}else{
    					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
    					mCallbackContext.sendPluginResult(pluginResult);
    				}
    			}
    		});
        }
        else{
	        myChildRef.setValue(value, new Firebase.CompletionListener() {
				@Override
				public void onComplete(FirebaseError arg0, Firebase arg1) {
					if(arg0 == null){
						PluginResult pluginResult = new PluginResult(Status.OK);
						mCallbackContext.sendPluginResult(pluginResult);
					}else{
						PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
						mCallbackContext.sendPluginResult(pluginResult);
					}
				}
			});
        }
    }

    private void writeValueToURLWithAutoID(JSONArray data) {
    	//
    	Object value;
    	String strURL = String.format("https://%s.firebaseio.com", appName);

        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
				value = data.get(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "writeValueToURLWithAutoID : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase myChildRef = new Firebase(strURL);
        Firebase autoIDRef = myChildRef.push(); //Firebase *autoIDRef = [myChildRef childByAutoId];
        isUsed = true;

        if(value.getClass() == JSONArray.class){
            autoIDRef.setValue((Object)toList((JSONArray)value), new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError arg0, Firebase arg1) {
                    if(arg0 == null){
                        PluginResult pluginResult = new PluginResult(Status.OK);
                        mCallbackContext.sendPluginResult(pluginResult);
                    }else{
                        PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
                        mCallbackContext.sendPluginResult(pluginResult);
                    }
                }
            });
        }else if (value.getClass() == JSONObject.class){
            autoIDRef.setValue((Object)jsonToMap((JSONObject)value), new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError arg0, Firebase arg1) {
                    if(arg0 == null){
                        PluginResult pluginResult = new PluginResult(Status.OK);
                        mCallbackContext.sendPluginResult(pluginResult);
                    }else{
                        PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
                        mCallbackContext.sendPluginResult(pluginResult);
                    }
                }
            });
        }
        else{
            autoIDRef.setValue(value, new Firebase.CompletionListener() {
                @Override
                public void onComplete(FirebaseError arg0, Firebase arg1) {
                    if(arg0 == null){
                        PluginResult pluginResult = new PluginResult(Status.OK);
                        mCallbackContext.sendPluginResult(pluginResult);
                    }else{
                        PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
                        mCallbackContext.sendPluginResult(pluginResult);
                    }
                }
            });
        }
    }

    // Child set by Appending Path
    private void childSet(JSONArray data) {
        ///
        String path;
        Object objData;
        if ( data.length() >= 2 )
        {
        	try {
        		path = data.getString(0);
        		objData = data.getString(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "childSet : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        // Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        Firebase myRootRef = new Firebase(strURL);
        isUsed = true;

        Firebase childRef = myRootRef.child(path); //childByAppendingPath:path];
        childRef.setValue(objData, new Firebase.CompletionListener() {

			@Override
			public void onComplete(FirebaseError arg0, Firebase arg1) {
				if(arg0 == null){
					PluginResult pluginResult = new PluginResult(Status.OK);
					mCallbackContext.sendPluginResult(pluginResult);
				}else{
					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
				}
			}
		});
    }

    //Authenticate using the provided credentials.
    private void authWithCustomToken(JSONArray data) {
        // Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        String token;
        if ( data.length() >= 1 )
        {
        	try {
        		token = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "authWithCustomToken : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase rootRef = new Firebase(strURL);
        rootRef.authWithCustomToken(token, new Firebase.AuthResultHandler() {

			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, "authWithCustomToken : Error");
				mCallbackContext.sendPluginResult(pluginResult);
			}

			@Override
			public void onAuthenticated(AuthData arg0) {
                JSONObject resultObj;
				try {
					resultObj = new JSONObject(arg0.getAuth().toString());	//NSDictionary *authDict = authData.auth;
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (JSONException e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
			}
		});
    }

    private void onDisconnectSetValue(JSONArray data) {

    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        Object value;
        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
        		value = data.getString(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnectSetValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        urlRef.onDisconnect().setValue(value);
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void onDisconnectSetValueWithCompletionBlock(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        Object value;
        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
        		value = data.getString(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnectSetValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        urlRef.onDisconnect().setValue(value, new Firebase.CompletionListener() {

			@Override
			public void onComplete(FirebaseError arg0, Firebase arg1) {
				if(arg0 == null){
					PluginResult pluginResult = new PluginResult(Status.OK);
					mCallbackContext.sendPluginResult(pluginResult);
				}else{
					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
				}
			}
		});
    }

    private void onDisconnectRemoveValue(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        if ( data.length() >= 1 )
        {
        	try {
        		strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnectSetValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        urlRef.onDisconnect().removeValue();
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void onDisconnectRemoveValueWithCompletionBlock(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        if ( data.length() >= 1 )
        {
        	try {
        		strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnectSetValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        urlRef.onDisconnect().removeValue(new Firebase.CompletionListener() {

			@Override
			public void onComplete(FirebaseError arg0, Firebase arg1) {
				if(arg0 == null){
					PluginResult pluginResult = new PluginResult(Status.OK);
					mCallbackContext.sendPluginResult(pluginResult);
				}else{
					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
				}
			}
		});
    }

    private void onDisconnectUpdateChildValues(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        JSONObject objData;
        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
        		objData = data.getJSONObject(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnectSetValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        Map<String, Object> update = new HashMap();
        Iterator<String> iter = objData.keys();
        while( iter.hasNext() ){
        	try {
				update.put(iter.toString(), objData.get(iter.toString()));
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        urlRef.onDisconnect().updateChildren(update);
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void onDisconnectUpdateChildValuesWithCompletionBlock(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        JSONObject objData;
        if ( data.length() >= 2 )
        {
        	try {
        		strURL = data.getString(0);
        		objData = data.getJSONObject(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnect().UpdateChildValuesWithCompletionBlock : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);
        Map<String, Object> update = new HashMap();
        Iterator<String> iter = objData.keys();
        while( iter.hasNext() ){
        	try {
				update.put(iter.toString(), objData.get(iter.toString()));
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        urlRef.onDisconnect().updateChildren(update, new Firebase.CompletionListener() {
			@Override
			public void onComplete(FirebaseError arg0, Firebase arg1) {
				if(arg0 == null){
					PluginResult pluginResult = new PluginResult(Status.OK);
					mCallbackContext.sendPluginResult(pluginResult);
				}else{
					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
				}
			}
		});
    }
    private void cancelDisconnectOperations(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        if ( data.length() >= 1 )
        {
        	try {
        		strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnect().cancel() : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase urlRef = new Firebase(strURL);

        urlRef.onDisconnect().cancel();
        PluginResult pluginResult = new PluginResult(Status.OK);
        mCallbackContext.sendPluginResult(pluginResult);
    }

    private void cancelDisconnectOperationsWithCompletionBlock(JSONArray data) {
    	// Create a reference to a Firebase database URL
        String strURL = String.format("https://%s.firebaseio.com", appName);
        if ( data.length() >= 1 )
        {
        	try {
        		strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "onDisconnect().cancel(): Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }
        Firebase urlRef = new Firebase(strURL);

        urlRef.onDisconnect().cancel(new Firebase.CompletionListener() {
			@Override
			public void onComplete(FirebaseError arg0, Firebase arg1) {
				if(arg0 == null){
					PluginResult pluginResult = new PluginResult(Status.OK);
					mCallbackContext.sendPluginResult(pluginResult);
				}else{
					PluginResult pluginResult = new PluginResult(Status.ERROR, arg0.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
				}
			}
		});
    }

    //----  Querying and limiting

    // Query Limite to Number of children
    private void queryLimitedToNumberOfChildren(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	int nLimit = 0;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				nLimit = data.getInt(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToNumberOfChildren : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        urlRef.limit(nLimit).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("limit(limit).addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToNumberOfChildren failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //Qeury Limite to First
    private void queryLimitedToFirst(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	int nLimit = 0;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				nLimit = data.getInt(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToFirst : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        urlRef.limitToFirst(nLimit).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("limitToFirst(limit).addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToFirst failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //Qeury Limite to Last
    private void queryLimitedToLast(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	int nLimit = 0;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				nLimit = data.getInt(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToLast : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        urlRef.limitToLast(nLimit).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("LimitedToLast(limit).addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryLimitedToLast failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //Qeury Order By Child
    private void queryOrderedByChild(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	String strChild;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				strChild = data.getString(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryOrderedByChild : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        urlRef.orderByChild(strChild).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryOrderedByChild(path).addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryOrderedByChild failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //Qeury Order By Key
    private void queryOrderedByKey(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;

        if ( data.length() >= 1 )
        {
        	try {
				strURL = data.getString(0);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryOrderedByKey : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        // Read data and react to changes
        urlRef.orderByKey().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
                    PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryOrderedByKey().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryOrderedByKey failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //Qeury Starting At Value
    private void queryStartingAtValue(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	Object objStartValue;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				objStartValue = data.get(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryStartingAtValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryStartingAtValue().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryStartingAtValue failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };
        // Read data and react to changes
        if( objStartValue.getClass() == Double.class )
        	urlRef.startAt(((Double)objStartValue).doubleValue()).addListenerForSingleValueEvent(listener);
        else if(objStartValue.getClass() == Boolean.class )
        	urlRef.startAt(((Boolean)objStartValue).booleanValue()).addListenerForSingleValueEvent(listener);
        else if( objStartValue.getClass() == String.class )
        	urlRef.startAt((String)objStartValue).addListenerForSingleValueEvent(listener);
        else
        	urlRef.startAt(objStartValue.toString()).addListenerForSingleValueEvent(listener);

    }

    //Qeury Starting At Value With Child Key
    private void queryStartingAtValueChildKey(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	String strChildKey;
    	Object objStartValue;

        if ( data.length() >= 3 )
        {
        	try {
				strURL = data.getString(0);
				objStartValue = data.get(1);
				strChildKey = data.getString(2);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryStartingAtValueChildKey : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryStartingAtValueChildKey().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryStartingAtValueChildKey failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };

        // Read data and react to changes
        if( objStartValue.getClass() == Double.class )
        	urlRef.startAt(((Double)objStartValue).doubleValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if(objStartValue.getClass() == Boolean.class )
        	urlRef.startAt(((Boolean)objStartValue).booleanValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if( objStartValue.getClass() == String.class )
        	urlRef.startAt((String)objStartValue, strChildKey).addListenerForSingleValueEvent(listener);
        else
        	urlRef.startAt(objStartValue.toString(), strChildKey).addListenerForSingleValueEvent(listener);
    }

    //Qeury Ending At Value
    private void queryEndingAtValue(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	Object objEndValue;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				objEndValue = data.get(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEndingAtValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryEndingAtValue().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEndingAtValue failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };

        if( objEndValue.getClass() == Double.class )
        	urlRef.endAt(((Double)objEndValue).doubleValue()).addListenerForSingleValueEvent(listener);
        else if(objEndValue.getClass() == Boolean.class )
        	urlRef.endAt(((Boolean)objEndValue).booleanValue()).addListenerForSingleValueEvent(listener);
        else if( objEndValue.getClass() == String.class )
        	urlRef.endAt((String)objEndValue).addListenerForSingleValueEvent(listener);
        else
        	urlRef.endAt(objEndValue.toString()).addListenerForSingleValueEvent(listener);
    }

    //Qeury Ending At Value With Child Key
    private void queryEndingAtValueChildKey(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	String strChildKey;
    	Object objEndValue;

        if ( data.length() >= 3 )
        {
        	try {
				strURL = data.getString(0);
				objEndValue = data.get(1);
				strChildKey = data.getString(2);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEndingAtValueChildKey : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryEndingAtValueChildKey().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEndingAtValueChildKey failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };

        // Read data and react to changes
        if( objEndValue.getClass() == Double.class )
        	urlRef.endAt(((Double)objEndValue).doubleValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if(objEndValue.getClass() == Boolean.class )
        	urlRef.endAt(((Boolean)objEndValue).booleanValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if( objEndValue.getClass() == String.class )
        	urlRef.endAt((String)objEndValue, strChildKey).addListenerForSingleValueEvent(listener);
        else
        	urlRef.endAt(objEndValue.toString(), strChildKey).addListenerForSingleValueEvent(listener);
    }

    //Qeury Equal to Value
    private void queryEqualToValue(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	Object objValue;

        if ( data.length() >= 2 )
        {
        	try {
				strURL = data.getString(0);
				objValue = data.get(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEqualToValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryEqualToValue().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEqualToValue failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };
        // Read data and react to changes
        if( objValue.getClass() == Double.class )
        	urlRef.equalTo(((Double)objValue).doubleValue()).addListenerForSingleValueEvent(listener);
        else if(objValue.getClass() == Boolean.class )
        	urlRef.equalTo(((Boolean)objValue).booleanValue()).addListenerForSingleValueEvent(listener);
        else if( objValue.getClass() == String.class )
        	urlRef.equalTo((String)objValue).addListenerForSingleValueEvent(listener);
        else
        	urlRef.equalTo(objValue.toString()).addListenerForSingleValueEvent(listener);
    }

    //Qeury Equal to Value with Child Key
    private void queryEqualToValueChildKey(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	String strChildKey;
    	Object objValue;

        if ( data.length() >= 3 )
        {
        	try {
				strURL = data.getString(0);
				objValue = data.get(1);
				strChildKey = data.getString(2);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEqualToValueChildKey : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase urlRef = new Firebase(strURL);

        if(isUsed != true)
            isUsed = true;
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("queryEqualToValueChildKey().addListenerForSingleValueEvent failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEqualToValueChildKey failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        };

        // Read data and react to changes
        if( objValue.getClass() == Double.class )
        	urlRef.equalTo(((Double)objValue).doubleValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if(objValue.getClass() == Boolean.class )
        	urlRef.equalTo(((Boolean)objValue).booleanValue(), strChildKey).addListenerForSingleValueEvent(listener);
        else if( objValue.getClass() == String.class )
        	urlRef.equalTo((String)objValue, strChildKey).addListenerForSingleValueEvent(listener);
        else
        	urlRef.equalTo(objValue.toString(), strChildKey).addListenerForSingleValueEvent(listener);
    }
    public  enum QSearchType{
        QSearchAt_EQUAL(0),
        QSearchAt_STARTING(1),
        QSearchAt_ENDING(2);

        private int index;
        private QSearchType(int value) {
        	this.index = value;
        }
        public int getType(){
        	return index;
        }
    };

    public  enum QOrderType{
        QOrderBy_NOT(0),
        QOrderBy_CHILDKEY(1),
        QOrderBy_CHILDVALUE(2);

        private int index;
        private QOrderType(int value) {
        	this.index = value;
        }
        public int getType(){
        	return index;
        }
    };

    public  enum QLimitType{
        QLimitedTo_NOT(0),
        QLimitedTo_FIRST(1),
        QLimitedTo_LAST(2);

        private int index;
        private QLimitType(int value) {
        	this.index = value;
        }
        public int getType(){
        	return index;
        }
    };
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
    private void querySearch(JSONArray data) throws JSONException {
        //
        String strURL = String.format("https://%s.firebaseio.com", appName);
        JSONObject queryInfo;

        QSearchType typeofSearch = QSearchType.QSearchAt_EQUAL;
        Object searchValue = null;
        String strSearchKey = "";
        QOrderType typeofOrder = QOrderType.QOrderBy_NOT;
        String strOrderValue = "";
        QLimitType typeofLimit = QLimitType.QLimitedTo_NOT;
        Integer numLimited = 0;

       // String temp;
        if ( data.length() >= 2 )
        {
            strURL = data.getString(0);
            queryInfo = data.getJSONObject(1);
            {
                if (!queryInfo.has("search")){
//                    typeofSearch = QSearchType.QSearchAt_STARTING;
//                    searchValue = "";
//                    strSearchKey = "";
                }
                else {
                    if (queryInfo.getJSONObject("search").getString("type").compareTo("starting") == 0)
                        typeofSearch = QSearchType.QSearchAt_STARTING;
                    else if (queryInfo.getJSONObject("search").getString("type").compareTo("ending") == 0)
                        typeofSearch = QSearchType.QSearchAt_ENDING;
                    else    //"equal" or nil
                        typeofSearch = QSearchType.QSearchAt_EQUAL;
                    searchValue = queryInfo.getJSONObject("search").get("value");
                    if (searchValue == null)
                        searchValue = "";

                    strSearchKey = queryInfo.getJSONObject("search").getString("child");
                    if (strSearchKey == null)
                        strSearchKey = "";
                }

                if (!queryInfo.has("order")){
//                    typeofOrder = QOrderType.QOrderBy_NOT;
//                    strOrderValue = "";
                }
                else {
                    if (queryInfo.getJSONObject("order").getString("by").compareTo("key") == 0)
                        typeofOrder = QOrderType.QOrderBy_CHILDKEY;
                    else if (queryInfo.getJSONObject("order").getString("by").compareTo("value") == 0)
                        typeofOrder = QOrderType.QOrderBy_CHILDVALUE;
                    else
                        typeofOrder = QOrderType.QOrderBy_NOT;

                    strOrderValue = queryInfo.getJSONObject("order").getString("field");
                    if (strOrderValue == null)
                        strOrderValue = "";
                }

                if (!queryInfo.has("limit")) {
//                    typeofLimit = QLimitType.QLimitedTo_NOT;
//                    numLimited = 0;
                }
                else{
                    if (queryInfo.getJSONObject("limit").getString("at").compareTo("first") == 0)
                        typeofLimit = QLimitType.QLimitedTo_FIRST;
                    else if (queryInfo.getJSONObject("limit").getString("at").compareTo("last") == 0)
                        typeofLimit = QLimitType.QLimitedTo_LAST;
                    else
                        typeofLimit = QLimitType.QLimitedTo_NOT;

                    if (queryInfo.getJSONObject("limit").isNull("num"))
                        numLimited = 0;
                    else
                        numLimited = queryInfo.getJSONObject("limit").getInt("num");
                }
            }
        }

        Firebase urlRef = new Firebase(strURL);
        Query queryOrder = null;

        switch (typeofOrder) {
            case QOrderBy_NOT:
            default:
            case QOrderBy_CHILDKEY:
                queryOrder = urlRef.orderByKey();
                break;

            case QOrderBy_CHILDVALUE:
                queryOrder = urlRef.orderByChild(strOrderValue);
                break;
        }

        Query queryObj = null;

        switch (typeofSearch) {
            case QSearchAt_EQUAL:
                if(strSearchKey == null | strSearchKey.length() == 0){
                    if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.equalTo(((Double)searchValue).doubleValue());
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.equalTo(((Boolean)searchValue).booleanValue());
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.equalTo((String)searchValue);
                    else
                    	queryObj = queryOrder.equalTo(searchValue.toString());
                }
                else{
                	if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.equalTo(((Double)searchValue).doubleValue(), strSearchKey);
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.equalTo(((Boolean)searchValue).booleanValue(), strSearchKey);
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.equalTo((String)searchValue, strSearchKey);
                    else
                    	queryObj = queryOrder.equalTo(searchValue.toString(), strSearchKey);
                }
                break;

            case QSearchAt_STARTING:
                if(strSearchKey == null | strSearchKey.length() == 0){
                	if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.startAt(((Double)searchValue).doubleValue());
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.startAt(((Boolean)searchValue).booleanValue());
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.startAt((String)searchValue);
                    else
                    	queryObj = queryOrder.startAt(searchValue.toString());
                }
                else{
                	if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.startAt(((Double)searchValue).doubleValue(), strSearchKey);
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.startAt(((Boolean)searchValue).booleanValue(), strSearchKey);
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.startAt((String)searchValue, strSearchKey);
                    else
                    	queryObj = queryOrder.startAt(searchValue.toString(), strSearchKey);
                }
                break;

            case QSearchAt_ENDING:
                if(strSearchKey == null | strSearchKey.length() == 0){
                	if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.endAt(((Double)searchValue).doubleValue());
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.endAt(((Boolean)searchValue).booleanValue());
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.endAt((String)searchValue);
                    else
                    	queryObj = queryOrder.endAt(searchValue.toString());
                }
                else{
                	if( searchValue.getClass() == Double.class )
                    	queryObj = queryOrder.endAt(((Double)searchValue).doubleValue(), strSearchKey);
                    else if(searchValue.getClass() == Boolean.class )
                    	queryObj = queryOrder.endAt(((Boolean)searchValue).booleanValue(), strSearchKey);
                    else if( searchValue.getClass() == String.class )
                    	queryObj = queryOrder.endAt((String)searchValue, strSearchKey);
                    else
                    	queryObj = queryOrder.endAt(searchValue.toString(), strSearchKey);
                }
                break;

            default:
            	queryObj = queryOrder.startAt();//queryObj = [queryOrder queryStartingAtValue:nil];
                break;
        }
        Query queryLimit = null;
        switch (typeofLimit) {
            case QLimitedTo_NOT:
            default:
                queryLimit = queryObj;
                break;
            case QLimitedTo_FIRST:
                queryLimit = queryObj.limitToFirst(numLimited);
                break;
            case QLimitedTo_LAST:
                queryLimit = queryObj.limitToLast(numLimited);
            break;
        }
        queryLimit.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                JSONObject resultObj;
				try {
                    HashMap result = snapshot.getValue(HashMap.class);
                    if (result == null)
                        resultObj = new JSONObject();
                    else
                        resultObj = new JSONObject(result);
	                PluginResult pluginResult = new PluginResult(Status.OK, resultObj);
	                //pluginResult.setKeepCallback(true);
	                mCallbackContext.sendPluginResult(pluginResult);
				} catch (Exception e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                System.out.println("The querySearch(addListenerForSingleValueEvent) failed: " + firebaseError.getMessage());
                PluginResult pluginResult = new PluginResult(Status.ERROR, "The querySearch failded: " + firebaseError.getMessage());
                mCallbackContext.sendPluginResult(pluginResult);
            }
        });
    }

    //User Log in
    private void userLogin(JSONArray data) {
    	String strURL = String.format("https://%s.firebaseio.com", appName); // = "https://%@.firebaseio.com" + appName;
    	String userName;
    	String password;

        if ( data.length() >= 2 )
        {
        	try {
				userName = data.getString(0);
				password = data.getString(1);
			} catch (JSONException e) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, e.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
				e.printStackTrace();
				return;
			}
        }
        else{
        	PluginResult pluginResult = new PluginResult(Status.ERROR, "queryEqualToValue : Parameter Error");
        	mCallbackContext.sendPluginResult(pluginResult);
        	return;
        }

        Firebase rootRef = new Firebase(strURL);
        rootRef.authWithPassword(userName, password, new Firebase.AuthResultHandler() {

			@Override
			public void onAuthenticationError(FirebaseError arg0) {
				PluginResult pluginResult = new PluginResult(Status.ERROR, "authWithPassword failded: " + arg0.getMessage());
				mCallbackContext.sendPluginResult(pluginResult);
			}

			@Override
			public void onAuthenticated(AuthData arg0) {
				JSONObject obj;
				try {
					obj = new JSONObject(arg0.getAuth().toString());
					PluginResult pluginResult = new PluginResult(Status.OK, obj);
					//pluginResult.setKeepCallback(true);
					mCallbackContext.sendPluginResult(pluginResult);
				} catch (JSONException e) {
					PluginResult pluginResult = new PluginResult(Status.ERROR, "AuthData failded: " + e.getMessage());
					mCallbackContext.sendPluginResult(pluginResult);
					e.printStackTrace();
				}
			}
		});
    }
}