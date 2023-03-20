package team.spring.springmbti.survey.dao;

import team.spring.springmbti.user.vo.User;

public interface SurveyDao {
	
	int updateSurveyOne(User user);
	
	int updateSurveyTwo(User user);
	
	int updateSurveyThree(User user);
	
	int updateSurveyFour(User user);
	
	int updateUserCharacter(User user);
}
