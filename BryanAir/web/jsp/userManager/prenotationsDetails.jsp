<%@page import="model.session.mo.LoggedUser"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <title>Bryanair</title>
        <link rel="stylesheet" type="text/css" href="css/profilostyle.css">
        <link rel="stylesheet" type="text/css" href="css/prenotation_checkin.css">
        <script language="javascript">
            
        </script>
    </head>
    <body>
        <%@include file="/include/header.jspf"%>
        <main class="prenotationmain">
            <h1 class="prenotationblocks"> 
                <img src="images/leftarrow.png" class="backarrow"/> Visualizza Dettagli 
            </h1>
            <h2 class="prenotationblocks">
                Malpensa --> Bali
            </h2>
            <table class="prenotationblocks float prentable">
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Partenza:
                        </h3>                        
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            13/11/2018 ore 8:30
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
                            13/11/2018 ore 23:30
                        </h4>
                    </th>
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Clase:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            seconda
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
                            64€
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
                            BA020
                        </h4>
                    </th>
                </tr>
                <tr>
                    <th>
                        <h3 class="table-padding">
                            Passeggeri:
                        </h3>
                    </th>
                    <th>
                        <h4 class="tableh4 table-padding">
                            Mr. Marcello Simonati (12354354464534)<br>
                            Mr. Filippo Soncini (12354375746546)<br>
                            Mr. Conor McGregor (1235432475646)<br>
                            Mr. Marcello Simonati (12354354464534)<br>
                            Mr. Filippo Soncini (12354375746546)<br>
                            Mr. Conor McGregor (1235432475646)<br>
                            
                        </h4>
                    </th>
                </tr>
            </table>
            
            <table class="float prentable">
                <tr>
                    <th>
                        <h3>
                            CheckIn Online:
                        </h3>
                    </th>
                    <th>
                        <h4>
                            Non effettuato
                        </h4>
                    </th>
                </tr>    
                <tr>
                    <th>
                        <h3>
                            Disponibile da:
                        </h3>
                    </th>
                    <th>
                        <h4>
                            27/10/2018 ore 16:00
                        </h4>
                    </th>
                </tr>
            </table> 
            <img src="images/Destinations/Denpasar-Bali.png" class="prenimg"/>
            
            
            
            
          
          
        </main>
      
        <form name="prenotationViewDetails" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" id="controllerAction" value="UserManager.prenotationViewDetails">          
        </form>
      
      <%@include file="/include/footer.jspf"%>
    </body>
</html>


