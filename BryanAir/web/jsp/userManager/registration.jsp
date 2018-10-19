<%-- 
    Document   : registration
    Created on : 6-ago-2018, 15.55.43
    Author     : Filippo
--%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page session="false"%>
<%@page import="model.mo.User"%>
<%String applicationMessage = (String) request.getAttribute("applicationMessage");
  User user = (User)request.getAttribute("user");
  Boolean loggedOn=false;
  LoggedUser loggedUser = null;
%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/registermodule.css">
        <title>Sito</title>
    </head>
    <body>
        <%@include file="/include/header.jspf"%>
        <main class="m-main">
            <section class="m-section m-section-color m-section-font">
                Registrazione
            </section>
            <div class="m-reg-form m-reg-form-color">
                <form name="registerForm" action="Dispatcher" method="post" class="regform-dimensioni regform-position">
                    <span class="error"><%=(user!=null) ? applicationMessage : ""%></span></br>
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
                    <input type="submit" value="Registrati" class="submit-dimensioni submit-position submit-color2">
                </form>
            </div>
        </main>
        <footer class="m-footer">
            &copy; copyright 2018
        </footer>
    </body>
</html>

