/*
 * Author: Meredith Van De Velde
 * Date: 10/27/2020
 * Notes: Credit to Jack Solomon's extra credit lecture, as well as 
 * this blog post: https://pepipost.com/tutorials/send-email-in-java-using-gmail-smtp/
 */

package mypackage;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;


public class SendEmail {
	String pass = "kunhoisc00l";
	
	// for testing
    String to = "meredithvandevelde@gmail.com";
    String from = "b6books@gmail.com";
	
	public void sendMail(String to, String from){
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
            		"Please follow this link  to go to our password reset form where you will be able to select a new password and sign-in.");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
	}
	
	
}
