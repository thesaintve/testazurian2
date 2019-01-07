var app=angular.module("app",[]);

function RemoteResourceProvider() {
  this.$get=['$http', function($http) {
//    return new RemoteResource($http,"http://35.175.88.180:8080/SpringAngular03-1.0/api/vehiculo");
//    return new RemoteResource($http,"http://localhost:8080/SpringAngular03/api/vehiculo");
    return new RemoteResource($http,"http://localhost:8080/SpringAngular03-1.0/api/vehiculo");
  }];
}
app.provider("recursoRemoto",RemoteResourceProvider);

app.controller("VehiculoController", ["$scope","$http","recursoRemoto",function($scope, $http, recursoRemoto) {
  $scope.recursoRemoto=recursoRemoto;
  $scope.item= new Vehiculo();
  $scope.lstItem=[];
  $scope.seleccionado=false;

  recursoRemoto.getAll($scope);

  $scope.titulo="Crud de Vehiculo";
  

  $scope.nuevo=function(){
    $scope.item= new Vehiculo();
    $scope.seleccionado=false;
  };

  $scope.openEdit=function(ite){
    $scope.item=ite;
    $scope.seleccionado=true;
  };

  $scope.cargaLista=function(){
    $scope.recursoRemoto.getAll($scope);
  };

  $scope.guardarItem=function(){
    $scope.recursoRemoto.guardar($scope.item, $scope);
  };

  $scope.borrarItem=function(){
    $scope.recursoRemoto.borrar($scope.item.matricula, $scope);
  };

}]
);

function RemoteResource($http, baseUrl) {
  this.baseUrl=baseUrl;

  this.getAll = function($scope) {
    var config={
      method:"GET",
      url: this.baseUrl
    };
     
    var response=$http(config);
    response.success(function(data, status, headers, config) {
      $scope.lstItem=data;
    });
    response.error(function(data, status, headers, config) {
      alert("Ha fallado la petición. Estado HTTP:"+status);
    });

  };

  this.get = function(id) {
    var result;
    $http({
        method: 'GET',
        url: this.baseUrl + '/' + id
    }).success(function(data, status, headers, config) {
      result = data;
    }).error(function(data, status, headers, config) {
        throw new Error("Fallo obtener los datos:" + status + "\n" + data);
        alert("Ha fallado la petición. Estado HTTP:"+status);
      });
    return result;
  };

  this.guardar = function(item, $scope) {
  var result;
  $http({
      method: 'POST',
      url: this.baseUrl,
      data: item
  }).success(function(data, status, headers, config) {
      $scope.recursoRemoto.getAll($scope);
      $scope.nuevo();
      result = data;
  }).error(function(data, status, headers, config) {
      //throw new Error("Fallo obtener los datos:" + status + "\n" + data);
      alert("Ha fallado la petición. Estado HTTP:"+status);
    });
  return result;
  };

this.borrar = function(id, $scope) {
  var result;
  $http({
      method: 'DELETE',
      url: this.baseUrl + '/' + id
  }).success(function(data, status, headers, config) {
    $scope.recursoRemoto.getAll($scope);
    $scope.nuevo();
    result = data;
  }).error(function(data, status, headers, config) {
      //throw new Error("Fallo obtener los datos:" + status + "\n" + data);
      alert("Ha fallado la petición. Estado HTTP:"+status);
    });
  return result;
};


}

var Vehiculo = function(){
    this.matricula="";
    this.modelo="";
    this.kilometraje="";
    this.kilometrajeIni="0";
    this.alta="S";
    
};




