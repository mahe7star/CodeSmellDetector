package codesmelldetectors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class UnprotectedMain {

    public static void main(String[] args, String path) throws IOException {
        ArrayList<String> words = new ArrayList<String>();
        File file = new File(path);
        Scanner input = new Scanner(file);

        int count = 0;
        int flag = 0;
        while (input.hasNext()) {
            String word = input.next();
            if(word.contains("main")) {
                words.add(word);
                count = count + 1;
                break;
            }else{
                flag=1;
            }
            words.add(word);
            count = count + 1;
        }
        if(flag==1){
                Index.Output("\nMain method not found");
            }
        int mainIndex = words.indexOf("void");

        for (int counter = mainIndex-5; counter < mainIndex; counter++) {
            if(words.get(counter).contains("public") || words.get(counter).contains("private")){
                //JOptionPane.showMessageDialog(null, "\nCode Smell Found: Unprotected Main Method");
                Index.Output("\nCode Smell Found: Unprotected Main Method");
            }
        }

    }
}