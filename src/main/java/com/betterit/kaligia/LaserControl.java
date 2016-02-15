/**
 * 
 */
package com.betterit.kaligia;

/**
 * @author V135012
 *
 */

import com.labjack.LJUD;
import com.labjack.LJUDException;

public class LaserControl {

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

	public void setLaserPower(double vPower) {

		if (vPower >= 0 & vPower < 1.2)
			LJUD.ePut(LJhandle, LJUD.Constants.ioPUT_DAC, 0, vPower, 0);
		else
			System.out.println("The voltage of laser power must be between 0 and 1.2V");
	}

}
