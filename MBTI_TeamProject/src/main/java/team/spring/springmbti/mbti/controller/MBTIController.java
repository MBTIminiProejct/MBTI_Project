package team.spring.springmbti.mbti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "mbti")
public class MBTIController {
	
	@GetMapping("test1")
	public String myTest1() {
		
		return "myPage";
	}
	
//	@GetMapping("share")
//	public String kakaoShare() {
//		
//		return "resultPage";
}
