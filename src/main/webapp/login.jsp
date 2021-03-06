<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
        <head>
                <meta charset="utf-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1">
                <meta name="description" content="">
                <meta name="author" content="">

                <title>Login Apostas</title>

                <!-- Bootstrap Core CSS -->
                <link href="/template/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

                <!-- MetisMenu CSS -->
                <link href="/template/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

                <!-- Custom CSS -->
                <link href="/template/dist/css/sb-admin-2.css" rel="stylesheet">

                <!-- Custom Fonts -->
                <link href="/template/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

                <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
                <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
                <!--[if lt IE 9]>
                    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
                    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
                <![endif]-->
                
        </head>
        <body>
                <div class="container">
                        <div class="row">
                                <div class="col-md-12" style="text-align: center">
                                    <c:if test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}" >
                                        <div id="msg_security" class="errorHandler alert alert-danger">
                                                <i class="fa fa-remove-sign"></i> ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                                        </div>
                                    </c:if>
                                </div>
                        </div>
                        <div class="row">
                                <div class="col-md-4 col-md-offset-4">
                                        <div class="login-panel panel panel-default">
                                                <div class="panel-heading">
                                                        <h3 class="panel-title">Entre com seu Login</h3>
                                                </div>
                                                <div class="panel-body">
                                                    <form role="form" name="loginForm" action="j_spring_security_check" method="post">
                                                                <fieldset>
                                                                        <div class="form-group">
                                                                                <input id="usuarioForm" class="form-control" placeholder="Login" name="j_username" maxlength="30" type="text" autofocus/>
                                                                        </div>
                                                                        <div class="form-group">
                                                                            <input id="senhaHidden" class="form-control" placeholder="Senha" name="j_password" maxlength="30" type="password"/>
                                                                        </div>
                                                                        <input type="submit" class="btn btn-lg btn-success btn-block" value="Vai">
                                                                </fieldset>
                                                        </form>
                                                </div>
                                        </div>
                                </div>
                        </div>
                </div>

                <!-- jQuery -->
                <script src="/template/bower_components/jquery/dist/jquery.min.js"></script>

                <!-- Bootstrap Core JavaScript -->
                <script src="/template/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

                <!-- Metis Menu Plugin JavaScript -->
                <script src="/template/bower_components/metisMenu/dist/metisMenu.min.js"></script>

                <!-- Custom Theme JavaScript -->
                <script src="/template/dist/js/sb-admin-2.js"></script>
        </body>

</html>
