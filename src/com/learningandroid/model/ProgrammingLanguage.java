package com.learningandroid.model;

public class ProgrammingLanguage {
	private Long id;
	private String name;
	private String description;
	private boolean isChecked;
	
	public ProgrammingLanguage() {}
	
	public ProgrammingLanguage(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	public void toggleChecked(){
		this.isChecked = !isChecked;
	}
}
