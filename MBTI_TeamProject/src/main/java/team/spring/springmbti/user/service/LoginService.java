package team.spring.springmbti.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.user.dao.UserDao;
import team.spring.springmbti.user.vo.User;

@Service
public class LoginService {
	
	@Autowired
	private UserDao dao;
	
	Logger log = LogManager.getLogger("case3");

	public void userRegistration(User user) {
		int count = dao.insertUser(user);
		if(count==1) {
			log.debug("유저 등록 성공");
		}else {
			log.debug("유저 등록 실패");
		}
		
	}

	public boolean checkEmail(String userEmail) {
		
		boolean canRegister = false;
		
		User user = dao.emailCheck(userEmail);
		
		if(user==null) {
			canRegister=true;
		}
		
		return canRegister;
	}

	public boolean checkCharacter(String userEmail) {
		
		boolean isExist = false;
		
		int characterNum = dao.characterCheck(userEmail);
		
		if(characterNum > 1) {
			isExist=true;
		}
		
		return isExist;
	}

	public boolean checkExistUser(String battleUserNum) {
		
		boolean isExist = false;
		
		int cnt = dao.checkExistUser(battleUserNum);
		
		if(cnt >= 1) {
			isExist=true;
		}
		
		return isExist;
	}
	
	
	
}
