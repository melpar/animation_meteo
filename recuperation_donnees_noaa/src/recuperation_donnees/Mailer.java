package recuperation_donnees;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mailer {

  private static final String from = "animation.meteo@gmail.com";
  private static final String password = "animationmeteo";
  private static final String to = "query@saildocs.com";

  public static void send(String msg) {
    // Get properties object
    Properties props = new Properties();
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.socketFactory.port", "465");
    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.port", "465");
    // get Session
    Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(from, password);
      }
    });
    // compose message
    try {
      MimeMessage message = new MimeMessage(session);
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
      message.setSubject("");
      message.setText(msg);
      // send message
      Transport.send(message);
      System.out.println("message sent successfully");
    } catch (MessagingException e) {
      throw new RuntimeException(e);
    }

  }

  public static void main(String[] args) {
    // from,password,to,subject,message
    Mailer.send("send gfs:40N,60N,140W,120W|2,2|24,48,72|WIND");
    // change from, password and to
  }
}