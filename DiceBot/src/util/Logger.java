package util;

public class Logger {
	private Logger()  {};
	
	public static void log(String s) {
		System.out.println(s);
	}

	public static void debug(String string) {
		System.out.println("DEBUG: " + string);
		
	}
	
	public static void warning(String s){
		System.out.println("WARNING: " + s);
	}
}
