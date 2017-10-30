package org.tec.proyecto2.flowchart.model;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

public class NodeModelContentProvider{
    private List<MyConnection> connections;
    private List<MyNode> nodes;

    
	final Image image = new Image(null,"C:/Users/Deiber/Desktop/iconos/return16.png");	
	
	public Image getImage(Object element) {

		if (element.equals("1")){
			final Image image = new Image(null,"C:/Users/Deiber/Desktop/Iconos/return24.png");
			return image;
		}
		return null;
	}
      
    public NodeModelContentProvider() {
    	
        // Image here a fancy DB access
        // now create a few nodes
        nodes = new ArrayList<MyNode>();
        MyNode node = new MyNode("1", "Hamburg");
        nodes.add(node);
        node = new MyNode("2", "Frankfurt");
        nodes.add(node);
        node = new MyNode("3", "Berlin");
        nodes.add(node);
        node = new MyNode("4", "Munich");
        nodes.add(node);
        node = new MyNode("5", "Eppelheim");
        nodes.add(node);
        node = new MyNode("6", "Ahrensboek");
        nodes.add(node);

        connections = new ArrayList<MyConnection>();
        MyConnection connect = new MyConnection("1", "1", nodes.get(0),
                nodes.get(1));
        connections.add(connect);
        connect = new MyConnection("2", "2", nodes.get(0), nodes.get(4));
        connections.add(connect);
        connect = new MyConnection("3", "3", nodes.get(2), nodes.get(1));
        connections.add(connect);
        connect = new MyConnection("4", "3", nodes.get(1), nodes.get(3));
        connections.add(connect);

        // Because we are lasy we save the info about the connections in the
        // nodes

        for (MyConnection connection : connections) {
            connection.getSource().getConnectedTo()
                    .add(connection.getDestination());
        }
    }

    public List<MyNode> getNodes() {
        return nodes;
    }

}