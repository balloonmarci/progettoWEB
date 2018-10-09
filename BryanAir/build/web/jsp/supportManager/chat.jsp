<%@page import="model.mo.Message"%>
<%@page import="model.mo.Conversation"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedUser"%>
<%@page import="java.util.List" %>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    
    Conversation conversation = (Conversation) request.getAttribute("conversation");
    List<Message> messages= (List<Message>) request.getAttribute("messages");
%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
        <script>
            function goBack(){
                document.goback.submit();
            }
            
            function sendMessage(){
                
                document.getElementById("convid").value=<%=conversation.getIdconv()%>;
                document.chatForm.submit();
            }
            
            function endConv(){
                
                document.getElementById("convidend").value=<%=conversation.getIdconv()%>;
                document.endconv.submit();
                alert("CIOSDIADIOSA");
            }
        </script>
    </head>
    <body>
        <%@include file="/include/header.jspf"%>
        <main class="support-main">
            <section class="support-section support-section-color support-section-font support-section-chat">
                <form name="goback" method="post" action="Dispatcher">
                    <input type="hidden" name="controllerAction" value="SupportManager.view">
                </form>
                <a href="javascript:goBack()" class="displayblockchat">
                    <img src="images/leftarrow.png" class="chatimage" alt="left">
                    
                </a>
                <%=conversation.getTitle()%>
                <form name="endconv" method="post" action="Dispatcher">
                    <input type="hidden" name="controllerAction" value="SupportManager.endConv">
                    <input type="hidden" name="convidend" id="convidend">
                </form>
                <a href="javascript:endConv()" class="displayblockchat">
                    <img src="images/delete.png" class="chatimage" alt="left">
                    
                </a>
                Termina Chat
                
            </section>
            
            <div class="clearfix">
                
                <%for(int i=0; i < messages.size(); i++){ %>
                    
                <% if(messages.get(i).getSender().equals("admin")){%>    
                 
                    <div class="wrapper div-chat left-chat support-div">
                        <article class="chatblock chatblock-admin">
                            <h3 class="support-h3 support-chat-name">
                                <%=conversation.getAdmin().getFirstname()%>
                            </h3>
                            <paragraph class="support-h3">
                                <%=messages.get(i).getText()%>
                            </paragraph>

                        </article>
                    </div>
                            
                <%}%>
                
                <%if(messages.get(i).getSender().equals("user")){%> 
                
                <div>
                    <div class="wrapper div-chat right-chat support-div">
                        <article class="chatblock chatblock-user">
                            <h3 class="support-h3 support-chat-name">
                                <%=conversation.getUser().getUsername()%>
                            </h3>
                            <paragraph class="support-h3">
                                <%=messages.get(i).getText()%>
                            </paragraph>

                        </article>
                    </div>
                </div>
                <%}%>                
                <%}%>
                
            </div>
            <div class="answer clearfix">
                <div class="answerbox clearfix">
                    <h2>
                        Rispondi
                    </h2>
                    <form name="chatForm" action="Dispatcher" method="post" class="c-support-form">
                        <input type="hidden" name="convid" id="convid">
                        <input type="hidden" name="controllerAction" value="SupportManager.newUserMessage">
                        <textarea name="text" id="text" required placeholder="Scrivi qui il tuo messaggio . . ." class="support-textarea answer-textarea"></textarea>
                        <input type="button" name="submitButton" value="INVIA" class="sendbutton" onclick="sendMessage();">                    
                    </form>
                </div>
            </div>
        </main>           
        <%@include file="/include/footer.jspf"%>
    </body>
</html>
