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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import team.spring.springmbti.user.vo.User;


/**
 * Handles requests for the application home page.
 */
@RestController
//@SessionAttributes(value= {"myUser","myCharacter"})
@RequestMapping(value = "survey",produces="application/json")
public class SurveyController {
   
   @Autowired
   private SurveyService surveyservice;
   
   @Autowired
   private CharacterService characterservice;
   
   @Autowired
   private MBTIService mbtiservice;
   
   Logger log = LogManager.getLogger("case3");
   
//   @ModelAttribute("myUser")
//   public User createUser() {
//      User user = new User();
//      return user;
//   }
//   
//   @ModelAttribute("myCharacter")
//   public CharacterInfo createCharacter() {
//      CharacterInfo characterinfo = new CharacterInfo();
//      return characterinfo;
//   }
   
   @PostMapping("surveyone")
   public String handler(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
      
      
      User userbefore = (User)session.getAttribute("myUser");
      CharacterInfo character = 
            new CharacterInfo(100, 10, 10, 5 , 5 , 10, 10 , 10 , 30  ,0 );
      int result = characterservice.createCharacter(character);
      log.debug("잘되고있는거니");
      
      int maxresult = characterservice.maxCharacter();
      
      user.setUserCharacter(maxresult); 
      model.addAttribute("myUser", user);
      
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
   
   @RequestMapping("/surveyresult")
      public String popup1(Model model,@ModelAttribute("myUser") User user, @ModelAttribute("myCharacter") CharacterInfo characterinfo, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
         
        User usercurrent = (User)session.getAttribute("myUser");
        int characternum = usercurrent.getUserCharacter();
        log.debug(characternum);
        //CharacterInfo characterinfo = new CharacterInfo();
        //characterinfo.setCharacterNum(characternum);   
        //log.debug(characterinfo);
        characterinfo = characterservice.getCharacter(characternum);
        log.debug(characterinfo);
        model.addAttribute("myCharacter",characterinfo);
        
        log.debug("결과 출력 성공!");
         
         return "resultPage";
      }
   
//   @GetMapping("surveyone1")
//   public void handler01(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler01() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qnum = Integer.parseInt(request.getParameter("qnum"));
//      int onum = Integer.parseInt(request.getParameter("onum"));
//
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qnum", qnum);
//      jsonObject.addProperty("onum", onum);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }   
   
   @GetMapping("/partone/surveyone")
   public Map<String, String> handler01(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler01() 성공");
      //request.setCharacterEncoding("UTF-8");
      //int qnum = Integer.parseInt(request.getParameter("qnum"));
      //int onum = Integer.parseInt(request.getParameter("onum"));
      log.debug(cnum);
      log.debug(onum);
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/parttwo/surveytwo")
   public Map<String, String> handler02(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler01() 성공");
      //request.setCharacterEncoding("UTF-8");
      //int qnum = Integer.parseInt(request.getParameter("qnum"));
      //int onum = Integer.parseInt(request.getParameter("onum"));
      log.debug(cnum);
      log.debug(onum);
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/partthree/surveythree")
   public Map<String, String> handler03(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler01() 성공");
      //request.setCharacterEncoding("UTF-8");
      //int qnum = Integer.parseInt(request.getParameter("qnum"));
      //int onum = Integer.parseInt(request.getParameter("onum"));
      log.debug(cnum);
      log.debug(onum);
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
   @GetMapping("/partfour/surveyfour")
   public Map<String, String> handler04(@RequestParam(value="cnum", required=false) String cnum,
         @RequestParam(value="onum", required=false) String onum) {
      log.debug("handler01() 성공");
      //request.setCharacterEncoding("UTF-8");
      //int qnum = Integer.parseInt(request.getParameter("qnum"));
      //int onum = Integer.parseInt(request.getParameter("onum"));
      log.debug(cnum);
      log.debug(onum);
      Map<String, String> map = new HashMap<String, String>();
      map.put("cnum", cnum);
      map.put("onum", onum);
      return map;
   }
   
//   @GetMapping("surveytwo2")
//   public void handler02(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler02() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qnum = Integer.parseInt(request.getParameter("qnum"));
//      int onum = Integer.parseInt(request.getParameter("onum"));
//
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qnum", qnum);
//      jsonObject.addProperty("onum", onum);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }   
//   
//   @GetMapping("surveythree3")
//   public void handler03(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler03() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qnum = Integer.parseInt(request.getParameter("qnum"));
//      int onum = Integer.parseInt(request.getParameter("onum"));
//
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qnum", qnum);
//      jsonObject.addProperty("onum", onum);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }   
//   
//   @GetMapping("surveyfour4")
//   public void handler04(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler04() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qnum = Integer.parseInt(request.getParameter("qnum"));
//      int onum = Integer.parseInt(request.getParameter("onum"));
//
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qnum", qnum);
//      jsonObject.addProperty("onum", onum);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }   
   
   @GetMapping("/partone/sbuttonone")
   public Map<String, Integer> handler001(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         Model model, User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler001() 성공");
      request.setCharacterEncoding("UTF-8");
      log.debug(qone);
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      //session.setAttribute("myUser", user);
       //session.setAttribute("myCharacter", character);
      user = (User)session.getAttribute("myUser");
//      String email = new String();
//      email = "okay@naver.com";
      //user.setUserEmail(email);
      user.setUserI(nqtotal);
      user.setUserE(qtotal);
      
      
      //session.setAttribute("myUser", user);
      //model.addAttribute("myUser", user);
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
         Model model, User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler001() 성공");
      request.setCharacterEncoding("UTF-8");
      log.debug(qone);
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      user = (User)session.getAttribute("myUser");
      //String email = new String();
//      email = "okay@naver.com";
//      user.setUserEmail(email);
      user.setUserN(nqtotal);
      user.setUserS(qtotal);
      
      
      //session.setAttribute("myUser", user);
      //model.addAttribute("myUser", user);
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
         Model model, User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      log.debug("handler001() 성공");
      request.setCharacterEncoding("UTF-8");
      log.debug(qone);
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      
      user = (User)session.getAttribute("myUser");
      //String email = new String();
//      email = "okay@naver.com";
//      user.setUserEmail(email);
      user.setUserT(nqtotal);
      user.setUserF(qtotal);
      
      
      //session.setAttribute("myUser", user);
      //model.addAttribute("myUser", user);
      surveyservice.updateScoreThree(user);
      
      Map<String, Integer> map = new HashMap<String, Integer>();
      
      map.put("nqtotal", nqtotal);
      map.put("qtotal", qtotal);
      return map;
   }
   
   @GetMapping("/partfour/sbuttonfour")
   public String handler004(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
         @RequestParam(value="qfive", required=false) int qfive,
         Model model, User user, CharacterInfo characterinfo,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, JsonProcessingException {
      log.debug("handler001() 성공");
      request.setCharacterEncoding("UTF-8");
      log.debug(qone);
      int qtotal = qone + qtwo + qthree + qfour + qfive;
      int total = qtotal - 15;
      int nqtotal = total*-1;
      
      user = (User)session.getAttribute("myUser");
//      String email = new String();
//      email = "okay@naver.com";
//      user.setUserEmail(email);
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
      //model.addAttribute("myUser", user);
      surveyservice.updateScoreFour(user);
      
      User userbefore = (User)session.getAttribute("myUser");
      CharacterInfo character = 
            new CharacterInfo(100, 10, 10, 5 , 5 , 10, 10 , 10 , 30  ,0 );
      int result = characterservice.createCharacter(character);
      log.debug("잘되고있는거니");
      
      int maxresult = characterservice.maxCharacter();
      
      userbefore.setUserCharacter(maxresult); 
      
      
      User usercurrent = (User)session.getAttribute("myUser");
        int characternum = usercurrent.getUserCharacter();
        log.debug(characternum);
        //CharacterInfo characterinfo = new CharacterInfo();
        //characterinfo.setCharacterNum(characternum);   
        //log.debug(characterinfo);
        // 최신 캐릭터 num을 user계정에 반영해줘야 함
        
        surveyservice.updateUserCharacter(user);
        
        characterinfo = 
            new CharacterInfo(characternum,100, 10 + lone, 10 + ltwo, 5 + lthree, 5 + lfour, 10, 10 + lfive, 10 + lsix, 30 + lseven ,0 + leight);
         int characterresult = characterservice.updateCharacter(characterinfo);
        
        characterinfo = characterservice.getCharacter(characternum);
        
        session.setAttribute("myCharacter", characterinfo);
         characterinfo = (CharacterInfo)session.getAttribute("myCharacter");
        log.debug(characterinfo);
      
      ObjectMapper mapper = new ObjectMapper();
       String characterInfo = mapper.writeValueAsString(characterinfo);
      return characterInfo;
   }
   
   @GetMapping(value = "/partfour/sbuttonfour/user")
   public Map<String, String> getMbti(HttpSession session) throws JsonProcessingException {
	   
       User user = (User)session.getAttribute("myUser");
       MBTIResult mbti = mbtiservice.getMBTI(user.getUserMBTI());
       ObjectMapper mapper = new ObjectMapper();
       String userInfo = mapper.writeValueAsString(user);
       String mbtiInfo = mapper.writeValueAsString(mbti);
       Map<String,String> map = new HashMap<String, String>();
       map.put("mbtiInfo", mbtiInfo);
       map.put("userInfo", userInfo);       
       
       log.debug(userInfo);
       log.debug("제발");
      return map;
   }
   
//   @GetMapping("/partfour/sbuttonfour")
//   public Map<String, Object> handler004(@RequestParam(value="qone", required=false) int qone, @RequestParam(value="qtwo", required=false) int qtwo, 
//         @RequestParam(value="qthree", required=false) int qthree, @RequestParam(value="qfour", required=false) int qfour,
//         @RequestParam(value="qfive", required=false) int qfive,
//         Model model, User user, CharacterInfo characterinfo,HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler001() 성공");
//      request.setCharacterEncoding("UTF-8");
//      log.debug(qone);
//      int qtotal = qone + qtwo + qthree + qfour + qfive;
//      int total = qtotal - 15;
//      int nqtotal = total*-1;
//      
//      user = (User)session.getAttribute("myUser");
////      String email = new String();
////      email = "okay@naver.com";
////      user.setUserEmail(email);
//      user.setUserP(nqtotal);
//      user.setUserJ(qtotal);
//      String typeone = new String();
//      String typetwo = new String();
//      String typethree = new String();
//      String typefour = new String();
//      String mbti = new String();
//      
//      int lone = user.getUserE();
//      int ltwo = user.getUserI();
//      int lthree = user.getUserS();
//      int lfour = user.getUserN();
//      int lfive = user.getUserF();
//      int lsix = user.getUserT();
//      int lseven = user.getUserJ();
//      int leight = user.getUserP();
//      
//      if (lone > ltwo) {
//         typeone = "E";
//      } else {
//         typeone = "I";
//      }
//      
//      if (lthree > lfour) {
//         typetwo = "S";
//      } else {
//         typetwo = "N";
//      }
//
//      if (lfive > lsix) {
//         typethree = "F";
//      } else {
//         typethree = "T";
//      }
//      
//      if (lseven > leight) {
//         typefour = "J";
//      } else {
//         typefour = "P";
//      }
//      
//      mbti = typeone + typetwo + typethree + typefour;
//      user.setUserMBTI(mbti);
//      //model.addAttribute("myUser", user);
//      surveyservice.updateScoreFour(user);
//      
//      User userbefore = (User)session.getAttribute("myUser");
//      CharacterInfo character = 
//            new CharacterInfo(100, 10, 10, 5 , 5 , 10, 10 , 10 , 30  ,0 );
//      int result = characterservice.createCharacter(character);
//      log.debug("잘되고있는거니");
//      
//      int maxresult = characterservice.maxCharacter();
//      
//      userbefore.setUserCharacter(maxresult); 
//      
//      
//      User usercurrent = (User)session.getAttribute("myUser");
//        int characternum = usercurrent.getUserCharacter();
//        log.debug(characternum);
//        //CharacterInfo characterinfo = new CharacterInfo();
//        //characterinfo.setCharacterNum(characternum);   
//        //log.debug(characterinfo);
//        // 최신 캐릭터 num을 user계정에 반영해줘야 함
//        
//        surveyservice.updateUserCharacter(user);
//        
//        characterinfo = 
//            new CharacterInfo(characternum,100, 10 + lone, 10 + ltwo, 5 + lthree, 5 + lfour, 10, 10 + lfive, 10 + lsix, 30 + lseven ,0 + leight);
//         int characterresult = characterservice.updateCharacter(characterinfo);
//        
//        characterinfo = characterservice.getCharacter(characternum);
//        
//        session.setAttribute("myCharacter", characterinfo);
//         characterinfo = (CharacterInfo)session.getAttribute("myCharacter");
//        log.debug(characterinfo);
//      
//      Map<String, Object> map = new HashMap<String, Object>();
//      map.put("mbti", mbti);
//      map.put("Character", characterinfo.getCharacterNum());
//      //map.put("test", 11);
//      map.put("HP", characterinfo.getCharacterHP());
//      map.put("AD", characterinfo.getCharacterAD());
//      map.put("AP", characterinfo.getCharacterAP());
//      map.put("ADDefence", characterinfo.getCharacterADDefence());
//      map.put("APDefence", characterinfo.getCharacterAPDefence());
//      map.put("Speed", characterinfo.getCharacterSpeed());
//      map.put("HitRate", characterinfo.getCharacterHitRate());
//      map.put("AvoidanceRate", characterinfo.getCharacterAvoidanceRate());
//      map.put("Critical", characterinfo.getCharacterCritical());
//      map.put("AdditionalDmg", characterinfo.getCharacterAdditionalDmg());
//      return map;
//   }
   
//   @PutMapping("sbutton1")
//   public void handler001(Model model, @ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler001() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qone = Integer.parseInt(request.getParameter("qone"));
//      int qtwo = Integer.parseInt(request.getParameter("qtwo"));
//      int qthree = Integer.parseInt(request.getParameter("qthree"));
//      int qfour = Integer.parseInt(request.getParameter("qfour"));
//      int qfive = Integer.parseInt(request.getParameter("qfive"));
//      int qtotal = qone + qtwo + qthree + qfour + qfive;
//      int total = qtotal - 15;
//      int nqtotal = total*-1;
//      
//      //user = (User)session.getAttribute("myUser");
//      user.setUserI(nqtotal);
//      user.setUserE(qtotal);
//      log.debug(user);
//      
//      // session.setAttribute("myUser", user);
//      model.addAttribute("myUser", user);
//      surveyservice.updateScoreOne(user);
//      
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qone", qone);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }
   
//   @PutMapping("sbutton2")
//   public void handler002(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler002() 괜찮슴");
//      request.setCharacterEncoding("UTF-8");
//      int qone = Integer.parseInt(request.getParameter("qone"));
//      int qtwo = Integer.parseInt(request.getParameter("qtwo"));
//      int qthree = Integer.parseInt(request.getParameter("qthree"));
//      int qfour = Integer.parseInt(request.getParameter("qfour"));
//      int qfive = Integer.parseInt(request.getParameter("qfive"));
//      int qtotal = qone + qtwo + qthree + qfour + qfive;
//      int total = qtotal - 15;
//      int nqtotal = total*-1;
//      
//      //user = (User)session.getAttribute("myUser");
//      user.setUserN(nqtotal);
//      user.setUserS(qtotal);
//      
//      //session.setAttribute("myUser", user);
//      log.debug(user);
//      model.addAttribute("myUser", user);
//      surveyservice.updateScoreTwo(user);
//      
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qone", qone);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }
//   
//   @PutMapping("sbutton3")
//   public void handler003(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      log.debug("handler003() 성공");
//      request.setCharacterEncoding("UTF-8");
//      int qone = Integer.parseInt(request.getParameter("qone"));
//      int qtwo = Integer.parseInt(request.getParameter("qtwo"));
//      int qthree = Integer.parseInt(request.getParameter("qthree"));
//      int qfour = Integer.parseInt(request.getParameter("qfour"));
//      int qfive = Integer.parseInt(request.getParameter("qfive"));
//      int qtotal = qone + qtwo + qthree + qfour + qfive;
//      int total = qtotal - 15;
//      int nqtotal = total*-1;
//      
//      //user = (User)session.getAttribute("myUser");
//      user.setUserT(nqtotal);
//      user.setUserF(qtotal);
//      
//      //session.setAttribute("myUser", user);
//      log.debug(user);
//      model.addAttribute("myUser", user);
//      surveyservice.updateScoreThree(user);
//      
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qone", qone);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }
//   
//   @PutMapping("sbutton4")
//   public void handler004(Model model,@ModelAttribute("myUser") User user, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      request.setCharacterEncoding("UTF-8");
//      int qone = Integer.parseInt(request.getParameter("qone"));
//      int qtwo = Integer.parseInt(request.getParameter("qtwo"));
//      int qthree = Integer.parseInt(request.getParameter("qthree"));
//      int qfour = Integer.parseInt(request.getParameter("qfour"));
//      int qfive = Integer.parseInt(request.getParameter("qfive"));
//      int qtotal = qone + qtwo + qthree + qfour + qfive;
//      int total = qtotal - 15;
//      int nqtotal = total*-1;
//      String typeone = new String();
//      String typetwo = new String();
//      String typethree = new String();
//      String typefour = new String();
//      String mbti = new String();
//      
//      user.setUserP(nqtotal);
//      user.setUserJ(qtotal);
//      model.addAttribute("myUser", user);
//      User usertotal = (User)session.getAttribute("myUser");
//      //session.setAttribute("myUser", user);
//      
//      int lone = usertotal.getUserE();
//      int ltwo = usertotal.getUserI();
//      int lthree = usertotal.getUserS();
//      int lfour = usertotal.getUserN();
//      int lfive = usertotal.getUserF();
//      int lsix = usertotal.getUserT();
//      int lseven = usertotal.getUserJ();
//      int leight = usertotal.getUserP();
//      
//      if (lone > ltwo) {
//         typeone = "E";
//      } else {
//         typeone = "I";
//      }
//      
//      if (lthree > lfour) {
//         typetwo = "S";
//      } else {
//         typetwo = "N";
//      }
//
//      if (lfive > lsix) {
//         typethree = "F";
//      } else {
//         typethree = "T";
//      }
//      
//      if (lseven > leight) {
//         typefour = "J";
//      } else {
//         typefour = "P";
//      }
//      
//      mbti = typeone + typetwo + typethree + typefour;
//      user.setUserMBTI(mbti);
//      surveyservice.updateScoreFour(user);
//      log.debug(user);
//      
//      CharacterInfo character = 
//            new CharacterInfo(usertotal.getUserCharacter(),100, 10 + lone, 10 + ltwo, 5 + lthree, 5 + lfour, 10, 10 + lfive, 10 + lsix, 30 + lseven ,0 + leight);
//      int characterresult = characterservice.updateCharacter(character);
//      log.debug("안녕");
//      
//      Gson gson = new Gson();
//      JsonObject jsonObject = new JsonObject();
//      jsonObject.addProperty("qone", qone);
//      String find = gson.toJson(jsonObject);
//      response.getWriter().write(find);
//   }
      
      
   
}
