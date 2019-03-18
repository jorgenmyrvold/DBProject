import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

public class Main {
	
	public Main() {
		System.out.println("Velkommen til treningsdagboka di!");
	}
	
	
	

	public static void regTrening() {
		Scanner reader = new Scanner(System.in);
		String tidspunkt = Helpers.getDatetime(reader);
		int varighet = Helpers.getVarighet(reader);
		int form = Helpers.getForm(reader);
		int prestasjon = Helpers.getEffort(reader);
		String notat = Helpers.getNote(reader);
		reader.close();
		
		
		RegTreningCtrl regTrening = new RegTreningCtrl();
//		regTrening.connect();
//		regTrening.startReg();
		regTrening.regTrening(tidspunkt, varighet, form, prestasjon, notat);
	}

// Registrer øvelse med med overgangstabell
//	public static void regOvelse() {
//		Scanner reader = new Scanner(System.in);
//		String ovelseNavn = Helpers.getOvelseNavn(reader);
//		String ovelseBeskrivelse = Helpers.getOvelseBeskrivelse(reader);
//		Boolean isApparat = Helpers.getIsApparat(reader);
//
//        RegOvelseCtrl regOvelse = new RegOvelseCtrl();
//		if (isApparat){
//		    String apparatNavn = Helpers.getApparatNavn(reader);
//              regOvelse.regApparatOvelse(ovelseNavn, ovelseBeskrivelse, apparatNavn);
//        }
//        else {
//            regOvelse.regFriOvelse(ovelseNavn, ovelseBeskrivelse);
//        }
//        reader.close();
//	}

// Registrer øvelse uten ekstra tabell, funker ikke heilt
    public static void regOvelse() {
        Scanner reader = new Scanner(System.in);
        String ovelseNavn = Helpers.getOvelseNavn(reader);
        String ovelseBeskrivelse = Helpers.getOvelseBeskrivelse(reader);
        Boolean isApparat = Helpers.getIsApparat(reader);

        RegOvelseCtrl regOvelse = new RegOvelseCtrl();
        if (isApparat){
            String apparatNavn = Helpers.getApparatNavn(reader);
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse, apparatNavn);
        }
        else {
            regOvelse.regOvelse(ovelseNavn, ovelseBeskrivelse);
        }
        reader.close();
    }

	public static void regApparat() {
	    Scanner reader = new Scanner(System.in);
	    String apparatNavn = Helpers.getApparatNavn(reader);
	    String apparatBeskrivelse = Helpers.getApparatBeskrivelse(reader);
        reader.close();

        RegApparatCtrl regApparatCtrl = new RegApparatCtrl();
        regApparatCtrl.regApparat(apparatNavn, apparatBeskrivelse);
    }

	
	public static void main(String[] args) {
		Main main = new Main();

		regOvelse();
//		regTrening();
//        regApparat();
		
	}	
}
