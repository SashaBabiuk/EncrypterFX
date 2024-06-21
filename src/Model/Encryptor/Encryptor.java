package Model.Encryptor;

import java.io.File;
import java.util.HashMap;

public interface Encryptor {
    void encode(File inputFile, int key);

    void decode(File inputFile, int key);
    void bruteForce(File inputFile);
    void statisticalAnalysis(File inputFile);
}