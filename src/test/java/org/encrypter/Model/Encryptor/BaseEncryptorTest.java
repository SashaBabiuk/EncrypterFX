package org.encrypter.Model.Encryptor;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class BaseEncryptorTest {

    private static final String ARABIC_ALPHABET = "ى ء آ ؤ إ ئ ة\n؛ ، ؟ا ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ى ء آ ؤ إ ئ ة ؛ ، ؟";
    private static final String CHINESE_ALPHABET = "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生会着对后自里那学好看起发当么事";
    private static final String UKRAINIAN_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,«»\"':!? ";
    private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,.!? ";

    private final String inputText;
    private final String expectedEncodedText;
    private final String alphabet;

    // Конструктор параметризованого тесту
    public BaseEncryptorTest(String inputText, String expectedEncodedText, String alphabet) {
        this.inputText = inputText;
        this.expectedEncodedText = expectedEncodedText;
        this.alphabet = alphabet;
    }

    @Test
    public void testEncodeAndDecodeContent() {
        int key = 3;

        // Шифруємо
        String encoded = new BaseEncryptor(alphabet).encodeContent(inputText, key);
        assertEquals(expectedEncodedText, encoded);

        // Дешифруємо
        String decoded = new BaseEncryptor(alphabet).decodeContent(encoded, key);
        assertEquals(inputText, decoded);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                // Англійська мова
                {"Hello, World!", "Khoor?CZruogA", ENGLISH_ALPHABET},

                // Українська мова
                {"Привіт, світ!", "Туйдкх\"ВфдкхА", UKRAINIAN_ALPHABET},

                // Китайська мова
                {"你好，世界！", "会发，世界！", CHINESE_ALPHABET},

        });
    }
}
