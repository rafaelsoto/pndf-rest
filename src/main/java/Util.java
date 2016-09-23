import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Util {

	
	public Date obterHorarioON() throws Exception
	{
		String dateON = getHTML("http://www.horalegalbrasil.mct.on.br/RelogioServidor.php");
		
		return new Date(Long.valueOf(dateON)*1000);
		
	}
	
	public String getHTML(String urlToRead) throws Exception {
	      StringBuilder result = new StringBuilder();
	      URL url = new URL(urlToRead);
	      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	      conn.setRequestMethod("GET");
	      BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	      String line;
	      while ((line = rd.readLine()) != null) {
	         result.append(line);
	      }
	      rd.close();
	      return result.toString();
	   }
	
	
	public Date converteDataPercurso(String data) throws Exception
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		return dateFormat.parse(data);
	}
	
	public String converteDataPercurso(Date data) throws Exception
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat("ddMMyy");
		return dateFormat.format(data);
	}
}
