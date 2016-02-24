/**
 * 
 */
package com.betterit.kaligia;

import java.util.List;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.betterit.kaligia.dao.model.kaligia.TestProcedure;
import com.betterit.kaligia.service.TestProcedureService;


/**
 * @author nayar
 *
 */


@Controller
public class KaligiaMainController {

	Logger log = Logger.getLogger(KaligiaMainController.class.getName());

	@Autowired
	private TestProcedureService procedureServiceObject;
	
	@RequestMapping(value="/KaligiaMainApp", method=RequestMethod.GET)
    public String mainForm(Model model) {
		
		/** TO-DO get all procedures from database */
		List<TestProcedure> procedureList = procedureServiceObject.findAll();
		
		model.addAttribute("ProcedureList", procedureList);
		
		return ("KaligiaMainApp");
	}
}
