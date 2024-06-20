package Model.Encryptor;

public class EnglishEncryptor extends BaseEncryptor {

    private static final String ENGLISH_ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,«»\"':!? ";

    public EnglishEncryptor() {
        super(ENGLISH_ALPHABET);
    }

}
