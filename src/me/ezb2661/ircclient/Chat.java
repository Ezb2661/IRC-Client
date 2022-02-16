package me.ezb2661.ircclient;

import me.ezb2661.ircclient.gui.ChatFrame;

public class Chat
{
    public static void addChatMessage( String message )
    {
        ChatFrame.instance.addChatMessage( message );
    }
}
