package team.spring.springmbti.character.dao;

import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import team.spring.springmbti.character.vo.CharacterInfo;


@Repository
public class CharacterDaoImpl implements CharacterDao{

	
	@Autowired
	private SqlSession session;
	
	Logger log = LogManager.getLogger("case3");
	
	@Override
	public int insertCharacter(CharacterInfo character) {
		
		int count = session.insert("myCharacter.insertCharacter", character);
		
		if(count==1) {
			log.debug("캐릭터 등록 커밋 성공");
		}else {
			log.debug("캐릭터 등록 실패, 롤백");
		}
		log.debug("test");
		return count;
	}

	@Override
	public CharacterInfo getCharacter(int userCharacterNum) {
		
		CharacterInfo character = session.selectOne("myCharacter.getCharacterByCharacterNum", userCharacterNum);
		
		return character;
	}

}
