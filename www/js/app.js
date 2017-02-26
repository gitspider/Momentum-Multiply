// Ionic Starter App

// angular.module is a global place for creating, registering and retrieving Angular modules
// 'starter' is the name of this angular module example (also set in a <body> attribute in index.html)
// the 2nd parameter is an array of 'requires'
// 'starter.services' is found in services.js
// 'starter.controllers' is found in controllers.js

angular.module('starter', ['ionic', 'ngCordova', 'starter.controllers', 'starter.services', 'starter.directives'])

    .run(function($ionicPlatform, $rootScope, $localstorage, $cordovaSplashscreen, $timeout, $state, BeaconsScans) {


        $ionicPlatform.ready(function() {

            //cordova.plugins.backgroundMode.enable();
            // Hide the accessory bar by default (remove this to show the accessory bar above the keyboard
            // for form inputs)
            if (window.cordova && window.cordova.plugins && window.cordova.plugins.Keyboard) {
                //cordova.plugins.Keyboard.hideKeyboardAccessoryBar(true);
            }

            if (window.StatusBar) {
                StatusBar.styleDefault();
            }


            $rootScope.backgroundMode = false;
            document.addEventListener('pause', function() {
                // App has gone to background.
                $rootScope.backgroundMode = true;
                //console.log('mAppInBackground = true');
            });

            document.addEventListener('resume', function() {
                // App is in foreground.
                console.log('mAppInBackground = false')
                $rootScope.backgroundMode = false;
            });

        });

        $rootScope.DataNotif = {};


        $timeout(function () {
            $cordovaSplashscreen.hide();
        }, 5000);


        var showChekinPage = function () {
            $state.go('checkin', {
                points: $rootScope.DataNotif.points,
                gym_name: $rootScope.DataNotif.gym_name,
                gym_id: $rootScope.DataNotif.gym_id,
                ibeacon_code: $rootScope.DataNotif.ibeacon_code,
            });
            $rootScope.DataNotif = {};
        };


        $rootScope.$on('$cordovaLocalNotification:click', function (event, notification, state) {
            $rootScope.DataNotif = JSON.parse(notification.data);
            //console.log(Data);
            BeaconsScans.stopScan(showChekinPage);
        });


        document.addEventListener("deviceready", function () {


            var firebase = cordova.require("cordova/plugin/FireBase");
            firebase.init('momentum-checkin', function(success) {
                    firebase.setPersistenceEnabled(true, function(success) {
                        //console.log("persistenceEnabled success");
                        firebase.readData(function(success) {
                            //console.log(success);
                            //console.log('readData success');
                            //getGyms();
                            //alert("readData success: " + JSON.stringify(success));

                            $cordovaSplashscreen.hide();

                        }, function(fail) {
                            //alert("readData failed: " + fail);
                        });
                    }, function(fail) {
                        alert("persistenceEnabled failed: " + fail);
                    });
                }, function(fail) {
                    alert("init failed: " + fail);
                }
            );


            cordova.plugins.notification.local.registerPermission(function (granted) {
                // console.log('Permission has been granted: ' + granted);
            });
            cordova.plugins.locationManager.requestAlwaysAuthorization();
        }, false);


    })

    .config(function($stateProvider, $urlRouterProvider) {

        // Ionic uses AngularUI Router which uses the concept of states
        // Learn more here: https://github.com/angular-ui/ui-router
        // Set up the various states which the app can be in.
        // Each state's controller can be found in controllers.js
        $stateProvider

            // setup an abstract state for the tabs directive
            .state('login', {
                url: "/login",
                templateUrl: "templates/login.html",
                controller: "LoginCtrl"
            })
            .state('auth', {
                url: "/auth",
                templateUrl: "templates/auth.html",
                controller: "authCtrl"
            })

            .state('bluetooth', {
                url: "/bluetooth",
                templateUrl: "templates/bluetooth.html",
                controller: "BluetoothCtrl"
            })
            .state('welcome', {
                url: "/welcome",
                templateUrl: "templates/welcome.html",
                controller: "WelcomeCtrl"
            })
            .state('dashboard', {
                url: "/dashboard",
                templateUrl: "templates/dashboard.html",
                controller: "DashboardCtrl"
            })
            .state('checkin', {
                    url: "/checkin/:points/:gym_name/:gym_id/:ibeacon_code",
                templateUrl: "templates/checkin.html",
                controller: "CheckinCtrl"
            });
        // if none of the above states are matched, use this as the fallback

        $urlRouterProvider.otherwise(function($injector, $location){
            if($injector.get('AuthCheck').checkUser()){
                $location.path('/auth');
            }else {
                $location.path('/login');
            }
        });
        //$urlRouterProvider.otherwise('checkin/500/SportLife/dfsdfsfsdfs3454/evbe3');
        //$urlRouterProvider.otherwise('welcome');
        //$urlRouterProvider.otherwise('dashboard');


    });
