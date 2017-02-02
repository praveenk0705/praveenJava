package fi.pku;

public class StrReverse {

	public static void main(String[] args) {
		

		String s ="Aditya Sharma";
		char[] source = s.toCharArray();
		
		System.out.println(s.length());
		
		
		/*String myString  = reverseIt("Praveen Kumar Ujjwal");
		System.out.println(myString);
		
		reverseStringByWords("Praveen Kumar Ujjwal");
		//System.out.println(Reverse("Praveen Kumar Ujjwal"));
		//strRev();
*/	}
	private static String Reverse(String str) {
	      char charArray[] = str.toCharArray();
	    for (int i = 0; i <str.length(); i++){
	        if(charArray[i] == ' ')
	        return Reverse(str.substring(i + 1)) + str.substring(0, i) + " ";
	    }

	    return str + " ";
	}

	public static String reverseIt(String str) {

	    char[] source = str.toCharArray();
	    char[] newArray = new char[source.length];

	    int i = 0, j = 0, idx = 0;

	    for(i = 0; i < source.length; i++) {
	        if(source[i] == ' ') {
	            for(int k = i-1; k >= j; k--, idx++) {
	                newArray[idx] = source[k];
	            }
	            j = i + 1;
	            newArray[idx++] = ' ';
	        }
	    }
	    for(int k = i-1; k >= j; k--, idx++) {
	        newArray[idx] = source[k];
	    }

	    return new String(newArray);
	}
	
	public static void strRev () {
		String str = "He is the one";
	    String temp = "";
	    String finalString = "";
	        for(int i =str.length()-1;i>=0;i--){
	        	
	            //temp +=i!=0?str.charAt(i):str.charAt(i)+" ";
	            if(i!= 0){
	            	temp+= str.charAt(i);
	            }
	            else
	            	temp+= str.charAt(i)+ " " ;
	            	
	            if(str.charAt(i) == ' '||i==0){                            
	            	for(int j=temp.length()-1;j>=0;j--){
	                    finalString += temp.charAt(j);
	                }
	                temp = "";
	            }
	        }
	            System.out.println(finalString);
	}
	public static void reverseStringByWords(String string) {
	    StringBuilder stringBuilder = new StringBuilder();
	    String[] words = string.split(" ");
	    for (int i = 0; i < words.length; i++) {
			//System.out.println(words[i]);
		}

	    for (int j = words.length-1; j >= 0; j--) {
	        stringBuilder.append(words[j]).append(' ');
	    }
	    System.out.println("Reverse words: " + stringBuilder);
	}


}
