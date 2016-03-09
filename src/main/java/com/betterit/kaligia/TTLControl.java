package com.betterit.kaligia;

import java.util.concurrent.TimeUnit;
import com.labjack.LJUD;

public class TTLControl implements Runnable {
	String thrdName;
	int intHandle;
	int portNum;
	boolean stopped;
	boolean suspended;

	TTLControl(String name, int i, int j) {
		thrdName = name;
		intHandle = i;
		portNum = j;
		stopped = false;
		
	}

	public void run() {
		try {
			while (true) {
				LJUD.addRequest(intHandle, LJUD.Constants.ioPUT_DIGITAL_BIT, portNum, 1, 0, 0);
				LJUD.goOne(intHandle);
				TimeUnit.MILLISECONDS.sleep(50);
				LJUD.addRequest(intHandle, LJUD.Constants.ioPUT_DIGITAL_BIT, portNum, 0, 0, 0);
				LJUD.goOne(intHandle);
				TimeUnit.MILLISECONDS.sleep(50);
				synchronized (this) {
					if (stopped)
						break;
				}

			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void run() {
//		try {
//			while (true) {
//				LJUD.addRequest(intHandle, LJUD.Constants.ioPUT_DIGITAL_BIT, portNum, 1, 0, 0);
//				LJUD.goOne(intHandle);
//				TimeUnit.MILLISECONDS.sleep(1000);
//				LJUD.addRequest(intHandle, LJUD.Constants.ioPUT_DIGITAL_BIT, portNum, 0, 0, 0);
//				LJUD.goOne(intHandle);
//				TimeUnit.MILLISECONDS.sleep(1000);
//
//				synchronized (this) {
//					while (suspended) {
//						wait();
//					}
//
//					if (stopped)
//						break;
//				}
//			}
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
	synchronized void mystop() {
		stopped = true;
		
	}
//	
//	synchronized void mysuspend(){
//		suspended = true;
//	}
//	
//	synchronized void myresume(){
//		suspended = false;
//		notify();
//	}
}
