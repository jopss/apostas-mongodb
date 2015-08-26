appMain.config(function (RestangularProvider, $routeProvider, $httpProvider, CONST) {
        
        //para expiramento de sessao por ajax dos dados.
        RestangularProvider.setErrorInterceptor(function (response, deferred, responseHandler) {
                if (response.status === 401) {
                        return abrirLogin();
                }
                return true;
        });

        //PRA INDICAR AO SERVER QUE EH UMA REQUEST DO ANGULAR/AJAX
        RestangularProvider.setDefaultHeaders({'Content-Type': 'application/json'}); //para o services
        $httpProvider.defaults.headers.common['Content-Type'] = 'application/json';
        $httpProvider.defaults.cache = false;
        
        $routeProvider.when("/dashboard/", {
                templateUrl: getContexto(CONST) + "/pages/dashboard/" +getParamCache(),
                controller: "DashboardController"
        });
        $routeProvider.when("/usuario/", {
                templateUrl: getContexto(CONST) + "/pages/usuario/" +getParamCache(),
                controller: "UsuarioController"
        });
        $routeProvider.when("/usuario/:acao", {
                templateUrl: getContexto(CONST) + "/pages/usuario/" +getParamCache(),
                controller: "UsuarioController"
        });
        $routeProvider.when("/aposta/", {
                templateUrl: getContexto(CONST) + "/pages/aposta/" +getParamCache(),
                controller: "ApostaController"
        });
        $routeProvider.when("/aposta/:id", {
                templateUrl: getContexto(CONST) + "/pages/aposta/{id}" +getParamCache(),
                controller: "ApostaController"
        });
});
