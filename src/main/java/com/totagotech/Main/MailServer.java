/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.totagotech.Main;

import java.util.Properties;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;

/**
 *
 * @author Totago_User3
 */
public class MailServer {

    public MailServer(final String mailAddress, final String message, final String mailTitle,
            final String path, final String desc, final String name) throws EmailException {

        Properties props = System.getProperties();

        props.put("mail.smtp.ssl.trust", "smtp.googlemail.com");
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
        try {

            // Create the attachment
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(path); // here I want to set the prepared report path so that it could be sent to the user.
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription(desc);
            attachment.setName(name);

//            Properties props = new Properties();
//            props.put("mail.smtp.ssl.trust", "smtp.googlemail.com");
            final MultiPartEmail email = new MultiPartEmail();
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(587);
            email.setStartTLSEnabled(true);
            email.setStartTLSRequired(true);
            email.setAuthentication("princehardetuneji@gmail.com", "#AbdulwasiuIbrahim@");
            email.addTo(mailAddress);
            email.setFrom("mabdulwasii@gmail.com", "BOI ESB project");
            email.setSubject(mailTitle);
            email.setMsg(message);

            email.attach(attachment);

            System.out.println("Attachement file path" + attachment.getPath());

            email.send();
            System.out.println("Message is Sent");
        } catch (final EmailException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            System.out.println("message is not sent");
        }
    }
}
