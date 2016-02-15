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
import org.springframework.web.bind.annotation.RequestParam;

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
				   kaliSubmitTestObject.setStatus2("FAILURE: 2nd Reading with  ");
				   
			   }
		   }


		   if ( kaliSubmitTestObject.getIntegTime3() > 0 )
		   {
			   log.info("Running 3nd Reading with  " + kaliSubmitTestObject.getIntegTime3() + " s");
			   
			   TestRun tr3 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime3(),
					   kaliSubmitTestObject.getDelay3(),
					   kaliSubmitTestObject.getScan2Avg3(),
					   kaliSubmitTestObject.getElectricDark3(),
					   kaliSubmitTestObject.getNLCorrect3(),
					   kaliSubmitTestObject.getBoxCarWid3(),
					   0,
					   3,
					   jdbcTemplate
					   );
			   
			   if ( tr3.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 3rd Reading with  " + kaliSubmitTestObject.getIntegTime3() + " s");
				   kaliSubmitTestObject.setStatus3("SUCCESS: 3rd Reading with  " + kaliSubmitTestObject.getIntegTime3() + " s");
			   } else  {
				   log.info("FAILURE: 3rd Reading with  ");
				   kaliSubmitTestObject.setStatus3("FAILURE: 3rd Reading with  ");
				   
			   }
		   }

		   if ( kaliSubmitTestObject.getIntegTime4() > 0 )
		   {
			   log.info("Running 4th Reading with  " + kaliSubmitTestObject.getIntegTime4() + " s");
			   
			   TestRun tr4 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime4(),
					   kaliSubmitTestObject.getDelay4(),
					   kaliSubmitTestObject.getScan2Avg4(),
					   kaliSubmitTestObject.getElectricDark4(),
					   kaliSubmitTestObject.getNLCorrect4(),
					   kaliSubmitTestObject.getBoxCarWid4(),
					   0,
					   4,
					   jdbcTemplate
					   );
			   
			   if ( tr4.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 4th Reading with  " + kaliSubmitTestObject.getIntegTime4() + " s");
				   kaliSubmitTestObject.setStatus4("SUCCESS: 4th Reading with  " + kaliSubmitTestObject.getIntegTime4() + " s");
			   } else  {
				   log.info("FAILURE: 4th Reading with  ");
				   kaliSubmitTestObject.setStatus4("FAILURE: 4th Reading with  ");
				   
			   }
		   }

		   if ( kaliSubmitTestObject.getIntegTime5() > 0 )
		   {
			   log.info("Running 5th Reading with  " + kaliSubmitTestObject.getIntegTime5() + " s");
			   
			   TestRun tr5 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime5(),
					   kaliSubmitTestObject.getDelay5(),
					   kaliSubmitTestObject.getScan2Avg5(),
					   kaliSubmitTestObject.getElectricDark5(),
					   kaliSubmitTestObject.getNLCorrect5(),
					   kaliSubmitTestObject.getBoxCarWid5(),
					   0,
					   5,
					   jdbcTemplate
					   );
			   
			   if ( tr5.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 5th Reading with  " + kaliSubmitTestObject.getIntegTime5() + " s");
				   kaliSubmitTestObject.setStatus4("SUCCESS: 5th Reading with  " + kaliSubmitTestObject.getIntegTime5() + " s");
			   } else  {
				   log.info("FAILURE: 5th Reading with  " + kaliSubmitTestObject.getIntegTime5() + " s");
				   kaliSubmitTestObject.setStatus1("FAILURE: 5th Reading with  " + kaliSubmitTestObject.getIntegTime5() + " s");
				   
			   }
		   }

		   if ( kaliSubmitTestObject.getIntegTime6() > 0 )
		   {
			   log.info("Running 6th Reading with  " + kaliSubmitTestObject.getIntegTime6() + " s");
			   
			   TestRun tr6 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime6(),
					   kaliSubmitTestObject.getDelay6(),
					   kaliSubmitTestObject.getScan2Avg6(),
					   kaliSubmitTestObject.getElectricDark6(),
					   kaliSubmitTestObject.getNLCorrect6(),
					   kaliSubmitTestObject.getBoxCarWid6(),
					   0,
					   6,
					   jdbcTemplate
					   );
			   
			   if ( tr6.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 6th Reading with  " + kaliSubmitTestObject.getIntegTime6() + " s");
				   kaliSubmitTestObject.setStatus6("SUCCESS: 6th Reading with  " + kaliSubmitTestObject.getIntegTime6() + " s");
			   } else  {
				   log.info("FAILURE: 6th Reading with  " + kaliSubmitTestObject.getIntegTime6() + " s");
				   kaliSubmitTestObject.setStatus6("FAILURE: 6th Reading with  " + kaliSubmitTestObject.getIntegTime6() + " s");
				   
			   }
		   }

		   if ( kaliSubmitTestObject.getIntegTime7() > 0 )
		   {
			   log.info("Running 7th Reading with  " + kaliSubmitTestObject.getIntegTime7() + " s");
			   
			   TestRun tr7 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime7(),
					   kaliSubmitTestObject.getDelay7(),
					   kaliSubmitTestObject.getScan2Avg7(),
					   kaliSubmitTestObject.getElectricDark7(),
					   kaliSubmitTestObject.getNLCorrect7(),
					   kaliSubmitTestObject.getBoxCarWid7(),
					   0,
					   7,
					   jdbcTemplate
					   );
			   
			   if ( tr7.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 7th Reading with  " + kaliSubmitTestObject.getIntegTime7() + " s");
				   kaliSubmitTestObject.setStatus7("SUCCESS: 7th Reading with  " + kaliSubmitTestObject.getIntegTime7() + " s");
			   } else  {
				   log.info("FAILURE: 7th Reading with  " + kaliSubmitTestObject.getIntegTime7() + " s");
				   kaliSubmitTestObject.setStatus7("FAILURE: 7th Reading with  " + kaliSubmitTestObject.getIntegTime7() + " s");
				   
			   }
		   }


		   if ( kaliSubmitTestObject.getIntegTime8() > 0 )
		   {
			   log.info("Running 8th Reading with  " + kaliSubmitTestObject.getIntegTime8() + " s");
			   
			   TestRun tr8 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime8(),
					   kaliSubmitTestObject.getDelay8(),
					   kaliSubmitTestObject.getScan2Avg8(),
					   kaliSubmitTestObject.getElectricDark8(),
					   kaliSubmitTestObject.getNLCorrect8(),
					   kaliSubmitTestObject.getBoxCarWid8(),
					   0,
					   8,
					   jdbcTemplate
					   );
			   
			   if ( tr8.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 8th Reading with  " + kaliSubmitTestObject.getIntegTime8() + " s");
				   kaliSubmitTestObject.setStatus8("SUCCESS: 8th Reading with  " + kaliSubmitTestObject.getIntegTime8() + " s");
			   } else  {
				   log.info("FAILURE: 8th Reading with  " + kaliSubmitTestObject.getIntegTime8() + " s");
				   kaliSubmitTestObject.setStatus8("FAILURE: 8th Reading with  " + kaliSubmitTestObject.getIntegTime8() + " s");
				   
			   }
		   }


		   if ( kaliSubmitTestObject.getIntegTime9() > 0 )
		   {
			   log.info("Running 9th Reading with  " + kaliSubmitTestObject.getIntegTime9() + " s");
			   
			   TestRun tr9 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime9(),
					   kaliSubmitTestObject.getDelay9(),
					   kaliSubmitTestObject.getScan2Avg9(),
					   kaliSubmitTestObject.getElectricDark9(),
					   kaliSubmitTestObject.getNLCorrect9(),
					   kaliSubmitTestObject.getBoxCarWid9(),
					   0,
					   9,
					   jdbcTemplate
					   );
			   
			   if ( tr9.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 9th Reading with  " + kaliSubmitTestObject.getIntegTime9() + " s");
				   kaliSubmitTestObject.setStatus9("SUCCESS: 9th Reading with  " + kaliSubmitTestObject.getIntegTime9() + " s");
			   } else  {
				   log.info("FAILURE: 9th Reading with  " + kaliSubmitTestObject.getIntegTime9() + " s");
				   kaliSubmitTestObject.setStatus9("FAILURE: 9th Reading with  " + kaliSubmitTestObject.getIntegTime9() + " s");
				   
			   }
		   }

		   if ( kaliSubmitTestObject.getIntegTime10() > 0 )
		   {
			   log.info("Running 10th Reading with  " + kaliSubmitTestObject.getIntegTime10() + " s");
			   
			   TestRun tr10 = new TestRun( kaliSubmitTestObject.getName(),
					   kaliSubmitTestObject.getDescription(),
					   "Test Run",
					   kaliSubmitTestObject.getIntegTime10(),
					   kaliSubmitTestObject.getDelay10(),
					   kaliSubmitTestObject.getScan2Avg10(),
					   kaliSubmitTestObject.getElectricDark10(),
					   kaliSubmitTestObject.getNLCorrect10(),
					   kaliSubmitTestObject.getBoxCarWid10(),
					   0,
					   10,
					   jdbcTemplate
					   );
			   
			   if ( tr10.doTestRun() == 0 ) 
			   {
				   log.info("SUCCESS: 10th Reading with  " + kaliSubmitTestObject.getIntegTime10() + " s");
				   kaliSubmitTestObject.setStatus10("SUCCESS: 10th Reading with  " + kaliSubmitTestObject.getIntegTime10() + " s");
			   } else  {
				   log.info("FAILURE: 10th Reading with  " + kaliSubmitTestObject.getIntegTime10() + " s");
				   kaliSubmitTestObject.setStatus10("FAILURE: 10th Reading with  " + kaliSubmitTestObject.getIntegTime10() + " s");
				   
			   }
		   }

		  model.addAttribute("KaliSubmitTest", kaliSubmitTestObject); 
	      return "resultKali";
	   }

}
