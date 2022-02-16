package me.ezb2661.ircclient;

import me.ezb2661.ircclient.gui.ChatFrame;

import java.io.IOException;

public class ServerThread extends Thread implements Runnable
{
    private final ServerConnection serverConnection;

    public ServerThread( ServerConnection serverConnection )
    {
        this.serverConnection = serverConnection;
    }

    @Override
    public void run( )
    {
        while( true )
        {
            try
            {
                String message = serverConnection.readString( );
                Chat.addChatMessage( message );
            }
            catch( IOException e )
            {
                e.printStackTrace( );
                try
                {
                    this.serverConnection.disconnect( );
                    ChatFrame.instance.setVisible( false );
                    return;
                }
                catch( IOException ex )
                {
                    ex.printStackTrace();
                    return;
                }
            }
        }
    }
}
