package org.apache.jsp.jsp.supportManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;
import java.util.List;

public final class chat_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");    
    String applicationMessage = (String) request.getAttribute("applicationMessage");   

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
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
      out.write("\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/supportmodule.css\">\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
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
      out.write("\n");
      out.write("        <main>\n");
      out.write("        \n");
      out.write("            \n");
      out.write("        \n");
      out.write("        </main>\n");
      out.write("           \n");
      out.write("        ");
      out.write("<footer class=\"m-footer\">\r\n");
      out.write("\r\n");
      out.write("    &copy; copyright 2018\r\n");
      out.write("    <form name=\"flights\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("        <input type=\"hidden\" name=\"controllerAction\" value=\"AdminManager.view\" />\r\n");
      out.write("        <input class=\"logout\" type=\"submit\" value=\"VOLI\" ");
      out.print((loggedOn)? "hidden":"");
      out.write(">\r\n");
      out.write("    </form>\r\n");
      out.write("</footer>\r\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
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
