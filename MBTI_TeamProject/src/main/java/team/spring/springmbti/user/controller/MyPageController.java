package team.spring.springmbti.user.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.HttpServerErrorException;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;

@RestController
//@SessionAttributes(value= { "myCharacter","myUser","battleCharacter","battleUser" })
@RequestMapping(value = "mypage", produces="application/json")
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
   public void myPage(HttpSession session, Model model, @ModelAttribute("myCharacter") CharacterInfo character) {
      
      User user = (User)session.getAttribute("myUser");
      int userNum = service.getUserNum(user);
      user = service.getUserInfo(Integer.toString(userNum));
      int userCharacterNum = service.getUserCharacterNum(userNum);
      character = cService.getCharacter(userCharacterNum);
      log.debug(character);
      model.addAttribute("myUser", user);
      model.addAttribute("myCharacter", character);
      
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
	public Map<String, String> getUserInfo(HttpSession session, @RequestParam(value="battleUserNum", required=false) String battleUserNum) {

		User user = new User();
		user = service.getUserInfo(battleUserNum);
		CharacterInfo character = new CharacterInfo();
		character = cService.getCharacter(user.getUserCharacter());
		session.setAttribute("battleUser", user);
		session.setAttribute("battleCharacter", character);
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("bNum", user.getUserNum());
		map.put("bNickname", user.getUserName());
		map.put("bEmail", user.getUserEmail());
		map.put("bProfile", user.getUserProfile());
		map.put("bChracter", Integer.toString(user.getUserCharacter()));
		map.put("bMbti", user.getUserMBTI());
		map.put("bWin", Integer.toString(user.getUserWin()));
		map.put("bDefeat", Integer.toString(user.getUserDefeat()));
		map.put("bPoint", Integer.toString(user.getUserPoint()));
		map.put("bItem", Integer.toString(user.getUserItem()));
		map.put("bAcceptance", user.getUserAcceptance());
		
		map.put("bCNum", Integer.toString(character.getCharacterNum()));
		map.put("bHp", Double.toString(character.getCharacterHP()));
		map.put("bAd", Double.toString(character.getCharacterAD()));
		map.put("bAp", Double.toString(character.getCharacterAP()));
		map.put("bAdDefence", Double.toString(character.getCharacterADDefence()));
		map.put("bApDefence", Double.toString(character.getCharacterAPDefence()));
		map.put("bSpeed", Double.toString(character.getCharacterSpeed()));
		map.put("bHitRate", Double.toString(character.getCharacterHitRate()));
		map.put("bAvoidanceRate", Double.toString(character.getCharacterAvoidanceRate()));
		map.put("bCritical", Double.toString(character.getCharacterCritical()));
		map.put("bAdditionalDmg", Double.toString(character.getCharacterAdditionalDmg()));
		log.debug(map);
		log.debug(battleUserNum);
		return map;
		
	}
	
}
