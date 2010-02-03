package net.gtcmt.audiosketch.p5.util;

import java.io.Serializable;

public class P5Size2D implements Serializable {

	private static final long serialVersionUID = -8193166306461384786L;
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
