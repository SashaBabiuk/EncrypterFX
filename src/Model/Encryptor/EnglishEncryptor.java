package Model.Encryptor;

import static Model.Constants.ENGLISH_ALPHABET;

public class EnglishEncryptor extends BaseEncryptor {

    public EnglishEncryptor() {
        super(ENGLISH_ALPHABET.getText());
    }

}
