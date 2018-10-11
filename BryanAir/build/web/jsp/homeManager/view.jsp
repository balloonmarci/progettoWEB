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
    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
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
            
            if(c[1].checked)
                f.controllerAction.value = "ConcreteFlightManager.viewConcreteFlightPerReturnDate";
            else
                f.controllerAction.value = "ConcreteFlightManager.viewConcreteFlightPerDepartureDate";
            f.submit();
        }
        
        function mainHomeOnLoadHandler(){
            document.concreteFlightsForm.addEventListener("submit", searchFlights);
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
            <input type="text" name="arrivalAirportName" list="arrivalAirports" placeholder="Aeroporto di destinazione" autocomplete="off" required></br></br>
            <datalist id="arrivalAirports">
                <select name="arrivalAirports">
             <%for(int i=0; i<airports.size(); i++) {%>
                    <option value= "<%=(airports != null)? airports.get(i).getAirportname() : ""%>">
             <%}%>
                </select>
            </datalist>
            <input type="date" name="departuredate" required>
            <input type="date" name="returndate" required></br></br>

            <input type="submit" value="Cerca" class="submit-dimensioni submit-color">
            <input type="hidden" name="controllerAction"> <!--value="ConcreteFlightManager.viewConcreteFlightPerDate"-->

          </form>
        </div>
      </section>
      <div>
      </div>
      <main class = "m-main clearfix">
        <div class="m-content clearfix">
          <div>
                <%for(int i=0; i<pushedFlights.size(); i++ ) { %>
                <article>
                    <h1><%=pushedFlights.get(i).getArrivalcity()%></h1>
                  <img src="images/Destinations/<%=pushedFlights.get(i).getArrivalcity().concat(".png")%>" alt="<%=pushedFlights.get(i).getArrivalcity()%>">
                  <h3 class="h3-home">
                      A partire da <strong class="strong-home"><%=Math.floor(pushedFlights.get(i).getFinalprice()*100)/100%>€</strong> !!! <br>
                      <%if(pushedFlights.get(i).getDifference()>0) {%>
                        Affrettati! Risparmi fino a <%=Math.floor(pushedFlights.get(i).getDifference()*100)/100%>€ se prenoti subito !
                      <%}%>
                  </h3>
                </article>                
                <%}%>
          </div>
          <div>
            <article>
              <h1>Roma</h1>
              <img src="images/roma.png" alt="Roma">
            </article>
            <article>
              <h1>Toronto</h1>
              <img src="images/toronto.png" alt="Toronto">
            </article>
            <article>
              <h1>Caraibi</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
          </div>
          <div>
            <article>
              <h1>Mosca</h1>
              <img src="images/cremlino.png" alt="Cremlino">
            </article>
            <article>
              <h1>Luogo 8</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
            <article>
              <h1>Luogo 9</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
          </div>
        </div>
        <aside class="m-sidebar">
            <div>
                <h3>See your Wishlist!</h3>
                <article>
              <h1>Caraibi</h1>
              <img src="images/Caraibi.png" alt="Caraibi">
            </article>
            <article>
              <h1>Maldive</h1>
              <img src="images/maldive.png" alt="Maldive">
            </article>
            <article>
              <h1>New York</h1>
              <img src="images/nycity.png" alt="New York">
            </article>
            </div>
        </aside>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
</html>
