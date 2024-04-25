package io.myjournal.models;

public class JournalEntry {
    private String title;
    private String bodyText;
    private boolean state;

    public JournalEntry (){
        this.title = "";
        this.bodyText = "";
        this.state = false;
    }
    public JournalEntry(String title, String bodyText){
        if(this.setTitle(title)) {
            this.state = true;
        }
        this.setBodyText(bodyText);
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
    }
    public String getBodyText() {
        return this.bodyText;
    }
    public boolean getState(){
        return this.state;
    }
}
