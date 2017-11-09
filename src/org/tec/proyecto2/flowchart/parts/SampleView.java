package org.tec.proyecto2.flowchart.parts;

import javax.annotation.PostConstruct;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.LightweightSystem;
import org.eclipse.draw2d.MouseEvent;
import org.eclipse.draw2d.MouseListener;
import org.eclipse.draw2d.MouseMotionListener;
import org.eclipse.draw2d.geometry.Dimension;
import org.eclipse.draw2d.geometry.Point;
import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.tec.proyecto2.flowchart.figures.ActivityFigure;
import org.tec.proyecto2.flowchart.figures.ChartFigure;
import org.tec.proyecto2.flowchart.figures.DecisionFigure;
import org.tec.proyecto2.flowchart.figures.PathFigure;
import org.tec.proyecto2.flowchart.figures.ProcessFigure;
import org.tec.proyecto2.flowchart.figures.TerminatorFigure;

import java.util.ArrayList;

public class SampleView {
	
	private static ScrolledComposite ScrollComposite;

	@PostConstruct
	public void createPartControl(Composite parent) {

		ScrollComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		ScrollComposite.setExpandHorizontal(true);
		ScrollComposite.setExpandVertical(true);
		ScrollComposite.setMinWidth(500);
		ScrollComposite.setMinHeight(10000);
	}	
	
