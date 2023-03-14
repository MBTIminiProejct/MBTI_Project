package team.spring.springmbti.battle.service;

import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.vo.User;

@Service
public class BattleService {

	private CharacterInfo winner;
	private CharacterInfo loser;
	public double adAttack(CharacterInfo a, CharacterInfo d, String aUser, String dUser) {
		
		Logger log = LogManager.getLogger("case4");
		
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		double dmg = 0;
		double critical = 0;
		double miss = 0;
		
		dmg = a.getCharacterAD() * (random.nextInt(99) + 50) * 0.01;
		critical = random.nextInt(80) + a.getCharacterCritical();
		if (critical >= 100) {
			log.debug("크리티컬공격!");
			dmg = dmg * 2;
		}
		dmg = dmg - (d.getCharacterADDefence()/2);
		if (dmg <= 0) {
			log.debug("최종데미지가 0 이하이므로 데미지 0처리");
		}
		miss = d.getCharacterAvoidanceRate() + random.nextInt(100) - a.getCharacterHitRate();
		if (miss  >= 100) {
			log.debug("miss!");
			dmg = 0;
		}
		dmg = Math.round(dmg * 10 / 10.0);
		return dmg;
	}
	
	public double apAttack(CharacterInfo a, CharacterInfo d, String aUser, String dUser) {
		
		Logger log = LogManager.getLogger("case4");
		
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		double dmg = 0;
		double critical = 0;
		double miss = 0;
		
		dmg = a.getCharacterAP() * (random.nextInt(99) + 50) * 0.01;
		critical = random.nextInt(80) + a.getCharacterCritical();
		if (critical >= 100) {
			log.debug("크리티컬공격!");
			dmg = dmg * 2;
		}
		dmg = dmg - (d.getCharacterAPDefence()/2);
		if (dmg <= 0) {
			log.debug("최종데미지가 0 이하이므로 데미지 0처리");
		}
		miss = d.getCharacterAvoidanceRate() + random.nextInt(100) - a.getCharacterHitRate();
		if (miss  >= 100) {
			log.debug("miss!");
			dmg = 0;
		}
		dmg = Math.round(dmg * 10 / 10.0);
		return dmg;
	}
	
	public BattleLog prepBattle(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField) {
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		for(int cnt = 1; cnt <= 10; cnt++) {
			Logger log = LogManager.getLogger("case4");
			// 속도비교 도전자가빠를때만 선공 같을경우도 방어자 선공
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				log.debug(challengerName + "의" + cnt + "번째 턴!");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					break;
				}
				log.debug(defenderName  + "의" + cnt + "번째 턴!");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenceUser + " 승리 !");
					break;
				}
			} else {
				log.debug(defenderName  + "의" + cnt + "번째 턴!");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					break;
				}
				log.debug(challengerName + "의" + cnt + "번째 턴!");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					break;
				}
			}
		}
		// 대결종료 승자, 패자 가지고 db 처리
		return null;
	}

}
