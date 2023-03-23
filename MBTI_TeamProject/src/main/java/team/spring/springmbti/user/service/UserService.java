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
		for(User user : list) {
			log.debug(user);
		}
		return list;
	}
	
public User getRankingOne(int cnt) {
		
		User user = dao.getRankingOne(cnt);
		
		return user;
	}
	
	public User getUserPage(String count) {
		User user = dao.getUserPage(count);
		return user;
	}

	public int changeUserAccept(User user) {
		
		int result = 0;
				
		if (user.getUserAcceptance().equals("대결불가")) {
			result = dao.chageUserAcceptYes(user.getUserNum());
		} else {
			result = dao.chageUserAcceptNo(user.getUserNum());
		}
		return result;
	}

}
