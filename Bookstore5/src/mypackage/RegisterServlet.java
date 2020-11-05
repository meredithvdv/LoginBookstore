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



/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private String message;

	   public void init() throws ServletException {
	      // Do required initialization
	      message = "Hello World";
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
	         
	        // read form fields
	        String name = request.getParameter("name");
	        String email = request.getParameter("email");
			String phoneNumber = request.getParameter("phoneNumber");
			String password = request.getParameter("password");
			String password2 = request.getParameter("password2");
			
		    String lastName =name.split(" ")[name.split(" ").length-1];
		    String firstName = name.substring(0, name.length() - lastName.length());
	         
	        System.out.println("first name: " + firstName);
	        System.out.println("last name: " + lastName);
	        System.out.println("email: " + email);
			System.out.println("phoneNumber: " + phoneNumber);
			System.out.println("password: " + password);
			System.out.println("password2: " + password2);
	 
	        // do some processing here...
	         
	        // get response writer
	        PrintWriter writer = response.getWriter();
	         
	        // build HTML code
	        String htmlRespone = "<html>";
	        htmlRespone += "<h2>Your username is: " + name + "<br/>";      
	        htmlRespone += "Your password is: " + password + "</h2>";
	        htmlRespone += "<h2>Your phone number is: " + phoneNumber + "<br/>"; 
	        htmlRespone += "<h2>Your email is: " + email + "<br/>"; 
	        htmlRespone += "</html>";
	        
	        /*
	        try {
				
				Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/sys?serverTimezone=UTC", "root", "Pizza11!11");
				
			} catch (SQLException e){
				e.printStackTrace();
			}
			*/
	        
	        UserDA test = new UserDA();
	        test.insertNewUser(8, firstName, lastName, password, email, 1);
	        test.printHi();
	        
	         
	        // return response
	        writer.println(htmlRespone);
	         
	    }

	   public void destroy() {
	      // do nothing.
	   }

}
