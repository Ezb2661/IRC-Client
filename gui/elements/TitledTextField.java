package me.ezb2661.ircclient.gui.elements;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TitledTextField extends JTextField
{
    private final TitledBorder border;

    public TitledTextField( String title, int x, int y, int width, int height, Color titleColor )
    {
        super( );
        this.setBounds( x, y, width, height );
        this.border = BorderFactory.createTitledBorder( title );
        border.setTitleColor( titleColor );
        this.setBorder( border );
    }

    public void setBorderTitleAndColor( String title, Color color )
    {
        this.border.setTitleColor( color );
        this.border.setTitle( title );
        this.repaint( );
    }
}
