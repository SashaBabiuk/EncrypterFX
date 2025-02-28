package org.encrypter.Model.EncryptorFanctory;

import org.encrypter.Model.Encryptor.ChineseEncryptor;
import org.encrypter.Model.Encryptor.Encryptor;

public class ChineseEncryptorFactory implements EncryptorFactory{
    @Override
    public Encryptor createEncryptor() {
        return new ChineseEncryptor();
    }
}
