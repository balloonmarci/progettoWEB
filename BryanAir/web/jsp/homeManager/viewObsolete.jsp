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
    
    String airportIata = (String) request.getAttribute("iata");
    String airportName = (String) request.getAttribute("airportName");
    String airportCity = (String) request.getAttribute("city");
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
        
        Ricerca Aeroporto <br>
        <form name="airportForm" action="Dispatcher" method="post">
            <label for="iata"> IATA CODE </label>
            <input type="text" id="iata" name="iata" maxlenght="3" required>
            <input type="hidden" name="controllerAction" value="HomeManager.findAirport"/>
            <input type="submit" value="OK">
        </form>
        
        <br>
        <% if (applicationMessage != null) { %>
        <%=applicationMessage%> <br>
        <% } %>
        
        <% if(airportIata != null){ %>
            
        
        <%=airportIata%> <%=airportName%> <%=airportCity%>
        
        
        
        <% } %>
        
        <br><br>
        
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
