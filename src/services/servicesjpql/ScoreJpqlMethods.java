package services.servicesjpql;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.entityjpql.CommentJPQL;
import entity.entityjpql.GameJPQL;
import entity.entityjpql.PlayerJPQL;
import entity.entityjpql.ScoreJPQL;
import services.JpaHelper;
import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;

public class ScoreJpqlMethods {

	public void addScoreJpql(ScoreJPQL score) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(score);
		JpaHelper.commitTransaction();
	}

	public void scoreToDatabaseJPQL(int userScore, String gameName, String userName) {
		
		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();

		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addScoreJpql(new ScoreJPQL(userScore, new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, method.findPlayerObjectbyID(userName), method.findGameObjectbyID(gameName)));
			}
			
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addScoreJpql(new ScoreJPQL(userScore, method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}

		}
	}
	
	public long countOfGamePlays(String gameName){
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(s.player) FROM ScoreJPQL s Where s.game=:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		//return ((Integer) query.getSingleResult()).intValue();
		Long avg = (Long) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}		
	}
	
	public List<ScoreJPQL> printScore(GameJPQL game){
		
		List<ScoreJPQL> score = new ArrayList<>();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("select s from ScoreJPQL s WHERE s.game =:gameID");
		query.setParameter("gameID", game).setMaxResults(10);		
		score = query.getResultList();
		return score;
	
	}
	
	public int highScore(String gameName){
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select MAX(s.score) from ScoreJPQL s where s.game =:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		if (query.getSingleResult()==null) {
			return 0;			
		}else{
			return (int) query.getSingleResult();
		}
	}
	

}
