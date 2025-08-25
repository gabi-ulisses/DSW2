package edu.ifsp.report.reference;

import java.util.List;

import edu.ifsp.report.Relatorio;

public class GeradorRelatorios {
	public static void main(String[] args) {
		Relatorio relatorio = new Relatorio();
		relatorio.imprimir(GeradorRelatorios::formatoHtml);
	}
	
	private static void formatoCSV(List<String> clientes) {
		for (String c : clientes) {
			System.out.println(c + ";");
		}
	}
	
	private static void formatoHtml(List<String> clientes) {
		System.out.println("<ul>");
		for (String c : clientes) {
			System.out.println("<li>" + c + "</li>");
		}
		System.out.println("</ul>");
	}
	
}
