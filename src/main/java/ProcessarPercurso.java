import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.heroku.sdk.jdbc.DatabaseUrl;


public class ProcessarPercurso {

	public static void main(String[] args) throws Exception {
		
	}
	
	/**
	 * Verifica se o percurso ja existe na base de dados e caso contrario
	 * baixa do site PNDF e armazena em banco de dados.
	 * @param data TODO
	 * @return
	 * @throws Exception
	 */
	public String processarPercursoDia(Date data) throws Exception
	{
		Date horarioON = data;
		
		if(horarioON == null)
			horarioON = new Util().obterHorarioON();
		
		if(new LocalDate(horarioON).getDayOfWeek() == DateTimeConstants.SATURDAY || new LocalDate(horarioON).getDayOfWeek() == DateTimeConstants.SUNDAY)
			return "HOJE NAO E DIA DE PEDAL";
		
		Percurso percurso = this.obterPercursoDia(horarioON);
		
		if(percurso != null && percurso.getTitulo() != null)
			return "PERCURSO JA CADASTRADO";
			
		try {
			percurso = new IntegradorSitePNDF().obterPercursoSite(new Util().converteDataPercurso(horarioON));
		} catch (Exception e) {
			return "ERRO AO OBTER PERCURSO E PARSER - Error Message: " + e.getMessage();
		}
		
		this.inserirPercurso(percurso);
		
		ObjectMapper mapper = new ObjectMapper();
		
		return 	mapper.writeValueAsString(percurso);
		
		
		
	}
	
	public Percurso inserirPercurso(Percurso percurso)
	{
		
		Connection connection = null;
		  
		try {
			connection = DatabaseUrl.extract().getConnection();
			
			
			String insertTableSQL = "INSERT INTO percurso(titulo, nome, distancia, percurso, diasemana, mapa,data,texto)"
					+ " VALUES"
					+ "(?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertTableSQL);
			
			preparedStatement.setString(1, percurso.getTitulo());
			preparedStatement.setString(2, percurso.getNome());
			preparedStatement.setString(3, percurso.getDistancia());
			preparedStatement.setString(4, percurso.getPercurso());
			preparedStatement.setString(5, percurso.getDiaSemana());
			preparedStatement.setString(6, percurso.getMapa());
			preparedStatement.setDate(7, new java.sql.Date(percurso.getData().getTime()));
			preparedStatement.setString(8, percurso.getTexto());
			
			// execute insert SQL stetement
			preparedStatement.executeUpdate();
			
		 } catch (Exception e) {
		       
		        System.out.println(e);
		      } finally {
		        if (connection != null) try{connection.close();} catch(SQLException e){}
		      }
		
		return percurso;
	}
	
	public Percurso obterPercursoDia(Date dia)
	{
		
		
		  Percurso retorno = null;
		  Connection connection = null;
	    
	      try {
	        connection = DatabaseUrl.extract().getConnection();

	        PreparedStatement stmt = connection.prepareStatement("select * from percurso where data = ?");
	        stmt.setDate(1, new java.sql.Date(dia.getTime()));
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        ArrayList<String> output = new ArrayList<String>();
	        retorno = new Percurso();
	        while (rs.next()) {
	          retorno.setTitulo(rs.getString("titulo"));
	        }
	        
	      } catch (Exception e) {
	       
	        System.out.println(e);
	      } finally {
	        if (connection != null) try{connection.close();} catch(SQLException e){}
	      }
	      
	      return retorno;
	}
	
	public List<Percurso> obterPercursos()
	{
		  List<Percurso> retorno = new ArrayList<Percurso>();
		  Percurso percurso = null;
		  Connection connection = null;
	    
	      try {
	        connection = DatabaseUrl.extract().getConnection();

	        PreparedStatement stmt = connection.prepareStatement("select * from percurso order by data DESC");
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        
	        
	        while (rs.next()) {
	        	percurso = new Percurso();
	        	percurso.setId(rs.getLong("id"));
	        	percurso.setTitulo(rs.getString("titulo"));
	        	percurso.setNome(rs.getString("nome"));
	        	percurso.setDistancia(rs.getString("distancia"));
	        	percurso.setPercurso(rs.getString("percurso"));
	        	percurso.setDiaSemana(rs.getString("diasemana"));
	        	percurso.setMapa(rs.getString("mapa"));
	        	percurso.setData(rs.getDate("data"));
	        	percurso.setTexto(rs.getString("texto"));
	        	retorno.add(percurso);
	        }
	        
	      } catch (Exception e) {
	       
	        System.out.println(e);
	      } finally {
	        if (connection != null) try{connection.close();} catch(SQLException e){}
	      }
	      
	      return retorno;
	}
	
	public Percurso obterPercurso(Long id)
	{
		
		  Percurso retorno = null;
		  Connection connection = null;
	    
	      try {
	        connection = DatabaseUrl.extract().getConnection();

	        PreparedStatement stmt = connection.prepareStatement("select * from percurso where id = ?");
	        stmt.setLong(1, id);
	        
	        ResultSet rs = stmt.executeQuery();
	        
	        if(!rs.isBeforeFirst())
	        	return null;
	        
	        retorno = new Percurso();
	        while (rs.next()) {
	        	retorno.setId(rs.getLong("id"));
	        	retorno.setTitulo(rs.getString("titulo"));
	        	retorno.setNome(rs.getString("nome"));
	        	retorno.setDistancia(rs.getString("distancia"));
	        	retorno.setPercurso(rs.getString("percurso"));
	        	retorno.setDiaSemana(rs.getString("diasemana"));
	        	retorno.setMapa(rs.getString("mapa"));
	        	retorno.setData(rs.getDate("data"));
	        	retorno.setTexto(rs.getString("texto"));
	        }
	        
	      } catch (Exception e) {
	       
	        System.out.println(e);
	      } finally {
	        if (connection != null) try{connection.close();} catch(SQLException e){}
	      }
	      
	      return retorno;
	}

	public String obterPercursoJSON(Long id) throws Exception
	{
		Percurso percurso = this.obterPercurso(id);
		ObjectMapper mapper = new ObjectMapper();
		
		return 	mapper.writeValueAsString(percurso);
	}

	public String obterPercursosJSON() throws JsonProcessingException
	{
		List<Percurso> percursos = this.obterPercursos();
		
		ObjectMapper mapper = new ObjectMapper();		
		return 	mapper.writeValueAsString(percursos);
	}
	
}
