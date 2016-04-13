package com.betterit.kaligia;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Polygon;
import java.awt.Rectangle;
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

//import javafx.beans.value.ObservableValue;
//import javafx.concurrent.Task;

import ij.*;//new
import ij.process.*;//new
import ij.text.TextWindow;
import ij.gui.*;//new
import ij.io.Opener;
import ij.macro.Interpreter;
import ij.measure.*;//new
import ij.plugin.*;//new
import ij.plugin.filter.Analyzer;
import ij.plugin.filter.ThresholdToSelection;//new
import ij.plugin.frame.RoiManager;



public class CameraControl{
	
	static { /* works fine! ! */
	      System.setProperty("java.awt.headless", "false");
	      System.out.println(java.awt.GraphicsEnvironment.isHeadless());
	      /* ---> prints true */
	    }
	
	public static final Logger log = LoggerFactory.getLogger(CameraControl.class);
	// Run number 
	public static int run_number=222;
	
	public static int minHue = 111, minSat = 25, minBri = 185;//new
	public static int maxHue = 255, maxSat = 255, maxBri = 255;//new
	
	public static int minHue1 = 142, minSat1 = 130, minBri1 = 130;//new
	public static int maxHue1 = 190, maxSat1 = 255, maxBri1 = 255;//new
	
	public static float xref = 142, yref = 130; //new
	public static float xmar = 190, ymar = 255;
	
	static int xrefer=0,yrefer=0,x1refer=0,y1refer=0;
	static int xroi=0,yroi=0,x1roi=0,y1roi=0;
	static int marker=0,reference=0;
	
	public static int roiType;
	public static Polygon polygon;
	public static int beginningCount;
	public static Analyzer analyzer;
	public static Wand wand;
	public static int wandMode = Wand.LEGACY_MODE;
	public static boolean roiNeedsImage;
	public static ImageProcessor redirectIP;
	public static ImagePlus redirectImp;
	public static ImageProcessor drawIP;
	public static ResultsTable rt;
	private static int width,height;
	private static int measurements;
	private static Calibration calibration;
	private static ImagePlus outputImage;
	protected static boolean showResults,excludeEdgeParticles,showSizeDistribution,
	resetCounter,showProgress, recordStarts, displaySummary, floodFill,
	addToManager, inSituShow;
	private static RoiManager roiManager;
	
	public static final int INCLUDE_HOLES = 1024;
	private static ResultsTable staticResultsTable;

	
	static final int BYTE=0, SHORT=1, FLOAT=2, RGB=3;
	static final double DEFAULT_MIN_SIZE = 0.0;
	static final double DEFAULT_MAX_SIZE = Double.POSITIVE_INFINITY;
	
	private static double staticMinSize = 0.0;
	private static double staticMaxSize = DEFAULT_MAX_SIZE;
	private static boolean pixelUnits;
	
	private static double staticMinCircularity=0.0, staticMaxCircularity=1.0;
	
	private static double minSize=50000;
	private static double maxSize=Double.POSITIVE_INFINITY;
	private static double minCircularity=0.5, maxCircularity=1;
	private static int particleCount;
	private static int maxParticleCount ;
	private static int totalCount;
	private static int imageType, imageType2;
	
	private static int minX, maxX, minY, maxY;
	
	
	private static Rectangle r;
	private static ImageProcessor mask;
	private static double totalArea=1;
	private static PolygonFiller pf;
	private static FloodFiller ff;
	private static double fillColor=0;
	
	private static double level1, level2;
	
	
	
	
	// These values will be extracted from the image processing method
	static int x1=677,x2=790,y1=302,y2=353;
	


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
				webcam.close();
				webcam.setCustomViewSizes(nonStandardResolutions);
				webcam.setViewSize(WebcamResolution.HD720.getSize());
				
				
				String word = "Endoscope";

				Boolean found;

