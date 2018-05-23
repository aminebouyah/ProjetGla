
public class Club extends Location {

	private String name ; 
	public Club (String name, String adresse) throws Exception{
		super(adresse) ; 
		this.name = name ; 
	}
	
	//get
	public String getName(){
		return this.name ; 
	}
	
	@Override
	public String toString() {
		String str = "Nom du NightClub : " + this.name + "  Adresse : " + this.getAdresse() ; 
		return str ; 
	}
}
