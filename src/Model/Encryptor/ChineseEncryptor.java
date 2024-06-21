package Model.Encryptor;

import static Model.Constants.CHINESE_ALPHABET;

public class ChineseEncryptor extends BaseEncryptor {
    public ChineseEncryptor() {
        super(CHINESE_ALPHABET.getText());
    }
}
