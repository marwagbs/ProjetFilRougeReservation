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

import bo.Categorie;


public class CategorieDAOJdbcImpl implements GenericDAO<Categorie> {
	
	private static final String DELETE = "DELETE FROM categories WHERE id= ?";
	private static final String UPDATE = "UPDATE categories set libelle = ?  WHERE id= ?";
	private static final String INSERT = "INSERT INTO categories (libelle) VALUES (?)";
	private static final String SELECT_BY_ID = "Select * from categories WHERE id = ? ";
	private static final String SELECT = "Select * from categories";
	
	private Connection cnx;
	private Context context;

public	CategorieDAOJdbcImpl() throws DALException{
	
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

public List<Categorie> selectAll() throws DALException {
	List<Categorie> categories=new ArrayList<>();
	
	PreparedStatement ps = null;
	try {
		ps = cnx.prepareStatement(SELECT);
		
		ResultSet resultat=ps.executeQuery();
		while(resultat.next()) {
			Categorie categorie = new Categorie();
			categorie.setId(resultat.getInt("id"));
			categorie.setLibelle(resultat.getString("libelle"));
			
			categories.add(categorie);
		
		}
		
	} catch (SQLException e) {
		throw new DALException ("Imposible de récupérer les informations ", e);
	}


	
	return categories;
	
}

public Categorie selectById(int id) throws DALException {
	
	Categorie categorie = null;
	try {
		PreparedStatement  ps = cnx.prepareStatement(SELECT_BY_ID );
		ps.setInt(1, id); 
		ResultSet resultat=ps.executeQuery();
		if (resultat.next()) {
			categorie = new Categorie();
			categorie.setId(resultat.getInt("id"));
			categorie.setLibelle(resultat.getString("libelle"));
		}
							
	} catch (SQLException e) {
		
		throw new DALException ("Imposible de récupérer l'informations pour id "+ id ,e);
	}
	
	return categorie;
	
}

public void insert(Categorie categorie) throws DALException {
	
	try {
		PreparedStatement  ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		
		ps.setString(1, categorie.getLibelle());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		if(rs.next()) { 
			int id= rs.getInt(1);
			categorie.setId(id);
		}
	} catch (SQLException e) {
		
		throw new DALException ("Imposible d'insérer les donnés.", e);
	}
	
}

public void update(Categorie categorie) throws DALException {
	try {
		PreparedStatement  ps = cnx.prepareStatement(UPDATE);
		ps.setString(1, categorie.getLibelle());
		ps.executeUpdate();
	} catch (SQLException e) {
		throw new DALException ("Imposible de modifier les données. "+ categorie.getId(), e);
	}
}

	public void delete(int id) throws DALException { 
	try {
		PreparedStatement  ps = cnx.prepareStatement(DELETE);
		ps.setInt(1, id);
		
		int nbLignesSupprimees=ps.executeUpdate();
		if(nbLignesSupprimees==0) {
			throw new DALException ("Echec de suppression du categorie d'id " + id ,null);
		}
	} catch (SQLException e) {
		
		throw new DALException ("Imposible de supprimer la categorie d'id " + id ,e);
	}
}

	




}
