package net.bluefsd.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import net.bluefsd.entity.BFUser;

@Service
public class MailService {
	@Value("${spring.mail.username}")
	private String from;
	
	@Value("")
	private String verifyLink;

	@Autowired
	private JavaMailSender mailSender;

	private String subject = "Verify your registration";

	public void sendMail(BFUser user) {

		try {
			SimpleMailMessage message = new SimpleMailMessage();
			message.setTo(user.getEmail());
			message.setSubject(subject);
			message.setFrom(from);
			message.setText(composeSimpleContent(user));

			mailSender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static String composeSimpleContent(BFUser user) {
		String link = "http://localhost:4200/signup?baxjkd;afja;fd;as";
		StringBuffer sb = new StringBuffer();
		sb.append("Hi " + user.getUserName() + ":\r\n");
		sb.append("\r\n");
		sb.append("Thanks for your registration.\r\n");
		sb.append("\r\n");
		sb.append("Please click below link to verify you account:");
		sb.append("\r\n");
		sb.append(link);
		sb.append("\r\n");
		sb.append("Thanks");
		sb.append("\r\n");
		sb.append("from FSD server");

		return sb.toString();
	}

	private static String composeContent(BFUser user) {
		String link = "";
		StringBuffer sb = new StringBuffer();
		sb.append("<html>");
		sb.append("<body>");
		sb.append("<p>");
		sb.append("Hi " + user.getUserName() + ":");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("Thanks for your registration.");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("Please click below link to verify you account:");
		sb.append("</p>");
		sb.append("<p>");
		sb.append("<a href=\"" + link + "\">" + link + "</a>");
		sb.append("</p>");
		sb.append("<p></p>");
		sb.append("<p>");
		sb.append("Thanks");
		sb.append("</p>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
