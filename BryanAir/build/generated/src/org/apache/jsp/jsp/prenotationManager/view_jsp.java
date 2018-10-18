package org.apache.jsp.jsp.prenotationManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;
import java.text.DecimalFormat;
import org.joda.time.DateTime;
import model.mo.ConcreteFlight;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

 private String getDate(DateTime dt){
        String day = (dt.dayOfMonth().get() < 10)? "0" + dt.dayOfMonth().get() : "" + dt.dayOfMonth().get();
        String month = (dt.monthOfYear().get() < 10)? "0" + dt.monthOfYear().get() : "" + dt.monthOfYear().get();
        int year = dt.year().get();
        String hour = (dt.hourOfDay().get() < 10)? "0"+dt.hourOfDay().get() : ""+dt.hourOfDay().get();
        String minutes = (dt.minuteOfHour().get() < 10)? "0"+dt.minuteOfHour().get(): ""+dt.minuteOfHour().get();
        String date = year + "-" + month + "-" + day + " " + hour + ":" + minutes;
        
        return date;
}
  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(3);
    _jspx_dependants.add("/include/head.jspf");
    _jspx_dependants.add("/include/header.jspf");
    _jspx_dependants.add("/include/footer.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write('\n');
      out.write('\n');
LoggedUser loggedUser = (LoggedUser)request.getAttribute("loggedUser");
  boolean loggedOn = (boolean)request.getAttribute("loggedOn");
  String applicationMessage = (String)request.getAttribute("applicationMessage");
  
  int numeroPosti = (int)request.getAttribute("numeroposti");
  ConcreteFlight departureFlight = (ConcreteFlight)request.getAttribute("departureflight");
  ConcreteFlight returnFlight = (ConcreteFlight)request.getAttribute("returnflight");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<!--\n");
      out.write("To change this license header, choose License Headers in Project Properties.\n");
      out.write("To change this template file, choose Tools | Templates\n");
      out.write("and open the template in the editor.\n");
      out.write("-->\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Sito</title>\n");
      out.write("        ");
      out.write("        <title>Bryanair</title>\r\n");
      out.write("        <meta charset=\"UTF-8\">\r\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/headerstyle.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/footerstyle.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/form.css\">\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/state.css\">\r\n");
      out.write("        <script src=\"script/effetti.js\" type=\"application/javascript\"></script>\r\n");
      out.write("        <script src=\"script/homeeffects.js\" type=\"application/javascript\"></script>\r\n");
      out.write("        <script src=\"script/effettivoli.js\" type=\"application/javascript\"></script>\r\n");
      out.write("        <script language=\"javascript\">\r\n");
      out.write("            function regUser(){\r\n");
      out.write("                document.regForm.submit();\r\n");
      out.write("            }\r\n");
      out.write("            function goHome(){\r\n");
      out.write("                document.homeForm.submit();\r\n");
      out.write("            }\r\n");
      out.write("            function goSupport(){\r\n");
      out.write("                if(");
      out.print(loggedOn);
      out.write("){\r\n");
      out.write("                    document.supportForm.submit();\r\n");
      out.write("                } else {\r\n");
      out.write("                    alert(\"Devi effettuare il login per richiedere supporto tecnico\");\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            function goProfile(){\r\n");
      out.write("                if(");
      out.print(loggedOn);
      out.write("){\r\n");
      out.write("                    document.profileForm.submit();\r\n");
      out.write("                } else {\r\n");
      out.write("                    alert(\"Devi effettuare il login per entrare nel profilo\");\r\n");
      out.write("                }\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            function onLoadHandler(){\r\n");
      out.write("                try{ firstOnLoadHandler();             } catch(e){}\r\n");
      out.write("                try{ onHomeLoadHandler();              } catch(e){}\r\n");
      out.write("                try{ mainHomeOnLoadHandler();          } catch(e){}\r\n");
      out.write("                try{ profileLoadHandler();             } catch(e){}\r\n");
      out.write("                try{ onConcreteFlightsLoadHandler();   } catch(e){}\r\n");
      out.write("                try{ mainConcreteFlightsLoadHandler(); } catch(e){}\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            window.addEventListener(\"load\", onLoadHandler);\r\n");
      out.write("        </script>");
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/prenotation.css\">\n");
      out.write("        <script language=\"javascript\">\n");
      out.write("            \n");
      out.write("            function setTotalPrice(pricefirst, pricesecond, first, second){\n");
      out.write("                pricefirst.style.display = first;\n");
      out.write("                pricesecond.style.display = second;\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function setDepartureSelectedClass(classprice, classe){\n");
      out.write("                var textPrice = classprice.textContent;\n");
      out.write("                document.prenotationsForm.departureclass.value = classe;\n");
      out.write("                document.prenotationsForm.departureprice.value = textPrice.split(\" \")[0];\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function setReturnSelectedClass(classprice, classe){\n");
      out.write("                var textPrice = classprice.textContent;\n");
      out.write("                document.prenotationsForm.returnclass.value = classe;\n");
      out.write("                document.prenotationsForm.returnprice.value = textPrice.split(\" \")[0];\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function getPriceFromDepartureFirstClass(){\n");
      out.write("               var prices = document.querySelectorAll(\".totalprice\");\n");
      out.write("               setTotalPrice(prices[0], prices[1], \"block\",\"none\");\n");
      out.write("               var classes = document.querySelectorAll(\".priceclass\");\n");
      out.write("               setSelectedClass(classes[0], \"1\");\n");
      out.write("            }\n");
      out.write("            function getPriceFromDepartureSecondClass(){\n");
      out.write("               var prices = document.querySelectorAll(\".totalprice\");\n");
      out.write("               setTotalPrice(prices[0], prices[1], \"none\",\"block\");\n");
      out.write("               var classes = document.querySelectorAll(\".priceclass\");\n");
      out.write("               setSelectedClass(classes[1], \"2\");\n");
      out.write("            }\n");
      out.write("            function getPriceFromReturnFirstClass(){\n");
      out.write("               var prices = document.querySelectorAll(\".totalprice\");\n");
      out.write("               setTotalPrice(prices[2], prices[3], \"block\",\"none\");\n");
      out.write("               var classes = document.querySelectorAll(\".priceclass\");\n");
      out.write("               setReturnSelectedClass(classes[2], \"1\");\n");
      out.write("            }\n");
      out.write("            function getPriceFromReturnSecondClass(){\n");
      out.write("               var prices = document.querySelectorAll(\".totalprice\");\n");
      out.write("               setTotalPrice(prices[2], prices[3], \"none\",\"block\");\n");
      out.write("               var classes = document.querySelectorAll(\".priceclass\");\n");
      out.write("               setReturnSelectedClass(classes[3], \"2\");\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function goToInsertPrenotation(){\n");
      out.write("                ");
