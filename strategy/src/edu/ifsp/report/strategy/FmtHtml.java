package edu.ifsp.report.strategy;

import java.util.List;

import edu.ifsp.report.Formato;

public class FmtHtml implements Formato {

	@Override
	public void imprimir(List<String> clientes) {
		System.out.println("<ul>");
		for (String c : clientes) {
			System.out.println("\t<li>" + c + "</li>");
		}
		System.out.println("</ul>");
	}

}
