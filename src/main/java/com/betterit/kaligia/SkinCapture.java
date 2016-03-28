/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author nayar
 *
 */
public class SkinCapture {
	int lumosity =0;
	int red =0;
	int green=0;
	int blue=0;
	String imageFile;
	public int getLumosity() {
		return lumosity;
	}
	public void setLumosity(int lumosity) {
		this.lumosity = lumosity;
	}
	public int getRed() {
		return red;
	}
	public void setRed(int red) {
		this.red = red;
	}
	public int getGreen() {
		return green;
	}
	public void setGreen(int green) {
		this.green = green;
	}
	public int getBlue() {
		return blue;
	}
	public void setBlue(int blue) {
		this.blue = blue;
	}
	public String getImageFile() {
		return imageFile;
	}
	public void setImageFile(String imageFile) {
		this.imageFile = imageFile;
	}
	@Override
	public String toString() {
		return "SkinCapture [lumosity=" + lumosity + ", red=" + red + ", green=" + green + ", blue=" + blue + ", "
				+ (imageFile != null ? "imageFile=" + imageFile : "") + "]";
	}
	
}
