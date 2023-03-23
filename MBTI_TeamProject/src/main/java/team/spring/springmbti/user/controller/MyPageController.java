package team.spring.springmbti.user.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.service.LoginService;
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
   
   @Autowired
   private LoginService loginservice;
   
//   @ModelAttribute("myCharacter")
//   public CharacterInfo createCharacter() {
//      CharacterInfo character = new CharacterInfo();
//      return character;
//   }
//   
//   @ModelAttribute("battleCharacter")
//   public CharacterInfo createBattleCharacter() {
//      CharacterInfo character = new CharacterInfo();
//      return character;
//   }
//   @ModelAttribute("battleUser")
//   public User putBattleUser() {
//      User user = new User();
//      return user;
//   }
   
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
   public String deleteCharacter(
          @RequestBody Map<String,String> data)  throws JsonProcessingException {
      
      log.debug("@DeleteMapping 호출");
      log.debug(data.get("userCharacterNum"));
      
       
      int count = service.deleteCharacter(Integer.parseInt(data.get("userCharacterNum")));
      if(count==1) {
         log.debug("성공적으로 캐릭터 삭제 완료!");
      }
      User user = new User();
      user = loginservice.getUser(data.get("userEmail"));
      ObjectMapper mapper = new ObjectMapper();
      String userInfo = mapper.writeValueAsString(user);
      return userInfo;
     
   }
    
   @DeleteMapping("deleteUser")
   public String deleteUser(HttpSession session,@RequestBody Map<String,String> data) {
      
//      User user = (User)session.getAttribute("myUser");
      log.debug(data.get("userEmail"));
//      String userEmail = user.getUserEmail();
         
      int count = service.deleteUser(data.get("userEmail"));
      log.debug("성공적으로 회원 탈퇴 완료!");
      
      return "성공적으로 회원 탈퇴";
   }
	
	@GetMapping(value = "battleuser")
	public Map<String, String> getUserInfo(HttpSession session, @RequestParam(value="battleUserNum",
							required=false) String battleUserNum) throws JsonProcessingException {

		Logger log = LogManager.getLogger("case3");
		
		boolean checkUser = loginservice.checkExistUser(battleUserNum);
		Map<String, String> map = new HashMap<String, String>();
		if (!checkUser) {
			map.put("nonUser", "nonUser");
			return map;
		} else {
			User user = new User();
			user = service.getUserInfo(battleUserNum);
			CharacterInfo character = new CharacterInfo();
			character = cService.getCharacter(user.getUserCharacter());
			session.setAttribute("battleUser", user);
			session.setAttribute("battleCharacter", character);
			
			ObjectMapper mapper = new ObjectMapper();
		    String competionUserInfo = mapper.writeValueAsString(user);
		    String competionCharacterInfo = mapper.writeValueAsString(character);
		    map.put("competionUserInfo", competionUserInfo);
		    map.put("competionCharacterInfo", competionCharacterInfo);
			    
			return map;
		}
	}
	@GetMapping(value = "battlecharacter")
	public String getCharacterInfo(HttpSession session) throws JsonProcessingException {
		
		Logger log = LogManager.getLogger("case3");
		
		CharacterInfo character = (CharacterInfo)session.getAttribute("battleCharacter");
	
		log.debug("적 캐릭터" + character);
		ObjectMapper mapper = new ObjectMapper();
	    String competionCharacterInfo = mapper.writeValueAsString(character);
		return competionCharacterInfo;
		
	}
	
	@GetMapping(value ="useraccpet")
	public int changeUserAeccpet(HttpSession session) {
		
		User user = (User)session.getAttribute("myUser");
		
		int reuslt = service.changeUserAccept(user);
		
		return reuslt;
	}
	
//	@GetMapping(value = "mbti")
//	public void setUserMbti(HttpSession session, @RequestParam(value="userMbti",
//			required=false) String userMbti) {
//		
//		log.debug("test" + userMbti);
//		User user = (User)session.getAttribute("myUser");
//		user.setUserMBTI(userMbti);
//		session.setAttribute("myUser", user);
//		
//	}
	
	
}
