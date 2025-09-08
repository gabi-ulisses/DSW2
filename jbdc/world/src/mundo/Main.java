package mundo;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		PaisDAO dao = new PaisDAO();
		
		int ano = 1900;
		
		List<Pais> lista = dao.buscarPorAno(ano);
		System.out.println("Países: ");
		
		for(Pais p: lista) {
			System.out.println("\n " + p);
		}
	}
}
