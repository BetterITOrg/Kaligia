
public class Polyval {
	double coef[];
	double xaxis[];
	double fitData[];
	
	
	Polyval(double[] c, double[] x, int nPix){
		coef=c;
		xaxis=x;
		fitData=new double[nPix];
		
	}
	
	public double[] evl(){
	for(int i = 0; i < xaxis.length; i++){
		fitData[i]=coef[5]*Math.pow(xaxis[i],5)+coef[4]*Math.pow(xaxis[i],4)+coef[3]*Math.pow(xaxis[i],3)+coef[2]*Math.pow(xaxis[i],2)+coef[1]*Math.pow(xaxis[i],1)+coef[0];
	}
	return fitData;
	}
	

}
