package br.com.adesozasilva.downloadpicutures.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.adesozasilva.downloadpicutures.services.DownloadPicturesService;

@RestController
public class DownloadPicturesController {
	
	@Autowired
	private DownloadPicturesService downloadPicturesService;

	@GetMapping(value = "/downloadFile", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> downloadFile(@RequestParam("fileUrl") String fileUrl,
			@RequestParam(value = "pathToSave", required = false) String pathToSave) {
		
		if(pathToSave == null) pathToSave = System.getProperty("user.home") + File.separator + "Downloads";

		try {
			String filePath = downloadPicturesService.download(fileUrl, pathToSave);
			return new ResponseEntity<String>("Download file with success! The file was saved in " +  filePath, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<String>("Sorry, we could not fulfill your request! " + HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(), HttpStatus.INTERNAL_SERVER_ERROR);
		}


	}

}
