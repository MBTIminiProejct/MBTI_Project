package team.spring.springmbti.battle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleLog {

	private int logNum;
	private String winnerNum;
	private String loserNum;
	private String winnerName;
	private String loserName;
	private String battleField;
	private int winPoint;
	private int losePoint;
	
	public BattleLog(String winnerNum, String loserNum, String winnerName, String loserName, String battleField,
			int winPoint, int losePoint) {
		super();
		this.winnerNum = winnerNum;
		this.loserNum = loserNum;
		this.winnerName = winnerName;
		this.loserName = loserName;
		this.battleField = battleField;
		this.winPoint = winPoint;
		this.losePoint = losePoint;
	}
	
	
	
	
}

