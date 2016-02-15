/**
 * 
 */
package com.betterit.kaligia;

import java.sql.PreparedStatement;
import java.sql.SQLException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author V135012
 *
 */
public class TestResult {
	
	private static final Logger log = LoggerFactory.getLogger(TestResult.class);
	
	private int run_id;
	private int run_on;
	private double wavenumber[];
	private double photon_count[];
    private JdbcTemplate jdbc;
	
	/**
	 * @param run_id
	 * @param run_on
	 * @param wavenumber
	 * @param photon_count
	 */
	public TestResult(int run_id, int run_on, double[] wavenumber, double[] photon_count, JdbcTemplate jdbc) {
		this.run_id = run_id;
		this.run_on = run_on;
		this.wavenumber = wavenumber;
		this.photon_count = photon_count;
		this.jdbc = jdbc;
	}

	/**
	 * @return the run_id
	 */
	public int getRun_id() {
		return run_id;
	}

	/**
	 * @param run_id the run_id to set
	 */
	public void setRun_id(int run_id) {
		this.run_id = run_id;
	}

	/**
	 * @return the run_on
	 */
	public int getRun_on() {
		return run_on;
	}

	/**
	 * @param run_on the run_on to set
	 */
	public void setRun_on(int run_on) {
		this.run_on = run_on;
	}

	/**
	 * @return the wavenumber
	 */
	public double[] getWavenumber() {
		return wavenumber;
	}

	/**
	 * @param wavenumber the wavenumber to set
	 */
	public void setWavenumber(double[] wavenumber) {
		this.wavenumber = wavenumber;
	}

	/**
	 * @return the photon_count
	 */
	public double[] getPhoton_count() {
		return photon_count;
	}

	/**
	 * @param photon_count the photon_count to set
	 */
	public void setPhoton_count(double[] photon_count) {
		this.photon_count = photon_count;
	}
	
	public Integer insertResults() {
		
		String inSQL = "insert into kaligia.testresult (run_id, run_no, wavenumber, photon_count) values (?, ?, ?, ?);";
		int[] rr;

		try {
			
				rr = jdbc.batchUpdate( inSQL, 
				new BatchPreparedStatementSetter() {
	            @Override
	            public void setValues(PreparedStatement ps, int i) throws SQLException {
	            	ps.setInt(1, run_id);
	            	ps.setInt(2, run_on);
	            	ps.setDouble(3, wavenumber[i]);
	            	ps.setDouble(4, photon_count[i]);	
	            	log.info("Run: " + run_id + " Run No: " + run_on + " W: " + wavenumber[i] +  " P: " + photon_count[i]);
	            }
	            
	            @Override
	            public int getBatchSize()   
	            {  
	              return wavenumber.length;                    
	            }
			}
			);
			        
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.info("Insert Failed");
			return 100;
		}

		log.info(rr.length + " Rows Inserted");
		return 0;
	}
}
