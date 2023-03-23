package team.spring.springmbti.battle.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.battle.service.BattleService;
import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;

@RestController
@RequestMapping(value = "battle",produces = "application/json")
//@SessionAttributes(value= { "myCharacter","myUser","battleCharacter","battleUser" })
public class BattleController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	BattleService service;
	
	@Autowired
	UserService uService;
	
	@Autowired
	CharacterService cService;
	
	
	@GetMapping
	public String battle(@RequestParam(value="battleField", required=false) String battleField, @RequestParam(value="userNum",
			required=false) String userNum, @RequestParam(value="battleUserNum", 
			required=false) String battleUserNum) throws JsonProcessingException {
		
		log.debug("들어온건가");
		User myUser = uService.getUserInfo(userNum);
	    CharacterInfo myCharacter = cService.getCharacter(myUser.getUserCharacter());
	    log.debug(myUser);
	    log.debug(myCharacter);
	    
		CharacterInfo battleCharacter = cService.getCharacter(Integer.valueOf(battleUserNum));
		User battleUser = uService.getUserInfo(battleUserNum);
		log.debug(battleUser);
	    log.debug(battleCharacter);
		
		BattleLog battleLog = service.prepBattle(myCharacter, battleCharacter, myUser, battleUser, battleField);
		ObjectMapper mapper = new ObjectMapper();
	    String battleLogInfo = mapper.writeValueAsString(battleLog);
		return battleLogInfo;
	}
	
//	@GetMapping(value = "searchBattleUser")
//	public String searchBattleUser(HttpSession session) {
//		
//		User user = (User)session.getAttribute("myUser");
//		// 내캐릭터 받아오기
//		log.debug(user);
//		return "searchBattleUser";
//	}
	
	@GetMapping(value = "ranking")
	public JSONArray getRanking() {
		List<User> list = uService.getRanking(10);
		JSONArray arr = new JSONArray();
		for(User user : list) {
			JSONObject obj=new JSONObject();
			obj.put("userName",user.getUserName());
			obj.put("userNum",user.getUserNum());
			obj.put("userMBTI",user.getUserMBTI());
			obj.put("userWin",user.getUserWin());
			obj.put("userPoint",user.getUserPoint());
			arr.add(obj);
		}
		

		return arr;
	}
}
