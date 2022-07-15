package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    private String excelFilePath = "@../../Register.xlsx";

    @FXML
    private Button close;
    @FXML
    private Button logIn;
    @FXML
    private Hyperlink registerHyperlink;
    @FXML
    private Label wrongData;
    @FXML
    private ImageView brandingImageView;
    @FXML
    private TextField username;
    @FXML
    private PasswordField passwordField;

    private String usernameCell;
    private String passwordCell;

    //close button
    public void closeOnAction(ActionEvent event) {
        Stage scene = (Stage) close.getScene().getWindow();
        scene.close();
        Platform.exit();
    }

    public void registerLink(ActionEvent event) {
        try {
            //opening registration panel
            Parent root = FXMLLoader.load(getClass().getResource("RegistrationSample.fxml"));
            Stage scene2 = new Stage();
            scene2.initStyle(StageStyle.UNDECORATED);
            scene2.setScene(new Scene(root, 500, 812));
            scene2.show();

        } catch (Exception e) {
            e.printStackTrace();
            e.getCause();
        }

    }

    public void logIn(ActionEvent event) throws IOException {
        if (event.getSource() == logIn) {
            try (
                    FileInputStream inputted = new FileInputStream(excelFilePath);
                    XSSFWorkbook workbook = new XSSFWorkbook(inputted);
            ) {
                XSSFSheet sheet = workbook.getSheetAt(0);
                int numberOfRows = sheet.getPhysicalNumberOfRows();

                //iterating trough excel sheet
                for (int i = 2; i < numberOfRows; i++) {
                    System.out.println(numberOfRows);
                    XSSFCell usersnames = sheet.getRow(i).getCell(0);
                    DataFormatter formatter = new DataFormatter();
                    usernameCell = formatter.formatCellValue(usersnames);
                    //rejecting users with username non existent in excel file
                    if (!username.getText().equals(usernameCell)) {
                        wrongData.setText("Invalid username !");
                    } else {
                        XSSFCell passwords = sheet.getRow(i).getCell(4);
                        passwordCell = formatter.formatCellValue(passwords);
                        if (!passwordField.getText().equals(passwordCell)) {
                            wrongData.setText("Invalid password !");
                        } else {
                            wrongData.setText("Logged in successfully :)");
                            System.out.println("User :" + usernameCell + " logged in successfully :) !");
                        }
                    }

                }
            } catch (NullPointerException e) {
                e.printStackTrace();
                System.out.println("Enter all data required!");
                wrongData.setText("Enter all data required!");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("File on given filepath : ' " + excelFilePath + " ' was not found.");
            }

        }
    }
/*
    private void read(String excelFilePath) throws IOException {
        try (
                FileInputStream inputted = new FileInputStream(excelFilePath);
                XSSFWorkbook workbook = new XSSFWorkbook(inputted);
        ) {
            XSSFSheet sheet = workbook.getSheetAt(0);
            DataFormatter formatter = new DataFormatter();
            XSSFRow row = sheet.getRow(1);
            XSSFCell cell = row.getCell(5);
            XSSFCell cell4 = row.getCell(6);
            nameCell = formatter.formatCellValue(cell);
            passwordCell = formatter.formatCellValue(cell4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logIn(ActionEvent event) throws IOException {
        if (event.getSource() == logIn) {

            read(excelFilePath);
            if (!username.getText().equals(nameCell)) {
                wrongData.setText("Invalid username !");
                System.out.println("Entered username: " + passwordField.getText());
                System.out.println("Existent username in excel:" + nameCell);
            }
            if (passwordField.getText() != passwordCell) {
                wrongData.setText("Invalid password !");
                System.out.println("Entered password: " + passwordField.getText());
                System.out.println("Existent password in excel:" + passwordCell);

            } else {
                wrongData.setText("Logged in successfully :)");
            }
        }

    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image brandingImage = new Image(new File("@../../gif1.gif").toURI().toString());
        brandingImageView.setImage(brandingImage);

    }
}


