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

import bo.Message;
import bo.Restaurant;

public class MessageDAO implements GenericDAO<Message> {

	private static final String DELETE = "DELETE FROM messages WHERE id = ?";
	private static final String UPDATE = "UPDATE messages SET contenu = ? , sujet=?  WHERE id = ?";
	private static final String INSERT = "INSERT INTO messages (contenu , sujet , id_utilisateur, id_restaurant) VALUES (? , ?, ? ,?)";
	private static final String SELECT_BY_ID ="select m.id AS id_message , "
			+ "m.contenu,m.sujet, r.id AS id_restaurant, r.nom, "
			+ "r.adresse, r.cpo, r.ville   from messages m "
			+ "inner join Restaurants r "
			+ "ON  r.id=m.id_restaurant  "
			+ "WHERE m.id = 1" ;
	private static final String SELECT = "select m.id AS id_message , "
			+ "m.contenu,m.sujet, r.id AS id_restaurant, r.nom, "
			+ "r.adresse, r.cpo, r.ville   from messages m "
			+ "inner join Restaurants r "
			+ "ON  r.id=m.id_restaurant ";
	
	private Connection cnx;
	private Context context;
	
	public MessageDAO() throws DALException {
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

	
	public List<Message> selectAll() throws DALException {
		List<Message> messages = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getInt("id_message"));
				message.setContenu(rs.getString("contenu"));
				message.setSujet(rs.getString("sujet"));
				Restaurant restaurant=new Restaurant();
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				message.setRestaurant(restaurant);
				messages.add(message);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}
		return messages;
	}
	
	public Message selectById(int id) throws DALException {
		Message message = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				message = new Message();
				message.setId(rs.getInt("id_message"));
				message.setContenu(rs.getString("contenu"));
				message.setSujet(rs.getString("sujet"));
				Restaurant restaurant=new Restaurant();
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				message.setRestaurant(restaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return message;
	}
	
	public void insert(Message message) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, message.getContenu());
			ps.setString(2, message.getSujet());
			ps.setInt(3, message.getUtilisateur().getId());
			ps.setInt(4, message.getRestaurant().getId());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				message.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	
	public void update(Message message) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, message.getContenu());
			ps.setString(2, message.getSujet());
			ps.setInt(3, message.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + message.getId(), e);
		}
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de la message d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la message d'id " + id, e);
		}
	}
}
