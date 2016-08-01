package services.servicesjpql;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;
import entity.entityjpql.CommentJPQL;
import entity.entityjpql.GameJPQL;
import entity.entityjpql.PlayerJPQL;
import services.JpaHelper;
import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;


public class CommentJpqlMethods {

	public void addCommentJpql(CommentJPQL comment) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(comment);
		JpaHelper.commitTransaction();
	}

	public void commentToDatabaseJPQL(String comment,  String gameName, String userName) {

		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();

		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addCommentJpql(new CommentJPQL(comment, new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addCommentJpql(
						new CommentJPQL(comment, new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addCommentJpql(
						new CommentJPQL(comment, method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addCommentJpql(new CommentJPQL(comment, method.findPlayerObjectbyID(userName),
						method.findGameObjectbyID(gameName)));
			}
		}
	}
	
	public long countOfComments(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(c.commentId) FROM CommentJPQL c Where c.game=:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		Long avg = (Long) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}
	}
	
	public List<CommentJPQL> printComment(GameJPQL game){
		
		List<CommentJPQL> koment = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
	//	Query query = em.createQuery("select playerName,user_comment from PlayerJPQL p join CommentJPQL c on p.playerID=c.player WHERE c.game =:gameID");
		Query query = em.createQuery("select c from CommentJPQL c WHERE c.game =:gameID");

		query.setParameter("gameID", game).setMaxResults(10);		
		koment = query.getResultList();
		return koment;
	
	}
	
	public static void main(String[] args) {
		CommentJpqlMethods aaa = new CommentJpqlMethods();
		System.out.println(aaa.printComment(new UsefullServicesJpqlMethods().findGameObjectbyID("Minesweeper")));
	}
}
