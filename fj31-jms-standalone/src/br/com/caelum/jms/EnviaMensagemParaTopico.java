package br.com.caelum.jms;

import java.util.Properties;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class EnviaMensagemParaTopico {

	public static void main(String[] args) throws NamingException, JMSException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "jms");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "caelum");

		InitialContext ic = new InitialContext(jndiProperties);

		TopicConnectionFactory topiConnectionFactory = (TopicConnectionFactory) ic
				.lookup("jms/RemoteConnectionFactory");

		TopicConnection topicConnection = topiConnectionFactory
				.createTopicConnection("jms", "caelum");

		TopicSession topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		TextMessage textMessage = topicSession.createTextMessage();

		textMessage.setText("Esse é um topico escroto HUAhUHU!");

		Topic topic = (Topic) ic.lookup("jms/topic/loja");

		TopicPublisher publisher = topicSession.createPublisher(topic);
		publisher.publish(textMessage);
		topicConnection.close();
	}

}
