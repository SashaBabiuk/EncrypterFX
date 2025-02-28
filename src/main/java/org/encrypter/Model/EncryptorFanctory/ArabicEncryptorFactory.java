package org.encrypter.Model.EncryptorFanctory;

import org.encrypter.Model.Encryptor.ArabicEncryptor;
import org.encrypter.Model.Encryptor.Encryptor;

public class ArabicEncryptorFactory implements EncryptorFactory{
    @Override
    public Encryptor createEncryptor() {
        return new ArabicEncryptor();
    }
}
