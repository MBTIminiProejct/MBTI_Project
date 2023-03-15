package team.spring.springmbti.character.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.character.dao.CharacterDao;
import team.spring.springmbti.character.vo.CharacterInfo;

@Service
public class CharacterService {

	@Autowired
	private CharacterDao dao;
	
	Logger log = LogManager.getLogger("case3");
	
	public int createCharacter(CharacterInfo character) {
		
		int count = dao.insertCharacter(character);
		if(count==1) {
			log.debug("캐릭터 등록 성공");
		}else {
			log.debug("캐릭터 등록 실패");
		}
		return count;
	}

	public CharacterInfo getCharacter(int userCharacterNum) {
		
		CharacterInfo character = dao.getCharacter(userCharacterNum);
		
		return character;
	}
	
	public int maxCharacter() {
		int maxcharacter = dao.getMaxCharacter();
				
		return maxcharacter;
	}
	
	public int updateCharacter(CharacterInfo character) {
		
		int update = dao.updateCharacter(character);
		if(update==1) {
			log.debug("캐릭터 수정 성공");
		}else {
			log.debug("캐릭터 수정 실패");
		}
		return update;
	}
	
	public CharacterInfo selectCharacter(CharacterInfo characterinfo) {
		
		CharacterInfo character = dao.selectCharacterByNumber(characterinfo);
		
		return character;
	}

}