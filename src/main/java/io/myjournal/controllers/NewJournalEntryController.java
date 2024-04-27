package io.myjournal.controllers;

import io.myjournal.models.JournalEntryCollection;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class NewJournalEntryController {
    @FXML private TextField titleTextField;
    @FXML private TextArea contentTextArea;
    @FXML private Button btnCancelAddJournalEntry;
    @FXML private Button btnConfirmAddJournalEntry;
    JournalEntryCollection journalEntryCollection;
    public NewJournalEntryController(JournalEntryCollection journalEntryCollection) {
        this.journalEntryCollection = journalEntryCollection;
    }
    @FXML private void onBtnConfirmAddJournalEntryClick() {
        String title = titleTextField.getText().trim();
        String content = contentTextArea.getText().trim();
        if(!title.isEmpty() && !content.isEmpty()){
            journalEntryCollection.addEntry(title, content);
            closeWindow();
        }
    }
    @FXML
    private void onBtnCancelAddJournalEntryClick() {
        closeWindow();
    }
    private void closeWindow() {
        Stage stage = (Stage) btnCancelAddJournalEntry.getScene().getWindow();
        stage.close();
    }
}
