package team.spring.springmbti.user.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import team.spring.springmbti.user.service.SurveyService;

/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "survey")
public class SurveyController1 {
	
	@Autowired
	private SurveyService surveyservice;
	
	Logger log = LogManager.getLogger("case3");
	
	@PostMapping("surveyone")
	public String handler(Model model) {
		log.debug("handler1() 호출 - 일단연습");
		
		
		return "survey/survey1";
	}
	
	
	
	@PostMapping("surveyone1")
	public void handler1(Model model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler1() 호출 - 프로그레스바시작");
		request.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		//int num = Integer.parseInt(request.getParameter("num"));
		int countnum = 20;
		//log.debug(num);
		log.debug(qnum);
		//Survey survey1 = new Survey(num,countnum);
		//log.debug(survey1);
		//model.addAttribute("surveys",survey1);
		
//		if (num == countnum) {
//			
//		} else {
//			
//		}
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qnum", qnum);
		//jsonObject.addProperty("num", num);
		
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
		//return "survey/survey1";
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
