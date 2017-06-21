package org.lelecaps.anki;

/**
 *
 * @author gabriele
 */
public class Card {
    private String question;
    private String answer;
    private Box status;
    
    public Card(String question, String answer, String status){
        this.question = question;
        this.answer = answer;
        switch(status){
            case "ORANGE":
                this.status = Box.ORANGE;
                break;
            case "GREEN":
                this.status = Box.GREEN;
                break;
            case "RED":
            default:
                this.status = Box.RED;
        }
            
    }
    
    public void setQuestion(String question){
        this.question = question;
    }
    
    public void setAnswer(String answer){
        this.answer = answer;
    }
    
    public void setBox(Box status){
        this.status = status;
    }
    public String getQuestion(){
        return question;
    }
    
    public String getAnswer(){
        return answer;
    }
    
    public Box getBox(){
        return status;
    }
    
    @Override
    public String toString(){
        return question + "|" + answer + "|" + status.toString() + "\n";
    }
}
