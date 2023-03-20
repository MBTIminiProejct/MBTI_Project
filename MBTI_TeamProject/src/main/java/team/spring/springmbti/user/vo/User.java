
package team.spring.springmbti.user.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
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
	
	public User(String userNum, String userName, String userMBTI, int userWin, int userDefeat, int userPoint,
			boolean userAcceptance) {
		super();
		this.userNum = userNum;
		this.userName = userName;
		this.userMBTI = userMBTI;
		this.userWin = userWin;
		this.userDefeat = userDefeat;
		this.userPoint = userPoint;
		this.userAcceptance = userAcceptance;
	}
	
	public String getUserAcceptance() {
		if (this.userAcceptance == true) {
			return "허용";
		} else
			return "대결 불가능";
	}
	
}


