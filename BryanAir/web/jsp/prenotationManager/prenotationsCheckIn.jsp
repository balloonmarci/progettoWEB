<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="org.joda.time.DateTime"%>
<%@page import="model.mo.Prenotation"%>
<%@page import="java.util.List"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  List<Prenotation> prenotations = (List<Prenotation>)request.getAttribute("prenotations");
%>

<%! private String getDate(DateTime dt){
        String day = (dt.dayOfMonth().get() < 10)? "0" + dt.dayOfMonth().get() : "" + dt.dayOfMonth().get();
        String month = (dt.monthOfYear().get() < 10)? "0" + dt.monthOfYear().get() : "" + dt.monthOfYear().get();
        int year = dt.year().get();
        String hour = (dt.hourOfDay().get() < 10)? "0"+dt.hourOfDay().get() : ""+dt.hourOfDay().get();
        String minutes = (dt.minuteOfHour().get() < 10)? "0"+dt.minuteOfHour().get(): ""+dt.minuteOfHour().get();
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minutes;
        
        return date;
}%>

<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <title>Bryanair</title>
        <link rel="stylesheet" type="text/css" href="css/profilostyle.css">
        <link rel="stylesheet" type="text/css" href="css/prenotation_checkin.css">
        <script language="javascript">
            function goBack(){
                document.prenotationView.submit();
            }
        </script>
    </head>
    <body>
        <%@include file="/include/header.jspf"%>
        <main class="prenotationmain">
            <h1 class="prenotationblocks"> 
                
                <a href="javascript:goBack()"><img src="images/leftarrow.png" class="backarrow"/></a> Visualizza Dettagli
                
            </h1>
            <h2 class="prenotationblocks">
                <%=prenotations.get(0).getConcreteFlight().getVirtualFlight().getDepartureAirport().getAirportname()%> <img src="images/double-arrow-right.png" class="rightarrow"> <%=prenotations.get(0).getConcreteFlight().getVirtualFlight().getArrivalAirport().getAirportname()%>
            </h2>
            <table class="prenotationblocks float prentable border">
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Partenza:
                        </h3>                        
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            <%=getDate(prenotations.get(0).getConcreteFlight().getDate())%>
                        </h4>
                    </th>                        
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Arrivo:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            <%=getDate(prenotations.get(0).getConcreteFlight().getArrivalDate())%>
                        </h4>
                    </th>
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Classe:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            <%=prenotations.get(0).getClas()%>°
                        </h4>
                    </th>
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Prezzo Totale:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            <%=prenotations.get(0).getPricetotal()*prenotations.size()%>€
                        </h4>
                    </th>
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Codice Volo:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            <%=prenotations.get(0).getConcreteFlight().getVirtualFlight().getFlightCode()%>
                        </h4>
                    </th>
                </tr>
            </table>
            <form action="Dispatcher" method="post" name="checkInForm">
            <table class="float prentable border">
                <tr>
                    <th>
                        <h3>
                            CheckIn Online
                        </h3>
                    </th>
                    <th>
                        
                        <input type="submit" value="Invia">
                    </th>
                </tr>
                <%for(int i=0; i<prenotations.size(); i++) {%>
                <tr>
                    <th>
                        <h4>
                            <%=prenotations.get(i).getPassengerfirstname()%> <%=prenotations.get(i).getPassengerlastname()%>:
                        </h4>
                    </th>
                    <th>
                        <h4>
                            <label for="documento">
                                Tipo documento
                            </label> 
                            <select required name="documento<%=i%>" id="documento<%=i%>">
                                <option selected="selected" value="Passaporto">Passaporto</option>
                                <option value="Carta Identita">Carta Identita</option>
                            </select>
                        </h4>
                    </th>
                    
                    <th>
                        <h4>
                            <label for="documento">
                                Codice documento
                            </label> 
                            <input required type="number" max="999999" name="documentocodice<%=i%>" id="documentocodice<%=i%>">
                        </h4>
                    </th>
                    <input type="hidden" type="text" name="prencode<%=i%>" value="<%=prenotations.get(i).getId()%>">
                </tr>                    
                <%}%>
            </table>
                <input type="hidden" name="passengers" value="<%=prenotations.size()%>">
                <input type="hidden" name="controllerAction" value="PrenotationManager.insertCheckIn">
            </form>
            
            
            
          
          
        </main>
        
        
        
        <form name="prenotationView" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" id="controllerAction" value="PrenotationManager.prenotationView">          
        </form>
      
      <%@include file="/include/footer.jspf"%>
    </body>
</html>


