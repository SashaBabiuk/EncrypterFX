package Model.EncryptorFanctory;

import Model.Encryptor.ChineseEncryptor;
import Model.Encryptor.Encryptor;

public class ChineseEncryptorFactory implements EncryptorFactory{
    @Override
    public Encryptor createEncryptor() {
        return new ChineseEncryptor();
    }
}
