package com.miaskor.service;

import com.miaskor.util.CodeGenerator;
import com.miaskor.util.CodeGeneratorImpl;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SendEmailWithCodeService {
    private static final SendEmailWithCodeService INSTANCE = new SendEmailWithCodeService();
    private final CodeGenerator codeGenerator = CodeGeneratorImpl.getInstance();

    public String sendEmail(String emailTo){
        Email email = EmailBuilder.startingBlank()
                .from("lollypop", "mi3h1@mail.ru")
                .to("C. Cane", emailTo)
                .cc("C. Bo <chocobo@candyshop.org>")
                .withSubject("hey")
                .withPlainText("We should meet up! ;)")
                .buildEmail();


        var mailer = MailerBuilder
                .withSMTPServer("smtp.mail.ru", 465, "mi3h1@mail.ru", "23ab80i12")
                .withTransportStrategy(TransportStrategy.SMTP_TLS)
                .withDebugLogging(true)
                .buildMailer();

        mailer.sendMail(email);
        mailer.shutdownConnectionPool();
        return "code";
    }


    public static void main(String[] args) {
        INSTANCE.sendEmail("misha20012377@gmail.com");
    }


    public static SendEmailWithCodeService getInstance(){
        return INSTANCE;
    }
}
