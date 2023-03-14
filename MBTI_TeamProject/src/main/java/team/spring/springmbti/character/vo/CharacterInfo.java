package team.spring.springmbti.character.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharacterInfo {

	private int characterNum;
	private String characterUserEmail;
	private double characterHP;
	private double characterAD;
	private double characterAP;
	private double characterADDefence;
	private double characterAPDefence;
	private double characterSpeed;
	private double characterHitRate;
	private double characterAvoidanceRate;
	private double characterCritical;
	private double characterAdditionalDmg;
	
	
	public CharacterInfo(double characterHP, double characterAD, double characterAP, double characterADDefence,
			double characterAPDefence, double characterSpeed, double characterHitRate, double characterAvoidanceRate,
			double characterCritical, double characterAdditionalDmg) {
		super();
		this.characterHP = characterHP;
		this.characterAD = characterAD;
		this.characterAP = characterAP;
		this.characterADDefence = characterADDefence;
		this.characterAPDefence = characterAPDefence;
		this.characterSpeed = characterSpeed;
		this.characterHitRate = characterHitRate;
		this.characterAvoidanceRate = characterAvoidanceRate;
		this.characterCritical = characterCritical;
		this.characterAdditionalDmg = characterAdditionalDmg;
	}


	public CharacterInfo(String characterUserEmail, double characterHP, double characterAD, double characterAP,
			double characterADDefence, double characterAPDefence, double characterSpeed, double characterHitRate,
			double characterAvoidanceRate, double characterCritical, double characterAdditionalDmg) {
		super();
		this.characterUserEmail = characterUserEmail;
		this.characterHP = characterHP;
		this.characterAD = characterAD;
		this.characterAP = characterAP;
		this.characterADDefence = characterADDefence;
		this.characterAPDefence = characterAPDefence;
		this.characterSpeed = characterSpeed;
		this.characterHitRate = characterHitRate;
		this.characterAvoidanceRate = characterAvoidanceRate;
		this.characterCritical = characterCritical;
		this.characterAdditionalDmg = characterAdditionalDmg;
	}
	
	
	
	
	
	
}