				found = webcamName.contains(word);				
				if(found){
					
					log.info("Webcam detected: " + webcamName);
					webcam1=webcam;
					webcam1.open();
					
				}
			}
				
			} catch (IndexOutOfBoundsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Please connect the camera");
				
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
			BufferedImage image = null;
			try{
				image = webcam1.getImage();
			}
			catch (NullPointerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				log.info("Please connect the camera");
				
				
			}
				webcam1.close();
				java.util.Date date= new java.util.Date();
				Timestamp currentTimestamp= new Timestamp(date.getTime());
				String STime = new SimpleDateFormat("yyyy-MM-dd HH.mm.ss").format(currentTimestamp);
				imageFname = new String(uniqFileName+"."+STime+".jpg");
				//additional code for image processing
				int snumPixels=0; byte[] hsSource,ssSource,bsSource; int counter=0; byte[] hsSource1,ssSource1,bsSource1;
				 
				 ImagePlus imp = new ImagePlus("image", image);
				 
				 
				 ImageProcessor ip = imp.getProcessor();
				 
				 
				 ImageProcessor mask = new ByteProcessor(image.getWidth(), image.getHeight());
					imp.setProperty("Mask", mask);
					
					ImageProcessor mask1 = new ByteProcessor(image.getWidth(), image.getHeight());
					imp.setProperty("Mask1", mask1);
				 
				 snumPixels = image.getWidth()*image.getHeight();
				 hsSource = new byte[snumPixels];
					ssSource = new byte[snumPixels];
					bsSource = new byte[snumPixels];
					int [] pixs = new int[snumPixels];
					//for hue,sat,brig
					
					hsSource1 = new byte[snumPixels];
					ssSource1 = new byte[snumPixels];
					bsSource1 = new byte[snumPixels];
					
					
					for (int j10=0; j10<image.getHeight(); j10++) {
						for (int i10=0; i10<image.getWidth(); i10++) {
							pixs[counter++] = ip.getPixel(i10+0, j10+0);
						}
					}
					
					
					
					
					ColorProcessor cp2 = new ColorProcessor(1, snumPixels, pixs);
					//getLab(cp2, hsSource,ssSource,bsSource);
					cp2.getHSB(hsSource,ssSource,bsSource);
					
					
					cp2.getHSB(hsSource1,ssSource1,bsSource1);
					
					
					ImageProcessor fillMaskIP = (ImageProcessor) imp.getProperty("Mask");
					if (fillMaskIP==null) return null;
					byte[] fillMask = (byte[])fillMaskIP.getPixels();
					byte fill = (byte)255;
					byte keep = (byte)0;

					for (int j = 0; j < snumPixels; j++){
						int ligh = hsSource[j]&0xff;
						int avalue = ssSource[j]&0xff;
						int bvalue = bsSource[j]&0xff;
						if (((ligh < minHue)||(ligh > maxHue)) || ((avalue < minSat)||(avalue > maxSat)) || ((bvalue < minBri)||(bvalue > maxBri)))
							fillMask[j] = keep;
						else
							fillMask[j] = fill;
					}
					ImageProcessor fillMaskIP1 = (ImageProcessor) imp.getProperty("Mask1");
					if (fillMaskIP1==null) return null;
					byte[] fillMask1 = (byte[])fillMaskIP1.getPixels();
					
					for (int j1 = 0; j1 < snumPixels; j1++){
						int hue = hsSource1[j1]&0xff;
						int sat = ssSource1[j1]&0xff;
						int bri = bsSource1[j1]&0xff;
						if (((hue < minHue1)||(hue > maxHue1)) || ((sat < minSat1)||(sat > maxSat1)) || ((bri < minBri1)||(bri > maxBri1)))
							fillMask1[j1] = keep;
						else
							fillMask1[j1] = fill;
					}
					
					
					
					if (ip==null) return null;
					
						int[] pixels = (int[])ip.getPixels();
						int fcolor = Prefs.blackBackground?0xffffffff:0xff000000;
						int bcolor = Prefs.blackBackground?0xff000000:0xffffffff;
						for (int i11=0; i11<snumPixels; i11++) {
							if (fillMask[i11]!=0)
								pixels[i11] = fcolor;
							else
								pixels[i11]= bcolor;
						
						}
						
						BufferedImage image1 = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_BYTE_BINARY);
			            
			            image1.setRGB(0,0,image1.getWidth(), image1.getHeight(),pixels,0,image1.getWidth());
			            
			           ImagePlus gray=new ImagePlus("in", image1);
			           
			            ImageConverter ic = new ImageConverter(gray);
			            ic.convertToGray8();
			            gray.updateImage();
			            
			            IJ.run("Clear Results");
			        	IJ.run(gray, "Make Binary", "");
			        	
			        	
			            ImageProcessor ih = gray.getProcessor();
			            
			            
			            
			           /* try {
							ImageIO.write(image1, "JPEG", new File("test"+".jpg"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
			            */
			            //IJ.save(gray, S+"1.tiff") ;
			            
			            int[] pixels1 = (int[])ip.getPixels();
						int fcolor1 = Prefs.blackBackground?0xffffffff:0xff000000;
						int bcolor1 = Prefs.blackBackground?0xff000000:0xffffffff;
						for (int i12=0; i12<snumPixels; i12++) {
							if (fillMask1[i12]!=0)
								pixels1[i12] = fcolor1;
							else
								pixels1[i12]= bcolor1;
						}
						
						
						if (imp.getBitDepth()==24 && !isThresholdedRGB(imp)) {
							//System.out.println("Particle Analyzer RGB images must be thresholded using\n"
							//+"Image>Adjust>Color Threshold.");
							
						}
						
						
						analyze(gray, ih);
						
						try{
							int cou=0;
							//System.out.println("particleCount " + particleCount);
							if (particleCount<2){
							
							float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
							float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
							//System.out.println("X and Y: " + x3[0]+"y:"+y3[0]);
							
							xref=x3[0]; yref=y3[0];
							}
							else{
								float[] area = rt.getColumn(ResultsTable.AREA);
								double max = area[0];
								for (int counter3 =0 ; counter3 < area.length; counter3++)
								{
								     if (area[counter3] > max)
								     {
								      max = area[counter3];
								      cou=counter3;
								     }
								     
								}
								float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
								float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
								//System.out.println("X and Y: " + x3[cou]+"y:"+y3[cou]);
								xref=x3[cou]; yref=y3[cou];
							}
					
						}
						catch (NullPointerException e) {
							try{
							
							IJ.run("Clear Results");
							particleCount=0;
							
							minSize=50000;
							minCircularity=0.1; maxCircularity=1;
							
							analyze(gray, ih);
							
							int cou=0;
							//System.out.println("particleCount " + particleCount);
							if (particleCount<2){
							
							float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
							float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
							//System.out.println("X and Y: " + x3[0]+"y:"+y3[0]);
							xref=x3[0]; yref=y3[0];
							}
							else{
								float[] area = rt.getColumn(ResultsTable.AREA);
								double max = area[0];
								for (int counter3 = 0; counter3 <= area.length; counter3++)
								{
								     if (area[counter3] > max)
								     {
								      max = area[counter3];
								      cou=counter3;
								     }
								     
								}
								float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
								float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
								//System.out.println("X and Y: " + x3[cou]+"y:"+y3[cou]);
								xref=x3[cou]; yref=y3[cou];
							}
							
							
							}
							catch (NullPointerException z) {
								reference=1;
								
							}
							}
						

						
						//new new start
						
						
						
						
						//
						
						
						
				
						
						// new new end
						IJ.run("Clear Results");
						particleCount=0;
					
						minCircularity=0.5; maxCircularity=1;
						
			            BufferedImage image2 = new BufferedImage(image.getWidth(), image.getHeight(),BufferedImage.TYPE_INT_RGB);
			            
			            image2.setRGB(0,0,image2.getWidth(), image2.getHeight(),pixels1,0,image2.getWidth());
			            
			            
			            ImagePlus gray1=new ImagePlus("in", image2);
				           
			            ImageConverter ic1 = new ImageConverter(gray1);
			            ic1.convertToGray8();
			            gray.updateAndDraw();
			            
			        	
			        	IJ.run(gray1, "Make Binary", "");
			        	
			        	minSize=900;
			        	maxSize=Double.POSITIVE_INFINITY;;
			            ImageProcessor ih1 = gray1.getProcessor();
			           
			            
			            //IJ.save(gray1, S+"hsb.tiff") ;
			            analyze(gray1, ih1);
			            
			            
			            try{
			            	int cou1=0;
							//System.out.println("particleCount " + particleCount);
							if (particleCount<2){
							
							float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
							float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
							//System.out.println("X and Y: " + x3[0]+"y:"+y3[0]);
							xmar=x3[0]; ymar=y3[0];
							}
							else{
								float[] area = rt.getColumn(ResultsTable.AREA);
								double max = area[0];
								for (int counter3 = 0; counter3 <= area.length; counter3++)
								{
								     if (area[counter3] > max)
								     {
								      max = area[counter3];
								      cou1=counter3;
								     }
								     
								}
								float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
								float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
								//System.out.println("X and Y: " + x3[cou1]+"y:"+y3[cou1]);
								xmar=x3[cou1]; ymar=y3[cou1];
							}
					
						}
						catch (NullPointerException e) {
							try{
							IJ.run("Clear Results");
							particleCount=0;
							
							
							minCircularity=0.22; maxCircularity=1;
							
							analyze(gray1, ih1);
							
							int cou1=0;
							//System.out.println("particleCount " + particleCount);
							if (particleCount<2){
							
							float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
							float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
							//System.out.println("X and Y: " + x3[0]+"y:"+y3[0]);
							xmar=x3[0]; ymar=y3[0];
							
							}
							else{
								float[] area = rt.getColumn(ResultsTable.AREA);
								double max = area[0];
								for (int counter3 = 0; counter3 <= area.length; counter3++)
								{
								     if (area[counter3] > max)
								     {
								      max = area[counter3];
								      cou1=counter3;
								     }
								     
								}
								float[] x3 = rt.getColumn(ResultsTable.X_CENTROID);
								float[] y3 = rt.getColumn(ResultsTable.Y_CENTROID);
								//System.out.println("X and Y: " + x3[cou1]+"y:"+y3[cou1]);
								xmar=x3[cou1]; ymar=y3[cou1];
							}
							
							}
							catch (NullPointerException x) {
								marker=1;
								
							}
							
						}
			            
			            if(reference>=1 && marker<=0){
			            	log.info("Reference undetected:Please check the camera position/reduce the light intensity and try again");
			            	//System.out.println("Reference undetected:Please check the camera position/reduce the light intensity and try again");
			             }
			            else if(reference<=0 && marker>=1){
			            	log.info("Marker undetected: Please check the camera position/reduce the light intensity and try again");
			            	//System.out.println("Marker undetected: Please check the camera position/reduce the light intensity and try again");
			             }
			            else if(reference>=1 && marker>=1){
			            	log.info("Please check the LED in the camera/Turn on the LED");
			            	//System.out.println("Please check the LED in the camera/Turn on the LED");
			             }
			            else{
			            reference=0;marker=0;
			            }
			            
			            
			           /* try {
							ImageIO.write(image2, "JPEG", new File("test1"+".jpg"));
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}*/
								
				    		
				    					   
				    		
				    		
							float x_diff=xref-xmar;
							float y_diff=yref-ymar;
							 
							float refmar_total=Math.abs(x_diff)+Math.abs(y_diff);
							
							float x_coef=x_diff/refmar_total;
							float y_coef=y_diff/refmar_total;
							 
				            // new ends
				    		double angle=x_coef*100;
				    		double angle1=y_coef*100;
				    		//System.out.println("xc:"+angle+" yc:"+angle1);
				    		//double angle=x_coef*90;
				    		//System.out.println("xc:"+(-angle+xmar)+" yc:"+(-angle1+ymar));
				            
				    		//System.out.println("x1:"+(-angle-25+xmar)+" y1:"+(-angle1-25+ymar));
				    		//System.out.println("x2:"+(-angle+25+xmar)+" y2:"+(-angle1+25+ymar));
				    		xroi=(int)(-angle-25+xmar); yroi=(int)(-angle1-25+ymar); x1roi=(int)(-angle+25+xmar);y1roi=(int)(-angle1+25+ymar);
				    		
				    	
				    		
				    		
				    		//System.out.println("x1:"+(+angle-35+xref)+" y1:"+(+angle1-35+yref));
				    		//System.out.println("x2:"+(+angle+25+xref)+" y2:"+(+angle1+35+yref));
				    		
				    		xrefer=(int)(+angle-35+xmar);yrefer=(int)(+angle1-35+ymar);x1refer=(int)(+angle+35+xmar);y1refer=(int)(+angle1+35+ymar);
				    		//end of image processing
				
				
				
				
				
				
				int rref=0,gref=0,bref=0; int r1ref=0,g1ref=0,b1ref=0; int pixelref;
				for (int c=yrefer;c<=y1refer;c++)
				{
					for (int a=xrefer;a<=x1refer;a++){
				
				pixelref = image.getRGB(a, c);
				rref = (pixelref >> 16) & 0xff;
			    gref = (pixelref >> 8) & 0xff;
			    bref = (pixelref) & 0xff;
			    r1ref+=rref;
			    g1ref+=gref;
			    b1ref+=bref;
					}
					}
				red_grey=r1ref/((x1refer-xrefer)*(y1refer-yrefer));
				green_grey=g1ref/((x1refer-xrefer)*(y1refer-yrefer));
				blue_grey=b1ref/((x1refer-xrefer)*(y1refer-yrefer));
				//System.out.println("RGBref: "  + red_grey + ", " + green_grey + ", " + blue_grey);
				
				r_coef=(double)(r_grey/red_grey);
				g_coef=(double)(g_grey/green_grey);
				b_coef=(double)(b_grey/blue_grey);
				
				
				int r=0,g=0,b=0; int r1=0,g1=0,b1=0; int pixel;
				for (int c1=yroi;c1<=y1roi;c1++)
				{
					for (int a1=xroi;a1<=x1roi;a1++){
				
				pixel = image.getRGB(a1, c1);
				r = (pixel >> 16) & 0xff;
			    g = (pixel >> 8) & 0xff;
			    b = (pixel) & 0xff;
			    r1+=r;
			    g1+=g;
			    b1+=b;
					}
					}
				red=(int) ((r1/((x1roi-xroi)*(y1roi-yroi)))*r_coef);
				green=(int) ((g1/((x1roi-xroi)*(y1roi-yroi)))*g_coef);
				blue=(int) ((b1/((x1roi-xroi)*(y1roi-yroi)))*b_coef);
				//System.out.println("RGB: "  +red + ", " + green + ", " + blue);
				
				
				lum=(int) ((0.2126*red)+(0.7152*green)+(0.0722*blue));
				
				if ((lum>244)||(lum<25)){
					log.info("The values are out of bound. Please capture again");
					//System.out.println("The values are out of bound. Please capture again");
				    
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
				
				i++;
			
		
			retObj.setImageFile(imageFname);
			retObj.setLumosity(lum);
			retObj.setBlue(blue);
			retObj.setGreen(green);
			retObj.setRed(red);
		return retObj;
	}
	public static void getLab(ImageProcessor ip, byte[] L, byte[] a, byte[] b) {
		ColorSpaceConverter converter = new ColorSpaceConverter();
		int[] pixels = (int[])ip.getPixels();
		for (int i=0; i<pixels.length; i++) {
			double[] values = converter.RGBtoLAB(pixels[i]);
			int L1 = (int) (values[0] * 2.55);
			int a1 = (int) (Math.floor((1.0625 * values[1] + 128) + 0.5));
			int b1 = (int) (Math.floor((1.0625 * values[2] + 128) + 0.5));
			L[i] = (byte)((int)(L1<0?0:(L1>255?255:L1)) & 0xff);
			a[i] = (byte)((int)(a1<0?0:(a1>255?255:a1)) & 0xff);
			b[i] = (byte)((int)(b1<0?0:(b1>255?255:b1)) & 0xff);
		}
	}
	public static void RGBtoLab() {
		ImagePlus imp = IJ.getImage();
		if (imp.getBitDepth()==24)
			imp.setProcessor(RGBtoLab(imp.getProcessor()));
	}
	
	private static ImageProcessor RGBtoLab(ImageProcessor ip) {
		int n = ip.getPixelCount();
		byte[] L = new byte[n];
		byte[] a = new byte[n];
		byte[] b = new byte[n];
		CameraControl.getLab(ip, L, a, b);
		ColorProcessor cp = new ColorProcessor(ip.getWidth(),ip.getHeight());
		cp.setRGB(L,a,b);
		return cp;
	}
	private static boolean isThresholdedRGB(ImagePlus imp) {
		Object obj = imp.getProperty("Mask");
		if (obj==null || !(obj instanceof ImageProcessor))
			return false;
		ImageProcessor mask2 = (ImageProcessor)obj;
		
		return mask2.getWidth()==imp.getWidth() && mask2.getHeight()==imp.getHeight();
	}
