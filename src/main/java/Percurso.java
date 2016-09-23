import java.util.Date;

public class Percurso {

	private Long id;
	
	private String titulo;

	private String nome;

	private String distancia;

	private String percurso;

	private String diaSemana;

	private String texto;

	private Date data;
	
	private String mapa;

	public Percurso() {

	}

	

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getMapa() {
		return mapa;
	}



	public void setMapa(String mapa) {
		this.mapa = mapa;
	}



	public Date getData() {
		return data;
	}



	public void setData(Date data) {
		this.data = data;
	}



	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getPercurso() {
		return percurso;
	}

	public void setPercurso(String percurso) {
		this.percurso = percurso;
	}

	public String getDiaSemana() {
		return diaSemana;
	}

	public void setDiaSemana(String diaSemana) {
		this.diaSemana = diaSemana;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
