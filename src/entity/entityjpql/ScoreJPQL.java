package entity.entityjpql;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ScoreJPQL {

	@Id
	@GeneratedValue
	private int scoreID;
	private int score;

	@ManyToOne(cascade = CascadeType.ALL)
	private PlayerJPQL player;

	@ManyToOne(cascade = CascadeType.ALL)
	private GameJPQL game;

	public ScoreJPQL() {
	}

	public ScoreJPQL(int score, PlayerJPQL player, GameJPQL game) {
		this.score = score;
		this.player = player;
		this.game = game;
	}

	public int getScoreID() {
		return scoreID;
	}

	public void setScoreID(int scoreID) {
		this.scoreID = scoreID;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public PlayerJPQL getPlayer() {
		return player;
	}

	public void setPlayer(PlayerJPQL player) {
		this.player = player;
	}

	public GameJPQL getGame() {
		return game;
	}

	public void setGame(GameJPQL game) {
		this.game = game;
	}

	@Override
	public String toString() {
		return "ScoreJPQL [scoreID=" + scoreID + ", score=" + score + ", player=" + player + ", game=" + game + "]";
	}
	
	

}
