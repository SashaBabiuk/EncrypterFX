package Model;

public enum Constants {
    ENCRYPTED("[Encrypter]"),
    DECRYPTED("[DECRYPTED]"),
    ARABIC_ALPHABET("ى ء آ ؤ إ ئ ة\n؛ ، ؟ا ب ت ث ج ح خ د ذ ر ز س ش ص ض ط ظ ع غ ف ق ك ل م ن ه و ي ى ء آ ؤ إ ئ ة ؛ ، ؟"),
    CHINESE_ALPHABET("的一是不了人我在有他这为之大来以个中上们到说国和地也子时道出而要于就下得可你年生会着对后自里那学好看起发当么事"),
    ENGLISH_ALPHABET("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz.,«»\"':!? "),
    UKRAINIAN_ALPHABET("АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,«»\"':!? ");
    private String text;

    Constants(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
