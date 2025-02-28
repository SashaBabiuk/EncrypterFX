package org.encrypter.Model.Encryptor;

import java.io.File;
import java.util.HashMap;

public interface Encryptor {
    void encode(File inputFile, int key);

    void decode(File inputFile, int key);
    File getOutputFile();
    void bruteForce(File inputFile);

    int bruteForceFoundKey(String benchmarkEncryptText, String benchmarkDecryptText);
    void setBenchmarkEncryptText(String benchmarkEncryptText);

    void setBenchmarkDecryptText(String benchmarkDecryptText);
    void setInputFile(File inputFile);
    HashMap<Character,Integer> staticAnalysis(File inputFile);

}