package org.encrypter.Model.EncryptorFanctory;

import org.encrypter.Model.Encryptor.*;

public class EnglishEncryptorFactory implements EncryptorFactory {
    @Override
    public Encryptor createEncryptor() {
        return new EnglishEncryptor();
    }
}
