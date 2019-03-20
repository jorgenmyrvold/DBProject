import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
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
		System.out.println("Hvordan var formen? (0-10)");
		while (true) {
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall mellom 0 og 10" + RESET);
		}
	}
	
	public static int getEffort(Scanner reader) {
		System.out.println("Hvordan presterte du? (0-10)");
		while (true) {
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall mellom 0 og 10" + RESET);
		}
	}
	
	public static String getNote(Scanner reader) {
		System.out.println("Har du en kommentar til treningen?");
		return reader.nextLine();
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

    public static String getApparatNavn(Scanner reader) {
        System.out.println("Hva er navnet på apparatet?");
        return reader.nextLine();
    }

    public static String getApparatBeskrivelse(Scanner reader) {
        System.out.println("Beskriv apparatet");
        return reader.nextLine();
    }

    public static String getGruppeNavn(Scanner reader) {
        System.out.println("Hva kalles treningsgruppen?");
        return reader.nextLine();
    }

    public static Boolean getIsApparat(Scanner reader) {
		System.out.println("Er det apparat eller fri øvelse (a/f)");
		String ans = reader.nextLine();
		while (true) {
			if (ans.equalsIgnoreCase("a")) {
				return true;
			} else if (ans.equalsIgnoreCase("f")) {
				return false;
			}
			System.out.println("Skriv inn gyldig input (a/f)");
			ans = reader.next();
		}
	}

    public static String getOvelseNavn(Scanner reader) {
        System.out.println("Hva er navnet på øvelsen?");
        if (reader.hasNextLine() ) {
        	return reader.nextLine();        	
        }
        return null;
    }

    public static String getOvelseBeskrivelse(Scanner reader) {
        System.out.println("Beskrivelse av øvelsen?");
        return reader.nextLine();
    }
    
    public static Iterator<String> getOvelseGrupper(Scanner reader) {
    	System.out.println("Hvilke(n) øvelsegruppe hører øvelsen til? \n" +
    			"Trykk enter mellom hver gruppe og avslutt med 'q'");
    	ArrayList<String> ovelseGrupper = new ArrayList<String>();
    	while (true) {
    		String gruppe = reader.nextLine();
    		if (gruppe.equals("q")) {
    			break;
    		}
    		ovelseGrupper.add(gruppe);
    	}
    	return ovelseGrupper.iterator();
    	
    }

    public static int getN(Scanner reader) {
		System.out.println("Hvor mange øvelser ønsker du å se resultater fra?");
		return reader.nextInt();
	}
    
    public static int getVekt(Scanner reader) {
    	System.out.println("Hvor mange kg?");
    	while (true) {
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall" + RESET);
		}
    }
    
    public static int getAntallSet(Scanner reader) {
    	System.out.println("Hvor mange set?");
    	while (true) {
			if (reader.hasNextInt()) {
				return reader.nextInt();
			}
			reader.next();
			System.out.println(RED + "Input må vere et heltall" + RESET);
		}
    }
}
