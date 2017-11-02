package org.tec.proyecto2.flowchart.figures;
import org.eclipse.draw2d.*;

public class PathFigure extends PolylineConnection
{
  public PathFigure()
  {
	setForegroundColor(ColorConstants.white);		//Asignacion de Color
    setTargetDecoration(new PolylineDecoration());
    setConnectionRouter(new ManhattanConnectionRouter());
  }
}
