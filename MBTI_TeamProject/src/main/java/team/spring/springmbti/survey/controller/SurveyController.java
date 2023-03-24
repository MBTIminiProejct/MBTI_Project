package team.spring.springmbti.survey.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.mbti.service.MBTIService;
import team.spring.springmbti.mbti.vo.MBTIResult;
import team.spring.springmbti.survey.service.SurveyService;
import team.spring.springmbti.user.service.LoginService;
import team.spring.springmbti.user.vo.User;


/**
 * Handles requests for the application home page.
 */
@RestController
@RequestMapping(value = "survey",produces="application/json")
public class SurveyController {
   
   @Autowired
   private LoginService loginservice;
	
   @Autowired
   private SurveyService surveyservice;
   
   @Autowired
   private CharacterService characterservice;
   
   @Autowired
   private MBTIService mbtiservice;
   
   Logger log = LogManager.getLogger("case3");
   

   
   @GetMapping("/fast")
   public Map<String, Integer> beforefast(@RequestParam(value="fast", required=false) int fast, HttpSession session) {
      log.debug("fast 성공");
      
      session.setAttribute("before", fast);
      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("fast", fast);
      
      return map;
   }
   
   @GetMapping("/slow")
   public Map<String, Integer> beforeslow(@RequestParam(value="slow", required=false) int slow, HttpSession session) {
      log.debug("slow 성공");
      
      session.setAttribute("before", slow);
      Map<String, Integer> map = new HashMap<String, Integer>();
      map.put("slow", slow);
      
      return map;
   }
   
   @GetMapping("/partone/surveyone")
   public Map<String, String> handler01(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler01() 성공");
     
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/parttwo/surveytwo")
   public Map<String, String> handler02(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler02() 성공");
    
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/partthree/surveythree")
   public Map<String, String> handler03(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler03() 성공");
     
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/partfour/surveyfour")
   public Map<String, String> handler04(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler04() 성공");
     
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   

   
   @GetMapping("/partone/sbuttonone")
   public Map<String, Integer> handler001(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         @RequestParam(value="email", required=false) String email,
         Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler001() 성공");
      request.setCharacterEncoding("UTF-8");
      
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      log.debug("userEmail!"+email);
   
      User user = new User();
      user.setUserEmail(email);
      user.setUserI(nqtotal);
      user.setUserE(qtotal);
      
      surveyservice.updateScoreOne(user);
      
      Map<String, Integer> map = new HashMap<String, Integer>();
      
      map.put("nqtotal", nqtotal);
      map.put("qtotal", qtotal);
      return map;
   }
   
   @GetMapping("/parttwo/sbuttontwo")
   public Map<String, Integer> handler002(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         @RequestParam(value="email", required=false) String email,
         Model model,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler002() 성공");
      request.setCharacterEncoding("UTF-8");
      
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      User user = new User();
      user.setUserEmail(email);
      
      user.setUserN(nqtotal);
      user.setUserS(qtotal);
      
      surveyservice.updateScoreTwo(user);
      
      Map<String, Integer> map = new HashMap<String, Integer>();
      
      map.put("nqtotal", nqtotal);
      map.put("qtotal", qtotal);
      return map;
   }
   
   @GetMapping("/partthree/sbuttonthree")
   public Map<String, Integer> handler003(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         @RequestParam(value="email", required=false) String email,
         Model model, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler003() 성공");
      request.setCharacterEncoding("UTF-8");
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      
      User user = new User();
      user.setUserEmail(email);
      user.setUserT(nqtotal);
      user.setUserF(qtotal);
      
      surveyservice.updateScoreThree(user);
      
      Map<String, Integer> map = new HashMap<String, Integer>();
      
      map.put("nqtotal", nqtotal);
      map.put("qtotal", qtotal);
      return map;
   }
   
   @GetMapping("/partfour/sbuttonfour")
   public Map<String,String> handler004(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         @RequestParam(value="speed", required=false) int speed,
         @RequestParam(value="email", required=false) String email,
         Model model, CharacterInfo characterinfo,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JsonProcessingException {
		log.debug("handler004() 성공");
		request.setCharacterEncoding("UTF-8");
		int qtotal = qone + qtwo + qthree + qfour + qfive;
		int total = qtotal - 15;
		int nqtotal = total * -1;
		log.debug("P,J Check" + qtotal + "," + nqtotal);
		User user = new User();
		user.setUserEmail(email);
		user = loginservice.getUser(email);
		user.setUserP(nqtotal);
		user.setUserJ(qtotal);
		String typeone = new String();
		String typetwo = new String();
		String typethree = new String();
		String typefour = new String();
		String mbti = new String();

		int lone = user.getUserE();
		int ltwo = user.getUserI();
		int lthree = user.getUserS();
		int lfour = user.getUserN();
		int lfive = user.getUserF();
		int lsix = user.getUserT();
		int lseven = user.getUserJ();
		int leight = user.getUserP();

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

		if (speed == 2) {
			CharacterInfo character = new CharacterInfo(100, 10, 10, 5, 5, 11, 10, 10, 30, 0);

			int result = characterservice.createCharacter(character);
		} else {
			CharacterInfo character = new CharacterInfo(100, 10, 10, 5, 5, 9, 10, 10, 30, 0);

			int result = characterservice.createCharacter(character);
		}

		int maxresult = characterservice.maxCharacter();

		user.setUserCharacter(maxresult);

		int characternum = user.getUserCharacter();

		surveyservice.updateUserCharacter(user);

		characterinfo = new CharacterInfo(characternum, 100, 10 + lone, 10 + ltwo, 5 + lthree, 5 + lfour, 10,
				10 + lfive, 10 + lsix, 30 + lseven, 0 + leight);
		int characterresult = characterservice.updateCharacter(characterinfo);

		characterinfo = characterservice.getCharacter(characternum);

		log.debug("생성된 캐릭터!" + characterinfo);

		ObjectMapper mapper = new ObjectMapper();
		String characterInfo = mapper.writeValueAsString(characterinfo);
		String userInfo = mapper.writeValueAsString(user);
		Map<String, String> map = new HashMap<String, String>();
		map.put("userInfo", userInfo);
		map.put("characterInfo", characterInfo);
		return map;
   }
   
   @GetMapping(value = "/partfour/sbuttonfour/user")
   public Map<String, String> getMbti(HttpSession session,@RequestParam(value="mbti", required=false) String mbti) throws JsonProcessingException {
	   
       
       MBTIResult mbtiresult = mbtiservice.getMBTI(mbti);
       ObjectMapper mapper = new ObjectMapper();
       
       String mbtiInfo = mapper.writeValueAsString(mbtiresult);
       Map<String,String> map = new HashMap<String, String>();
       map.put("mbtiInfo", mbtiInfo);
       
       
      return map;
   }
   

      
      
   
}
