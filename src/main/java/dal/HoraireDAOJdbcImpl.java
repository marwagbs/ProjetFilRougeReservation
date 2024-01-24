package dal;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Horaire;

public class HoraireDAOJdbcImpl implements GenericDAO<Horaire>   {
private static final String TABLE_NAME = " Horaires ";
	
	private static final String DELETE = "DELETE FROM"+ TABLE_NAME +" WHERE id = ?";
	private static final String UPDATE = "UPDATE "+ TABLE_NAME +" SET jour = ?, heure_ouverture = ?, heure_fermeture = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO "+ TABLE_NAME +" (jour, heure_ouverture, heure_fermeture) VALUES (?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM "+ TABLE_NAME +" WHERE id = ?";
	private static final String SELECT = "SELECT * FROM "+ TABLE_NAME;
	
	private Connection cnx;
	private Context context;

	
	// Constructeur 
	public HoraireDAOJdbcImpl() throws DALException {
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

//------------------------------------------------------------------------//
	public List<Horaire> selectAll() throws DALException {
List<Horaire> horaires = new ArrayList<>(); 
	
		
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Horaire horaire = new Horaire();
				horaire.setId(rs.getInt("id"));
				horaire.setJour(rs.getString("jour"));
				horaire.setHeureOuverture(rs.getTime("heure_ouverture").toLocalTime());
	            horaire.setHeureFermeture(rs.getTime("heure_fermeture").toLocalTime());
				horaires.add(horaire);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations", e);
		}
		return horaires;
	}

	//------------------------------------------------------------------------//
	public Horaire selectById(int id) throws DALException {
		Horaire horaire = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); // Remplace le '?' numero 1 par la valeur de l'id
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				horaire = new Horaire();
				horaire.setId(rs.getInt("id"));
				horaire.setJour(rs.getString("jour"));
				horaire.setHeureOuverture(rs.getTime("heure_ouverture").toLocalTime());
	            horaire.setHeureFermeture(rs.getTime("heure_fermeture").toLocalTime());
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de recuperer les informations pour l'id "+ id, e);
		}
		return horaire;
	}

	
	//------------------------------------------------------------------------//
	public void insert(Horaire horaire) throws DALException {
		try {
			// L'ajout de RETURN_GENERATED_KEYS permet de récupérer l'id généré par la base
			PreparedStatement ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, horaire.getJour());
			ps.setTime(2, Time.valueOf(horaire.getHeureOuverture()));
	        ps.setTime(3, Time.valueOf(horaire.getHeureFermeture()));
			ps.executeUpdate();
			
			// Le bloc suivant permet de faire la récupération de l'id
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) { // Va chercher dans le resultat, la première ligne
				int id = rs.getInt(1); // plus précisément, le int à la première colonne
				horaire.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les donnees.", e);
		}
	}

	//------------------------------------------------------------------------//
	public void update(Horaire horaire) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, horaire.getJour());
			ps.setTime(2, Time.valueOf(horaire.getHeureOuverture()));
	        ps.setTime(3, Time.valueOf(horaire.getHeureFermeture()));
			ps.setInt(4, horaire.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre a jour les informations pour l'id "+ horaire.getId(), e);
		}
	}

	//------------------------------------------------------------------------//
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

}
