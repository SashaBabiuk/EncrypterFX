package Model.EncryptorFanctory;

import Model.Encryptor.ArabicEncryptor;
import Model.Encryptor.Encryptor;

public class ArabicEncryptorFactory implements EncryptorFactory{
    @Override
    public Encryptor createEncryptor() {
        return new ArabicEncryptor();
    }
}
