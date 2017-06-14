package com.gmail.kolesnyk.zakhar.controller;


import com.gmail.kolesnyk.zakhar.model.contactsService.ContactsService;
import com.gmail.kolesnyk.zakhar.model.contactsService.ContactsServiceImpl;
import com.gmail.kolesnyk.zakhar.model.historyService.FieldsContainer;
import com.gmail.kolesnyk.zakhar.model.historyService.HistoryService;
import com.gmail.kolesnyk.zakhar.model.historyService.HistoryServiceImpl;
import javafx.beans.InvalidationListener;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class ContactsController {

    private final static String WARNING = "Invalid input: ";
    private final static String RED_BORDER = "-fx-border-color: red; -fx-border-width: 2px;";
    private final static String TRANSPARENT_BORDER = "-fx-border-color: inherit; -fx-border-width: 0px;";

    private ContactsService contactsService;
    private HistoryService historyService;
    private Set<Button> idButtons;
    private Set<Button> nameButtons;
    private Set<Button> jsonButtons;
    private Set<TextInputControl> inputControls;

    public TextArea jsonArea;
    public TextField idTextField;
    public TextField nameTextField;
    public Label warningLabel;
    public ChoiceBox<FieldsContainer> historyChoiceBox;
    public Button addButton;
    public Button getByNameButton;
    public Button getByIdButton;
    public Button updateButton;
    public Button deleteByJsonButton;
    public Button deleteByIDButton;

    public void initialize() {
        contactsService = new ContactsServiceImpl();
        historyService = new HistoryServiceImpl(historyChoiceBox);
        initNodesCollections();
        nameTextField.textProperty().addListener(nameTextFieldListener());
        idTextField.textProperty().addListener(idTextFieldListener());
        jsonArea.textProperty().addListener(textAreaListener());
        getByIdButton.setOnAction(getByIdButtonListener());
        getByNameButton.setOnAction(getByNameButtonListener());
        updateButton.setOnAction(updateButtonListener());
        addButton.setOnAction(addButtonListener());
        deleteByIDButton.setOnAction(deleteByIdButtonListener());
        deleteByJsonButton.setOnAction(deleteByJsonButtonListener());
        historyChoiceBox.setOnAction(historyChoiceBoxListener());
    }

    private EventHandler<ActionEvent> historyChoiceBoxListener() {
        return value -> {
            FieldsContainer item = historyChoiceBox.getValue();
            if (item != null) {
                idTextField.setText(item.getIdText());
                nameTextField.setText(item.getNameText());
                jsonArea.setText(item.getJsonText());
            }
            historyChoiceBox.getSelectionModel().select(null);
        };
    }

    private void initNodesCollections() {
        inputControls = new HashSet<TextInputControl>() {{
            add(jsonArea);
            add(idTextField);
            add(nameTextField);
        }};
        idButtons = new HashSet<Button>() {{
            add(getByIdButton);
            add(updateButton);
            add(deleteByIDButton);
        }};
        nameButtons = new HashSet<Button>() {{
            add(getByNameButton);
        }};
        jsonButtons = new HashSet<Button>() {{
            add(addButton);
            add(deleteByJsonButton);
            add(updateButton);
        }};
    }

    private InvalidationListener idTextFieldListener() {
        return value -> {
            if (isId(idTextField.getText())) {
                cleanRedBorder(idTextField);
                idButtons.forEach(a -> a.setDisable(false));
                return;
            }
            idTextField.setStyle(RED_BORDER);
            idButtons.forEach(a -> a.setDisable(true));
        };
    }

    private InvalidationListener textAreaListener() {
        return value -> {
            if (isJson(jsonArea.getText())) {
                cleanRedBorder(jsonArea);
                jsonButtons.forEach(a -> a.setDisable(false));
                return;
            }
            jsonArea.setStyle(RED_BORDER);
            jsonButtons.forEach(a -> a.setDisable(true));
        };
    }

    private InvalidationListener nameTextFieldListener() {
        return value -> {
            if (isName(nameTextField.getText())) {
                cleanRedBorder(nameTextField);
                nameButtons.forEach(a -> a.setDisable(false));
                return;
            }
            nameTextField.setStyle(RED_BORDER);
            nameButtons.forEach(a -> a.setDisable(true));
        };
    }

    private EventHandler<ActionEvent> addButtonListener() {
        return event -> {
            if (!isJson(jsonArea.getText().trim())) {
                warningLabel.setText(WARNING + "enter correct json");
            } else {
                try {
                    jsonArea.setText(contactsService.addContact(jsonArea.getText().trim()));
                    warningLabel.setText("");
                    updateHistory();
                } catch (IOException e) {
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "such name or phone already exist");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> getByNameButtonListener() {
        return event -> {
            if (!isName(nameTextField.getText())) {
                warningLabel.setText(WARNING + "enter correct name");
            } else {
                try {
                    jsonArea.setText(contactsService.getContactByName(nameTextField.getText().trim()));
                    warningLabel.setText("");
                    updateHistory();
                } catch (IOException e) {
                    paintRedBorder(nameTextField);
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "Name not found");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> getByIdButtonListener() {
        return event -> {
            if (!isId(idTextField.getText())) {
                warningLabel.setText(WARNING + "enter number of ID");
            } else {
                try {
                    jsonArea.setText(contactsService.getContactByID(idTextField.getText().trim()));
                    warningLabel.setText("");
                    updateHistory();
                } catch (IOException e) {
                    paintRedBorder(idTextField);
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "ID not found");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> updateButtonListener() {
        return event -> {
            if (!isId(idTextField.getText()) || !isJson(jsonArea.getText())) {
                warningLabel.setText(WARNING + "enter number of ID and json");
            } else {
                try {
                    jsonArea.setText(contactsService.updateContact(idTextField.getText().trim(), jsonArea.getText().trim()));
                    warningLabel.setText("");
                    updateHistory();
                } catch (IOException e) {
                    paintRedBorder(idTextField);
                    paintRedBorder(jsonArea);
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "ID not found");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> deleteByIdButtonListener() {
        return event -> {
            if (!isId(idTextField.getText())) {
                warningLabel.setText(WARNING + "enter number of ID");
            } else {
                try {
                    contactsService.deleteContactByID(idTextField.getText().trim());
                    warningLabel.setText("Deleting successful");
                    jsonArea.setText("");
                    updateHistory();
                } catch (IOException e) {
                    paintRedBorder(idTextField);
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "ID not found");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private EventHandler<ActionEvent> deleteByJsonButtonListener() {
        return event -> {
            if (!isJson(jsonArea.getText())) {
                warningLabel.setText(WARNING + "enter number of ID");
            } else {
                try {
                    contactsService.deleteContactByJson(jsonArea.getText().trim());
                    warningLabel.setText("Deleting successful");
                    jsonArea.setText("");
                    updateHistory();
                } catch (IOException e) {
                    paintRedBorder(jsonArea);
                    if (e.getMessage().contains("400")) {
                        warningLabel.setText(WARNING + "ID not found");
                    } else {
                        warningLabel.setText(WARNING + "bad request");
                    }
                }
            }
        };
    }

    private boolean isJson(String json) {
        return json.trim().matches("(?i)\\{" +
                "((|[\\s])\"id\"(|[\\s]):(|[\\s])[0-9]{0,11}(|[\\s]),|)" +
                "(|[\\s])\"userName\"(|[\\s]):(|[\\s])\"[a-zA-Z]{2,32}\"(|[\\s])," +
                "(|[\\s])\"phone\"(|[*\\s]):(|[\\s])\"[0-9]{2,32}\"(|[\\s])" +
                "\\}");
    }

    private boolean isName(String name) {
        return name.matches("[a-zA-Z]{2,32}");
    }

    private boolean isId(String id) {
        return id.matches("[0-9]{1,11}");
    }

    private void paintRedBorder(TextInputControl node) {
        cleanRedBorder();
        node.setStyle(RED_BORDER);
    }

    private void cleanRedBorder() {
        inputControls.forEach(this::cleanRedBorder);
    }

    private void cleanRedBorder(TextInputControl node) {
        node.setStyle(TRANSPARENT_BORDER);
    }

    private void updateHistory() {
        historyService.addToHistory(idTextField.getText(), nameTextField.getText(), jsonArea.getText());
    }
}
