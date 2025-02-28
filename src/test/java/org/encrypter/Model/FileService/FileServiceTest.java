package org.encrypter.Model.FileService;

import org.junit.jupiter.api.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FileServiceTest {
    private static File file;
    private static final String EXPECTED_CONTENT = "Hello, JUnit!";

    @BeforeAll
    public static void setFile() throws IOException{
        file = new File("src/test/resources/testFile.txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(EXPECTED_CONTENT);
        }
    }

    @Test
    @Order(1)
    @DisplayName("Перевірка, що метод readFile не кидає винятків")
    public void testReadFile(){
        Assertions.assertDoesNotThrow(() -> FileService.readFile(file));
    }

    @Test
    @Order(2)
    @DisplayName("Перевірка правильного читання файлу")
    public void testReadFileCorrectContent() throws IOException {
        String content = FileService.readFile(file);
        Assertions.assertEquals(EXPECTED_CONTENT, content.trim(), "Файл має неправильний вміст");
    }

    @Test
    @Order(3)
    @DisplayName("Перевірка винятку при читанні неіснуючого файлу")
    public void testReadFileThrowsExceptionOnMissingFile() {
        File missingFile = new File("src/test/resources/missingFile.txt");
        Assertions.assertThrows(IOException.class, () -> FileService.readFile(missingFile));
    }

    @Test
    @Order(4)
    @DisplayName("Перевірка на правильний запис у файл")
    public void testWriteFile() throws IOException {
        String writeContent = "Test writing";
        FileService.writeFile(file, writeContent);
        String readContent = FileService.readFile(file);
        Assertions.assertEquals(writeContent, readContent.trim(), "Вміст файлу не співпадає після запису");
    }

    @AfterAll
    public static void cleanUp() throws IOException {
        Files.deleteIfExists(Path.of(file.getPath()));
    }

}
