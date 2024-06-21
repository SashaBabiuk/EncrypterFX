package Model.Operation;

import Model.FileService.FileService;

import java.io.File;
import java.io.IOException;
import static Model.Constants.ENCRYPTED;
public class EncryptOperation {
    public static void encode(String ALPHABET,File inputFile, int key) {
        File outputFile = OutputFile.getOutputFile(inputFile,ENCRYPTED.getText());
        try {
            String content = FileService.readFile(inputFile);
            String encodedContent = encodeContent(ALPHABET,content, key);
            FileService.writeFile(outputFile, encodedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String encodeContent(String ALPHABET,String content, int key) {
        StringBuilder encodedContent = new StringBuilder();
        for (char data : content.toCharArray()) {
            int index = ALPHABET.indexOf(data);
            if (index != -1) {
                int newIndex = (index + key) % ALPHABET.length();
                encodedContent.append(ALPHABET.charAt(newIndex));
            } else {
                encodedContent.append(data);
            }
        }
        return encodedContent.toString();
    }
}