	public static void figureGen(ArrayList<String> array) {
		
		Canvas canvas = new Canvas(ScrollComposite, SWT.NONE);
		ScrollComposite.setContent(canvas);
		
		LightweightSystem lws = new LightweightSystem(canvas);	
		
		ChartFigure flowchart = new ChartFigure();
		lws.setContents(flowchart);
//		flowchart.
		
		TerminatorFigure start = new TerminatorFigure();
		start.setName("Start");
		start.setBounds(new Rectangle(40, 20, 80, 20));
		flowchart.add(start);
		
		int y = 110;
		ActivityFigure temp = null;
		int type = 0;

		for (String statement : array) {
			String[] part = statement.split("~");
			
			if (part[0].compareTo("if") == 0) {
				DecisionFigure dec = new DecisionFigure();
				dec.setName("if");
				flowchart.add(dec);
				dec.setBounds(new Rectangle(30, y, 100, 60));
				new Dnd(dec);
				PathFigure path1 = new PathFigure();
				PathFigure path2 = new PathFigure();
				if (temp == null) {
					path1.setSourceAnchor(start.outAnchor);
					path1.setTargetAnchor(dec.inAnchor);
					temp = dec;
					type = 1;
				} else {
					if (type == 1) {
						DecisionFigure otemp = (DecisionFigure)temp;
						path1.setSourceAnchor(otemp.yesAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						temp = dec;
					} else if (type == 2) {
						ProcessFigure otemp = (ProcessFigure)temp;
						path1.setSourceAnchor(otemp.outAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						temp = dec;
						type = 1;
					}
				}
				flowchart.add(path1);
				
			} else if (part[0].compareTo("for") == 0) {
				DecisionFigure dec = new DecisionFigure();
				dec.setName("for");
				flowchart.add(dec);
				dec.setBounds(new Rectangle(30, y, 100, 60)); //Se pueden hacer en una sola, if, for y while
				new Dnd(dec);
				PathFigure path1 = new PathFigure();
				PathFigure path2 = new PathFigure();
				if (temp == null) {
					path1.setSourceAnchor(start.outAnchor);
					path1.setTargetAnchor(dec.inAnchor);
					path2.setSourceAnchor(dec.noAnchor);
					path2.setTargetAnchor(dec.inAnchor);
					temp = dec;
					type = 1;
				} else {
					if (type == 1) {
						DecisionFigure otemp = (DecisionFigure)temp;
						path1.setSourceAnchor(otemp.yesAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						path2.setSourceAnchor(dec.noAnchor);
						path2.setTargetAnchor(dec.inAnchor);
						temp = dec;
					} else if (type == 2) {
						ProcessFigure otemp = (ProcessFigure)temp;
						path1.setSourceAnchor(otemp.outAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						path2.setSourceAnchor(dec.noAnchor);
						path2.setTargetAnchor(dec.inAnchor);
						temp = dec;
						type = 1;
					}
				}
				flowchart.add(path1);
				flowchart.add(path2);
				
			} else if (part[0].compareTo("while") == 0) {
				DecisionFigure dec = new DecisionFigure();
				dec.setName("while");
				flowchart.add(dec);
				dec.setBounds(new Rectangle(30, y, 100, 60));
				new Dnd(dec);
				PathFigure path1 = new PathFigure();
				PathFigure path2 = new PathFigure();
				if (temp == null) {
					path1.setSourceAnchor(start.outAnchor);
					path1.setTargetAnchor(dec.inAnchor);
					path2.setSourceAnchor(dec.noAnchor);
					path2.setTargetAnchor(dec.inAnchor);
					temp = dec;
					type = 1;
				} else {
					if (type == 1) {
						DecisionFigure otemp = (DecisionFigure)temp;
						path1.setSourceAnchor(otemp.yesAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						path2.setSourceAnchor(dec.noAnchor);
						path2.setTargetAnchor(dec.inAnchor);
						temp = dec;
					} else if (type == 2) {
						ProcessFigure otemp = (ProcessFigure)temp;
						path1.setSourceAnchor(otemp.outAnchor);
						path1.setTargetAnchor(dec.inAnchor);
						path2.setSourceAnchor(dec.noAnchor);
						path2.setTargetAnchor(dec.inAnchor);
						temp = dec;
						type = 1;
					}
				}
				flowchart.add(path1);
				flowchart.add(path2);
				
			} else if (part[0].compareTo("var") == 0) {
				ProcessFigure dec = new ProcessFigure();
				dec.setName("var");
				flowchart.add(dec);
				dec.setBounds(new Rectangle(40, y, 80, 40));
				new Dnd(dec);
				PathFigure path = new PathFigure();
				if (temp == null) {
					path.setSourceAnchor(start.outAnchor);
					path.setTargetAnchor(dec.inAnchor);
					temp = dec;
					type = 2;
				} else {
					if (type == 1) {
						DecisionFigure otemp = (DecisionFigure)temp;
						path.setSourceAnchor(otemp.yesAnchor);
						path.setTargetAnchor(dec.inAnchor);
						temp = dec;
						type =2;
					} else if (type == 2) {
						ProcessFigure otemp = (ProcessFigure)temp;
						path.setSourceAnchor(otemp.outAnchor);
						path.setTargetAnchor(dec.inAnchor);
						temp = dec;
					}
				}
				flowchart.add(path);
				
			} else if (part[0].compareTo("exp") == 0) {
				ProcessFigure dec = new ProcessFigure();
				dec.setName("exp");
				flowchart.add(dec);
				dec.setBounds(new Rectangle(40, y, 80, 40));
				new Dnd(dec);
				PathFigure path = new PathFigure();
				if (temp == null) {
					path.setSourceAnchor(start.outAnchor);
					path.setTargetAnchor(dec.inAnchor);
					temp = dec;
					type = 2;
				} else {
					if (type == 1) {
						DecisionFigure otemp = (DecisionFigure)temp;
						path.setSourceAnchor(otemp.yesAnchor);
						path.setTargetAnchor(dec.inAnchor);
						temp = dec;
						type =2;
					} else if (type == 2) {
						ProcessFigure otemp = (ProcessFigure)temp;
						path.setSourceAnchor(otemp.outAnchor);
						path.setTargetAnchor(dec.inAnchor);
						temp = dec;
					}
				}
				flowchart.add(path);
				
			}
			y+=110;
		}
        canvas.update();
	}

	static class Dnd extends MouseMotionListener.Stub implements MouseListener {
		// PERMITE MOVIMIENTO y otras cosas
		public Dnd(IFigure figure) {
			figure.addMouseMotionListener(this);
			figure.addMouseListener(this);
		}

		Point start;

		@Override
		public void mousePressed(MouseEvent e) {
			start = e.getLocation();
		}

		@Override
		public void mouseDragged(MouseEvent e) {
			Point p = e.getLocation();
			Dimension d = p.getDifference(start);
			start = p;
			Figure f = ((Figure) e.getSource());
			f.setBounds(f.getBounds().getTranslated(d.width, d.height));
		}

		@Override
		public void mouseReleased(MouseEvent me) {
		}

		@Override
		public void mouseDoubleClicked(MouseEvent me) {
		}
	}
}
