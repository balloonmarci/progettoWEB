<%@page import="model.mo.PushedFlight"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.util.List" %>
<%@page import="model.mo.Airport"%>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");    
    List<Airport> airports = (List<Airport>) request.getAttribute("airports");
    List<PushedFlight> pushedFlights = (List<PushedFlight>) request.getAttribute("pushedFlights");
    List<PushedFlight> wishlist = (List<PushedFlight>) request.getAttribute("wishlist");
    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    
    int c;
%>
<!DOCTYPE html>
<html>
    <head>
    <%@include file="/include/head.jspf"%>
    <script>
        
        function searchFlights(){
            var f;
            var c;
            
            f = document.concreteFlightsForm;
            c = f.viaggio;
            f.controllerAction.value = "ConcreteFlightManager.viewConcreteFlightsPerAirportsName";
            if(c[1].checked)
                f.return.value = true;
            else
                f.return.value = false;
            
            f.submit();
        }
        
        function goToPrenotation(flightcode, millisdeparture, millisarrival){
            
            var number = parseInt(prompt("Inserisci il numero di posti che desideri acquistare: "));
            
            while(isNaN(number)||number>6 || number <1){
                number = parseInt(prompt("Non hai inserito un numero inferiore a 6. Inserisci il numero di posti che desideri acquistare: "));
            }
            
            document.getElementById("numeroposti").value=number;
            
            
            document.getElementById("flightcode").value=flightcode;
            document.getElementById("departuredate").value=millisdeparture;
            document.getElementById("arrivaldate").value=millisarrival;
            document.searchPushedFlights.submit();
        }
        
        function deleteFromWishlist(flightcode, depmillis, arrmillis){
            if(confirm("Sei sicuro di voler eliminare il volo dalla wishlist ?")){
                document.getElementById("flightcodeDelete").value=flightcode;
                document.getElementById("departuredateDelete").value=depmillis;
                document.getElementById("arrivaldateDelete").value=arrmillis;
                document.deleteWishlistFlight.submit();
            }
        }
        
        function addToWishlist(flightcode, depmillis, arrmillis){
            if(confirm("Sei sicuro di voler aggiungere il volo alla wishlist ?")){
                document.getElementById("flightcodeAdd").value=flightcode;
                document.getElementById("departuredateAdd").value=depmillis;
                document.getElementById("arrivaldateAdd").value=arrmillis;
                document.addWishlistFlight.submit();
            }
        }
        
        function mainHomeOnLoadHandler(){
            document.concreteFlightsForm.addEventListener("submit", searchFlights);
            /*document.concreteFlightsForm.ableFlightsDate.addEventListener("click", checkDates);*/
        }
    </script>
    <link rel="stylesheet" type="text/css" href="css/modulelogin.css">
    <link rel="stylesheet" type="text/css" href="css/home.css">
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <section class="m-centrale">
        <div class="m-aereoimg">
        </div>
        <div class="main-form">
            <form name="concreteFlightsForm" action="Dispatcher" method="post" class="form-color">
            <input type="radio" id="Andata" name="viaggio">
            <label for="Andata"> Sola andata </label>
            <input type="radio" id="AndataRitorno" name="viaggio" checked>
            <label for="AndataRitorno"> Andata e ritorno </label> </br></br>

            <input type="text" name="departureAirportName" list="departureAirports" placeholder="Aeroporto di partenza" autocomplete="off" required>
            <datalist id="departureAirports">
                <select name="departureAirports">
             <%for(int i=0; i<airports.size(); i++) {%>
                    <option value= "<%=(airports != null)? airports.get(i).getAirportname() : ""%>">
             <%}%>
                </select>
            </datalist>
            <input type="text" name="arrivalAirportName" list="arrivalAirports" placeholder="Aeroporto di destinazione" autocomplete="off" required>
            <datalist id="arrivalAirports">
                <select name="arrivalAirports">
             <%for(int i=0; i<airports.size(); i++) {%>
                    <option value= "<%=(airports != null)? airports.get(i).getAirportname() : ""%>">
             <%}%>
                </select>
            </datalist>
            <input type="number" name="numeroposti" placeholder="N° passeggeri" min="1" max="6" required>

            <input type="submit" value="Cerca" class="submit-dimensioni submit-color">
            <input type="hidden" name="controllerAction"> <!--value="ConcreteFlightManager.viewConcreteFlightPerDate"-->
            <input type="hidden" name="return">
          </form>
        </div>
      </section>
      <div>
      </div>
      <main class = "m-main clearfix">
        <div class="m-content clearfix">
          <div>
                <%for(c=0; c<4; c++ ) { %>
                <article>
                      <a href="javascript:goToPrenotation('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">  
                        <h1><%=pushedFlights.get(c).getArrivalcity()%></h1>
                      <img src="images/Destinations/<%=pushedFlights.get(c).getArrivalcity()%>.png" alt="<%=pushedFlights.get(c).getArrivalcity()%>">
                      <h3 class="h3-home">
                          A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(c).getFinalprice()*100)/100%>€</strong> !!! <br>
                          <%if(pushedFlights.get(c).getDifference()>0) {%>
                            Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(c).getDifference()*100)/100%>€ se prenoti subito !
                          <%}%>
                      </h3>
                      
                      </a>
                      <% if (loggedOn) {%>
                      <a href="javascript:addToWishlist('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%> );">
                            <h3>
                                Aggiungi alla Wishlist
                            </h3> 
                            <img class="menuimg-wishlist" src="images/addToWishlist.png" alt="addToWishlist">
                      </a>
                      <%}%>      
                    </article>
              <%}%>
          </div>
          <div>
                <%for(c=4; c<8; c++ ) { %>
                    <article>
                      <a href="javascript:goToPrenotation('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">  
                        <h1><%=pushedFlights.get(c).getArrivalcity()%></h1>
                      <img src="images/Destinations/<%=pushedFlights.get(c).getArrivalcity()%>.png" alt="<%=pushedFlights.get(c).getArrivalcity()%>">
                      <h3 class="h3-home">
                          A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(c).getFinalprice()*100)/100%>€</strong> !!! <br>
                          <%if(pushedFlights.get(c).getDifference()>0) {%>
                            Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(c).getDifference()*100)/100%>€ se prenoti subito !
                          <%}%>
                      </h3>
                      
                      </a>
                      <% if (loggedOn) {%>
                      <a href="javascript:addToWishlist('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%> , <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">
                            <h3>
                                Aggiungi alla Wishlist
                            </h3> 
                            <img class="menuimg-wishlist" src="images/addToWishlist.png" alt="addToWishlist">
                      </a>
                      <%}%>      
                    </article>     
                <%}%>
          </div>
          <div>
                <%for(c=8; c<12; c++ ) { %>
                    <article>
                      <a href="javascript:goToPrenotation('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">  
                        <h1><%=pushedFlights.get(c).getArrivalcity()%></h1>
                      <img src="images/Destinations/<%=pushedFlights.get(c).getArrivalcity()%>.png" alt="<%=pushedFlights.get(c).getArrivalcity()%>">
                      <h3 class="h3-home">
                          A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(c).getFinalprice()*100)/100%>€</strong> !!! <br>
                          <%if(pushedFlights.get(c).getDifference()>0) {%>
                            Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(c).getDifference()*100)/100%>€ se prenoti subito !
                          <%}%>
                      </h3>
                      
                      </a>
                      <% if (loggedOn) {%>
                      <a href="javascript:addToWishlist('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">
                            <h3>
                                Aggiungi alla Wishlist
                            </h3> 
                            <img class="menuimg-wishlist" src="images/addToWishlist.png" alt="addToWishlist">
                      </a>
                      <%}%>      
                    </article>      
                <%}%>
          </div>
        </div>
        
        <aside class="m-sidebar">            
            <div>
                <% if(loggedOn) {%>
                
                    <%if(wishlist.size()>0) { %>
                    <h3><strong>See your Wishlist!</strong></h3>
                    <%for(int i=0; i<wishlist.size(); i++){ %>
                        
                        <article class="sidebar-article">
                            <h1 class="sidebar-h1"><%=wishlist.get(i).getArrivalcity()%></h1>
                          <img class="width-fix" src="images/Destinations/<%=wishlist.get(i).getArrivalcity()%>.png" alt="<%=wishlist.get(i).getArrivalcity()%>">
                          <h3 class="h3-home">
                              <strong class="strong-home"> A partire da <%=Math.floor(wishlist.get(i).getFinalprice()*100)/100%>€ </strong> <br>
                              <% if(wishlist.get(i).getDifference()>0) {%>
                                Il prezzo è calato di <%=Math.floor(wishlist.get(i).getDifference()*100)/100%>€ rispetto al prezzo base, affrettati a prenotare!
                                <%} else if(wishlist.get(i).getDifference()<-20){%>
                                    Attenzione ! Il prezzo sta aumentando !
                                <%}%>                              
                              <br>
                              
                              <a href="javascript:goToPrenotation('<%=wishlist.get(i).getFlightcode()%>', <%=wishlist.get(i).getDeparturedate().getMillis()%>, <%=wishlist.get(i).getArrivaldate().getMillis()%>);">
                                <img class="menuimg" src="images/ok.png" alt ="ok" >
                              </a>
                              <a href="javascript:deleteFromWishlist('<%=wishlist.get(i).getFlightcode()%>',<%=wishlist.get(i).getDeparturedate().getMillis()%>, <%=wishlist.get(i).getArrivaldate().getMillis() %>);">
                                <img class="menuimg" src="images/delete.png" alt="ok">
                              </a>
                          </h3>
                        </article>
                    <%}%>
               <%} else {%>
               <h1>
                   Aggiungi dei voli alla Wishlist per tenerli sott'occhio !
               </h1>
               <%}%>
               <%} else {%>
               <h1>
                   Effettua il login per visualizzare la tua wishlist !
               </h1>
               <%}%>
               
               
            </div>
               <%if(!loggedOn) {%>
                <%for(c=12; c<15; c++ ) { %>
                    <article>
                      <a href="javascript:goToPrenotation('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">  
                        <h1><%=pushedFlights.get(c).getArrivalcity()%></h1>
                      <img class="width-fix" src="images/Destinations/<%=pushedFlights.get(c).getArrivalcity()%>.png" alt="<%=pushedFlights.get(c).getArrivalcity()%>">
                      <h3 class="h3-home">
                          A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(c).getFinalprice()*100)/100%>€</strong> !!! <br>
                          <%if(pushedFlights.get(c).getDifference()>0) {%>
                            Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(c).getDifference()*100)/100%>€ se prenoti subito !
                          <%}%>
                      </h3>
                      
                      </a>
                      <% if (loggedOn) {%>
                      <a href="javascript:addToWishlist('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">
                            <h3>
                                Aggiungi alla Wishlist
                            </h3> 
                            <img class="menuimg-wishlist" src="images/addToWishlist.png" alt="addToWishlist">
                      </a>
                      <%}%>      
                    </article>      
                <%}%>
                <%} else { %>
                    <%if(wishlist.size()==0) {%>
                    <%for(c=12; c<15; c++ ) { %>
                    <article>
                      <a href="javascript:goToPrenotation('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%>, <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">  
                        <h1><%=pushedFlights.get(c).getArrivalcity()%></h1>
                      <img class="width-fix" src="images/Destinations/<%=pushedFlights.get(c).getArrivalcity()%>.png" alt="<%=pushedFlights.get(c).getArrivalcity()%>">
                      <h3 class="h3-home">
                          A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(c).getFinalprice()*100)/100%>€</strong> !!! <br>
                          <%if(pushedFlights.get(c).getDifference()>0) {%>
                            Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(c).getDifference()*100)/100%>€ se prenoti subito !
                          <%}%>
                      </h3>
                      
                      </a>
                      <% if (loggedOn) {%>
                      <a href="javascript:addToWishlist('<%=pushedFlights.get(c).getFlightcode()%>', <%=pushedFlights.get(c).getDeparturedate().getMillis()%> , <%=pushedFlights.get(c).getArrivaldate().getMillis()%>);">
                            <h3>
                                Aggiungi alla Wishlist
                            </h3> 
                            <img class="menuimg-wishlist" src="images/addToWishlist.png" alt="addToWishlist">
                      </a>
                      <%}%>      
                    </article>      
                    <%}%>
                    <%}%>
                <%}%>
        </aside>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
    
    <form name="searchPushedFlights" action="Dispatcher" method="post">
        <input type="hidden" name="numeroposti" id="numeroposti">
        <input type="hidden" name="flightcode" id="flightcode"> 
        <input type="hidden" name="departuredate" id="departuredate">
        <input type="hidden" name="arrivaldate" id="arrivaldate">
        <input type="hidden" name="controllerAction" value="PrenotationManager.onlyDepartureViewMillis">
    </form>
    
    <form name="deleteWishlistFlight" action="Dispatcher" method="post">
        <input type="hidden" name="flightcodeDelete" id="flightcodeDelete">
        <input type="hidden" name="departuredateDelete" id="departuredateDelete">
        <input type="hidden" name="arrivaldateDelete" id="arrivaldateDelete">
        <input type="hidden" name="controllerAction" value="HomeManager.deleteFromWishlist">
    </form>
    
    <form name="addWishlistFlight" action="Dispatcher" method="post">
        <input type="hidden" name="flightcodeAdd" id="flightcodeAdd">
        <input type="hidden" name="departuredateAdd" id="departuredateAdd">
        <input type="hidden" name="arrivaldateAdd" id="arrivaldateAdd">
        <input type="hidden" name="controllerAction" value="HomeManager.addToWishlist">
    </form>
</html>
