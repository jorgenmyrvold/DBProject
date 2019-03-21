import java.io.*;
import java.awt.GraphicsEnvironment;
import java.net.URISyntaxException;

public class Run {
	
	private static String OS = System.getProperty("os.name").toLowerCase();
	
	public Run() {
		
	}
	
	public static void main (String [] args) throws IOException, InterruptedException, URISyntaxException{
        Console console = System.console();
        if (console == null && !GraphicsEnvironment.isHeadless()){
            String filename = Run.class.getProtectionDomain().getCodeSource().getLocation().toString().substring(6);
            if (OS.matches("^win.+")) {
            	Runtime.getRuntime().exec(new String[]{"cmd","/c","start","cmd","/k","java -jar \"" + filename + "\""});            	
            } else {
            	Runtime.getRuntime().exec(new String[]{"osascript", "-e", "tell app \"Terminal\" to do script \"java -jar \"" + filename + "\""});
            }
        } else {
            Main.main(new String[0]);
            System.out.println("Program has ended, please type 'exit' to close the console");
        }
		System.out.println(OS);
		if (OS.matches("^win.+")) {
        	System.out.println("yey");
        }
    }
}
