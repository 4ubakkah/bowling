var bowling = angular.module('bowling', ['ngRoute','gameModule']);

bowling.config(['$routeProvider', function($routeProvider) {
    $routeProvider.

        when('/player/create', {
            templateUrl: 'templates/create.html',
            controller: 'PlayerCreateController'
        }).
        when('/game/:id', {
            templateUrl: 'templates/game-board.html',
            controller: 'gameController'
        }).
        when('/player/panel', {
            templateUrl: 'templates/player-panel.html',
            controller: 'newGameController'
        }).
        when('/about', {
            templateUrl: 'templates/about.html'
        }).
        otherwise({
            redirectTo: '/player/panel'
        });
}]);