public static boolean analyze(ImagePlus imp, ImageProcessor ip) {
		
		showResults = true;
		excludeEdgeParticles = false;
		resetCounter = false;
		showProgress = false;
		floodFill = false;
		recordStarts = false;
		addToManager = false;
		
		
		if (staticResultsTable!=null) {
			rt = staticResultsTable;
			staticResultsTable = null;
			//showResultsWindow = false;
		}
		displaySummary = false ||  false;
		inSituShow = false;
		outputImage = null;
		ip.snapshot();
		ip.setProgressBar(null);
		if (Analyzer.isRedirectImage()) {
			redirectImp = Analyzer.getRedirectImage(imp);
			if (redirectImp==null) return false;
			int depth = redirectImp.getStackSize();
			if (depth>1 && depth==imp.getStackSize()) {
				ImageStack redirectStack = redirectImp.getStack();
				redirectIP = redirectStack.getProcessor(imp.getCurrentSlice());
			} else
				redirectIP = redirectImp.getProcessor();
		} else if (imp.getType()==ImagePlus.COLOR_RGB) {
			ImagePlus original = (ImagePlus)imp.getProperty("OriginalImage");
			if (original!=null && original.getWidth()==imp.getWidth() && original.getHeight()==imp.getHeight()) {
				redirectImp = original;
				redirectIP = original.getProcessor();
			}
		}
		if (!setThresholdLevels(imp, ip))
			return false;
		width = ip.getWidth();
		height = ip.getHeight();
		
		calibration = redirectImp!=null?redirectImp.getCalibration():imp.getCalibration();
		
		if (measurements==0)
			measurements = Analyzer.getMeasurements();
		measurements &= ~256;	 // ignore "Limit to Threshold"
		if (rt==null) {
			Frame table = WindowManager.getFrame("Results");
			if (!showResults && table!=null) {
				rt = new ResultsTable();
				if (resetCounter && table instanceof TextWindow) {
					IJ.run("Clear Results");
					((TextWindow)table).close();
					rt = Analyzer.getResultsTable();
				}
			} else
				rt = Analyzer.getResultsTable();
		}
		analyzer = new Analyzer(imp, measurements, rt);
		
		beginningCount = Analyzer.getCounter();

		byte[] pixels = null;
		if (ip instanceof ByteProcessor)
			pixels = (byte[])ip.getPixels();
		if (r==null) {
			r = ip.getRoi();
			mask = ip.getMask();
			
			
				if (mask!=null)
					totalArea = ImageStatistics.getStatistics(ip, 1, calibration).area;
				else
					
					totalArea = r.width*calibration.pixelWidth*r.height*calibration.pixelHeight;
			
			
		}
		
		minX=r.x; maxX=r.x+r.width; minY=r.y; maxY=r.y+r.height;
		
		if (r.width<width || r.height<height || mask!=null) {
			if (!eraseOutsideRoi(ip, r, mask)) return false;
		}
		int offset;
		double value;
		int inc = Math.max(r.height/25, 1);
		int mi = 0;
		ImageWindow win = imp.getWindow();
		if (win!=null)
			win.running = true;
		
		//roiNeedsImage = (measurements&128)!=0 || (measurements&8192)!=0 || (measurements&16384)!=0;
		particleCount = 0;
		wand = new Wand(ip);
		pf = new PolygonFiller();
		if (floodFill) {
			ImageProcessor ipf = ip.duplicate();
			ipf.setValue(fillColor);
			ff = new FloodFiller(ipf);
		}
		roiType = Wand.allPoints()?Roi.FREEROI:Roi.TRACED_ROI;
		
		boolean done = false;
		for (int y=r.y; y<(r.y+r.height); y++) {
			offset = y*width;
			for (int x=r.x; x<(r.x+r.width); x++) {
				if (pixels!=null)
					value = pixels[offset+x]&255;
					
				else if (imageType==SHORT)
					value = ip.getPixel(x, y);
				else
					value = ip.getPixelValue(x, y);
				if (value>=level1 && value<=level2 && !done) {
					
					analyzeParticle(x, y, imp, ip);
					
					done = level1==0.0&&level2==255.0&&imp.getBitDepth()==8;
					
				}
			}
			
			if (showProgress && ((y%inc)==0))
				IJ.showProgress((double)(y-r.y)/r.height);
			
			
		}
		if (showProgress)
			IJ.showProgress(1.0);
		
		imp.deleteRoi();
		ip.resetRoi();
		ip.reset();
		
		
		maxParticleCount = (particleCount > maxParticleCount) ? particleCount : maxParticleCount;
		totalCount += particleCount;
		
		return true;
	}
