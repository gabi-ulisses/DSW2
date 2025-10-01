package edu.ifsp.loja.web.produto;

import java.util.ArrayList;
import java.util.List;

public class ProdutoValidator {
	public List<String> validate(String descricao, String preco) {
		List<String> errors = new ArrayList<>();
		
		if (descricao == null || descricao.isBlank()) {
			errors.add("A descrição é obrigatória.");
		}
		
		if (preco == null || preco.isBlank()) {
			errors.add("O preço é obrigatório.");
		} else {
			try {
				double precoDouble = Double.parseDouble(preco);
				if (precoDouble < 0) {
					errors.add("O preço deve ser maior do que zero.");
				}
			} catch (NumberFormatException e) {
				errors.add("Preço inválido.");
			}
		}

		return errors;
	}
}
