<%-- 
    Document   : profile
    Created on : 10-ott-2018, 16.24.26
    Author     : Filippo
--%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%User user = (User)request.getAttribute("user");
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/profilostyle.css">
        <style>
        
        </style>
        <title>Sito</title>
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <main class="m-main clearfix">
        <aside class="sidebar">
          <div class="propic">
            <img src="images/me.png" alt="me">
          </div>
          <section class="utilities">
            <div>
              prenotazioni
            </div>
            <div>
              wishlist
            </div>
            <div>
              Cambia password
            </div>
            <div>
              Elimina account
            </div>
        </section>
        </aside>
        <section class="userform userform-position">
            <span class="error"><%=(applicationMessage != null)? applicationMessage:""%></span>
            <form name="uForm" action="Dispatcher" method="post" class="uform uform-position">
              <label for="username"> Username </label>
              <input type="text" id="username" name="username" maxlength="40" value="<%=user.getUsername()%>" required>
              </br></br>
              <label for="nome"> Nome </label>
              <input type="text" id="nome" name="nome" maxlength="40" value="<%=user.getFirstname()%>" required>
              </br></br>
              <label for="cognome"> Cognome </label>
              <input type="text" id="cognome" name="cognome" maxlength="40" value="<%=user.getLastname()%>" required>
              </br></br>
              <label for="email"> E-mail </label>
              <input type="email" id="email" name="email" maxlength="40" value="<%=user.getEmail()%>" required>            
              </br></br>
              <input type="submit" value="Salva" class="submit submit-dimensioni">
              <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
              <input type="hidden" name="controllerAction" value="UserManager.modify"/>
            </form>
        </section>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
</html>

