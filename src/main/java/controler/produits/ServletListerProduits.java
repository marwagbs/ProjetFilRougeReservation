package controler.produits;

import java.io.IOException;
import java.util.List;

import bll.BLLException;
import bll.ProduitBLL;
import bll.RestaurantBLL;
import bo.Produit;
import bo.Restaurant;
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
		try {
			
			List<Produit> produits = produitBLL.selectALL();
			request.setAttribute("listeDeProduit", produits);
		} catch (BLLException e) {
			e.printStackTrace();
		}
	}

}
