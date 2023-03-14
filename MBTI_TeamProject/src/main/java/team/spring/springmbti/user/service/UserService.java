package team.spring.springmbti.user.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.user.dao.UserDao;
import team.spring.springmbti.user.vo.User;

@Service
public class UserService {

	Logger log = LogManager.getLogger("case3");
	
	@Autowired
	private UserDao dao;
	
	public int getUserNum(User user) {
		
		int userNum = dao.getUserNum(user.getUserEmail());
		
		return userNum;
	}

	public int getUserCharacterNum(int userNum) {
		
		int userCharacterNum = dao.getUserCharacterNum(userNum);
		
		return userCharacterNum;
	}
	
	public int deleteUser(String userEmail) {
		
		int count = dao.deleteUser(userEmail);
		
		return count;
	}

	public int deleteCharacter(int userCharacterNum) {
		
		int count = dao.deleteCharacter(userCharacterNum);
		return count;
	}

	public User getUserInfo(String battleUserNum) {
		
		User user = dao.getUserInfo(battleUserNum);
		return user;
	}

	public List<User> getRanking(int cnt) {
		
		List<User> list = dao.getRanking(cnt);
		for (int i = 0; i <3; i++) {
			log.debug(list.get(i));
		}
		return list;
	}

}
