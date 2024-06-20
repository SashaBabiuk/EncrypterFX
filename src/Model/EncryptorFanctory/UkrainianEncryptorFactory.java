package Model.EncryptorFanctory;

import Model.Encryptor.*;

public class UkrainianEncryptorFactory implements EncryptorFactory {
    @Override
    public Encryptor createEncryptor() {
        return new UkrainianEncryptor();
    }
}
