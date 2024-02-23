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


public class FilterConnect extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;
	    private static final List<String> allowedPaths = new ArrayList<>();

	    static {
	        allowedPaths.add("/cartes");
	        allowedPaths.add("/creationDuCompte");
	        allowedPaths.add("/connexion");
	        allowedPaths.add("/accueil");
	        allowedPaths.add("/about");
	        allowedPaths.add("/nosRestaurants");
	        allowedPaths.add("/accueil");
	        allowedPaths.add("/WEB-INF/jsp/utilisateur/connexion.jsp");
	        allowedPaths.add("/WEB-INF/jsp/utilisateur/creationducompte.jsp");
	        allowedPaths.add("/WEB-INF/jsp/restaurants.jsp");
	        allowedPaths.add("/WEB-INF/jsp/cartes.jsp");
	        allowedPaths.add("/WEB-INF/jsp/accueil.jsp");
	        allowedPaths.add("/WEB-INF/jsp/autre/about.jsp");
	        allowedPaths.add(".css");
	        allowedPaths.add("css");
	        allowedPaths.add("assets");
	    }

	  
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	            throws IOException, ServletException {
	        HttpServletRequest req = (HttpServletRequest) request;
	        HttpServletResponse resp = (HttpServletResponse) response;

	        String path = req.getServletPath();
	        if (isAllowedPath(path) || isLoggedIn(req)) {
	            chain.doFilter(req, resp);
	            return;
	        }
	        
	     // Récupération des paramètres de la requête à échapper
	        String untrustedData = request.getParameter("identifiant"); // Remplacez "nomDuChamp" par le nom du champ que vous souhaitez échapper

	        // Échappement des caractères HTML spéciaux
	        String safeData = escapeHtml(untrustedData);

	      
	        req.getSession().setAttribute("identifiant", safeData);
	        
	        resp.sendRedirect(req.getContextPath() + "/connexion");
	    }

	    private boolean isAllowedPath(String path) {
	        return allowedPaths.stream().anyMatch(p -> path.endsWith(p) || path.contains(p));
	    }

	    private boolean isLoggedIn(HttpServletRequest request) {
	        return request.getSession().getAttribute("identifiant") != null;
	    }
	    
	    // Méthode pour échapper les caractères HTML spéciaux
	    private String escapeHtml(String input) {
	        return HtmlEscaper.escapeHtml(input);
	    }
}

