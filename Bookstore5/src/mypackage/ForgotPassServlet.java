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
@WebServlet("/ForgotPassServlet")
public class ForgotPassServlet extends HttpServlet {
	private String message;

	   public void init() throws ServletException {
	      // Do required initialization
	      message = "Hello World";
	   }
	   
	   
	   public void sendMail(String to){
		   	String from = "b6books@gmail.com";
		   	String pass = "kunhoisc00l";
			
			// for testing
		    //String to = "meredithvandevelde@gmail.com";
		    
			// Assuming you are sending email from through gmail's smtp
	        String host = "smtp.gmail.com";

	        // Get system properties
	        Properties properties = System.getProperties();

	        // Setup mail server
	        properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");

	        // Get the Session object.// and pass username and password
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(from, pass);
	            }
	        });

	        // Used to debug SMTP issues
	        session.setDebug(true);

	        try {
	            // Create a default MimeMessage object.
	            MimeMessage message = new MimeMessage(session);

	            // Set From: header field of the header.
	            message.setFrom(new InternetAddress(from));

	            // Set To: header field of the header.
	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            // Set Subject: header field
	            message.setSubject("Password Reset for B6 Bookstore Account");

	            // Now set the actual message
	            message.setText("Forgot your password?\n" + 
	            		"\n" + 
	            		"Please follow <a href='http://localhost/forgotPassword.html'>this link</a> to go to our password reset form where you will be able to select a new password and sign-in: "
	            		+ "<a href='http://localhost/forgotPassword.html'>http://localhost/forgotPassword.html</a>");

	            System.out.println("sending...");
	            // Send message
	            Transport.send(message);
	            System.out.println("Sent message successfully....");
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
		}
	   

	   protected void doPost(HttpServletRequest request,
	            HttpServletResponse response) throws ServletException, IOException {

		   try {
	            // The newInstance() call is a work around for some
	            // broken Java implementations

	            Class.forName("com.mysql.jdbc.Driver").newInstance();
	        } catch (Exception ex) {
	            // handle the error
	        }
	         
			
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlResponse = "<html>";
	        htmlResponse += "</html>";
	        
	        
	        //redirect to 
	        
	        
	         
	        // return response
	        writer.println(htmlResponse);
	         
	    }

	   public void destroy() {
	      // do nothing.
	   }

}
