package me.ezb2661.ircclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Client
{
    public static String CLIENT_VERSION = "1.0";
    public static Client instance;

    public ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool( 1 );

    private boolean isConnected = false;
    private Socket socket;
    private OutputStream outStream;
    private InputStream inStream;

    public Client( )
    {
    }

    public void connectToServer( String serverHost, int serverPort ) throws IOException
    {
        if( isConnected )
        {
            Logger.logMessage( "[!] Already connected, disconnect before attempting to connect to another server.");
            return;
        }

        this.socket = new Socket( serverHost, serverPort );
        this.outStream = socket.getOutputStream( );
        this.inStream = socket.getInputStream( );
        this.isConnected = true;
    }

    public void writeString( String message ) throws IOException
    {
        this.outStream.write( message.getBytes( StandardCharsets.UTF_8 ) );
    }

    public String readString( ) throws IOException
    {
        StringBuilder result = new StringBuilder( );
        byte[] incomingData = new byte[1024];
        int readBytes = this.inStream.read( incomingData );
        for( int i = 0; i < readBytes; i++ )
        {
            result.append( incomingData[i] );
        }
        return result.toString( );
    }

    public void disconnect( ) throws IOException
    {
        if( !this.isConnected )
        {
            Logger.logMessage( "[!] Not connected, connect to a server before trying to disconnect." );
            return;
        }

        try{ this.outStream.close( ); }

        finally
        {
            try{ this.inStream.close( ); }

            finally
            {
                this.socket.close( );
                this.isConnected = false;
            }
        }
    }
}