package team.spring.springmbti.user.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import team.spring.springmbti.user.service.SurveyService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SurveyControllerTF {
	
	@Autowired
	private SurveyService surveyservice;
	
	Logger log = LogManager.getLogger("case3");
	
	@PostMapping("surveythree")
	public String handler() {
		log.debug("handler1() 호출 - 일단연습");
		
		
		return "survey/survey3";
	}
	
	@PostMapping("surveythree1")
	public String handler1() {
		log.debug("handler1() 호출 - 일단연습");
		
		
		return "survey/survey3";
	}
	
//	@GetMapping("login")
//    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
//        System.out.println("#########" + code);
//        return "testPage";
//    }
//	 
//	@RequestMapping(value = "/home", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		boolean canUse=false;
//		
//		canUse = joinservice.idCheck("test123");
//		
//		if(canUse) {
//			log.debug("사용 가능");
//		}
//		
//		return "home";
//	}
	
}
