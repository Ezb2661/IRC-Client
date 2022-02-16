package me.ezb2661.ircclient.gui.elements;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;

public class TitledList<T> extends JList<T>
{
    private final DefaultListModel<T> listModel = new DefaultListModel<>( );

    public TitledList( String title, int x, int y, int width, int height, Color titleColor )
    {
        super( );
        TitledBorder border = BorderFactory.createTitledBorder( title );
        border.setTitleColor( titleColor );
        this.setBorder( border );
        this.setBounds( x, y, width, height );
        this.setModel( this.listModel );
    }

    public void addItem( T item )
    {
        this.listModel.addElement( item );
    }

    public void removeItem( T item )
    {
        this.listModel.removeElement( item );
    }
}
