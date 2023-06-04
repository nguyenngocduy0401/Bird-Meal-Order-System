/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.utils;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.activation.DataHandler;
//import javax.activation.DataSource;
//import jdk.javadoc.internal.doclets.toolkit.Content;

/**
 *
 * @author haong
 */
public class MailService {

    //Email: birdmealordersystem@gmail.com
    //pass: kgmnxkwcfipzyyow
    private static final String FROM = "birdmealordersystem@gmail.com";
    private static final String PASSWORD = "kgmnxkwcfipzyyow";

    public boolean sendVerifyEmail(String email,String link) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMPT HOST
        props.put("mail.smtp.port", "587"); //TLS 587 SSL465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Create authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, PASSWORD);
            }
        };
        Session session = Session.getInstance(props, auth);
        MimeMessage msg = new MimeMessage(session);
        try {
            //Kiểu nội dung
            msg.addHeader("Content-type", "text/html; charset=UTF-8");
            //Ng gửi
            msg.setFrom(FROM);

            //Ng nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email, false));

            //Tiêu đề
            msg.setSubject("Confirm your email address");

            //Quy định email nhận phản hồi
            //msg.setReplyTo()
            //Nội dung
            msg.setText("<div>Thanks for signing up. To complete your account registration " + link + "."
                    + "The link will expire in 15 minutes..</div>","UTF-8","html");
            //Gửi email
            Transport.send(msg);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}