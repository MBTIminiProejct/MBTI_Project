package team.spring.springmbti.battle.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.springmbti.battle.vo.BattleLog;

@Repository
public class BattleDaoImpl implements BattleDao{

	@Autowired
	private SqlSession session;
	
	@Override
	public int insertBattleLog(BattleLog log) {
		
		int count = session.insert("battle.insertBattleLog", log);
		return count;
	}

}
