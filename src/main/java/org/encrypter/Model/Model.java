package org.encrypter.Model;

import org.encrypter.Model.Encryptor.Encryptor;
import org.encrypter.Model.EncryptorFanctory.*;

public class Model{
    private Encryptor encryptor;

    public Model() {
        encryptor = new EnglishEncryptorFactory().createEncryptor();
    }

    public void setEncryptor(String language) {
        switch (language)
        {
            case "Ukrainian" -> encryptor = new UkrainianEncryptorFactory().createEncryptor();
            case "English" -> encryptor = new EnglishEncryptorFactory().createEncryptor();
            case "Chinese" -> encryptor = new ChineseEncryptorFactory().createEncryptor();
        }
    }

    public Encryptor getEncryptor() {
        return encryptor;
    }
}
