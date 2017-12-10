package recuperation_donnees;

public class NOAACommunicationException extends Exception {
    public NOAACommunicationException(String s){
        super(s);
    }

    public NOAACommunicationException(Exception e){
        super(e);
    }

    public NOAACommunicationException(String s, Exception e) {
        super(s, e);
    }
}
