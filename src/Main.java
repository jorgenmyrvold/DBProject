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
                "6) Hent personlig rekord for øvelse\n" +
                "7) Hent resultater for øvelse gitt tidsintervall\n"+
				"0) Avslutt\n");
		String ans = "";
		
		while (ans != "0") {
			System.out.println(menu);
			ans = reader.next();
			reader.nextLine();
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
			case "6":
				getPers(reader);
				break;
            case "7":
                getOvelseRes(reader);
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
	
	private static void regGjennomfortOvelse(Scanner reader) {
		String ovelseNavn = Helpers.getOvelseNavn(reader);
		DBQuery query = new DBQuery();
        if (!query.ovelseExisits(ovelseNavn)) {
        	regOvelse(reader, ovelseNavn);
        }
        String apparat = query.getApparat(ovelseNavn);
        RegGjennomfortOvelseCtrl ctrl = new RegGjennomfortOvelseCtrl();
        if (apparat == null) {
        	String beskrivelse = Helpers.getOvelseBeskrivelse(reader);
        	ctrl.regFriOvelse(ovelseNavn, beskrivelse);
        	
        } else {
        	int vekt = Helpers.getVekt(reader);
        	int antallSett = Helpers.getAntallSet(reader);
        	ctrl.regApparatOvelse(ovelseNavn, vekt, antallSett);
        }
	}

    public static void regOvelse(Scanner reader, String...strings) {
    	String ovelseNavn = strings.length == 1 ? strings[0] : Helpers.getOvelseNavn(reader);
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

		DBQuery query = new DBQuery();
		query.getNSisteTreningsokter(n);
	}

	public static void getOvelserIGruppe(Scanner reader) {
	    String gruppenavn = Helpers.getGruppeNavn(reader);

	    DBQuery query = new DBQuery();
	    query.getOvelserIGruppe(gruppenavn);
    }

    public static void getPers(Scanner reader) {
		String ovelseNavn = Helpers.getOvelseNavn(reader);

		DBQuery query = new DBQuery();
		query.getPers(ovelseNavn);
	}

	public static void getOvelseRes(Scanner reader) {
	    String ovelseNavn = Helpers.getOvelseNavn(reader);
        System.out.println("Se resultater fra dato (YYYY-MM-DD)");
	    String start_dato = Helpers.getDatoOvelseRes(reader);
        System.out.println("Se resultater til dato (YYYY-MM-DD)");
	    String slutt_dato = Helpers.getDatoOvelseRes(reader);

	    DBQuery query = new DBQuery();
	    query.getOvelseRes(ovelseNavn, start_dato, slutt_dato);
    }
	
	public static void main(String[] args) {
		run();
//		Scanner reader = new Scanner(System.in);
//		System.out.println(Helpers.getOvelseNavn(reader));
	}	
}
