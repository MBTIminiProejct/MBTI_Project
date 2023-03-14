package team.spring.springmbti.survey.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import team.spring.springmbti.survey.service.SurveyService;
import team.spring.springmbti.user.vo.User;


/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes(value= {"myUser"})
@RequestMapping(value = "survey")
public class SurveyController {
	
	@Autowired
	private SurveyService surveyservice;
	
	Logger log = LogManager.getLogger("case3");
	
	@ModelAttribute("myUser")
	public User createUser() {
		User user = new User();
		return user;
	}
	
	@PostMapping("surveyone")
	public String handler() {
		log.debug("handler() 성공");
		
		
		return "survey/survey1";
	}
	
	@PostMapping("surveytwo")
	public String handler1() {
		log.debug("handler1() 성공");
		
		
		return "survey/survey2";
	}
	
	@PostMapping("surveythree")
	public String handler2() {
		log.debug("handler2() 성공");
		
		
		return "survey/survey3";
	}
	
	@PostMapping("surveyfour")
	public String handler3() {
		log.debug("handler3() 성공");
		
		
		return "survey/survey4";
	}
	
	@PostMapping("surveyfive")
	public String handler4() {
		log.debug("handler4() 성공");
		
		
		return "survey/survey5";
	}
	
	@GetMapping("surveyone1")
	public void handler01(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler01() 성공");
		request.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		int onum = Integer.parseInt(request.getParameter("onum"));

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qnum", qnum);
		jsonObject.addProperty("onum", onum);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}	
	
	@GetMapping("surveytwo2")
	public void handler02(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler02() 성공");
		request.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		int onum = Integer.parseInt(request.getParameter("onum"));

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qnum", qnum);
		jsonObject.addProperty("onum", onum);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}	
	
	@GetMapping("surveythree3")
	public void handler03(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler03() 성공");
		request.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		int onum = Integer.parseInt(request.getParameter("onum"));

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qnum", qnum);
		jsonObject.addProperty("onum", onum);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}	
	
	@GetMapping("surveyfour4")
	public void handler04(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler04() 성공");
		request.setCharacterEncoding("UTF-8");
		int qnum = Integer.parseInt(request.getParameter("qnum"));
		int onum = Integer.parseInt(request.getParameter("onum"));

		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qnum", qnum);
		jsonObject.addProperty("onum", onum);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}	
	
	@PutMapping("sbutton1")
	public void handler001(Model model, @ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler001() 성공");
		request.setCharacterEncoding("UTF-8");
		int qone = Integer.parseInt(request.getParameter("qone"));
		int qtwo = Integer.parseInt(request.getParameter("qtwo"));
		int qthree = Integer.parseInt(request.getParameter("qthree"));
		int qfour = Integer.parseInt(request.getParameter("qfour"));
		int qfive = Integer.parseInt(request.getParameter("qfive"));
		int qtotal = qone + qtwo + qthree + qfour + qfive;
		int total = qtotal - 15;
		int nqtotal = total*-1;
		
		//user = (User)session.getAttribute("myUser");
		user.setUserI(nqtotal);
		user.setUserE(qtotal);
		log.debug(user);
		
		// session.setAttribute("myUser", user);
		model.addAttribute("myUser", user);
		surveyservice.updateScoreOne(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton2")
	public void handler002(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler002() 괜찮슴");
		request.setCharacterEncoding("UTF-8");
		int qone = Integer.parseInt(request.getParameter("qone"));
		int qtwo = Integer.parseInt(request.getParameter("qtwo"));
		int qthree = Integer.parseInt(request.getParameter("qthree"));
		int qfour = Integer.parseInt(request.getParameter("qfour"));
		int qfive = Integer.parseInt(request.getParameter("qfive"));
		int qtotal = qone + qtwo + qthree + qfour + qfive;
		int total = qtotal - 15;
		int nqtotal = total*-1;
		
		//user = (User)session.getAttribute("myUser");
		user.setUserN(nqtotal);
		user.setUserS(qtotal);
		
		//session.setAttribute("myUser", user);
		log.debug(user);
		model.addAttribute("myUser", user);
		surveyservice.updateScoreTwo(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton3")
	public void handler003(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler003() 성공");
		request.setCharacterEncoding("UTF-8");
		int qone = Integer.parseInt(request.getParameter("qone"));
		int qtwo = Integer.parseInt(request.getParameter("qtwo"));
		int qthree = Integer.parseInt(request.getParameter("qthree"));
		int qfour = Integer.parseInt(request.getParameter("qfour"));
		int qfive = Integer.parseInt(request.getParameter("qfive"));
		int qtotal = qone + qtwo + qthree + qfour + qfive;
		int total = qtotal - 15;
		int nqtotal = total*-1;
		
		//user = (User)session.getAttribute("myUser");
		user.setUserT(nqtotal);
		user.setUserF(qtotal);
		
		//session.setAttribute("myUser", user);
		log.debug(user);
		model.addAttribute("myUser", user);
		surveyservice.updateScoreThree(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton4")
	public void handler004(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("handler004() 성공");
		request.setCharacterEncoding("UTF-8");
		int qone = Integer.parseInt(request.getParameter("qone"));
		int qtwo = Integer.parseInt(request.getParameter("qtwo"));
		int qthree = Integer.parseInt(request.getParameter("qthree"));
		int qfour = Integer.parseInt(request.getParameter("qfour"));
		int qfive = Integer.parseInt(request.getParameter("qfive"));
		int qtotal = qone + qtwo + qthree + qfour + qfive;
		int total = qtotal - 15;
		int nqtotal = total*-1;
		String typeone = new String();
		String typetwo = new String();
		String typethree = new String();
		String typefour = new String();
		String mbti = new String();
		
		user.setUserP(nqtotal);
		user.setUserJ(qtotal);
		model.addAttribute("myUser", user);
		User usertotal = (User)session.getAttribute("myUser");
		//session.setAttribute("myUser", user);
		
		int lone = usertotal.getUserE();
		int ltwo = usertotal.getUserI();
		int lthree = usertotal.getUserS();
		int lfour = usertotal.getUserN();
		int lfive = usertotal.getUserF();
		int lsix = usertotal.getUserT();
		int lseven = usertotal.getUserJ();
		int leight = usertotal.getUserP();
		
		if (lone > ltwo) {
			typeone = "E";
		} else {
			typeone = "I";
		}
		
		if (lthree > lfour) {
			typetwo = "S";
		} else {
			typetwo = "N";
		}

		if (lfive > lsix) {
			typethree = "F";
		} else {
			typethree = "T";
		}
		
		if (lseven > leight) {
			typefour = "J";
		} else {
			typefour = "P";
		}
		
		mbti = typeone + typetwo + typethree + typefour;
		user.setUserMBTI(mbti);
		surveyservice.updateScoreFour(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	
}

