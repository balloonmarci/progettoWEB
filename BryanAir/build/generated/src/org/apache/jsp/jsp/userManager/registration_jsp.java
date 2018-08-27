package org.apache.jsp.jsp.userManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import model.mo.User;

public final class registration_jsp extends org.apache.jasper.runtime.HttpJspBase
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
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
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
String applicationMessage = (String) request.getAttribute("applicationMessage");
  User user = (User)request.getAttribute("user");
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
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Open+Sans\" rel=\"stylesheet\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/base.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/headerstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/registermodule.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/footerstyle.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/form.css\">\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css/state.css\">\n");
      out.write("        <script src=\"script/effetti.js\" type=\"application/javascript\"></script>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        <header>\n");
      out.write("            <div class=\"m-topbar m-topbar-position clearfix\">\n");
      out.write("                <div class=\"m-utente\">\n");
      out.write("                    <ul class=\"m-list-ul clearfix\">\n");
      out.write("                        <li><a href=\"registrazione.html\">Iscriviti</a></li>\n");
      out.write("                        <li class=\"m-dropdown\">\n");
      out.write("                            <span>Accedi</span>\n");
      out.write("                            <div class=\"m-form-login m-dropdown-content m-dropdown-content-position\">\n");
      out.write("                                <form name=\"loginForm\" action=\"Dispatcher\" method=\"post\" class=\"loginform-dimensioni\">\n");
      out.write("                                    <input type=\"text\" name=\"username\" maxlength=\"40\" placeholder=\"Username\" required>\n");
      out.write("                                    <input type=\"password\" name=\"password\" maxlength=\"40\" placeholder=\"Password\" required>\n");
      out.write("                                    </br></br>\n");
      out.write("                                    <input type=\"hidden\" name=\"controllerAction\" value=\"HomeManager.login\"/>\n");
      out.write("                                    <input type=\"submit\" value=\"Login\" class=\"submit-dimensioni submit-color\">\n");
      out.write("                                </form>\n");
      out.write("                            </div>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"m-logo\">\n");
      out.write("                        <a href=\"home.html\">BryanAir</a>\n");
      out.write("                    </div>\n");
      out.write("                    <div class=\"m-services\">\n");
      out.write("                        <ul class=\"m-list-ul\">\n");
      out.write("                            <li><a href=\"\">Home</a></li>\n");
      out.write("                            <li><a href=\"\">Prenota</a></li>\n");
      out.write("                            <li><a href=\"\">Servizi</a></li>\n");
      out.write("                            <li><a href=\"\">Viaggi</a></li>\n");
      out.write("                            <li><a href=\"\">Profilo</a></li>\n");
      out.write("                        </ul>\n");
      out.write("                    </div>\n");
      out.write("                \n");
      out.write("        </header>\n");
      out.write("        <main class=\"m-main\">\n");
      out.write("            <section class=\"m-section m-section-color m-section-font\">\n");
      out.write("                Registrazione\n");
      out.write("            </section>\n");
      out.write("            <div class=\"m-reg-form m-reg-form-color\">\n");
      out.write("                <form name=\"registerForm\" action=\"Dispatcher\" method=\"post\" class=\"regform-dimensioni regform-position\">\n");
      out.write("                    <span style=\"display: block; color:red; margin-left: 30%; margin-top:1%;\">");
      out.print((user!=null) ? applicationMessage : "");
      out.write("</span></br>\n");
      out.write("                    <label for=\"username\"> Username </label>\n");
      out.write("                    <input type=\"text\" id=\"username\" name=\"username\" style=\"");
      out.print((user!=null) ? "border: 1px solid red;" : "");
      out.write("\" maxlength=\"40\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <label for=\"nome\"> Nome </label>\n");
      out.write("                    <input type=\"text\" id=\"nome\" name=\"firstname\" maxlength=\"40\" value = \"");
      out.print((user != null) ? user.getFirstname() : "");
      out.write("\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <label for=\"cognome\"> Cognome </label>\n");
      out.write("                    <input type=\"text\" id=\"cognome\" name=\"lastname\" maxlength=\"40\" value = \"");
      out.print((user != null) ? user.getLastname() : "");
      out.write("\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <label for=\"email\"> E-mail </label>\n");
      out.write("                    <input type=\"email\" id=\"email\" name=\"email\" maxlength=\"40\" style=\"");
      out.print((user!=null) ? "border: 1px solid red;" : "");
      out.write("\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <label for=\"password\"> Password </label>\n");
      out.write("                    <input type=\"password\" id=\"password\" name=\"password\" maxlength=\"40\" required>\n");
      out.write("                    </br></br>\n");
      out.write("                    <input type=\"hidden\" name=\"controllerAction\" value=\"UserManager.register\"/>\n");
      out.write("                    <!--<input type=\"hidden\" name=\"userId\" value=\"95356\"/>-->\n");
      out.write("                    <input type=\"submit\" value=\"Registrati\" class=\"submit-dimensioni submit-position submit-color2\">\n");
      out.write("                </form>\n");
      out.write("            </div>\n");
      out.write("        </main>\n");
      out.write("        <footer class=\"m-footer\">\n");
      out.write("            &copy; copyright 2018\n");
      out.write("        </footer>\n");
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
