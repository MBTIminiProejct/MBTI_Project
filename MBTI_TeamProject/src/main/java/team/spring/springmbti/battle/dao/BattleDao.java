package team.spring.springmbti.battle.dao;

import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.character.vo.CharacterInfo;

public interface BattleDao {
	
	int insertBattleLog(BattleLog log);
}
