<%-- 
    Document   : view
    Created on : 10-ago-2018, 10.40.22
    Author     : Marcello
--%>
<%@page import="java.util.List"%>
<%@page import="model.mo.VirtualFlight"%>

<% 
    List<VirtualFlight> virtualFlights = (List<VirtualFlight>) request.getAttribute("flights");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>view flights</title>
    </head>
    <body>
        <% for (int i = 0; i<virtualFlights.size(); i++) { %>
            FLIGHT CODE: <%= virtualFlights.get(i).getFlightCode() %>
            PARTENZA: <%= virtualFlights.get(i).getDepartureAirport().getCity()%>
            ARRIVO <%= virtualFlights.get(i).getArrivalAirport().getCity()%> <br> <br>
        <%}%>
    </body>
</html>
