package codesmelldetectors;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import javax.swing.JOptionPane;

public class DataClass {
    static Stack output= new Stack();
    
	 static long lastposition = 00;
	 static int noOfDataClass = 0;
	 static int line = 0;
	 static int classNumber = 0;
	 static int flag = 0;
        Stack gt = new Stack();

	 public static void countingLine(File file, long lastpos, int line) {
	    	classNumber++;
	    	int lineCount = 0;
	        int count = 0;
                
	        Stack st = new Stack();
	        st.push("Asd");
	        try {
	        	FileInputStream fis = new FileInputStream(file);
	    		BufferedReader br = new BufferedReader(new FileReader(file));
	            fis.skip(lastpos - 4);
	            char current;
	            do{
	            	current = (char) fis.read();
	            	if(current == '{') {
	                    st.push(current);
	                    count++;
	                }
	            	if(current == '}') {
	                    st.pop();
	                    count--;
	                    if(count == 0){
//	                        lastposition = (char)fis.getChannel().position();
	                        st.pop();
	                    }
	                }
	                if((current == '\n') && (st.peek() != "Asd")) {
	                    lineCount++;
	                }            
	            }while(!st.empty());
	            
	            for (int i = 0; i < lineCount + line; i++) {
	            	
	            	String s =  br.readLine();
	            	if((i+1) > line){
	            		if(s.matches("(.*)System.out(.*)") || s.matches("(.*)return(.*)")){
	            			flag = 1;  
                                        
	            		}
	            	}
	            	
	            }
	            if(flag == 0){
        		noOfDataClass++;
                        output.push("\nClass number : " + classNumber + " in the file\n");
            	}
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	 
	public static void main(String[] args, String path) throws IOException{
        String s;
        
        File file = new File(path);
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        if (!(file.isFile() && file.canRead())) {
            System.out.println(file.getName() + " cannot be read from.");
            return;
        }

        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        int f= 0;
        while ((s = br.readLine()) != null) {
        	line++;
            if (s.matches("(.*) class (.*)")) {   
            	
            	FileInputStream fis = new FileInputStream(file);
        		int numberOfLine = 0;
        		int i = 0;
            	while(numberOfLine < line ){
            		if(fis.read() == '\n'){
            			numberOfLine++;
            		}
            		i++;
            	}
            	lastposition = i;
            	countingLine(file,lastposition, line);
            }
        }
        output.push("\nNumber of Data Class Found= " + noOfDataClass+"\n");
        //JOptionPane.showMessageDialog(null, output);
        Index.Output(output.toString());
    }
}
