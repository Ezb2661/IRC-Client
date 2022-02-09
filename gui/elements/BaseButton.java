package me.ezb2661.ircclient.gui.elements;

import javax.swing.*;

public class BaseButton extends JButton
{
    public BaseButton( String text, int x, int y, int width, int height )
    {
        super( );
        this.setText( text );
        this.setBounds( x, y, width, height );
    }
}
