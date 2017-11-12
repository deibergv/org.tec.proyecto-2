package org.tec.proyecto2.flowchart.figures;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.*;

public class ProcessFigure extends ActivityFigure {

	public FixedAnchor inAnchor;
	public FixedAnchor outAnchor;

	public ProcessFigure() {		//Asignacion de Color
		setForegroundColor(ColorConstants.white);
		inAnchor = new FixedAnchor(this);
		inAnchor.place = new Point(1, 0);
		targetAnchors.put("in_proc", inAnchor);
		outAnchor = new FixedAnchor(this);
		outAnchor.place = new Point(1, 2);
		sourceAnchors.put("out_proc", outAnchor);
	}

	public void paintFigure(Graphics g) {
		Rectangle r = bounds;
		g.drawText(message, r.x + r.width / 6, r.y + r.height / 4);
		g.drawRectangle(r.x, r.y, r.width - 1, r.height - 1);
	}
}
