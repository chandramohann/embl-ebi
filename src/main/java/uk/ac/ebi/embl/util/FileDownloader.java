package uk.ac.ebi.embl.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.zip.GZIPInputStream;

public class FileDownloader {

	public void downloadFile(String url, String fileName) throws IOException {
		ReadableByteChannel rbc;
		URL website;
		FileOutputStream fos = null;
		try {
			website = new URL(url);
			rbc = Channels.newChannel(website.openStream());
			fos = new FileOutputStream(fileName);
			fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
			fos.close();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			fos.close();
		}
	}

	public void gunzipIt(String inFile,String outFile){
	     byte[] buffer = new byte[1024];
	     try{
	          GZIPInputStream gzis = 
	    		new GZIPInputStream(new FileInputStream(inFile));
	 
	    	 FileOutputStream out = 
	            new FileOutputStream(outFile);
	        int len;
	        while ((len = gzis.read(buffer)) > 0) {
	        	out.write(buffer, 0, len);
	        }
	        gzis.close();
	    	out.close();
	    	System.out.println("unzip Done");
	    	
	    }catch(IOException ex){
	       ex.printStackTrace();   
	    }
	}

}
