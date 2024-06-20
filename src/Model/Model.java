package Model;

import Model.Encryptor.ArabicEncryptor;
import Model.Encryptor.ChineseEncryptor;
import Model.Encryptor.Encryptor;
import Model.EncryptorFanctory.*;

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
            case "Arabic" -> encryptor = new ArabicEncryptorFactory().createEncryptor();
        }
    }


    public Encryptor getEncryptor() {
        return encryptor;
    }
}
