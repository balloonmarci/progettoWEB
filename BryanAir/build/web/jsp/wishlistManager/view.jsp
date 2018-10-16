<%@page import="model.mo.PushedFlight"%>
<%@page import="model.mo.Wishlist"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.util.List" %>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    List<PushedFlight> wishlist = (List<PushedFlight>) request.getAttribute("wishlist");
    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
%>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="/include/head.jspf"%>    
    </head>
    <body style="background-color: white">
        <%@include file="/include/header.jspf"%>
        
        <div style="padding-top: 10%">   
            <%=wishlist.get(0).getFlightcode()%> <%=wishlist.get(0).getDeparturedate() %> <%=wishlist.get(0).getArrivalcity() %>
            <br>
            <%=wishlist.get(1).getFlightcode()%> <%=wishlist.get(1).getDeparturedate() %> <%=wishlist.get(1).getArrivalcity() %>
            <br>
            <%=wishlist.get(2).getFlightcode()%> <%=wishlist.get(2).getDeparturedate() %> <%=wishlist.get(2).getArrivalcity() %>
                    
        </div>


        
      
        <%@include file="/include/footer.jspf"%>
    </body>
    
    
    
</html>
