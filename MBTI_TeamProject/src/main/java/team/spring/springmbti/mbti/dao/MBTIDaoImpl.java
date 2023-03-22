package team.spring.springmbti.mbti.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.springmbti.mbti.vo.MBTIResult;

@Repository
public class MBTIDaoImpl implements MBTIDao{

	@Autowired
	private SqlSession session;

	@Override
	public MBTIResult getMBTI(String mbti) {
		MBTIResult mbtivo = session.selectOne("myMBTI.getMBTI",mbti);
		return mbtivo;
	}
	
	

}
