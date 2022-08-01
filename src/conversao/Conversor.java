package conversao;

public class Conversor {
	
	  public static String convertStringToBinary(String input) {

	        StringBuilder result = new StringBuilder();
	        char[] chars = input.toCharArray();
	        for (char aChar : chars) {
	            result.append(
	                    String.format("%8s", Integer.toBinaryString(aChar))   // char -> int, auto-cast
	                            .replaceAll(" ", "0")                         // zero pads
	            );
	        }
	        return result.toString();

	    }

}