static boolean eraseOutsideRoi(ImageProcessor ip, Rectangle r, ImageProcessor mask) {
	int width = ip.getWidth();
	int height = ip.getHeight();
	ip.setRoi(r);
	if (excludeEdgeParticles && polygon!=null) {
		ImageStatistics stats = ImageStatistics.getStatistics(ip, 16, null);
		if (fillColor>=stats.min && fillColor<=stats.max) {
			double replaceColor = level1-1.0;
			if (replaceColor<0.0 || replaceColor==fillColor) {
				replaceColor = level2+1.0;
				int maxColor = imageType==BYTE?255:65535;
				if (replaceColor>maxColor || replaceColor==fillColor) {
					//IJ.error("Particle Analyzer", "Unable to remove edge particles");
					return false;
				}
			}
			for (int y=minY; y<maxY; y++) {
				for (int x=minX; x<maxX; x++) {
					int v  = ip.getPixel(x, y);
					if (v==fillColor) ip.putPixel(x, y, (int)replaceColor);
				}
			}
		}
	}
	ip.setValue(fillColor);		
	if (mask!=null) {
		mask = mask.duplicate();
		mask.invert();
		ip.fill(mask);
	}		
	ip.setRoi(0, 0, r.x, height);
	ip.fill();
	ip.setRoi(r.x, 0, r.width, r.y);
	ip.fill();
	ip.setRoi(r.x, r.y+r.height, r.width, height-(r.y+r.height));
	ip.fill();
	ip.setRoi(r.x+r.width, 0, width-(r.x+r.width), height);
	ip.fill();
	ip.resetRoi();
	return true;
}


