package QuizServerSide;

import java.io.Serializable;

public class NetworkMessage implements Serializable {

    public NetworkMessage(byte firstByte)
    {
        this.firstByte = firstByte;
    }
    byte firstByte = 0x0;

    public byte getFirstByte() { return firstByte; }
}
