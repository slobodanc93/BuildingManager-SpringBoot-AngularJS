var kucniSavetApp = angular.module("kucniSavetApp", ['ngRoute']);

kucniSavetApp.config(['$routeProvider', function ($routeProvider) {
	$routeProvider
	.when('/', {
		templateUrl : '/app/html/home.html',
	})
	.when('/poruke', {
		templateUrl : '/app/html/poruke.html'
    }).when('/poruka/edit/:id',{
        templateUrl: '/app/html/edit-poruka.html'
    }).when('/poruka/unos-glasa/:id',{
        templateUrl: '/app/html/unos-glasa.html'
    }).otherwise({
        redirectTo: '/'
    });
}]);

kucniSavetApp.controller("homeCtrl", function($scope){
	$scope.message = "Dobro došli u aplikaciju za komunikaciju kućnih saveta!";
});

kucniSavetApp.controller("porukeCtrl", function($scope, $http, $location){
	
	/*** INICIJALIZACIJA ***/
	var baseUrlPoruke = "/api/poruke";
	var baseUrlZgrade = "api/zgrade";
	
	$scope.pageNum = 0;
	$scope.totalPages = 0;
	
	$scope.poruke = [];
	$scope.zgrade = [];

	$scope.formSearch = {};
	$scope.formSearch.zgradaId = "";
	$scope.formSearch.naslov = "";
	$scope.formSearch.tip = "";
	

	/*** FUNKCIJA ZA DOBAVLJANJE PORUKA ***/
	var getPoruke = function() {
		
		var config = {params: {}};
		config.params.pageNum = $scope.pageNum;
		
		if($scope.formSearch.zgradaId != "") {
			config.params.zgradaId = $scope.formSearch.zgradaId;
		}
		
		if($scope.formSearch.naslov != "") {
			config.params.naslov = $scope.formSearch.naslov;
		}
		
		if($scope.formSearch.tip != "") {
			config.params.tip = $scope.formSearch.tip;
		}
		
		$http.get(baseUrlPoruke, config)
			.then(
				function success(res) {
					$scope.poruke = res.data;
					$scope.totalPages = res.headers('totalPages');
				},
				function error(res) {
					alert("Neuspešno dobavljanje poruka");
				}
			);
		
	}
	
	
	/*** FUNKCIJA ZA DOBAVLJANJE ZGRADA ***/
	var getZgrade = function() {	
		$http.get(baseUrlZgrade)
			.then(
				function success(res) {
					$scope.zgrade = res.data;
				},
				function error(res) {
					alert("Neuspešno dobavljanje zgrada");
				}	
			);	
	}
	
	getZgrade();
	getPoruke();

	/*** NAVIGACIJA TABELE ***/
	$scope.nazad = function(){
        if($scope.pageNum > 0) {
            $scope.pageNum = $scope.pageNum - 1;
            getPoruke();
        }
    }

    $scope.napred = function(){
        if($scope.pageNum < $scope.totalPages - 1){
            $scope.pageNum = $scope.pageNum + 1;
            getPoruke();
        }
    }
    
	/*** PRETRAGA ***/
	$scope.search = function() {
		$scope.pageNum = 0;
		getPoruke();
	}
	$scope.resetSearch = function() {
		$scope.pageNum = 0;
		$scope.formSearch = {};
		getPoruke();
	}
	
	
	
	/*** IZMENA ***/
	$scope.edit = function(id) {
		$location.path("/poruka/edit/" + id);
	}
	
	/*** BRISANJE ***/
	$scope.delete = function(id) {
		$http.delete(baseUrlPoruke + "/" + id)
			.then(
				function success(res){
					getPoruke();
					alert("Brisanje uspešno izvršeno");
				},
				function error(res){
					alert("Došlo je do greške, brisanje nije uspešno izvršeno");
				}
			);
	}
	
	
	/*** DODAVANJE ***/
	$scope.formAdd = {};
	
	/*** DODAVANJE ***/
	$scope.add = function() {
		$http.post(baseUrlPoruke, $scope.formAdd)
			.then(
				function success(res){
					$scope.formAdd = {};
					getPoruke();
					alert("Uspešno dodata poruka");
				},
				function error(res){
	    			alert("Neuspešno dodavanje poruke");
				}		
			);
	}
	
	
	/*** UNOS GLASA ***/
	$scope.unosGlasa = function(id) {
		$location.path("/poruka/unos-glasa/" + id);
	}

});


kucniSavetApp.controller("editPorukaCtrl", function($scope, $http, $routeParams, $location){

	/*** INICIJALIZACIJA ***/
	var baseUrlPoruke = "/api/poruke";
	var baseUrlZgrade = "api/zgrade";
	
	$scope.formEdit = {};
	
	/*** FUNKCIJA ZA DOBAVLJANJE ENTITETA ZA IZMENU ***/
	var getPorukaForEdit = function() {
		$http.get(baseUrlPoruke + "/" + $routeParams.id)
	        .then(
	        	function success(res){
	        		$scope.formEdit = res.data;
	        	},
	        	function error(data){
	        		alert("Neuspešno dobavljanje poruke");
	        	}
	        );
	}
	
	
	/*** FUNKCIJA ZA DOBAVLJANJE ZGRADA ***/
	var getZgrade = function(){
		    $http.get(baseUrlZgrade)
		        .then(
		        	function success(res){
		        		$scope.zgrade = res.data;
		        		getPorukaForEdit();
		        	},
		        	function error(res){
		        		alert("Neuspešno dobavljanje zgrada");
		        	}
		        );
	}
	
	
	/*** POZIVAMO DOBAVLJANJE PODATAKA ***/
	getZgrade();
	
	
	/*** FUNKCIJA ZA IZMENU ENTITETA ***/
	$scope.izmeni = function() {
		$http.put(baseUrlPoruke + "/" + $scope.formEdit.id, $scope.formEdit)
	        .then(
	    		function success(data){
	    			alert("Uspešno izmenjena poruka");
	    			$location.path("/poruke");
	    		},
	    		function error(data){
	    			alert("Neuspešna izmena poruke");
	    		}
	        );
	}


});

 	
kucniSavetApp.controller("unosGlasaCtrl", function($scope, $http, $routeParams, $location){

	/*** INICIJALIZACIJA ***/
	var baseUrlGlasanje= "/api/poruke/glasanje";
	
	$scope.glas = {};
	
	var getPoruka = function() {
		$http.get("api/poruke/" + $routeParams.id)
	        .then(
	        	function success(res){
	        		$scope.poruka = res.data;
	        		$scope.glas.porukaId = $scope.poruka.id;
	        	},
	        	function error(data){
	        		alert("Neuspešno dobavljanje poruka");
	        	}
	        );
	}
	getPoruka();

	
	/*** DODAVANJE ***/
	$scope.za = function() {
		$scope.glas.komentar = $scope.komentar;
		$scope.glas.predlogPodrzan = "DA";
		$http.post(baseUrlGlasanje, $scope.glas)
			.then(
				function success(res){
					alert("Uspešno ste glasali za ovaj predlog");
	    			$location.path("/poruke");
				},
				function error(res){
	    			alert("Neuspešno glasanje za predlog!");
				}		
			);
	}
	
	$scope.protiv = function() {
		$scope.glas.komentar = $scope.komentar;
		$scope.glas.predlogPodrzan = "NE";
		$http.post(baseUrlGlasanje, $scope.glas)
			.then(
				function success(res){
					alert("Uspešno ste glasali protiv ovog predloga");
	    			$location.path("/poruke");
				},
				function error(res){
	    			alert("Neuspešno glasanje za predlog!");
				}		
			);
	}

});

 	