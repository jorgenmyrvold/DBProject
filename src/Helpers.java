import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Scanner;

public class Helpers {
	
	public static final String RED = "\u001B[31;1m";
	public static final String RESET = "\u001B[0m";
	
	public static String getDatetime(Scanner reader) {
		String dato = getDato(reader);
		
		while (true) {
			System.out.println("Når begynte treningen? (HH:MM)");
			String ans = reader.next();
			
			if (ans.matches("\\d+:\\d+")) {
				String[] check = ans.split(":");
				int hour = Integer.parseInt(check[0]);
				int minutes = Integer.parseInt(check[1]);
				if (hour >= 0 && hour < 24 && minutes >= 0 && minutes < 60) {
					return dato + " " + ans;
				}
			}
			System.out.println(RED + "Ugyldig tidspunkt!" + RESET);
		}
	}
	
	public static int getVarighet(Scanner reader) {
		
		while (true) {
			System.out.println("Hvor lenge varte treningen? (minutter)");
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et helt antall minutter" + RESET);
		}
	}
	
	public static int getForm(Scanner reader) {
		while (true) {
			System.out.println("Hvordan var formen? (0-10)");
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall mellom 0 og 10" + RESET);
		}
	}
	
	public static int getEffort(Scanner reader) {
		while (true) {
			System.out.println("Hvordan presterte du? (0-10)");
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall mellom 0 og 10" + RESET);
		}
	}
	
	public static String getNote(Scanner reader) {
		System.out.println("Har du en kommentar til treningen?");
		return reader.next();
	}
	
	private static String getDato(Scanner reader) {
		String dato = "";
		do {
			System.out.println("Var treningen i dag? (y/n)");
			String ans = reader.next();
			
			if (ans.equalsIgnoreCase("y")) {
				dato = LocalDate.now().toString();
			} else if (ans.equalsIgnoreCase("n")){
				while (true) {
					System.out.println("Vennligs oppgi dato: (YYYY-MM-DD)");
					ans = reader.next();
//					ans.matches("\\d{4}-\\d{2}-\\d{2}") && 
					if (isDateValid(ans)) {
						dato = ans;
						break;
					}
					System.out.println(RED + "Ugyldig dato" + RESET);
				}
			}
		} while (dato.isEmpty());
		return dato;
	}
	
	private static boolean isDateValid(String date) 
	{
	        try {
	            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}
}
