import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	
	public Main() {
		System.out.println("Velkommen til treningsdagboka di!");
	}

	public static void run() {
		Scanner reader = new Scanner(System.in);
		String menu = ("\nHva vil du gj�re? (0-?)\n" +
				"1) Registrer trening \n" +
				"2) Reigstrer Apparat\n" +
				"3) Registrer Øvelse\n" +
                "4) Se siste treningsøkter\n" +
                "5) Hent øvelser tilknyttet gruppe\n" +
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
                regApparat(reader);
                break;
			case "3":
				regOvelse(reader);
				break;
			case "4":
                getNSisteOvelser(reader);
                break;
            case "5":
                getOvelserIGruppe(reader);
                break;
			case "0":
				reader.close();
				return;
			default:
				System.out.println("Input må vere et tall mellom 0 og 5");
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
     
        
        //TODO: Sjekke om apparatet eksisterer i databasen og evt kj�re regApparat() f�rst

        RegOvelseCtrl regOvelse = new RegOvelseCtrl();
        if (isApparat){
            String apparatNavn = Helpers.getApparatNavn(reader);
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse, apparatNavn);
        }
        else {
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse);
        }
    }

	public static void regApparat(Scanner reader) {
	    String apparatNavn = Helpers.getApparatNavn(reader);
	    String apparatBeskrivelse = Helpers.getApparatBeskrivelse(reader);

        RegApparatCtrl regApparatCtrl = new RegApparatCtrl();
        regApparatCtrl.regApparat(apparatNavn, apparatBeskrivelse);
    }

    public static void getNSisteOvelser(Scanner reader) {
		int n = Helpers.getN(reader);

		DBQuery getNSisteTreninger = new DBQuery();
		getNSisteTreninger.getNSisteTreningsokter(n);
	}

	public static void getOvelserIGruppe(Scanner reader) {
	    String gruppenavn = Helpers.getGruppeNavn(reader);

	    DBQuery getOvelseIGruppe = new DBQuery();
	    getOvelseIGruppe.getOvelserIGruppe(gruppenavn);
    }
	
	public static void main(String[] args) {
		Main main = new Main();

		run();
		
	}	
}
