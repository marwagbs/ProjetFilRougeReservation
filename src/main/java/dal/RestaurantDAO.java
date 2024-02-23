package dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Carte;
import bo.Horaire;
import bo.Restaurant;
import bo.TableRes;

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


	public List<Restaurant> selectAllRes() throws DALException {
	    List<Restaurant> restaurants = new ArrayList<>();
	    try {
	    	PreparedStatement ps = cnx.prepareStatement("SELECT\r\n"
	    			+ "    R.id AS id_restaurant,\r\n"
	    			+ "    R.nom,\r\n"
	    			+ "    R.adresse,\r\n"
	    			+ "    R.cpo,\r\n"
	    			+ "    R.ville,\r\n"
	    			+ "    STUFF((\r\n"
	    			+ "            SELECT '; ' + H.jour + ' : ' + \r\n"
	    			+ "                STRING_AGG(CONVERT(varchar, H.heure_ouverture, 108) + '-' + CONVERT(varchar, H.heure_fermeture, 108), ', ')\r\n"
	    			+ "            FROM Horaires H \r\n"
	    			+ "            INNER JOIN horaires_restaurants HR ON H.id = HR.id_horaire \r\n"
	    			+ "            WHERE HR.id_restaurant = R.id \r\n"
	    			+ "            GROUP BY H.jour \r\n"
	    			+ "            ORDER BY\r\n"
	    			+ "                CASE\r\n"
	    			+ "                    WHEN H.jour = 'Lundi' THEN 1\r\n"
	    			+ "                    WHEN H.jour = 'Mardi' THEN 2\r\n"
	    			+ "                    WHEN H.jour = 'Mercredi' THEN 3\r\n"
	    			+ "                    WHEN H.jour = 'Jeudi' THEN 4\r\n"
	    			+ "                    WHEN H.jour = 'Vendredi' THEN 5\r\n"
	    			+ "                    WHEN H.jour = 'Samedi' THEN 6\r\n"
	    			+ "                    WHEN H.jour = 'Dimanche' THEN 7\r\n"
	    			+ "                END ASC\r\n"
	    			+ "            FOR XML PATH('')\r\n"
	    			+ "        ), 1, 2, '') AS horaires,\r\n"
	    			+ "    COUNT(T.id) AS NombreTables\r\n"
	    			+ "FROM\r\n"
	    			+ "    Restaurants R\r\n"
	    			+ "INNER JOIN\r\n"
	    			+ "    tableres T ON R.id = T.id_restaurant\r\n"
	    			+ "GROUP BY\r\n"
	    			+ "    R.id, R.nom, R.adresse, R.cpo, R.ville;\r\n");
	        ResultSet rs = ps.executeQuery();
	        
	        while (rs.next()) {
	            Restaurant restaurant = new Restaurant();
	            restaurant.setId(rs.getInt("id_restaurant"));
	            restaurant.setNom(rs.getString("nom"));
	            restaurant.setAdresse(rs.getString("adresse"));
	            restaurant.setCpo(rs.getString("cpo"));
	            restaurant.setVille(rs.getString("ville"));
	            String horairesConcat = rs.getString("horaires");
	            List<Horaire> horaires = parseHoraires(horairesConcat);
	      
	            restaurant.setHoraire(horaires);
	            TableRes table = new TableRes();
	            table.setId(rs.getInt("nombreTables"));
	            restaurant.setTableRes(table);
	            
	            restaurants.add(restaurant);
	        }

	    } catch (SQLException e) {
	        throw new DALException("Impossible de récupérer les infos", e);
	    }   
	    return restaurants;
	}


	

	private List<Horaire> parseHoraires(String horairesConcat) {
	    List<Horaire> horaires = new ArrayList<>();

	    if (horairesConcat != null && !horairesConcat.isEmpty()) {
	        String[] lines = horairesConcat.split("; ");

	        for (String line : lines) {
	            String[] parts = line.split(" : ");
	            if (parts.length == 2) {
	                String jourStr = parts[0].trim();
	                String horairesStr = parts[1].trim();

	                String[] horairesArray = horairesStr.split(", ");
	                for (String horaireStr : horairesArray) {
	                    String[] heures = horaireStr.split("-");
	                    if (heures.length == 2) {
	                        LocalTime heureOuverture = LocalTime.parse(heures[0].trim());
	                        LocalTime heureFermeture = LocalTime.parse(heures[1].trim());
	                        Horaire horaireObj = new Horaire(jourStr, heureOuverture, heureFermeture);
	                        horaires.add(horaireObj);
	                    }
	                }
	            }
	        }
	    }

	    return horaires;
	}












}
