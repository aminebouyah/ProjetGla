import java.util.ArrayList;
import org.json.JSONException;


public class Location {
	
	private GoogleApi gapi ; //sert à utiliser les fonctions de calcule implémenter dans GoogleAPI 
	private String adresse ; // Adresse du lieu
	private double lat ; // Latitude du lieu
	private double lon ;  // Longitude du lieu
	
	public Location (String adresse) throws Exception {
		this.gapi = new GoogleApi() ; 
		this.adresse = adresse ;
		this.lat = gapi.toLat(this.adresse) ; 
		this.lon = gapi.toLon(this.adresse) ; 
	}
	
	public String getAdresse() {
		return this.adresse;
	}
	
	public double getLat() {
		return this.lat ; 
	}
	
	public double getLon() {
		return this.lon ; 
	}
	
	//voir la méthode dans GoogleApi
	public ArrayList<Bar> getBars() throws JSONException, Exception {
		return this.gapi.getBars(this) ; 
	}
	
	//voir la méthode dans GoogleApi
	public ArrayList<Resto> getResto(Bar bar, String pref) throws JSONException, Exception {
		return this.gapi.getResto(bar, pref) ; 
	}
	
	//voir la méthode dans GoogleApi
	public ArrayList<Club> getClubs(Resto resto) throws JSONException, Exception {
		return this.gapi.getClubs(resto);
	}
	
//********Convertir les degrés en radians**********************	
	public double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

//********Conevrtir les radians en degrés
	public double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
//******************************************
	
	//Calcule la distance en KM entre deux lieu en utilisant la latitude et la longitude
	public double distance(Location l) {
		double theta = this.lon - l.getLon();
		double dist = Math.sin(deg2rad(this.lat)) * Math.sin(deg2rad(l.getLat())) + Math.cos(deg2rad(this.lat)) * Math.cos(deg2rad(l.getLat())) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515 * 1.609344 ; 

		return dist;
	}
}
