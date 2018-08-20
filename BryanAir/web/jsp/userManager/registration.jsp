<%-- 
    Document   : registration
    Created on : 6-ago-2018, 15.55.43
    Author     : Filippo
--%>
<%@page session="false"%>
<%@page import="model.mo.User"%>
<%String applicationMessage = (String) request.getAttribute("applicationMessage");
  User user = (User)request.getAttribute("user");%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Sito</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/headerstyle.css">
        <link rel="stylesheet" type="text/css" href="css/registermodule.css">
        <link rel="stylesheet" type="text/css" href="css/footerstyle.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">
        <script src="script/effetti.js" type="application/javascript"></script>
    </head>
    <body>
        <header>
            <div class="m-topbar m-topbar-position clearfix">
                <div class="m-utente">
                    <ul class="m-list-ul clearfix">
                        <li><a href="registrazione.html">Iscriviti</a></li>
                        <li class="m-dropdown">
                            <span>Accedi</span>
                            <div class="m-form-login m-dropdown-content m-dropdown-content-position">
                                <form name="loginForm" action="Dispatcher" method="post" class="loginform-dimensioni">
                                    <input type="text" name="username" maxlength="40" placeholder="Username" required>
                                    <input type="password" name="password" maxlength="40" placeholder="Password" required>
                                    </br></br>
                                    <input type="hidden" name="controllerAction" value="HomeManager.login"/>
                                    <input type="submit" value="Login" class="submit-dimensioni submit-color">
                                </form>
                            </div>
                        </li>
                    </ul>
                    </div>
                    <div class="m-logo">
                        <a href="home.html">BryanAir</a>
                    </div>
                    <div class="m-services">
                        <ul class="m-list-ul">
                            <li><a href="">Home</a></li>
                            <li><a href="">Prenota</a></li>
                            <li><a href="">Servizi</a></li>
                            <li><a href="">Viaggi</a></li>
                            <li><a href="">Profilo</a></li>
                        </ul>
                    </div>
                
        </header>
        <main class="m-main">
            <section class="m-section m-section-color m-section-font">
                Registrazione
            </section>
            <div class="m-reg-form m-reg-form-color">
                <form name="registerForm" action="Dispatcher" method="post" class="regform-dimensioni regform-position">
                    <span style="display: block; color:red; margin-left: 30%; margin-top:1%;"><%=(user!=null) ? applicationMessage : ""%></span></br>
                    <label for="username"> Username </label>
                    <input type="text" id="username" name="username" style="<%=(user!=null) ? "border: 1px solid red;" : ""%>" maxlength="40" required>
                    </br></br>
                    <label for="nome"> Nome </label>
                    <input type="text" id="nome" name="firstname" maxlength="40" value = "<%=(user != null) ? user.getFirstname() : ""%>" required>
                    </br></br>
                    <label for="cognome"> Cognome </label>
                    <input type="text" id="cognome" name="lastname" maxlength="40" value = "<%=(user != null) ? user.getLastname() : ""%>" required>
                    </br></br>
                    <label for="email"> E-mail </label>
                    <input type="email" id="email" name="email" maxlength="40" style="<%=(user!=null) ? "border: 1px solid red;" : ""%>" required>
                    </br></br>
                    <label for="password"> Password </label>
                    <input type="password" id="password" name="password" maxlength="40" required>
                    </br></br>
                    <input type="hidden" name="controllerAction" value="UserManager.register"/>
                    <!--<input type="hidden" name="userId" value="95356"/>-->
                    <input type="submit" value="Registrati" class="submit-dimensioni submit-position submit-color2">
                </form>
            </div>
        </main>
        <footer class="m-footer">
            &copy; copyright 2018
        </footer>
    </body>
</html>

