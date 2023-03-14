package team.spring.springmbti.battle.service;

import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import team.spring.springmbti.battle.dao.BattleDao;
import team.spring.springmbti.battle.vo.BattleLog;
import team.spring.springmbti.character.vo.CharacterInfo;
import team.spring.springmbti.user.dao.UserDao;
import team.spring.springmbti.user.vo.User;

@Service
public class BattleService {

	@Autowired
	private BattleDao dao;
	
	@Autowired
	private UserDao uDao;
	
	public void updateBattleLogToUser(BattleLog battleLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		int resultWin = uDao.updateWinnerPoint(battleLog);
		int resultLose = uDao.updateLoserPoint(battleLog);
		
		if (resultWin == 1 && resultLose == 1) {
			log.debug("등록성공");
		} else {
			log.debug("실패");
		}
		
	}
	public BattleLog makeBattleLog(User winner , User loser, String battleField) {
		
		Logger log = LogManager.getLogger("case3");
		
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		BattleLog battleLog = new BattleLog(winner.getUserNum(), loser.getUserNum(), 
				winner.getUserName(), loser.getUserName(), battleField, (random.nextInt(2) +8), -(random.nextInt(2) + 8));
		int result = dao.insertBattleLog(battleLog);
		if (result == 1) {
			log.debug("등록성공");
		} else {
			log.debug("등록실패");
		}
		return battleLog;
	}
	
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
		
		Logger log = LogManager.getLogger("case3");
		
		BattleLog battleLog = null;
		
		String[] mbtiC = challengeUser.getUserMBTI().split("");
		String[] mbtiD = defenceUser.getUserMBTI().split("");
		
		
		if (battleField.equals("없음")) {
			battleLog = battleNone(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField);
		} else if (battleField.equals("놀이동산")) {
			battleLog = amusementPark(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, mbtiC, mbtiD);
		}
		
		if (battleLog.getWinnerName().equals(null)) {
			log.debug("무승부!");
			battleLog.setWinnerNum("0");
			battleLog.setLoserNum("0");
			battleLog.setWinnerName("무승부");
			battleLog.setLoserName("무승부");
			battleLog.setBattleField(battleField);
			battleLog.setWinPoint(0);
			battleLog.setLosePoint(0);
		}
		return battleLog;
	}
	
	public BattleLog battleNone(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField) {
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = null;
		
		for(int cnt = 1; cnt <= 10; cnt++) {
			Logger log = LogManager.getLogger("case4");
			// 속도비교 도전자가빠를때만 선공 같을경우도 방어자 선공
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					log.debug("\n");
				}
				log.debug(cnt + "번째 턴!");
				log.debug(challengerName + "의 선공으로 시작합니다");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
			} else {
				if (cnt != 0) {
					log.debug("\n");
				}
				log.debug(cnt + "번째 턴!");
				log.debug(defenderName + "의 선공으로 시작합니다");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
			}
		}
		return battleLog;
	}
	
	private BattleLog amusementPark(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter,
			User challengeUser, User defenceUser, String battleField, String[] mbtiC, String[] mbtiD) {
		
		Logger log = LogManager.getLogger("case4");
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = null;
		
		log.debug("대결장소 : 놀이동산");
		log.debug("주위에 사람이 많습니다. i들은 이런분위기를 좋아하지않습니다");
		if (mbtiC[0].equals("I")) {
			log.debug("i인 " + challengerName + "는 모든방어력이 2 만큼 감소합니다");
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() - 2);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() - 2);
		}
		if (mbtiD[0].equals("I")) {
			log.debug("i인 " + defenderName + "는 모든방어력이 2 만큼 감소합니다");
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() - 2);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() - 2);
		}
		log.debug("\n");
		log.debug("각종 놀이기구가 N의 상상력을 자극합니다");
		if (mbtiC[1].equals("N")) {
			log.debug("N인 " + challengerName + "는 마법공격, 방어력이 2 만큼 증가합니다");
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 2);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() + 2);
		}
		if (mbtiD[1].equals("N")) {
			log.debug("N인 " + defenderName + "는 마법공격, 방어력이 2 만큼 증가합니다");
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 2);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() + 2);
		}
		
		for(int cnt = 1; cnt <= 10; cnt++) {
			// 속도비교 도전자가빠를때만 선공 같을경우도 방어자 선공
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					log.debug("\n");
				}
				log.debug(cnt + "번째 턴!");
				log.debug(challengerName + "의 선공으로 시작합니다");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName);
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
			} else {
				if (cnt != 0) {
					log.debug("\n");
				}
				log.debug(cnt + "번째 턴!");
				log.debug(defenderName + "의 선공으로 시작합니다");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName);
				log.debug(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				log.debug(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP());
				if (challengeCharacter.getCharacterHP() <= 0) {
					log.debug(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(defenderName + " 승리 !");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName) + 1;
				log.debug(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				log.debug(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP());
				if (defenceCharacter.getCharacterHP() <= 0) {
					log.debug(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다");
					log.debug(challengerName + " 승리 !");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField);
					updateBattleLogToUser(battleLog);
					break;
				}
			}
		}
		return battleLog;
	}

}
