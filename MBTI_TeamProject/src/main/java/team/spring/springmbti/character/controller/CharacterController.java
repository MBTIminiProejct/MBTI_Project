package team.spring.springmbti.character.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;


@Controller
public class CharacterController {
	
	@Autowired
	private CharacterService service;

	Logger log = LogManager.getLogger("case3");
	@PostMapping(value = "character")
	public String createCharacter(Model model) {
		log.debug("캐릭터생성");
		CharacterInfo character = new CharacterInfo(100, 10, 10, 5, 5, 10, 10, 10, 30 ,0);
		
		int result = service.createCharacter(character);
		log.debug(result);
		
		return "resultPage";
	}
	
}