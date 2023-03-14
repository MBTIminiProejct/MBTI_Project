package team.spring.springmbti.user.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;





@Repository
public class SurveyDaoImpl implements SurveyDao {
	
	@Autowired
	private SqlSession session;
	
//	@Override
//	public Member idCheck(String memberId) {
//		
//		Member member = session.selectOne("myMember.checkid",memberId);
//		
//		return member;
//	}

}
