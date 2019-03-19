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
		regTrening.regTrening(tidspunkt, varighet, form, prestasjon, notat);
	}



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

    public static void getNSisteOvelser() {
		Scanner reader = new Scanner(System.in);
		int n = Helpers.getN(reader);
		reader.close();

		DBQuery getNSisteTreninger = new DBQuery();
		getNSisteTreninger.getNSisteTreningsokter(n);
	}
	
	public static void main(String[] args) {
		Main main = new Main();

//		regOvelse();
//		regTrening();
//        regApparat();
		getNSisteOvelser();
		
	}	
}
