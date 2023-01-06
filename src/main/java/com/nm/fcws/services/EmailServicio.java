/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nm.fcws.services;

import com.nm.fcws.modeldb.Contribuyente;
import com.nm.fcws.modeldb.ContribuyenteContacto;
import java.util.List;
import java.util.Objects;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

/**
 *
 * @author BlackSpider
 */
@Service
public class EmailServicio {

    @Autowired
    private JavaMailSender javaMailSender;

    public EmailServicio(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void send(String from, String[] to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        javaMailSender.send(message);
    }

    public void sendWithAttach(String from, String to, String subject,
            String text, String attachName,
            InputStreamSource inputStream) throws MessagingException {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(text, true);
        helper.addAttachment(attachName, inputStream);
        javaMailSender.send(message);
    }

    public void enviar(Contribuyente c, String mensaje) {

        int emailSize = 0;

        if (mensaje.length() > 0) {

            List<ContribuyenteContacto> lcc = c.getContactos();

            if (!lcc.isEmpty() || !Objects.isNull(lcc)) {

                emailSize = c.getContactos().size();

            }

            if (emailSize > 0) {

                String[] destinos = new String[emailSize];
                int i = 0;

                for (ContribuyenteContacto x : c.getContactos()) {

                    destinos[i] = x.getMail();
                    i++;

                }

                this.send("fe@vidrioluz.com.py", destinos, "Alerta", mensaje);

            }

        }

    }

}


