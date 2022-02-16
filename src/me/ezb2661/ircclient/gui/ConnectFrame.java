package me.ezb2661.ircclient.gui;

import me.ezb2661.ircclient.Client;
import me.ezb2661.ircclient.gui.elements.BaseButton;
import me.ezb2661.ircclient.gui.elements.BaseFrame;
import me.ezb2661.ircclient.gui.elements.TitledTextField;

import javax.swing.*;
import java.io.IOException;

public class ConnectFrame extends BaseFrame
{
    public static ConnectFrame instance;

    private static final int FRAME_WIDTH = 400;
    private static final int FRAME_HEIGHT = 350;

    private TitledTextField serverHostField;
    private TitledTextField serverPortField;
    private TitledTextField nicknameField;

    public ConnectFrame( )
    {
        super( "IRC Client v" + Client.CLIENT_VERSION + " - Connect to Server", FRAME_WIDTH, FRAME_HEIGHT );
        this.setResizable( false );
    }

    @Override
    protected void initializeComponents( )
    {
        JPanel contentPanel = new JPanel( );
        contentPanel.setBounds( 0, 0, FRAME_WIDTH, FRAME_HEIGHT );
        contentPanel.setBackground( Colors.BACKGROUND );
        contentPanel.setLayout( null );

        this.serverHostField = new TitledTextField( "Server IP", (FRAME_WIDTH / 2) - 115, (FRAME_HEIGHT / 2) - 125, 210, 60, Colors.FOREGROUND );
        this.serverHostField.setBackground( Colors.BACKGROUND );
        this.serverHostField.setForeground( Colors.FOREGROUND );
        contentPanel.add( this.serverHostField );

        this.serverPortField = new TitledTextField( "Server Port", (FRAME_WIDTH / 2) - 115, (FRAME_HEIGHT / 2) - 65, 210, 60, Colors.FOREGROUND );
        this.serverPortField.setBackground( Colors.BACKGROUND );
        this.serverPortField.setForeground( Colors.FOREGROUND );
        contentPanel.add( this.serverPortField );

        this.nicknameField = new TitledTextField( "Nickname", (FRAME_WIDTH/2) - 115, (FRAME_HEIGHT / 2) - 5, 210, 60, Colors.FOREGROUND );
        this.nicknameField.setBackground( Colors.BACKGROUND );
        this.nicknameField.setForeground( Colors.FOREGROUND );
        contentPanel.add( this.nicknameField );

        BaseButton connectButton = new BaseButton( "Connect", (FRAME_WIDTH / 2) - 55, (FRAME_HEIGHT / 2) + 75, 100, 30 );
        connectButton.addActionListener( e ->
            Client.instance.threadPoolExecutor.execute( ( ) ->
            {
                try
                {
                    int serverPort = Integer.parseInt( serverPortField.getText( ) );
                    serverPortField.setBorderTitleAndColor( "Server Port", Colors.FOREGROUND );
                    Client.instance.connectToServer( nicknameField.getText( ), serverHostField.getText( ), serverPort );
                    serverHostField.setBorderTitleAndColor( "Server IP", Colors.FOREGROUND );
                    instance.setVisible( false );
                }
                catch( IllegalArgumentException ex )
                {
                    serverPortField.setBorderTitleAndColor( "Error: Invalid port.", Colors.ERROR );
                }
                catch( IOException ex )
                {
                    serverHostField.setBorderTitleAndColor( "Error: Could not connect to server.", Colors.ERROR );
                }
        } ) );
        connectButton.setBackground( Colors.BACKGROUND );
        connectButton.setForeground( Colors.FOREGROUND );
        contentPanel.add( connectButton );

        this.add( contentPanel );
    }
}
