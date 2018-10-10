<%-- 
    Document   : jspObsolete
    Created on : 29-set-2018, 16.12.49
    Author     : Filippo
--%>

<%@page import="model.mo.VirtualFlight"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%VirtualFlight virtualFlight = (VirtualFlight)request.getAttribute("virtualflight");%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        FlightCode: <%=virtualFlight.getFlightCode()%> <br/>
        DepartureAirport: <%=virtualFlight.getDepartureAirport().getIata()%>
        ArrivalAirport: <%=virtualFlight.getArrivalAirport().getIata()%>
        <h1>Hello World!</h1>
    </body>
</html>
