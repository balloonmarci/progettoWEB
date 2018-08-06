<%-- 
    Document   : index
    Created on : 3-ago-2018, 14.41.22
    Author     : Marcello
--%>

<%@page session="false"%>
<!DOCTYPE html>
<html lang="it-IT">
    <head>
        <meta charset="UTF-8"> 
        <meta http-equiv="refresh" content="0; url=/BryanAir/Dispatcher">
        <script type ="text/javascript">
            function onLoadHandler(){
                window.location.href="/BryanAir/Dispatcher";
            }
            window.addEventListener("load", onLoadHandler);
        </script>
        <title> Dispatcher Redirection </title>
    </head>
    <body>
        Se non vieni redirezionato segui: <a href='BrianAir/Dispatcher'> link </a>
    </body>
</html>
