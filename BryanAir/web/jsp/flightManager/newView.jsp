<%-- 
    Document   : newView
    Created on : 12-ott-2018, 0.11.11
    Author     : Filippo
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="org.joda.time.DateTime"%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.VirtualFlight"%>
<%@page import="model.mo.ConcreteFlight"%>

<% LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String)request.getAttribute("applicationMessage");
    
    int numeroPosti = (int)request.getAttribute("numeroposti");
    List<ConcreteFlight> concreteDeparture = (List<ConcreteFlight>) request.getAttribute("departureflights");
    ConcreteFlight selectedDepartureFlight = (ConcreteFlight) request.getAttribute("selectedDepartureFlight");
    
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    
    boolean action = (concreteDeparture.size()== 0);
    boolean returnFlight = (boolean)request.getAttribute("return");
%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Bryanair</title>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/concreteflights.css">
        <script language="javascript">
            
            function submitDateForm(){
                document.flightDateForm.month.value = getMonthNumber();
                document.flightDateForm.submit();
            }
            
            function goOnPrenotation(){
                
                var flightdate = document.querySelector('input[name=flightcheckbox]:checked').value;
                var departureDate = flightdate.split("#")[0];
                var arrivalDate = flightdate.split("#")[1];
                
                <%if(selectedDepartureFlight != null){%>
                    document.prenotationForm.departuredate.value = departureDate;
                    document.prenotationForm.arrivaldate.value = arrivalDate;
                    document.prenotationForm.submit();
                <%}else{
                
                    if(returnFlight){%>
                        document.goReturnPageForm.departuredate.value = departureDate;
                        document.goReturnPageForm.arrivaldate.value = arrivalDate;
                        document.goReturnPageForm.submit();
                    <%}else{%>
                        document.onlyDeparturePrenotationForm.departuredate.value = departureDate;
                        document.onlyDeparturePrenotationForm.arrivaldate.value = arrivalDate;
                        document.onlyDeparturePrenotationForm.submit();
                    <%}
                  }%>
                        
            }
            
            function setAvailableMonths(dates){
                var monthsDatalist = document.querySelector("#flightMonth");
                var months = dates.split("/");
                var option = "";
                var array = new Array();

                for(i = 1; i < months.length; i++){
                    var month = getMonth(months[i]);
                    if (array.indexOf(month) === -1){ 
                        array.push(month);
                        option += '<option value="'+ month +'" />';
                    }
                }

                monthsDatalist.innerHTML = option;
            }
            
            function setPage(){
                var flightdays = document.querySelectorAll(".departureflightday");
                var flightDates = "";
                
                <%for(int i = 0; i < concreteDeparture.size(); i++) {%>
                    var departuredate = "<%=new Timestamp(concreteDeparture.get(i).getDate().getMillis())%>";
                    setDate(flightdays[<%=i%>], departuredate);
                    flightDates += "/" + departuredate;
                <%}%>
                    
                setAvailableMonths(flightDates);
            }
            
            function mainConcreteFlightsLoadHandler(){
                <%if(!action){%>
                    setPage();
                    document.flightDateForm.addEventListener("submit", submitDateForm);
                    document.querySelector("#submitbutton").addEventListener("click", goOnPrenotation);
                <%}else {%>
                    alert("Siamo spiacenti non ci sono voli disponibili. Cerca nuovi voli con Bryanair!");
                    window.location.href="/BryanAir/Dispatcher";
                <%}%>
            }
        </script>
    </head>
    <body>
       <%@include file="/include/header.jspf"%>
       <main class="m-maincf">
           
           <section class="setInfo clearfix">
               <%String flight = "";
                  try{
                    flight = concreteDeparture.get(0).getVirtualFlight().getDepartureAirport().getCity() + " to "
                         + concreteDeparture.get(0).getVirtualFlight().getArrivalAirport().getCity();%>
               <div>
                <span ><%=flight%></span> <!--class="flightday flightdepday"-->
               </div>
               <div>
                   <form name="flightDateForm" action="Dispatcher" method="post">
                       <input type="text" name="flightmonth" list="flightMonth" placeholder="Month" autocomplete="off" required>
                       <datalist id="flightMonth">
                       </datalist>
                       <input type="submit">
                       <input type="hidden" name="flightcode" value="<%=concreteDeparture.get(0).getVirtualFlight().getFlightCode()%>"/>
                       <input type="hidden" name="numeroposti" value="<%=numeroPosti%>"/>
                       <input type="hidden" name="controllerAction" value="ConcreteFlightManager.viewConcreteFlightsPerMonth"/>
                       <input type="hidden" name="month"/>
                       <input type="hidden" name="firstDepartureDate" value="<%=concreteDeparture.get(0).getDate()%>">
                       <input type="hidden" name = "return" value = "<%=returnFlight%>">
                   </form>
               </div>
                <%}catch(Exception e){
                 flight="Siamo spiacenti non ci sono voli disponibili. Cerca nuovi voli con Bryanair!";%>
                 <span ><%=flight%></span> <!--class="flightday flightdepday"-->
                <%}%>
           </section>
            <section class="flights">
                <form name="checkFlightForm">
                <%for(int i=0; i < concreteDeparture.size(); i++) {%>
                <div class= "flight f-color clearfix">
                    <span class="departureflightday"></span>
                    <div class="flightinfo flightinfo-font clearfix">
                        <div>
                            <%String hour = concreteDeparture.get(i).getDate().getHourOfDay() + ":" 
                                          + concreteDeparture.get(i).getDate().getMinuteOfHour();
                            if(concreteDeparture.get(i).getDate().getMinuteOfHour() < 10)
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
                        <div class="radiodeparture"><input name="flightcheckbox" class="radiojsdep" 
                        type="radio" value = "<%=""+concreteDeparture.get(i).getDate() + "#"+ concreteDeparture.get(i).getArrivalDate()%>" <%=(i==0)? "checked":""%>></div>

                        <div class="price">Da <%=new DecimalFormat("#.##").format(
                        concreteDeparture.get(i).getVirtualFlight().getPriceSecond() * concreteDeparture.get(i).getMultiplier())%>€</div>
                    </div>
                </div>
                    <%}%>
                </form>
            </section>
            <form name="onlyDeparturePrenotationForm" action="Dispatcher" method="post">
                <%try{
                  String flightcode=concreteDeparture.get(0).getVirtualFlight().getFlightCode();
                  %>
                <input type="hidden" name = "flightcode" value="<%=flightcode%>"/>
                <input type="hidden" name="numeroposti" value="<%=numeroPosti%>"/>
                <input type="hidden" name = "controllerAction" value = "PrenotationManager.onlyDepartureView"/>
                <input type="hidden" name = "departuredate" />
                <input type="hidden" name = "arrivaldate"/>
                <%}catch(Exception e){}%>
              </form>
              
              <form name="goReturnPageForm" action="Dispatcher" method="post">
                <%try{
                  String flightcode=concreteDeparture.get(0).getVirtualFlight().getFlightCode();
                  String departureAirportName=concreteDeparture.get(0).getVirtualFlight().getArrivalAirport().getAirportname();
                  String arrivalAirportName = concreteDeparture.get(0).getVirtualFlight().getDepartureAirport().getAirportname();
                  %>
                <input type="hidden" name = "flightcode" value = "<%=flightcode%>"/>
                <input type="hidden" name = "departureAirportName" value = "<%=departureAirportName%>"/>
                <input type="hidden" name = "arrivalAirportName" value = "<%=arrivalAirportName%>"/>
                <input type="hidden" name="numeroposti" value="<%=numeroPosti%>"/>
                <input type="hidden" name = "controllerAction" value = "ConcreteFlightManager.viewReturnConcreteFlightsPerAirportsName"/>
                <input type="hidden" name = "departuredate" />
                <input type="hidden" name = "arrivaldate"/>
                <%}catch(Exception e){}%>
                
              </form>

              <form name="prenotationForm" action="Dispatcher" method="post">
                <%try{
                  String departureflightcode=selectedDepartureFlight.getVirtualFlight().getFlightCode();
                  DateTime departureflightdeparturedate=selectedDepartureFlight.getDate();
                  DateTime departureflightarrivaldate = selectedDepartureFlight.getArrivalDate();
                  String returnflightcode=concreteDeparture.get(0).getVirtualFlight().getFlightCode();
                  %>
                <input type="hidden" name = "departureflightcode" value = "<%=departureflightcode%>"/>
                <input type="hidden" name = "departureflightdeparturedate" value="<%=departureflightdeparturedate%>"/>
                <input type="hidden" name = "departureflightarrivaldate" value="<%=departureflightarrivaldate%>"/>
                <input type="hidden" name = "returnflightcode" value="<%=returnflightcode%>"/>
                <input type="hidden" name=  "numeroposti" value="<%=numeroPosti%>"/>
                <input type="hidden" name = "controllerAction" value = "PrenotationManager.view"/>
                <input type="hidden" name = "departuredate" />
                <input type="hidden" name = "arrivaldate"/>
                <%}catch(Exception e){}%>
              </form>
                <input type="button" id="submitbutton" name = "prenota" value = "Continua" class ="submit-dimensioni2 submit-position2"/>
        </main>
    </body>
</html>


