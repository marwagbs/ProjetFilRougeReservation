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

import bo.Utilisateur;

public class UtilisateurDAO implements GenericDAO<Utilisateur> {
	private static final String SELECT_BY_EMAIL = "SELECT * FROM utilisateurs WHERE email = ?";
	private static final String DELETE = "DELETE FROM utilisateurs WHERE id = ?";
	private static final String UPDATE = "UPDATE utilisateurs SET nom = ?, prenom = ?, email = ?, mot_de_passe = ?, telephone = ?, isAdmin = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO utilisateurs (nom, prenom, email, mot_de_passe, telephone, isAdmin) VALUES (?,?,?,?,?,?)";
	private static final String SELECT_BY_ID = "SELECT * FROM utilisateurs WHERE id = ?";
	private static final String SELECT = "SELECT * FROM utilisateurs";
	
	private Connection cnx;
	private Context context;
	public UtilisateurDAO() throws DALException {
		try {
			context = new InitialContext();
			DataSource dataSource= (DataSource) context.lookup("java:comp/env/sqlserver");
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
	
	public List<Utilisateur> selectAll() throws DALException {
		List<Utilisateur> utilisateurs = new ArrayList<>();
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setIsAdmin(rs.getBoolean("isAdmin"));
				
				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}	
		return utilisateurs;
		
	}

	public Utilisateur selectById(int id) throws DALException {
		Utilisateur utilisateur = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setIsAdmin(rs.getBoolean("isAdmin"));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return utilisateur;	
	}
	
	
	public Utilisateur selectByEmail(String email) throws DALException {
		Utilisateur utilisateur = null;
		try {
			PreparedStatement ps = cnx.prepareStatement("SELECT * FROM utilisateurs WHERE email = ?");
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				utilisateur = new Utilisateur();
				utilisateur.setId(rs.getInt("id"));
				utilisateur.setNom(rs.getString("nom"));
				utilisateur.setPrenom(rs.getString("prenom"));
				utilisateur.setEmail(rs.getString("email"));
				utilisateur.setMotDePasse(rs.getString("mot_de_passe"));
				utilisateur.setTelephone(rs.getString("telephone"));
				utilisateur.setIsAdmin(rs.getBoolean("isAdmin"));
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + email, e);
		}
		return utilisateur;	
	}
	
	
	public void insert(Utilisateur utilisateur) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getEmail());
			ps.setString(4, utilisateur.getMotDePasse());
			ps.setString(5, utilisateur.getTelephone());
			ps.setBoolean(6, utilisateur.getIsAdmin());
			
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				utilisateur.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	
	public void update(Utilisateur utilisateur) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE);
			ps.setString(1, utilisateur.getNom());
			ps.setString(2, utilisateur.getPrenom());
			ps.setString(3, utilisateur.getEmail());
			ps.setString(4, utilisateur.getMotDePasse());
			ps.setString(5, utilisateur.getTelephone());
			ps.setBoolean(6, utilisateur.getIsAdmin());
			ps.setInt(7, utilisateur.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + utilisateur.getId(), e);
		}		
	}
	
	public void delete(int id) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(DELETE);
			ps.setInt(1, id);
			int nbLignesSupprimees = ps.executeUpdate();
			if (nbLignesSupprimees == 0) {
				throw new DALException("Echec de suppression de le utilisateur d'id " + id, null);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de supprimer le utilisateur d'id " + id, e);
		}
	}
}
