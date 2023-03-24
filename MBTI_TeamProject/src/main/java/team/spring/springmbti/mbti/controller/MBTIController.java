package team.spring.springmbti.mbti.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MBTIController {
	@GetMapping("test1")
	public String myTest1() {
		
		return "myPage";
	}
	
}
