package com.ojasa.reimburseit.service;

import java.sql.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ojasa.reimburseit.dao.ExpenseStatusDaoImplement;
import com.ojasa.reimburseit.model.EmailParameters;

@Service
public class ExpenseStatusService {

	@Autowired
	ExpenseStatusDaoImplement expensestatusdaoimplement;

	// Email
	static long employeeID;
	static long expenseReqID;
	static long approverEmployeeID;
	static String recepientemail;
	static String date;
	static String approve;
	static String reject;

	public ExpenseStatusService() {
	}

	public ExpenseStatusService(long employeeID, long expenseReqID, String recepientemail) {
		super();
		this.employeeID = employeeID;
		this.expenseReqID = expenseReqID;
		this.recepientemail = recepientemail;
	}

	public static void sendMail(EmailParameters emailparamobj, int expReqId) throws Exception {
		Properties properties = new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		final String myAccountEmail = "accolitespringau2020@gmail.com";
		final String password = "accolitespringau";

		Session session = Session.getInstance(properties, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			};
		});

		Message message = prepareMessage(session, myAccountEmail, emailparamobj, expReqId);
		Transport.send(message);
	}

	private static Message prepareMessage(Session session, String myAccountEmail, EmailParameters emailparamobj,
			int expReqId) {
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccountEmail));
			System.out.println(emailparamobj.getApproveremail());
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(emailparamobj.getApproveremail()));
			message.setSubject("Approval Request for Reimbursement");
			message.setContent(makeHtml(emailparamobj, expReqId), "text/html");

			Multipart multipart = new MimeMultipart();
			int length = 2;
			for (int i = 1; i <= length; i++) {
				DataSource source = new FileDataSource(
						"/media/aparna879/Technical volume/Accolite/SPRINGAU-PROJECT/BackEnd Code To Push/reimburseit/src/main/resources/invoice"
								+ i + ".pdf");
				BodyPart attachmentBodyPart = new MimeBodyPart();
				attachmentBodyPart.setDataHandler(new DataHandler(source));
				attachmentBodyPart.setFileName("invoice" + i + ".pdf");
				multipart.addBodyPart(attachmentBodyPart);
				multipart.addBodyPart(attachmentBodyPart);
			}

			BodyPart htmlBodyPart = new MimeBodyPart();
			htmlBodyPart.setContent(makeHtml(emailparamobj, expReqId), "text/html");
			multipart.addBodyPart(htmlBodyPart);
			message.setContent(multipart);
			return message;
		} catch (Exception e) {
			e.getLocalizedMessage();
		}
		return null;
	}

	static String makeHtml(EmailParameters emailparamobj, int expReqId) {
		String requestorname = emailparamobj.getRequestername();
		String requestorrole = emailparamobj.getRequesterrole();
		String name = emailparamobj.getEmpname();
		String category = emailparamobj.getExpensecategory();
		Date date = emailparamobj.getCurrentdate();
		double total = emailparamobj.getTotalcost();

		approve = "http://localhost:8080/reimburseit/approval/accept?ExpReqId=" + expReqId + "&ApproverEmail="
				+ emailparamobj.getApproveremail() + "&ExpStatus=Approved";
		reject = "http://localhost:8080/reimburseit/approval/reject?ExpReqId=" + expReqId + "&ApproverEmail="
				+ emailparamobj.getApproveremail() + "&ExpStatus=Rejected";

		return "<h1>Approved By: " + requestorname + "(" + requestorrole + ")</h1>" + "<br/>"
				+ "<p>Request From Employee : <b>" + name + "</b></p>" + "<p>Category of Expense : <b>" + category
				+ "</b></p>" + "<p>Request Generated on : <b>" + date + "</b></p>" + "<p>Total Reimbursement Cost: <b>"
				+ total + "</b></p>" + "<br/>" + "<button type=\"button\">  <a  href=" + approve
				+ "/>    Approve  </button>\n" + "<button type=\"button\">  <a  href=" + reject
				+ "/>    Reject  </button>\n" + "<br/>"
				+ "<a href=\"http://localhost:4200/pending/view\">For more details</a>";
	}
	//http://localhost:8080/reimburseit/viewexpenses/view?empId=" + emailparamobj.getEmpid()
	public String updateacceptstatus(int expReqId, String approveremail, String expstatus) throws Exception {
		EmailParameters emailparamobj = expensestatusdaoimplement.updateacceptstatus(expReqId, approveremail,
				expstatus);

		if (emailparamobj.getEmpid() == 0) {
			return "Accepted";
		} else {
			AddExpenseService obj = new AddExpenseService(emailparamobj.getEmpid(), expReqId,
					emailparamobj.getApproveremail());
			sendMail(emailparamobj, expReqId);
			return "Accepted";
		}
	}

	public String updaterejectstatus(int expReqId, String approveremail, String expstatus) {
		return expensestatusdaoimplement.updaterejectstatus(expReqId, approveremail, expstatus);
	}

	public String ftacceptservice(int expReqId) {
		return expensestatusdaoimplement.ftacceptdao(expReqId);
	}

	public String ftrejectservice(int expReqId) {
		return expensestatusdaoimplement.ftrejectdao(expReqId);
	}
}
