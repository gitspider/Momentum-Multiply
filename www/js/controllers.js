
angular.module('starter.controllers', [])

    .controller('authCtrl', ['$scope', '$timeout', '$localstorage', '$state', '$rootScope', '$ionicPlatform', 'getGyms', '$cordovaSplashscreen', function($scope, $timeout, $localstorage, $state, $rootScope, $ionicPlatform, getGyms, $cordovaSplashscreen) {
        $ionicPlatform.ready(function () {
            $timeout(function () {
                getGyms.gymslist($scope.callbackInit)
            }, 500);
        });


        $scope.callbackInit = function (gymList) {

            $rootScope.userFirebaseId   = $localstorage.get('userFirebaseId');
            $rootScope.user             = $localstorage.getObject('user');
            $rootScope.beaconsList = gymList;

            ble.isEnabled(function() {

                //console.log("Bluetooth is enabled");
                //console.log($rootScope.beaconsList);
                //console.log("Bluetooth is enabled");
                //var points = 0;
                //console.log(points > 0)
                var Userid = $localstorage.get('userFirebaseId');
                //console.log('USERID');
                //console.log(Userid);
                //console.log('USERID');

                var firebase = cordova.require("cordova/plugin/FireBase");
                firebase.init('momentum-checkin', function(success) {

                    var url2 = 'https://momentum-checkin.firebaseio.com/checkins';

                    queryInfo = {
                        order: {            // if this key is nil, not order
                            by: 'value',          // 'key', 'value' or nil
                            field: 'user'       // if the key "by" is 'key', doesn't use this key but if not, use.
                        },
                        search: {
                            type: 'equal',   //'starting', 'ending' 'equal' or nil
                            value: ""+Userid,           // object
                            //value: ""+Userid,           // object
                            child: ""     // string   //if this key is nil or empty, doesn't use.
                        }
                    };
                    $cordovaSplashscreen.hide();
                    //alert(JSON.stringify(queryInfo));
                    //console.log(queryInfo);
                    firebase.querySearch(url2, queryInfo, function(success2) {
                        //console.log('result');
                        //console.log(success2);
                        //console.log('result');


                        if(success2 == null){
                            //If user points == null send user to welcome page
                            //console.log('empty checkins');
                            $state.go("welcome");

                        }else {

                            //console.log('NOTempty checkins');

                            $scope.totalPoints = 0;
                            //console.log('angular.forEach');
                            //console.log(success2);
                            //console.log('angular.forEach');
                            angular.forEach(success2, function (value, key) {
                                //console.log('angular.forEach');
                                //console.log('GGGGGGG');
                                //console.log(value);
                                //console.log('GGGGGGG');
                                //alert(value.points_allocated);
                                //console.log('angular.forEach');
                                $scope.totalPoints = $scope.totalPoints + parseInt(value.points_allocated);
                            });

                            //console.log($scope.totalPoints);

                            //console.log($scope.totalPoints);
                            if($scope.totalPoints > 0) {
                                $state.go("dashboard");
                            }else {
                                $state.go("welcome");
                            }
                        }
                    }, function(fail) {
                        console.log(fail);
                    });
                }, function(fail) {
                    alert("init failed: " + fail);
                });


            }, function() {
                console.log("Bluetooth is *not* enabled");
                $state.go('bluetooth');
                $cordovaSplashscreen.hide();
            });
        };

    }])

    .controller('LoginCtrl', ['$scope', '$timeout', '$localstorage', '$state', '$rootScope', 'getGyms', '$cordovaSplashscreen', function($scope, $timeout, $localstorage, $state, $rootScope, getGyms, $cordovaSplashscreen) {

        $scope.checkUser = false;
        $scope.userError = false;
        $scope.usersuccesAuth = false;
        $scope.login = {
            number: ""
            //number: 47824674341   //mark
            //number: 47824653812  //greg
        };
        $scope.user = {};
        $scope.username = "";


        $scope.subForm = function(form) {
            //console.log(form);
            if(form.$valid){
                $scope.checkUser = !$scope.checkUser;

                var firebase = cordova.require("cordova/plugin/FireBase");
                var strURL = 'https://momentum-checkin.firebaseio.com/users';

                queryInfo = {
                    order: {            // if this key is nil, not order
                        by: 'value',          // 'key', 'value' or nil
                        field: 'multiply_number'       // if the key "by" is 'key', doesn't use this key but if not, use.
                    },
                    //search: {
                    //    type: 'starting',   //'starting', 'ending' 'equal' or nil
                    //    value: $scope.login.number,           // object
                    //    child: 'multiply_number'           // string   //if this key is nil or empty, doesn't use.
                    //},
                    search: {
                        type: 'equal',   //'starting', 'ending' 'equal' or nil
                        value: ""+$scope.login.number,           // object
                        child: ''           // string   //if this key is nil or empty, doesn't use.
                    },
                    limit: {            // if this key is nil, no limit.
                        at: "last",        //'first', last' or nil
                        num: 1             //integer
                    }
                };

                //console.log('queryInfo');
                //console.log(queryInfo);
                //console.log('queryInfo');
                firebase.querySearch(strURL, queryInfo, function(success) {
                    //console.log("subForm success");
                    //console.log(success);
                    //console.log("subForm success");

                    //console.log('successNOTNULL', Object.keys(success).length);
                    //console.log('successNOTNULL', typeof(success));
                    //console.log('successNOTNULL', success != null);
                    //if(Object.keys(success).length != 0 || success != null){

                    var isIOS = ionic.Platform.isIOS();
                    //console.log('IIIIIIOOOOS', isIOS);

                    $scope.loginRes = false;

                    if(isIOS) {
                        //console.log('IOS');
                        if(success != null){
                            $scope.loginRes = true;
                        }
                    }else {
                        //console.log('Android');
                        if(Object.keys(success).length != 0){
                            $scope.loginRes = true;
                        }
                    }

                    if($scope.loginRes){
                        //console.log('login true');
                        //console.log("querySearch success :");
                        //console.log(Object.keys(success)[0]);
                        $scope.user = success[Object.keys(success)[0]];
                        $rootScope.user = success[Object.keys(success)[0]];
                        $rootScope.userFirebaseId = Object.keys(success)[0];
                        //console.log($scope.user);
                        $scope.checkUser = !$scope.checkUser;
                        $scope.usersuccesAuth = true;
                    }else {
                        //console.log('login false');
                        $scope.userError = true;
                        $scope.checkUser = !$scope.checkUser;
                        $timeout(function () {
                            $scope.userError = false;
                        }, 4000);
                    }

                }, function(fail) {
                    //console.log("querySearch failed: " + fail);
                    $scope.userError = true;
                    $scope.checkUser = !$scope.checkUser;
                    $timeout(function () {
                        $scope.userError = false;
                    }, 4000);

                });
            }
        };

        $scope.backToLogin = function () {
            $scope.usersuccesAuth = false;
            $scope.login = {
                number: ""
            };
        };

        $scope.acceptLogin = function () {
            $localstorage.set('userFirebaseId', $rootScope.userFirebaseId);
            $localstorage.setObject('user', $rootScope.user);
            getGyms.gymslist($scope.processLogin);
        };

        $scope.processLogin = function (gymslist) {
            //console.log(gymslist);

            $rootScope.beaconsList = gymslist;
            //console.log($rootScope.beaconsList);

            ble.isEnabled(
                function() {
                    //console.log("Bluetooth is enabled");

                    var firebase = cordova.require("cordova/plugin/FireBase");
                    var strURL = 'https://momentum-checkin.firebaseio.com/checkins';

                    queryInfo = {
                        order: {            // if this key is nil, not order
                            by: 'value',          // 'key', 'value' or nil
                            field: 'user'       // if the key "by" is 'key', doesn't use this key but if not, use.
                        },
                        search: {
                            type: 'equal',   //'starting', 'ending' 'equal' or nil
                            value: ""+$rootScope.userFirebaseId,           // object
                            child: ""     // string   //if this key is nil or empty, doesn't use.
                        }
                    };

                    //console.log(queryInfo);
                    firebase.querySearch(strURL, queryInfo, function(success) {
                        //console.log('result after login');
                        //console.log(success);
                        //console.log(success == null);
                        //console.log('result after login');

                        if(success == null){
                            //console.log('will load welcome page')
                            //If user points == null send user to welcome page
                            $state.go("welcome");
                        }else {
                            //If user points > 0 send user to dashboard page
                            $scope.totalPoints = 0;
                            angular.forEach(success, function (value, key) {
                                //console.log(value);
                                $scope.totalPoints = $scope.totalPoints + parseInt(value.points_allocated);
                            });

                            //console.log($scope.totalPoints);
                            if($scope.totalPoints > 0) {
                                $state.go("dashboard");
                            }else {
                                $state.go("welcome");
                            }
                        }
                    }, function(fail) {
                        console.log(fail);
                    });
                },
                function() {
                    console.log("Bluetooth is *not* enabled");
                    $state.go("bluetooth");
                }
            );

        }
    }])

    .controller('BluetoothCtrl', ['$scope', '$rootScope', '$timeout', '$ionicPlatform', '$cordovaDevice', '$state', '$ionicPopup', '$localstorage', function($scope, $rootScope, $timeout, $ionicPlatform, $cordovaDevice, $state, $ionicPopup, $localstorage) {
        $scope.windowHeight = $(window).height();
        $('.bluetooth-panel').height($scope.windowHeight/2+20);

        $scope.android = false;

        $scope.EnabledBLE = false;


        ionic.Platform.ready(function(){
            $scope.isIOS = ionic.Platform.isIOS();

            if($scope.isIOS) {
                $scope.android = false;
            }else {
                $scope.android = true;
            }
        });

        $scope.bluetoothChange = function () {
            if($scope.EnabledBLE){
                //console.log('enable true');
                ble.enable(
                    function() {
                        //console.log("Bluetooth is enabled");

                        var Userid = $localstorage.get('userFirebaseId');

                        var firebase = cordova.require("cordova/plugin/FireBase");
                        firebase.init('momentum-checkin', function(success) {
                            //var firebase = cordova.require("cordova/plugin/FireBase");
                            var url = 'https://momentum-checkin.firebaseio.com/checkins';

                            var queryInfo = {
                                order: {            // if this key is nil, not order
                                    by: 'value',          // 'key', 'value' or nil
                                    field: 'user'       // if the key "by" is 'key', doesn't use this key but if not, use.
                                },
                                search: {
                                    type: 'equal',   //'starting', 'ending' 'equal' or nil
                                    value: Userid,           // object
                                    child: ""     // string   //if this key is nil or empty, doesn't use.
                                }
                            };
                            //alert(JSON.stringify(queryInfo));

                            //console.log(queryInfo);
                            firebase.querySearch(url, queryInfo, function(success) {
                                //alert(3);
                                //console.log('result');
                                //console.log(success);
                                //console.log('result');

                                if(success == null){
                                    //alert(4);
                                    //If user points == null send user to welcome page
                                    //console.log('empty checkins');
                                    $state.go("welcome");
                                }else {
                                    //alert(5);
                                    //If user points > 0 send user to dashboard page
                                    $scope.totalPoints = 0;
                                    angular.forEach(success, function (value, key) {
                                        //console.log(value);
                                        $scope.totalPoints = $scope.totalPoints + parseInt(value.points_allocated);
                                    });

                                    //console.log($scope.totalPoints);
                                    if($scope.totalPoints > 0) {
                                        //alert(6);
                                        //console.log('GO DASHBOARD');
                                        $state.go("dashboard");
                                    }else {
                                        //alert(7);
                                        //console.log('GO WELCOME');
                                        $state.go("welcome");
                                    }
                                }
                            }, function(fail) {
                                console.log(fail);
                            });
                        }, function(fail) {
                            alert("init failed: " + fail);
                        });
                    },
                    function() {
                        console.log("The user did *not* enable Bluetooth");
                    }
                );
            }
        };

        $scope.checkBLEenabled = function () {
            ble.isEnabled(
                function() {
                    //console.log("Bluetooth is enabled");
                    var Userid = $localstorage.get('userFirebaseId');
                    var firebase = cordova.require("cordova/plugin/FireBase");
                    firebase.init('momentum-checkin', function(success) {
                        //var firebase = cordova.require("cordova/plugin/FireBase");
                        var url = 'https://momentum-checkin.firebaseio.com/checkins';

                        queryInfo = {
                            order: {            // if this key is nil, not order
                                by: 'value',          // 'key', 'value' or nil
                                field: 'user'       // if the key "by" is 'key', doesn't use this key but if not, use.
                            },
                            search: {
                                type: 'equal',   //'starting', 'ending' 'equal' or nil
                                value: ""+Userid,           // object
                                child: ""     // string   //if this key is nil or empty, doesn't use.
                            }
                        };

                        //console.log(queryInfo);
                        firebase.querySearch(url, queryInfo, function(success) {
                            //console.log('result');
                            //console.log(success);
                            //console.log('result');

                            if(success == null){
                                //If user points == null send user to welcome page
                                //console.log('empty checkins');
                                $state.go("welcome");
                            }else {
                                //If user points > 0 send user to dashboard page
                                $scope.totalPoints = 0;
                                angular.forEach(success, function (value, key) {
                                    //console.log(value);
                                    $scope.totalPoints = $scope.totalPoints + parseInt(value.points_allocated);
                                });

                                //console.log($scope.totalPoints);
                                if($scope.totalPoints > 0) {
                                    //console.log('GO DASHBOARD');
                                    $state.go("dashboard");
                                }else {
                                    //console.log('GO WELCOME');
                                    $state.go("welcome");
                                }
                            }
                        }, function(fail) {
                            console.log(fail);
                        });
                    }, function(fail) {
                        alert("init failed: " + fail);
                    });
                },
                function() {
                    //console.log("Bluetooth is *not* enabled");
                    var alertPopup = $ionicPopup.alert({
                        title: 'Notification!',
                        template: 'Bluetooth is *not* enabled'
                    });

                }
            );
        };

    }])

    .controller('WelcomeCtrl', ['$scope', '$timeout', '$cordovaLocalNotification', '$ionicPlatform', '$state', '$rootScope', '$localstorage', 'LocalNotification', function($scope, $timeout, $cordovaLocalNotification, $ionicPlatform, $state, $rootScope, $localstorage, LocalNotification) {
        $scope.windowHeight = $(window).height();
        $scope.pointspanel = $('.points-panel').height();

        $scope.user = ($localstorage.getObject('user'));

        //$('.bluetooth-panel').height($scope.windowHeight-$scope.pointspanel);
        $('.circle').circleProgress({
            startAngle: -1.5,
            value:0/ 100,
            lineCap: 'square',
            size: 120,
            fill: { color: '#ffa500' },
            emptyFill: "rgba(0, 0, 0, 1)",
        });


        $scope.onepush = false;

        $scope.enableBeaconScan = function () {

            cordova.plugins.locationManager.requestAlwaysAuthorization();

            var delegate = new cordova.plugins.locationManager.Delegate();

            delegate.didDetermineStateForRegion = function (pluginResult) {
                //console.log(pluginResult);
            };

            delegate.didStartMonitoringForRegion = function (pluginResult) {
                //console.log('didStartMonitoringForRegion:');
                //console.log(pluginResult);
                //console.log('didStartMonitoringForRegion:');
            };

            delegate.didRangeBeaconsInRegion = function (pluginResult) {

                if (pluginResult.beacons.length > 0) {
                    $scope.deviceCheckIn = $localstorage.getObject('lastCheckIn');
                    //console.log(pluginResult.region);
                    //console.log($rootScope.backgroundMode);
                    var ResData = {};
                    angular.forEach($rootScope.beaconsList, function (value, key) {
                        //console.log('BEACONS FOREACH');
                        //console.log(value, key);
                        //console.log('BEACONS FOREACH');
                        if(value.ibeacon_code == pluginResult.region.identifier){
                            ResData = {gym_id: key, gym_data:value};
                        }
                    });

                    if(moment(moment().format('YYYY-MM-DD')).isAfter($scope.deviceCheckIn.date) && !$scope.onepush ||  typeof ($scope.deviceCheckIn.date) == "undefined" && !$scope.onepush){
                        $scope.onepush = true;
                        if($rootScope.backgroundMode) {
                            $scope.onepush = true;
                            //console.log('background mode beacon');
                            LocalNotification.sendNotification({
                                points: ResData.gym_data.point_allocation,
                                gym_name: ResData.gym_data.name,
                                gym_id: ResData.gym_id,
                                ibeacon_code: ResData.gym_data.ibeacon_code
                            });


                        }else {
                            //console.log('WORKING');

                            var isIOS = ionic.Platform.isIOS();
                            //console.log('IIIIIIOOOOS', isIOS);

                            LocalNotification.sendNotification({
                                points: ResData.gym_data.point_allocation,
                                gym_name: ResData.gym_data.name,
                                gym_id: ResData.gym_id,
                                ibeacon_code: ResData.gym_data.ibeacon_code
                            });
                            $scope.disableBeaconScan();
                            $state.go('checkin', {
                                points: ResData.gym_data.point_allocation,
                                gym_name: ResData.gym_data.name,
                                gym_id: ResData.gym_id,
                                ibeacon_code: ResData.gym_data.ibeacon_code,
                            });
                        }

                        $timeout(function () {
                            $scope.onepush = false;
                        },15000);
                    }
                }

                //logToDom('[DOM] didRangeBeaconsInRegion: ' + JSON.stringify(pluginResult));
            };

            cordova.plugins.locationManager.setDelegate(delegate);
            //cordova.plugins.locationManager.requestWhenInUseAuthorization();
            cordova.plugins.locationManager.requestAlwaysAuthorization();

            angular.forEach($rootScope.beaconsList, function (value, key) {
                //console.log('BEACON TO REGION');
                //console.log(value, key);
                //console.log('BEACON TO REGION');

                if(value.ibeacon_code != "" && value.uuid != "" && value.major != "" && value.minor != "") {
                    var beaconRegion = new cordova.plugins.locationManager.BeaconRegion(
                        value.ibeacon_code, value.uuid, value.major, value.minor);
                    // Start monitoring.
                    cordova.plugins.locationManager.startMonitoringForRegion(beaconRegion)
                        .fail(console.error)
                        .done();

                    // Start ranging.
                    cordova.plugins.locationManager.startRangingBeaconsInRegion(beaconRegion)
                        .fail(console.error)
                        .done();
                }
            });


        };

        $scope.disableBeaconScan = function () {
            angular.forEach($rootScope.beaconsList, function (value, key) {
                if(value.ibeacon_code != "" && value.uuid != "" && value.major != "" && value.minor != "") {
                    var beaconRegion = new cordova.plugins.locationManager.BeaconRegion(
                        value.ibeacon_code, value.uuid, value.major, value.minor);
                    // Start monitoring.
                    cordova.plugins.locationManager.startMonitoringForRegion(beaconRegion)
                        .fail(console.error)
                        .done();

                    cordova.plugins.locationManager.stopRangingBeaconsInRegion(beaconRegion)
                        .fail(console.error)
                        .done();
                }

            });
        };


        $scope.updateBeaconsList = function () {
            var firebase = cordova.require("cordova/plugin/FireBase");
            firebase.init('momentum-checkin', function(success) {
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
                    $rootScope.beaconsList = success;
                    $scope.enableBeaconScan();
                }, function(fail) {
                    console.log(fail);
                });
            }, function(fail) {
                alert("init failed: " + fail);
            });
        };




        $ionicPlatform.ready(function () {
            $scope.enableBeaconScan();
            //console.log('$ionicPlatform.ready');
            var firebase = cordova.require("cordova/plugin/FireBase");
            firebase.init('momentum-checkin', function(success) {
                //var firebase = cordova.require("cordova/plugin/FireBase");
                var url = 'https://momentum-checkin.firebaseio.com/gyms';
                firebase.readValueTypeEventWithURL(url, function(success) {
                    $scope.disableBeaconScan();
                    $scope.updateBeaconsList();
                }, function(fail) {
                    console.log(fail);
                });
            }, function(fail) {
                alert("init failed: " + fail);
            });
        });

    }])

    .controller('CheckinCtrl', ['$scope', '$timeout', '$cordovaLocalNotification', '$state', '$stateParams', '$localstorage', '$rootScope', '$cordovaNetwork', '$ionicHistory', function($scope, $timeout, $cordovaLocalNotification, $state, $stateParams, $localstorage, $rootScope, $cordovaNetwork, $ionicHistory) {
        //console.log($stateParams);
        $scope.points           = $stateParams.points;
        $scope.gym_name         = $stateParams.gym_name;
        $scope.ibeacon_code     = $stateParams.ibeacon_code;
        $scope.gym_id           = $stateParams.gym_id;
        $scope.userFirebaseId   = $localstorage.get('userFirebaseId');

        $scope.windowHeight = $(window).height();
        $('.bluetooth-panel').height($scope.windowHeight/2+20);

        $scope.checkinOpen = true;

        $scope.checkin = function () {
            //console.log('ttttt');
            $scope.checkinOpen = false;

            $cordovaLocalNotification.cancelAll().then(function (result) {
                // ...
            });


            var firebase = cordova.require("cordova/plugin/FireBase");
            firebase.init('momentum-checkin', function(success) {
                var strURL = 'https://momentum-checkin.firebaseio.com/checkins';

                //console.log($scope.gym_id);
                //console.log($scope.points);
                //console.log($scope.userFirebaseId);
                var Object =  {
                    date_time: moment().format('YYYY-MM-DD HH:mm:ss'),
                    gym: $scope.gym_id,
                    points_allocated: $scope.points,
                    user: $scope.userFirebaseId
                };
                //console.log(Object);
                //console.log('$scope.checkin');
                var isOffline = $cordovaNetwork.isOffline();

                firebase.writeValueToURLWithAutoID(strURL, Object, function(success) {
                    //console.log('writeValueToURLWithAutoID');
                    //console.log(success);
                    $scope.processCheking();
                }, function(fail) {
                    console.log(fail);
                });

                if(isOffline){
                    $scope.processCheking();
                }
            }, function(fail) {
                alert("init failed: " + fail);
            });

        };


        $scope.processCheking = function () {
            //console.log('processCheking');
            $timeout(function () {
                $localstorage.setObject('lastCheckIn', {
                    date: moment().format('YYYY-MM-DD'),
                    gym_id: $scope.gym_id
                });
                $state.go('dashboard');
            },1000);
            $timeout(function () {
                $scope.checkinOpen = true;
            },2000);
        }
    }])

    .controller('DashboardCtrl', ['$scope', '$timeout', '$cordovaLocalNotification', '$localstorage', '$rootScope', '$ionicPlatform', '$state', '$filter', 'LocalNotification', 'filterFilter', 'getGyms', function($scope, $timeout, $cordovaLocalNotification, $localstorage, $rootScope, $ionicPlatform, $state, $filter, LocalNotification, filterFilter, getGyms) {
        $scope.windowHeight = $(window).height();
        $scope.pointspanel = $('.points-panel').height();
        $scope.userFirebaseId   = $localstorage.get('userFirebaseId');

        $scope.user = ($localstorage.getObject('user'));

        $scope.gymList = [];

        $scope.tempPoints = 0;


        angular.forEach($rootScope.beaconsList, function (value, key){
            $scope.gymList.push({
                id: key,
                name: value.name
            });
        });
        //console.log($rootScope.beaconsList);
        //console.log($scope.gymList);




        var firebase = cordova.require("cordova/plugin/FireBase");


        $scope.updateChkinsList = function () {
            console.log('updateChkinsList');
            $scope.Checkin = {};

            firebase.init('momentum-checkin', function(success) {
                var strURL = 'https://momentum-checkin.firebaseio.com/checkins';

                queryInfo = {
                    order: {            // if this key is nil, not order
                        by: 'value',          // 'key', 'value' or nil
                        field: 'user'       // if the key "by" is 'key', doesn't use this key but if not, use.
                    },
                    search: {
                        type: 'equal',   //'starting', 'ending' 'equal' or nil
                        value: ""+$scope.userFirebaseId,           // object
                        child: ""     // string   //if this key is nil or empty, doesn't use.
                    },
                    limit: {
                        at: 'last', //'first', last' or nil
                        num: 31
                    }
                };

                //console.log(queryInfo);
                firebase.querySearch(strURL, queryInfo, function(success) {
                    //console.log('NEWLIST');
                    //console.log(success);
                    //console.log('NEWLIST');



                    $timeout(function () {
                        $scope.totalPoints = 0;
                        $scope.tempCheckin = [];
                        $scope.lastCheckin = [];


                        $scope.lasttime = [];

                        angular.forEach(success, function (value, key){
                            //console.log(value);

                            var GymObj = filterFilter($scope.gymList, value.gym);
                            //console.log(GymObj, value.gym);

                            $scope.tempCheckin.push({
                                date: moment(value.date_time).format('DD MMM YYYY'),
                                time: moment(value.date_time).format('HH:mm'),
                                datetime: moment(value.date_time).format('YYYY-MMM-DD HH:mm:s'),
                                date_stamp: parseInt(moment(value.date_time).unix()),
                                prevMonth: moment().format('MMM') != moment(value.date_time).format('MMM') ? true : false,
                                name: GymObj[0].name,
                                points: value.points_allocated
                            });

                            $scope.lasttime.push({
                                date: moment(value.date_time).format('YYYY-MMM-DD HH:mm:s'),
                                date_stamp: moment(value.date_time).unix(),
                            });

                            if(moment().format('MMM') == moment(value.date_time).format('MMM')){
                                $scope.totalPoints = $scope.totalPoints + parseInt(value.points_allocated);
                            }
                        });


                        $scope.lastCheckin = [];
                        $timeout(function(){
                            $scope.lastCheckin =  $scope.tempCheckin;

                            //console.log($scope.totalPoints);
                            //console.log($scope.lastCheckin);

                            $scope.findEarliestDate = function(dates){
                                if(dates.length == 1){
                                    return dates[0];
                                }
                                var Obj = {};
                                var earliestDate = dates[0].date_stamp;
                                Obj = dates[0];
                                for(var i = 1; i < dates.length ; i++){
                                    var currentDate = dates[i].date_stamp;
                                    //console.log(currentDate, earliestDate, currentDate > earliestDate);
                                    if(currentDate > earliestDate){

                                        earliestDate = currentDate;
                                        Obj = dates[i];
                                    }
                                }
                                //console.log(Obj);
                                return Obj;
                            };

                            var lstD = $scope.findEarliestDate($scope.lastCheckin);




                            //console.log('LLLLLLLL');
                            //console.log(lstD);
                            //console.log('LLLLLLLL');

                            $scope.checkinDate      = lstD.date;
                            $scope.checkinTime      = lstD.time;
                            $scope.checkinName      = lstD.name;
                            $scope.checkinPoints    = lstD.points;




                            var percents = (parseInt($scope.totalPoints)/62)/100;

                            if($scope.totalPoints > 6200) {
                                percents = (parseInt(6200)/62)/100;
                            }


                            if($scope.tempPoints != percents){
                                $scope.tempPoints = percents;
                                $('.circle').circleProgress({
                                    startAngle: -1.55,
                                    value: percents,
                                    lineCap: 'square',
                                    size: 120,
                                    fill: { color: '#007888' },
                                    emptyFill: "rgba(0, 0, 0, 1)",
                                });
                                //
                                $('.number').animateNumber({number: $scope.totalPoints}, 1500);
                            }

                            $scope.enableBeaconScan();
                        }, 0);

                    }, 0)
                    //console.log($scope.lastCheckin);
                }, function(fail) {
                    console.log(fail);
                    console.log('dashboard query search failed');
                });
            }, function(fail) {
                alert("init failed: " + fail);
            });
        };





        /** WORKING WITH BEACONS **/
        /** WORKING WITH BEACONS **/
        /** WORKING WITH BEACONS **/


        $scope.onepush = false;


        $scope.enableBeaconScan = function () {
            console.log('enableBeaconScan');
            //cordova.plugins.notification.local.hasPermission(function (granted) {
            //     console.log('Permission has been granted: ' + granted);
            //});
            cordova.plugins.notification.local.registerPermission(function (granted) {
                 console.log('Permission has been granted: ' + granted);
            });


            var delegate = new cordova.plugins.locationManager.Delegate();

            delegate.didDetermineStateForRegion = function (pluginResult) {
                //console.log(pluginResult);
            };

            delegate.didStartMonitoringForRegion = function (pluginResult) {
                //console.log('didStartMonitoringForRegion:', pluginResult);
                //console.log(pluginResult.beacons.length > 0);
                //logToDom('didStartMonitoringForRegion:' + JSON.stringify(pluginResult));
            };

            delegate.didRangeBeaconsInRegion = function (pluginResult) {
                console.log('pluginResult.beacons.length > 0', pluginResult);
                if (pluginResult.beacons.length > 0) {
                    //console.log('pluginResult.beacons.length > 0');
                    $scope.deviceCheckIn = $localstorage.getObject('lastCheckIn');
                    //console.log(pluginResult.region);
                    //console.log($rootScope.backgroundMode);
                    $scope.ResData = {};
                    angular.forEach($scope.beaconsList, function (value, key) {
                        //console.log('BEACONS FOREACH');
                        //console.log(value, key);
                        //console.log('BEACONS FOREACH');
                        if(value.ibeacon_code == pluginResult.region.identifier){
                            $scope.ResData = {gym_id: key, gym_data:value};
                        }
                    });
                    //console.log("moment(moment().format('YYYY-MM-DD')).isAfter($scope.deviceCheckIn.date) ||  typeof ($scope.deviceCheckIn.date) == \"undefined\"", moment(moment().format('YYYY-MM-DD')).isAfter($scope.deviceCheckIn.date) ||  typeof ($scope.deviceCheckIn.date) == "undefined");
                    if(moment(moment().format('YYYY-MM-DD')).isAfter($scope.deviceCheckIn.date) && !$scope.onepush ||  typeof ($scope.deviceCheckIn.date) == "undefined" && !$scope.onepush){
                        $scope.onepush = true;

                        //console.log($rootScope.backgroundMode);
                        //console.log($rootScope.backgroundMode && !$scope.onepush);
                        //console.log('DATA IS OLD');
                        if($rootScope.backgroundMode) {
                            //console.log('$rootScope.backgroundMode');
                            //console.log('background mode beacon');
                            LocalNotification.sendNotification({
                                points: $scope.ResData.gym_data.point_allocation,
                                gym_name: $scope.ResData.gym_data.name,
                                gym_id: $scope.ResData.gym_id,
                                ibeacon_code: $scope.ResData.gym_data.ibeacon_code
                            });

                        }else{
                            console.log('WORKING');
                            var isIOS = ionic.Platform.isIOS();
                            console.log('IIIIIIOOOOS', isIOS);

                            LocalNotification.sendNotification({
                                points: $scope.ResData.gym_data.point_allocation,
                                gym_name: $scope.ResData.gym_data.name,
                                gym_id: $scope.ResData.gym_id,
                                ibeacon_code: $scope.ResData.gym_data.ibeacon_code
                            });


                            $scope.disableBeaconScan();
                            $scope.processChangepage = function (success){
                                console.log(success);
                                $state.go('checkin', {
                                    points: $scope.ResData.gym_data.point_allocation,
                                    gym_name: $scope.ResData.gym_data.name,
                                    gym_id: $scope.ResData.gym_id,
                                    ibeacon_code: $scope.ResData.gym_data.ibeacon_code,
                                });
                            }
                            $scope.clearGymsReatimeUpate = function (success){
                                console.log(success);
                                getGyms.removeAllCallbackEvents($scope.processChangepage, 'checkins');
                            }
                            getGyms.removeAllCallbackEvents($scope.clearGymsReatimeUpate, 'gyms');
                        }

                        $timeout(function () {
                            $scope.onepush = false;
                        },15000);
                    }
                }

                //logToDom('[DOM] didRangeBeaconsInRegion: ' + JSON.stringify(pluginResult));
            };

            cordova.plugins.locationManager.setDelegate(delegate);
            //cordova.plugins.locationManager.requestWhenInUseAuthorization();


            angular.forEach($scope.beaconsList, function (value, key) {
                //console.log('BEACON TO REGION');
                //console.log(value, key);
                //console.log('BEACON TO REGION');

                if(value.ibeacon_code != "" && value.uuid != "" && value.major != "" && value.minor != "") {
                    var beaconRegion = new cordova.plugins.locationManager.BeaconRegion(
                        value.ibeacon_code, value.uuid, value.major, value.minor);
                    // Start monitoring.
                    //console.log('Start monitoring');
                    cordova.plugins.locationManager.startMonitoringForRegion(beaconRegion)
                        .fail(console.error)
                        .done();

                    // Start ranging.
                    //console.log('Start ranging');
                    cordova.plugins.locationManager.startRangingBeaconsInRegion(beaconRegion)
                        .fail(console.error)
                        .done();
                }

            })
        };

        $scope.disableBeaconScan = function () {
            //console.log('disableBeaconScan');
            angular.forEach($scope.beaconsList, function (value, key) {
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
        };


        $scope.updateBeaconsList = function (callback) {
            //console.log('updateBeaconsList');
            var firebase = cordova.require("cordova/plugin/FireBase");
            firebase.init('momentum-checkin', function(success) {
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
                    $scope.beaconsList = success;
                    //console.log($scope.beaconsList);
                    callback();
                }, function(fail) {
                    console.log(fail);
                });
            }, function(fail) {
                alert("init failed: " + fail);
            });
        };


        $scope.EventUpdateChekins = function () {
            var firebase = cordova.require("cordova/plugin/FireBase");
            var url2 = 'https://momentum-checkin.firebaseio.com/checkins';
            firebase.readValueTypeEventWithURL(url2, function(success) {
                console.log('!$scope.runChekinUpdate', !$scope.runChekinUpdate);
                $scope.updateChkinsList();

            }, function(fail) {
                console.log('firebase.readValueTypeEventWithURL - checkins');
                console.log(fail);
            });
        }



        $ionicPlatform.ready(function () {


            document.addEventListener("deviceready", function () {
                var firebase = cordova.require("cordova/plugin/FireBase");
                firebase.init('momentum-checkin', function(success) {
                    /*  EVENT UPDATE BEACONS LIST  */
                    var url = 'https://momentum-checkin.firebaseio.com/gyms';
                    console.log(url);
                    firebase.readValueTypeEventWithURL(url, function(success) {
                        console.log('readValueTypeEventWithURL - gyms');
                        $scope.disableBeaconScan();
                        $scope.updateBeaconsList($scope.EventUpdateChekins);
                        //$timeout(function () {
                        /* EVENT UPDATE CHECKINS LIST */

                        //}, 1000);
                    }, function(fail) {
                        console.log('firebase.readValueTypeEventWithURL - gyms');
                        console.log(fail);
                    });
                }, function(fail) {
                    alert("init failed: " + fail);
                });
            });
        });

    }])