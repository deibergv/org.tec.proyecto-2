package org.tec.proyecto2.flowchart.viewer;

import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.zest.core.viewers.EntityConnectionData;

import org.tec.proyecto2.flowchart.model.MyConnection;
import org.tec.proyecto2.flowchart.model.MyNode;

public class FlowLabelProvider extends LabelProvider {
    @Override
    public String getText(Object element) {
        if (element instanceof MyNode) {
            MyNode myNode = (MyNode) element;
            return myNode.getName();
        }
        // Not called with the IGraphEntityContentProvider
        if (element instanceof MyConnection) {
            MyConnection myConnection = (MyConnection) element;
            return myConnection.getLabel();
        }

        if (element instanceof EntityConnectionData) {
            EntityConnectionData test = (EntityConnectionData) element;
            return "";
        }
        throw new RuntimeException("Wrong type: "
                + element.getClass().toString());
    }
}