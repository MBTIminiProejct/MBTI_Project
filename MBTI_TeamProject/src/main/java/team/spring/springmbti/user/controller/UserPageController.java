package team.spring.springmbti.user.controller;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.mbti.service.MBTIService;
import team.spring.springmbti.mbti.vo.MBTIResult;
import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;

@RestController
@RequestMapping(value="userpage", produces="application/json")
public class UserPageController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private UserService service;
	
	@Autowired
	private MBTIService mbtiservice;
	
	@GetMapping(value="readUserPage")
	public String userPage(@RequestParam(value="index", required=false) 
					int index,HttpSession session, Model model)throws JsonProcessingException{
		log.debug("Test" + index);
		int realindex = index + 1;
		User user = service.getRankingOne(realindex);
		log.debug(user);
		 ObjectMapper mapper = new ObjectMapper();
	       String userInfo = mapper.writeValueAsString(user);
		log.debug(userInfo);
		return userInfo;
	}
	
	@GetMapping(value="readMbtiPage")
	public String mbtiPage(@RequestParam(value="index", required=false) 
					int index,HttpSession session, Model model)throws JsonProcessingException{
		log.debug("Test" + index);
		int realindex = index + 1;
		User user = service.getRankingOne(realindex);
		MBTIResult mbti = mbtiservice.getMBTI(user.getUserMBTI());
		
		 ObjectMapper mapper = new ObjectMapper();
		 String mbtiImg = mapper.writeValueAsString(mbti);
	      
		
		return mbtiImg;
	}
	

}
