package common;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class SendMessage 
{
    public void Start(String bt,String nr,String yx) 
    {
        String smtphost = "smtp.163.com";
        String user = "rianddy@163.com";
        String password = "cairuicairuiaaaa";
        String from = "rianddy@163.com";
        String to = yx; 
        String subject = bt;
        String body = nr;
        try
        {
            Properties props = new Properties();
            props.put("mail.smtp.host", smtphost);
            props.put("mail.smtp.auth","true");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.connectiontimeout", "2000");
            Session ssn = Session.getInstance(props, null);

            MimeMessage message = new MimeMessage(ssn);

            InternetAddress fromAddress = new InternetAddress(from);
            message.setFrom(fromAddress);
            InternetAddress toAddress = new InternetAddress(to);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            message.setSubject(subject);
            message.setText(body);

            Transport transport = ssn.getTransport("smtp");
            transport.connect(smtphost, user, password);
            transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
            //transport.send(message);
            transport.close();
        }
        catch(Exception m)
        {
            m.printStackTrace();
        }
    }
}