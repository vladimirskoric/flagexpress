
package ph.devcon.flag.core.component.utils.application;

import java.time.LocalDateTime;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import lombok.extern.slf4j.Slf4j;
import ph.devcon.flag.core.port.persistence.ConfigurationRepository;
import ph.devcon.flag.infrastructure.persistence.PanacheConfigurationRepository;

@Slf4j
@ApplicationScoped
public class SimpleEmailer implements Emailer {

	public void sendReport(String reportFilename){

		log.info("Report {}", reportFilename);

		ConfigurationRepository configRepo = new PanacheConfigurationRepository();
		String from = configRepo.findByKey("report_email").getValue();
		String to = configRepo.findByKey("report_dest_email").getValue();
		final String username = configRepo.findByKey("reportusername").getValue();
		final String password = configRepo.findByKey("reportpassword").getValue();

		// Assuming you are sending email through relay.jangosmtp.net
		String host = configRepo.findByKey("smtp").getValue();
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", "587");

		// Get the Session object.
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
				}
			});

		try {
			// Create a default MimeMessage object.
			Message message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));

			// Set Subject: header field
			message.setSubject("Generated Report".concat("_").concat(LocalDateTime.now().toString()));

			// Create the message part
			BodyPart messageBodyPart = new MimeBodyPart();

			// Now set the actual message
			messageBodyPart.setText("Hi, Please find generated report for today.");

			// Create a multipar message
			Multipart multipart = new MimeMultipart();

			// Set text message part
			multipart.addBodyPart(messageBodyPart);

			// Part two is attachment
			messageBodyPart = new MimeBodyPart();
			String filename = reportFilename;
			DataSource source = new FileDataSource(filename);
			messageBodyPart.setDataHandler(new DataHandler(source));

			String[] file = filename.split("/");
			messageBodyPart.setFileName(file[file.length-1]);
			multipart.addBodyPart(messageBodyPart);

			// Send the complete message parts
			message.setContent(multipart);

			// Send message
			Transport.send(message);

			log.info("Message{}",message);

			System.out.println("Sent message successfully....");
	
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}