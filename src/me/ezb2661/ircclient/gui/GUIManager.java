package me.ezb2661.ircclient.gui;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;

public class GUIManager
{
    private static void setLookAndFeel( String lookAndFeelName )
    {
        try
        {
            for( UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels( ) )
            {
                if( info.getName( ).equals( lookAndFeelName ) )
                {
                    UIManager.setLookAndFeel( info.getClassName( ) );
                }
            }
        }
        catch( Exception ex )
        {
            System.out.println( "[!] Unable to set " + lookAndFeelName + " look and feel." );
        }
    }

    public static void setup( )
    {
        setLookAndFeel( "Nimbus" );
        BorderUIResource nimbusBorder = (BorderUIResource) UIManager.getLookAndFeelDefaults( ).get( "TitledBorder.border" );

    }
}
