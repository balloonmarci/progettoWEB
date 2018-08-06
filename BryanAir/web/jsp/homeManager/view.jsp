<%-- 
    Document   : view
    Created on : 3-ago-2018, 15.20.36
    Author     : Marcello
--%>

<%@page import="model.session.mo.LoggedUser"%>

<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TEST</title>
    </head>
    <body>
        <section>
        <% if(loggedOn) { %>
        Bentornato <%=loggedUser.getFirstname()%> <%=loggedUser.getLastname()%> ! <br>
        ID <%=loggedUser.getUserId()%> <br><br>
        
        Fai click qui per effettuare il logout: <br>
        <form name="logoutForm" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" value="HomeManager.logout"/>
            <input type="submit" value="LOGOUT">
        </form>
        
        
        <%} else {%>
            <% if(applicationMessage != null) {%>
            
                <%=applicationMessage%>
            
            <%}%>
            <form name="loginForm" action="Dispatcher" method="post">
                <label for="username"> Utente </label>
                <input type="text" id="username" name="username" maxlength="40" required>
                <label for="password"> Password </label>
                <input type="text" id="password" name="password" maxlength="40" required>
                <input type="hidden" name="controllerAction" value="HomeManager.login"/>
                <input type="submit" value="OK">                
            </form>        
        <%}%>            
        </section>        
    </body>
</html>
