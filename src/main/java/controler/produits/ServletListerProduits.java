package controler.produits;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bll.BLLException;
import bll.ProduitBLL;
import bo.Produit;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ServletListerProduits extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProduitBLL produitBLL;
	
	@Override
	public void init() throws ServletException {
		try {
			produitBLL = new ProduitBLL();
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idStr =request.getParameter("id");
		if(idStr==null || idStr.isBlank()) {
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		}
	  int id = Integer.parseInt(idStr);
		try {
			List<Produit> listeProduits = produitBLL.selectALL();
			for (Produit current : listeProduits) {
				System.out.println(current);
			}
			
			List<Produit> listeEntrees = new ArrayList<>();
			List<Produit> listePlats = new ArrayList<>();
			List<Produit> listeDesserts = new ArrayList<>();
			List<Produit> listeBoissons = new ArrayList<>();
			
			for (Produit current : listeProduits) {
			if (current.getCarte().getRestaurant().getId()==id) {
				request.setAttribute("nomCarte", current.getCarte().getLibelle());
				request.setAttribute("nomRestaurant", current.getCarte().getRestaurant().getNom());
				System.out.println(current);
			    if (current.getCategorie().getId() == 1) {
			        listeEntrees.add(current);
			        
			        request.setAttribute("listeEntrees", listeEntrees);
			    } else if (current.getCategorie().getId() == 2) {
			    	
			        listePlats.add(current);
			        request.setAttribute("listePlats", listePlats);
			    } else if (current.getCategorie().getId() == 3) {
			    	
			        listeDesserts.add(current);
			    	request.setAttribute("listeDesserts", listeDesserts);
			    } else if (current.getCategorie().getId() == 4) {
			        listeBoissons.add(current);
			    	request.setAttribute("listeBoissons", listeBoissons);
			    }
			}
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	

		

		
		
	
	

		request.getRequestDispatcher("WEB-INF/jsp/cartes.jsp").forward(request, response);
	}


}
