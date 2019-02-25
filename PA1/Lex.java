/* Ashley Lavizadeh 
   alavizad
   pa1
*/   
import java.io.*;
import java.util.Scanner;


class Lex{
	public static void main(String[] args) throws IOException {

	//---Input/Output-----------------------------------------------------------------------------	
         Scanner in = null;
         PrintWriter out = null;
         String line = null; 
         int i = 0;

	
		if (args.length < 2){
			System.err.println("Usage: FileIO infile outfile"); 
		    System.exit(1);
		}
     
		in = new Scanner(new File(args[0]));
		out = new PrintWriter(new FileWriter(args[1]));

        
		while(in.hasNextLine()){
		    line = in.nextLine();
			i++;
		
		}
        in.close();
        Scanner str = new Scanner(new File(args[0]));
        String[] token = new String[i];
        int n = 0;
        
		while(str.hasNextLine()){
            token[n] = str.nextLine();
            n++;
		}
         
			
        
        
    //---List Creation-----------------------------------------------------------------------------
        

        List List = new List();


        if(List.length() == 0){
        	List.append(0);
        }
        
        for(int j = 1; j < token.length; j++ ){
        	//if token[j] is the least element so far
        	if(token[j].compareTo(token[List.front()]) <= 0){
        		List.prepend(j);
        	}
        	//if token[j] is the greatest element so far
        	else if(token[j].compareTo(token[List.back()]) >= 0){
        		List.append(j);
        	}else{
        		List.moveFront();
        		while(List.index() != -1){
        			if(token[j].compareTo(token[List.get()]) <= 0){
        				List.insertBefore(j);
        				break;
        			}else{
        				List.moveNext();
                    
        			}
        		}
        	}
        }
        
        //print array using list
        List.moveFront();
        while(List.index() != -1){
        	out.println(token[List.get()]);
        	List.moveNext();

        }

        str.close();
        out.close();
        
      		
	}
}