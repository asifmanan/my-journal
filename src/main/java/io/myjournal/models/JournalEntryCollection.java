package io.myjournal.models;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

public class JournalEntryCollection {
    private final List<JournalEntry> journalEntryList;
    private PropertyChangeSupport pcs = null;
    public JournalEntryCollection(){
        this.journalEntryList = new ArrayList<>();
        pcs = new PropertyChangeSupport(this);
    }
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        pcs.addPropertyChangeListener(listener);
    }
    public List<JournalEntry> getJournalEntryList(){
        return this.journalEntryList;
    }
    public int getSize() {
        return this.journalEntryList.size();
    }
    public void addEntry(JournalEntry journalEntry){
        if (journalEntry.getState()) {
            journalEntryList.add(journalEntry);
            int index = getSize() - 1;
            pcs.firePropertyChange("addEntry",null,journalEntry);
        }
    }
    public void addEntry(String title, String bodyText){
        JournalEntry journalEntry = new JournalEntry(title, bodyText);
        this.addEntry(journalEntry);
    }
}
