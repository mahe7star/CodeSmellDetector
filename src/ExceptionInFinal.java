import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;
import javax.swing.JOptionPane;

public class ExceptionInFinal {
	static long lastposition = 00;
	static int line = 0;
	static Stack output = new Stack();
        
	static void findingBadSmell(File file, long lastpos, int line){
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
    //                    lastposition = (char)fis.getChannel().position();
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
                                if(s.matches("(.*)throw(.*)")){
                                    output.push("\nBad Smell - Exception thrown in finally block at : "+ (i+1) + " line\n");
                                }
                            }
                    }
		} catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) { e.printStackTrace(); }
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
            System.out.println(file.getName() + " not readable.");
            return;
        }
        
        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        while ((s = br.readLine()) != null) {
        	line++;
            if (s.matches("(.*)finally(.*)")) {            	
            	
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
                findingBadSmell(file,lastposition, line);
            }
        }
        JOptionPane.showMessageDialog(null, output);
    }
}
