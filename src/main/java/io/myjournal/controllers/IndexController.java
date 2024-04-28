package io.myjournal.controllers;

import io.myjournal.models.JournalEntry;
import io.myjournal.models.JournalEntryCollection;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class IndexController implements PropertyChangeListener {
    private final JournalEntryCollection journalEntryCollection = new JournalEntryCollection();
    @FXML public Button btnNewJournalEntry;
    @FXML protected Button btnDeleteJournalEntry;
    @FXML private ListView<JournalEntry> listViewJournalEntryCollection;
    @FXML private TextField titleTextDisplay;
    @FXML private TextArea contentTextDisplay;
    @FXML private Label lblStatus;
    @FXML private Label lblCreatedOn;
    @FXML private Label lblUpdatedOn;

    public IndexController(){

    }
    @FXML
    public void initialize(){
        journalEntryCollection.addPropertyChangeListener(this);
        populateListView();
        listViewJournalEntryCollection.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> updateSelection(newValue));
    }
    private void updateSelection(JournalEntry selectedJournalEntry){
        if (selectedJournalEntry!=null) {
            resetLabels();
            titleTextDisplay.setText(selectedJournalEntry.getTitle());
            contentTextDisplay.setText(selectedJournalEntry.getBodyText());
            lblCreatedOn.setText(selectedJournalEntry.getEntryCreateDate() + " at "+ selectedJournalEntry.getEntryCreateTime());
        } else {
            titleTextDisplay.setText("");
            contentTextDisplay.setText("");
        }
    }
    @FXML protected void onBtnNewJournalEntryClick() {
        try{
            resetLabels();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/io/myjournal/newJournalEntry.fxml"));
            fxmlLoader.setControllerFactory(new ControllerFactory(journalEntryCollection));
            Parent root = fxmlLoader.load();
            Scene scene = new Scene(root);
            Stage journalEntryWindow = new Stage();
            journalEntryWindow.setTitle("New Journal Entry");
            journalEntryWindow.setScene(scene);
            journalEntryWindow.show();

        } catch (Exception e){
            e.printStackTrace();
        }

    }
    @FXML protected void onBtnDeleteJournalEntryClick() {
        JournalEntry listViewSelectedEntry = listViewJournalEntryCollection.getSelectionModel().getSelectedItem();
        if(listViewSelectedEntry!=null){
            int index = journalEntryCollection.getIndex(listViewSelectedEntry);
            journalEntryCollection.deleteEntry(index);
            resetLabels();
        }

    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("entryAdded")){
            JournalEntry journalEntry = (JournalEntry) evt.getNewValue();
            addToListView(journalEntry);
            lblStatus.setText("New Entry Added");
        }
        if(evt.getPropertyName().equals("entryDeleted")){
            int selectedIndex = listViewJournalEntryCollection.getSelectionModel().getSelectedIndex();
            listViewJournalEntryCollection.getItems().remove(selectedIndex);
            lblStatus.setText("Entry Deleted");
        }
    }
    private void resetStatusLabel() {
        lblStatus.setText("");
    }
    private void resetCreatedOnLabel() {
        lblCreatedOn.setText("");
    }
    private void resetUpdateOnLabel() {
        lblUpdatedOn.setText("");
    }
    private void resetLabels() {
        resetStatusLabel();
        resetCreatedOnLabel();
        resetUpdateOnLabel();
    }
    private void populateListView() {
        listViewJournalEntryCollection.getItems().addAll(journalEntryCollection.getJournalEntryList());
    }
    public void addToListView(JournalEntry journalEntry) {
        listViewJournalEntryCollection.getItems().add(journalEntry);
    }
}
