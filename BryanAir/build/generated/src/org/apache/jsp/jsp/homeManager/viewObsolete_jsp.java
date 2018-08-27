package org.apache.jsp.jsp.homeManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.session.mo.LoggedUser;

public final class viewObsolete_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

    boolean loggedOn = (Boolean) request.getAttribute("loggedOn");
    LoggedUser loggedUser = (LoggedUser) request.getAttribute("loggedUser");
    String applicationMessage = (String) request.getAttribute("applicationMessage");
    
    String airportIata = (String) request.getAttribute("iata");
    String airportName = (String) request.getAttribute("airportName");
    String airportCity = (String) request.getAttribute("city");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("        <title>TEST</title>\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <section>\r\n");
      out.write("        ");
 if(loggedOn) { 
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        Bentornato ");
      out.print(loggedUser.getFirstname());
      out.write(' ');
      out.print(loggedUser.getLastname());
      out.write(" ! <br>\r\n");
      out.write("        ID ");
      out.print(loggedUser.getUserId());
      out.write(" <br><br>\r\n");
      out.write("        \r\n");
      out.write("        Ricerca Aeroporto <br>\r\n");
      out.write("        <form name=\"airportForm\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("            <label for=\"iata\"> IATA CODE </label>\r\n");
      out.write("            <input type=\"text\" id=\"iata\" name=\"iata\" maxlenght=\"3\" required>\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.findAirport\"/>\r\n");
      out.write("            <input type=\"submit\" value=\"OK\">\r\n");
      out.write("        </form>\r\n");
      out.write("        \r\n");
      out.write("        <br>\r\n");
      out.write("        ");
 if (applicationMessage != null) { 
      out.write("\r\n");
      out.write("        ");
      out.print(applicationMessage);
      out.write(" <br>\r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        ");
 if(airportIata != null){ 
      out.write("\r\n");
      out.write("            \r\n");
      out.write("        \r\n");
      out.write("        ");
      out.print(airportIata);
      out.write(' ');
      out.print(airportName);
      out.write(' ');
      out.print(airportCity);
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        ");
 } 
      out.write("\r\n");
      out.write("        \r\n");
      out.write("        <br><br>\r\n");
      out.write("        \r\n");
      out.write("        Fai click qui per effettuare il logout: <br>\r\n");
      out.write("        <form name=\"logoutForm\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("            <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.logout\"/>\r\n");
      out.write("            <input type=\"submit\" value=\"LOGOUT\">\r\n");
      out.write("        </form>\r\n");
      out.write("        \r\n");
      out.write("        \r\n");
      out.write("        ");
} else {
      out.write("\r\n");
      out.write("            ");
 if(applicationMessage != null) {
      out.write("\r\n");
      out.write("            \r\n");
      out.write("                ");
      out.print(applicationMessage);
      out.write("\r\n");
      out.write("            \r\n");
      out.write("            ");
}
      out.write("\r\n");
      out.write("            <form name=\"loginForm\" action=\"Dispatcher\" method=\"post\">\r\n");
      out.write("                <label for=\"username\"> Utente </label>\r\n");
      out.write("                <input type=\"text\" id=\"username\" name=\"username\" maxlength=\"40\" required>\r\n");
      out.write("                <label for=\"password\"> Password </label>\r\n");
      out.write("                <input type=\"text\" id=\"password\" name=\"password\" maxlength=\"40\" required>\r\n");
      out.write("                <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.login\"/>\r\n");
      out.write("                <input type=\"submit\" value=\"OK\">                \r\n");
      out.write("            </form>        \r\n");
      out.write("        ");
}
      out.write("            \r\n");
      out.write("        </section>        \r\n");
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
