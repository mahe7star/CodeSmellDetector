package codesmelldetectors;

import java.io.*;
import java.util.Stack;
import javax.swing.JOptionPane;

public class comment{
	static int line = 0;
	static int flag = 0;
	public static void main(String[] args, String path) throws IOException{
        String s;
        Stack  lineNumberSingleComment= new Stack();
        Stack  lineNumberMultiComment= new Stack();
        Stack output = new Stack();
        
        File file = new File(path);
       
        if (!file.exists()) {
            JOptionPane.showMessageDialog(null, "File does not exist.");
            return;
        }
        if (!(file.isFile() && file.canRead())) {
            JOptionPane.showMessageDialog(null, file.getName() + " cannot be read from.");
            return;
        }
        
        FileReader freader = new FileReader(file);
        BufferedReader in = new BufferedReader(freader);
        while ((s = in.readLine()) != null) {
        	line++;
        	if (s.matches("(.*)//(.*)")) {            	
        		flag=1;
        		lineNumberSingleComment.push(line);
            }
        	if(s.matches("(.*)/\\*(.*)")){
        		flag=1;
        		lineNumberMultiComment.push(line);
        	}
        }
        
        if (flag==1) { 
        	if(lineNumberSingleComment.size() > 0){
                    output.push("\nSingle Line Comments Encountered at Line Numbers: " + lineNumberSingleComment +"\n");
        		//JOptionPane.showMessageDialog(null, "Single Line Comments Encountered at Line Numbers: "+ lineNumberSingleComment);
        	}
        	
        	if(lineNumberMultiComment.size() > 0){
                    output.push("\nMulti-Line Comments Encountered at Line Numbers: "+ lineNumberMultiComment+"\n");
        		//JOptionPane.showMessageDialog(null, "Multi-Line Comments Encountered at Line Numbers: "+ lineNumberMultiComment);
        	}
                
                if(output.size() > 0){
                    output.push("\n------------------------------------------------------------------\nIgnore in following cases: \n1. When explaining why something is being implemented in a particular way. \n2. When explaining complex algorithms \n (when all other methods for simplifying the algorithm have been tried and come up short).\n------------------------------------------------------------------\n");
                    Index.Output(output.toString());
                    //JOptionPane.showMessageDialog(null, output);
                }
//        	System.out.println("");
//        	System.out.println("*********************************************************************");
//        	System.out.println("");
//        	System.out.println("Ignore in following cases: \n1. When explaining why something is being implemented in a particular way. \n2. When explaining complex algorithms (when all other methods for simplifying the algorithm have been tried and come up short).");
//        	System.out.println("");
//        	System.out.println("*********************************************************************");
        }else{
        	Index.Output("No Comments Encountered");
                
        }
    }
}
