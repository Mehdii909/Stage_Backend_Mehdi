package com.example.stage_backend.services;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void sendEmail(String recipientEmail, String login, String password) {
        // Configuration SMTP
        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.debug", "true");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");

        // Création d'une session avec les informations d'authentification
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("mailrecoverjee@gmail.com", "qoxobsznknlbjxpv"); // Remplacez "your-email" et "your-password" par vos informations d'authentification
            }
        });

        try {
            // Création du message e-mail
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("mailrecoverjee@gmail.com")); // Remplacez "your-email" par votre adresse e-mail
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Vos informations de connexion");
            message.setText("Cher élève," +
                    "\n\nVoici vos informations de connexion :" +
                    "\nIdentifiant : " + login +
                    "\nMot de passe : " + password +
                    "\n\nVeuillez conserver ces informations en sécurité." +
                    "\n\nCordialement," +
                    "\nVotre école Les Nouvelles Générations");

            // Envoi du message
            Transport.send(message);

            System.out.println("E-mail sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
