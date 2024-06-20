package Model.EncryptorFanctory;

import Model.Encryptor.*;

public class EnglishEncryptorFactory implements EncryptorFactory {
    @Override
    public Encryptor createEncryptor() {
        return new EnglishEncryptor();
    }
}
