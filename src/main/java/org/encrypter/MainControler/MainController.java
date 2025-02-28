package org.encrypter.MainControler;

import org.encrypter.Model.Encryptor.Encryptor;
import org.encrypter.Model.FileService.FileService;
import org.encrypter.Model.Model;
import org.encrypter.View.MainApplication;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import org.encrypter.View.*;

public class MainController {
    private MainApplication view;
    private Encryptor encryptor;
    private Model model;

    public MainController(MainApplication view) {
        this.view = view;
        this.model = new Model();
    }

    public void initialize() {
        model.getEncryptor().setInputFile(view.getInputDefaultFile());
        initializeEncryptor();
        initializeFileSelection();
        initializeLanguageChooserBox();
        initializeDataAreaBox();
        initializeActionButtonBox();
    }
    private void initializeEncryptor()
    {
        this.encryptor = model.getEncryptor();
    }

    private void initializeFileSelection() {
        view.getSelectFileButton().setOnAction(this::handleFileSelection);
        view.getDeleteFileButton().setOnAction(this::handleDeleteFile);
    }

    private void handleFileSelection(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        File selectedFile = fileChooser.showOpenDialog(view.getStage());
        if (selectedFile == null) {
            createAlertWindow("Error", null, "No file selected. Please select a file.", Alert.AlertType.WARNING);
            return;
        }

        try {
            view.setInputFile(selectedFile);
            view.getFilePathField().setText(selectedFile.toString());
            String fileData = FileService.readFile(view.getInputFile());
            view.getInputDataArea().setText(fileData);
            setInputFileEncryptor(selectedFile);
            view.getOutputDataArea().clear();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void setInputFileEncryptor(File inputFile)
    {
        model.getEncryptor().setInputFile(inputFile);
    }
    private Alert createAlertWindow(String title, String headerText, String contentText, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
        return alert;
    }

    private void handleDeleteFile(ActionEvent event) {
        view.getFilePathField().clear();
        view.getInputDataArea().clear();
        view.getOutputDataArea().clear();
        setDefaultData();
    }

    private void setDefaultData() {
        try {
            String inputFileData = FileService.readFile(view.getInputDefaultFile());
            view.setInputFile(view.getInputDefaultFile());
            view.getInputDataArea().setText(inputFileData);
            view.getFilePathField().setText(view.getInputDefaultFile().toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void initializeLanguageChooserBox() {
        System.out.println(view.getPressedRadioButton());
        model.setEncryptor(view.getPressedRadioButton());
    }

    private void initializeDataAreaBox() {
        setDefaultData();
    }

    private void initializeActionButtonBox() {
        view.getEncryptButton().setOnAction(this::handleFileEncrypt);
        view.getDecryptButton().setOnAction(this::handleFileDecrypt);
        view.getBruteForceButton().setOnAction(this::handleBruteForce);
        view.getFrequencyAnalysisButton().setOnAction(this::handleFrequencyAnalysis);
    }

    private void handleFileEncrypt(ActionEvent event) {
        try {
            int key = Integer.parseInt(view.getKey().getText());
            encryptor.encode(view.getInputFile(),key);
            view.getOutputDataArea().setText(FileService.readFile(encryptor.getOutputFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleFileDecrypt(ActionEvent event) {
        try {
            int key = Integer.parseInt(view.getKey().getText());
            encryptor.decode(view.getInputFile(),key);
            view.getOutputDataArea().setText(FileService.readFile(encryptor.getOutputFile()));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private void handleBruteForce(ActionEvent event)
    {
        try {
            BruteForceWindow bruteForceWIndow = new BruteForceWindow();
            bruteForceWIndow.newWindow();
            encryptor.setBenchmarkDecryptText(bruteForceWIndow.getDecryptBenchmarkField().getText());
            encryptor.setBenchmarkEncryptText(bruteForceWIndow.getEncryptBenchmarkField().getText());
            int key = encryptor.bruteForceFoundKey(MainApplication.getBenchmarkEncryptText(),MainApplication.getBenchmarkDecryptText());
            if (key == -1) {
                view.getKey().setText("0");
                createAlertWindow("Brute force","sorry I can't pick up the key","enter the new correct values of the benchmark texts", Alert.AlertType.INFORMATION);
            } else {
                view.getKey().setText(""+ key);
                encryptor.bruteForce(view.getInputFile());
                String fileData = FileService.readFile(encryptor.getOutputFile());
                view.getOutputDataArea().setText(fileData);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void handleFrequencyAnalysis(ActionEvent event)
    {
        StatisticAnalysis statisticAnalysis = new StatisticAnalysis();
        statisticAnalysis.newWindow();

    }
}
