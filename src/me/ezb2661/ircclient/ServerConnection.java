package me.ezb2661.ircclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerConnection
{
    private final Socket socket;
    private final InputStream inStream;
    private final OutputStream outStream;

    public boolean isConnected = false;

    public ServerConnection( String serverHost, int serverPort ) throws IOException
    {
        socket = new Socket( serverHost, serverPort );
        inStream = socket.getInputStream( );
        outStream = socket.getOutputStream( );
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
            result.append( Character.toChars( incomingData[i] ) );
        }
        return result.toString( );
    }

    public String getServerIP( )
    {
        return this.socket.getInetAddress( ).getHostAddress( );
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
