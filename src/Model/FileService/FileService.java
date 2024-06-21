package Model.FileService;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

public class FileService {

    public static String readFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());
        return String.join("\n",lines);
    }

    public static void writeFile(File file, String content) throws IOException {
        Files.writeString(file.toPath(),content);
    }

    public static boolean fileExists(File file) {
        return file.exists();
    }
}
