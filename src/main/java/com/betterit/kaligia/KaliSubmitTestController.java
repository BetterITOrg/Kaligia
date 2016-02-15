package com.betterit.kaligia;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


//port com.betterit.kaligia.TestRun;

@Controller
public class KaliSubmitTestController {
	
    @Autowired
    JdbcTemplate jdbcTemplate;

	
	private static final Logger log = LoggerFactory.getLogger(KaliSubmitTestController.class);
	
	   @RequestMapping(value = "/KaliSubmitTest", method = RequestMethod.GET)
	   public String KaliSubmitTest(Model model) {
	      model.addAttribute("KaliSubmitTest", new KaliSubmitTest());
        return "KaliSubmitTest";
	   }


	   @RequestMapping(value = "/KaliSubmitTest", method = RequestMethod.POST)
	   public String kaliSubmit(@ModelAttribute KaliSubmitTest kaliSubmitTestObject, Model model) {
		   
		   log.info("In POST");
		   
		   if ( kaliSubmitTestObject.getIntegTime1() > 0 )
		   {
			   log.info("Running 1st Reading with  " + kaliSubmitTestObject.getIntegTime1() + " s");

			   /*
		    	TestRun tr = new TestRun("Sample", "Test Run", "Test", 15, 1, 1, 1, 0, 1, 0, jdbcTemplate);
		    	tr.doTestRun();
		    	*/
			   TestRun tr1 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime1(),
					   kaliSubmitTestObject.getDelay1(),
					   kaliSubmitTestObject.getScan2Avg1(),
					   kaliSubmitTestObject.getElectricDark1(),
					   kaliSubmitTestObject.getNLCorrect1(),
					   kaliSubmitTestObject.getBoxCarWid1(),
					   0,
					   1,
					   jdbcTemplate
					   );
			   
			   if ( tr1.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 1st Reading with  " + kaliSubmitTestObject.getIntegTime1() + " s");
				   kaliSubmitTestObject.setStatus1("SUCCESS: 1st Reading with  " + kaliSubmitTestObject.getIntegTime1() + " s");
			   }
			   else   {
				   log.info("FAILURE: 1st Reading with  ");
				   kaliSubmitTestObject.setStatus1("FAILURE: 1st Reading with  ");
			   }
		   }
		   
		   
		   if ( kaliSubmitTestObject.getIntegTime2() > 0 )
		   {
			   log.info("Running 2nd Reading with  " + kaliSubmitTestObject.getIntegTime2() + " s");
			   
			   TestRun tr2 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime2(),
					   kaliSubmitTestObject.getDelay2(),
					   kaliSubmitTestObject.getScan2Avg2(),
					   kaliSubmitTestObject.getElectricDark2(),
					   kaliSubmitTestObject.getNLCorrect2(),
					   kaliSubmitTestObject.getBoxCarWid2(),
					   0,
					   2,
					   jdbcTemplate
					   );
			   
			   if ( tr2.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 2nd Reading with  " + kaliSubmitTestObject.getIntegTime2() + " s");
				   kaliSubmitTestObject.setStatus2("SUCCESS: 2nd Reading with  " + kaliSubmitTestObject.getIntegTime2() + " s");
			   } else  {
				   log.info("FAILURE: 2nd Reading with  ");
				   kaliSubmitTestObject.setStatus1("FAILURE: 1st Reading with  ");
				   
			   }
		   }

		  model.addAttribute("KaliSubmitTest", kaliSubmitTestObject); 
	      return "resultKali";
	   }

}
