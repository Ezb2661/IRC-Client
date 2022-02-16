package me.ezb2661.ircclient.gui.elements;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class TitledTextArea extends JTextArea
{
    public TitledTextArea( String title, int x, int y, int width, int height, Color titleColor )
    {
        super( );
        TitledBorder border = BorderFactory.createTitledBorder( title );
        border.setTitleColor( titleColor );
        this.setBorder( border );
        super.setBounds( x, y, width, height );
    }
}
