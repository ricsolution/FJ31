package br.com.caelum.jms;

import java.util.Properties;
import java.util.Scanner;

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.naming.Context;
import javax.naming.InitialContext;

public class RegistraTratadorNaFila {
	public static void main(String[] args) throws Throwable {
		Properties jndiProperties = new Properties();
		jndiProperties.put(Context.SECURITY_PRINCIPAL, "jms");
		jndiProperties.put(Context.SECURITY_CREDENTIALS, "caelum");

		InitialContext ic = new InitialContext(jndiProperties);

		QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) ic
				.lookup("jms/RemoteConnectionFactory");
		QueueConnection queueConnection = queueConnectionFactory
				.createQueueConnection("jms", "caelum");

		QueueSession queueSession = queueConnection.createQueueSession(false,
				Session.AUTO_ACKNOWLEDGE);
		Queue queue = (Queue) ic.lookup("jms/queue/loja");

		QueueReceiver queueReceiver = queueSession.createReceiver(queue);

		queueReceiver.setMessageListener(new TratadorDeMensagem());

		queueConnection.start();

		Scanner teclado = new Scanner(System.in);

		System.out
		.println("Esperando as mensagens na fila JMS. Aperte ENTER para parar");
		teclado.nextLine();

		queueConnection.close();

	}
}
