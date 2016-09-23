import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.format.TextStyle;
import java.util.Locale;

import org.joda.time.LocalDate;

import spark.utils.IOUtils;

import com.fasterxml.jackson.databind.ObjectMapper;


public class IntegradorSitePNDF {


	
	public String obterPercurso(String data) throws Exception
	{
			String url = "http://pedalnoturnodf.com.br/pedaldehoje/"+data+".txt";
		

			BufferedReader in = new BufferedReader(
			        new InputStreamReader(HTTPDownloadUtility.downloadFile(url)));
			
			StringWriter writer = new StringWriter();
			IOUtils.copy(in, writer);
			String outputString = writer.toString();
			ObjectMapper mapper = new ObjectMapper();
			in.close();	
			return 	mapper.writeValueAsString(this.parse(outputString,data));
			
		

		
	}
	
	
	private Percurso parse(String textoOriginal, String data) throws ParseException
	{
		Percurso retorno = new Percurso();
		String primeiraLinha = textoOriginal.split("\n")[0];
		int d = new LocalDate(new SimpleDateFormat("ddMMyy").parse(data)).getDayOfWeek();
		
		retorno.setTitulo(primeiraLinha.trim());
		retorno.setNome(primeiraLinha.split("-")[0].trim());
		retorno.setPercurso(primeiraLinha.split("\\)")[1].trim());
		retorno.setDistancia(primeiraLinha.split("-")[1].substring(0, 5).trim());
		retorno.setDiaSemana(DayOfWeek.of(d).getDisplayName(TextStyle.FULL, Locale.ENGLISH));
		retorno.setData(new SimpleDateFormat("ddMMyy").parse(data));
		try {
			retorno.setMapa("http:"+textoOriginal.split("http:")[1].split("\r")[0].trim());
		} catch (Exception e) {
			
		}
		retorno.setTexto(textoOriginal);
		
		return retorno;
	}
}
