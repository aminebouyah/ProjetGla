
public class Resto extends Location {

	private String name ; 
	public Resto (String name, String adresse) throws Exception{
		super(adresse) ; 
		this.name = name ; 
	}
	
	public String getName(){
		return this.name ; 
	}
	
	@Override
	public String toString() {
		String str = "Nom du Resto : " + this.name + "  Adresse : " + this.getAdresse(); 
		return str ; 
	}
}
