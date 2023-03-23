package team.spring.springmbti.user.dao;

import java.util.List;

import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.user.vo.User;

public interface UserDao {

	User emailCheck(String userEmail);

	int insertUser(User user);

	int getUserNum(String string);

	int getUserCharacterNum(int userNum);

	int deleteUser(String userEmail);

	int characterCheck(String userEmail);

	int deleteCharacter(int userCharacterNum);

	User getUserInfo(String battleUserNum);

	int updateWinnerPoint(BattleLog battleLog);

	int updateLoserPoint(BattleLog battleLog);

	List<User> getRanking(int cnt);
	
	User getRankingOne(int cnt);

	User isOutCheck(String userEmail);

	int reJoin(String userEmail);
	
	int checkExistUser(String battleUserNum);

	User getUserPage(String count);

	int chageUserAcceptNo(String userNum);

	int chageUserAcceptYes(String userNum);
	
}
