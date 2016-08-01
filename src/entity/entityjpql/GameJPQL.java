package entity.entityjpql;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class GameJPQL {
	
	@Id
	@GeneratedValue
	private  int gameID;
	@Column(unique=true)
	private String gameName;
	
	public GameJPQL() {

	}
	public GameJPQL(String gameName) {
		this.gameName = gameName;
	}
	public int getGameID() {
		return gameID;
	}
	public void setGameID(int gameID) {
		this.gameID = gameID;
	}
	public String getGameName() {
		return gameName;
	}
	public void setGameName(String gameName) {
		this.gameName = gameName;
	}
	@Override
	public String toString() {
		return "GameJPQL [gameID=" + gameID + ", gameName=" + gameName + "]";
	}
	
	
	
}
