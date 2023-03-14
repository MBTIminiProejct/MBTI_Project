package team.spring.springmbti.survey.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Survey {
	
	private String userNum;
	private String userEmail;
	private String userName;
	private String userProfile;
	private int userCharacter;
	private String userMBTI;
	private int userWin;
	private int userDefeat;
	private int userPoint;
	private int userI;
	private int userE;
	private int userS;
	private int userN;
	private int userT;
	private int userF;
	private int userP;
	private int userJ;
	private int userItem;
	private boolean userAcceptance;
	private Date initDate;
	private String isout;
	private Date outDate;
	
}
