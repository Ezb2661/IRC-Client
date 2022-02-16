package me.ezb2661.ircclient;

import me.ezb2661.ircclient.gui.ChatFrame;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client
{
    public static String CLIENT_VERSION = "1.0";
    public static Client instance;

    public ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool( 1 );
    public ServerConnection connection;

    public Client( )
    {
    }

    public void connectToServer( String nickname, String serverHost, int serverPort ) throws IOException
    {
        if( this.connection != null && this.connection.isConnected )
        {
            Logger.logMessage( "[!] Already connected, disconnect before attempting to connect to another server.");
            return;
        }

        this.connection = new ServerConnection( serverHost, serverPort );
        ServerThread serverThread = new ServerThread( this.connection );
        serverThread.start( );

        String msg = this.connection.readString( );
        if( !msg.equals( "hello" ) )
        {
            Logger.logMessage( "[!] Did not not receive greeting from server." );
            this.connection.disconnect( );
            throw new IOException( );
        }

        this.connection.writeString( "nickname: " + nickname );
        ChatFrame.instance = new ChatFrame( );
    }
}