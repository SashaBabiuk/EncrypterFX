package org.encrypter.Model.EncryptorFanctory;

import org.encrypter.Model.Encryptor.*;

public class UkrainianEncryptorFactory implements EncryptorFactory {
    @Override
    public Encryptor createEncryptor() {
        return new UkrainianEncryptor();
    }
}
