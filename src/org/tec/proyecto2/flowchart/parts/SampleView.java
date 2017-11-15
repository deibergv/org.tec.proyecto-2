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
import org.tec.proyecto2.flowchart.figures.ExternalFigure;
import org.tec.proyecto2.flowchart.figures.PathFigure;
import org.tec.proyecto2.flowchart.figures.ProcessFigure;
import org.tec.proyecto2.flowchart.figures.TerminatorFigure;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

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
		
		int x = 60;
		int y = 110;	
		int ytemp = 0;
		int xtemp = 0;
		
		List<ActivityFigure> list = flowchart.getChildren();
		
		ArrayList<Integer> types = new ArrayList<>();
		ArrayList<ArrayList<Integer>> direction = new ArrayList<>(); 
		
		TerminatorFigure start = new TerminatorFigure();
		start.setName("Start");
		start.setBounds(new Rectangle(60, 20, 80, 20));
		flowchart.add(start);
		
		
		types.add(6);
		
		ArrayList<Integer> ini = new ArrayList<>();
		ini.add(1);
		direction.add(ini);
		
		int counter = 1;
		Stack<Integer> stack = new Stack<>(); 
		
		for (String statement : array) {
			ArrayList<Integer> tempy = new ArrayList<>();
			String[] part = statement.split("~");
			
			if (part[0].compareTo("if") == 0) {
				if (part[1].compareTo("start") == 0) {
					stack.push(counter - 1);
					ytemp = y;
					
				} else if (part[1].compareTo("end") == 0){
					stack.push(counter - 1);
					xtemp = y;
					
				} else if (part[1].compareTo("else") == 0) {
					stack.push(counter);
					y = ytemp;
					x = 260;
					
				} else if (part[1].compareTo("endelse") == 0) {
					int elseind = stack.pop();
					int ifend = stack.pop();
					int ifstart = stack.pop();
					direction.remove(ifend);
					tempy.add(counter);
					direction.add(ifend,tempy);
					direction.get(ifstart).add(elseind);
					y = xtemp;
					x = 30;
					
				} else {
					create(part[1],y,x,0,flowchart);
					y+=110;
					types.add(0);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
				}
			} else if (part[0].compareTo("for") == 0) {
				if (part[1].compareTo("start") == 0) {
					stack.push(counter - 1);
					
				} else if (part[1].compareTo("end") == 0) {
					int ind = stack.pop();
					tempy.add(ind);
					direction.remove(counter-1);
					direction.add(counter-1,tempy);
					direction.get(ind).add(counter);
					
				} else {
					create(part[1],y,x,0,flowchart);
					y+=110;
					types.add(1);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
				}
				
			} else if (part[0].compareTo("while") == 0) {
				if (part[1].compareTo("start") == 0) {
					stack.push(counter - 1);
					
				} else if (part[1].compareTo("end") == 0) {
					int ind = stack.pop();
					tempy.add(ind);
					direction.remove(counter-1);
					direction.add(counter-1,tempy);
					direction.get(ind).add(counter);
					
				} else {
					create(part[1],y,x,0,flowchart);
					y+=110;
					types.add(2);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
				}
				
			} else if (part[0].compareTo("var") == 0) {
				create(part[1],y,x,1,flowchart);
				y+=110;
				types.add(5);
				tempy.add(counter+1);
				direction.add(tempy);
				counter += 1;
				
			} else if (part[0].compareTo("exp") == 0) {
				if (part[1].contains("System")) {
					create(part[1],y,x,1,flowchart);
					y+=110;
					types.add(3);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
					
				} else if (part[1].contains("()")) {
					create(part[1],y,x,2,flowchart);
					y+=110;
					types.add(4);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
					
				} else {
					create(part[1],y,x,1,flowchart);
					y+=110;
					types.add(3);
					tempy.add(counter+1);
					direction.add(tempy);
					counter += 1;
				}
			}
		}
		TerminatorFigure end = new TerminatorFigure();
		end.setName("End");
		end.setBounds(new Rectangle(x, y, 80, 20));
		flowchart.add(end);
		types.add(6);
		ArrayList<Integer> init = new ArrayList<>();
		direction.add(init);
		counter +=1;
		
		setLinks(types,direction,flowchart);
		canvas.update();
	}
	
	private static void create(String name, int y, int x, int type, ChartFigure flowchart) {
		
		if (type == 0) {
			DecisionFigure dec = new DecisionFigure();
			dec.setName(name);
			flowchart.add(dec);
			dec.setBounds(new Rectangle(x, y, name.length()*10+40, 60));
			new Dnd(dec);
		} else if (type == 1) {
			ProcessFigure dec = new ProcessFigure();
			dec.setName(name);
			flowchart.add(dec); 
			int large = name.length();
			if (large*10 > 200) {
				large = large*10 - large*10/4;
			} else {
				large = large*10;
			}
			dec.setBounds(new Rectangle(x, y, large, 40));
			new Dnd(dec);
		} else if (type == 2) {
			ExternalFigure dec = new ExternalFigure();
			dec.setName(name);
			flowchart.add(dec);
			int large = name.length();
			if (large*10 > 200) {
				large = large*15 - large*10/4;
			} else {
				large = large*15;
			}
			dec.setBounds(new Rectangle(x, y, large, 40));
			new Dnd(dec);
		}
	}
	
	/*types:
	 * if 0
	 * for 1
	 * while 2
	 * exp 3
	 * external 4
	 * var 5
	 * terminator 6
	 */
	
	private static void setLinks(ArrayList<Integer> types, ArrayList<ArrayList<Integer>> direction, ChartFigure flowchart) {
		
		List<ActivityFigure> list = flowchart.getChildren();
		
		for (int i = 0; i < list.size() ;i++) {
			PathFigure path1 = new PathFigure();
			PathFigure path2 = new PathFigure();
			
			int type = types.get(i);
			
			ActivityFigure figure = list.get(i);
			ArrayList<Integer> directions = direction.get(i);
			
			if (directions.get(0) > (list.size()-1) || directions.get(0) > (types.size()-1)) {
				return;
			}
						
			if (type == 0 || type == 1 || type == 2) {
				DecisionFigure temp = (DecisionFigure) figure;
				path1.setSourceAnchor(temp.yesAnchor);
				int tempType = types.get(directions.get(0));
				if (tempType == 0 || tempType == 1 || tempType == 2) {
					DecisionFigure otemp = (DecisionFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 3 || tempType == 5) {
					ProcessFigure otemp = (ProcessFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 4) {
					ExternalFigure otemp = (ExternalFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 6) {
					TerminatorFigure otemp = (TerminatorFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				}
				path2.setSourceAnchor(temp.noAnchor);
				tempType = types.get(directions.get(1));
				if (tempType == 0 || tempType == 1 || tempType == 2) {
					DecisionFigure otemp = (DecisionFigure) list.get(directions.get(1));
					path2.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 3 || tempType == 5) {
					ProcessFigure otemp = (ProcessFigure) list.get(directions.get(1));
					path2.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 4) {
					ExternalFigure otemp = (ExternalFigure) list.get(directions.get(1));
					path2.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 6) {
					TerminatorFigure otemp = (TerminatorFigure) list.get(directions.get(1));
					path2.setTargetAnchor(otemp.inAnchor);
				}
				flowchart.add(path1);
				flowchart.add(path2);
			} else if (type == 3 || type == 5) {
				ProcessFigure temp = (ProcessFigure) figure;
				path1.setSourceAnchor(temp.outAnchor);
				int tempType = types.get(directions.get(0));
				if (tempType == 0 || tempType == 1 || tempType == 2) {
					DecisionFigure otemp = (DecisionFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 3 || tempType == 5) {
					ProcessFigure otemp = (ProcessFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 4) {
					ExternalFigure otemp = (ExternalFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 6) {
					TerminatorFigure otemp = (TerminatorFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				}
				flowchart.add(path1);
			} else if (type == 4) {
				ExternalFigure temp = (ExternalFigure) figure;
				path1.setSourceAnchor(temp.outAnchor);
				int tempType = types.get(directions.get(0));
				if (tempType == 0 || tempType == 1 || tempType == 2) {
					DecisionFigure otemp = (DecisionFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 3 || tempType == 5) {
					ProcessFigure otemp = (ProcessFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 4) {
					ExternalFigure otemp = (ExternalFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 6) {
					TerminatorFigure otemp = (TerminatorFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				}
				flowchart.add(path1);
			} else if (type == 6) {
				TerminatorFigure temp = (TerminatorFigure) figure;
				path1.setSourceAnchor(temp.outAnchor);
				int tempType = types.get(directions.get(0));
				if (tempType == 0 ||tempType == 1 || tempType == 2) {
					DecisionFigure otemp = (DecisionFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 3 || tempType == 5) {
					ProcessFigure otemp = (ProcessFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 4) {
					ExternalFigure otemp = (ExternalFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				} else if (tempType == 6) {
					TerminatorFigure otemp = (TerminatorFigure) list.get(directions.get(0));
					path1.setTargetAnchor(otemp.inAnchor);
				}
				flowchart.add(path1);
			} else {
				return;
			}
			
			
			
			
			
		}
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
