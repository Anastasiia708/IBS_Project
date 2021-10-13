import javax.jms.JMSExeption;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueConnection;
import com.ibm.mq.MQQueueConnectionFactory;
import com.ibm.mq.MQQueueReceiver;
import com.ibm.mq.MQQueueSession;

public class MqStub {

	public static void main(String[] args) {
		try {
			MQQueueConnection mqConn;
			MQQueueConnectionFactory mqCF;
		    final MQQueueSession mqQSession;
		    MQQueue mqIn;
		    MQQueueReceiver mqReceiver;
		
		    mqCF = new MQQueueConnectionFactory();
	 	    mqCF.setHostName("localhost");
		
		    mqCF.set.port(1414);
		
		    mqCF.setQueueManager("MQtester");
		    mqCF.setChannel("SYSTEM.DEF.SVRCONN");
		
		    mqConn = (MQQueueConnection) mqCF.createQueueConnection();
		    mqSession = (MQQueueSession) mqConn.createQueueSession(true, Session.AUTO_ACKNOWLEDGE);
		
		    mqIn = (MQQueue) mqQSession.createQueue("Mq.IN"); // входная
		    mqReceiver = (MQQueueReceiver) mqQSession.createReceiver (mqIn);
		
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
		    };
		
		    mqReceiver.setMessageListener(Listener);
		    mqConn.start();
		    System.out.println("Stub Started.");
		
        } catch (JMSException e) {
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