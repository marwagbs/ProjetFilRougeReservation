package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Carte;
import bo.Restaurant;

public class RestaurantDAO implements GenericDAO<Restaurant> {
	
	private static final String DELETE = "DELETE FROM restaurants WHERE id = ?";
	private static final String UPDATE = "UPDATE restaurants SET nom = ?, adresse = ?, cpo = ?, ville = ?, id_carte = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO restaurants (nom, adresse, cpo, ville) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM restaurants WHERE id = ?";
	private static final String SELECT = "SELECT R.id AS id_restaurant, R.nom, R.adresse, R.cpo, R.ville, C.id AS id_carte FROM  Restaurants AS R INNER JOIN Cartes AS C ON R.id_carte = C.id order by id_restaurant";
	
	private Connection cnx;
	private Context context;
	public RestaurantDAO() throws DALException {
		try {
			context = new InitialContext();
			DataSource dataSource= (DataSource) context.lookup("java:comp/env/sqlserver");
			 cnx =dataSource.getConnection();
			 if(!cnx.isClosed()) {
					System.out.println("la connexion est ouverte");
				}
		} catch (NamingException e) {
			
			e.printStackTrace();
		}
		 catch (SQLException e) {
			
			e.printStackTrace();
		}
	}
	
	public List<Restaurant> selectAll() throws DALException {
		List<Restaurant> restaurants = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				Restaurant restaurant = new Restaurant();
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				Carte carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				restaurant.setCarte(carte);
				
				restaurants.add(restaurant);
			}

		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}	
		return restaurants;
		
	}

	public Restaurant selectById(int id) throws DALException {
		Restaurant restaurant = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				restaurant = new Restaurant();
				Carte carte = new Carte();
				restaurant.setId(rs.getInt("id"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				carte.setId(rs.getInt("id_carte"));
				restaurant.setCarte(carte);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return restaurant;	
	}
	
	public void insert(Restaurant restaurant) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setString(3, restaurant.getCpo());
			ps.setString(4, restaurant.getVille());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				restaurant.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	
	public void update(Restaurant restaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, restaurant.getNom());
			ps.setString(2, restaurant.getAdresse());
			ps.setString(3, restaurant.getCpo());
			ps.setString(4, restaurant.getVille());
			ps.setInt(5, restaurant.getCarte().getId());
			ps.setInt(6, restaurant.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + restaurant.getId(), e);
		}		
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de le restaurant d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer le restaurant d'id " + id, e);
		}
	}


	

}
