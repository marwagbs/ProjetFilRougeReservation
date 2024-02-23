package filter;

public class HtmlEscaper {
	 public static String escapeHtml(String input) {
	        if (input == null) return null;
	        return input.replaceAll("&", "&amp;")
	                    .replaceAll("<", "&lt;")
	                    .replaceAll(">", "&gt;")
	                    .replaceAll("\"", "&quot;")
	                    .replaceAll("'", "&#39;");
	    }
}
