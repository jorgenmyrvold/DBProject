import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

public class Main {
	
	public Main() {
		System.out.println("Velkommen til treningsdagboka di!");
	}

	public static void run() {
		Scanner reader = new Scanner(System.in);
		String menu = ("\nHva vil du gjøre? (0-?)\n" +
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
		reader.nextLine();
		String notat = Helpers.getNote(reader);
		
		
		RegTreningCtrl regTrening = new RegTreningCtrl();
		regTrening.regTrening(tidspunkt, varighet, form, prestasjon, notat);
		
		while (true) {
			System.out.println("Har du gjennomført flere øvelser? (y/n)");
			String ans = reader.next();
			reader.nextLine();
			if (ans.equalsIgnoreCase("n")) {
				break;
			} else if (ans.equalsIgnoreCase("y")) {
				regGjennomfortOvelse(reader);				
			} else {
				System.out.println("Du må svare y eller n");
			}
		}
		
	}
	
	private static void regGjennomfortOvelse(Scanner reader) {
		String ovelseNavn = Helpers.getOvelseNavn(reader);
		DBQuery query = new DBQuery();
        if (!query.ovelseExisits(ovelseNavn)) {
        	System.out.println("Dette er en ny øvelse, så vennligst si litt mer om den\n");
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
        
        RegOvelseCtrl regOvelse = new RegOvelseCtrl();
        if (isApparat){
            String apparatNavn = Helpers.getApparatNavn(reader);
            DBQuery query = new DBQuery();
            if (query.getApparat(apparatNavn) == null) {
            	System.out.println("Dette er et nytt apparat. Vennligst si litt mer om det\n");
            	regApparat(reader, apparatNavn);
            }
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse, apparatNavn);
        }
        else {
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse);
        }
        regOvelseIGruppe(reader, ovelseNavn);
    }
    
    public static void regOvelseIGruppe(Scanner reader, String ovelseNavn) {
    	Iterator<String> ovelseGrupper = Helpers.getOvelseGrupper(reader);
    	
    	RegOvelseIGruppeCtrl ctrl = new RegOvelseIGruppeCtrl();
    	while(ovelseGrupper.hasNext()) {
    		String gruppe = ovelseGrupper.next();
    		if (gruppe.length() > 0) {
    			ctrl.regOvelse(ovelseNavn, gruppe);    			
    		}
    	}
    }

	public static void regApparat(Scanner reader, String...strings) {
	    String apparatNavn = strings.length > 0 ? strings [0] : Helpers.getApparatNavn(reader);
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

	    DBQuery query = new DBQuery();
	    query.getOvelserIGruppe(gruppenavn);
    }
	
	public static void main(String[] args) {
		run();
//		Scanner reader = new Scanner(System.in);
//		System.out.println(Helpers.getOvelseNavn(reader));
	}	
}
