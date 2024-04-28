package io.myjournal.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class JournalEntry {
    private String title;
    private String bodyText;
    private final LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private boolean state;
    private final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yy");
    private final DateTimeFormatter timeFormat = DateTimeFormatter.ofPattern("HH:mm");

    public JournalEntry (){
        this.title = "";
        this.bodyText = "";
        this.state = false;
        this.createdOn = LocalDateTime.now();
        this.updatedOn = this.createdOn;
    }
    public JournalEntry(String title, String bodyText){
        if(this.setTitle(title)) {
            this.state = true;
        }
        this.setBodyText(bodyText);
        this.createdOn = LocalDateTime.now();
        this.updatedOn = this.createdOn;
    }

    public boolean setTitle(String title){
        if(title == null || title.isEmpty()){
            return false;
        }
        this.title = title;
        this.state = true;
        return true;
    }
    public String getTitle(){
        return this.title;
    }
    public void setBodyText(String bodyText){
        this.bodyText = bodyText;
        setUpdatedOn();
    }
    public String getBodyText() {
        return this.bodyText;
    }
    public boolean getState(){
        return this.state;
    }
    public String getEntryCreateDate() {
        return this.createdOn.format(dateFormat);
    }
    public String getEntryUpdateDate() {
        return this.updatedOn.format(dateFormat);
    }
    public String getEntryCreateTime() {
        return this.createdOn.format(timeFormat);
    }
    public String getEntryUpdateTime() {
        return this.updatedOn.format(timeFormat);
    }
    private void setUpdatedOn() {
        this.updatedOn = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return this.getTitle();
    }
}
