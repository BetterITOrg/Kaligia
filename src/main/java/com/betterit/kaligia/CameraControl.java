package com.betterit.kaligia;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamDiscoveryEvent;
import com.github.sarxos.webcam.WebcamDiscoveryListener;
import com.github.sarxos.webcam.WebcamResolution;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;

public class CameraControl{
	
	private static final Logger log = LoggerFactory.getLogger(CameraControl.class);
	// Run number 
	public static int run_number=222;
	
	// These values will be extracted from the image processing method
	static int x1=677,x2=790,y1=302,y2=353;
	
	
	static int x1ref=627;
	static int x2ref=846;
	static int y1ref=413;
	static int y2ref=547;

	static double r_grey=128.0,g_grey=128.0,b_grey=128.0;
	
	static double r_coef=1.0,g_coef=1.0,b_coef=1.0;
	
	static Webcam webcam1 ;
	public CameraControl() {
			String webcamName;
			
			try {
				for (Webcam webcam : Webcam.getWebcams()) {
			
				webcamName=webcam.getName();
			
				Dimension[] nonStandardResolutions = new Dimension[] {
						WebcamResolution.PAL.getSize(),
						WebcamResolution.HD720.getSize()
						};
				// get default webcam and open it
				// webcam = Webcam.getDefault();
				
				webcam.setCustomViewSizes(nonStandardResolutions);
				webcam.setViewSize(WebcamResolution.HD720.getSize());
				
String word = "Endoscope";

Boolean found;

found = webcamName.contains(word);				
				if(found){

					
					log.info("Webcam detected: " + webcamName);
					webcam1=webcam;
					webcam.open();
					
				}
			}
				
			} catch (IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Please connect the camera");
				throw e;
				
			}
			catch (com.github.sarxos.webcam.WebcamLockException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Camera is being used by another app");
				
			}
		}
	public SkinCapture capture(String uniqFileName){
		
			int i=1; 
			int red=0,green=0,blue=0; 
			int lum=0;
			int red_grey=0,green_grey=0,blue_grey=0;
			SkinCapture retObj = new SkinCapture();
			String imageFname = new String();
				// get image
			try{
				BufferedImage image = webcam1.getImage();

				java.util.Date date= new java.util.Date();
				Timestamp currentTimestamp= new Timestamp(date.getTime());
				String STime = new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss").format(currentTimestamp);
				imageFname = new String(uniqFileName+"."+STime+".jpg");
				log.info("image file is : " + imageFname);
				
				int rref=0,gref=0,bref=0; int r1ref=0,g1ref=0,b1ref=0; int pixelref;
				for (int c=y1ref;c<=y2ref;c++)
				{
					for (int a=x1ref;a<=x2ref;a++){
				
				pixelref = image.getRGB(a, c);
				rref = (pixelref >> 16) & 0xff;
			    gref = (pixelref >> 8) & 0xff;
			    bref = (pixelref) & 0xff;
			    r1ref+=rref;
			    g1ref+=gref;
			    b1ref+=bref;
					}
					}
				red_grey=r1ref/((x2ref-x1ref)*(y2ref-y1ref));
				green_grey=g1ref/((x2ref-x1ref)*(y2ref-y1ref));
				blue_grey=b1ref/((x2ref-x1ref)*(y2ref-y1ref));
				//System.out.println("RGBref: "  + red_grey + ", " + green_grey + ", " + blue_grey);
				
				r_coef=(double)(r_grey/red_grey);
				g_coef=(double)(g_grey/green_grey);
				b_coef=(double)(b_grey/blue_grey);
				
				
				int r=0,g=0,b=0; int r1=0,g1=0,b1=0; int pixel;
				for (int c=y1;c<=y2;c++)
				{
					for (int a=x1;a<=x2;a++){
				
				pixel = image.getRGB(a, c);
				r = (pixel >> 16) & 0xff;
			    g = (pixel >> 8) & 0xff;
			    b = (pixel) & 0xff;
			    r1+=r;
			    g1+=g;
			    b1+=b;
					}
					}
				red=(int) ((r1/((x2-x1)*(y2-y1)))*r_coef);
				green=(int) ((g1/((x2-x1)*(y2-y1)))*g_coef);
				blue=(int) ((b1/((x2-x1)*(y2-y1)))*b_coef);
				//System.out.println("RGB: "  +red + ", " + green + ", " + blue);
				
				
				lum=(int) ((0.2126*red)+(0.7152*green)+(0.0722*blue));
				
				if ((lum>244)||(lum<35)){
					System.out.println("The values are out of bound. Please capture again");
				    
				}
				else{
					try {
						//ImageIO.write(image, "JPEG", new File("Run_number- "+run_number+" Time- "+S+".jpg"));
						ImageIO.write(image, "JPEG", new File(imageFname));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						log.info("error writing file for skin image " + imageFname);
					}
					
				}
				//System.out.println("argb: "  +r + ", " + g + ", " + b);
			    // Want to include the code to divide the values by total number of pixels
				webcam1.close();
				i++;
			}
			catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Please connect the camera");
				throw e;
				
			}
		
			retObj.setImageFile(imageFname);
			retObj.setLumosity(lum);
			retObj.setBlue(blue);
			retObj.setGreen(green);
			retObj.setRed(red);
		return retObj;
	}
			
	/*public static void main(String[] args) throws Throwable {
				new CameraControl();
				Thread.sleep(2400);
				int result[]=capture();  // This function should be called when 'Capture' button is pressed
				System.out.println("Lum:"+result[0] +" R:"+ result[1]+" G:"+ result[2]+" B:"+ result[3]);
				System.out.println("Bye!");
			}*/
			
		}


