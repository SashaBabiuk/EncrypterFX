package Model.Encryptor;

import static Model.Constants.UKRAINIAN_ALPHABET;

public class UkrainianEncryptor extends BaseEncryptor {
    public UkrainianEncryptor() {
        super(UKRAINIAN_ALPHABET.getText());
    }
}
