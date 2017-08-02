package com.string.tetris;

import java.io.Serializable;

public class Cell implements Cloneable,Serializable{
	
	private static final long serialVersionUID = 1L;

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	protected int x,y;
	protected int image;
	
	Cell(int x,int y){
		this.x = x;
		this.y = y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}

}
