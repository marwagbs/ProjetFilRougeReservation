package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Horaire;
import bo.HoraireRestaurant;
import bo.Restaurant;

public class HoraireRestaurantDAOJdbcimpl implements GenericDAO<HoraireRestaurant>{
	private static final String TABLE_NAME = "Horaires_restaurants";
	private static final String DELETE = "DELETE FROM"+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET id_horaire = ?, id_restaurant = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO Horaires_restaurants (id_horaire, id_restaurant) VALUES (?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;
	private static final String COUNT = "SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE id_horaire = ? AND id_restaurant = ?";
	private static final String SELECT_ALL_BY_RESTAURANT = "SELECT * FROM " + TABLE_NAME + " JOIN Horaires h ON id_horaire = h.id WHERE id_restaurant = ?";

	
	
	
	private Connection cnx;
	private Context context;
	
	public HoraireRestaurantDAOJdbcimpl() throws DALException {
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


	
	//-------------------------------------------------------------------//
	public List<HoraireRestaurant> selectAll() throws DALException {
    List<HoraireRestaurant> horairesRestaurants = new ArrayList<>();
    try {
    	PreparedStatement ps = cnx.prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			HoraireRestaurant horaireRestaurant = new HoraireRestaurant();
			
			horaireRestaurant.setId(rs.getInt("id"));
			horaireRestaurant.getRestaurant().setId(rs.getInt("id"));
			horaireRestaurant.getHoraire().setId(rs.getInt("id"));
			horairesRestaurants.add(horaireRestaurant);
		}
	} catch (SQLException e) {
		throw new DALException("Impossible de recuperer les informations", e);
	}
    return horairesRestaurants;
	}

//-------------------------------------------------------------------------------//
	public HoraireRestaurant selectById(int id) throws DALException {
		HoraireRestaurant horaireRestaurant = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				horaireRestaurant = new HoraireRestaurant();
				horaireRestaurant.setId(rs.getInt("id"));
				horaireRestaurant.getRestaurant().setId(rs.getInt("id"));
				horaireRestaurant.getHoraire().setId(rs.getInt("id"));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id "+ id, e);
		}
		return horaireRestaurant;
	}
//-------------------------------------------------------------------------------------------//

	public void insert(HoraireRestaurant horaireRestaurant) throws DALException {
	try {
		
		if (!isCombinationExists(horaireRestaurant.getHoraire().getId(),horaireRestaurant.getRestaurant().getId())) 
		{
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
	        ps.setInt(1, horaireRestaurant.getHoraire().getId());
	        ps.setInt(2, horaireRestaurant.getRestaurant().getId());
	    	ps.executeUpdate();

			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				horaireRestaurant.setId(id);
		}
		
		}
	} catch (SQLException e) {
		throw new DALException("Impossible d'inserer les donnees.", e);
	}
}

//-------------------------------------------------------------------------------------------//
	public void update(HoraireRestaurant horaireRestaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setInt(1, horaireRestaurant.getRestaurant().getId());
	        ps.setInt(2, horaireRestaurant.getHoraire().getId());
	        ps.setInt(3, horaireRestaurant.getId());
	    	ps.executeUpdate();
		
	    	ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id "+ horaireRestaurant.getId(), e);
		}
	}

	 //---------------------------------------------------------//
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de l'horaire d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer l'horaire d'id "+ id, e);
		}
		
	}
	//-------------------------------------------------------------------//
	public boolean isCombinationExists(int idHoraire, int idRestaurant) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(COUNT);
			ps.setInt(1, idHoraire);
			ps.setInt(2, idRestaurant);
			
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;  // Return true if the combination exists, false otherwise
                }
            }
		} catch (SQLException e) {
			throw new DALException("Cette ligne existe deja dans la table", e);	
		}
		return false;
	}
	//---------------------------------------------------------------//
	public List<HoraireRestaurant> selectAllByRestaurant(int id) throws DALException {
	    List<HoraireRestaurant> horairesRestaurants = new ArrayList<>();
	   
	    
	    try {
	    	PreparedStatement ps = cnx.prepareStatement(SELECT_ALL_BY_RESTAURANT);
	    	ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			
			
			while (rs.next()) {
				HoraireRestaurant horaireRestaurant = new HoraireRestaurant();
				Horaire horaire = new Horaire();
				Restaurant restaurant = new Restaurant();
				
				
				horaireRestaurant.setHoraire(horaire);
				horaireRestaurant.setRestaurant(restaurant);
				
				horaireRestaurant.setId(rs.getInt("id"));
				horaireRestaurant.getRestaurant().setId(rs.getInt("id"));
				horaireRestaurant.getHoraire().setId(rs.getInt("id"));
				horaire.setJour(rs.getString("jour"));
				horaire.setHeureOuverture(LocalTime.parse(rs.getString("heure_ouverture"), DateTimeFormatter.ofPattern("HH:mm:ss.n")));
				horaire.setHeureFermeture(LocalTime.parse(rs.getString("heure_fermeture"), DateTimeFormatter.ofPattern("HH:mm:ss.n")));
				
				horairesRestaurants.add(horaireRestaurant);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}
	    return horairesRestaurants;
		}

}
