package View;

import MainControler.MainController;
import Model.Encryptor.Encryptor;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class MainApplication extends Application {
    private Stage stage;

    private VBox rootLayout = new VBox(50);

    private TextField filePathField;
    private Button selectFileButton;
    private Button deleteFileButton;
    private TextField key;
    private TextArea inputDataArea;
    private TextArea outputDataArea;
    private String pressedRadioButton = "English";
    private Button encryptButton;
    private Button decryptButton;
    private Button bruteForceButton;
    private Button frequencyAnalysisButton;
    private File inputDefaultFile = new File("resource/dataEnglish.txt");
    private File inputFile;
    private File outputFile;
    private TextField inputKey;
    private MainController mainController;
    static File dictionaryFile;
    static String BENCHMARK_ENCRYPT_TEXT;
    static String BENCHMARK_DECRYPT_TEXT;

    @Override
    public void start(Stage primaryStage) throws Exception {

        this.stage = primaryStage;
        initializationRootLayout(primaryStage);
        Scene scene = new Scene(rootLayout, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Encryption Tool");
        primaryStage.show();
        this.mainController = new MainController(this);
        mainController.initialize();

    }

    private void initializationRootLayout(Stage stage) {
        rootLayout.getChildren().addAll(
                createFileSelectionBox(stage),
                createLanguageChooserBox(),
                createTextLabelsBox(),
                createDataAreaBox(),
                createActionButtonBox()
        );
    }

    private HBox createFileSelectionBox(Stage stage)
    {
        HBox fileSelectionBox = new HBox(10);

        Text filePath = createFilePathText();
        TextField filePathField = createFilePathField();
        this.selectFileButton = createButton("Select");
        this.deleteFileButton = createButton("Delete");

        fileSelectionBox.getChildren().addAll(filePath, filePathField, selectFileButton, deleteFileButton);

        fileSelectionBox.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        return fileSelectionBox;
    }


    private Text createFilePathText() {
        Text filePathText = new Text("File Path");
        Font font = new Font(25);
        filePathText.setFont(font);
        HBox.setMargin(filePathText, new Insets(0, 30, 30, 0));
        return filePathText;
    }

    private TextField createFilePathField() {
        TextField filePathField = new TextField();
        HBox.setMargin(filePathField, new Insets(2, 30, 30, 0));
        filePathField.setPrefSize(180, 30);
        Font font = new Font(25);
        filePathField.setText(inputDefaultFile.toString());
        HBox.setHgrow(filePathField, Priority.ALWAYS);
        this.filePathField = filePathField;
        return filePathField;
    }
    private HBox createLanguageChooserBox()
    {
        HBox languageChooserBox = new HBox();
        ToggleGroup toggleGroup = new ToggleGroup();

        RadioButton ukrainian = createRadioButton(toggleGroup, "Ukrainian");
        RadioButton english = createRadioButton(toggleGroup, "English");
        RadioButton chinese = createRadioButton(toggleGroup, "Chinese");
        RadioButton arabic = createRadioButton(toggleGroup, "Arabic");
        english.setSelected(true);
        toggleGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle newToggle) {
                pressedRadioButton = ((RadioButton) newToggle).getText();
                mainController.initializeLanguageChooserBox();
            }
        });
        languageChooserBox.getChildren().addAll(ukrainian, english, chinese, arabic);
        return languageChooserBox;
    }
    //
    private RadioButton createRadioButton(ToggleGroup toggleGroup, String text) {
        RadioButton radioButton = new RadioButton(text);
        radioButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(radioButton, Priority.ALWAYS);
        radioButton.setUserData(text);
        radioButton.setToggleGroup(toggleGroup);
        return radioButton;
    }

    private HBox createTextLabelsBox() {
        HBox textLabelsBox = new HBox(300);
        textLabelsBox.setAlignment(Pos.CENTER_LEFT);

        Label textDataLabel = createTextDataLabel();
        Label encryptLabel = createEncryptTextLabel();
        TextField inputKey = createTextFieldKey();
        this.inputKey = inputKey;


        textLabelsBox.getChildren().addAll(textDataLabel,inputKey,encryptLabel);
        textDataLabel.setAlignment(Pos.CENTER);
        return textLabelsBox;
    }

    private Label createTextDataLabel() {
        Label textDataLabel = new Label("File Data:");
        Font font = new Font(25);
        textDataLabel.setFont(font);
        return textDataLabel;
    }

    //
    private TextField createTextFieldKey()
    {
        TextField key = new TextField();
        Font font = new Font(25);
        key.setPromptText("Enter key");
        key.setFont(font);
        this.key = key;
        return key;
    }
    private Label createEncryptTextLabel() {
        Label encryptTextLabel = new Label("Encrypt/Decrypt:");
        Font font = new Font(25);
        encryptTextLabel.setFont(font);
        return encryptTextLabel;
    }

    private HBox createDataAreaBox() {
        HBox dataEntryBox = new HBox(10);
        TextArea inputDataArea = createInputDataArea();
        TextArea modifiedDataArea = createModifiedDataArea();
        dataEntryBox.getChildren().addAll(inputDataArea, modifiedDataArea);
        this.inputDataArea = inputDataArea;
        this.outputDataArea = modifiedDataArea;
        return dataEntryBox;
    }

    private TextArea createInputDataArea() {
        TextArea inputDataArea = new TextArea();
        inputDataArea.setPrefRowCount(100);
        inputDataArea.setPrefColumnCount(100);
        inputDataArea.setWrapText(true);
        return inputDataArea;
    }

    private TextArea createModifiedDataArea() {
        TextArea outputDataArea = new TextArea();
        outputDataArea.setPrefRowCount(100);
        outputDataArea.setPrefColumnCount(100);
        outputDataArea.setWrapText(true);
        return outputDataArea;
    }

    private HBox createActionButtonBox() {
        HBox actionButtonBox = new HBox(10);
        this.encryptButton = createButton("Encrypt");
        this.decryptButton = createButton("Decrypt");
        this.bruteForceButton = createButton("Brute force");
        this.frequencyAnalysisButton = createButton("Statistical Analysis");
        actionButtonBox.getChildren().addAll(encryptButton, decryptButton, bruteForceButton, frequencyAnalysisButton);
        return actionButtonBox;
    }
    private Button createButton(String text) {
        Button button = new Button(text);
        button.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        HBox.setHgrow(button, Priority.ALWAYS);
        return button;
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public VBox getRootLayout() {
        return rootLayout;
    }

    public void setRootLayout(VBox rootLayout) {
        this.rootLayout = rootLayout;
    }

    public TextField getFilePathField() {
        return filePathField;
    }

    public void setFilePathField(TextField filePathField) {
        this.filePathField = filePathField;
    }

    public Button getSelectFileButton() {
        return selectFileButton;
    }

    public void setSelectFileButton(Button selectFileButton) {
        this.selectFileButton = selectFileButton;
    }

    public Button getDeleteFileButton() {
        return deleteFileButton;
    }

    public void setDeleteFileButton(Button deleteFileButton) {
        this.deleteFileButton = deleteFileButton;
    }

    public TextField getKey() {
        return key;
    }

    public void setKey(TextField key) {
        this.key = key;
    }

    public TextArea getInputDataArea() {
        return inputDataArea;
    }

    public void setInputDataArea(TextArea inputDataArea) {
        this.inputDataArea = inputDataArea;
    }

    public TextArea getOutputDataArea() {
        return outputDataArea;
    }

    public void setOutputDataArea(TextArea outputDataArea) {
        this.outputDataArea = outputDataArea;
    }

    public String getPressedRadioButton() {
        return pressedRadioButton;
    }

    public void setPressedRadioButton(String pressedRadioButton) {
        this.pressedRadioButton = pressedRadioButton;
    }

    public Button getEncryptButton() {
        return encryptButton;
    }

    public void setEncryptButton(Button encryptButton) {
        this.encryptButton = encryptButton;
    }

    public Button getDecryptButton() {
        return decryptButton;
    }

    public void setDecryptButton(Button decryptButton) {
        this.decryptButton = decryptButton;
    }

    public Button getBruteForceButton() {
        return bruteForceButton;
    }

    public void setBruteForceButton(Button bruteForceButton) {
        this.bruteForceButton = bruteForceButton;
    }

    public Button getFrequencyAnalysisButton() {
        return frequencyAnalysisButton;
    }

    public void setFrequencyAnalysisButton(Button frequencyAnalysisButton) {
        this.frequencyAnalysisButton = frequencyAnalysisButton;
    }

    public File getInputDefaultFile() {
        return inputDefaultFile;
    }

    public void setInputDefaultFile(File inputDefaultFile) {
        this.inputDefaultFile = inputDefaultFile;
    }

    public File getInputFile() {
        return inputFile;
    }

    public void setInputFile(File inputFile) {
        this.inputFile = inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    public void setOutputFile(File outputFile) {
        this.outputFile = outputFile;
    }

    public TextField getInputKey() {
        return inputKey;
    }

    public void setInputKey(TextField inputKey) {
        this.inputKey = inputKey;
    }

    public MainController getMainController() {
        return mainController;
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public static String getBenchmarkEncryptText() {
        return BENCHMARK_ENCRYPT_TEXT;
    }

    public static void setBenchmarkEncryptText(String benchmarkEncryptText) {
        BENCHMARK_ENCRYPT_TEXT = benchmarkEncryptText;
    }

    public static String getBenchmarkDecryptText() {
        return BENCHMARK_DECRYPT_TEXT;
    }

    public static void setBenchmarkDecryptText(String benchmarkDecryptText) {
        BENCHMARK_DECRYPT_TEXT = benchmarkDecryptText;
    }

    public static File getDictionaryFile() {
        return dictionaryFile;
    }

    public static void setDictionaryFile(File dictionaryFile) {
        MainApplication.dictionaryFile = dictionaryFile;
    }
}
