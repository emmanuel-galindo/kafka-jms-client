import java.util.Date;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.InitialContext;

public class Producer {
    public static void main(String[] args) throws JMSException, NamingException {
        Context ctx = new InitialContext();
        ConnectionFactory connectionFactory = (ConnectionFactory)ctx.lookup("ConnectionFactory");
        Connection connection = connectionFactory.createConnection();
        System.out.println("Connected with " + connection.toString());
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue queue = (Queue)ctx.lookup("TestTopic");
        System.out.println("Queue " + queue.toString());

        MessageProducer producer = session.createProducer(queue);
        System.out.println("Producer " + producer.toString());
        TextMessage message = session.createTextMessage();
        message.setText(new Date().toString() + " - Text message");
        System.out.println("Message " + message.toString());
        producer.send(message);
        System.out.println("Message sent");
    }
}

