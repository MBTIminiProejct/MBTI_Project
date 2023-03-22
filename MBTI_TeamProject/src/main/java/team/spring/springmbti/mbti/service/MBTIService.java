package team.spring.springmbti.mbti.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.mbti.dao.MBTIDao;
import team.spring.springmbti.mbti.vo.MBTIResult;

@Service
public class MBTIService {
	
	@Autowired
	private MBTIDao dao;
	
	Logger log = LogManager.getLogger("case3");

	public MBTIResult getMBTI(String userMBTI) {
		MBTIResult mbti = dao.getMBTI(userMBTI);
		return mbti;
	}
	
	
}
