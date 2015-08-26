appMain.service("Login", function (Restangular, CONST) {
        this.logar = function (login) {
                var data = 'j_username=' + login.j_username + '&j_password=' + login.j_password;
                return Restangular.one(getContexto(CONST) + "j_spring_security_check")
                        .customPOST(
                                data, null, null, {'Content-Type': "application/x-www-form-urlencoded; charset=UTF-8"}
                        );
        };
        
        this.verificar = function(){
                return Restangular.one(getContexto(CONST)+ "pages/usuario/verificar/").get();
        };
});