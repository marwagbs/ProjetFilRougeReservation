package dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import bo.Reservation;
import bo.Restaurant;
import bo.Utilisateur;

public class ReservationDAO implements GenericDAO<Reservation>{
	private static final String DELETE = "DELETE FROM reservations WHERE id = ?";
	private static final String UPDATE = "UPDATE reservations SET date_res = ?, heure = ?, nb_personne = ?, id_utilisateur = ?,id_restaurant = ?, statut = ?, commentaire = ?   WHERE id = ?";
	private static final String UPDATE_STATUT_RES ="UPDATE reservations SET statut = ? WHERE id = ?";
	private static final String INSERT = "INSERT INTO reservations (date_res, heure, nb_personne, id_utilisateur, id_restaurant, statut, commentaire) VALUES (?, ?, ?, ?, ?, ?, ?)";
	private static final String SELECT_BY_ID = "SELECT * FROM reservations WHERE id = ?";
	private static final String SELECT = "SELECT * FROM reservations";
	
	private Connection cnx;
	private Context context;
	
	
	public ReservationDAO(Connection cnx, Context context) {
		super();
		this.cnx = cnx;
		this.context = context;
	}
	public ReservationDAO () throws DALException {
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
	//---------------------------------------------------//
	
	public List<Reservation> selectAll() throws DALException {
  List<Reservation> reservations = new ArrayList<>();
  try {
		PreparedStatement ps = cnx.prepareStatement(SELECT);
		ResultSet rs = ps.executeQuery();	while (rs.next()) {
		Reservation reservation = new Reservation();
		reservation.setId(rs.getInt("id"));
		reservation.setDateRes(LocalDate.parse(rs.getString("date_res"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
		reservation.setHeure(LocalTime.parse(rs.getString("heure"), DateTimeFormatter.ofPattern("HH:mm")));
		reservation.setNbrPersonnes(rs.getInt("nb_personne"));
		reservation.setCommentaire(rs.getString("commentaire"));
		reservation.setStatut(rs.getString("statut"));
		reservation.getRestaurant().setId(rs.getInt("id_restaurant"));
		reservation.getUtilisateur().setId(rs.getInt("id_utilisateur"));
		
		reservations.add(reservation);
		}
		} catch (SQLException e) {
			throw new DALException("Impossible de récuperer les infos", e);
		}	
		return reservations;
		
	}
	//------------------------------------------------------------------//
	public Reservation selectById(int id) throws DALException {
		Reservation reservation = null;
		try {
			PreparedStatement ps = cnx.prepareStatement(SELECT_BY_ID);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				reservation = new Reservation();
				Utilisateur utilisateur = new Utilisateur();
				Restaurant restaurant = new Restaurant();
				
				reservation.setId(rs.getInt("id"));
				reservation.setDateRes(LocalDate.parse(rs.getString("date_res"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				reservation.setHeure(LocalTime.parse(rs.getString("heure"), DateTimeFormatter.ofPattern("HH:mm")));
				reservation.setNbrPersonnes(rs.getInt("nb_personne"));
				reservation.setCommentaire(rs.getString("commentaire"));
				reservation.setStatut(rs.getString("statut"));
				restaurant.setId(rs.getInt("id_restaurant"));
				utilisateur.setId(rs.getInt("id_utilisateur"));
				reservation.setUtilisateur(utilisateur);
				reservation.setRestaurant(restaurant);	
			}
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour les infos pour l'id " + id, e);
		}
		return reservation;
	}
//-----------------------------------------------------------//
	public void insert(Reservation reservation) throws DALException {
		PreparedStatement ps;
		try {
			ps = cnx.prepareStatement(INSERT, PreparedStatement.RETURN_GENERATED_KEYS);
			
			ps.setDate(1, Date.valueOf(reservation.getDateRes()));
			ps.setTime(2, Time.valueOf(reservation.getHeure()));
		    ps.setInt(3, reservation.getNbrPersonnes());
		    ps.setInt(4, reservation.getUtilisateur().getId());
		    ps.setInt(5, reservation.getRestaurant().getId());
		    ps.setString(6, reservation.getStatut());
		    ps.setString(7, reservation.getCommentaire());
		
		    
		
			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				int id = rs.getInt(1);
				reservation.setId(id);
			}
		} catch (SQLException e) {
			throw new DALException("Impossible d'inserer les données.", e);
		}
	}
	

//---------------------------------------------------------------------//	
	public void update(Reservation reservation) throws DALException {
	try {
		PreparedStatement ps = cnx.prepareStatement(UPDATE);
		ps.setDate(1, java.sql.Date.valueOf(reservation.getDateRes()));
		ps.setString(2, reservation.getHeure().format(DateTimeFormatter.ofPattern("HH:mm")));
	    ps.setInt(3, reservation.getNbrPersonnes());
	    ps.setInt(4, reservation.getUtilisateur().getId());
	    ps.setInt(5, reservation.getRestaurant().getId());
	    ps.setString(6, reservation.getStatut());
	    ps.setString(7, reservation.getCommentaire());
	    ps.setInt(8, reservation.getId());
		ps.executeUpdate();
	} catch (SQLException e) {
		throw new DALException("Impossible de mettre à jour les infos pour l'id " + reservation.getId(), e);
	}		
}

	
//-----------------------------------------------------------------------//
	public void updateStatutRes(Reservation reservation) throws DALException {
		try {
			PreparedStatement ps = cnx.prepareStatement(UPDATE_STATUT_RES);
			  ps.setString(1, reservation.getStatut());
			  ps.setInt(2, reservation.getId());
				ps.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Impossible de mettre à jour le statut  pour l'id " + reservation.getId(), e);
		}		
	}
	
//--------------------------------------------------------------------//
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


}
