package team.spring.springmbti.user.controller;


import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import team.spring.springmbti.user.service.OAuthService;


@Controller
@RequestMapping(value="logout")
public class LogoutController {
	
	
	
	@Autowired
	private OAuthService kakaoservice;
	
	Logger log = LogManager.getLogger("case3");
	
	
	@GetMapping
    public String logout(HttpSession session) {
        String access_Token = (String)session.getAttribute("access_Token");

        if(access_Token != null && !"".equals(access_Token)){

        	kakaoservice.unlink(access_Token);
            session.removeAttribute("access_Token");
            session.removeAttribute("myUser");
            log.debug("로그아웃 성공");
        }else{
            System.out.println("access_Token is null");
            
        }
        
        return "redirect:/resources/main.html";
    }
	
	
}
