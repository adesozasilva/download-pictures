package br.com.adesozasilva.downloadpictures;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PegarFotos {

	private static final String pathToSave = "C:\\Users\\asouzsil\\IdeaProjects\\download-pictures\\src\\main\\resources\\pictures";

	public static void get(String url, String fileName) throws Exception {
		URL urlObj = new URL(url);                                    
		HttpURLConnection  httpConnection = (HttpURLConnection)urlObj.openConnection();
		httpConnection.setRequestMethod("GET");
		InputStream inputStream = httpConnection.getInputStream();
		OutputStream outputStream = null;
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			criaSenaoExisteApasta(pathToSave);
			
			File file = new File(pathToSave + File.separator + fileName);
			outputStream = new FileOutputStream(file);
			System.out.println(file.getAbsolutePath());
			while ((read = inputStream.read(bytes)) != -1) {
				outputStream.write(bytes, 0, read);
			}
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (outputStream != null) {
					outputStream.close();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private static void criaSenaoExisteApasta(String pathToSave) {
		File file = new File(pathToSave);
		if(!file.exists()) file.mkdir();
	}


}
