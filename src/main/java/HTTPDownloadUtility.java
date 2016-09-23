import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class HTTPDownloadUtility {
	 private static final int BUFFER_SIZE = 4096;
	 
	    /**
	     * Downloads a file from a URL
	     * @param fileURL HTTP URL of the file to be downloaded
	     * @param saveDir path of the directory to save the file
	     * @throws IOException
	     */
	    public static InputStream downloadFile(String fileURL)
	            throws IOException {
	        URL url = new URL(fileURL);
	        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	        int responseCode = httpConn.getResponseCode();
	 
	        // always check HTTP response code first
	        if (responseCode == HttpURLConnection.HTTP_OK) {
	            String fileName = "";
	           
	            String contentType = httpConn.getContentType();
	            int contentLength = httpConn.getContentLength();
	 
	           
	 
	            System.out.println("Content-Type = " + contentType);
	            System.out.println("Content-Length = " + contentLength);
	            System.out.println("fileName = " + fileName);
	 
	            // opens input stream from the HTTP connection
	            return httpConn.getInputStream();
	            
	 
	            
	            
	        } else {
	            System.out.println("No file to download. Server replied HTTP code: " + responseCode);
	        }
	        httpConn.disconnect();
	        
	        return null;
	    }
}