if(loggedOn){
      out.write("\n");
      out.write("                    \n");
      out.write("                    document.prenotationsForm.submit();\n");
      out.write("                ");
}else{
      out.write("\n");
      out.write("                    alert(\"Prima di prenotare devi effettuare il login!\");\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function setPage(){\n");
      out.write("               var classes = document.querySelectorAll(\".priceclass\");\n");
      out.write("               setDepartureSelectedClass(classes[0], \"1\");\n");
      out.write("               ");
if(returnFlight != null){
      out.write("\n");
      out.write("                setReturnSelectedClass(classes[2], \"1\");\n");
      out.write("               ");
}
      out.write("\n");
      out.write("               var prices = document.querySelectorAll(\".totalprice\");\n");
      out.write("               setTotalPrice(prices[0], prices[1], \"block\",\"none\");\n");
      out.write("               \n");
      out.write("               ");
if(returnFlight != null){
      out.write("\n");
      out.write("                    setTotalPrice(prices[2], prices[3], \"block\",\"none\");\n");
      out.write("               ");
}
      out.write("\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            function prenotationLoadHandler(){\n");
      out.write("               document.prenotationInfo.submitPrenotation.addEventListener(\"click\",goToInsertPrenotation);\n");
      out.write("               for(i=0; i < ");
      out.print(numeroPosti);
      out.write("; i++){\n");
      out.write("                   document.forms[\"prenotationsForm\"][\"passengerfirstname\"+i].addEventListener(\"change\",checkField);\n");
      out.write("               }\n");
      out.write("               \n");
      out.write("               setPage();\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("            window.addEventListener(\"load\", prenotationLoadHandler);\n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("      ");
      out.write("\r\n");
      out.write("<header class=\"clearfix\">\r\n");
      out.write("          \r\n");
      out.write("        <div class=\"m-topbar m-topbar-position clearfix\">\r\n");
      out.write("          <div class=\"m-utente\">              \r\n");
      out.write("            <ul class=\"m-list-ul clearfix\">\r\n");
      out.write("                \r\n");
      out.write("            ");
 
                try{
                
                  if (!loggedOn) { 
      out.write("\r\n");
      out.write("                \r\n");
      out.write("              <li><a href=\"javascript:regUser();\">Iscriviti</a></li>\r\n");
      out.write("              <li class=\"m-dropdown\">\r\n");
      out.write("                <span>Accedi</span>\r\n");
      out.write("                <div class=\"m-form-login m-dropdown-content\r\n");
      out.write("                m-dropdown-content-position\">\r\n");
      out.write("                  <form name=\"loginForm\" action=\"Dispatcher\" method=\"post\" class=\"loginform-dimensioni\">\r\n");
      out.write("                    <input type=\"text\" id=\"username\" name=\"username\" maxlength=\"40\" placeholder=\"Username\" required>\r\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\" maxlength=\"40\" placeholder=\"Password\" required>\r\n");
      out.write("                    </br></br>\r\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.login\"/>\r\n");
      out.write("                    <input type=\"submit\" value=\"Login\" class=\"submit-dimensioni submit-color\">\r\n");
      out.write("                  </form>\r\n");
      out.write("                   \r\n");
      out.write("                </div>\r\n");
      out.write("                </li>\r\n");
      out.write("                \r\n");
      out.write("                ");
 if(applicationMessage != null) { 
      out.write("\r\n");
      out.write("                <li class=\"error\">\r\n");
      out.write("                    ");
      out.print( applicationMessage );
      out.write(" !\r\n");
      out.write("                </li>\r\n");
      out.write("                ");
 } 
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                ");
 } else { 
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                <li class=\"loggedIn\">\r\n");
      out.write("                    Bentornato ");
      out.print(loggedUser.getFirstname());
      out.write(' ');
      out.print(loggedUser.getLastname());
      out.write(" !\r\n");
      out.write("                </li>\r\n");
      out.write("                \r\n");
      out.write("                <li>\r\n");
      out.write("                    \r\n");
      out.write("                    <form name=\"logoutForm\" id=\"logoutForm\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.logout\"/>\r\n");
      out.write("                    <input class=\"logout\" type=\"submit\" value=\"LOGOUT\">\r\n");
      out.write("                    </form>\r\n");
      out.write("                    \r\n");
      out.write("                                     \r\n");
      out.write("                    \r\n");
      out.write("                    \r\n");
      out.write("                </li>\r\n");
      out.write("                \r\n");
      out.write("                \r\n");
      out.write("                ");
    }
                  }catch(Exception e){}
      out.write("\r\n");
      out.write("                \r\n");
      out.write("                </ul>\r\n");
      out.write("              </div>           \r\n");
      out.write("          <div class=\"m-logo\">\r\n");
      out.write("            <a href=\"javascript:goHome();\">BryanAir</a>\r\n");
      out.write("          </div>\r\n");
      out.write("          <div class=\"m-services\">\r\n");
      out.write("            <ul class=\"m-list-ul\">\r\n");
      out.write("              <li><a href=\"javascript:goHome();\">Home</a></li>\r\n");
      out.write("              <li><a href=\"\">Prenota</a></li>\r\n");
      out.write("              <li><a href=\"javascript:goSupport();\">Supporto</a></li>\r\n");
      out.write("              <li><a href=\"\">Viaggi</a></li>\r\n");
      out.write("              <li><a href=\"javascript:goProfile();\">Profilo</a></li>\r\n");
      out.write("            </ul>\r\n");
      out.write("          </div>\r\n");
      out.write("        </div>\r\n");
      out.write("        <form name=\"regForm\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"UserManager.viewReg\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("        <form name=\"homeForm\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.view\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("        <form name=\"supportForm\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"SupportManager.view\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("        <form name=\"profileForm\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"UserManager.view\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("      </header>");
      out.write("\n");
      out.write("      <main class=\"clearfix\">\n");
      out.write("        <section class=\"setflightsclasses\">\n");
      out.write("            <div class=\"");
      out.print((returnFlight == null)? "onlydpflight":"dpflight");
      out.write(" clearfix\">\n");
      out.write("              <div class=\"title\">\n");
      out.write("                <span><b>Partenza</b></span>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"flightclass\">\n");
      out.write("                <a href=\"javascript:getPriceFromDepartureFirstClass();\"><b>Prima classe</b></a>\n");
      out.write("                <span>Tariffa più bassa</span>\n");
      out.write("                <span>Posto prenotato</span>\n");
      out.write("                <span>Biglietti flessibili</span>\n");
      out.write("                <span>Bagaglio da 20kg</span>\n");
      out.write("                <span>Bagagli assicurati</span>\n");
      out.write("                <span class=\"priceclass\">");
      out.print(new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceFirst() * 
                        departureFlight.getMultiplier()));
      out.write(" €</span>\n");
      out.write("              </div>\n");
      out.write("              <div class=\"flightclass\">\n");
      out.write("                <a href=\"javascript:getPriceFromDepartureSecondClass();\"><b>Seconda classe</b></a>\n");
      out.write("                <span>Tariffa più bassa</span>\n");
      out.write("                <span>Posto prenotato</span>\n");
      out.write("                <span class=\"ban\">Biglietti flessibili</span>\n");
      out.write("                <span class=\"ban\">Bagaglio da 20kg</span>\n");
      out.write("                <span class=\"ban\">Bagagli assicurati</span>\n");
      out.write("                <span class=\"priceclass\">");
      out.print(new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceSecond() * 
                        departureFlight.getMultiplier()));
      out.write(" €</span>\n");
      out.write("              </div>\n");
      out.write("          </div>\n");
      out.write("          ");
if(returnFlight != null){
      out.write("\n");
      out.write("          <div class=\"dpflight clearfix\">\n");
      out.write("            <div class=\"title\">\n");
      out.write("              <span><b>Ritorno</b></span>\n");
      out.write("            </div>\n");
      out.write("            <div class=\"flightclass\">\n");
      out.write("              <a href=\"javascript:getPriceFromReturnFirstClass();\"><b>Prima classe</b></a>\n");
      out.write("              <span>Tariffa più bassa</span>\n");
      out.write("              <span>Posto prenotato</span>\n");
      out.write("              <span>Biglietti flessibili</span>\n");
      out.write("              <span>Bagaglio da 20kg</span>\n");
      out.write("              <span>Bagagli assicurati</span>\n");
      out.write("              <span class=\"priceclass\">");
      out.print(new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceFirst() * 
                        returnFlight.getMultiplier()));
      out.write(" €</span>\n");
      out.write("              </div>\n");
      out.write("            \n");
      out.write("            <div class=\"flightclass\">\n");
      out.write("                <a href=\"javascript:getPriceFromReturnSecondClass();\"><b>Seconda classe</b></a>\n");
      out.write("              <span>Tariffa più bassa</span>\n");
      out.write("              <span>Posto prenotato</span>\n");
      out.write("              <span class=\"ban\">Biglietti flessibili</span>\n");
      out.write("              <span class=\"ban\">Bagaglio da 20kg</span>\n");
      out.write("              <span class=\"ban\">Bagagli assicurati</span>\n");
      out.write("              <span class=\"priceclass\">");
      out.print(new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceSecond() * 
                        returnFlight.getMultiplier()));
      out.write(" €</span>\n");
      out.write("            </div>\n");
      out.write("          </div>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </section>\n");
      out.write("        \n");
      out.write("        <aside class=\"flightInfo\">\n");
      out.write("          <div>\n");
      out.write("            <span><b>Dettaglio prenotazione</b></span>\n");
      out.write("          </div>\n");
      out.write("          <div>\n");
      out.write("            \n");
      out.write("            <span><b>");
      out.print(departureFlight.getVirtualFlight().getDepartureAirport().getAirportname());
      out.write("</b> a \n");
      out.write("                <b>");
      out.print(departureFlight.getVirtualFlight().getArrivalAirport().getAirportname());
      out.write("</b></span>\n");
      out.write("            <span>");
      out.print(getDate(departureFlight.getDate()));
      out.write(" - ");
      out.print(getDate(departureFlight.getArrivalDate()));
      out.write(' ');
      out.print(departureFlight.getVirtualFlight().getFlightCode());
      out.write("</span>\n");
      out.write("            <span>");
      out.print(numeroPosti);
      out.write(" x Adulti</span>\n");
      out.write("            <form>\n");
      out.write("                <span class=\"totalprice\">Prima classe <br/><br/> Totale: ");
      out.print(new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceFirst() * 
                        departureFlight.getMultiplier() * numeroPosti)+"€");
      out.write("</span>\n");
      out.write("                <span class=\"totalprice\">Seconda classe <br/><br/> Totale: ");
      out.print(new DecimalFormat("#.##").format(departureFlight.getVirtualFlight().getPriceSecond() * 
                        departureFlight.getMultiplier() * numeroPosti)+"€");
      out.write("</span>\n");
      out.write("            </form>\n");
      out.write("          </div>\n");
      out.write("          ");
if(returnFlight != null){
      out.write("\n");
      out.write("          <div>\n");
      out.write("            <span><b>");
      out.print(returnFlight.getVirtualFlight().getDepartureAirport().getAirportname());
      out.write("</b> a \n");
      out.write("                <b>");
      out.print(returnFlight.getVirtualFlight().getArrivalAirport().getAirportname());
      out.write("</b></span>\n");
      out.write("            <span>");
      out.print(getDate(returnFlight.getDate()));
      out.write(" - ");
      out.print(getDate(returnFlight.getArrivalDate()));
      out.write(' ');
      out.print(returnFlight.getVirtualFlight().getFlightCode());
      out.write("</span>\n");
      out.write("            <span>");
      out.print(numeroPosti);
      out.write(" x Adulti</span>\n");
      out.write("            <span class=\"totalprice\">Prima classe <br/><br/> Totale: ");
      out.print(new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceFirst() * 
                        returnFlight.getMultiplier() * numeroPosti)+"€");
      out.write("</span>\n");
      out.write("            <span class=\"totalprice\">Seconda classe <br/><br/> Totale: ");
      out.print(new DecimalFormat("#.##").format(returnFlight.getVirtualFlight().getPriceSecond() * 
                        returnFlight.getMultiplier() * numeroPosti)+"€");
      out.write("</span>\n");
      out.write("          </div>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("           <form name=\"prenotationInfo\">\n");
      out.write("            <input type=\"button\" name=\"submitPrenotation\" value=\"Prenota\"/>\n");
      out.write("           </form>\n");
      out.write("        </aside>\n");
      out.write("      <section class=\"passengerData\">\n");
      out.write("        <div class=\"title\">\n");
      out.write("          <span><b>Passeggeri</b></span>\n");
      out.write("        </div>\n");
      out.write("        <form name=\"prenotationsForm\" action=\"Dispatcher\" method=\"post\" class=\"passenger\">\n");
      out.write("        ");
for(int i = 1; i <= numeroPosti; i++){
      out.write("\n");
      out.write("            <div class=\"passenger\">\n");
      out.write("              <input type=\"text\" name=\"passengerfirstname");
      out.print(i);
      out.write("\" class=\"jsCheck\" placeholder=\"Inserisci nome\" required />\n");
      out.write("              <input type=\"text\" name=\"passengerlastname");
      out.print(i);
      out.write("\" class=\"jsCheck\" placeholder=\"Inserisci cognome\" required />\n");
      out.write("              <input type=\"text\" name=\"passengergender");
      out.print(i);
      out.write("\"  class=\"jsCheck\"  list=\"Gender\" placeholder=\"Inserisci sesso\" required />\n");
      out.write("              <datalist id=\"Gender\">\n");
      out.write("                <select name=\"Gender\">\n");
      out.write("                  <option value=\"Maschio\">\n");
      out.write("                  <option value=\"Femmina\">\n");
      out.write("                  <option value=\"Altro\">\n");
      out.write("                </select>\n");
      out.write("              </datalist>\n");
      out.write("           </div>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            ");
if(returnFlight == null){
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"numeroposti\" value=\"");
      out.print(numeroPosti);
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureclass\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureprice\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightcode\" value=\"");
      out.print(departureFlight.getVirtualFlight().getFlightCode());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightdeparturedate\" value=\"");
      out.print(departureFlight.getDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightarrivaldate\" value=\"");
      out.print(departureFlight.getArrivalDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"PrenotationManager.createOnlyDeparturePrenotation\"/>\n");
      out.write("            ");
}else{
      out.write("\n");
      out.write("            <input type=\"hidden\" name=\"numeroposti\" value=\"");
      out.print(numeroPosti);
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureclass\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureprice\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightcode\" value=\"");
      out.print(departureFlight.getVirtualFlight().getFlightCode());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightdeparturedate\" value=\"");
      out.print(departureFlight.getDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"departureflightarrivaldate\" value=\"");
      out.print(departureFlight.getArrivalDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"returnclass\"/>\n");
      out.write("            <input type=\"hidden\" name=\"returnprice\"/>\n");
      out.write("            <input type=\"hidden\" name=\"returnflightcode\" value=\"");
      out.print(returnFlight.getVirtualFlight().getFlightCode());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"returnflightdeparturedate\" value=\"");
      out.print(returnFlight.getDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"returnflightarrivaldate\" value=\"");
      out.print(returnFlight.getArrivalDate());
      out.write("\"/>\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"PrenotationManager.createPrenotation\"/>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("        </form>\n");
      out.write("    </section>\n");
      out.write("      </main>\n");
      out.write("      ");
      out.write("<footer class=\"m-footer\">\n");
      out.write("\n");
      out.write("    &copy; copyright 2018\n");
      out.write("    <form name=\"flights\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("        <input type=\"hidden\" name=\"controllerAction\" value=\"AdminManager.view\" />\n");
      out.write("        <input class=\"logout\" type=\"submit\" value=\"VOLI\" ");
      out.print((loggedOn)? "hidden":"");
      out.write(">\n");
      out.write("    </form>\n");
      out.write("</footer>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
      out.write("\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
