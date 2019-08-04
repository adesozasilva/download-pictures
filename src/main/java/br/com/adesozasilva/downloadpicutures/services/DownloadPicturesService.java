package br.com.adesozasilva.downloadpicutures.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.stereotype.Service;

@Service
public class DownloadPicturesService {

	public String download(String url, String pathToSave) throws Exception {
		String pathname = "";
		URL urlObj = new URL(url);                                    
		HttpURLConnection  httpConnection = (HttpURLConnection)urlObj.openConnection();
		httpConnection.setRequestMethod("GET");
		InputStream inputStream = httpConnection.getInputStream();
		OutputStream outputStream = null;
		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			createIfnotExistDir(pathToSave);

			pathname = pathToSave + File.separator + "download" + getFileExtension(url);
			File file = new File(pathname);
			outputStream = new FileOutputStream(file);
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
		return pathname;
	}
	
	private String getFileExtension(String fileName) {
		String extension =  "";
		if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			extension = "."+fileName.substring(fileName.lastIndexOf(".")+1);
		}
		return extension;
	}

	private static void createIfnotExistDir(String pathToSave) {
		File file = new File(pathToSave);
		if(!file.exists()) file.mkdir();
	}


}
