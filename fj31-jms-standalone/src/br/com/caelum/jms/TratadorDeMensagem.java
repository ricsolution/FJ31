package br.com.caelum.jms;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class TratadorDeMensagem implements MessageListener {

	@Override
	public void onMessage(Message msg) {
		// TODO Auto-generated method stub
		TextMessage textMessage = (TextMessage)msg;
		try {
			System.out.println("Tratador recebendo mensagem: "
					+ textMessage.getText());
		} catch (JMSException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


}
