<%-- 
    Document   : convotest
    Created on : 30-set-2018, 18.47.23
    Author     : Marcello
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.Conversation"%>

<% 
    List<Conversation> conversations = (List<Conversation>) request.getAttribute("conversations");
    
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    
    String applicationMessage = (String)request.getAttribute("applicationMessage");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>testconvos</title>
    </head>
    <body>
        <%for(int i=0; i<conversations.size(); i++) {%>
            <%=conversations.get(i).getIdconv()%>
            <%=conversations.get(i).getTitle()%>
            <%=conversations.get(i).getUser().getUsername()%>
            <%=conversations.get(i).getStartdate()%>
            <br>
        <%}%>
                
    </body>
</html>
