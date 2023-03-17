package team.spring.springmbti.survey.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.survey.dao.SurveyDao;
import team.spring.springmbti.user.vo.User;





@Service
public class SurveyService {
	
	@Autowired
	private SurveyDao dao;
	
	Logger log = LogManager.getLogger("case3");
	
	public void updateScoreOne(User user) {
		int result = dao.updateSurveyOne(user);
		if(result == 1) {
			log.debug("���� ���� ����");
		} else {
			log.debug("���� ���� ����");
		}
	}
	
	public void updateScoreTwo(User user) {
		int result = dao.updateSurveyTwo(user);
		if(result == 1) {
			log.debug("���� ���� ����");
		} else {
			log.debug("���� ���� ����");
		}
	}
	
	public void updateScoreThree(User user) {
		int result = dao.updateSurveyThree(user);
		if(result == 1) {
			log.debug("���� ���� ����");
		} else {
			log.debug("���� ���� ����");
		}
	}
	
	public void updateScoreFour(User user) {
		int result = dao.updateSurveyFour(user);
		if(result == 1) {
			log.debug("���� ���� ����");
		} else {
			log.debug("���� ���� ����");
		}
	}
	
	public void updateUserCharacter(User user) {
		int result = dao.updateUserCharacter(user);
		if(result == 1) {
			log.debug("���� ���� ����");
		} else {
			log.debug("���� ���� ����");
		}
	}

}