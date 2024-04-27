package io.myjournal.controllers;

import io.myjournal.models.JournalEntryCollection;
import javafx.util.Callback;


public class ControllerFactory implements Callback<Class<?>, Object> {
    private JournalEntryCollection journalEntryCollection;
    public ControllerFactory(){

    }
    public ControllerFactory(JournalEntryCollection journalEntryCollection){
        this.journalEntryCollection = journalEntryCollection;
    }

    @Override
    public Object call(Class<?> type) {
        if(type == NewJournalEntryController.class){
            if (journalEntryCollection!=null) {
                return new NewJournalEntryController(journalEntryCollection);
            } else {
                // Handle the case where the required dependency is not available
                throw new IllegalStateException("NewJournalEntryController cannot be instantiated without a JournalEntryCollection.");
            }
        }
        try{
            //The default behaviour for instantiating controller.
            return type.getDeclaredConstructor().newInstance();
        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
