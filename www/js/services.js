

angular.module('starter.services', [])


    .factory('$localstorage', ['$window', function ($window) {
        return {
            set: function (key, value) {
                $window.localStorage[key] = value;
            },
            get: function (key, defaultValue) {
                return $window.localStorage[key] || defaultValue;
            },
            remove: function (key) {
                $window.localStorage.removeItem(key);
            },
            setObject: function (key, value) {
                $window.localStorage[key] = JSON.stringify(value);
            },
            getObject: function (key) {
                return JSON.parse($window.localStorage[key] || '{}');
            }
        }
    }])

    .factory('AuthCheck', ['$window', '$localstorage', function ($window, $localstorage) {
        return {
            checkUser: function (key, value) {
                var user = Object.keys($localstorage.getObject('user')).length;

                if(user > 0){
                    return true;
                }else {
                    return false;
                }
            }
        }
    }])


    .factory('getGyms', ['$localstorage', function ($localstorage) {
        return {
            gymslist: function (callback) {
                var firebase = cordova.require("cordova/plugin/FireBase");
                //firebase.init('momentum-checkin', function(success) {
                    var strURL = 'https://momentum-checkin.firebaseio.com/gyms';

                    queryInfo = {
                        search: {
                            type: 'starting',
                            value: "",
                            child: ""
                        }
                    };
                    //console.log(queryInfo);
                    firebase.querySearch(strURL, queryInfo, function(success) {
                        //console.log(callback);
                        //console.log(success);
                        callback(success);
                    }, function(fail) {
                        console.log(fail);
                        callback(fail);
                    });
                //}, function(fail) {
                //    alert("init failed: " + fail);
                //});
            },
            removeAllCallbackEvents: function (callback, table) {
                var firebase = cordova.require("cordova/plugin/FireBase");
                var url = 'https://momentum-checkin.firebaseio.com/'+table;
                firebase.removeAllCallbacksWithURL(url, function(success) {
                    console.log(success);
                    callback(success);
                }, function(fail) {
                    console.log(fail);
                    callback(fail);
                });
            }
        }
    }])

    .factory('BeaconsScans', ['$localstorage', '$rootScope', function ($localstorage, $rootScope) {
        return {
            stopScan: function (callback) {

                angular.forEach($rootScope.beaconsList, function (value, key) {
                    if(value.ibeacon_code !== "" && value.uuid !== "" && value.major !== "" && value.minor !== "") {
                        var beaconRegion = new cordova.plugins.locationManager.BeaconRegion(
                            value.ibeacon_code, value.uuid, value.major, value.minor);
                        // Start monitoring.
                        cordova.plugins.locationManager.stopMonitoringForRegion(beaconRegion)
                            .fail(console.error)
                            .done();

                        cordova.plugins.locationManager.stopRangingBeaconsInRegion(beaconRegion)
                            .fail(console.error)
                            .done();
                    }
                });

                callback();
            }
        }
    }])

    .factory('LocalNotification', ['$window', '$localstorage', '$cordovaLocalNotification', function ($window, $localstorage, $cordovaLocalNotification) {
        return {
            sendNotification: function (object) {
                console.log('sendNotification', object);
                var now = new Date().getTime();
                var _10SecondsFromNow = new Date(now + 5 * 1000);


                $cordovaLocalNotification.schedule({
                    id: 1,
                    title: 'GYM Detected',
                    text: 'Would you like to check-in?',
                    at: _10SecondsFromNow,
                    data: object
                }).then(function (result) {
                    console.log(result);
                });
            }
        }
    }])

