package com.shupoha.model;

import org.springframework.stereotype.Component;

@Component
public class FileDto {

	//private String uuid; // unique 한 파일 이름을 만들기 위한 속성
	private String fileName; // 실제 파일 이름
	private String contentType;

	public FileDto() {
	}

	public FileDto( String fileName, String contentType) {
	//(String uuid, String fileName, String contentType) 
	
		this.fileName = fileName;
		this.contentType = contentType;
		System.out.println(contentType);
	}


	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	
}
