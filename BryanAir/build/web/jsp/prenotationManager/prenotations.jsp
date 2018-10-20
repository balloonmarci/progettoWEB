<%@page import="model.mo.Prenotation"%>
<%@page import="model.mo.PrenotationView"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  List<PrenotationView> prenotations = (List<PrenotationView>)request.getAttribute("prenotations");
  List<PrenotationView> checkPrenotations = (List<PrenotationView>)request.getAttribute("checkprenotations");
  int i=0;
  long l;
%>


<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <title>Bryanair</title>
        <link rel="stylesheet" type="text/css" href="css/profilostyle.css">
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
        <script language="javascript">
            function goToViewPrenotation(flightcode, departuremillis, arrivalmillis, clas, datemillis){
                
                document.getElementById("flightcode").value=flightcode;
                document.getElementById("departuredate").value=departuremillis;
                document.getElementById("arrivaldate").value=arrivalmillis;
                document.getElementById("class").value=clas;
                document.getElementById("date").value=datemillis;
                
                document.prenotationViewDetails.submit();
            }
            
            function goToCheckIn(flightcode, departuremillis, arrivalmillis, clas, datemillis){
                
                document.getElementById("flightcode").value=flightcode;
                document.getElementById("departuredate").value=departuremillis;
                document.getElementById("arrivaldate").value=arrivalmillis;
                document.getElementById("class").value=clas;
                document.getElementById("date").value=datemillis;
                
                document.getElementById("controllerAction").value="PrenotationManager.prenotationViewCheckIn";
                
                document.prenotationViewDetails.submit();
            }
            
        </script>
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <main class="support-main">
            
          
          
          <section class="support-section support-section-color support-section-font">                  
                Gestore Prenotazioni
            </section>
            <div class="wrapper">
                <div class="left-admin support-div">
                    Visualizza i dettagli delle prenotazioni effettuate:
                    <br><br>
                    <div>
                        <%for(i=0; i<prenotations.size(); i++) {%>
                            
                        <a href="javascript:goToViewPrenotation('<%=prenotations.get(i).getConcreteFlight().getVirtualFlight().getFlightCode()%>', <%=prenotations.get(i).getConcreteFlight().getDate().getMillis()%>, <%=prenotations.get(i).getConcreteFlight().getArrivalDate().getMillis()%>, <%=prenotations.get(i).getClas()%>, <%=prenotations.get(i).getPrenotationDate().getMillis()%>);">
                            
                            <article class="support-article-chat clearfix">
                                <img src="images/aereostil.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        <%=prenotations.get(i).getConcreteFlight().getVirtualFlight().getFlightCode()%>, 
                                        <%=prenotations.get(i).getPassengers()%> passeggeri
                                    </h2>
                                    <h3 class='support-h3'>
                                        <%=prenotations.get(i).getConcreteFlight().getVirtualFlight().getDepartureAirport().getAirportname()%>
                                        <img src="images/double-arrow-right.png" class="rightarrow">
                                        <%=prenotations.get(i).getConcreteFlight().getVirtualFlight().getArrivalAirport().getAirportname()%>
                                        <br>
                                        <% if(prenotations.get(i).getCheckin()==0){%>
                                        
                                            Check-In Online non ancora effettuato
                                        
                                        <%} else {%>
                                            <strong>Check-In Online effettuato</strong>
                                        <%}%>
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        Partenza il: <%=prenotations.get(i).getConcreteFlight().getDate().getDayOfMonth()%>/<%=prenotations.get(i).getConcreteFlight().getDate().getMonthOfYear()%>/<%=prenotations.get(i).getConcreteFlight().getDate().getYear()%>
                                    </h2>
                                </div>
                            </article>
                            
                            </a>
                                    
                            <%}%>
                        
                            <% if(prenotations.size()==0) {%>
                            <article class="support-article-chat clearfix">
                                <img src="images/error.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        Nessun volo prenotato!
                                    </h2>
                                    <h3 class='support-h3'>
                                        Effettua l'acquisto di un volo per visualizzarne i dettagli.
                                    </h3>
                                </div>
                            </article>
                            <%}%>
                        
                    </div>
                </div>
                <div class="right support-div">
                    Effettua il check in online delle seguenti prenotazioni per risparmiare tempo in aereoporto:
                    <br>
                    <div>
                        
                            
                            <%for(i=0; i<checkPrenotations.size(); i++) {%>
                            
                            <a href="javascript:goToCheckIn('<%=checkPrenotations.get(i).getConcreteFlight().getVirtualFlight().getFlightCode()%>', <%=checkPrenotations.get(i).getConcreteFlight().getDate().getMillis()%>, <%=checkPrenotations.get(i).getConcreteFlight().getArrivalDate().getMillis()%>, <%=checkPrenotations.get(i).getClas()%>, <%=checkPrenotations.get(i).getPrenotationDate().getMillis()%>)">
                            
                            
                            <article class="support-article-chat clearfix">
                                <img src="images/notes.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        <%=checkPrenotations.get(i).getConcreteFlight().getVirtualFlight().getFlightCode()%>, 
                                        <%=checkPrenotations.get(i).getPassengers()%> passeggeri
                                    </h2>
                                    <h3 class='support-h3'>
                                        <%=checkPrenotations.get(i).getConcreteFlight().getVirtualFlight().getDepartureAirport().getAirportname()%>
                                        <img src="images/double-arrow-right.png" class="rightarrow">
                                        <%=checkPrenotations.get(i).getConcreteFlight().getVirtualFlight().getArrivalAirport().getAirportname()%>
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        Partenza il: <%=checkPrenotations.get(i).getConcreteFlight().getDate().getDayOfMonth()%>/<%=checkPrenotations.get(i).getConcreteFlight().getDate().getMonthOfYear()%>/<%=checkPrenotations.get(i).getConcreteFlight().getDate().getYear()%>
                                    </h2>
                                </div>
                            </article>
                            
                            </a>
                                    
                        <%}%>
                            
                        <% if(checkPrenotations.size()==0) {%>
                        
                        
                        <article class="support-article-chat clearfix">
                                <img src="images/lightbulbicon.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        Non puoi effettuare il check in online di alcuna prenotazione
                                    </h2>
                                    <h3 class='support-h3'>
                                        Puoi effettuare il check in online a partire da una settimana prima della partenza.
                                    </h3>
                                </div>
                        </article>
                        
                        <%}%>
                        
                    </div>
                    
                    
                </div>    
            </div>
        </main>
                
        
        
      <form name="prenotationViewDetails" action="Dispatcher" method="post">
          <input type="hidden" name="flightcode" id="flightcode">
          <input type="hidden" name="departuredate" id ="departuredate">
          <input type="hidden" name="arrivaldate" id="arrivaldate">
          <input type="hidden" name="class" id="class">
          <input type="hidden" name="controllerAction" id="controllerAction" value="PrenotationManager.prenotationViewDetails">          
          <input type="hidden" name="date" id="date">
      </form>
      
      <%@include file="/include/footer.jspf"%>
    </body>
    
   
</html>


