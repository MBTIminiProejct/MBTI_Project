package team.spring.springmbti.battle.service;

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
	public BattleLog makeBattleLog(User winner , User loser, String battleField, StringBuilder bbLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		Random random = new Random();
		random.setSeed(System.currentTimeMillis());
		
		String bLog = bbLog.toString();
		BattleLog battleLog = new BattleLog(winner.getUserNum(), loser.getUserNum(), 
				winner.getUserName(), loser.getUserName(), battleField, (random.nextInt(2) +8),
				-(random.nextInt(2) + 8), winner.getUserMBTI(), loser.getUserMBTI(), bLog);
		int result = dao.insertBattleLog(battleLog);
		if (result == 1) {
			log.debug("등록성공");
		} else {
			log.debug("등록실패");
		}
		return battleLog;
	}
	
	private BattleLog drawLog(StringBuilder bbLog, String battleField) {
		
		Logger log = LogManager.getLogger("case3");
		
		String bLog = bbLog.toString();
		BattleLog battleLog = new BattleLog("무승부", "무승부", "무승부", "무승부", battleField, 0, 0, "무승부", "무승부", bLog);
		int result = dao.insertBattleLog(battleLog);
		
		if (result == 1) {
			log.debug("등록성공");
		} else {
			log.debug("등록실패");
		}
		return battleLog;
	}
	public double adAttack(CharacterInfo a, CharacterInfo d, String aUser, String dUser, StringBuilder bLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		Random random = new Random();

		
		double dmg = 0;
		double critical = 0;
		double miss = 0;
		
		dmg = a.getCharacterAD() * (random.nextInt(99) + 50) * 0.01;
		critical = random.nextInt(70) + a.getCharacterCritical();
		if (critical >= 100) {
			bLog.append("크리티컬공격!  ");
			dmg = dmg * 2;
		}
		dmg = dmg - (d.getCharacterADDefence()/2);
		if (dmg <= 0) {
			bLog.append("최종데미지가 0 이하이므로 데미지 0처리 \n");
			dmg = 0;
		}
		miss = d.getCharacterAvoidanceRate() + random.nextInt(90) - a.getCharacterHitRate();
		if (miss  >= 99) {
			bLog.append("miss! \n");
			dmg = 0;
		}
		dmg = dmg + (0.2 * a.getCharacterAdditionalDmg());
		dmg = Math.round(dmg * 10 / 10.0);
		return dmg;
	}
	
	public double apAttack(CharacterInfo a, CharacterInfo d, String aUser, String dUser, StringBuilder bLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		Random random = new Random();

		
		double dmg = 0;
		double critical = 0;
		double miss = 0;
		
		dmg = a.getCharacterAP() * (random.nextInt(99) + 50) * 0.01;
		critical = random.nextInt(70) + a.getCharacterCritical();
		if (critical >= 99) {
			bLog.append("크리티컬공격!  ");
			dmg = dmg * 2;
		}
		dmg = dmg - (d.getCharacterAPDefence()/2);
		if (dmg <= 0) {
			bLog.append("최종데미지가 0 이하이므로 데미지 0처리 \n");
			dmg = 0;
		}
		miss = d.getCharacterAvoidanceRate() + random.nextInt(90) - a.getCharacterHitRate();
		if (miss  >= 96) {
			bLog.append("miss! \n");
			dmg = 0;
		}
		dmg = dmg + (0.2 * a.getCharacterAdditionalDmg());
		dmg = Math.round(dmg * 10 / 10.0);
		
		return dmg;
	}
	
	public BattleLog prepBattle(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField) {
		
		Logger log = LogManager.getLogger("case3");
		
		BattleLog battleLog = new BattleLog();
		String[] mbtiC = challengeUser.getUserMBTI().split("");
		String[] mbtiD = defenceUser.getUserMBTI().split("");
		StringBuilder bLog = new StringBuilder();
		
		if (battleField.equals("기본")) {
			battleLog = battleNone(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, bLog);
		} else if (battleField.equals("놀이동산")) {
			battleLog = amusementPark(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, mbtiC, mbtiD, bLog);
		} else if (battleField.equals("계획없이 떠나게된 여행")) {
			battleLog = unplannedTrip(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, mbtiC, mbtiD, bLog);
		} else if (battleField.equals("도서관")) {
			battleLog = library(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, mbtiC, mbtiD, bLog);
		} else if (battleField.equals("꿈속")) {
			battleLog = dream(challengeCharacter, defenceCharacter, challengeUser, defenceUser, battleField, mbtiC, mbtiD, bLog);
		}
		return battleLog;
	}
	
	private BattleLog dream(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField, String[] mbtiC, String[] mbtiD, StringBuilder bLog) {
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = new BattleLog();
		
		bLog.append("======= 꿈속에서 배틀을 시작합니다! =======\n");
		bLog.append("======= 꿈속에선 N 망상이 더욱더 다양하게 발휘됩니다. =======\n");
		bLog.append("======= N의 마법 공격력,방어력이 회피율이 상승합니다. =======\n");
		if (mbtiC[1].equals("N")) {
			bLog.append("====" + challengerName + "의 마법 공격력,방어력이 +3, 회피율이 + 10 만큼 상승합니다. ====\n");
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 3);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() + 3);
			challengeCharacter.setCharacterAvoidanceRate(challengeCharacter.getCharacterAvoidanceRate() + 10);
		}
		if (mbtiD[1].equals("N")) {
			bLog.append("====" +defenderName + "의 마법 공격력,방어력이 +3, 회피율이 + 10 만큼 상승합니다. ====\n");
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 3);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() + 3);
			defenceCharacter.setCharacterAvoidanceRate(defenceCharacter.getCharacterAvoidanceRate() + 10);
		}
		bLog.append("======= 꿈속에서 F의 감수성이 더욱 예민하게 반응합니다 =======\n");
		bLog.append("======= F의 모든 공격력, 크리티컬확률, 회피율, 명중률이 상승합니다. 모든 방어력이만큼 감소합니다. =======\n");
		if (mbtiC[2].equals("F")) {
			bLog.append("====" + challengerName + "의 모든 공격력이 +3 만큼 상승, 모든 방어력이 - 3 만큼 감소합니다. ==== \n");
			bLog.append("====" + challengerName + "의 크리티컬확률, 회피율, 명중률이 7씩 상승합니다. ==== \n");
			challengeCharacter.setCharacterAD(challengeCharacter.getCharacterAD() + 3);
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 3);
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() - 3);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() - 3);
			challengeCharacter.setCharacterAvoidanceRate(challengeCharacter.getCharacterAvoidanceRate() + 7);
			challengeCharacter.setCharacterCritical(challengeCharacter.getCharacterCritical() + 7);
			challengeCharacter.setCharacterHitRate(challengeCharacter.getCharacterHitRate() + 7);
		}
		if (mbtiD[2].equals("F")) {
			bLog.append("====" + defenderName + "의 모든 공격력이 +3 만큼 상승, 모든 방어력이 - 3 만큼 감소합니다. ==== \n");
			bLog.append("====" + defenderName + "의 크리티컬확률, 회피율, 명중률이 7씩 상승합니다. ==== \n");
			defenceCharacter.setCharacterAD(defenceCharacter.getCharacterAD() + 3);
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 3);
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() - 3);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() - 3);
			defenceCharacter.setCharacterAvoidanceRate(defenceCharacter.getCharacterAvoidanceRate() + 7);
			defenceCharacter.setCharacterCritical(defenceCharacter.getCharacterCritical() + 7);
			defenceCharacter.setCharacterHitRate(defenceCharacter.getCharacterHitRate() + 7);
		}
		
		bLog.append("\n \n");
		for(int cnt = 1; cnt <= 10; cnt++) {
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(challengerName + "의 선공으로 시작합니다.\n");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() +" \n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다 \n");
					bLog.append(challengerName + " 승리 ! \n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() +"\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			} else {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(defenderName + "의 선공으로 시작합니다\n");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP()+"\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			}
		}
		bLog.append("======= 10턴동안 승패가 나오지않아 무승부로 기록됩니디 ======= \n");
		battleLog = drawLog(bLog, battleField);
		return battleLog;
	}
	private BattleLog library(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField, String[] mbtiC, String[] mbtiD, StringBuilder bLog) {
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = new BattleLog();
		
		bLog.append("======= 도서관에서 대결을 시작합니다! =======\n");
		bLog.append("==== 조용한분위기에 소음을 만들수 없습니다. 모든유저의 물리공격력이 1/3만 적용됩니다. ==== \n");
		bLog.append("==== E는 도서관보다는 카페가 좋습니다. 조용한분위가 조금 어렵습니다 모든방어력이 감소합니다. ==== \n");
		if (mbtiC[0].equals("E")) {
			bLog.append("====" + challengerName + "의 모든방어력이 -4 감소합니다 ==== \n");
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() - 4);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() - 4);
		}
		if (mbtiD[0].equals("E")) {
			bLog.append("====" + defenderName + "의 모든방어력이 -4 감소합니다 ==== \n");
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() - 4);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() - 4);
		}
		bLog.append("도서관은 조용하게 자신의 하루 계획을 실행하기 좋은 공간입니다 J의 모든 공격력,방어력이 상승합니다 \n");
		if (mbtiC[3].equals("J")) {
			bLog.append("====" + challengerName + "의 모든 공격력,방어력이 +1 만큼 상승합니다 ==== \n");
			challengeCharacter.setCharacterAD(challengeCharacter.getCharacterAD() + 1);
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 1);
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() + 1);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() + 1);
		}
		if (mbtiD[3].equals("J")) {
			bLog.append("====" + defenderName + "의 모든 공격력,방어력이 +1 만큼 상승합니다 ==== \n");
			defenceCharacter.setCharacterAD(defenceCharacter.getCharacterAD() + 1);
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 1);
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() + 1);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() + 1);
		}
		
		bLog.append("\n \n");
		for(int cnt = 1; cnt <= 10; cnt++) {
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(challengerName + "의 선공으로 시작합니다.\n");
				adDmg = Math.round(0.3 * adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog));
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() +" \n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다 \n");
					bLog.append(challengerName + " 승리 ! \n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = Math.round(0.3 * adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog));
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() +"\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			} else {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(defenderName + "의 선공으로 시작합니다\n");
				adDmg = Math.round(0.3 * adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog));
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = Math.round(0.3 * (adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog)));
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP()+"\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			}
		}
		bLog.append("======= 10턴동안 승패가 나오지않아 무승부로 기록됩니디 ======= \n");
		battleLog = drawLog(bLog, battleField);
		return battleLog;
	}
	
	private BattleLog unplannedTrip(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter,
			User challengeUser, User defenceUser, String battleField, String[] mbtiC, String[] mbtiD, StringBuilder bLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = new BattleLog();
		
		bLog.append("====== 계획없던, 계획이없는 여행을 출발했습니다 ====== \n");
		if(mbtiC[3].equals("J")) {
			bLog.append(" ==================================== \n");
			bLog.append("계획에없던, 계획없는 여행에 J인" + challengerName +"는 극도로 불안합니다. \n");
			bLog.append("====" + challengerName +"는 지금이 현실이 믿기지않습니다. 모든방어력이 4만큼 감소합니다 ==== \n");
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() - 4);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() - 4);
			bLog.append("====" + challengerName +"는 극도로 예민한 상태입니다 모든공격력이 4만큼, 크리티컬확률이 12% 상승합니다 ====\n");
			challengeCharacter.setCharacterAD(challengeCharacter.getCharacterAD() + 4);
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 4);
			challengeCharacter.setCharacterCritical(challengeCharacter.getCharacterCritical() + 12);
			bLog.append(" ==================================== \n");
			bLog.append("\n");
		}
		if(mbtiD[3].equals("J")) {
			bLog.append(" ==================================== \n");
			bLog.append("계획에없던, 계획없는 여행에 J인" + defenderName +"는 극도로 불안합니다.\n");
			bLog.append("====" + defenderName +"는 지금이 현실이 믿기지않습니다. 모든방어력이 4만큼 감소합니다 ====\n");
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() - 4);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() - 4);
			bLog.append("====" + defenderName +"는 극도로 예민한 상태입니다 모든공격력이 4만큼, 크리티컬확률이 12% 상승합니다 ====\n");
			defenceCharacter.setCharacterAD(defenceCharacter.getCharacterAD() + 4);
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 4);
			defenceCharacter.setCharacterCritical(defenceCharacter.getCharacterCritical() + 12);
			bLog.append(" ==================================== \n");
			bLog.append("\n");
		}
		if(mbtiC[0].equals("E") && mbtiC[3].equals("P")) {
			bLog.append(" ==================================== \n");
			bLog.append("E + P 인 " + challengerName +"는 큰 걱정이없어보입니다. 그냥 밖에나온것에 기분이 좋아보입니다.\n");
			bLog.append("그저, 저녁식사를위한 맛집이 있는지 검색하고있습니다. 모든 공격력, 방어력이 1만큼 증가합니다. \n");
			challengeCharacter.setCharacterAD(challengeCharacter.getCharacterAD() + 1);
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 1);
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() + 1);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() + 1);
			bLog.append(" ==================================== \n");
			bLog.append("\n");
		}
		if(mbtiD[0].equals("E") && mbtiD[3].equals("P")) {
			bLog.append(" ==================================== \n");
			bLog.append("E + P 인 " + defenderName +"는 큰 걱정이없어보입니다. 그냥 밖에나온것에 기분이 좋아보입니다. \n");
			bLog.append("그저, 저녁식사를위한 맛집이 있는지 검색하고있습니다. 모든 공격력, 방어력이 1만큼 증가합니다. \n");
			defenceCharacter.setCharacterAD(defenceCharacter.getCharacterAD() + 1);
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 1);
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() + 1);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() + 1);
			bLog.append(" ==================================== \n");
			bLog.append("\n");
		}
		
		bLog.append("\n \n");
		for(int cnt = 1; cnt <= 10; cnt++) {
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(challengerName + "의 선공으로 시작합니다.\n");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() +" \n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다 \n");
					bLog.append(challengerName + " 승리 ! \n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() +"\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			} else {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(defenderName + "의 선공으로 시작합니다\n");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP()+"\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			}
		}
		
		bLog.append("======= 10턴동안 승패가 나오지않아 무승부로 기록됩니디 ======= \n");
		battleLog = drawLog(bLog, battleField);
		return battleLog;
	}
	public BattleLog battleNone(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter, User challengeUser,
			User defenceUser, String battleField, StringBuilder bLog) {
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = new BattleLog();
		for(int cnt = 1; cnt <= 10; cnt++) {
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(challengerName + "의 선공으로 시작합니다.\n");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() +" \n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다 \n");
					bLog.append(challengerName + " 승리 ! \n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() +"\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			} else {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(defenderName + "의 선공으로 시작합니다\n");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP()+"\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			}
		}
		bLog.append("======= 10턴동안 승패가 나오지않아 무승부로 기록됩니디 ======= \n");
		battleLog = drawLog(bLog, battleField);
		return battleLog;
	}
	
	private BattleLog amusementPark(CharacterInfo challengeCharacter, CharacterInfo defenceCharacter,
			User challengeUser, User defenceUser, String battleField, String[] mbtiC, String[] mbtiD, StringBuilder bLog) {
		
		Logger log = LogManager.getLogger("case3");
		
		String challengerName = challengeUser.getUserName();
		String defenderName = defenceUser.getUserName();
		double adDmg = 0;
		double apDmg = 0;
		BattleLog battleLog = new BattleLog();
		
		bLog.append(" ========== 대결장소 : 놀이동산 ==========\n");
		bLog.append(" ====== 주위에 사람이 많습니다. i들은 이런분위기를 좋아하지않습니다 ====== \n");
		if (mbtiC[0].equals("I")) {
			bLog.append("i인 " + challengerName + "는(은) 모든방어력이 3 만큼 감소합니다\n");
			challengeCharacter.setCharacterADDefence(challengeCharacter.getCharacterADDefence() - 3);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() - 3);
		}
		if (mbtiD[0].equals("I")) {
			bLog.append("i인 " + defenderName + "는 모든방어력이 3 만큼 감소합니다\n");
			defenceCharacter.setCharacterADDefence(defenceCharacter.getCharacterADDefence() - 3);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() - 3);
		}
		bLog.append("\n");
		bLog.append(" ====== 각종 놀이기구가 N의 상상력을 자극합니다 ====== \n");
		if (mbtiC[1].equals("N")) {
			bLog.append("N인 " + challengerName + "는 마법공격, 방어력이 3 만큼 증가합니다\n");
			challengeCharacter.setCharacterAP(challengeCharacter.getCharacterAP() + 3);
			challengeCharacter.setCharacterAPDefence(challengeCharacter.getCharacterAPDefence() + 3);
		}
		if (mbtiD[1].equals("N")) {
			bLog.append("N인 " + defenderName + "는 마법공격, 방어력이 3 만큼 증가합니다\n");
			defenceCharacter.setCharacterAP(defenceCharacter.getCharacterAP() + 2);
			defenceCharacter.setCharacterAPDefence(defenceCharacter.getCharacterAPDefence() + 3);
		}
		bLog.append("\n \n");
		for(int cnt = 1; cnt <= 10; cnt++) {
			
			
			if (challengeCharacter.getCharacterSpeed() > defenceCharacter.getCharacterSpeed()) {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(challengerName + "의 선공으로 시작합니다.\n");
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() +" \n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다 \n");
					bLog.append(challengerName + " 승리 ! \n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격! \n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() +"\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			} else {
				if (cnt != 0) {
					bLog.append("\n");
				}
				bLog.append(cnt + "번째 턴!\n");
				bLog.append(defenderName + "의 선공으로 시작합니다\n");
				adDmg = adAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - adDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				adDmg = adAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ad데미지 : " + adDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - adDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP() + "\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(defenceCharacter, challengeCharacter, defenderName, challengerName, bLog);
				bLog.append(defenderName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				challengeCharacter.setCharacterHP(challengeCharacter.getCharacterHP() - apDmg);
				bLog.append(challengerName + "의 잔여 HP : " + challengeCharacter.getCharacterHP() + "\n");
				if (challengeCharacter.getCharacterHP() <= 0) {
					bLog.append(challengerName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(defenderName + " 승리 !\n");
					battleLog = makeBattleLog(defenceUser, challengeUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
				apDmg = apAttack(challengeCharacter, defenceCharacter, challengerName, defenderName, bLog);
				bLog.append(challengerName + "의 ap데미지 : " + apDmg + "으로 공격!\n");
				defenceCharacter.setCharacterHP(defenceCharacter.getCharacterHP() - apDmg);
				bLog.append(defenderName + "의 잔여 HP : " + defenceCharacter.getCharacterHP()+"\n");
				if (defenceCharacter.getCharacterHP() <= 0) {
					bLog.append(defenderName + " 의 잔여 HP 가 0이하이므로 대전을 종료합니다\n");
					bLog.append(challengerName + " 승리 !\n");
					battleLog = makeBattleLog(challengeUser, defenceUser, battleField, bLog);
					updateBattleLogToUser(battleLog);
					return battleLog;
				}
			}
		}
		bLog.append("======= 10턴동안 승패가 나오지않아 무승부로 기록됩니디 ======= \n");
		battleLog = drawLog(bLog, battleField);
		return battleLog;
	}

}
