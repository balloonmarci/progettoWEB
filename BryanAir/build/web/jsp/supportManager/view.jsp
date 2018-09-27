<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.util.List" %>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");    
    String applicationMessage = (String) request.getAttribute("applicationMessage");   
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
    </head>
    <body>
        <%@include file="/include/header.jspf"%>
        <main class="support-main">
            <section class="support-section support-section-color support-section-font">                  
                Supporto Tecnico
            </section>
            <div class="wrapper">
                <div class="left support-div">
                    Inizia una nuova chat !
                    <div>
                        <form name="registerForm" action="Dispatcher" method="post" class="m-support-form">
                            <input type="text" name="title" id="title" placeholder="Titolo" class="support-text">
                            <textarea name="question" id="question" placeholder="Scrivi qui la tua domanda . . ." class="support-textarea"></textarea>
                            <input type="button" value="INVIA" class="sendbutton2">                            
                        </form>
                    </div>
                </div>
                <div class="right support-div">
                    Apri una chat esistente:
                    <div>
                        <article class="support-article-chat clearfix">
                            <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                            <div class='support-article-centraldiv'>
                                <h2 class='support-h2'>
                                Problemi nell'effettuare il checkin online
                                </h2>
                                <h3 class='support-h3'>
                                    L'admin ha risposto!
                                </h3>
                            </div>
                            
                            <div class='support-article-rightdiv'>
                                <h2 class='support-h2'>
                                    11/11/11
                                </h2>
                            </div>
                        </article>
                        
                        <article class="support-article-chat clearfix">
                            <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                            <div class='support-article-centraldiv'>
                                <h2 class='support-h2'>
                                Problemi nell'effettuare il checkin online
                                </h2>
                                <h3 class='support-h3'>
                                    L'admin ha risposto!
                                </h3>
                            </div>
                            
                            <div class='support-article-rightdiv'>
                                <h2 class='support-h2'>
                                    11/11/11
                                </h2>
                            </div>
                        </article>
                    </div>
                </div>    
            </div>
            <form name="supportForm" method="post" action="Dispatcher">
                <input type="submit" name="controllerAction" value="SupportManager.chat"/>
            </form>
            
        </main>
           
        <%@include file="/include/footer.jspf"%>
    </body>
</html>
