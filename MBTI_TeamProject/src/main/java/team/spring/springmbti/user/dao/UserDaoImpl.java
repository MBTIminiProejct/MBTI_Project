package team.spring.springmbti.user.dao;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.user.vo.User;



@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SqlSession session;
	
	Logger log = LogManager.getLogger("case3");
	
	@Override
	public User emailCheck(String userEmail) {
		
		User user = session.selectOne("myUser.checkEmail",userEmail);
		
		return user;
	}

	@Override
	public int insertUser(User user) {

		int count = session.insert("myUser.insertUser", user);
		
		if(count==1) {
			log.debug("유저 등록 커밋 성공");
		}else {
			log.debug("유저 등록 실패, 롤백");
		}
		return count;
	}

	@Override
	public int getUserNum(String userEmail) {
		
		int userNum = session.selectOne("myUser.getUserNum", userEmail);
		return userNum;
	}

	@Override
	public int getUserCharacterNum(int userNum) {
		
		log.debug(userNum);
		int userCharacterNum = session.selectOne("myUser.getUserCharacter", userNum);
		return userCharacterNum;
	}

	@Override
	public int deleteUser(String userEmail) {
		Date outDate=java.sql.Date.valueOf(java.time.LocalDate.now());
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("userEmail",userEmail);
		map.put("outDate",outDate); // 현재 탈퇴 시간을 받아 옴
		int count = session.update("myUser.deleteUser", map);
		
		return count;
	}

	@Override
	public int characterCheck(String userEmail) {
		log.debug("캐릭터 존재 유무 확인용" + userEmail);
		int characterNum = session.selectOne("myUser.getUserCharacterByEmail", userEmail);
		return characterNum;
	}

	@Override
	public int deleteCharacter(int userCharacterNum) {
		int count = session.update("myUser.deleteCharacter",userCharacterNum);
		return count;
	}

	@Override
	public User getUserInfo(String battleUserNum) {
		User user = session.selectOne("myUser.getUserInfoByUserNum", battleUserNum);
		return user;
	}

	@Override
	public int updateWinnerPoint(BattleLog battleLog) {
		int count = session.update("myUser.updateWinnerPoint", battleLog);
		return count;
	}

	@Override
	public int updateLoserPoint(BattleLog battleLog) {
		int count = session.update("myUser.updateLoserPoint", battleLog);
		return count;
	}

	@Override
	public List<User> getRanking(int cnt) {
		List<User> list = session.selectList("myUser.getRanking", cnt);
		return list;
	}

	@Override
	public User isOutCheck(String userEmail) {
		User user = session.selectOne("myUser.checkIsOut",userEmail);
		return user;
	}

	@Override
	public int reJoin(String userEmail) {
		int count = session.update("myUser.reJoin",userEmail);
		return count;
	}
	
	@Override
	public int checkExistUser(String battleUserNum) {
		int cnt = session.selectOne("myUser.getExistUserNum", battleUserNum);
		return cnt;
	}
	
	
	
}