static void analyzeParticle(int x, int y, ImagePlus imp, ImageProcessor ip) {
	ImageProcessor ip2 = redirectIP!=null?redirectIP:ip;
	wandMode = Wand.FOUR_CONNECTED;
	wand.autoOutline(x, y, level1, level2, wandMode);
	if (wand.npoints==0)
		{
		//IJ.log("wand error: "+x+" "+y); 
		return;}
	Roi roi = new PolygonRoi(wand.xpoints, wand.ypoints, wand.npoints, roiType);
	Rectangle r = roi.getBounds();
	
	if (r.width>1 && r.height>1) {
		PolygonRoi proi = (PolygonRoi)roi;
		pf.setPolygon(proi.getXCoordinates(), proi.getYCoordinates(), proi.getNCoordinates());
		ip2.setMask(pf.getMask(r.width, r.height));
		if (floodFill) ff.particleAnalyzerFill(x, y, level1, level2, ip2.getMask(), r);
	}
	ip2.setRoi(r);
	ip.setValue(fillColor);
	ImageStatistics stats = getStatistics(ip2, measurements, calibration);
	boolean include = true;
	
	
		if (r.x==minX||r.y==minY||r.x+r.width==maxX||r.y+r.height==maxY)
			include = false;
		if (polygon!=null) {
			Rectangle bounds = roi.getBounds();
			int x1=bounds.x+wand.xpoints[wand.npoints-1];
			int y1=bounds.y+wand.ypoints[wand.npoints-1];
			int x2, y2;
			for (int i=0; i<wand.npoints; i++) {
				x2=bounds.x+wand.xpoints[i];
				y2=bounds.y+wand.ypoints[i];
				if (!polygon.contains(x2, y2))
					{include = false; break;}
				if ((x1==x2 && ip.getPixel(x1,y1-1)==fillColor) || (y1==y2 && ip.getPixel(x1-1,y1)==fillColor))
					{include = false; break;}
				x1=x2; y1=y2;
				
				
			}
		}
	
	double circ=0;
	ImageProcessor mask = ip.getMask();
	if (minCircularity>0.0 || maxCircularity<1.0) {
		double perimeter = roi.getLength();
		double circularity = perimeter==0.0?0.0:4.0*Math.PI*(stats.pixelCount/(perimeter*perimeter));
		if (circularity>1.0) circularity = 1.0;
		if (circularity<minCircularity || circularity>maxCircularity) include = false;
		else circ=circularity;
	}
	if (stats.pixelCount>=minSize && stats.pixelCount<=maxSize && include) {
		
		particleCount++;
		if (roiNeedsImage)
			roi.setImage(imp);
		stats.xstart=x; stats.ystart=y;
		
		saveResults(stats, roi);
		//System.out.println("circularity: " + circ);
		
		
	}
	
	
	if (redirectIP!=null)
		ip.setRoi(r);
	ip.fill(mask);
}
static ImageStatistics getStatistics(ImageProcessor ip, int mOptions, Calibration cal) {
	switch (imageType2) {
		case BYTE:
			return new ByteStatistics(ip, mOptions, cal);
		case SHORT:
			return new ShortStatistics(ip, mOptions, cal);
		case FLOAT:
			return new FloatStatistics(ip, mOptions, cal);
		case RGB:
			return new ColorStatistics(ip, mOptions, cal);
		default:
			return null;
	}
}
protected static void saveResults(ImageStatistics stats, Roi roi) {
	analyzer.saveResults(stats, roi);
	if (recordStarts) {
		rt.addValue("XStart", stats.xstart);
		rt.addValue("YStart", stats.ystart);
	}
	if (addToManager) {
		if (roiManager==null) {
			if (Macro.getOptions()!=null && Interpreter.isBatchMode())
				roiManager = Interpreter.getBatchModeRoiManager();
			if (roiManager==null) {
				Frame frame = WindowManager.getFrame("ROI Manager");
				if (frame==null)
					IJ.run("ROI Manager...");
				frame = WindowManager.getFrame("ROI Manager");
				if (frame==null || !(frame instanceof RoiManager))
					{addToManager=false; return;}
				roiManager = (RoiManager)frame;
			}
			if (resetCounter)
				roiManager.runCommand("reset");
		}
		
		//roiManager.add(imp, roi, rt.getCounter());
	}
	
}

