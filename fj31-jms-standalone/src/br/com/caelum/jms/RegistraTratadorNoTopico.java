package br.com.caelum.jms;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class RegistraTratadorNoTopico {
	public static void main(String[] args) throws NamingException, JMSException {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "jms");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "caelum");

		InitialContext ic = new InitialContext(jndiProperties);

		TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) ic
				.lookup("jms/RemoteConnectionFactory");

		TopicConnection topicConnection = topicConnectionFactory
				.createTopicConnection("jms", "caelum");

		TopicSession topicSession = topicConnection.createTopicSession(false,
				Session.AUTO_ACKNOWLEDGE);

		Topic topic = (Topic) ic.lookup("jms/topic/loja");

		TopicSubscriber topicSubscriber = topicSession.createSubscriber(topic);

		topicSubscriber.setMessageListener(new TratadorDeMensagem());

		topicConnection.start();

		Scanner teclado = new Scanner(System.in);

		teclado.nextLine();

		topicConnection.close();

	}

}
