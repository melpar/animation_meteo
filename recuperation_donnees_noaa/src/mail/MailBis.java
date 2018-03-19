package mail;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailBis {
  
  private String from;
  private String to;
  private String subject;
  private String text;
  private String userName;
  private String password;
  private String sendingHost;
  private int sendingPort;
  

  public void sendmail(String from, String to, String subject, String text, String uName, String pwd) {   
    

    this.from=from;

    this.to=to;

    this.subject=subject;

    this.text=text;

    this.userName=uName;

    this.password=pwd;

    

    this.sendingHost="smtp.gmail.com";             

    this.sendingPort=587;
    Properties props = new Properties();  
    
   props.put("mail.smtp.auth", "true");    
    props.put("mail.smtp.starttls.enable", "true");    
    props.put("mail.smtp.EnableSSL.enable", "true");
    props.put("mail.smtp.host", this.sendingHost);
    props.put("mail.smtp.port", String.valueOf(this.sendingPort));    
   //props.setProperty("mail.smtp.socketFactory.port", String.valueOf(this.sendingPort));
    props.put("mail.smtp.user", this.userName);
    props.put("mail.smtp.password", this.password);    
    props.put("mail.transport.protocol", "smtp");    
   // props.put("java.net.preferIPv4Stack", "true");    
    props.put("mail.smtp.ssl.enable","true");      
 

    
   // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
    
    /*props.setProperty("proxySet","true");
    
    props.setProperty("socksProxyHost", "uboproxy4.univ-brest.fr");
    props.setProperty("socksProxyPort", "3128");   
    
  
    props.setProperty("http.proxyHost","uboproxy4.univ-brest.fr");
    props.setProperty("http.proxyPort","3128");*/
    
    props.put("mail.debug", "true");



    Session session1 = Session.getDefaultInstance(props);

    Message simpleMessage = new MimeMessage(session1);

    InternetAddress fromAddress = null;

    InternetAddress toAddress = null;



    try {

          fromAddress = new InternetAddress(this.from);

          toAddress = new InternetAddress(this.to);

     } catch(AddressException e) {

           System.out.println("Mail sending Failed : "+e);

        }



    try {

           simpleMessage.setFrom(fromAddress);

           simpleMessage.setRecipient(RecipientType.TO, toAddress);

           simpleMessage.setSubject(this.subject);

           simpleMessage.setText(this.text);



            Transport transport = session1.getTransport("smtp");

            transport.connect (this.sendingHost,sendingPort, this.userName, this.password);

            transport.sendMessage(simpleMessage, simpleMessage.getAllRecipients());

            transport.close();

            System.out.println("Mail Sent Successfully");

     } catch(MessagingException e) {

            System.out.println("Mail sending Failed : "+e);

        }

    }  

  public void mailSSl () {
            Properties props = new Properties();
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                            "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getDefaultInstance(props,
                    new javax.mail.Authenticator() {
                            protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication("animation.meteo@gmail.com","animationmeteo");
                            }
                    });

            try {

                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress("animation.meteo@gmail.com"));
                    message.setRecipients(Message.RecipientType.TO,
                                    InternetAddress.parse("animation.meteo@gmail.com"));
                    message.setSubject("Testing Subject");
                    message.setText("Dear Mail Crawler," +
                                    "\n\n No spam to my email, please!");

                    Transport.send(message);

                    System.out.println("Done");

            } catch (MessagingException e) {
                    throw new RuntimeException(e);
            }
    }
  
  public void mailTSl () {

    final String username = "animation.meteo@gmail.com";
    final String password = "animationmeteo";

    Properties props = new Properties();
    props.put("mail.smtp.auth", "true");
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");

    Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
            }
      });

    try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("animation.meteo@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("animation.meteo@gmail.com"));
            message.setSubject("Testing Subject");
            message.setText("Dear Mail Crawler,"
                    + "\n\n No spam to my email, please!");

            Transport.send(message);

            System.out.println("Done");

    } catch (MessagingException e) {
            throw new RuntimeException(e);
    }
}
  
  
  public static void main(String[] args) {
     MailBis mail= new MailBis();
     mail.sendmail("animation.meteo@gmail.com", "animation.meteo@gmail.com", "envoie","send grib:0.0S,0.0S,10.0W,10.0W|1,1|00,12,24|WIND","animation.meteo", "animationmeteo");
    //mail.mailSSl();
     //mail.mailTSl();
     //latruiteremonteleruisseau
     System.out.println("ok");
  }
}
