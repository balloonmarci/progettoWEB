<%-- 
    Document   : login
    Created on : 30-ago-2018, 16.10.48
    Author     : Filippo
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Admin"%>

<% boolean loggedOn = false;
   LoggedUser loggedUser = null;
   String applicationMessage = null;
   String adminApplicationMessage = (String) request.getAttribute("adminApplicationMessage");
%>
   
<!DOCTYPE html>

<html>
    <head>
        <title>Sito</title>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/registermodule.css">
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <main class="m-main">
        <section class="m-section m-section-color m-section-font">
          Admin place
        </section>
        <div class="m-reg-form m-reg-form-color">
          <form name="adminLoginForm" action="Dispatcher" method="post" class="regform-dimensioni regform-position">
            <span style="display: block; color:red; margin-left: 30%; margin-top:1%;"><%=(adminApplicationMessage!=null) ? adminApplicationMessage : ""%></span></br>
            <label for="adminFirstName"> Name </label>
            <input type="text" id="adminFirstName" name="firstname" maxlength="40" required>
            </br></br>
            <label for="adminLastName"> Surname </label>
            <input type="text" id="adminLastName" name="lastname" maxlength="40" required>
            </br></br>
            <label for="adminId"> AdminId </label>
            <input type="text" id="adminId" name="adminId" maxlength="40" required>
            </br></br>
            <label for="password"> Password </label>
            <input type="password" id="password" name="password" maxlength="40" required>
            <input type="hidden" name="controllerAction" value="AdminManager.login"/>
            <input type="submit" value="Login" class="submit submit-dimensioni submit-position submit-color2">
          </form>
        </div>
      </main>
      <footer class="m-footer">
          &copy; copyright 2018
      </footer>
  </body>
</html>

