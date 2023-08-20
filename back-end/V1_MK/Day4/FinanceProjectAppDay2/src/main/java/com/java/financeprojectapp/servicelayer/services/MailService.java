package com.java.financeprojectapp.servicelayer.services;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.java.financeprojectapp.entities.EmailRequest;
import com.java.financeprojectapp.servicelayer.entities.RandomPasswordGenerator;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/mail")
public class MailService {
	
	@POST
    @Path("/send")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendEmail(EmailRequest emailRequest) {
        try {
            String host = "smtp.gmail.com";
//            int port = 587;
            final String username = "manishssssskumaraaaaa@gmail.com"; // Replace with your Gmail email
            final String password = "osbzpdwcdbexonng"; // Replace with your Gmail password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
    		props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    		props.put("mail.smtp.port", "465"); //SMTP Port
//            props.put("mail.smtp.port", port);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            session.setDebug(true);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getTo()));
            message.setSubject(emailRequest.getSubject());
            message.setText(emailRequest.getBody());

            Transport.send(message);

            return Response.ok("Email sent successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error sending email: " + e.getMessage())
                    .build();
        }
    }
	
	@POST
    @Path("/send/password")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response sendPasswordEmail(EmailRequest emailRequest) {
		
		String loginpassword = RandomPasswordGenerator.generateRandomPassword(15);
		String body = "We're excited to inform you that your new login credentials have been generated and are ready for use. Please find below your login details:\r\n"
				+ "\r\n"
				+ "Username: " + emailRequest.getTo()
				+ "\r\n"
				+ "Password: " + loginpassword
				+ "\r\n"
				+ "For security purposes, we recommend that you change your password upon logging in for the first time. To do so, please follow the steps outlined on our website's password reset page.\r\n"
				+ "\r\n"
				+ "If you encounter any issues or have any questions regarding your new login credentials, please don't hesitate to reach out to our support team at manishssssskumaraaaaa@gmail.com. We're here to assist you every step of the way.\r\n"
				+ "\r\n"
				+ "Thank you for choosing Ganesh Finance Limited Company for your Loan needs. We look forward to serving you and ensuring a seamless experience.\r\n"
				+ "\r\n"
				+ "Best regards,\r\n"
				+ "\r\n"
				+ "Ganesh Finance Limited Company\r\n"
				+ "manishssssskumaraaaaa@gmail.com\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "\r\n"
				+ "";
		
		
        try {
            String host = "smtp.gmail.com";
//            int port = 587;
            final String username = "manishssssskumaraaaaa@gmail.com"; // Replace with your Gmail email
            final String password = "osbzpdwcdbexonng"; // Replace with your Gmail password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
//            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
    		props.put("mail.smtp.socketFactory.class",
    				"javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
    		props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
    		props.put("mail.smtp.port", "465"); //SMTP Port
//            props.put("mail.smtp.port", port);

            Session session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            
            session.setDebug(true);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getTo()));
            message.setSubject("New Login Credentials");
            message.setText(body);

            Transport.send(message);

            return Response.ok("Email sent successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error sending email: " + e.getMessage())
                    .build();
        }
    }

}