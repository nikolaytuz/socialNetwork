package com.example.socialNetwork.controller;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

	@Value("${upload.path}")
	private String path;

	@PostMapping("/uploudhoto")
	public Map<String, Object> handleFileUpload(@RequestParam("file") MultipartFile file ) throws IOException {
		Map<String, Object> model = new LinkedHashMap<>();
		if (file != null) {
			File uploadFolder = new File(path);
			if (!uploadFolder.exists()) {
				uploadFolder.mkdir();
			}
			String uuidFile = UUID.randomUUID().toString();
			String nameFile = uuidFile + "-" + file.getOriginalFilename();
			file.transferTo(new File(uploadFolder+"/"+nameFile));
			
			model.put("url", "/temp/"+nameFile);
		}else {
			model.put("url", false);
		}
		return model;

	}
}
