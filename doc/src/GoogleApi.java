 import java.net.*;
import java.util.*;
import org.json.*;
import org.json.*;

/*
 * Cette Classe sert à effectuer les différents calcules utilissant l'API Google
 */
public class GoogleApi {

	//Clé d'API Google
	
	//My new key  AIzaSyCLbBRt7BfbmUtR8V10sLdzr_K0v3yC_Sg 
	private static String key = "AIzaSyBu55F8MgTCWkubDfmvhNMwPazOfHZQRTQ";
     
	public String getKey() {
		return this.key ; 
	}
	
	//Calculer la latitude d'un lieu à partir de l'adresse
	/*
	 * @param adresse du lieu
	 * @return la latitude du lieu
	 */
	public double toLat(String adresse) throws Exception {
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+adresse.replaceAll(" ","+")+"&key="+this.getKey());
		Scanner scan = new Scanner(url.openStream());
		String html_output = new String();
		while (scan.hasNext())
			html_output += scan.nextLine();
		scan.close();
		// Construction l'objet JSON		
		JSONObject j = new JSONObject(html_output);
		JSONObject lieu = (j.getJSONArray("results")).getJSONObject (0);   
		return lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lat") ;	
	}
	
	
	//Calculer la latitude d'un lieu à partir de l'adresse
	/*
	 * @Param l'adresse du lieu
	 * @return la longitude du lieu
	 */
	public double toLon(String adresse) throws Exception {
		URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+adresse.replaceAll(" ","+")+"&key="+this.key);
		Scanner scan = new Scanner(url.openStream());
		String html_output = new String();
		while (scan.hasNext())
			html_output += scan.nextLine();
		scan.close();
		// Construction l'objet JSON		
		JSONObject j = new JSONObject(html_output);
		JSONObject lieu = (j.getJSONArray("results")).getJSONObject (0);   
		return lieu.getJSONObject("geometry").getJSONObject("location").getDouble("lng") ;	
	}
	
	//Calculer les bars approximités d'une adresse dans un rayon de 10 KM
	/*
	 * @param la localisation du lieu 
	 * @return La liste des bars approximités
	 */
	public ArrayList<Bar> getBars(Location l) throws JSONException, Exception {
		 URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+l.getLat()+","+l.getLon()+"&radius=10000&type=bar&key=" + this.key);
		 Scanner scan = new Scanner(url.openStream());
		 String html_output = new String();
		 while (scan.hasNext())
		     html_output += scan.nextLine();
		 scan.close();

		 // Construire l'objet JSON
		 // Toute la documentation de la bibliothèque org.json est disponible sur
		 // https://stleary.github.io/JSON-java/index.html
		 JSONObject j = new JSONObject(html_output);
		 ArrayList<Bar> res = new ArrayList<Bar>() ; 
		 
		 for (int i = 0 ; i < j.getJSONArray("results").length() ; i++){
		     JSONObject lieu = (j.getJSONArray("results")).getJSONObject (i);
		     Bar bar = new Bar(lieu.getString("name"),lieu.getString("vicinity") ) ;
		     res.add(bar) ;
		 }
		 return res ; 
	}
	
	//Calculer les restaurant approximités du bar dans un rayon de 10 KM en tenant compte des préférences alimentares spécifier par l'utilisateur
	/*
	 * @param la localisation du bar
	 * @return La liste des restaurants approximités
	 */
	public ArrayList<Resto> getResto(Bar bar, String pref) throws JSONException, Exception {
		 URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+bar.getLat()+","+bar.getLon()+"&radius=10000&type=restaurant&name="+pref+"&key=" + this.key);
		 Scanner scan = new Scanner(url.openStream());
		 String html_output = new String();
		 while (scan.hasNext())
		     html_output += scan.nextLine();
		 scan.close();

		 // Construire l'objet JSON
		 // Toute la documentation de la bibliothèque org.json est disponible sur
		 // https://stleary.github.io/JSON-java/index.html
		 JSONObject j = new JSONObject(html_output);
		 ArrayList<Resto> res = new ArrayList<Resto>() ; 
		 
		 for (int i = 0 ; i < j.getJSONArray("results").length() ; i++){
		     JSONObject lieu = (j.getJSONArray("results")).getJSONObject (i);
		     Resto resto = new Resto(lieu.getString("name"),lieu.getString("vicinity") ) ;
		     res.add(resto) ;
		 }
		 return res ; 
	}
	
	//Calculer les clubs de nuit approximités du restaurant dans un rayon de 10 KM
	/*
	 * @param la localisation du restaurant
	 * @return La liste des clubs de nuit approximités
	 */
	public ArrayList<Club> getClubs(Resto resto) throws JSONException, Exception {
		 URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location="+resto.getLat()+","+resto.getLon()+"&radius=10000&type=NightClub&key=" + this.key);
		 Scanner scan = new Scanner(url.openStream());
		 String html_output = new String();
		 while (scan.hasNext())
		     html_output += scan.nextLine();
		 scan.close();

		 // Construire l'objet JSON
		 // Toute la documentation de la bibliothèque org.json est disponible sur
		 // https://stleary.github.io/JSON-java/index.html
		 JSONObject j = new JSONObject(html_output);
		 ArrayList<Club> res = new ArrayList<Club>() ; 
		 
		 for (int i = 0 ; i < j.getJSONArray("results").length() ; i++){
		     JSONObject lieu = (j.getJSONArray("results")).getJSONObject (i);
		     Club club = new Club(lieu.getString("name"),lieu.getString("vicinity") ) ;
		     res.add(club) ;
		 }
		 return res ; 
	}

}
