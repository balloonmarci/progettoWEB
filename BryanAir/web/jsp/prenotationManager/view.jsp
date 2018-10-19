<%-- 
    Document   : view
    Created on : 15-ott-2018, 15.37.24
    Author     : Filippo
--%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="model.mo.ConcreteFlight"%>
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

<%LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  boolean loggedOn = (boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  
  int numeroPosti = (int)request.getAttribute("numeroposti");
  ConcreteFlight departureFlight = (ConcreteFlight)request.getAttribute("departureflight");
  ConcreteFlight returnFlight = (ConcreteFlight)request.getAttribute("returnflight");
  
  boolean departureHasAvailableSeatFirst = numeroPosti <= departureFlight.getSeatFirst();
  boolean departureHasAvailableSeatSecond =  numeroPosti <= departureFlight.getSeatSecond();
  boolean returnHasAvailableSeatFirst = true,  returnHasAvailableSeatSecond = true;
  if(returnFlight != null){
    returnHasAvailableSeatFirst = numeroPosti <= returnFlight.getSeatFirst();
    returnHasAvailableSeatSecond =  numeroPosti <= returnFlight.getSeatSecond();}%>
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
        <link rel="stylesheet" type="text/css" href="css/prenotation.css">
        <script language="javascript">
            
            function setTotalPrice(pricefirst, pricesecond, first, second){
                pricefirst.style.display = first;
                pricesecond.style.display = second;
            }
            
            function setDepartureSelectedClass(classprice, classe){
                var textPrice = classprice.textContent;
                document.prenotationsForm.departureclass.value = classe;
                document.prenotationsForm.departureprice.value = textPrice.split(" ")[0];
            }
            
            function setReturnSelectedClass(classprice, classe){
                var textPrice = classprice.textContent;
                document.prenotationsForm.returnclass.value = classe;
                document.prenotationsForm.returnprice.value = textPrice.split(" ")[0];
            }
            
            function getPriceFromDepartureFirstClass(){
               <%if(departureHasAvailableSeatFirst){%>
               var prices = document.querySelectorAll(".totalprice");
               setTotalPrice(prices[0], prices[1], "block","none");
               var classes = document.querySelectorAll(".priceclass");
               setSelectedClass(classes[0], "1");
               <%}else{%>
                   alert("Non ci sono posti diponibili in prima classe.");
               <%}%>
            }
            function getPriceFromDepartureSecondClass(){
               <%if(departureHasAvailableSeatSecond){%>
               var prices = document.querySelectorAll(".totalprice");
               setTotalPrice(prices[0], prices[1], "none","block");
               var classes = document.querySelectorAll(".priceclass");
               setSelectedClass(classes[1], "2");
               <%}else{%>
                   alert("Non ci sono posti diponibili in seconda classe.");
               <%}%>
            }
            function getPriceFromReturnFirstClass(){
               <%if(returnHasAvailableSeatFirst){%>
               var prices = document.querySelectorAll(".totalprice");
               setTotalPrice(prices[2], prices[3], "block","none");
               var classes = document.querySelectorAll(".priceclass");
               setReturnSelectedClass(classes[2], "1");
               <%}else{%>
                   alert("Non ci sono posti diponibili in prima classe.");
               <%}%>
            }
            function getPriceFromReturnSecondClass(){
               <%if(returnHasAvailableSeatSecond){%>
               var prices = document.querySelectorAll(".totalprice");
               setTotalPrice(prices[2], prices[3], "none","block");
               var classes = document.querySelectorAll(".priceclass");
               setReturnSelectedClass(classes[3], "2");
               <%}else{%>
                   alert("Non ci sono posti diponibili in seconda classe.");
               <%}%>
            }
            
            function goToInsertPrenotation(){
                <%if(loggedOn){%>
                    if(document.querySelector(".jsCheck") === null)
                        document.prenotationsForm.submit();
                    else
                        alert("Inserire tutti i campi richiesti.");
                <%}else{%>
                    alert("Prima di prenotare devi effettuare il login!");
                <%}%>
            }
            
            function checkField(){
                if(event.target.value !== "")
                    event.target.classList.remove("jsCheck");
                else
                    event.target.classList.add("jsCheck");
            }
            
            function setPage(){
               var classes = document.querySelectorAll(".priceclass");
               var prices = document.querySelectorAll(".totalprice");
               <%if(!departureHasAvailableSeatFirst && departureHasAvailableSeatSecond){%>
                       
                    setDepartureSelectedClass(classes[1], "2");
                    setTotalPrice(prices[0], prices[1], "none","block");
                    
               <%}else{%>
                   
                    setTotalPrice(prices[0], prices[1], "block","none");
                    setDepartureSelectedClass(classes[0], "1");
                    
               <%}if(returnFlight != null){
                    if(!returnHasAvailableSeatFirst && returnHasAvailableSeatSecond){%>
                       setReturnSelectedClass(classes[3], "2");
                       setTotalPrice(prices[2], prices[3], "none","block");
                     <%}else{%>
                       setReturnSelectedClass(classes[2], "1");
                       setTotalPrice(prices[2], prices[3], "block","none");
                <%}
              }%>
            }
            
            function prenotationLoadHandler(){
               document.prenotationInfo.submitPrenotation.addEventListener("click",goToInsertPrenotation);
               for(i=1; i <= <%=numeroPosti%>; i++){
                   document.forms["prenotationsForm"]["passengerfirstname"+i].addEventListener("change",checkField);
                   document.forms["prenotationsForm"]["passengerlastname"+i].addEventListener("change",checkField);
                   document.forms["prenotationsForm"]["passengertitle"+i].addEventListener("change",checkField);
               }
               
               setPage();
            }
            
            window.addEventListener("load", prenotationLoadHandler);
        </script>
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <main class="clearfix">
        <section class="setflightsclasses">
            <div class="<%=(returnFlight == null)? "onlydpflight":"dpflight"%> clearfix">
              <div class="title">
                <span><b>Partenza</b></span>
              </div>
              <div class="flightclass">
                
                <a href="javascript:getPriceFromDepartureFirstClass();"><b>Prima classe</b></a>
                <span>Tariffa più bassa</span>
                <span>Posto prenotato</span>
                <span>Biglietti flessibili</span>
                <span>Bagaglio da 20kg</span>
                <span>Bagagli assicurati</span>
                <span class="priceclass"><%=Math.floor(departureFlight.getVirtualFlight().getPriceFirst() * 
                        departureFlight.getMultiplier()*100)/100%> €</span>
              </div>
              <div class="flightclass">
                <a href="javascript:getPriceFromDepartureSecondClass();"><b>Seconda classe</b></a>
                <span>Tariffa più bassa</span>
                <span>Posto prenotato</span>
                <span class="ban">Biglietti flessibili</span>
                <span class="ban">Bagaglio da 20kg</span>
                <span class="ban">Bagagli assicurati</span>
                <span class="priceclass"><%=Math.floor(departureFlight.getVirtualFlight().getPriceSecond() * 
                        departureFlight.getMultiplier()*100)/100%> €</span>
              </div>
          </div>
          <%if(returnFlight != null){%>
          <div class="dpflight clearfix">
            <div class="title">
              <span><b>Ritorno</b></span>
            </div>
            <div class="flightclass">
              <a href="javascript:getPriceFromReturnFirstClass();"><b>Prima classe</b></a>
              <span>Tariffa più bassa</span>
              <span>Posto prenotato</span>
              <span>Biglietti flessibili</span>
              <span>Bagaglio da 20kg</span>
              <span>Bagagli assicurati</span>
              <span class="priceclass"><%=Math.floor(returnFlight.getVirtualFlight().getPriceFirst() * 
                        returnFlight.getMultiplier()*100)/100%> €</span>
              </div>
            
            <div class="flightclass">
                <a href="javascript:getPriceFromReturnSecondClass();"><b>Seconda classe</b></a>
              <span>Tariffa più bassa</span>
              <span>Posto prenotato</span>
              <span class="ban">Biglietti flessibili</span>
              <span class="ban">Bagaglio da 20kg</span>
              <span class="ban">Bagagli assicurati</span>
              <span class="priceclass"><%=Math.floor(returnFlight.getVirtualFlight().getPriceSecond() * 
                        returnFlight.getMultiplier()*100)/100%> €</span>
            </div>
          </div>
            <%}%>
        </section>
        
        <aside class="flightInfo">
          <div>
            <span><b>Dettaglio prenotazione</b></span>
          </div>
          <div>
            
            <span><b><%=departureFlight.getVirtualFlight().getDepartureAirport().getAirportname()%></b> a 
                <b><%=departureFlight.getVirtualFlight().getArrivalAirport().getAirportname()%></b></span>
            <span><%=getDate(departureFlight.getDate())%> - <%=getDate(departureFlight.getArrivalDate())%> <%=departureFlight.getVirtualFlight().getFlightCode()%></span>
            <span><%=numeroPosti%> x Adulti</span>
            <form>
                <span class="totalprice">Prima classe <br/><br/> Totale: <%=new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceFirst() * 
                        departureFlight.getMultiplier() * numeroPosti)+"€"%></span>
                <span class="totalprice">Seconda classe <br/><br/> Totale: <%=new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceSecond() * 
                        departureFlight.getMultiplier() * numeroPosti)+"€"%></span>
            </form>
          </div>
          <%if(returnFlight != null){%>
          <div>
            <span><b><%=returnFlight.getVirtualFlight().getDepartureAirport().getAirportname()%></b> a 
                <b><%=returnFlight.getVirtualFlight().getArrivalAirport().getAirportname()%></b></span>
            <span><%=getDate(returnFlight.getDate())%> - <%=getDate(returnFlight.getArrivalDate())%> <%=returnFlight.getVirtualFlight().getFlightCode()%></span>
            <span><%=numeroPosti%> x Adulti</span>
            <span class="totalprice">Prima classe <br/><br/> Totale: <%=new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceFirst() * 
                        returnFlight.getMultiplier() * numeroPosti)+"€"%></span>
            <span class="totalprice">Seconda classe <br/><br/> Totale: <%=new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceSecond() * 
                        returnFlight.getMultiplier() * numeroPosti)+"€"%></span>
          </div>
           <%}%>
           <form name="prenotationInfo">
            <input type="button" name="submitPrenotation" value="Prenota"/>
           </form>
        </aside>
      <section class="passengerData">
        <div class="title">
          <span><b>Passeggeri</b></span>
        </div>
        <form name="prenotationsForm" action="Dispatcher" method="post" class="passenger">
        <%for(int i = 1; i <= numeroPosti; i++){%>
            <div class="passenger">
              <input type="text" name="passengerfirstname<%=i%>" class="jsCheck" placeholder="Inserisci nome" required />
              <input type="text" name="passengerlastname<%=i%>" class="jsCheck" placeholder="Inserisci cognome" required />
              <input type="text" name="passengertitle<%=i%>"  class="jsCheck"  list="Title" placeholder="Inserisci sesso" required />
              <datalist id="Title">
                <select name="Title">
                  <option value="Mr">
                  <option value="Mrs">
                  <option value="Altro">
                </select>
              </datalist>
           </div>
            <%}%>
            <%if(returnFlight == null){%>
            <input type="hidden" name="numeroposti" value="<%=numeroPosti%>"/>
            <input type="hidden" name="departureclass"/>
            <input type="hidden" name="departureprice"/>
            <input type="hidden" name="departureflightcode" value="<%=departureFlight.getVirtualFlight().getFlightCode()%>"/>
            <input type="hidden" name="departureflightdeparturedate" value="<%=departureFlight.getDate()%>"/>
            <input type="hidden" name="departureflightarrivaldate" value="<%=departureFlight.getArrivalDate()%>"/>
            <input type="hidden" name="controllerAction" value="PrenotationManager.createOnlyDeparturePrenotation"/>
            <%}else{%>
            <input type="hidden" name="numeroposti" value="<%=numeroPosti%>"/>
            <input type="hidden" name="departureclass"/>
            <input type="hidden" name="departureprice"/>
            <input type="hidden" name="departureflightcode" value="<%=departureFlight.getVirtualFlight().getFlightCode()%>"/>
            <input type="hidden" name="departureflightdeparturedate" value="<%=departureFlight.getDate()%>"/>
            <input type="hidden" name="departureflightarrivaldate" value="<%=departureFlight.getArrivalDate()%>"/>
            <input type="hidden" name="returnclass"/>
            <input type="hidden" name="returnprice"/>
            <input type="hidden" name="returnflightcode" value="<%=returnFlight.getVirtualFlight().getFlightCode()%>"/>
            <input type="hidden" name="returnflightdeparturedate" value="<%=returnFlight.getDate()%>"/>
            <input type="hidden" name="returnflightarrivaldate" value="<%=returnFlight.getArrivalDate()%>"/>
            <input type="hidden" name="controllerAction" value="PrenotationManager.createPrenotation"/>
            <%}%>
        </form>
    </section>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
</html>

