package team.spring.springmbti.user.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;





@Service
public class SurveyService {
	
//	@Autowired
//	private MemberDao dao;
	
	Logger log = LogManager.getLogger("case3");
	
//	public boolean idCheck(String memberId) {
//		
//		Member member = dao.idCheck(memberId);
//		
//		boolean canUse = false;
//		
//		if(member==null)
//		{
//			log.debug("중복된 아이디 없음");
//			canUse=true;
//		}else {
//			log.debug("아이디 중복");
//		}
//		
//		return canUse;
//	}

}
