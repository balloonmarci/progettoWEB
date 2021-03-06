<%-- 
    Document   : abstractFlightsFactory
    Created on : 2-set-2018, 23.29.11
    Author     : Filippo
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="model.mo.Airport"%>
<%@page import="model.mo.VirtualFlight"%>

<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
   List<Airport> airports = (List<Airport>)request.getAttribute("airports");
   VirtualFlight virtualFlight = (VirtualFlight)request.getAttribute("virtualFlight");
   String applicationMessage = (String)request.getAttribute("applicationMessage");
   String action=(virtualFlight != null) ? "modify" : "insert";%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Sito</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/adminheaderstyle.css">
        <link rel="stylesheet" type="text/css" href="css/createabstractflights.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">
        
        <script language="javascript">
            
            function logout(){
                b = document.logoutForm;
                b.submit();
            }
            
            function goHome(){
                b = document.goHomeForm;
                b.submit();
            }
            
            function goBack(){
                b = document.goBackForm;
                b.submit();
            }
        </script>
    </head>
    <body>
      <header class="ontop bkColor clearfix">
        <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%>!</span>
        <a href="javascript:goBack()" class="homeclass leftarrow" <%=(action.equals("modify"))? "":"hidden"%>><img src="images/leftarrow2.png" width="60%"></a>
        <a href="javascript:logout()" class="logoutclass">Logout</a>
        <a href="javascript:goHome()" class="homeclass">Home</a>
      </header>
      <main>
        <span class="insertflightTitle"><%=action.equals("modify")? "Modify":"Insert new"%> abstract flight:</span><br/>
        <section class="bkColor insertFlightData dimensioni">
          <span class="error"><%=(applicationMessage != null)? applicationMessage : ""%></span>
          <form action="Dispatcher" method="post" class="flightform">
            <input type="text" name="flightCode" value="<%=action.equals("modify")? virtualFlight.getFlightCode(): ""%>"
                   placeholder="Enter flight code" <%=(action.equals("modify"))? "readOnly": ""%>/>
            <input type="text" name="departureAirportIata" list="departureAirport"
                               value="<%=action.equals("modify")? virtualFlight.getDepartureAirport().getIata(): ""%>"
                               placeholder="Enter departure airport" <%=(action.equals("modify"))? "readOnly": ""%> autocomplete="off" required/>
            <datalist id="departureAirport">
              <select name="departureAirport">
              <%for(int i = 0; i < airports.size(); i++){%>
                <option value="<%=airports.get(i).getIata()%>">
              <%}%>
              </select>
            </datalist>

            <input type="text" name="arrivalAirportIata" list="arrivalAirport"
                               value="<%=action.equals("modify")? virtualFlight.getArrivalAirport().getIata(): ""%>" 
                               placeholder="Enter arrival airport" <%=(action.equals("modify"))? "readOnly": ""%> autocomplete="off" required/>
            <datalist id="arrivalAirport">
              <select name="arrivalAirport">
                <%for(int i = 0; i < airports.size(); i++){%>
                <option value="<%=airports.get(i).getIata()%>">
              <%}%>
              </select>
            </datalist></br></br>
            <input type="text" name="pricefirst" value = "<%=action.equals("modify")? virtualFlight.getPriceFirst() : ""%>" placeholder="Enter first class price" required/>
            <input type="text" name="pricesecond" value = "<%=action.equals("modify")? virtualFlight.getPriceSecond() : ""%>" placeholder="Enter second class price" required/>
            <input type="submit" name="insertFlight" value="<%=action.equals("modify")? "Modify":"Insert"%>!"/>
            <input type="hidden" name="controllerAction" value="<%=action.equals("modify")? "FlightManager.modifyVirtualFlight" : "FlightManager.createAbstractFlights"%>">
          </form>
        </section>
      </main>
      <form name="logoutForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManager.view">
      </form>
      <form name="goHomeForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManager.viewHome">
      </form>
      <form name="goBackForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="FlightManager.view">
      </form>
    </body>
</html>

