package com.java.financeprojectapp.servicelayer.services;

import java.util.Properties;

import com.java.financeprojectapp.entities.EmailRequest;

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
            int port = 587;
            final String username = "your_email@gmail.com"; // Replace with your Gmail email
            final String password = "your_password"; // Replace with your Gmail password

            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", port);

//            Session session = Session.getInstance(props, new Authenticator() {
//                protected PasswordAuthentication getPasswordAuthentication() {
//                    return new PasswordAuthentication(username, password);
//                }
//            });
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailRequest.getTo()));
//            message.setSubject(emailRequest.getSubject());
//            message.setText(emailRequest.getBody());
//
//            Transport.send(message);

            return Response.ok("Email sent successfully").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Error sending email: " + e.getMessage())
                    .build();
        }
    }
	
}
