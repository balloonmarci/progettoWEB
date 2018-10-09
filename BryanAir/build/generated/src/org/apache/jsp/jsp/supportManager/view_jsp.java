package org.apache.jsp.jsp.supportManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;

public final class view_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

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

      out.write('\n');
      out.write('\n');

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    
    String airportIata = (String) request.getAttribute("iata");
    String airportName = (String) request.getAttribute("airportName");
    String airportCity = (String) request.getAttribute("city");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>Bryanair</title>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/headerstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/modulelogin.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/footerstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/form.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/state.css\">\n");
      out.write("        <script src=\"script/effetti.js\" type=\"application/javascript\"></script>\n");
      out.write("        <script language=\"javascript\">\n");
      out.write("            function regUser(){\n");
      out.write("                document.regForm.submit();\n");
      out.write("            }\n");
      out.write("            function goHome(){\n");
      out.write("                document.homeForm.submit();\n");
      out.write("            }\n");
      out.write("            \n");
      out.write("        </script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("      <header class=\"clearfix\">\n");
      out.write("        <div class=\"m-topbar m-topbar-position clearfix\">\n");
      out.write("          <div class=\"m-utente\">              \n");
      out.write("            <ul class=\"m-list-ul clearfix\">\n");
      out.write("                \n");
      out.write("            ");
 if (!loggedOn) { 
      out.write("\n");
      out.write("                \n");
      out.write("              <li><a href=\"javascript:regUser();\">Iscriviti</a></li>\n");
      out.write("              <li class=\"m-dropdown\">\n");
      out.write("                <span>Accedi</span>\n");
      out.write("                <div class=\"m-form-login m-dropdown-content\n");
      out.write("                m-dropdown-content-position\">\n");
      out.write("                  <form name=\"loginForm\" action=\"Dispatcher\" method=\"post\" class=\"loginform-dimensioni\">\n");
      out.write("                    <input type=\"text\" id=\"username\" name=\"username\" maxlength=\"40\" placeholder=\"Username\" required>\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\" maxlength=\"40\" placeholder=\"Password\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.login\"/>\n");
      out.write("                    <input type=\"submit\" value=\"Login\" class=\"submit-dimensioni submit-color\">\n");
      out.write("                  </form>\n");
      out.write("                  <form name=\"regForm\" method=\"post\" action=\"Dispatcher\">\n");
      out.write("                      <input type=\"hidden\" name=\"controllerAction\" value=\"UserManager.viewReg\"/>\n");
      out.write("                  </form>\n");
      out.write("                  <form name=\"homeForm\" method=\"post\" action=\"Dispatcher\">\n");
      out.write("                      <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.view\"/>\n");
      out.write("                  </form> \n");
      out.write("                   \n");
      out.write("                </div>\n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                ");
 if(applicationMessage != null) { 
      out.write("\n");
      out.write("                <li class=\"error\">\n");
      out.write("                    ");
      out.print( applicationMessage );
      out.write(" !\n");
      out.write("                </li>\n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                \n");
      out.write("                ");
 } else { 
      out.write("\n");
      out.write("                \n");
      out.write("                <li class=\"loggedIn\">\n");
      out.write("                    Bentornato ");
      out.print(loggedUser.getFirstname());
      out.write(' ');
      out.print(loggedUser.getLastname());
      out.write(" !\n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                <li>\n");
      out.write("                    \n");
      out.write("                    <form name=\"logoutForm\" id=\"logoutForm\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.logout\"/>\n");
      out.write("                    <input class=\"logout\" type=\"submit\" value=\"LOGOUT\">\n");
      out.write("                    </form>\n");
      out.write("                    \n");
      out.write("                                     \n");
      out.write("                    \n");
      out.write("                    \n");
      out.write("                </li>\n");
      out.write("                \n");
      out.write("                \n");
      out.write("                ");
 } 
      out.write("\n");
      out.write("                \n");
      out.write("                </ul>\n");
      out.write("              </div>           \n");
      out.write("          <div class=\"m-logo\">\n");
      out.write("            <a href=\"javascript:goHome();\">BryanAir</a>\n");
      out.write("          </div>\n");
      out.write("          <div class=\"m-services\">\n");
      out.write("            <ul class=\"m-list-ul\">\n");
      out.write("              <li><a href=\"javascript:goHome();\">Home</a></li>\n");
      out.write("              <li><a href=\"\">Prenota</a></li>\n");
      out.write("              <li><a href=\"\">Servizi</a></li>\n");
      out.write("              <li><a href=\"\">Viaggi</a></li>\n");
      out.write("              <li><a href=\"\">Profilo</a></li>\n");
      out.write("            </ul>\n");
      out.write("          </div>\n");
      out.write("        </div>\n");
      out.write("      </header>\n");
      out.write("      <footer class=\"m-footer\">\n");
      out.write("          &copy; copyright 2018\n");
      out.write("          <form name=\"flights\" action=\"Dispatcher\" method=\"post\">\n");
      out.write("                <input type=\"hidden\" name=\"controllerAction\" value=\"FlightManager.view\"/>\n");
      out.write("                <input class=\"logout\" type=\"submit\" value=\"VOLI\">\n");
      out.write("          </form>\n");
      out.write("      </footer>\n");
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
