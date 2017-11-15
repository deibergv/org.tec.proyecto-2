package org.tec.proyecto2.flowchart.figures;
import org.eclipse.draw2d.IFigure;

public class FigureFactory {
	public static IFigure createTerminatorFigure() {
		return new TerminatorFigure();
	}

	public static IFigure createDecisionFigure() {
		return new DecisionFigure();
	}

	public static IFigure createProcessFigure() {
		return new ProcessFigure();
	}
	
	public static IFigure createExternalFigure() {
		return new ExternalFigure();
	}

	public static PathFigure createPathFigure() {
		return new PathFigure();
	}

	public static ChartFigure createChartFigure() {
		return new ChartFigure();
	}
}