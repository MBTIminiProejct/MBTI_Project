package team.spring.springmbti.user.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import team.spring.springmbti.user.service.UserService;
import team.spring.springmbti.user.vo.User;

@RestController
@RequestMapping(value="userpage", produces="application/json")
public class UserPageController {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private UserService service;
	
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
	
//	@PostMapping(value = "readUserPage")
//	public JSONArray getRanking() {
//		User user = service.getRankingOne(2);
//		JSONArray arr = new JSONArray();
//		for(User user : user) {
//			JSONObject obj=new JSONObject();
//			obj.put("userName",user.getUserName());
//			obj.put("userNum",user.getUserNum());
//			obj.put("userMBTI",user.getUserMBTI());
//			obj.put("userWin",user.getUserWin());
//			obj.put("userPoint",user.getUserPoint());
//			arr.add(obj);
//		}
//		
//
//		return arr;
//	}
}
