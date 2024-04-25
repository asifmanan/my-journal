package io.myjournal.controllers;

import io.myjournal.models.JournalEntry;
import io.myjournal.models.JournalEntryCollection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class IndexController {
    @FXML private ListView<String> entriesListView;
    private JournalEntryCollection journalEntryCollection = new JournalEntryCollection();
    private ObservableList<String> entryTitles = FXCollections.observableArrayList();

//    @FXML private Label titleLabel;
//    @FXML private Label timeLabel;
//    @FXML Label datelabel;
    public IndexController(){
        journalEntryCollection.addPropertyChangeListener(evt -> {
            if("addEntry".equals(evt.getPropertyName())) {
                JournalEntry journalEntry = (JournalEntry) evt.getNewValue();
                entryTitles.add(journalEntry.getTitle());
            }
        });
        journalEntryCollection.getJournalEntryList().forEach(entry -> entryTitles.add(entry.getTitle()));
    }
    @FXML
    public void initialize(){
        entriesListView.setItems(entryTitles);
    }


}
