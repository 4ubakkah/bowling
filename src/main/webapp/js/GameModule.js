var gameModule = angular.module('gameModule', []);

gameModule.controller('newGameController', ['$rootScope', '$scope', '$http', '$location',

    function (rootScope, scope, http, location) {

        rootScope.gameId = null;
        scope.newGameData = null;

        scope.newGameOptions = {
            availableGameTypes: [
                {name: 'Single'},
                {name: 'Multiplayer'}
            ],
            selectedGameType: {name: 'Single'},
            availablePlayersCount: [
                {name: '2'},
                {name: '3'},
                {name: '4'}
            ],
            selectedPlayerCount: {name: '2'},
        };

        scope.createNewGame = function () {
            http.get("/game/create").success(function (data, status, headers, config) {
                rootScope.gameId = data.id;
                location.path('/game/' + rootScope.gameId);
            }).error(function (data, status, headers, config) {
                //location.path('/player/panel');
            });
        }
    }
]);


gameModule.controller('gamesToJoinController', ['$scope', '$http', '$location',
    function (scope, http, location) {

        scope.gamesToJoin = [];

        http.get('/game/list').success(function (data) {
            scope.gamesToJoin = data;
        }).error(function (data, status, headers, config) {
            location.path('/player/panel');
        });

        scope.joinGame = function (id) {
            http.get('/game/join/'  + id).success(function (data) {
                location.path('/game/' + data.id);
            }).error(function (data, status, headers, config) {
                location.path('/player/panel');
            });
        }

        scope.deleteGame = function (id) {
            http.get('/game/delete/'  + id).success(function (data) {
                location.path('/');
            }).error(function (data, status, headers, config) {
                location.path('/player/panel');
            });
        }
    }]);


gameModule.controller('playerGamesController', ['$scope', '$http', '$location', '$routeParams',
    function (scope, http, location, routeParams) {

        scope.playerGames = [];

        http.get('/game/player/list').success(function (data) {
            scope.playerGames = data;
        }).error(function (data, status, headers, config) {
            location.path('/player/panel');
        });

        scope.loadGame = function (id) {
            console.log(id);
            location.path('/game/' + id);
        }

        scope.deleteGame = function (id) {
            http.get('/game/delete/'  + id).success(function (data) {
                location.path('/');
            }).error(function (data, status, headers, config) {
                location.path('/player/panel');
            });
        }

    }]);


gameModule.controller('gameController', ['$rootScope', '$routeParams', '$scope', '$http',
    function (rootScope, routeParams, scope, http) {
       var gameStatus;
       getInitialData()

        function getInitialData() {

           http.get('/game/' + routeParams.id).success(function (data) {
                scope.gameProperties = data;
                gameStatus = scope.gameProperties.gameStatus;
                getMoveHistory();
            }).error(function (data, status, headers, config) {
                scope.errorMessage = "Failed do load game properties";
            });
        }

        function getMoveHistory() {
            scope.rolls = [];

          return  http.get('/move/list').success(function (data) {
                scope.rolls = data;
                scope.playerMoves = [];
            }).error(function (data, status, headers, config) {
                scope.errorMessage = "Failed to load moves in game"
            });
        }

        scope.roll = function () {
            http.get("/move/create").success(function (data, status, headers, config) {
                rootScope.gameId = data.id;
                gameStatus = data.currentGame.gameStatus;
                getMoveHistory().success(function () {
                    if (gameStatus == 'FINISHED') {
                        alert('Game is finished!');
                    }
                });
            }).error(function (data, status, headers, config) {
                //location.path('/player/panel');
            });
        }

        scope.isGameComplete = function () {
           return gameStatus == 'FINISHED';
        }
    }
]);



