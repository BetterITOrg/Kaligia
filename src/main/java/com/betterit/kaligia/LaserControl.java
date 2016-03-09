package com.betterit.kaligia;

import com.labjack.LJUD;

public  class LaserControl {
	int LJhandle;
	int ptNumber;
	
	LaserControl(int i, int j){
		LJhandle = i;
		ptNumber = j;
	}
	
	public void setTTLSwitchHigh() {
		LJUD.addRequest(LJhandle, LJUD.Constants.ioPUT_DIGITAL_BIT, ptNumber, 1, 0, 0); // send
																						// high
																						// TTL
																						// to
																						// laser
		LJUD.goOne(LJhandle);
	}

	public void setTTLSwitchLow() {
		LJUD.addRequest(LJhandle, LJUD.Constants.ioPUT_DIGITAL_BIT, ptNumber, 0, 0, 0); // send
																						// low
																						// TTL
																						// to
																						// laser
		LJUD.goOne(LJhandle);
	}

	public int setLaserPower(double vPower, int portNumLasInt) {

		if (vPower >= 0 & vPower <= 0.8) {
			LJUD.ePut(LJhandle, LJUD.Constants.ioPUT_DAC, portNumLasInt, vPower, 0);
		} else {
			System.out.println("The voltage of laser power must be between 0 and 0.8V");
			return 100;
		}
		return 0;
	}
	
}
