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

public class CarteDAO implements GenericDAO<Carte> {

	private static final String DELETE = "DELETE FROM cartes WHERE id = ?";
	private static final String UPDATE = "UPDATE cartes SET libelle = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO cartes (libelle) VALUES (?)";
	private static final String SELECT_BY_ID = "select c.id AS id_carte ,c.libelle, r.id AS id_restaurant, r.nom, r.adresse, r.cpo, r.ville   from cartes c inner join Restaurants r ON  r.id_carte=c.id WHERE c.id = ?";
	private static final String SELECT = "select c.id AS id_carte ,c.libelle, r.id AS id_restaurant, r.nom, r.adresse, r.cpo, r.ville   from cartes c inner join Restaurants r ON  r.id_carte=c.id";
	
	private Connection cnx;
	private Context context;
	
	public CarteDAO() throws DALException {
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

	
	public List<Carte> selectAll() throws DALException {
		List<Carte> cartes = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Carte carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				carte.setLibelle(rs.getString("libelle"));
				Restaurant restaurant=new Restaurant();
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				carte.setRestaurant(restaurant);
				cartes.add(carte);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}
		return cartes;
	}
	
	public Carte selectById(int id) throws DALException {
		Carte carte = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				carte = new Carte();
				carte.setId(rs.getInt("id_carte"));
				carte.setLibelle(rs.getString("libelle"));
				Restaurant restaurant=new Restaurant();
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				carte.setRestaurant(restaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return carte;
	}
	
	public void insert(Carte carte) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, carte.getLibelle());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				carte.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	
	public void update(Carte carte) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, carte.getLibelle());
			ps.setInt(2, carte.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + carte.getId(), e);
		}
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de la carte d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la carte d'id " + id, e);
		}
	}
}
