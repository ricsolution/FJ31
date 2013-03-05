package br.com.caelum.loja.session;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Session;

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
	public void enviaMensagem(List<Livro> livros) throws JMSException {
		// TODO Auto-generated method stub
		//System.out.println("MSG: " + livros.toString());
		
		//cria sessao
		
		Connection conexao = this.fabrica.createConnection("jms","caelum");
		Session sessao = conexao.createSession(false, Session.AUTO_ACKNOWLEDGE);
		
		//Cria wrapper do JAXB
		Livros wrapper = new Livros();
		wrapper.adicionaLivros(livros);
		
		//Mensagem JMS
		
		
		
	}
	

}
