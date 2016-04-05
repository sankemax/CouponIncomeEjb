package core.ejb;

import java.util.Collection;

import javax.jms.JMSException;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import beans.IncomeService;

public class BusinessDelegate {

	private InitialContext ctx;
	private QueueConnectionFactory qcf;
	private QueueConnection qc;
	private QueueSession session;
	private Queue queue;
	private QueueSender sender;
	private ObjectMessage message;

	public BusinessDelegate() throws NamingException, JMSException {
		ctx = new InitialContext();
		qcf = (QueueConnectionFactory) ctx.lookup("ConnectionFactory");
		qc = qcf.createQueueConnection();
		session = qc.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		queue = (Queue) ctx.lookup("queue/incomeQueue");
		sender = session.createSender(queue);
		message = session.createObjectMessage();
	}
	public void storeIncome(Income income) throws JMSException {
		message.setObject(income);
		sender.send(message);
	}

	public synchronized Collection<Income> viewAllIncome() throws NamingException {
		IncomeService isb = (IncomeService) ctx.lookup("IncomeBean");
		return isb.viewAllIncome();
	}

	public synchronized Collection<Income> viewCustomerIncome(String name) throws NamingException {
		IncomeService isb = (IncomeService) ctx.lookup("IncomeBean");
		return isb.viewIncomeByCustomer(name);
	}
	
	public synchronized Collection<Income> viewCompanyIncome(String name) throws NamingException {
		IncomeService isb = (IncomeService) ctx.lookup("IncomeBean");
		return isb.viewAllIncome();
	}
}
