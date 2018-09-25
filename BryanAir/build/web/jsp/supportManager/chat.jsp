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
            <section class="support-section support-section-color support-section-font support-section-chat ">
                NomeChat
            </section>
        <div class="clearfix">
            <div class="wrapper div-chat left-chat support-div">
                <article class="chatblock chatblock-admin">
                    <h2 class="support-h2 support-chat-name">
                        Admin: Nome Cognome
                    </h2>
                    <paragraph>
                        Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum                        
                    </paragraph>
                        
                </article>
            </div>
            <div>
                <div class="wrapper div-chat right-chat support-div">
                    <article class="chatblock chatblock-user">
                        <h2 class="support-h2 support-chat-name">
                            Marcello Simonati
                        </h2>
                        <paragraph>
                            Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum                        
                        </paragraph>

                    </article>
                </div>
            </div>
        </div>
            <div class="answer">
                <div class="answerbox">
                    <h2>
                        Rispondi
                    </h2>
                    <form name="chatForm" action="Dispatcher" method="post" class="c-support-form">
                        <textarea name="message" id="message" placeholder="Scrivi qui il tuo messaggio . . ." class="support-textarea"></textarea>
                        <input type="button" value="SUBMIT">                            
                    </form>
                </div>
            </div>
            
          
        
        </main>
           
        <%@include file="/include/footer.jspf"%>
    </body>
</html>
