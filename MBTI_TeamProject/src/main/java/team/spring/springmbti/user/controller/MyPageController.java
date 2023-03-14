package team.spring.springmbti.user.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;

@Controller
@SessionAttributes(value= { "myCharacter","myUser","battleCharacter","battleUser" })
@RequestMapping(value = "mypage")
public class MyPageController {
   
   Logger log = LogManager.getLogger("case3");
   
   @Autowired
   private UserService service;
   
   @Autowired
   private CharacterService cService;
   
   @ModelAttribute("myCharacter")
   public CharacterInfo createCharacter() {
      CharacterInfo character = new CharacterInfo();
      return character;
   }
   @ModelAttribute("myUser")
   public User putUser() {
      User user = new User();
      return user;
   }
   
   @ModelAttribute("battleCharacter")
   public CharacterInfo createBattleCharacter() {
      CharacterInfo character = new CharacterInfo();
      return character;
   }
   @ModelAttribute("battleUser")
   public User putBattleUser() {
      User user = new User();
      return user;
   }
   
   @GetMapping
   public String myPage(HttpSession session, Model model, @ModelAttribute("myCharacter") CharacterInfo character) {
      
      User user = (User)session.getAttribute("myUser");
      int userNum = service.getUserNum(user);
      user = service.getUserInfo(Integer.toString(userNum));
      int userCharacterNum = service.getUserCharacterNum(userNum);
      character = cService.getCharacter(userCharacterNum);
      log.debug(character);
      model.addAttribute("myUser", user);
      model.addAttribute("myCharacter", character);
      
      return "userMyPage";
   }
   
   @DeleteMapping(value = "character")
   public void deleteCharacter(HttpSession session,
         @ModelAttribute("myUser") User user, 
         HttpServletResponse response,
         HttpServletRequest request)throws ServletException, IOException {
      
      log.debug("@DeleteMapping 호출");
      log.debug("num : " + request.getParameter("num"));
      
      request.setCharacterEncoding("UTF-8");
       int qone = Integer.parseInt(request.getParameter("num"));
      
      user = (User)session.getAttribute("myUser");
      log.debug("여기로 오면 좋겠어");
      int userNum = service.getUserNum(user);
      int userCharacterNum = service.getUserCharacterNum(userNum);
      log.debug(userCharacterNum);      
      int count = service.deleteCharacter(userCharacterNum);
      if(count==1) {
         log.debug("성공적으로 캐릭터 삭제 완료!");
      }
      Gson gson = new Gson();
       JsonObject jsonObject = new JsonObject();
       jsonObject.addProperty("num",qone);
       String find = gson.toJson(jsonObject);
       response.getWriter().write(find);
   }
    
   @DeleteMapping
   public String deleteUser(HttpSession session) {
      
      User user = (User)session.getAttribute("myUser");
         log.debug(user);
      String userEmail = user.getUserEmail();
         log.debug(userEmail);
         log.debug("성공적으로 회원 탈퇴 완료!");
      int count = service.deleteUser(userEmail);
      
      
      return "redirect:/resources/main.html";
   }
	
	@GetMapping(value = "battleuser")
	public String getUserInfo(HttpSession session, Model model, String battleUserNum, 
			@ModelAttribute("battleCharacter") CharacterInfo character, @ModelAttribute("battleUser") User user) {

		user = service.getUserInfo(battleUserNum);
		character = cService.getCharacter(user.getUserCharacter());
		log.debug("Test " + character);
		
		model.addAttribute("battleUser", user);
		model.addAttribute("battleCharacter", character);
		return "prepBattle";
	}
	
}
