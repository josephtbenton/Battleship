package net;

/**
 * Created by Eric on 10/3/2015.
 */
public enum MessageType {
    HIT("H"), MISS("M"), TEXT("T"), ATTACK("A"), ERROR("E");

    String character;

    MessageType(String character) {
        this.character = character;
    }
    public String getCharacter(){
        return character;
    }
}
