import java.util.*;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;



public class Main extends Exception{
	
	private static final int Max = 1000;

	//****************Renvoie l'intersection de deux ArrayList de Bar**************************
	public static ArrayList<Bar> intersection2List(ArrayList<Bar> listBar1, ArrayList<Bar> listBar2){
		ArrayList<Bar> res = new ArrayList<Bar>() ; 
		for(Bar bar : listBar1){
			for(Bar bar2 : listBar2){
				if(bar.getName().equals(bar2.getName())){
					res.add(bar) ; break ; 
				}
			}
		}
		return res ; 
	}
	

	{
		
	}
	//****************Renvoie l'intersection de 3 ArrayList de Bar************************
	public static ArrayList<Bar> intersection3List (ArrayList<Bar> listBar1, ArrayList<Bar> listBar2, ArrayList<Bar> listBar3){
		return intersection2List(intersection2List(listBar1,listBar2),listBar3) ; 
	}
	
	public static String ajoutparticipant() throws IOException,ExistedejaException{
		Scanner sc = new Scanner(System.in);
		System.out.println("Veuillez saisir le nom :");
		String nom = sc.nextLine();
		System.out.println("Vous avez saisir le prenom : " );
		String prenom = sc.nextLine();
		System.out.println("Vous avez saisir l'adresse : ");
		String adresse = sc.nextLine();
		System.out.println("Vous avez saisir le mail : ");
		String email = sc.nextLine();
		//partie de verification
		// on verifie le 4em champs dans le fichier text
		String filePath = "test.txt";
		InputStream ips = new FileInputStream(filePath);
		InputStreamReader ipsr= new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;
		String [] res = new String[Max];
		String[] str= new String [Max];
		int i=0;
		boolean boul = false;
		while((ligne=br.readLine())!=null && !boul){
		  
		
		str=ligne.split(",");
		if (i>=1)
	      if(str[3].equals(email)) boul=true;
		i++;
		}
		if(boul) System.out.println("l'adresse mail existe deja");
		else  {
		 FileWriter fileWriter = new FileWriter("test.txt", true);
         BufferedWriter bufWriter = new BufferedWriter(fileWriter);
         //Ins�rer un saut de ligne
       bufWriter.newLine();
         bufWriter.write(nom+","+prenom+","+adresse+","+email);
         bufWriter.close();
         }
	
if (boul)
		return "existe";
else return adresse;

	      
	
		
		
		
	}
	
	//****************Renvoie l'intersection de deux ArrayList de Resto**************************
	public static ArrayList<Resto> intersection2ListR(ArrayList<Resto> listResto1, ArrayList<Resto> listResto2){
		ArrayList<Resto> res = new ArrayList<Resto>() ; 
		for(Resto resto : listResto1){
			for(Resto resto2 : listResto2){
				if(resto.getName().equals(resto2.getName())){
					res.add(resto) ; break ; 
				}
			}
		}
		return res ; 
	}
	
	//***************Renvoie l'intersection de 3 ArrayList de Resto*************************
	public static ArrayList<Resto> intersection3ListR (ArrayList<Resto> listResto1, ArrayList<Resto> listResto2, ArrayList<Resto> listResto3){
		return intersection2ListR(intersection2ListR(listResto1,listResto2),listResto3) ; 
	}

	//******Calcule de Bar le Plus proche à partir des adresses des 3 utilisateurs***
	/*
	 * @param tableau contenant les adresses des utilisateurs
	 * @param liste contenant l'intersection des bars entre les 3 adresses
	 * @return le bar le plus proches des 3 adresses
	 */
	
