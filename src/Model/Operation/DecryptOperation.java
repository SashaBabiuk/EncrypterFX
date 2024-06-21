package Model.Operation;

import Model.FileService.FileService;
import static Model.Constants.DECRYPTED;
import java.io.File;
import java.io.IOException;

public class DecryptOperation {

    public static void decode(String alphabet,File inputFile, int key) {
        File outputFile = OutputFile.getOutputFile(inputFile,DECRYPTED.getText());
        try {
            String content = FileService.readFile(inputFile);
            String decodedContent = decodeContent(alphabet,content, key);
            FileService.writeFile(outputFile, decodedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String decodeContent(String alphabet,String content, int key) {
        StringBuilder decodedContent = new StringBuilder();
        for (char data : content.toCharArray()) {
            int index = alphabet.indexOf(data);
            if (index != -1) {
                int newIndex = (index - key) % alphabet.length();
                if (newIndex < 0) {
                    newIndex += alphabet.length();
                }
                decodedContent.append(alphabet.charAt(newIndex));
            } else {
                decodedContent.append(data);
            }
        }
        return decodedContent.toString();
    }
}