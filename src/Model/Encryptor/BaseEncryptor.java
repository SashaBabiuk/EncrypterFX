package Model.Encryptor;

import Model.FileService.FileService;
import Model.Operation.BruteForceOperation;
import Model.Operation.DecryptOperation;
import Model.Operation.EncryptOperation;
import Model.Operation.StatisticalAnalysis;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class BaseEncryptor implements Encryptor {
    private static String ALPHABET;

    public BaseEncryptor(String ALPHABET) {
        BaseEncryptor.ALPHABET = ALPHABET;
    }

    @Override
    public void encode(File inputFile, int key) {
        EncryptOperation.encode(ALPHABET,inputFile,key);
    }

    @Override
    public void decode(File inputFile, int key) {
        DecryptOperation.decode(ALPHABET,inputFile,key);
    }

    @Override
    public void bruteForce(File inputFile) {
        BruteForceOperation.bruteForce(ALPHABET,inputFile);
    }

    @Override
    public void statisticalAnalysis(File inputFile) {
        StatisticalAnalysis.statisticalAnalysis(ALPHABET,inputFile);
    }
}
