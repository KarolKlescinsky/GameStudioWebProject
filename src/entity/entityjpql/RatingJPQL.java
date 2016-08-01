package entity.entityjpql;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class RatingJPQL {

	@Id
	@GeneratedValue
	private int ratingID;
	private int rating;

	@ManyToOne(cascade = CascadeType.ALL)
	private PlayerJPQL player;

	@ManyToOne(cascade = CascadeType.ALL)
	private GameJPQL game;

	public RatingJPQL() {
	}

	public RatingJPQL (int rating, PlayerJPQL player, GameJPQL game) {
		this.rating = rating;
		this.player = player;
		this.game = game;
	}

	public int getRatingID() {
		return ratingID;
	}

	public void setRatingID(int ratingID) {
		this.ratingID = ratingID;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
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
		return "RatingJPQL [ratingID=" + ratingID + ", rating=" + rating + ", player=" + player + ", game=" + game
				+ "]";
	}
	
	
}
