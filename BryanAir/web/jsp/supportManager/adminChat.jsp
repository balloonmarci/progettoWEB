<%@page import="model.mo.Message"%>
<%@page import="model.mo.Conversation"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>
<%@page import="java.util.List" %>
<%
    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedAdmin loggedAdmin = (LoggedAdmin) request.getAttribute("loggedAdmin");    
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    
    Conversation conversation = (Conversation) request.getAttribute("conversation");
    List<Message> messages= (List<Message>) request.getAttribute("messages");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/adminheaderstyle.css">
        <link rel="stylesheet" type="text/css" href="css/adminmenu.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">
        <link rel="stylesheet" type="text/css" href="css/supportmodule.css">
        <script language="javascript">
            
            function logout(){
                b = document.logoutForm;
                b.submit();
            }
            function goBack(){
                document.goBackForm.submit();
            }
            
            function sendMessage(){
                
                document.getElementById("convid").value=<%=conversation.getIdconv()%>;
                document.chatForm.submit();
            }
        </script>
    </head>
    <body>
        <header class="ontop bkColor clearfix">
            <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%>!</span>
            <a href="javascript:logout()" class="logoutclass">Logout</a>
        </header>
        <main class="support-main-admin">
            <section class="support-section support-section-color support-section-font support-section-chat section-padding">
                <a href="javascript:goBack()" class="displayblockchat">
                    <img src="images/leftarrow.png" class="chatimage" alt="left">
                </a>
                <%=conversation.getTitle()%>              
            </section>
            
            <div class="clearfix">
                
                <%for(int i=0; i < messages.size(); i++){ %>
                    
                <% if(messages.get(i).getSender().equals("user")){%>    
                 
                    <div class="wrapper div-chat left-chat support-div">
                        <article class="chatblock chatblock-admin">
                            <h3 class="support-h3 support-chat-name">
                                <%=conversation.getUser().getUsername()%>
                            </h3>
                            <paragraph class="support-h3">
                                <%=messages.get(i).getText()%>
                            </paragraph>

                        </article>
                    </div>
                            
                <%}%>
                
                <%if(messages.get(i).getSender().equals("admin")){%> 
                
                <div>
                    <div class="wrapper div-chat right-chat support-div">
                        <article class="chatblock chatblock-user">
                            <h3 class="support-h3 support-chat-name">
                                <%=conversation.getAdmin().getFirstname()%>
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
                        <input type="hidden" name="controllerAction" value="SupportManager.newAdminMessage">
                        <textarea name="text" id="text" required placeholder="Scrivi qui il tuo messaggio . . ." class="support-textarea answer-textarea"></textarea>
                        <input type="button" name="submitButton" value="INVIA" class="sendbutton" onclick="sendMessage();">                    
                    </form>
                </div>
            </div>
        </main>
        <form name="goBackForm" method="post" action="Dispatcher">
            <input type="hidden" name="controllerAction" value="SupportManager.adminView">
        </form>
                
        <form name="logoutForm" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" value="AdminManager.view">
        </form>
        <%@include file="/include/footer.jspf"%>
    </body>
</html>
