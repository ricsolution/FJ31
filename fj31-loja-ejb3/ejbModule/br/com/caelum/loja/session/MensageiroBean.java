package br.com.caelum.loja.session;

import java.io.StringWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBException;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import br.com.caelum.loja.entity.Livro;
import br.com.caelum.loja.util.Livros;

@Stateless
@Local(Mensageiro.class)
public class MensageiroBean implements Mensageiro{

	@Resource(mappedName="java:/ConnectionFactory")
	private ConnectionFactory fabrica;


	//Injeta...
	@Resource(mappedName="java:/queue/loja")
	private Destination destination;




	@Override
	public void enviaMensagem(List<Livro> livros) {
		// TODO Auto-generated method stub
		System.out.println("MSG: " + livros.toString());

		try {
			//cria sessao

			Connection conexao = this.fabrica.createConnection("jms","caelum");
			Session sessao = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);

			//Cria wrapper do JAXB
			Livros wrapper = new Livros();
			wrapper.adicionaLivros(livros);
			System.out.println("LIVRO  ADICIONADO AO WRAPPER!!");

			//gerando xml
			Marshaller marshaller = JAXBContext.newInstance(Livros.class)
					.createMarshaller();
			StringWriter stringWriter = new StringWriter();
			marshaller.marshal(wrapper, stringWriter);

			// Mensagem jms
			TextMessage textMessage = sessao.createTextMessage(stringWriter
					.toString());

			// criando produtor de mensagems
			MessageProducer produtor = sessao.createProducer(this.destination);
			produtor.send(textMessage);

			// Fecha conexao
			produtor.close();
			conexao.close();

		} catch (JMSException e) {
			throw new EJBException(e);
		} catch (JAXBException e) {
			throw new EJBException(e);
		}


	}


}
