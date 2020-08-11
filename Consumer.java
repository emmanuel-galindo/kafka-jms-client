import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.InitialContext;

public class Consumer {
    public static void main(String[] args) throws JMSException, NamingException {
        Context ctx = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory)ctx.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        System.out.println("Connected with " + connection.toString());
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue)ctx.lookup("TestTopic");
        System.out.println("Queue " + queue.toString());

        MessageConsumer consumer = session.createConsumer(queue);
        System.out.println("Consumer " + consumer.toString());
        while(true) {
            try {
                TextMessage rmessage = (TextMessage) consumer.receive();
                System.out.println("Message received " + rmessage.getText());
            }
            catch (Exception e) {
                System.out.println("Error getting the message. Continuing. Error is: " + e.getMessage());
            }
        }
    }
}

