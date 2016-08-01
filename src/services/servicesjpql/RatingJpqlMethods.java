package services.servicesjpql;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entity.entityjpql.GameJPQL;
import entity.entityjpql.PlayerJPQL;
import entity.entityjpql.RatingJPQL;
import services.JpaHelper;
import services.servicesjpql.usefullservicesjpqlmethods.UsefullServicesJpqlMethods;
import usefullmethods.ReadLine;

public class RatingJpqlMethods {

	public void addRatingJpql(RatingJPQL rating) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		em.persist(rating);
		JpaHelper.commitTransaction();
	}

	public int writeRating() {
		System.out.println("PLease write down your rating.");
		String userRating = new ReadLine().readLine();
		return Integer.parseInt(userRating);
	}

	public void ratingToDatabaseJPQL(int rating, String gameName, String userName) {

		UsefullServicesJpqlMethods method = new UsefullServicesJpqlMethods();

		if (method.isGameUniqueJpql(gameName) && method.isUserUniqueJpql(userName)) {
			addRatingJpql(new RatingJPQL(rating, new PlayerJPQL(userName), new GameJPQL(gameName)));
		} else {
			if (method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(rating, new PlayerJPQL(userName), method.findGameObjectbyID(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(rating, method.findPlayerObjectbyID(userName), new GameJPQL(gameName)));
			}
			if (!method.isUserUniqueJpql(userName) && !method.isGameUniqueJpql(gameName)) {
				addRatingJpql(new RatingJPQL(rating, method.findPlayerObjectbyID(userName),
						method.findGameObjectbyID(gameName)));
			}
		}
	}

	public int getRatingId(String userName, String gameName) {

		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select r.ratingID FROM RatingJPQL r Where r.game=:gameID AND r.player=:userID");
		query.setParameter("userID", new UsefullServicesJpqlMethods().findPlayerObjectbyID(userName));
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));

		if (query.getResultList().isEmpty()) {
			return 0;
		} else {
			return (int) query.getResultList().get(0);
		}
	}

	public void deleteExistingRating(String userName, String gameName) {
		JpaHelper.beginTransaction();
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("DELETE FROM RatingJPQL r Where r.ratingID=:ratingID ");
		query.setParameter("ratingID", getRatingId(userName, gameName));
		query.executeUpdate();
		JpaHelper.commitTransaction();
	}

	public void addUniqueRatingToDatabase(int rating, String gameName, String userName) {
		if (new UsefullServicesJpqlMethods().isGameUniqueJpql(gameName)
				&& new UsefullServicesJpqlMethods().isUserUniqueJpql(userName)) {
			ratingToDatabaseJPQL(rating, gameName, userName);
		} else {
			deleteExistingRating(userName, gameName);
			ratingToDatabaseJPQL(rating, gameName, userName);
		}
	}

	public double averageRating(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select AVG(cast(r.rating as double)) FROM RatingJPQL r Where r.game=:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		Double avg = (Double) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}
	}

	public long countOfRatings(String gameName) {
		EntityManager em = JpaHelper.getEntityManager();
		Query query = em.createQuery("Select Count(r.rating) FROM RatingJPQL r Where r.game=:gameID");
		query.setParameter("gameID", new UsefullServicesJpqlMethods().findGameObjectbyID(gameName));
		// return ((Integer) query.getSingleResult()).intValue();
		Long avg = (Long) query.getSingleResult();
		if (avg == null) {
			return 0;
		} else {
			return avg;
		}
	}

}
