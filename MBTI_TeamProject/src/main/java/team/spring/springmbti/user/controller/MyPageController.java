package team.spring.springmbti.user.controller;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	
	@GetMapping(value = "deleteCharacter")
	public String deleteCharacter(HttpSession session,@ModelAttribute("myUser") User user) {
		
		user = (User)session.getAttribute("myUser");
		int userNum = service.getUserNum(user);
		int userCharacterNum = service.getUserCharacterNum(userNum);
		int count = service.deleteCharacter(userCharacterNum);
		if(count==1) {
			log.debug("성공적으로 캐릭터 삭제 완료");
		}
		
		return "myPage";
	}
	 
	@RequestMapping(value="myPageDeleteUser", method = RequestMethod.POST)
	public String deleteUser(HttpSession session) {
		
		User user = (User)session.getAttribute("myUser");
//			log.debug(user);
		String userEmail = user.getUserEmail();
//			log.debug(userEmail);
//			log.debug("회원정보 읽기 성공");
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
