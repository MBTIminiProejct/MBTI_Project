package team.spring.springmbti.character.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.user.vo.User;


@Controller
@SessionAttributes(value= {"myUser"})
public class CharacterController {
	
	
	Logger log = LogManager.getLogger("case3");
	
	@ModelAttribute("myUser")
	public User createUser() {
		User user = new User();
		return user;
	}
	
	@PostMapping(value = "character")
	public String createCharacter(Model model,@ModelAttribute("myUser") User user, HttpSession session) {
		log.debug("캐릭터생성");
		
		
		return "resultPage";
	}
	
}