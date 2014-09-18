package com.learningandroid.model;


public enum TextSize {
	SMALL(android.R.style.TextAppearance_Small_Inverse), 
	MEDIUM(android.R.style.TextAppearance_Medium_Inverse), 
	LARGE(android.R.style.TextAppearance_Large_Inverse);
	
	private TextSize(int size) {
		this.size = size;
	}
	
	private int size;
	
	public int size(){
		return this.size;
	}
}
