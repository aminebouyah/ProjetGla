
public class Bar extends Location{

	private String name ; 
	
	public Bar (String name, String adresse) throws Exception{
		super(adresse) ; 
		this.name = name ; 
	}
	
	public String getName(){
		return this.name ; 
	}
	
	@Override
	public String toString() {
		String str = "Nom du bar : " + this.name + "  Adresse : " + this.getAdresse() ;
		return str ; 
	}
}
