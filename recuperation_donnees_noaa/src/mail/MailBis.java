package mail;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.imap.protocol.MessageSet;

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

    this.sendingPort=465;
    Properties props = new Properties();  
    
   props.put("mail.smtp.auth", "true");    
 
    props.put("mail.smtp.host", this.sendingHost);
    props.put("mail.smtp.port", String.valueOf(this.sendingPort));    
    props.put("mail.smtp.user", this.userName);
    props.put("mail.smtp.password", this.password);    
    props.put("mail.transport.protocol", "smtp");     
    props.put("mail.smtp.ssl.enable","true"); 
    props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
    props.put("mail.smtp.starttls.enable", "true");    
    props.put("mail.smtp.EnableSSL.enable", "true");

    
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
  
  
  public Message getMessage() {

    Message monmessage = null;

    try {

      // 1 -> Création de la session
      Properties properties = new Properties();

      properties.put("mail.pop3s.host", "imap.gmail.com");
      properties.put("mail.pop3s.port", "993");
      properties.put("mail.pop3s.starttls.enable", "true");
      properties.put("mail.pop3s.ssl.enable","true"); 
      properties.put("mail.pop3s.ssl.trust", "imap.gmail.com");
      properties.put("mail.pop3s.EnableSSL.enable", "true");

      Session emailSession = Session.getDefaultInstance(properties);

      // create the POP3 store object and connect with the pop server
      Store store = emailSession.getStore("pop3s");

      store.connect("imap.gmail.com", 993,this.userName, password);

      // create the folder object and open it
      Folder emailFolder = store.getFolder("inbox");
      emailFolder.open(Folder.READ_ONLY);

      // retrieve the messages from the folder in an array
      Message[] messages = emailFolder.getMessages();
      monmessage = messages[messages.length - 1];

    } catch (NoSuchProviderException e) {
      e.printStackTrace();
    } catch (MessagingException e) {
      e.printStackTrace();
    } catch (Exception e) {
      e.printStackTrace();
    }
    return monmessage;
  }

  public List<byte[]> pieceJointe(Message message)   throws FileNotFoundException, MessagingException, IOException {

    String disposition = "";
    Multipart mp = (Multipart) message.getContent();
    double n = mp.getCount();
    List<byte[]> listeFichier=new ArrayList<byte[]>();
    for (int j = 0; j < n; j++) {
      Part part = mp.getBodyPart(j);
      disposition = part.getDisposition();
      if ((disposition != null) && ((disposition.equalsIgnoreCase(Part.ATTACHMENT) || (disposition.equalsIgnoreCase(Part.INLINE))))) {       
     
        InputStream lecture = part.getInputStream();       
        listeFichier.add(new byte[lecture.available()]);
        lecture.close();
      }
    }
    return listeFichier;
  }

  

  
  
  public static void main(String[] args) throws InterruptedException {
     MailBis mail= new MailBis();
     mail.sendmail("animation.meteo@gmail.com", "query@saildocs.com", "","Send grib:0.0S,0.0S,10.0W,15.0W|1,1|00,12,24|WIND","animation.meteo", "animationmeteo");
     Thread.sleep(4000);
     try {
      mail.pieceJointe(mail.getMessage());
    } catch (FileNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (MessagingException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
     System.out.println("ok");
  }
}
