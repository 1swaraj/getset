'use strict';
/* App Controllers */


var memoryGameApp = angular.module('memoryGameApp', []);


memoryGameApp.factory('game', function() {
  var tileNames = ['lock', 'key', 'ball', 'clock', 'wallet', 'tree',
    'bird', 'star'];

  return new Game(tileNames);
});


memoryGameApp.controller('GameCtrl', function GameCtrl($scope, game) {
  $scope.game = game;
});
