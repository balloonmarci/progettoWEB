<%-- 
    Document   : view
    Created on : 25-set-2018, 16.47.58
    Author     : Filippo
--%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="model.mo.VirtualFlight"%>
<%@page import="java.util.List"%>
<%@page import="model.mo.Airport"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashSet"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%List<Airport> airports = (List<Airport>) request.getAttribute("airports");
  List<VirtualFlight> virtualFlights = (List<VirtualFlight>)request.getAttribute("flights");
  LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
  
  Set<String> countries = new HashSet<>();
  for(int i = 0; i < airports.size(); i++)
      countries.add(airports.get(i).getCountry());%>
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
        <link rel="stylesheet" type="text/css" href="css/adminflights.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">

        <script language="javascript">
            
            function viewVirtualFlight(flightcode){
                b = document.viewVirtualForm;
                b.flightcode.value = flightcode;
                b.submit();
            }
            
            function viewConcreteFlights(flightCode){
                b = document.viewConcreteForm;
                b.flightcode.value = flightCode;
                b.submit();
            }
            
            function deleteVirtualFlight(flightCode){
                b = document.deleteFlightForm;
                b.flightcode.value = flightCode;
                b.submit();
            }
            
            function logout(){
                b = document.logoutForm;
                b.submit();
            }

            function goHome(){
                b = document.goHomeForm;
                b.submit();
            }
            
            function search(){
                b = document.virtualFlightsForm;
                a = b.orderBy.value;
                b.orderBy.value = a.split(" ")[0] + a.split(" ")[1];
                b.submit();
            }
            
            function onLoadFunction(){
                document.virtualFlightsForm.searchbutton.addEventListener("click", search);
            }
            
            window.addEventListener("load", onLoadFunction);
        </script>
    </head>
    <body>
      <header class="ontop bkColor clearfix">
        <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%> </span>
        <a href="javascript:logout()" class="logoutclass">Logout</a>
        <a href="javascript:goHome()" class="homeclass">Home</a>
      </header>
      <main class="clearfix">
        <aside class="sidebar">
          <form class="newFlightForm" name="virtualFlightsForm" action="Dispatcher" method="post">
            <label for="flightCode">Flight code: </label>
            <input type="text" id="flightCode" name="flightcode" placeholder="Enter flight code"/>
            <section class="setInfo clearfix">
                <div>
                    <label for="departure">Departure airport: </label>
                    <input type="text" id="departure" name="departureairport" list="departureAirport"
                     placeholder="Departure airport" autocomplete="off"/>
                    <datalist id="departureAirport">
                      <select name="departureAirport">
                        <%for(int i=0; i<airports.size(); i++) {%>
                            <option value= "<%=(airports != null)? airports.get(i).getAirportname() : ""%>">
                     <%}%>
                      </select>
                    </datalist>
                </div>
                <div>
                    <label for="departure">Arrival airport: </label>
                    <input type="text" id="arrival" name="arrivalairport" list="arrivalAirport"
                     placeholder="Arrival airport" autocomplete="off"/>
                    <datalist id="arrivalAirport">
                      <select name="arrivalAirport">
                        <%for(int i=0; i<airports.size(); i++) {%>
                            <option value= "<%=(airports != null)? airports.get(i).getAirportname() : ""%>">
                     <%}%>
                      </select>
                    </datalist>
                </div>
            </section>
            <label for="country">Departure country: </label>
            <input type="text" id="country" name="departurecountry" list="departureCountry"
             placeholder="Enter country" autocomplete="off"/>
            <datalist id="departureCountry">
              <select name="departureCountry">
                <%for (String country : countries) {%>
                    <option value= "<%=(countries != null)? country : ""%>">
             <%}%>
              </select>
            </datalist>

            <label for="arrCountry">Arrival country: </label>
            <input type="text" id="arrCountry" name="arrivalcountry" list="arrivalCountry"
             placeholder="Enter country" autocomplete="off"/>
            <datalist id="arrivalCountry">
              <select name="arrivalCountry">
                <%for (String country : countries) {%>
                    <option value= "<%=(countries != null)? country : ""%>">
             <%}%>
              </select>
            </datalist>

            <label for="ordina">Order by: </label>
            <section class="setInfo clearfix">
                <div>
                    <input type="text" id="ordina" name="orderBy" list="orderby"
                     placeholder="Order by" value="flight code" autocomplete="off"/>
                    <datalist id="orderby">
                      <select name="orderby">
                        <option value="price first">
                        <option value="price second">
                        <option value="flight code">    
                      </select>
                    </datalist>
                </div>
                <div>
                    <input type="text" name="direction" list="Direction" value="ASC" autocomplete="off"/>
                    <datalist id="Direction">
                      <select name="Direction">
                        <option value="ASC">
                        <option value="DESC">
                      </select>
                    </datalist>
                </div>
            </section>
            <input type="hidden" name="controllerAction" value="FlightManager.searchVirtualFlights">
            <input type="button" name="searchbutton" value="Search"/><br/>
          </form>
        </aside>
        <section class="abstractFlightsList">
          <%for(int i = 0; i < virtualFlights.size(); i++){%>
          <div class ="virtualFlight">
            <img src="images/aereostil.png" alt="aereo" width="40%"/>
            <a href="javascript:viewVirtualFlight('<%=virtualFlights.get(i).getFlightCode()%>');"><%=virtualFlights.get(i).getFlightCode()%></a>
            <span><%=virtualFlights.get(i).getDepartureAirport().getIata()%> to <%=virtualFlights.get(i).getArrivalAirport().getIata()%></span>
            <span>F: <%=virtualFlights.get(i).getPriceFirst()%>€</span>
            <span>S: <%=virtualFlights.get(i).getPriceSecond()%>€</span>
            <div class="settings clearfix">
              <a href="javascript:deleteVirtualFlight('<%=virtualFlights.get(i).getFlightCode()%>');" class="delete"><img id="trashcan"src="images/trashcan.png" width="70%"/></a>
              <a href="javascript:viewConcreteFlights('<%=virtualFlights.get(i).getFlightCode()%>');" class="delete"><img id="lightbulb" src="images/lightbulbicon.png" width="70%"/></a>
            </div>
          </div>
            <%}%>
        </section>
      </main>
      <form name="logoutForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManager.view">
      </form> 
      <form name="viewVirtualForm" action="Dispatcher" method="post">
         <input type="hidden" name="flightcode">
         <input type="hidden" name="controllerAction" value="FlightManager.viewVirtualFlight">
      </form>
      <form name="viewConcreteForm" action="Dispatcher" method="post">
         <input type="hidden" name="flightcode">
         <input type="hidden" name="controllerAction" value="ConcreteFlightManager.view">
      </form>
      <form name="goHomeForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManager.viewHome">
      </form>
      <form name="deleteFlightForm" action="Dispatcher" method="post">
        <input type="hidden" name="flightcode">
        <input type="hidden" name="controllerAction" value="FlightManager.deleteVirtualFlight">
      </form>
    </body>
</html>

