package org.tec.proyecto2.flowchart.parts;

import javax.annotation.PostConstruct;

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
import org.eclipse.swt.widgets.*;
import org.tec.proyecto2.flowchart.figures.*;

public class SampleView {

	@PostConstruct
	public void createPartControl(Composite parent) {

		 ScrolledComposite ScrollComposite = new ScrolledComposite(parent, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
		 ScrollComposite.setExpandHorizontal(true);
		 ScrollComposite.setExpandVertical(true);
		 ScrollComposite.setMinWidth(500);
		 ScrollComposite.setMinHeight(10000);
		
		Canvas canvas = new Canvas(ScrollComposite, SWT.NONE);
		LightweightSystem lws = new LightweightSystem(canvas);
		ChartFigure flowchart = new ChartFigure();
		lws.setContents(flowchart);

		ScrollComposite.setContent(canvas);
		
		
		// fillToolBar();
		// createToolbar(parent);

		TerminatorFigure start = new TerminatorFigure();
		start.setName("Start");
		start.setBounds(new Rectangle(40, 20, 80, 20));
		DecisionFigure dec = new DecisionFigure();
		dec.setName("Should I?");
		dec.setBounds(new Rectangle(30, 80, 100, 60));
		ProcessFigure proc = new ProcessFigure();
		proc.setName("Do it!");
		proc.setBounds(new Rectangle(40, 160, 80, 40));
		TerminatorFigure stop = new TerminatorFigure();
		stop.setName("End");
		stop.setBounds(new Rectangle(40, 300, 80, 20));

		PathFigure path1 = new PathFigure();
		path1.setSourceAnchor(start.outAnchor);
		path1.setTargetAnchor(dec.inAnchor);
		PathFigure path2 = new PathFigure();
		path2.setSourceAnchor(dec.yesAnchor);
		path2.setTargetAnchor(proc.inAnchor);
		PathFigure path3 = new PathFigure();
		path3.setSourceAnchor(dec.noAnchor);
		path3.setTargetAnchor(stop.inAnchor);
		PathFigure path4 = new PathFigure();
		path4.setSourceAnchor(proc.outAnchor);
		path4.setTargetAnchor(stop.inAnchor);

		flowchart.add(start);
		flowchart.add(dec);
		flowchart.add(proc);
		flowchart.add(stop);
		flowchart.add(path1);
		flowchart.add(path2);
		flowchart.add(path3);
		flowchart.add(path4);

		new Dnd(start);
		new Dnd(proc);
		new Dnd(dec); // PERMITE MOVIMIENTO
		new Dnd(stop);

	}

	// private void fillToolBar() {
	// ZoomContributionViewItem toolbarZoomContributionViewItem = new
	// ZoomContributionViewItem(this);
	// IActionBars bars = getViewSite().getActionBars();
	// bars.getMenuManager().add(toolbarZoomContributionViewItem);
	// }
	//
	// @Override
	// public AbstractZoomableViewer getZoomableViewer() {
	// return viewer;
	// }

	class Dnd extends MouseMotionListener.Stub implements MouseListener {
		// PERMITE MOVIMIENTO y otras cosas
		public Dnd(IFigure figure) {
			figure.addMouseMotionListener(this);
			figure.addMouseListener(this);
		}

		Point start;

		public void mouseReleased(MouseEvent e) {
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mouseDoubleClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			start = e.getLocation();
		}

		public void mouseDragged(MouseEvent e) {
			Point p = e.getLocation();
			Dimension d = p.getDifference(start);
			start = p;
			Figure f = ((Figure) e.getSource());
			f.setBounds(f.getBounds().getTranslated(d.width, d.height));
		}
	}

	public void createToolbar(Composite parent) {
		ToolBar toolBar = new ToolBar(parent, SWT.BORDER | SWT.FLAT);

		ToolItem item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("TEST");
		item = new ToolItem(toolBar, SWT.PUSH);
		item.setText("Boton prueba");
		// new ToolItem(toolBar, SWT.SEPARATOR);
		// item = new ToolItem(toolBar, SWT.CHECK);
		// item.setText("Check One");
		// item = new ToolItem(toolBar, SWT.CHECK);
		// item.setText("Check Two");
		// new ToolItem(toolBar, SWT.SEPARATOR);
		// item = new ToolItem(toolBar, SWT.RADIO);
		// item.setText("Radio One");
		// item = new ToolItem(toolBar, SWT.RADIO);
		// item.setText("Radio Two");
		// new ToolItem(toolBar, SWT.SEPARATOR);
		// item = new ToolItem(toolBar, SWT.DROP_DOWN);
		// item.setText("Dropdown One");
		// item = new ToolItem(toolBar, SWT.DROP_DOWN);
		// item.setText("Dropdown Two");
	}
}

