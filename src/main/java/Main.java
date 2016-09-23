import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

public class Main {

  public static void main(String[] args) {

	 String port =  System.getenv("PORT");
	 if (port == null || port.isEmpty())
		 port = "5000";
	  
    port(Integer.valueOf(port));
    staticFileLocation("/public");

    get("/", (request, response) -> "PNDF-REST");
    
    get("/percursoSite/:data", (request, response) -> {
        return new IntegradorSitePNDF().obterPercurso(request.params(":data"));
    });
    
    get("/processarPercursoDia/:data", (request, response) -> {
        return new ProcessarPercurso().processarPercursoDia(new Util().converteDataPercurso(request.params(":data")));
    });
    
    get("/processarPercursoDia", (request, response) -> {
        return new ProcessarPercurso().processarPercursoDia(null);
    });
    
    get("/percurso/:id", (request, response) -> {
        return new ProcessarPercurso().obterPercursoJSON(new Long(request.params(":id")));
    });
    
    get("/percursos", (request, response) -> {
        return new ProcessarPercurso().obterPercursosJSON();
    });

  }

}
