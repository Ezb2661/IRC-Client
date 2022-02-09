package me.ezb2661.ircclient;

import me.ezb2661.ircclient.gui.ConnectFrame;
import me.ezb2661.ircclient.gui.GUIManager;

public class ClientMain
{
    public static void main( String[] args )
    {
        GUIManager.setup( );
        Client.instance = new Client( );
        ConnectFrame.instance = new ConnectFrame( );
    }
}
