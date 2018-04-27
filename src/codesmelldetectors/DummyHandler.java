package codesmelldetectors;

import java.io.*;
import java.util.Stack;
import javax.swing.JOptionPane;

public class DummyHandler {
   static long lastposition = 00;
   static int noOfDummyHandler = 0;
   static int line = 0;
   static int catchNumber = 0;
    public static void countingLine(File file, long lastpos, int line ) {
    	catchNumber++;
    	int lineCount = 0;
        int count = 0;
//        System.out.println("function");
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
//                        lastposition = (char)fis.getChannel().position();
                        st.pop();
                    }
                }
                if((current == '\n') && (st.peek() != "Asd")) {
                    lineCount++;
                }            
            }while(!st.empty());
            if(lineCount ==2){
            	System.out.println("Dummy Handler found");
            	System.out.println("Catch number : " + catchNumber + " in the file");
            	noOfDummyHandler++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args, String path) throws IOException{
        String s;
        int classNo=0;
        
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
        while ((s = br.readLine()) != null) {
        	line++;
            if (s.matches("(.*)catch(.*)")) {            	
            	
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
        //JOptionPane.showMessageDialog(null, "Total number of Dummy Handler blocks = " + noOfDummyHandler);
        Index.Output("Total number of Dummy Handler blocks = " + noOfDummyHandler);
    }
}