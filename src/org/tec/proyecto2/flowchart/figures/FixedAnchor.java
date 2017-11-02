package org.tec.proyecto2.flowchart.figures;

import org.eclipse.draw2d.*;
import org.eclipse.draw2d.geometry.*;

public class FixedAnchor extends AbstractConnectionAnchor {
	Point place;

	public FixedAnchor(IFigure owner) {
		super(owner);
	}

	public Point getLocation(Point loc) {
		Rectangle r = getOwner().getBounds();
		int x = r.x + place.x * r.width / 2;
		int y = r.y + place.y * r.height / 2;
		Point p = new PrecisionPoint(x, y);
		getOwner().translateToAbsolute(p);
		return p;
	}
}