package org.apache.jsp.jsp.orderManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.ArrayList<String>(2);
    _jspx_dependants.add("/include/head.jspf");
    _jspx_dependants.add("/include/header.jspf");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, false, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
 
    LoggedUser loggedAdmin = (LoggedUser)request.getAttribute("loggedUser");
    boolean loggedOn = (boolean)request.getAttribute("loggedOn");

      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
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
      out.write("            \r\n");
      out.write("            function onLoadHandler(){\r\n");
      out.write("                try{ firstOnLoadHandler(); } catch(e){}\r\n");
      out.write("                try{ onHomeLoadHandler();  } catch(e){}\r\n");
      out.write("                try{ mainHomeOnLoadHandler(); } catch(e){}\r\n");
      out.write("                try{ onConcreteFlightsLoadHandler(); } catch(e){}\r\n");
      out.write("                try{ mainConcreteFlightsLoadHandler(); } catch(e){}\r\n");
      out.write("            }\r\n");
      out.write("            \r\n");
      out.write("            window.addEventListener(\"load\", onLoadHandler);\r\n");
      out.write("        </script>");
      out.write("\r\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/supportmodule.css\">        \r\n");
      out.write("    </head>\r\n");
      out.write("    <body class=\"adminviewbody\">\r\n");
      out.write("        ");
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
      out.write("              <li><a href=\"\">Profilo</a></li>\r\n");
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
      out.write("      </header>");
      out.write("            \r\n");
      out.write("            <main class=\"support-main\">\r\n");
      out.write("            <section class=\"support-section support-section-color support-section-font\">                  \r\n");
      out.write("                Ecco le tue prenotazioni\r\n");
      out.write("            </section>\r\n");
      out.write("            <div class=\"wrapper\">\r\n");
      out.write("                <div class=\"left-admin support-div\">\r\n");
      out.write("                    Prendi in consegna una nuova chat:\r\n");
      out.write("                    \r\n");
      out.write("                    <div>\r\n");
      out.write("                        <a href=\"javascript:\">\r\n");
      out.write("                            <article class=\"support-article-chat clearfix\">\r\n");
      out.write("                                <img src=\"images/Chat.png\" alt=\"Chat\" class=\"support-img-chat\">\r\n");
      out.write("                                <div class='support-article-centraldiv'>\r\n");
      out.write("                                    <h2 class='support-h2'>\r\n");
      out.write("                                        Prenotazione n° 32143214321 \r\n");
      out.write("                                    </h2>\r\n");
      out.write("                                    <h3 class='support-h3'>\r\n");
      out.write("                                        Roma --> Bangkok\r\n");
      out.write("                                    </h3>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class='support-article-rightdiv'>\r\n");
      out.write("                                    <h2 class='support-h2'>\r\n");
      out.write("                                        321312\r\n");
      out.write("                                    </h2>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </article>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                </div>\r\n");
      out.write("                <div class=\"right support-div\">\r\n");
      out.write("                    Effettua il check in online: \r\n");
      out.write("                    <div>\r\n");
      out.write("                        <a href=\"javascript:\">\r\n");
      out.write("                            <article class=\"support-article-chat clearfix\">\r\n");
      out.write("                                <img src=\"images/Chat.png\" alt=\"Chat\" class=\"support-img-chat\">\r\n");
      out.write("                                <div class='support-article-centraldiv'>\r\n");
      out.write("                                    <h2 class='support-h2'>\r\n");
      out.write("                                        Prenotazione n° 32143214321 \r\n");
      out.write("                                    </h2>\r\n");
      out.write("                                    <h3 class='support-h3'>\r\n");
      out.write("                                        Roma --> Bangkok\r\n");
      out.write("                                    </h3>\r\n");
      out.write("                                </div>\r\n");
      out.write("                                <div class='support-article-rightdiv'>\r\n");
      out.write("                                    <h2 class='support-h2'>\r\n");
      out.write("                                        321312\r\n");
      out.write("                                    </h2>\r\n");
      out.write("                                </div>\r\n");
      out.write("                            </article>\r\n");
      out.write("                        </a>\r\n");
      out.write("                    </div>\r\n");
      out.write("                    \r\n");
      out.write("                    \r\n");
      out.write("                </div>    \r\n");
      out.write("            </div> \r\n");
      out.write("            \r\n");
      out.write("        </main>\r\n");
      out.write("                    \r\n");
      out.write("        <form name=\"supportFormJoin\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"convidJoin\" id=\"convidJoin\"/>\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"SupportManager.adminJoinConversation\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("        \r\n");
      out.write("        <form name=\"supportFormAdminChat\" method=\"post\" action=\"Dispatcher\">\r\n");
      out.write("            <input type=\"hidden\" name=\"convidChat\" id=\"convidChat\"/>\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"SupportManager.adminViewChat\"/>\r\n");
      out.write("        </form>\r\n");
      out.write("                    \r\n");
      out.write("                    \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        <form name=\"logoutForm\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"AdminManager.view\">\r\n");
      out.write("        </form>\r\n");
      out.write("    </body>\r\n");
      out.write("</html>\r\n");
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
