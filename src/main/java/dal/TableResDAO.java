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

import bo.Restaurant;
import bo.TableRes;

public class TableResDAO implements GenericDAO<TableRes> {
	
	private static final String DELETE = "DELETE FROM tablesRes WHERE id = ?";
	private static final String UPDATE = "UPDATE tablesRes SET nombre_places = ?, numero_table = ?, statut = ?, id_restaurant = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO tablesRes (nombre_places, numero_table, statut, id_restaurant) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM tablesRes JOIN restaurants ON tablesRes.id_restaurant = restaurants.id WHERE tablesRes.id = ?";
	private static final String SELECT = "SELECT * FROM tablesRes JOIN restaurants ON tablesRes.id_restaurant = restaurants.id";
	
	private Connection cnx;
	private Context context;

	public TableResDAO() throws DALException {
		try {
			context = new InitialContext();
			DataSource dataSource = (DataSource) context.lookup("java:comp/env/sqlserver");
			 cnx = dataSource.getConnection();
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

	public List<TableRes> selectAll() throws DALException {
		List<TableRes> tablesRes = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TableRes tableRes = new TableRes();
				Restaurant restaurant = new Restaurant();
				tableRes.setId(rs.getInt("id"));
				tableRes.setNombrePlaces(rs.getInt("nombre_places"));
				tableRes.setNumeroTable(rs.getInt("numero_table"));
				tableRes.setStatut(rs.getString("statut"));
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				tableRes.setRestaurant(restaurant);
				
				tablesRes.add(tableRes);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}	
		return tablesRes;
		
	}

	public TableRes selectById(int id) throws DALException {
		TableRes tableRes = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				tableRes = new TableRes();
				Restaurant restaurant = new Restaurant();
				tableRes.setId(rs.getInt("id"));
				tableRes.setNombrePlaces(rs.getInt("nombre_places"));
				tableRes.setNumeroTable(rs.getInt("numero_table"));
				tableRes.setStatut(rs.getString("statut"));
				restaurant.setId(rs.getInt("id_restaurant"));
				restaurant.setNom(rs.getString("nom"));
				restaurant.setAdresse(rs.getString("adresse"));
				restaurant.setCpo(rs.getString("cpo"));
				restaurant.setVille(rs.getString("ville"));
				tableRes.setRestaurant(restaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return tableRes;	
	}
	
	public void insert(TableRes tableRes) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setInt(1, tableRes.getNombrePlaces());
			ps.setInt(2, tableRes.getNumeroTable());
			ps.setString(3, tableRes.getStatut());
			ps.setInt(4, tableRes.getRestaurant().getId());
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				tableRes.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	
	public void update(TableRes tableRes) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setInt(1, tableRes.getNombrePlaces());
			ps.setInt(2, tableRes.getNumeroTable());
			ps.setString(3, tableRes.getStatut());
			ps.setInt(4, tableRes.getRestaurant().getId());
			ps.setInt(5, tableRes.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + tableRes.getId(), e);
		}		
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de la table d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer la table d'id " + id, e);
		}
	}
}
