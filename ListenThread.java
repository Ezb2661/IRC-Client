package me.ezb2661.ircclient;

public class ListenThread extends Thread implements Runnable
{
    private final ServerConnection serverConnection;

    public ListenThread( ServerConnection serverConnection )
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
                System.out.println( this.serverConnection.readString( ) );
            }
            catch( Exception ex )
            {
                ex.printStackTrace( );
            }
        }
    }
}
