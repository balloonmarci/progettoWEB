<%-- 
    Document   : viewAdmin
    Created on : 6-ott-2018, 11.34.58
    Author     : Marcello
--%>

<%@page import="model.mo.Conversation"%>
<%@page import="java.util.List"%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>

<% 
    LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedAdmin");
    boolean loggedOn = (boolean)request.getAttribute("loggedOn");
    List<Conversation> newConversations = (List<Conversation>)request.getAttribute("newConversations");
    List<Conversation> adminConversations = (List<Conversation>)request.getAttribute("adminConversations");
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
            
            function adminJoin(id){
                if(confirm("Vuoi prendere in consegna la conversazione selezionata?")){
                    document.getElementById("convidJoin").value=id;
                    document.supportFormJoin.submit();
                }                
            }
            
            function adminOpenChat(id){

                document.getElementById("convidChat").value=id;
                document.supportFormAdminChat.submit();
                                
            }
            
            function goBack(){
                document.goBackForm.submit();
            }
            
        </script>
    </head>
    <body class="adminviewbody">
        <header class="ontop bkColor clearfix">
            <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%>!</span>
            <a href="javascript:logout()" class="logoutclass">Logout</a>
        </header>
            
            <main class="support-main-admin">
            <section class="support-section support-section-color support-section-font section-padding">                  
                <a href="javascript:goBack();"> <img src="images/leftarrow.png" class="leftarrow"> </a> Supporto Tecnico
            </section>
            <div class="wrapper">
                <div class="left-admin support-div">
                    Prendi in consegna una nuova chat:
                    <% for(int i=0; i<newConversations.size(); i++) {%>
                    <div>
                        <a href="javascript:adminJoin(<%=newConversations.get(i).getIdconv()%>)">
                            <article class="support-article-chat clearfix">
                                <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        <%=newConversations.get(i).getTitle()%>
                                    </h2>
                                    <h3 class='support-h3'>
                                        <%=newConversations.get(i).getIdconv()%>
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        <%=newConversations.get(i).getStartdate().getDayOfMonth()%>/<%=newConversations.get(i).getStartdate().getMonthOfYear()%>/<%=newConversations.get(i).getStartdate().getYear()%>
                                    </h2>
                                </div>
                            </article>
                        </a>
                    </div>
                    <%}%>
                </div>
                <div class="right support-div">
                    Apri una chat che hai preso in consegna:
                    <% for(int j=0; j<adminConversations.size(); j++) {%>
                    <div>
                        <a href="javascript:adminOpenChat(<%=adminConversations.get(j).getIdconv()%>)">
                            <article class="support-article-chat clearfix">
                                <img src="images/Chat.png" alt="Chat" class="support-img-chat">
                                <div class='support-article-centraldiv'>
                                    <h2 class='support-h2'>
                                        <%=adminConversations.get(j).getTitle()%>
                                    </h2>
                                    <h3 class='support-h3'>
                                        <%=adminConversations.get(j).getIdconv()%>
                                    </h3>
                                </div>
                                <div class='support-article-rightdiv'>
                                    <h2 class='support-h2'>
                                        <%=adminConversations.get(j).getStartdate().getDayOfMonth()%>/<%=adminConversations.get(j).getStartdate().getMonthOfYear()%>/<%=adminConversations.get(j).getStartdate().getYear()%>
                                    </h2>
                                </div>
                            </article>
                        </a>
                    </div>
                    <%}%>
                    <% if(adminConversations.size()==0){ %>
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
            
        </main>
                    
        <form name="supportFormJoin" method="post" action="Dispatcher">
            <input type="hidden" name="convidJoin" id="convidJoin"/>
            <input type="hidden" name="controllerAction" value="SupportManager.adminJoinConversation"/>
        </form>

        <form name="supportFormAdminChat" method="post" action="Dispatcher">
            <input type="hidden" name="convidChat" id="convidChat"/>
            <input type="hidden" name="controllerAction" value="SupportManager.adminViewChat"/>
        </form>
        
        <form name="goBackForm" method="post" action="Dispatcher">
            <input type="hidden" name="controllerAction" value="AdminManager.viewHome">
        </form>
                    
                    
        
        
        <form name="logoutForm" action="Dispatcher" method="post">
            <input type="hidden" name="controllerAction" value="AdminManager.view">
        </form>
    </body>
</html>
