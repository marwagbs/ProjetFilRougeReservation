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

import bo.ProduitCarte;

public class ProduitCarteDAOJdbcImpl implements GenericDAO<ProduitCarte> {

	private static final String SELECTWHERE = "SELECT * FROM produits_cartes WHERE id_produit =? and id_carte=?";
	private static final String INSERT = "INSERT INTO produits_cartes (id_produit, id_carte) VALUES (?,?)";
	private static final String DELETE = "DELETE FROM produits_cartes WHERE id =?";
	private static final String SELECT_BY_ID = "Select * from produits_cartes WHERE id = ? ";
	private static final String SELECT = "Select * from produits_cartes";
	private static final String UPDATE = "UPDATE produits_cartes set id_produit = ?, id_carte=?  WHERE id= ?";
	

	private Connection cnx;
	private Context context;

public	ProduitCarteDAOJdbcImpl() throws DALException{
	
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

public List<ProduitCarte> selectAll()throws DALException{
	
	
    List<ProduitCarte> produitsCartes=new ArrayList<>();
		
		PreparedStatement ps = null;
		try {
			ps = cnx.prepareStatement(SELECT);
			
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				ProduitCarte produitCarte = new ProduitCarte();
				produitCarte.setId(resultat.getInt("id_produit"));
				produitCarte.setId(resultat.getInt("id_carte"));
				
				produitsCartes.add(produitCarte);
			
			
			}
			
		} catch (SQLException e) {
			throw new DALException ("Imposible de récupérer les informations ", e);
		}

	
		
		return produitsCartes;
	}
public ProduitCarte selectWhere(int idProduit, int idCarte)throws DALException{
	
	
	ProduitCarte produitCarte = null;
		
		PreparedStatement ps = null;
		try {
			ps = cnx.prepareStatement(SELECTWHERE);
			
			ps.setInt(1, idProduit);
			ps.setInt(2, idCarte);
			
			
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				produitCarte = new ProduitCarte();
				produitCarte.setId(resultat.getInt("id_produit"));
				produitCarte.setId(resultat.getInt("id_carte"));			
			}
			
		} catch (SQLException e) {
			throw new DALException ("Imposible de récupérer les informations ", e);
		}

		return produitCarte;
	}


public ProduitCarte selectById(int id) throws DALException {
	
	ProduitCarte produitCarte = null;
	try {
		PreparedStatement  ps = cnx.prepareStatement(SELECT_BY_ID );
		ps.setInt(1, id); 
		ResultSet resultat=ps.executeQuery();
		if (resultat.next()) {
			produitCarte = new ProduitCarte();
			produitCarte.setId(resultat.getInt("id_produit"));
			produitCarte.setId(resultat.getInt("id_carte"));
		}
							
	} catch (SQLException e) {
		
		throw new DALException ("Imposible de récupérer l'informations pour id "+ id ,e);
	}
	
	return produitCarte;
	
}

public void insert(ProduitCarte produitCarte) throws DALException {
	
	try {
		PreparedStatement  ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		
		ps.setInt(1, produitCarte.getProduit().getId());
		ps.setInt(2, produitCarte.getCarte().getId());
		ps.executeUpdate();
		ResultSet rs=ps.getGeneratedKeys();
		if(rs.next()) { 
			int id= rs.getInt(1);
			produitCarte.setId(id);
		}
	} catch (SQLException e) {
		
		throw new DALException ("Imposible d'insérer les donnés.", e);
	}
	
}


public void update(ProduitCarte produitCarte) throws DALException {
	try {
		PreparedStatement  ps = cnx.prepareStatement(UPDATE);
		ps.setInt(1, produitCarte.getProduit().getId());
		ps.setInt(2, produitCarte.getCarte().getId());
		ps.executeUpdate();
	} catch (SQLException e) {
		throw new DALException ("Imposible de modifier les données. "+ produitCarte.getId(), e);
	}
}

public void delete (int id) throws DALException { 
	try {
		PreparedStatement  ps = cnx.prepareStatement(DELETE);
		ps.setInt(1, id);
		
		int nbLignesSupprimees=ps.executeUpdate();
		if(nbLignesSupprimees==0) {
			throw new DALException ("Echec de suppression du  produit d'id " + id ,null);
		}
	} catch (SQLException e) {
		
		throw new DALException ("Imposible de supprimer le produit d'id " + id ,e);
	}
}




}
