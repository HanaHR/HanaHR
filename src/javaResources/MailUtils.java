package javaResources;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {

    public static void sendEmail(String recipient, String subject, String content) {
        // 발신자 계정 정보 설정
        String senderEmail = "kebhanaroHR@gmail.com"; // 발신자 이메일 주소
        String senderPassword = "awwucbrqszkubqmt"; // 발신자 이메일 계정 비밀번호

        // SMTP 서버 설정
        String smtpHost = "smtp.gmail.com"; // SMTP 서버 호스트
        String smtpPort = "587"; // SMTP 서버 포트 (일반적으로 587 또는 465)

        // 메일 속성 설정
        Properties properties = new Properties();
        properties.put("mail.smtp.host", smtpHost);
        properties.put("mail.smtp.port", smtpPort);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // 인증 정보 설정
        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, senderPassword);
            }
        };

        // 메일 세션 생성
        Session session = Session.getInstance(properties, authenticator);

        try {
            // 메시지 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));
            message.setSubject(subject);
            message.setText(content);

            // 메일 전송
            Transport.send(message);

            System.out.println("이메일이 성공적으로 전송되었습니다.");
        } catch (MessagingException e) {
            System.out.println("이메일 전송 중 오류가 발생했습니다.");
            e.printStackTrace();
        }
    }
}
