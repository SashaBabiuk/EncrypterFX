package org.encrypter.Model.Encryptor;

public class UkrainianEncryptor extends BaseEncryptor {
    private static final String UKRAINIAN_ALPHABET = "АБВГҐДЕЄЖЗИІЇЙКЛМНОПРСТУФХЦЧШЩЬЮЯабвгґдеєжзиіїйклмнопрстуфхцчшщьюя.,«»\"':!? ";
    public UkrainianEncryptor() {
        super(UKRAINIAN_ALPHABET);
    }
}
