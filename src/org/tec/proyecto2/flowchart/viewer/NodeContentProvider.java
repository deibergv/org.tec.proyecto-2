package org.tec.proyecto2.flowchart.viewer;

import org.eclipse.jface.viewers.ArrayContentProvider;
import org.eclipse.zest.core.viewers.IGraphEntityContentProvider;

import org.tec.proyecto2.flowchart.model.MyNode;

public class NodeContentProvider extends ArrayContentProvider  implements IGraphEntityContentProvider {

    @Override
    public Object[] getConnectedTo(Object entity) {
        if (entity instanceof MyNode) {
            MyNode node = (MyNode) entity;
            return node.getConnectedTo().toArray();
        }
        throw new RuntimeException("Type not supported");
    }
}