package me.ezb2661.ircclient;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ServerConnection
{
    private final Socket socket;
    private InputStream inStream;
    private OutputStream outStream;

    public ServerConnection( String serverHost, int serverPort ) throws IOException
    {
        socket = new Socket( serverHost, serverPort );
        inStream = socket.getInputStream( );
        outStream = socket.getOutputStream( );
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

}
