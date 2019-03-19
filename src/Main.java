import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public Main() {
		System.out.println("Velkommen til treningsdagboka di!");
	}
	
	
	public static void run() {
		Scanner reader = new Scanner(System.in);
		String menu = ("Hva vil du gjï¿½re? (0-?)\n" +
				"1) Registrer trening \n" +
				"2) Se siste treningsï¿½kter\n" +
				"3) ???\n" +
				"0) Avslutt\n");
		String ans = "";
		
		while (ans != "0") {
			System.out.println(menu);
			ans = reader.next();
			switch(ans) {
			case "1":
				regTrening(reader);
				break;
			case "2":
				System.out.println("Henter ut liste over treningsï¿½kter");
				break;
			case "3":
				System.out.println("Ett eller anna");
				regOvelse(reader);
				break;
			case "0":
				reader.close();
				return;
			default:
				System.out.println("Input mï¿½ vere et tall mellom 0 og 3");
			}
		}
	}

	public static void regTrening(Scanner reader) {
		String tidspunkt = Helpers.getDatetime(reader);
		int varighet = Helpers.getVarighet(reader);
		int form = Helpers.getForm(reader);
		int prestasjon = Helpers.getEffort(reader);
		String notat = Helpers.getNote(reader);
		
		
		RegTreningCtrl regTrening = new RegTreningCtrl();
		regTrening.regTrening(tidspunkt, varighet, form, prestasjon, notat);
	}

    public static void regOvelse(Scanner reader) {
        String ovelseNavn = Helpers.getOvelseNavn(reader);
        String ovelseBeskrivelse = Helpers.getOvelseBeskrivelse(reader);
        Boolean isApparat = Helpers.getIsApparat(reader);
        ArrayList<String> ovelseGrupper = Helpers.getOvelseGrupper(reader);
     
        
        //TODO: Sjekke om apparatet eksisterer i databasen og evt kjøre regApparat() først

        RegOvelseCtrl regOvelse = new RegOvelseCtrl();
        if (isApparat){
            String apparatNavn = Helpers.getApparatNavn(reader);
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse, apparatNavn);
        }
        else {
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse);
        }
    }

	public static void regApparat() {
	    Scanner reader = new Scanner(System.in);
	    String apparatNavn = Helpers.getApparatNavn(reader);
	    String apparatBeskrivelse = Helpers.getApparatBeskrivelse(reader);
        reader.close();

        RegApparatCtrl regApparatCtrl = new RegApparatCtrl();
        regApparatCtrl.regApparat(apparatNavn, apparatBeskrivelse);
    }

    public static void getNSisteOvelser() {
		Scanner reader = new Scanner(System.in);
		int n = Helpers.getN(reader);
		reader.close();

		DBQuery getNSisteTreninger = new DBQuery();
		getNSisteTreninger.getNSisteTreningsokter(n);
	}
	
	public static void main(String[] args) {
		Main main = new Main();

		run();
//		regOvelse();
//		regTrening();
//        regApparat();
		getNSisteOvelser();
		
	}	
}
