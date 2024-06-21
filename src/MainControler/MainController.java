package MainControler;

import Model.Encryptor.Encryptor;
import Model.EncryptorFanctory.EncryptorFactory;
import Model.FileService.FileService;
import Model.Model;
import View.MainApplication;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import View.*;
public class MainController {
    private MainApplication view;
    private Encryptor encryptor;
    private Model model;

    public MainController(MainApplication view) {
        this.view = view;
        this.model = new Model();
    }
}
