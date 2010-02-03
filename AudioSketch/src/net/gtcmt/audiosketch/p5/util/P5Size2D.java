package net.gtcmt.audiosketch.p5.util;

public class P5Size2D {

	private int width;
	private int height;
	
	public P5Size2D(int width, int height){
		this.width = width;
		this.height = height;
	}

	public void setSize(int width, int height){
		this.width = width;
		this.height = height;
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
