package team.spring.springmbti.battle.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BattleLog {

	private int logNum;
	private String winnerName;
	private String loserName;
	private String battleField;
	private int winPoing;
	private int losePoing;
}

