package edu.ifsp.report;

import java.util.List;

public class Relatorio {
	private List<String> clientes = List.of(
			"Bradesco", "Itaú", "Santander");
	
	public void imprimir(Formato fmt) {
		fmt.imprimir(clientes);
	}
}