static boolean setThresholdLevels(ImagePlus imp, ImageProcessor ip) {
	double t1 = ip.getMinThreshold();
	double t2 = ip.getMaxThreshold();
	//System.out.println("min"+t1+"max"+t2);
	boolean invertedLut = imp.isInvertedLut();
	boolean byteImage = ip instanceof ByteProcessor;
	if (ip instanceof ShortProcessor)
		imageType = SHORT;
	else if (ip instanceof FloatProcessor)
		imageType = FLOAT;
	else
		imageType = BYTE;
	if (t1==ImageProcessor.NO_THRESHOLD) {
		
		ImageStatistics stats = imp.getStatistics();
		if (imageType!=BYTE || (stats.histogram[0]+stats.histogram[255]!=stats.pixelCount)) {
			/*System.out.println("A thresholded image or 8-bit binary image is\n"
				+"required. Threshold levels can be set using\n"
				+"the Image->Adjust->Threshold tool");*/
			
			
			IJ.error("Particle Analyzer",
				"A thresholded image or 8-bit binary image is\n"
				+"required. Threshold levels can be set using\n"
				+"the Image->Adjust->Threshold tool.");
			
			return false;
		}
		boolean threshold255 = invertedLut;
		if (Prefs.blackBackground)
			threshold255 = !threshold255;
		if (threshold255) {
			level1 = 255;
			level2 = 255;
			fillColor = 64;
		} else {
			level1 = 0;
			level2 = 0;
			fillColor = 192;
		}
	} else {
		level1 = t1;
		level2 = t2;
		if (imageType==BYTE) {
			if (level1>0)
				fillColor = 0;
			else if (level2<255)
				fillColor = 255;
		} else if (imageType==SHORT) {
			if (level1>0)
				fillColor = 0;
			else if (level2<65535)
				fillColor = 65535;
		} else if (imageType==FLOAT)
				fillColor = -Float.MAX_VALUE;
		else
			return false;
	}
	imageType2 = imageType;
	if (redirectIP!=null) {
		if (redirectIP instanceof ShortProcessor)
			imageType2 = SHORT;
		else if (redirectIP instanceof FloatProcessor)
			imageType2 = FLOAT;
		else if (redirectIP instanceof ColorProcessor)
			imageType2 = RGB;
		else
			imageType2 = BYTE;
	}
	return true;
}		
	/*public static void main(String[] args) throws Throwable {
				new CameraControl();
				Thread.sleep(2400);
				int result[]=capture();  // This function should be called when 'Capture' button is pressed
				System.out.println("Lum:"+result[0] +" R:"+ result[1]+" G:"+ result[2]+" B:"+ result[3]);
				System.out.println("Bye!");
			}*/
			
		}



