<%-- 
    Document   : home.jsp
    Created on : 31-ago-2018, 17.17.49
    Author     : Filippo
--%>
<%@page session="false"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.session.mo.LoggedAdmin"%>

<% LoggedAdmin loggedAdmin = (LoggedAdmin)request.getAttribute("loggedadmin");%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title>Sito</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="css/base.css">
        <link rel="stylesheet" type="text/css" href="css/adminheaderstyle.css">
        <link rel="stylesheet" type="text/css" href="css/adminmenu.css">
        <link rel="stylesheet" type="text/css" href="css/state.css">
        <script language="javascript">
            
            function logout(){
                b = document.logoutForm;
                b.submit();
            }
            
            function goOnCreateAbsFlightsPage(){
                c = document.createAbsFlightForm;
                c.submit();
            }
            
            function goOnAbsFlightsPage(){
                c = document.absFlightsForm;
                c.submit();
            }
            
            function goSupport(){
                document.supportForm.submit();
            }
            
            function onLoadHandler(){
                
            }            
            
            window.addEventListener("load", onLoadHandler);
            
        </script>
    </head>
    <body>
      <header class="ontop bkColor clearfix">
        <span class="title">I was waiting for you admin <%=loggedAdmin.getFirstname()%>!</span>
        <a href="javascript:logout()" class="logoutclass">Logout</a>
      </header>
      <main class="main">
        <section class="action bkColor clearfix">
          <div class="actionimage">
            <img src="images/notes.png" alt="" width="40%">
          </div>
          <div class="actioninfo">
            <p class="actiondescription">
              <span>Crea voli virtuali
              aggiornabili successivamente in voli concreti.</span>
            </p>
            <a class="actionlink" href="javascript:goOnCreateAbsFlightsPage()">Go on!</a>
          </div>
        </section>
        <section class="action bkColor clearfix">
          <div class="actionimage">
            <img src="images/notes.png" alt="" width="40%">
          </div>
          <div class="actioninfo">
            <p class="actiondescription">
              <span>Modifica, ricerca, eliminazione di voli virtuali e
                    gestione dei voli concreti associati</span>
            </p>
            <a class="actionlink" href="javascript:goOnAbsFlightsPage()">Go on!</a>
          </div>
        </section>
        <section class="action bkColor clearfix">
          <div class="actionimage">
            <img src="images/notes.png" alt="" width="40%">
          </div>
          <div class="actioninfo">
            <p class="actiondescription">
              <span>Gestione Supporto Tecnico</span>
            </p>
            <a class="actionlink" href="javascript:goSupport()">Go on!</a>
          </div>
        </section>
      </main>
      <form name="logoutForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="AdminManager.view">
      </form> 
      
      <form name="createAbsFlightForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="FlightManager.viewCreatePage">
      </form> 
      
      <form name="absFlightsForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="FlightManager.view">
      </form>
        
      <form name="supportForm" action="Dispatcher" method="post">
        <input type="hidden" name="controllerAction" value="SupportManager.adminView">
      </form>
        
    </body>
</html>
