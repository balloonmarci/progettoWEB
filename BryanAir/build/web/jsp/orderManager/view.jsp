
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>

<% 
    LoggedUser loggedAdmin = (LoggedUser)request.getAttribute("loggedUser");
    boolean loggedOn = (boolean)request.getAttribute("loggedOn");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">        
    </head>
    <body class="adminviewbody">
        <%@include file="/include/header.jspf"%>            
            <main class="support-main">
            <section class="support-section support-section-color support-section-font">                  
                Ecco le tue prenotazioni
            </section>
            <div class="wrapper">
                <div class="left-admin support-div">
                    Prendi in consegna una nuova chat:
                    
                    <div>
                        <a href="javascript:">
                            <article class="support-article-chat clearfix">
                                <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        Prenotazione n° 32143214321 
                                    </h2>
                                    <h3 class='support-h3'>
                                        Roma --> Bangkok
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        321312
                                    </h2>
                                </div>
                            </article>
                        </a>
                    </div>
                </div>
                <div class="right support-div">
                    Effettua il check in online: 
                    <div>
                        <a href="javascript:">
                            <article class="support-article-chat clearfix">
                                <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        Prenotazione n° 32143214321 
                                    </h2>
                                    <h3 class='support-h3'>
                                        Roma --> Bangkok
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        321312
                                    </h2>
                                </div>
                            </article>
                        </a>
                    </div>
                    
                    
                </div>    
            </div> 
            
        </main>
                    
        <form name="supportFormJoin" method="post" action="Dispatcher">
            <input type="hidden" name="convidJoin" id="convidJoin"/>
            <input type="hidden" name="controllerAction" value="SupportManager.adminJoinConversation"/>
        </form>
        
        <form name="supportFormAdminChat" method="post" action="Dispatcher">
            <input type="hidden" name="convidChat" id="convidChat"/>
            <input type="hidden" name="controllerAction" value="SupportManager.adminViewChat"/>
        </form>
                    
                    
        
        
        <form name="logoutForm" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" value="AdminManager.view">
        </form>
    </body>
</html>
