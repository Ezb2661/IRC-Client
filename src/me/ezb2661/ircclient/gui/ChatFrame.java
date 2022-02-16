package me.ezb2661.ircclient.gui;

import me.ezb2661.ircclient.Client;
import me.ezb2661.ircclient.gui.elements.BaseFrame;
import me.ezb2661.ircclient.gui.elements.TitledList;
import me.ezb2661.ircclient.gui.elements.TitledTextArea;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class ChatFrame extends BaseFrame
{
    public static ChatFrame instance;

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 800;

    private TitledTextArea chatTextArea;
    private TitledList<String> connectedClientsList;

    public ChatFrame( )
    {
        super( "IRC Client v" + Client.CLIENT_VERSION + " - Connected to: " + Client.instance.connection.getServerIP( ), FRAME_WIDTH, FRAME_HEIGHT );
        this.setResizable( false );
    }

    public void addConnectedClient( String displayText )
    {
        this.connectedClientsList.addItem( displayText );
    }

    public void removeConnectedClient( String displayText )
    {
        this.connectedClientsList.removeItem( displayText );
    }

    @Override
    public void initializeComponents( )
    {
        JPanel contentPanel = new JPanel( );
        contentPanel.setBounds( 0, 0, FRAME_WIDTH, FRAME_HEIGHT );
        contentPanel.setBackground( Colors.BACKGROUND );
        contentPanel.setLayout( null );

        chatTextArea = new TitledTextArea( "Chat", 0, 5, FRAME_WIDTH - 200, FRAME_HEIGHT - 80, Colors.FOREGROUND );
        chatTextArea.setBackground( Colors.BACKGROUND );
        chatTextArea.setEditable( false );
        chatTextArea.setForeground( Colors.FOREGROUND );
        contentPanel.add(chatTextArea);

        JTextField messageField = new JTextField( );
        messageField.setBackground( Colors.BACKGROUND );
        messageField.setForeground( Colors.FOREGROUND );
        messageField.setBounds( 5, FRAME_HEIGHT - 75, FRAME_WIDTH - 25, 30 );
        messageField.addKeyListener( new KeyListener( )
                                     {
                                         @Override
                                         public void keyTyped( KeyEvent e )
                                         {
                                         }

                                         @Override
                                         public void keyPressed( KeyEvent e )
                                         {
                                             if( e.getKeyCode( ) == 10 )
                                             {
                                                 Client.instance.threadPoolExecutor.execute( () -> {
                                                     try
                                                     {
                                                         Client.instance.connection.writeString( messageField.getText( ) );
                                                         messageField.setText( "" );
                                                     }
                                                     catch( IOException ex )
                                                     {
                                                        addChatMessage( "[!] Couldn't send message." );
                                                     }
                                                 } );
                                             }
                                         }

                                         @Override
                                         public void keyReleased( KeyEvent e )
                                         {
                                         }
                                     }
        );
        contentPanel.add( messageField );

        connectedClientsList = new TitledList<>( "Connected Clients", chatTextArea.getWidth( ) + 10, 0, 174, FRAME_HEIGHT - 75, Colors.FOREGROUND );
        connectedClientsList.setBackground( Colors.BACKGROUND );
        connectedClientsList.setForeground( Colors.FOREGROUND );
        contentPanel.add( connectedClientsList );

        this.add( contentPanel );
    }

    public void addChatMessage( String msg )
    {
        this.chatTextArea.setText( this.chatTextArea.getText( ) + msg + "\n" );
    }
}
