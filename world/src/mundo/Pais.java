package mundo;

public class Pais {
	
	private String codigo; 
	private String nome;
	
	public Pais(String codigo, String nome) {
		setCodigo(codigo);
		setNome(nome);
	}
	
	public String getCodigo() {
		return codigo;
	}
	
	private void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	private void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return nome + " (" + codigo + ")";
	}
	
}
