<%-- 
    Document   : view
    Created on : 10-ago-2018, 10.40.22
    Author     : Marcello
--%>
<%@page import="java.util.List"%>
<%@page import="model.mo.VirtualFlight"%>
<%@page import="model.mo.ConcreteFlight"%>

<% 
    //List<VirtualFlight> virtualFlights = (List<VirtualFlight>) request.getAttribute("flights");
    List<ConcreteFlight> concreteFlights = (List<ConcreteFlight>) request.getAttribute("flights");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>view flights</title>
    </head>
    <body>
        <% for (int i = 0; i<concreteFlights.size(); i++) { %>
            FLIGHT CODE: <%= concreteFlights.get(i).getVirtualFlight().getFlightCode() %>
            PARTENZA: <%= concreteFlights.get(i).getVirtualFlight().getDepartureAirport().getCity()%>
            ARRIVO <%= concreteFlights.get(i).getVirtualFlight().getArrivalAirport().getCity()%> <br> <br>
        <%}%>
    </body>
</html>
