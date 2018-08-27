<%-- 
    Document   : view
    Created on : 23-ago-2018, 14.28.11
    Author     : Filippo
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
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
    
    String applicationMessage = request.getParameter("applicationMessage");
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
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/headerstyle.css">
        <link rel="stylesheet" tyoe="text/css" href="css/concreteflights.css">
        <link rel="stylesheet" type="text/css" href="css/footerstyle.css">
        <link rel="stylesheet" type="text/css" href="css/form.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">
        <script src="script/effetti.js" type="application/javascript"></script>
        <script src="script/effettivoli.js" type="application/javascript"></script>
        <script language="javascript">
            function regUser(){
                document.regForm.submit();
            }
            
            function goHome(){
                document.homeForm.submit();
            }
            
            function goSupport(){
                if(<%=loggedOn%>){
                    document.supportForm.submit();
                } else {
                    alert("Devi effettuare il login per richiedere supporto tecnico");
                }
            }
            
            function getDay(data){
                var daysOfTheWeek = ["Domenica","Lunedì","Martedì","Mercoledì","Giovedì","Venerdì","Sabato"];
                var d = new Date(data);
                var n = d.getDay();
                
                return daysOfTheWeek[n];
            }
            
            function dayMonth(data){
                var d = new Date(data);
                return d.getDate();
            }
            
            function getMonth(data){
                var daysOfTheMonth = ["Gennaio","Febbraio","Marzo","Aprile","Maggio","Giunio","Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"];
                var d = new Date(data);
                var n =  d.getMonth();
                
                return daysOfTheMonth[n];
            }
            
            function mainLoadHandler(){
                var flightdays = document.getElementsByClassName("flightday");
                <%if(concreteDeparture.size() != 0 && concreteReturn.size() != 0) {%>
                var departuredate = "<%=new Timestamp(concreteDeparture.get(0).getDate().getMillis())%>";
                var returndate = "<%=new Timestamp(concreteReturn.get(0).getDate().getMillis())%>";
                
                flightdays[0].innerHTML = "Partenza: " + getDay(departuredate) + ", " 
                                                       + dayMonth(departuredate) + " "
                                                       + getMonth(departuredate);
                                               
                flightdays[1].innerHTML = "Ritorno: " + getDay(returndate) + ", " 
                                                       + dayMonth(returndate) + " "
                                                       + getMonth(returndate);
                <%} else{%>
                    flightdays[0].innerHTML = "No flights found";
                    flightdays[1].innerHTML = "";
                <%}%>
            }
            
            window.addEventListener("load", mainLoadHandler);
        </script>
    </head>
    <body>
        <header class="clearfix">
        <div class="m-topbar m-topbar-position clearfix">
          <div class="m-utente">              
            <ul class="m-list-ul clearfix">
                
            <% if (!loggedOn) { %>
                
              <li><a href="javascript:regUser();">Iscriviti</a></li>
              <li class="m-dropdown">
                <span>Accedi</span>
                <div class="m-form-login m-dropdown-content
                m-dropdown-content-position">
                  <form name="loginForm" action="Dispatcher" method="post" class="loginform-dimensioni">
                    <input type="text" id="username" name="username" maxlength="40" placeholder="Username" required>
                    <input type="password" id="password" name="password" maxlength="40" placeholder="Password" required>
                    </br></br>
                    <input type="hidden" name="controllerAction" value="HomeManager.login"/>
                    <input type="submit" value="Login" class="submit-dimensioni submit-color">
                  </form>
                   
                </div>
                </li>
                
                <% if(applicationMessage != null) { %>
                <li class="error">
                    <%= applicationMessage %> !
                </li>
                <% } %>
                
                <% } else { %>
                
                <li class="loggedIn">
                    Bentornato <%=loggedUser.getFirstname()%> <%=loggedUser.getLastname()%> !
                </li>
                
                <li>
                    
                    <form name="logoutForm" id="logoutForm" action="Dispatcher" method="post">
                    <input type="hidden" name="controllerAction" value="HomeManager.logout"/>
                    <input class="logout" type="submit" value="LOGOUT">
                    </form>
                    
                                     
                    
                    
                </li>
                
                
                <% } %>
                
                </ul>
              </div>           
          <div class="m-logo">
            <a href="javascript:goHome();">BryanAir</a>
          </div>
          <div class="m-services">
            <ul class="m-list-ul">
              <li><a href="javascript:goHome();">Home</a></li>
              <li><a href="">Prenota</a></li>
              <li><a href="javascript:goSupport();">Supporto</a></li>
              <li><a href="">Viaggi</a></li>
              <li><a href="">Profilo</a></li>
            </ul>
          </div>
        </div>
        <form name="regForm" method="post" action="Dispatcher">
            <input type="hidden" name="controllerAction" value="UserManager.viewReg"/>
        </form>
        <form name="homeForm" method="post" action="Dispatcher">
            <input type="hidden" name="controllerAction" value="HomeManager.view"/>
        </form>
        <form name="supportForm" method="post" action="Dispatcher">
            <input type="hidden" name="controllerAction" value="SupportManager.view"/>
        </form>
      </header>
        <main class="m-maincf">
            <span class="flightday flightdepday">Partenza: </span>
            <section class="flights">
                <%for(int i=0; i < concreteDeparture.size(); i++) {%>
                <div class= "flight f-color clearfix">
                    <div class="flightinfo flightinfo-font clearfix">
                        <div>
                            <%String hour = concreteDeparture.get(i).getDate().getHourOfDay() + ":" 
                                          + concreteDeparture.get(i).getDate().getMinuteOfHour();%>
                            <span><%=hour%></span></br>
                            <span><%=concreteDeparture.get(i).getVirtualFlight().getDepartureAirport().getAirportname()%></span>
                        </div>
                        <div>
                            <img src="images/aereostil.png" width="100%">
                            <span><%=concreteDeparture.get(i).getVirtualFlight().getFlightCode()%></span>
                        </div>
                        <div>              
                            <span>12:45</span></br>
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
            <span class="flightday flightretday">Ritorno: Lunedì, 3 Settembre</span>
            <section class="flights">
                <%for(int i=0; i < concreteReturn.size(); i++) {%>
                <div class= "flight f-color clearfix">
                    <div class="flightinfo flightinfo-font clearfix">
                        <div>
                            <%String hour = concreteReturn.get(i).getDate().getHourOfDay() + ":" 
                                          + concreteReturn.get(i).getDate().getMinuteOfHour();%>
                            <span><%=hour%></span></br>
                            <span><%=concreteReturn.get(i).getVirtualFlight().getDepartureAirport().getAirportname()%></span>
                        </div>
                        <div>
                            <img src="images/aereostil.png" width="100%">
                            <span><%=concreteReturn.get(i).getVirtualFlight().getFlightCode()%></span>
                        </div>
                        <div>
                            <span>12:15</span></br>
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
            <form>
                <input type="submit" value = "continua" class ="submit-dimensioni2 submit-position2">
            </form>
        </main>
    </body>
</html>

