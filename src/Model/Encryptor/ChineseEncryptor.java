package Model.Encryptor;

public class ChineseEncryptor extends BaseEncryptor{
    private static final String CHINESE_ALPHABET = "的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生会着对后自里那学好看起发当么事";

    public ChineseEncryptor() {
        super(CHINESE_ALPHABET);
    }
}
