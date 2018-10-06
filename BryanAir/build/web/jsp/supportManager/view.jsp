<%@page import="model.mo.Conversation"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.util.List" %>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    List<Conversation> conversations = (List<Conversation>) request.getAttribute("conversations");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
        <script>
            function openChat(id){
                document.getElementById("convid").value=id;
                alert(document.getElementById("convid").value);
                document.supportFormChat.submit();
            }        
        </script>
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
                            <input type="text" name="title" id="title" placeholder="Titolo" class="support-text" required="">
                            <textarea name="question" id="question" placeholder="Scrivi qui la tua domanda . . ." class="support-textarea" required></textarea>
                            <input type="hidden" name="controllerAction" value="SupportManager.startConversation"/>
                            <input type="submit" value="INVIA" class="sendbutton2" >                            
                        </form>
                    </div>
                </div>
                <div class="right support-div">
                    Apri una chat esistente:
                    <div>
                        <%for(int i=0; i<conversations.size(); i++) {%>
                        <a href="javascript:openChat(<%=conversations.get(i).getIdconv()%>)">
                            <article class="support-article-chat clearfix">
                                <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        <%=conversations.get(i).getTitle()%>
                                    </h2>
                                    <h3 class='support-h3'>
                                        <%=conversations.get(i).getIdconv()%>
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        <%=conversations.get(i).getStartdate().getDayOfMonth()%>/<%=conversations.get(i).getStartdate().getMonthOfYear()%>/<%=conversations.get(i).getStartdate().getYear()%>
                                    </h2>
                                </div>
                            </article>
                        </a>
                        <%}%>
                        <%if(conversations.size()==0){%>
                        <article class="support-article-chat clearfix">
                            <img src="images/error.png" alt="Chat" class="support-img-chat">
                            <div class='support-article-centraldiv'>
                                <h2 class='support-h2'>
                                Nessuna Conversazione Trovata !!!
                                </h2>
                                <h3 class='support-h3'>
                                    Crea una nuova conversazione nel modulo a sinistra
                                </h3>
                            </div>
                            
                            <div class='support-article-rightdiv'>
                                <h2 class='support-h2'>
                                    
                                </h2>
                            </div>
                        </article>
                        <%}%>
                    </div>
                </div>    
            </div> 
            <form name="supportFormChat" method="post" action="Dispatcher">
                <input type="hidden" name="convid" id="convid"/>
                <input type="hidden" name="controllerAction" value="SupportManager.viewChat"/>
            </form>
                    
                    
            
        </main>
           
        <%@include file="/include/footer.jspf"%>
    </body>
</html>
