package com.roux.service;

import com.roux.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Service("mailService")
public class MailServiceImpl implements MailService {

    @Autowired
    JavaMailSender mailSender;

    private String website = "http://localhost:8090";

    private void send(MimeMessagePreparator preparator) {
        try {
            mailSender.send(preparator);
            System.out.println("Message Sent");
        } catch (MailException ex) {
            ex.printStackTrace();
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void sendConfirmationEmail(Object object) {
        User user = (User) object;
        MimeMessagePreparator preparator = getMessagePreparator(user);
        send(preparator);
    }

    private MimeMessagePreparator getMessagePreparator(final User user) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setFrom("roux.noreply@gmail.com");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(user.getEmail()));
                mimeMessage.setText(getConfirmationBody(user));
                mimeMessage.setSubject("Confirm email");
            }
        };
        return preparator;
    }

    private String getConfirmationBody(final User user) {
        return "Dear " + user.getFirstName() + " " + user.getLastName()
                + ", Thank You for choosing ROUX Hotel. Your ID id " + Integer.toString(user.getId()) + ". Click link below to get (" + user.getUsername()
                + ") verified and start booking." + '\n' + generateConfirmationEmail(user.getUsername(), user.getToken());
    }

    private String generateConfirmationEmail(String username, String token) {
        return (website + "/user/profileid-" + username + "/" + token + "/confirm");
    }

    public JavaMailSender getMailSender() {
        return mailSender;
    }

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

}