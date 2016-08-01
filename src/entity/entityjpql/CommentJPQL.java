package entity.entityjpql;

import javax.persistence.*;

@Entity
public class CommentJPQL {

	@Id
	@GeneratedValue
	private int commentId;
	private String user_comment;

	@ManyToOne(cascade = CascadeType.ALL)
	private PlayerJPQL player;

	@Override
	public String toString() {
		return "CommentJPQL [commentId=" + commentId + ", user_comment=" + user_comment + ", player=" + player
				+ ", game=" + game + "]";
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private GameJPQL game;

	public CommentJPQL() {
	}

	public CommentJPQL( String comment, PlayerJPQL player, GameJPQL game) {
		this.user_comment = comment;
		this.player = player;
		this.game = game;
	}

	public int getCommentId() {
		return commentId;
	}

	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}

	public String getComment() {
		return user_comment;
	}

	public void setComment(String comment) {
		this.user_comment = comment;
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

}
