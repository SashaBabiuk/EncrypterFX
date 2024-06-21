package Model.Operation;

import java.io.File;

public class OutputFile {
    public static File getOutputFile(File inputFile, String syfix) {
        return new File(inputFile.getName() + syfix);
    }
}