	public static Bar barLePlusProche(Location[] users, ArrayList<Bar> listBar){
		Bar res = listBar.get(0) ; 
		double min = users[0].distance(res) + users[1].distance(res) + users[2].distance(res) ;
		for(int i = 1 ; i<listBar.size() ; i++){
			double dist = users[0].distance(listBar.get(i)) + users[1].distance(listBar.get(i)) + users[2].distance(listBar.get(i)) ;
			if (dist <min){
				min = dist ; 
				res = listBar.get(i) ;
			}
		}
		return res ; 
	} 
	public static Bar barLePlusProche2(Location[] users, ArrayList<Bar> listBar){
		Bar res = listBar.get(0) ; 
		double min = users[0].distance(res) + users[1].distance(res) ;
		for(int i = 1 ; i<listBar.size() ; i++){
			double dist = users[0].distance(listBar.get(i)) + users[1].distance(listBar.get(i))  ;
			if (dist <min){
				min = dist ; 
				res = listBar.get(i) ;
			}
		}
		return res ; 
	} 
	public static Bar barleplusproche(Location user,ArrayList<Bar>listBar){
		
		Bar res =listBar.get(0);
		double min=user.distance(res);
		for(int i = 1 ; i<listBar.size() ; i++){
			double dist = user.distance(listBar.get(i));
			if (dist <min){
				min = dist ; 
				res = listBar.get(i) ;
			}
			}
		return res;
		
	}
	
	//******Calcule le restaurant le Plus proche à partir du bar selectionné et en respectant les préférences***
	/*
	 * @param le bar selectionné
	 * @param tableau des préférences
	 * @return le restaurant le plus proche du bar
	 */
	public static Resto restoLePlusProche(Bar bar, String[] preference) throws JSONException, Exception {
		ArrayList<Resto> resto1 = bar.getResto(bar, preference[0]) ; 
		ArrayList<Resto> resto2 = bar.getResto(bar, preference[1]) ;
		ArrayList<Resto> resto3 = bar.getResto(bar, preference[2]) ;
		ArrayList<Resto> restos = intersection3ListR(resto1,resto2,resto3) ; 
		if(restos.size()==0){
			return null ; 
		}
		double min = bar.distance(restos.get(0)) ;
		Resto res = restos.get(0) ; 
		for(int i = 1 ; i<restos.size() ; i++){
			double dist = bar.distance(restos.get(i)) ; 
			if(dist<min){
				min = dist ; 
				res = restos.get(i) ; 
			}
		}
		return res ; 
	}
public static  Resto Restoleplusproche (Bar b,String[] preference) throws JSONException, Exception{
		ArrayList<Resto> resto=b.getResto(b,preference[0]);
		Resto res =resto.get(0);
		double min=b.distance(res);
		for(int i = 1 ; i<resto.size() ; i++){
			double dist = b.distance(resto.get(i));
			if (dist <min){
				min = dist ; 
				res = resto.get(i) ;
			}
			}
		return res;
		
	}
public static  Resto Restoleplusproche2 (Bar b,ArrayList<Resto> r) throws JSONException, Exception{
	Resto res =r.get(0);
	double min=b.distance(res);
	for(int i = 1 ; i<r.size() ; i++){
		double dist = b.distance(r.get(i));
		if (dist <min){
			min = dist ; 
			res = r.get(i) ;
		}
		}
	return res;
	
}
	
