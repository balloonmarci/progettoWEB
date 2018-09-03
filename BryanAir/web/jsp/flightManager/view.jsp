<%-- 
    Document   : view
    Created on : 23-ago-2018, 14.28.11
    Author     : Filippo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="org.joda.time.DateTime"%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.VirtualFlight"%>
<%@page import="model.mo.ConcreteFlight"%>

<% 
    List<ConcreteFlight> concreteDeparture = (List<ConcreteFlight>) request.getAttribute("departureflights");
    List<ConcreteFlight> concreteReturn = (List<ConcreteFlight>) request.getAttribute("returnflights");
    
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    
    String applicationMessage = (String)request.getAttribute("applicationMessage");
    String message = (String)request.getAttribute("noflights");
    String action = (concreteReturn == null)? "onlydeparture":null; 
%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Sito</title>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/concreteflights.css">
        <script language="javascript">
            
            function mainConcreteFlightsLoadHandler(){
                
                var flightdays = document.getElementsByClassName("flightday");
                var departuredate = "<%=(message == null)? new Timestamp(concreteDeparture.get(0).getDate().getMillis()):""%>";
                var returndate = "<%=(message == null) && (action == null)? new Timestamp(concreteReturn.get(0).getDate().getMillis()):""%>";
                
                <%if(message == null){%>
                flightdays[0].innerHTML = "Partenza: " + getDay(departuredate) + ", " 
                                                       + dayMonth(departuredate) + " "
                                                       + getMonth(departuredate);
                                               
                flightdays[1].innerHTML = "Ritorno: " + getDay(returndate) + ", " 
                                                       + dayMonth(returndate) + " "
                                                       + getMonth(returndate);
                <%}%>
                
            }
        </script>
    </head>
    <body>
       <%@include file="/include/header.jspf"%>
        <main class="m-maincf">
            <span class="flightday flightdepday"><%=(message == null)? "": "No flights found."%> </span>
            <%if(message == null) {%>
            <section class="flights">
                <%for(int i=0; i < concreteDeparture.size(); i++) {%>
                <div class= "flight f-color clearfix">
                    <div class="flightinfo flightinfo-font clearfix">
                        <div>
                            <%String hour = concreteDeparture.get(i).getDate().getHourOfDay() + ":" 
                                          + concreteDeparture.get(i).getDate().getMinuteOfHour();
                            if(concreteDeparture.get(i).getArrivalDate().getMinuteOfHour() < 10)
                                hour = hour + "0";%>
                            <span><%=hour%></span></br>
                            <span><%=concreteDeparture.get(i).getVirtualFlight().getDepartureAirport().getAirportname()%></span>
                        </div>
                        <div>
                            <img src="images/aereostil.png" width="100%">
                            <span><%=concreteDeparture.get(i).getVirtualFlight().getFlightCode()%></span>
                        </div>
                        <div>
                            <%hour = concreteDeparture.get(i).getArrivalDate().getHourOfDay() + ":" 
                                          + concreteDeparture.get(i).getArrivalDate().getMinuteOfHour();
                              if(concreteDeparture.get(i).getArrivalDate().getMinuteOfHour() < 10)
                                hour = hour + "0";%>
                                
                            <span><%=hour%></span></br>
                            <span><%=concreteDeparture.get(i).getVirtualFlight().getArrivalAirport().getAirportname()%></span>
                        </div>
                    </div>
                    <div class="flightselect">
                        <div class="radiodeparture"><input class="radiojsdep" type="radio" name="departure" <%=(i==0)? "checked":""%>></div>
                        <div class="price">Da <%=concreteDeparture.get(i).getVirtualFlight().getPriceSecond()%>€</div>
                    </div>
                </div>
                    <%}%>
            </section>
            <%if(action == null) {%>
            <span class="flightday flightretday">Ritorno: Lunedì, 3 Settembre</span>
            <section class="flights">
                <%for(int i=0; i < concreteReturn.size(); i++) {%>
                <div class= "flight f-color clearfix">
                    <div class="flightinfo flightinfo-font clearfix">
                        <div>
                            <%String hour = concreteReturn.get(i).getDate().getHourOfDay() + ":" 
                                          + concreteReturn.get(i).getDate().getMinuteOfHour();
                            if(concreteDeparture.get(i).getArrivalDate().getMinuteOfHour() < 10)
                                hour = hour + "0";%>
                            <span><%=hour%></span></br>
                            <span><%=concreteReturn.get(i).getVirtualFlight().getDepartureAirport().getAirportname()%></span>
                        </div>
                        <div>
                            <img src="images/aereostil.png" width="100%">
                            <span><%=concreteReturn.get(i).getVirtualFlight().getFlightCode()%></span>
                        </div>
                        <div>
                            <%hour = concreteReturn.get(i).getArrivalDate().getHourOfDay() + ":" 
                                          + concreteReturn.get(i).getArrivalDate().getMinuteOfHour();
                            if(concreteDeparture.get(i).getArrivalDate().getMinuteOfHour() < 10)
                                hour = hour + "0";%>
                            <span><%=hour%></span></br>
                            <span><%=concreteReturn.get(i).getVirtualFlight().getArrivalAirport().getAirportname()%></span>
                        </div>
                    </div>
                    <div class="flightselect">
                        <div class="radioreturn"><input class="radiojsret" type="radio" name="return" <%=(i==0)? "checked":""%>></div>
                        <div class="price">Da <%=concreteReturn.get(i).getVirtualFlight().getPriceSecond()%>€</div>
                    </div>
                </div>
                    <%}%>
            </section>
            <%}%>
            <form>
                <input type="submit" value = "continua" class ="submit-dimensioni2 submit-position2">
            </form>
            <%} else {%>
                <form>
                   <input type="submit" value = "Ritorna alla home" class ="submit-dimensioni2 submit-position2">
                </form>
            <%}%>
        </main>
    </body>
</html>

