import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnection;
import com.ibm.mq.jms.MQQueueConnectionFactory;
import com.ibm.mq.jms.MQQueueReceiver;
import com.ibm.mq.jms.MQQueueSender;
import com.ibm.mq.jms.MQQueueSession;

public class MqStub1 {

	public static void main(String[] args) {
		try {
			MQQueueConnection mqConn;
			MQQueueConnectionFactory mqCF;
		    final MQQueueSession mqQSession;
		    MQQueue mqIn;
		    MQQueueReceiver mqReceiver;
		    MQQueue mqOut;
		    MQQueueSender mqSender;
		
		    mqCF = new MQQueueConnectionFactory();
	 	    mqCF.setHostName("localhost");
		
		    mqCF.setPort(1414);
		
		    mqCF.setQueueManager("MQtester");
		    mqCF.setChannel("SYSTEM.DEF.SVRCONN");
		
		    mqConn = (MQQueueConnection) mqCF.createQueueConnection();
		    mqQSession = (MQQueueSession) mqConn.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
		
		    mqIn = (MQQueue) mqQSession.createQueue("Mq.IN"); // входная
		    mqReceiver = (MQQueueReceiver) mqQSession.createReceiver (mqIn);
		    
		    mqOut = (MQQueue) mqQSession.createQueue("Mq.OUT"); // выходная
		    mqSender = (MQQueueSender) mqQSession.createSender (mqOut);


		    javax.jms.MessageListener Listener = new javax.jms.MessageListener() {
		    	@Override
		    	public void onMessage(Message msg) {
				    System.out.println("Got message!");
				    if (msg instanceof TextMessage)
				    {
					    try {
						    TextMessage tMsg = (TextMessage) msg;
						    String msgText = tMsg.getText();
						    System.out.println(msgText);
					    } catch (JMSException e) {
						    e.printStackTrace();
					    }
				    }
			    }
		     
		    	javax.jms.TextMessage msg = mqQSession.createTextMessage("Hallo,world!");
		    	public void send(Message msg) {
		    		mqSender.send(msg);
		    		try {
					    TextMessage tMsg = (TextMessage) msg;
					    String msgText = tMsg.getText();	
		    	} catch (JMSException e) {
				    e.printStackTrace();
		    		
		    	}
		       };
		    
		
		mqReceiver.setMessageListener(Listener);
	    mqConn.start();
	    System.out.println("Stub Started.");
		
		
		    }  catch (JMSException e) {
		// TODO Auto-generated catch block
		    e.printStackTrace();
	    }
	    try {
	    	Thread.sleep(60000);
	    } catch (InterruptedException e) {
		// TODO Auto-generated catch block
		    e.printStackTrace();
	    }  
      }
	}
}
