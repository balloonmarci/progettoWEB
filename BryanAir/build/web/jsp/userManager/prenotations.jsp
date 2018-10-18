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
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
        <script language="javascript">
            function goToViewPrenotation(){
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
                        <a href="javascript:goToViewPrenotation()">
                            <article class="support-article-chat clearfix">
                                <img src="images/aereostil.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        ID Prenotazione: 146548
                                    </h2>
                                    <h3 class='support-h3'>
                                        Malpensa
                                        <img src="images/double-arrow-right.png" class="rightarrow">
                                        Bali
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        Partenza il: 10/11/2018
                                    </h2>
                                </div>
                            </article>
                        </a>
                        
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
                        
                    </div>
                </div>
                <div class="right support-div">
                    Effettua il check in online delle seguenti prenotazioni per risparmiare tempo in aereoporto:
                    <br>
                    <div>
                        <a href="javascript:">
                            <article class="support-article-chat clearfix">
                                <img src="images/notes.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        ID Prenotazione: 777
                                    </h2>
                                    <h3 class='support-h3'>
                                        Malpensa 
                                        <img src="images/double-arrow-right.png" class="rightarrow">
                                        San Francisco
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        Partenza il: 10/11/2018
                                    </h2>
                                </div>
                            </article>
                        </a>
                        
                        
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
                    </div>
                    
                    
                </div>    
            </div> 
            
        </main>
      
      <form name="prenotationViewDetails" action="Dispatcher" method="post">
          <input type="hidden" name="controllerAction" id="controllerAction" value="UserManager.prenotationViewDetails">          
      </form>
      
      <%@include file="/include/footer.jspf"%>
    </body>
</html>


