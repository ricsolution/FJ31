package br.com.caelum.cliente.programa;

import java.math.BigDecimal;
import java.util.List;

import br.com.caelum.correios.ws.CResultado;
import br.com.caelum.correios.ws.CServico;
import br.com.caelum.correios.ws.CalcPrecoPrazoWS;
import br.com.caelum.correios.ws.CalcPrecoPrazoWSSoap;

public class ConsumidorServicoCorreios {
	public static void main(String[] args) {
		CalcPrecoPrazoWSSoap servico = new CalcPrecoPrazoWS()
		.getCalcPrecoPrazoWSSoap();

		// 40010 Sedex, 41106 PAC
		Long inicio = System.currentTimeMillis();
		CResultado resultado = servico.calcPrecoPrazo("", "", "40010,41106",
				"04101300", "20040030", "20", 2, new BigDecimal(19),
				new BigDecimal(10), new BigDecimal(15), new BigDecimal(10),
				"s", BigDecimal.ZERO, "s");
		Long fim = System.currentTimeMillis();
		List<CServico> servicosPesquisados = resultado.getServicos()
				.getCServico();
		for (CServico s : servicosPesquisados) {
			System.out.println("Codigo: " + s.getCodigo());
			System.out.println("Valor: " + s.getValor());
			System.out.println("Tempo para consumo do WS correios em ms: "
					+ (fim - inicio));
		}

	}
}
