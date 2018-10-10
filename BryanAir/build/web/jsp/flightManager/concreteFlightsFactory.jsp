<%-- 
    Document   : concreteFlightsFactory
    Created on : 1-ott-2018, 15.34.09
    Author     : Filippo
--%>

<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="model.mo.ConcreteFlight"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%! private String getDate(DateTime dt){
        String day = (dt.dayOfMonth().get() < 10)? "0" + dt.dayOfMonth().get() : "" + dt.dayOfMonth().get();
        String month = (dt.monthOfYear().get() < 10)? "0" + dt.monthOfYear().get() : "" + dt.monthOfYear().get();
        int year = dt.year().get();
        String hour = (dt.hourOfDay().get() < 10)? "0"+dt.hourOfDay().get() : ""+dt.hourOfDay().get();
        String minutes = (dt.minuteOfHour().get() < 10)? "0"+dt.minuteOfHour().get(): ""+dt.minuteOfHour().get();
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minutes;
        
        return date;
}%>

<%List<ConcreteFlight> concreteFlights = (List<ConcreteFlight>)request.getAttribute("concreteflights");
  LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");
  String flightcode = (String)request.getAttribute("flightcode");
  String applicationMessage = (String)request.getAttribute("applicationmessage");%>
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
            
            function search(){
                b = document.newFlightForm;
                b.controllerAction.value = "";
                b.submit();
            }
            
            function insert(){
                b = document.newFlightForm;
                b.push.value = (b.pushCheckbox.checked === true)? true:false;
                b.controllerAction.value = "ConcreteFlightManager.createConcreteFlight";
                b.submit();
            }
            
            function modify(){
                b = document.newFlightForm;
                b.push.value = (b.pushCheckbox.checked === true)? true:false;
                b.controllerAction.value = "ConcreteFlightManager.modifyConcreteFlight";
                b.submit();
            }
            
            function deleteFlight(flightcode, depdate, arrdate){
                b = document.deleteFlightForm;
                b.flightcode.value = flightcode;
                b.departuredate.value = depdate;
                b.arrivaldate.value = arrdate;
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
            
            function goBack(){
                b = document.goBackForm;
                b.submit();
            }
            
            function setReadOnly(readOnly){
                document.newFlightForm.flightcode.readOnly = readOnly;
                document.newFlightForm.departureDate.readOnly = readOnly;
                document.newFlightForm.arrivalDate.readOnly = readOnly;
                document.newFlightForm.departureTime.readOnly = readOnly;
                document.newFlightForm.arrivalTime.readOnly = readOnly;
            }
            
            function setDateFormatted(date){
                y = date.getFullYear();
                mese = date.getMonth() + 1;
                m = (mese < 10)? "0" + mese : mese; 
                giorno = date.getDate();
                d = (giorno < 10)? "0" + giorno : giorno;
                return y + "-" + m + "-" + d;
            }
            
            function insertInfo(depDate, arrDate, depTime, arrTime, sFirst, sSecond, multiplier, push){
                document.newFlightForm.departureDate.value = depDate;
                document.newFlightForm.arrivalDate.value = arrDate;
                document.newFlightForm.departureTime.value = depTime;
                document.newFlightForm.arrivalTime.value = arrTime;
                document.newFlightForm.seatFirst.value = sFirst;
                document.newFlightForm.seatSecond.value = sSecond;
                document.newFlightForm.multiplier.value = multiplier;
                document.newFlightForm.pushCheckbox.checked = (push === 'true')? true : false;
            }
            
            function handleButtons(insertAndSearch, modifyAndUndo){
                document.newFlightForm.insertButton.style.display = insertAndSearch;
                document.newFlightForm.searchButton.style.display = "none";
                document.newFlightForm.modifyButton.style.display = modifyAndUndo;
                document.newFlightForm.annullaButton.style.display = modifyAndUndo;
            }
            
            function selectFlight(index, depDate, arrDate, sFirst, sSecond, multiplier, push){
                
                try{
                     flight = document.querySelector(".selectedFlight");
                     flight.classList.remove("selectedFlight");
                }catch(e){}

                flight = document.querySelector(".flight" + index);
                flight.classList.add("selectedFlight");
                insertInfo(depDate.split(" ")[0], arrDate.split(" ")[0], depDate.split(" ")[1], arrDate.split(" ")[1], sFirst, sSecond, multiplier, push);
                setReadOnly(true);
                handleButtons("none","block");
            }
            
            function hide(){
                flight = document.querySelector(".selectedFlight");
                flight.classList.remove("selectedFlight");
                insertInfo(setDateFormatted(new Date()), setDateFormatted(new Date()), "00:00", "00:00", "0", "0", "1.0", false);
                setReadOnly(false);
                handleButtons("block","none");
            }
            
            function onLoadHandler(){
                document.newFlightForm.searchButton.addEventListener("click", search);
                document.newFlightForm.insertButton.addEventListener("click", insert);
                document.newFlightForm.modifyButton.addEventListener("click", modify);
                document.newFlightForm.annullaButton.addEventListener("click", hide);
                insertInfo(setDateFormatted(new Date()), setDateFormatted(new Date()), "00:00", "00:00", "0", "0", "1.0", false);
                handleButtons("block","none");
            }
            
            window.addEventListener("load",onLoadHandler);
        </script>
    </head>
    <body>
      <header class="ontop bkColor clearfix">
        <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%>!</span>
        <a href="javascript:goBack()" class="homeclass leftarrow"><img id="leftarrow" src="images/leftarrow2.png"></a>
        <a href="javascript:logout()" class="logoutclass">Logout</a>
        <a href="javascript:goHome()" class="homeclass">Home</a>
        
      </header>
      <main class="mainclass clearfix">
        <aside class="sidebar">
          <form class="newFlightForm" name="newFlightForm" method="post" action="Dispatcher">
            <span class="error"><%=(applicationMessage != null)? applicationMessage : ""%></span>
            <label for="flightcode">Flight code: </label> 
            <input type="text" id="flightcode" name="flightcode" value="<%=(flightcode != null)? flightcode : concreteFlights.get(0).getVirtualFlight().getFlightCode() %>"
                                                                                                                                                        readOnly required/>
            <label for="departureDate">Departure date: </label>
            <section class="setInfo clearfix">
                <div>
                    <input type="date" id="departureDate" name="departureDate" required>
                </div>
                <div>
                <input type="time" id="departureTime" value="00:00" name="departureTime" required>
                </div>
            </section>
            <label for="arrivalDate">Arrival date: </label>
            <section class="setInfo clearfix">
                <div>
                    <input type="date" id="arrivalDate" name="arrivalDate" required>
                </div>
                <div>
                <input type="time" id="arrivalTime" value="00:00" name="arrivalTime" required>
                </div>
            </section>
            <section class="setInfo clearfix">
              <div>
                <label class="seat" for="seatFirst">Seat first: </label>
                <input type="number" id="seatFirst" name="seatFirst" value="0" required/>
              </div>
              <div>
                <label class="seat" for="seatSecond">Seat second: </label>
                <input type="number" id="seatSecond" name="seatSecond" value="0" required/>
              </div>
            </section>

            <section class="setInfo clearfix">
              <div>
                <label for="multiplier">Multiplier: </label>
                <input type="number" id="multiplier" name="multiplier" value="1.0" required/>
              </div>
              <div>
                <label id="pushlabel" for="pushCheckbox">Push:</label>
                <input type="hidden" name="push"/>
                <input type="checkbox" id="pushCheckbox" name="pushCheckbox"/>
              </div>
            </section>
            <section class="setInfo clearfix">
              <div>
                <input type="button" name="insertButton" value="Insert"/>
                <input type="button" name="modifyButton" value="Modify" />
              </div>
              <div>
                <input type="button" name="searchButton" value="Search"/>
                <input type="button" name="annullaButton" value="Annulla"/>
              </div>
            </section>
            <input type="hidden" name="adminId" value="<%=loggedAdmin.getId()%>"/>
            <input type="hidden" name="controllerAction"/>
          </form><br/><br/><br/><br/><br/>
        </aside>
        <section class="abstractFlightsList">
          <%for(int i=0; i < concreteFlights.size(); i++){%>
          <div class ="virtualFlight concreteflight <%="flight" + (i+1)%>">
            <img src="images/aereostil.png" alt="aereo" width="25%"/>
            <%String flightCode = concreteFlights.get(i).getVirtualFlight().getFlightCode();
              String departureDate = getDate(concreteFlights.get(i).getDate());
              String arrivalDate = getDate(concreteFlights.get(i).getArrivalDate());
              String seatFirst =  ""+concreteFlights.get(i).getSeatFirst();
              String seatSecond = ""+concreteFlights.get(i).getSeatSecond();
              String multiplier = ""+ concreteFlights.get(i).getMultiplier(); 
              String push = (concreteFlights.get(i).getPush())? "T" : "F";%>
            <a href="javascript:selectFlight('<%=(i+1)%>','<%=departureDate%>','<%=arrivalDate%>','<%=seatFirst%>','<%=seatSecond%>','<%=multiplier%>','<%=concreteFlights.get(i).getPush()%>');">
                                                                                         <%=flightCode%></a>
            <span><%=concreteFlights.get(i).getVirtualFlight().getDepartureAirport().getIata()%> to 
                <%=concreteFlights.get(i).getVirtualFlight().getArrivalAirport().getIata()%></span>
            <span><%=departureDate%></span>
            <span><%=arrivalDate%></span>
            <span>F: <%=seatFirst%> S: <%=seatSecond%></span>
            <span>M: <%=multiplier%> Push: <%=push%></span>
            <span><%=concreteFlights.get(i).getVirtualFlight().getPriceFirst()%>€-<%=concreteFlights.get(i).getVirtualFlight().getPriceSecond()%>€</span>

            <a href="javascript:deleteFlight('<%=flightCode%>',
                                             '<%=concreteFlights.get(i).getDate()%>',
                                             '<%=concreteFlights.get(i).getArrivalDate()%>');"class="delete"><img src="images/trashcan.png" width="15%"/></a>
          </div>
            <%}%>
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
      <form name="deleteFlightForm" action="Dispatcher" method="post">
        <input type="hidden" name="flightcode">
        <input type="hidden" name="departuredate">
        <input type="hidden" name="arrivaldate">
        <input type="hidden" name="controllerAction" value="ConcreteFlightManager.deleteConcreteFlight">
      </form>
    </body>
</html>
