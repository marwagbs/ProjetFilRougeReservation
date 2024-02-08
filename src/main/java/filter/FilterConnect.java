package filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FilterConnect
 */
public class FilterConnect extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		System.out.println("Passage dans le filtre");
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse resp= (HttpServletResponse) response;
		String path=req.getServletPath();
		List<String> listPath=new ArrayList<>();
		listPath.add("/cartes");
		listPath.add("/creationDuCompte");
		listPath.add("/connexion");
		listPath.add("/accueil");
		listPath.add("/nosRestaurants");
		listPath.add("/accueil");
		listPath.add("/WEB-INF/jsp/utilisateur/connexion.jsp");
		listPath.add("/WEB-INF/jsp/utilisateur/creationducompte.jsp");
		listPath.add("/WEB-INF/jsp/utilisateur/restaurants.jsp");
		listPath.add("/WEB-INF/jsp/cartes.jsp");
		listPath.add("/WEB-INF/jsp/accueil.jsp");
		listPath.add(".css");
		
		for(String current : listPath) {
			if (current.endsWith(".css") || current.equals(path)) {
				chain.doFilter(req, resp);
				return;
			}
			
		}
		 if (req.getSession().getAttribute("identifiant")!=null){
			chain.doFilter(req, resp);
		}
	    else {
			resp.sendRedirect(req.getContextPath() +"/connexion");
		}
	}

	
}
