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
		regTrening.connect();
		regTrening.startReg();
		regTrening.regTrening(tidspunkt, varighet, form, prestasjon, notat);
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		
		regTrening();
		
	}	
}
