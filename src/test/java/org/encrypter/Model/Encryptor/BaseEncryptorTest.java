package org.encrypter.Model.Encryptor;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BaseEncryptorTest {

    private static final String ARABIC_ALPHABET = "ى ء آ ؤ إ ئ ة\n؛ ، ؟ا ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ى ء آ ؤ إ ئ ة ؛ ، ؟";
    private static final String CHINESE_ALPHABET = "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生会着对后自里那学好看起发当么事";
    private static final String UKRAINIAN_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,«»\"':!? ";
    private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz,.!? ";

    static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("Hello, World!", "Khoor?CZruogA", ENGLISH_ALPHABET),
                Arguments.of("Привіт, світ!", "Туйдкх\"ВфдкхА", UKRAINIAN_ALPHABET),
                Arguments.of("你好，世界！", "会发，世界！", CHINESE_ALPHABET)
        );
    }

    @ParameterizedTest(name = "Test encoding and decoding functionality")
    @MethodSource("provideTestCases")
    void testEncodeAndDecodeContent(String inputText, String expectedEncodedText, String alphabet) {
        System.out.println("Testing with alphabet: " + alphabet);
        int key = 3;

        // Шифруємо
        String encoded = new BaseEncryptor(alphabet).encodeContent(inputText, key);
        assertEquals(expectedEncodedText, encoded);

        // Дешифруємо
        String decoded = new BaseEncryptor(alphabet).decodeContent(encoded, key);
        assertEquals(inputText, decoded);
    }
}
