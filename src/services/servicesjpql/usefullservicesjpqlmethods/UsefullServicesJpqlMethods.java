package services.servicesjpql.usefullservicesjpqlmethods;

import javax.persistence.EntityManager;


import javax.persistence.Query;

import entity.entityjpql.GameJPQL;
import entity.entityjpql.PlayerJPQL;
import services.JpaHelper;

public class UsefullServicesJpqlMethods {

	public boolean isUserUniqueJpql(String userName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT playerName FROM PlayerJPQL p WHERE p.playerName =:userName");
		query.setParameter("userName", userName);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isGameUniqueJpql(String gameName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT gameName FROM GameJPQL g WHERE g.gameName =:gameName");
		query.setParameter("gameName", gameName);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	public int getGameIdOfUniqueGame(String gameName) {
		int ident;

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT gameID FROM GameJPQL g WHERE g.gameName =:gameName");
		query.setParameter("gameName", gameName);

		if (query.getResultList().isEmpty()) {
			ident = 0;
		} else {

			ident = (int) query.getResultList().get(0);
		}
		return ident;
	}

	public int getPlayerIdOfUniquePlayer(String userName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("SELECT playerID FROM PlayerJPQL p WHERE p.playerName =:userName");
		query.setParameter("userName", userName);

		int ident = (int) query.getResultList().get(0);
		return ident;

	}

	public PlayerJPQL findPlayerObjectbyID(String userName) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(PlayerJPQL.class, getPlayerIdOfUniquePlayer(userName));
	}

	public GameJPQL findGameObjectbyID(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		return em.find(GameJPQL.class, getGameIdOfUniqueGame(gameName));
	}

	public long countOfPlayers(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(DISTINCT s.player) FROM ScoreJPQL s Where s.game=:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		// return ((Integer) query.getSingleResult()).intValue();
		Long avg = (Long) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}
	}

	public long numberOfPlayers() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(p.playerID) from PlayerJPQL p");
		Integer number = (int) (long) query.getSingleResult();
		return number;
	}

	public long numberOfGames() {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(g.gameID) from GameJPQL g");
		Integer number = (int) (long) query.getSingleResult();
		return number;
	}

	public void addPlayerToDatabase(PlayerJPQL player) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(player);
		JpaHelper.commitTransaction();
	}

	public void playerToDatabaseJpql(String playerName, String playerPwd) {
		addPlayerToDatabase(new PlayerJPQL(playerName, playerPwd));
	}

	public boolean isUserRegistered(String playerName, String playerPwd) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery(
				"Select p.playerName FROM PlayerJPQL p Where p.playerName=:playerName AND p.playerPwd=:playerPwd");
		System.out.println(playerName);
		System.out.println(playerPwd);
		query.setParameter("playerName", playerName);
		query.setParameter("playerPwd", playerPwd);

		if (query.getResultList().isEmpty()) {
			return true;
		} else {
			return false;
		}

	}

}
