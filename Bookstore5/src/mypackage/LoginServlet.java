package mypackage;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.io.File;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



/**
 * Servlet implementation class RegisterServletLogin
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private String message;

	   public void init() throws ServletException {
	      // Do required initialization
	      message = "Hello World";
	   }
	   
	   // COOKIES PLS
	   

	   protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

		   try {
	            // The newInstance() call is a work around for some
	            // broken Java implementations

	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {
	            // handle the error
	        }
	         
	        // read form fields
	        String email = request.getParameter("email");
			String password = request.getParameter("password");
			
	        System.out.println("email: " + email);
			System.out.println("password: " + password);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlResponse = "<html>";     
	        htmlResponse += "Your password is: " + password + "</h2>";
	        htmlResponse += "<h2>Your email is: " + email + "<br/>"; 
	        htmlResponse += "</html>";
	        
	        // get User
	        UserDA test = new UserDA();
	        test.getUser(email);
	        
	        // Does password match?
	        // if not throw error and make log in again
	         
	        // return response
	        writer.println(htmlResponse);
	         
	    }

	   public void destroy() {
	      // do nothing.
	   }

}