	//******Calcule le club de nuit le Plus proche du restaurant selectionné***
	/*
	 * @param restaurant selectionné
	 * @param liste des club de nuits les plus proches du restaurant
	 * @return le club de nuit le plus proche
	 */
	public static Club clubLePlusProche(Resto resto, ArrayList<Club> listClub){
		if(listClub.size()==0){
			return null ;
		}
		double min = resto.distance(listClub.get(0)) ; 
		Club res = listClub.get(0) ; 
		for(int i = 1 ; i<listClub.size() ; i++){
			double dist = resto.distance(listClub.get(i)) ;
			if(dist<min){
				min = dist ; 
				res = listClub.get(i) ; 
			}
		}
		return res ; 
	}
	public static Club clubLePlusProche1(Resto resto, ArrayList<Club> listClub){
		if(listClub.size()==0){
			return null ;
		}
		double min = resto.distance(listClub.get(0)) ; 
		Club res = listClub.get(0) ; 
		for(int i = 1 ; i<listClub.size() ; i++){
			double dist = resto.distance(listClub.get(i)) ;
			if(dist<min){
				min = dist ; 
				res = listClub.get(i) ; 
			}
		}
		return res ; 
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println("entrez le nombre de participants):");
		Scanner sc = new Scanner(System.in);
		Scanner scc = new Scanner(System.in);
		int nb=sc.nextInt();
		int i=0;
		String pref[]= new String[nb];
		String res[] = new String [nb];
		while (i<nb )
		    {
			String ad=ajoutparticipant();
			if (ad!="existe"){
			res[i]=ad;
			System.out.print("\n entrer la preference ");
			pref[i]= scc.nextLine();
			i++;
			}
			else nb--;
			
		}
		Location[] users = new Location[nb] ;
		String[] preference = new String[nb] ;
		ArrayList<ArrayList<Resto>>res3=new ArrayList<ArrayList<Resto>>();
		ArrayList<ArrayList<Bar>> res2= new ArrayList<ArrayList<Bar>>();
		int j=0;
		while (j<nb){
			users[j]= new Location(res[j]);
			res2.add(users[j].getBars());
			j++;
		}
		j=0;

		//affichage des bars propos�s
	/*	while (j<nb)
		{
			for (Bar bar: res2.get(j))
			{
				System.out.println(bar.toString()) ;
			}
			j++;
			
		}*/
	 String barc="";
	 String resto="";
	 String club="";
	 
		if (nb<=0) System.out.println("un peu de serieux");
		else if( nb==1) {barc=(barleplusproche(users[0],res2.get(0))).toString();
		resto=(Restoleplusproche(barleplusproche(users[0],res2.get(0)),pref)).toString();
		ArrayList<Club> c= (Restoleplusproche(barleplusproche(users[0],res2.get(0)),pref)).getClubs((Restoleplusproche(barleplusproche(users[0],res2.get(0)),pref)));
		club=(clubLePlusProche1((Restoleplusproche(barleplusproche(users[0],res2.get(0)),pref)),c)).toString();
		}
		else if(nb==2){
			ArrayList<Bar> b=intersection2List(res2.get(0),res2.get(1));
			Bar barpr=barLePlusProche2(users,b);
			barc=(barpr).toString();
			
			
			ArrayList<Resto> r1= barpr.getResto(barpr, pref[0]);
			ArrayList<Resto> r2= barpr.getResto(barpr, pref[1]);
			ArrayList<Resto> restores= new ArrayList<Resto>();
			if ( pref[0]!=pref[1]){ Random rand = new Random(); int nombreAleatoire = rand.nextInt(2) ;
			if (nombreAleatoire==0) { restores=r1;    }
			else if(nombreAleatoire==1) { restores=r2;    }
			
			}
			else  restores=intersection2ListR(r1,r2);
			
			Resto resupr=Restoleplusproche2(barpr,restores);
			
			resto=resupr.toString();
			club =clubLePlusProche(resupr,resupr.getClubs(resupr)).toString();
		
			
		}
		else if (nb==3){
			ArrayList<Bar> br = intersection3List(res2.get(0),res2.get(1),res2.get(2));
			Bar barpr= barLePlusProche(users,br);
			barc= barpr.toString();
			ArrayList<Resto> r1= barpr.getResto(barpr, pref[0]);
			ArrayList<Resto> r2= barpr.getResto(barpr, pref[1]);
			ArrayList<Resto> r3= barpr.getResto(barpr, pref[2]);
			ArrayList<Resto> restores= new ArrayList<Resto>();
			if (pref[0]==pref[1]) restores=r1;
			else if (pref[1]==pref[2]) restores=r2;
			else if (pref[0]==pref[2]) restores=r1;
			else {Random rand = new Random(); int nombreAleatoire = rand.nextInt(3);
			if (nombreAleatoire==0) { restores=r1;    }
			else if(nombreAleatoire==1) { restores=r2;    }
			else if (nombreAleatoire==2) { restores=r3;    }
				
				
			}
			
Resto resupr=Restoleplusproche2(barpr,restores);
			
			resto=resupr.toString();
			club =clubLePlusProche(resupr,resupr.getClubs(resupr)).toString();
			

			}	
		System.out.println(barc);
		System.out.println(resto);
		System.out.println(club);
		
	
	
	}

	private static String clubleplusproche1() {
		// TODO Auto-generated method stub
		return null;
	}

	private static Object getClub(Resto resto) {
		// TODO Auto-generated method stub
		return null;
	} 
}
