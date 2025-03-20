package com.nichi.front;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

    public class AddCompanyDialog {
        public static Companydto showAddDialog(Stage parentStage) {
            Dialog<Companydto> dialog = new Dialog<>();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(parentStage);
            dialog.setTitle("New Company");
            dialog.setHeaderText("Add new Company");

            GridPane grid = new GridPane();

            grid.setPadding(new Insets(10));
            grid.setHgap(10);
            grid.setVgap(10);

            TextField companyIDField = new TextField();
            TextField boothNOField = new TextField();
            TextField nameField = new TextField();
            TextField countryField = new TextField();
            TextField categoryField = new TextField();
            TextField emailField = new TextField();
            TextField phoneNoField = new TextField();
            TextField websiteField = new TextField();
            TextField addressField = new TextField();
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

            // Buttons
            ButtonType addButtonType = new ButtonType("Add", ButtonBar.ButtonData.OK_DONE);
            ButtonType cancelButtonType = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            dialog.getDialogPane().getButtonTypes().addAll(addButtonType, cancelButtonType);

            dialog.setResultConverter(dialogButton -> {
                if (dialogButton == addButtonType) {
                    Companydto company = new Companydto();
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



