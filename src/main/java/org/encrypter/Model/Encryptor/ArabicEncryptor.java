package Model.Encryptor;

public class ArabicEncryptor extends BaseEncryptor{
    private static final String ARABIC_ALPHABET = "ى ء آ ؤ إ ئ ة\n؛ ، ؟ا ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ى ء آ ؤ إ ئ ة ؛ ، ؟";

    public ArabicEncryptor() {
        super(ARABIC_ALPHABET);
    }
}
