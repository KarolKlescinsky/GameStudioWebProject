package entity.entityjpql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class PlayerJPQL {

	@Id
	@GeneratedValue
	private int playerID;
	@Column(unique = true)
	private String playerName;
	private String playerPwd;

	public PlayerJPQL() {
	}

	public PlayerJPQL(String playerName) {
		this.playerName = playerName;
		// this.playerPwd = playerPwd;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getPlayerPwd() {
		return playerPwd;
	}

	public void setPlayerPwd(String playerPwd) {
		this.playerPwd = playerPwd;
	}

	@Override
	public String toString() {
		return "PlayerJPQL [playerID=" + playerID + ", playerName=" + playerName + ", playerPwd=" + playerPwd + "]";
	}
}
