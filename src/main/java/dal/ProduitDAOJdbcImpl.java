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
import bo.Categorie;
import bo.Produit;

public class ProduitDAOJdbcImpl  implements GenericDAO<Produit>{
	
	private static final String UPDATE = "UPDATE produits set nom=? , description =? , prix =? WHERE id= ?";
	private static final String DELETE = "DELETE FROM produits WHERE id =?";
	private static final String INSERT = "INSERT INTO produits ( nom, description, prix , id_categorie ) VALUES (?,?,?,?)";
	private static final String SELECT_BY_ID ="SELECT"
			+ "    P.id AS id_produit,"
			+ "    P.nom ,"
			+ "    P.description , "
			+ "    P.prix, "
			+ "    C.id AS id_carte, "
			+ "    C.libelle , "
			+ "    CAT.id AS id_categorie, "
			+ "    CAT.libelle "
			+ "FROM "
			+ "    Produits AS P "
			+ "INNER JOIN "
			+ "    Produits_Cartes AS PC ON P.id = PC.id_produit "
			+ "INNER JOIN "
			+ "    Cartes AS C ON PC.id_carte = C.id "
			+ "INNER JOIN "
			+ "    Categories AS CAT ON P.id_categorie = CAT.id WHERE P.id=  ?";
	
	
	private static final String SELECT = "SELECT * from produits  ";
	

	private static final String SELECTByCarte = "SELECT"
			+ "    P.id AS id_produit,"
			+ "    P.nom ,"
			+ "    P.description , "
			+ "    P.prix, "
			+ "    C.id AS id_carte, "
			+ "    C.libelle , "
			+ "    CAT.id AS id_categorie, "
			+ "    CAT.libelle "
			+ "FROM "
			+ "    Produits AS P "
			+ "INNER JOIN "
			+ "    Produits_Cartes AS PC ON P.id = PC.id_produit "
			+ "INNER JOIN "
			+ "    Cartes AS C ON PC.id_carte = C.id "
			+ "INNER JOIN "
			+ "    Categories AS CAT ON P.id_categorie = CAT.id where c.id= ? ";
	
	
	private static final String SELECT_BY_Nom = "select * from Produits where nom= ?";
	
	private Connection cnx;
	private Context context;
	
	
	public	ProduitDAOJdbcImpl() throws DALException{
		
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
	
	public List<Produit> selectAll()throws DALException{
		
		
     List<Produit> produits=new ArrayList<>();
		
		PreparedStatement ps = null;
		try {
			ps = cnx.prepareStatement(SELECT);
			 
			ResultSet resultat=ps.executeQuery();
			while(resultat.next()) {
				Produit produit = new Produit();
				produit.setId(resultat.getInt("id_produit"));
				produit.setNom(resultat.getString("nom"));
				produit.setDescription(resultat.getString("description"));
				produit.setPrix(resultat.getFloat("prix"));
//				Categorie categorie=new Categorie();
//				categorie.setId(resultat.getInt("id_categorie"));
//				categorie.setLibelle(resultat.getString("libelle"));
//				produit.setCategorie(categorie);
//				Carte carte=new Carte();
//				carte.setId(resultat.getInt("id_carte"));
//				carte.setLibelle(resultat.getString("libelle"));
//				produit.setCarte(carte);
				produits.add(produit);
			
			}
			
		} catch (SQLException e) {
			throw new DALException ("Imposible de récupérer les informations ", e);
		}

	
		
		return produits;
	}
	
	public List<Produit> selectAllByCarte(int id)throws DALException{
		
		
	     List<Produit> produits=new ArrayList<>();
			
			PreparedStatement ps = null;
			try {
				ps = cnx.prepareStatement(SELECTByCarte);
				ps.setInt(1, id); 
				ResultSet resultat=ps.executeQuery();
				while(resultat.next()) {
					Produit produit = new Produit();
					produit.setId(resultat.getInt("id_produit"));
					produit.setNom(resultat.getString("nom"));
					produit.setDescription(resultat.getString("description"));
					produit.setPrix(resultat.getFloat("prix"));
					Categorie categorie=new Categorie();
					categorie.setId(resultat.getInt("id_categorie"));
					categorie.setLibelle(resultat.getString("libelle"));
					produit.setCategorie(categorie);
					Carte carte=new Carte();
					carte.setId(resultat.getInt("id_carte"));
					carte.setLibelle(resultat.getString("libelle"));
					produit.setCarte(carte);
					produits.add(produit);
				
				}
				
			} catch (SQLException e) {
				throw new DALException ("Imposible de récupérer les informations ", e);
			}

		
			
			return produits;
		}
		
	
	public Produit selectById(int id) throws DALException{
		Produit produit=null;
	
		try {
			PreparedStatement  ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id); 
			ResultSet resultat=ps.executeQuery();
			if (resultat.next()) {
			    produit = new Produit();
				produit.setId(resultat.getInt("id_produit"));
				produit.setNom(resultat.getString("nom"));
				produit.setDescription(resultat.getString("description"));
				produit.setPrix(resultat.getFloat("prix"));
				Categorie categorie=new Categorie();
				categorie.setId(resultat.getInt("id_categorie"));
				categorie.setLibelle(resultat.getString("libelle"));
				produit.setCategorie(categorie);
				Carte carte=new Carte();
				carte.setId(resultat.getInt("id_carte"));
				carte.setLibelle(resultat.getString("libelle"));
				produit.setCarte(carte);
				
			}
								
		} catch (SQLException e) {
			
			throw new DALException ("Imposible de récupérer l'information pour id "+ id ,e);
		}
		return produit;
		
	}
	
	
	public Produit selectByNom(String nom) throws DALException{
		Produit produit=null;
	
		try {
			PreparedStatement  ps = cnx.prepareStatement(SELECT_BY_Nom);
			ps.setString(1, nom); 
			ResultSet resultat=ps.executeQuery();
			if (resultat.next()) {
				produit = new Produit();
				produit.setId(resultat.getInt("id"));
				produit.setNom(resultat.getString("nom"));
				produit.setDescription(resultat.getString("description"));
				produit.setPrix(resultat.getFloat("prix"));
			
			}
								
		} catch (SQLException e) {
			
			throw new DALException ("Imposible de récupérer l'information pour le nom "+ nom ,e);
		}
		return produit;
		
	}
	public void insert(Produit produit)throws DALException{
		try {
			
			PreparedStatement  ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
		
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setFloat(3, produit.getPrix());
			ps.setInt(4, produit.getCategorie().getId());
			
		
			ps.executeUpdate();
			ResultSet rs=ps.getGeneratedKeys();
			if(rs.next()) { 
				int id= rs.getInt(1);
				produit.setId(id);
			}
		} catch (SQLException e) {
			
			throw new DALException ("Imposible d'insérer les données.", e);
		}
		
	}
	
	public void update(Produit produit)throws DALException{
		try {
			PreparedStatement  ps = cnx.prepareStatement(UPDATE);
		
			ps.setString(1, produit.getNom());
			ps.setString(2, produit.getDescription());
			ps.setFloat(3, produit.getPrix());
			ps.setInt(4, produit.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			
			throw new DALException ("Imposible de modifier les données.", e);
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


