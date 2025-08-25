package edu.ifsp.report.strategy;

import java.util.List;

import edu.ifsp.report.Formato;

public class FmtTexto implements Formato {

	@Override
	public void imprimir(List<String> clientes) {
		for (String c : clientes) {
			System.out.println("- " + c);
		}
	}

}
