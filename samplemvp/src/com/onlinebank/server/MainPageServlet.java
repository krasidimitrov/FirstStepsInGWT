package com.onlinebank.server;

import com.google.inject.Singleton;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Krasimir Dimitrov (kpackapgo@gmail.com, krasimir.dimitrov@clouway.com)
 */

@Singleton
public class MainPageServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    PrintWriter writer = resp.getWriter();
    req.getSession().getId();
    writer.println(
            "<html>\n" +
                    "<head>\n" +
                    "    <title>Bank</title>\n" +
                    "    <link type=\"text/css\" rel=\"stylesheet\" href=\"Bank.css\">\n" +
                    "    <script type=\"text/javascript\" language=\"javascript\" src=\"MySampleApplication/MySampleApplication.nocache.js\"></script>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "\n" +
                    "</body>\n" +
                    "</html>\n"
    );
  }
}
