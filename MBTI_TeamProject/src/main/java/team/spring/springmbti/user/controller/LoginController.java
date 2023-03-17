package team.spring.springmbti.user.controller;



import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import team.spring.springmbti.character.service.CharacterService;
import team.spring.springmbti.character.vo.CharacterInfo;
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
	
	@ModelAttribute("myCharacter")
	   public CharacterInfo createCharacter() {
	      CharacterInfo character = new CharacterInfo();
	      return character;
	   }
	
	Logger log = LogManager.getLogger("case3");
	
	
	@GetMapping
	public Map<String, String> test(@RequestParam(value="nickname", required=false) String nickname,
			@RequestParam(value="email", required=false) String email,
			@RequestParam(value="profile", required=false) String profile,
			HttpSession session) {
		log.debug("nickname - " + nickname);
		log.debug("email - " + email);
		log.debug("profile - " + profile);
		
		User user = new User();
		user.setUserEmail(email);
		user.setUserName(nickname);
		user.setUserProfile(profile);
        int userNum = service.getUserNum(user);
        user = service.getUserInfo(Integer.toString(userNum));
	    int userCharacterNum = service.getUserCharacterNum(userNum);
	    CharacterInfo character = new CharacterInfo();
	    character = cService.getCharacter(userCharacterNum);
	    
	    Map<String, String> map = new HashMap<String, String>();
	    map.put("num", user.getUserNum());
		map.put("nickname", nickname);
		map.put("email", email);
		map.put("profile", profile);
		map.put("chracter", Integer.toString(user.getUserCharacter()));
		map.put("mbti", user.getUserMBTI());
		map.put("win", Integer.toString(user.getUserWin()));
		map.put("defeat", Integer.toString(user.getUserDefeat()));
		map.put("point", Integer.toString(user.getUserPoint()));
		map.put("item", Integer.toString(user.getUserItem()));
		map.put("acceptance", user.getUserAcceptance());
		
		map.put("cNum", Integer.toString(character.getCharacterNum()));
		map.put("hp", Double.toString(character.getCharacterHP()));
		map.put("ad", Double.toString(character.getCharacterAD()));
		map.put("ap", Double.toString(character.getCharacterAP()));
		map.put("adDefence", Double.toString(character.getCharacterADDefence()));
		map.put("apDefence", Double.toString(character.getCharacterAPDefence()));
		map.put("speed", Double.toString(character.getCharacterSpeed()));
		map.put("hitRate", Double.toString(character.getCharacterHitRate()));
		map.put("avoidanceRate", Double.toString(character.getCharacterAvoidanceRate()));
		map.put("critical", Double.toString(character.getCharacterCritical()));
		map.put("additionalDmg", Double.toString(character.getCharacterAdditionalDmg()));
		
		
	    session.setAttribute("myUser", user);
	    session.setAttribute("myCharacter", character);
	    
		return map;
		
	}
	
//	@GetMapping
//	public ResponseEntity<Map<String,String>> method01(String id, String name){
//		
//		log.debug("Get방식으로 호출됨");
//		log.debug(id + "," + name);
//		
//		Map<String, String> map = new HashMap<String, String>();
//		map.put("id", id);
//		map.put("name", name);
//		
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(new MediaType("application","json",
//				Charset.forName("UTF-8")));
//		// 결국 responseEntitiy가 덮어 씌우기 때문에 produces속성에 어떤 값이 써있든 상관없이 헤더에 쓰인 값으로 출력됨
//		
//		return new ResponseEntity<Map<String,String>>(map,headers,HttpStatus.OK);
//	}
	
	
//	@GetMapping
//    public String home(@RequestParam(value = "code", required = false) String code,Model model,@ModelAttribute("myUser") User user,HttpSession session) throws Exception{
//        log.debug("#########" + code);
//        String access_Token = kakaoservice.getKakaoAccessToken(code);
//        HashMap<String, Object> userInfo = kakaoservice.getUserInfo(access_Token);
//        log.debug("###access_Token#### : " + access_Token);
//        log.debug("###useremail#### : " + userInfo.get("email"));
//        log.debug("###nickname#### : " + userInfo.get("nickname"));
//        log.debug("###profile_image#### : " + userInfo.get("profile_image"));
//        
//        session.setAttribute("access_Token", access_Token);
//        
//        if(userInfo.get("email")==null)
//        {
//        	return "redirect:https://kauth.kakao.com/oauth/authorize?client_" + 
//        			"id=26c4c3e8460d88a695130b78307910f4&redirect_uri=http://localhost:8080/springmbti/" + 
//        			"login&response_type=code"; 
//        }
//        
//        String userName = (String) userInfo.get("nickname");
//        String userEmail = (String) userInfo.get("email");
//        String userProfile = (String) userInfo.get("profile_image");
//        
////      User user = new User();
//        user.setUserName(userName);
//        user.setUserEmail(userEmail);
//        user.setUserProfile(userProfile);
//        
//        boolean canRegister = loginservice.checkEmail(userEmail);
//        
//        model.addAttribute("canRegister",canRegister);
//        
//        if(canRegister) {
//        	loginservice.userRegistration(user);
//        }else {
//        	log.debug("이미 생성된 아이디가 존재합니다.");
//        	boolean	isExist = loginservice.checkCharacter(userEmail);
//        	model.addAttribute("isExist",isExist);
//        }
//        model.addAttribute("user", user);
//        
//        return "myPage";
//    }
//	
//	
}
