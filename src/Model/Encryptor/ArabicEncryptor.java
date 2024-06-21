package Model.Encryptor;

import Model.*;

import static Model.Constants.ARABIC_ALPHABET;

public class ArabicEncryptor extends BaseEncryptor {

    public ArabicEncryptor() {
        super(ARABIC_ALPHABET.getText());
    }
}
