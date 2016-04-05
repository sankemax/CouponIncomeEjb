package beans;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import core.ejb.Income;

@MessageDriven(
		activationConfig = { 
				@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
				@ActivationConfigProperty(propertyName = "destination", propertyValue = "queue/incomeQueue")
		})
public class IncomeMessage implements MessageListener {
	@EJB private IncomeService income;
	
    public IncomeMessage() {}
    
    public void onMessage(Message message) {
        try {
        	income.storeIncome((Income)((ObjectMessage)message).getObject());
		} catch (JMSException  e) {}
    }
}
