<%-- 
    Document   : profile
    Created on : 10-ott-2018, 16.24.26
    Author     : Filippo
--%>

<%@page import="model.session.mo.LoggedUser"%>
<%@page import="model.mo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%User user = (User)request.getAttribute("user");
  LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  Boolean loggedOn = (Boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  String actionPage = (String)request.getAttribute("actionPage");
  
  String thisform = null;
  if(actionPage.equals("account")) thisform = "'block','none','none'";
  if(actionPage.equals("setpassword")) thisform = "'none','block','none'";
  if(actionPage.equals("delete")) thisform = "'none','none','block'";%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <%@include file="/include/head.jspf"%>
        <title>Bryanair</title>
        <link rel="stylesheet" type="text/css" href="css/profilostyle.css">
        <script language="javascript">
            function whichForm(accountForm, passwordForm, deleteAccountForm){
                forms = document.querySelectorAll(".userform");
                forms[0].style.display = accountForm;
                forms[1].style.display = passwordForm;
                forms[2].style.display = deleteAccountForm;
            }
            
            function visualizeAccount(){
                whichForm("block","none","none");
            }
            
            function changePassword(){
                whichForm("none","block","none");
            }
            
            function deleteAccount(){
                whichForm("none","none","block");
            }
            
            function profileLoadHandler(){
                whichForm(<%=thisform%>);
            }
            
        </script>
    </head>
    <body>
      <%@include file="/include/header.jspf"%>
      <main class="m-main clearfix">
        <aside class="sidebar">
          <div class="propic">
            <img src="images/me.png" alt="me">
          </div>
          <section class="utilities">
            <div>
                <a href="javascript:">Prenotazioni</a>
            </div>
            <div>
                <a href="javascript:">Wishlist</a>
            </div>
            <div>
                <a href="javascript:visualizeAccount()">Account</a>
            </div>
            <div>
                <a href="javascript:changePassword()">Cambia password</a>
            </div>
            <div>
                <a href="javascript:deleteAccount()">Elimina account</a>
            </div>
        </section>
        </aside>
        <section class="userform userform-position">
            <span class="error"><%=(applicationMessage != null && actionPage.equals("account"))? applicationMessage:""%></span>
            <form name="uForm" action="Dispatcher" method="post" class="uform uform-position">
              <label for="username"> Username </label>
              <input type="text" id="username" name="username" maxlength="40" value="<%=user.getUsername()%>" required>
              </br></br>
              <label for="nome"> Nome </label>
              <input type="text" id="nome" name="nome" maxlength="40" value="<%=user.getFirstname()%>" required>
              </br></br>
              <label for="cognome"> Cognome </label>
              <input type="text" id="cognome" name="cognome" maxlength="40" value="<%=user.getLastname()%>" required>
              </br></br>
              <label for="email"> E-mail </label>
              <input type="email" id="email" name="email" maxlength="40" value="<%=user.getEmail()%>" required>            
              </br></br>
              <input type="submit" value="Salva" class="submit submit-dimensioni">
              <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
              <input type="hidden" name="controllerAction" value="UserManager.modify"/>
            </form>
        </section>
        <section class="userform userform-position passwordform">
            <span class="error"><%=(applicationMessage != null && actionPage.equals("setpassword"))? applicationMessage:""%></span>
            <form name="passwordForm" action="Dispatcher" method="post" class="uform uform-position">
              <label for="oldPassword"> Password </label>
              <input type="password" id="oldPassword" name="oldpassword" maxlength="40" required>
              </br></br>
              <label for="newPassword"> New password </label>
              <input type="password" id="newPassword" name="newpassword" maxlength="40" required>
              </br></br>
              <input type="submit" value="Salva" class="submit submit-dimensioni">
              <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
              <input type="hidden" name="controllerAction" value="UserManager.modifyPassword"/>
            </form>
        </section>
        <section class="userform userform-position deleteAccountForm">
            <span class="error"><%=(applicationMessage != null && actionPage.equals("delete"))? applicationMessage:""%></span>
            <form name="deleteAccountForm" action="Dispatcher" method="post" class="uform uform-position">
              <label for="Password"> Password </label>
              <input type="password" id="Password" name="password" maxlength="40" required>
              </br></br>
              <input type="submit" value="Elimina" class="submit submit-dimensioni">
              <input type="hidden" name="userId" value="<%=user.getUserId()%>"/>
              <input type="hidden" name="controllerAction" value="UserManager.deleteAccount"/>
            </form>
        </section>
      </main>
      <%@include file="/include/footer.jspf"%>
    </body>
</html>

