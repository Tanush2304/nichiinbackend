package com.nichi.front;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class UpdateCompanyDialog {

    public static Companydto showUpdateDialog(Stage parentStage, Companydto company) {
        Dialog<Companydto> dialog = new Dialog<>();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.initOwner(parentStage);
        dialog.setTitle(company.getCompanyID());
        dialog.setHeaderText("Update details for: " + company.getCompanyID());
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10));
        grid.setHgap(10);
        grid.setVgap(10);

        TextField companyIDField = new TextField(company.getCompanyID());
        TextField boothNOField = new TextField(company.getBoothNO());
        TextField nameField = new TextField(company.getName());
        TextField countryField = new TextField(company.getCountryoforigin());
        TextField categoryField = new TextField(company.getCategory());
        TextField emailField = new TextField(company.getEmail());
        TextField phoneNoField = new TextField(company.getPhoneNo());
        TextField websiteField = new TextField(company.getWebsite());
        TextField addressField = new TextField(company.getAddress());


        grid.add(new Label("Company ID:"), 0, 1);
        grid.add(companyIDField, 1, 1);

        grid.add(new Label("Booth No:"), 0, 2);
        grid.add(boothNOField, 1, 2);

        grid.add(new Label("Name:"), 0, 3);
        grid.add(nameField, 1, 3);

        grid.add(new Label("Country of Origin:"), 0, 4);
        grid.add(countryField, 1, 4);

        grid.add(new Label("Category:"), 0, 5);
        grid.add(categoryField, 1, 5);

        grid.add(new Label("Email:"), 0, 6);
        grid.add(emailField, 1, 6);

        grid.add(new Label("Phone No:"), 0, 7);
        grid.add(phoneNoField, 1, 7);

        grid.add(new Label("Website:"), 0, 8);
        grid.add(websiteField, 1, 8);

        grid.add(new Label("Address:"), 0, 9);
        grid.add(addressField, 1, 9);

        dialog.getDialogPane().setContent(grid);



        ButtonType updateButtonType = new ButtonType("Update", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
        dialog.getDialogPane().getButtonTypes().addAll(updateButtonType, cancelButtonType);


        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == updateButtonType) {

                company.setCompanyID(companyIDField.getText());
                company.setBoothNO(boothNOField.getText());
                company.setName(nameField.getText());
                company.setCountryoforigin(countryField.getText());
                company.setCategory(categoryField.getText());
                company.setEmail(emailField.getText());
                company.setPhoneNo(phoneNoField.getText());
                company.setWebsite(websiteField.getText());
                company.setAddress(addressField.getText());
                return company;
            }
            return null;
        });

        return dialog.showAndWait().orElse(null);
    }
}
