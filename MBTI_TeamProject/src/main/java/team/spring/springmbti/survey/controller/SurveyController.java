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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import team.spring.springmbti.survey.service.SurveyService;
import team.spring.springmbti.user.vo.User;


/**
 * Handles requests for the application home page.
 */
@Controller
@RequestMapping(value = "survey")
public class SurveyController {
	
	@Autowired
	private SurveyService surveyservice;
	
	Logger log = LogManager.getLogger("case3");
	
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
	public void handler001(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		user = (User)session.getAttribute("myUser");
		user.setUserI(nqtotal);
		user.setUserE(qtotal);
		log.debug(user);
		
		session.setAttribute("myUser", user);
		
		surveyservice.updateScoreOne(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton2")
	public void handler002(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		user = (User)session.getAttribute("myUser");
		user.setUserN(nqtotal);
		user.setUserS(qtotal);
		
		session.setAttribute("myUser", user);
		log.debug(user);
		surveyservice.updateScoreTwo(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton3")
	public void handler003(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		user = (User)session.getAttribute("myUser");
		user.setUserT(nqtotal);
		user.setUserF(qtotal);
		
		session.setAttribute("myUser", user);
		log.debug(user);
		surveyservice.updateScoreThree(user);
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	@PutMapping("sbutton4")
	public void handler004(User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		
		user = (User)session.getAttribute("myUser");
		user.setUserP(nqtotal);
		user.setUserJ(qtotal);
		
		session.setAttribute("myUser", user);
		log.debug(user);
		
		surveyservice.updateScoreFour(user);
		
		
		
		Gson gson = new Gson();
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("qone", qone);
		String find = gson.toJson(jsonObject);
		response.getWriter().write(find);
	}
	
	
}

