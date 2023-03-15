package team.spring.springmbti.character.dao;

import team.spring.springmbti.character.vo.CharacterInfo;

public interface CharacterDao {

	int insertCharacter(CharacterInfo character);

	CharacterInfo getCharacter(int userCharacterNum);
	
	CharacterInfo selectCharacterByNumber(CharacterInfo characterinfo);
	
	int updateCharacter(CharacterInfo character);
	
	int getMaxCharacter();
}
