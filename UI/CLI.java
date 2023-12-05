package com.company.UI;
import com.company.Board;
import com.company.MessageCallback;

import java.util.*;

public class CLI {
    private MessageCallback messageCallback;

    public void print(String s){
        messageCallback.send(s);
    }

    public CLI(){
        messageCallback = (message) -> System.out.println(message);
    }

    public MessageCallback getMessageCallback() {return this.messageCallback;}


}
