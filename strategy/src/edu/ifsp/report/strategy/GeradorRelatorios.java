package edu.ifsp.report.strategy;

import java.util.List;

import edu.ifsp.report.Formato;
import edu.ifsp.report.Relatorio;

public class GeradorRelatorios {
	public static void main(String[] args) {
		
		Relatorio relatorio = new Relatorio();
		//relatorio.imprimir(new FmtTexto());
		relatorio.imprimir(new Formato() {
			
			@Override
			public void imprimir(List<String> clientes) {
				// CSV
				for (String c : clientes) {
					System.out.println(c + ";");
				}
			}
		});
		
	}
}
