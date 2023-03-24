package team.spring.springmbti.user.controller;



import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.mbti.service.MBTIService;
import team.spring.springmbti.mbti.vo.MBTIResult;
import team.spring.springmbti.user.service.LoginService;
import team.spring.springmbti.user.service.OAuthService;
import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;



@RestController
//@SessionAttributes(value= {"myUser"})
@RequestMapping(value="login",produces="application/json")
public class LoginController {
	
	@Autowired
	private LoginService loginservice;
	
	@Autowired
	private OAuthService kakaoservice;
	
	@Autowired
	private UserService service;
	
	@Autowired
	private CharacterService cService;
	
	@Autowired
	private MBTIService mbtiservice;
	
	@ModelAttribute("myCharacter")
	   public CharacterInfo createCharacter() {
	      CharacterInfo character = new CharacterInfo();
	      return character;
	   }
	
	Logger log = LogManager.getLogger("case3");
	
	
	@GetMapping
	public  Map<String,String> Login(@RequestParam(value="nickname", required=false) String nickname,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="profile", required=false) String profile,
			HttpSession session, Model model) throws JsonProcessingException {
		log.debug("nickname - " + nickname);
		log.debug("email - " + email);
		log.debug("profile - " + profile);
		
		User user = new User();
		user.setUserEmail(email);
		user.setUserName(nickname);
		user.setUserProfile(profile);
		
		boolean canRegister = loginservice.checkEmail(user.getUserEmail());
        
        model.addAttribute("canRegister",canRegister);
        
        if(canRegister) {
        	loginservice.userRegistration(user);
        }else {
        	boolean isOut = loginservice.checkIsOut(user.getUserEmail());
        	if(isOut) {
        		loginservice.reRegistration(user.getUserEmail());
        		log.debug("재가입");
        	}else {
        		log.debug("이미 생성된 아이디가 존재합니다.");
        	}
        }
        int userNum = service.getUserNum(user);
        user = service.getUserInfo(Integer.toString(userNum));

	    CharacterInfo character = new CharacterInfo();
	    character = cService.getCharacter(user.getUserCharacter());
	    
	    session.setAttribute("myUser", user);
	    log.debug(user);
	    session.setAttribute("myCharacter", character);
	    
	    MBTIResult mbtiresult = new MBTIResult();
	    mbtiresult = mbtiservice.getMBTI(user.getUserMBTI());
	    if(mbtiresult==null) {
	    	mbtiresult = new MBTIResult();
	    	mbtiresult.setMBTIImgurl("https://i.imgur.com/oMQzKAv.jpg");
	    }
	    
	    ObjectMapper mapper = new ObjectMapper();
	    String userInfo = mapper.writeValueAsString(user);
	    String characterInfo = mapper.writeValueAsString(character);
	    String mbtiresultInfo = mapper.writeValueAsString(mbtiresult);
	    Map<String,String> map = new HashMap<String, String>();
	    map.put("userInfo",userInfo);
	    map.put("characterInfo",characterInfo);
	    map.put("mbtiresultInfo",mbtiresultInfo);
	    
	    
		return map;
		
	}
	
	@GetMapping(value = "character")
	public String getCharacterInfo(HttpSession session) throws JsonProcessingException {
		CharacterInfo character = (CharacterInfo)session.getAttribute("myCharacter");
		System.out.println("캐릭터 세션" + character);
		ObjectMapper mapper = new ObjectMapper();
	    String characterInfo = mapper.writeValueAsString(character);
	    System.out.println("캐릭터 정보!!!!!!!!!!!!!!!" + characterInfo);
		return characterInfo;
	}
	
}
