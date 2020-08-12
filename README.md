# Kafka JMS Client to Confluent Cloud example
Kafka JMS Client consumer and producer implementation, using standalone Java, to connect to Confluent Kafka Cloud. 
This client, with minor adaptations from below doc, could be used for an on premise installation as well.
Reference: https://docs.confluent.io/current/clients/kafka-jms-client/index.html

Tutorial: https://emmanuel-galindo.github.io/en/2020/08/11/kafka-jms-client-to-confluent-cloud/

## Requirements
- Compile a fat jar. Reference: https://docs.confluent.io/current/clients/kafka-jms-client/installation.html#appendix-1-creating-a-shaded-fat-jar 
- Download Java JMS 1.1 library. Reference: https://repository.jboss.org/maven2/javax/jms/jms/1.1/
- A confluent cloud account and in there:
    - Create a new "TestTopic" topic
    - At API Access menu, issue an API Key and Secret to be used as username and password
    - In the Tools and client section
        - In clients tab, at Java section, copy the properties
        - In Confluent Platform Components, at Schema Registry, create a new API Key and Secret

## Installation
- Clone this repo locally
- Create a "lib/" folder at the root level, and copy the two jar files
- Copy the jndi.properties.example and configure with your configuration
- Compile the code

# Execution
- Execute first the producer, it will generate a message in TestTopic. It should print something similar to:
``` Connected with io.confluent.kafka.jms.KafkaConnection@448c8166
Queue KafkaQueue{topic=TestTopic}
Producer io.confluent.kafka.jms.KafkaMessageProducer@2c78324b
Message KafkaTextMessage{JMSCorrelationID=null, JMSDeliveryMode=2, JMSDestination=null, JMSExpiration=0, JMSMessageID=null, JMSPriority=4, JMSRedelivered=false, JMSReplyTo=null, JMSTimestamp=1597183969795, JMSType=null, Type=KafkaTextMessage}
Message sent
```

- Ctrl+c to exit the producer, and execute the consumer. It should show the message just sent:
```
Connected with io.confluent.kafka.jms.KafkaConnection@448c8166
Queue KafkaQueue{topic=TestTopic}
Consumer io.confluent.kafka.jms.KafkaMessageConsumer@34129c78
Message received Tue Aug 11 19:12:49 ART 2020 - Text message
```

