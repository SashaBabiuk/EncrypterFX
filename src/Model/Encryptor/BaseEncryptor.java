package Model.Encryptor;

import Model.FileService.FileService;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseEncryptor implements Encryptor {
    private final String ENCRYPTED = "[ENCRYPTED]";
    private final String DECRYPTED = "[DECRYPTED]";
    private static String ALPHABET;
    private String BENCHMARK_ENCRYPT_TEXT;
    private String BENCHMARK_DECRYPT_TEXT;
    private File outputFile;
    private File inputFile;

    public BaseEncryptor(String ALPHABET) {
        BaseEncryptor.ALPHABET = ALPHABET;
    }

    @Override
    public void encode(File inputFile, int key) {
        File outputFile = new File("resource/" + getFileName(inputFile, ENCRYPTED, ".txt"));
        this.outputFile = outputFile;
        try {
            String content = FileService.readFile(inputFile);
            String encodedContent = encodeContent(content, key);
            FileService.writeFile(outputFile, encodedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void decode(File inputFile, int key) {
        File outputFile = new File("resource/" + getFileName(inputFile, DECRYPTED, ".txt"));
        this.outputFile = outputFile;
        try {
            String content = FileService.readFile(inputFile);
            String decodedContent = decodeContent(content, key);
            FileService.writeFile(outputFile, decodedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void bruteForce(File inputFile) {
        File outputFile = new File("resource/" + getFileName(inputFile, DECRYPTED, ".txt"));
        int key = bruteForceFoundKey(BENCHMARK_ENCRYPT_TEXT, BENCHMARK_DECRYPT_TEXT);
        try {
            String content = FileService.readFile(inputFile);
            String decodedContent = decodeContent(content, key);
            FileService.writeFile(outputFile, decodedContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.outputFile = outputFile;
    }

    public int bruteForceFoundKey(String benchmarkEncryptText, String benchmarkDecryptText) {
        int key = 0;
        String decryptedText = benchmarkEncryptText;
        while (!decryptedText.equals(benchmarkDecryptText) && key < ALPHABET.length()) {
            decryptedText = decodeContent(benchmarkEncryptText, key);
            key++;
        }
        return key == ALPHABET.length() ? -1 : key - 1;
    }
    private String decodeContent(String content, int key) {
        StringBuilder decodedContent = new StringBuilder();
        for (char data : content.toCharArray()) {
            int index = ALPHABET.indexOf(data);
            if (index != -1) {
                int newIndex = (index - key) % ALPHABET.length();
                if (newIndex < 0) {
                    newIndex += ALPHABET.length();
                }
                decodedContent.append(ALPHABET.charAt(newIndex));
            } else {
                decodedContent.append(data);
            }
        }
        return decodedContent.toString();
    }

    private String encodeContent(String content, int key) {
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
    private String getFileName(File inputFile, String suffix, String format) {
        String fileName = inputFile.getName();
        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex != -1) {
            return fileName.substring(0, dotIndex) + suffix + format;
        } else {
            return fileName + suffix + format;
        }
    }
    @Override
    public HashMap<Character,Integer> staticAnalysis(File inputFile) {
        HashMap<Character,Integer> charFrequencyMap = new HashMap<>();
        try {
            String fileData = FileService.readFile(inputFile);
            for(char ch : fileData.toCharArray())
            {
                charFrequencyMap.put(ch,charFrequencyMap.getOrDefault(ch,0)+1);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return charFrequencyMap;
    }

    public void setBenchmarkEncryptText(String benchmarkEncryptText) {
        BENCHMARK_ENCRYPT_TEXT = benchmarkEncryptText;
    }

    public void setBenchmarkDecryptText(String benchmarkDecryptText) {
        BENCHMARK_DECRYPT_TEXT = benchmarkDecryptText;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

}
