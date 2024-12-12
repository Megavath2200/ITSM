package com.ticketing.tool.service;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.ticketing.tool.dto.Email;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	private Logger logger = LoggerFactory.getLogger(EmailService.class);

	@Autowired
	private JavaMailSender javaMailSender;

	@Async
	public void sendEmail(Email email) throws IOException {
		logger.info("Sending email to: {}", email.getTo());
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper helper;
		try {

			helper = new MimeMessageHelper(mimeMessage, true);

			helper.setTo(email.getTo());
			helper.setSubject(email.getSubject());
			helper.setText(email.getBody(), true);

			try {
				ClassPathResource logoResource = new ClassPathResource("images/Logo.jpeg");
				byte[] logoBytes = Files.readAllBytes(Paths.get(logoResource.getURI()));

				if (logoBytes != null) {

					final InputStreamSource logoSource = new InputStreamSource() {

						@Override
						public InputStream getInputStream() throws IOException {
							return new ByteArrayInputStream(logoBytes);
						}

					};

					helper.addInline("companyLogo", logoSource, "image/jpeg");
				}
			} catch (IOException e) {
				logger.error("Failed to fetch company logo from resources folder", e);
			}

			javaMailSender.send(mimeMessage);

			logger.info("Email sent successfully to: {}", email.getTo());

		} catch (MessagingException e) {
			logger.error("Error sending email to: {}", email.getTo(), e);
			e.printStackTrace();
		}
	}
}
