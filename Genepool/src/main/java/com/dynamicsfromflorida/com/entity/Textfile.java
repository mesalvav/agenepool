package com.dynamicsfromflorida.com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;


@Entity
@NamedQuery(name="find_all_textfiles", query="select t from Textfile t")
public class Textfile {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String filename;
	
	//@Column(nullable = false)
	//private String filepath;
	

	protected Textfile() {
	}

	public Textfile(String filename) {
		this.filename = filename;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Long getId() {
		return id;
	}

	@Override
	public String toString() {
		return String.format("Student[%s]", filename);
	}
/*
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	*/
}