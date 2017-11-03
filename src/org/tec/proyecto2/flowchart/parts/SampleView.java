package org.tec.proyecto2.flowchart.parts;

import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
//import org.eclipse.jface.viewers.LabelProvider;
//import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
//import org.eclipse.swt.events.MouseWheelListener;
//import org.eclipse.swt.graphics.Image;
//import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
//import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
//import org.eclipse.swt.widgets.Shell;
//import org.eclipse.ui.IActionBars;
//import org.eclipse.ui.part.ViewPart;
//import org.eclipse.zest.core.viewers.AbstractZoomableViewer;
//import org.eclipse.zest.core.viewers.GraphViewer;
//import org.eclipse.zest.core.viewers.IGraphContentProvider;
//import org.eclipse.zest.core.viewers.IZoomableWorkbenchPart;
//import org.eclipse.zest.core.viewers.ZoomContributionViewItem;
//import org.eclipse.zest.layouts.LayoutAlgorithm;
//import org.eclipse.zest.layouts.LayoutStyles;
//import org.eclipse.zest.layouts.algorithms.SpringLayoutAlgorithm;
//import org.eclipse.zest.layouts.algorithms.TreeLayoutAlgorithm;
//import org.tec.proyecto2.flowchart.model.NodeModelContentProvider;
//import org.tec.proyecto2.flowchart.viewer.FlowLabelProvider;
//import org.tec.proyecto2.flowchart.viewer.NodeContentProvider;

//import de.vogella.zest.jface.model.NodeModelContentProvider;
//import de.vogella.zest.jface.zestviewer.ZestLabelProvider;
//import de.vogella.zest.jface.zestviewer.ZestNodeContentProvider;


public class SampleView {
	private Label myLabelInView;
	

	@PostConstruct
	public void createPartControl(Composite parent) {
		System.out.println("Enter in SampleE4View postConstruct");

		myLabelInView = new Label(parent, SWT.BORDER);
		myLabelInView.setText("This is a sample E4 view");

	}

	@Focus
	public void setFocus() {
		myLabelInView.setFocus();

	}

}


//public class SampleView extends ViewPart implements IZoomableWorkbenchPart {
//	static GraphViewer viewer = null;
//	static class MyContentProvider implements IGraphContentProvider {
//
//		public Object getSource(Object rel) {
//			if ("Rock2Paper".equals(rel)) {
//				return "Rock";
//			} else if ("Paper2Scissors".equals(rel)) {
//				return "Paper";
//			} else if ("Scissors2Rock".equals(rel)) {
//				return "Scissors";
//			}
//			return null;
//		}
//
//		public Object[] getElements(Object input) {
//			return new Object[] { "Rock2Paper", "Paper2Scissors", "Scissors2Rock" };
//		}
//
//		public Object getDestination(Object rel) {
//			if ("Rock2Paper".equals(rel)) {
//				return "Paper";
//			} else if ("Paper2Scissors".equals(rel)) {
//				return "Scissors";
//			} else if ("Scissors2Rock".equals(rel)) {
//				return "Rock";
//			}
//			return null;
//		}
//
////		public double getWeight(Object connection) {
////			return 0;
////		}
////
////		public void dispose() {
////		}
////
////		public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
////		}
//
//	}
//
//	static class MyLabelProvider extends LabelProvider {
//		
//		
////		final Image image = Display.getDefault().getSystemImage(SWT.ICON_WARNING);
//		final Image image = new Image(null,"C:/Users/Deiber/Desktop/iconos/return16.png");	
////    	final Image image = new Image(null, getClass().getResourceAsStream("icons/eclipse128.png"));
////    	final Image image = new ImageIcon("eclipse128.png").getImage();
////    	Image image = new ImageIcon(getClass().getResource("eclipse128.png")).getImage(); //
////		Image image = new Image(display,"C:/devEclipse_02/eclipse/plugins/org.eclipse.platform_2.0.2/eclipse_lg.gif"); 
//    	
//		public Image getImage(Object element) {
//
//			if (element.equals("Rock")){
//				final Image image = new Image(null,"C:/Users/Deiber/Desktop/Iconos/return24.png");
//				return image;
//			}
//			else if (element.equals("Rock") || element.equals("Paper") || element.equals("Scissors")) {
//				return image;
//			}
//			return null;
//		}
//
////		public String getText(Object element) {
////			return element.toString();
////		}
//
//	}
//
////	public static void test() {
////		Display d = new Display();
////		Shell shell = new Shell(d);
////		shell.setText("GraphJFaceSnippet2");
////		shell.setLayout(new FillLayout(SWT.VERTICAL));
////		shell.setSize(400, 400);
////		viewer = new GraphViewer(shell, SWT.NONE);
////		viewer.setContentProvider(new MyContentProvider());
////		viewer.setLabelProvider(new MyLabelProvider());
////		viewer.setLayoutAlgorithm(new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
////		// viewer.setLayoutAlgorithm(new
////		// TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING));
////		viewer.setInput(new Object());
////		shell.open();
////		while (!shell.isDisposed()) {
////			while (!d.readAndDispatch()) {
////				d.sleep();
////			}
////		}
////	}
////	}
//	@PostConstruct
//	public void createPartControl(Composite parent) {
//		viewer = new GraphViewer(parent, SWT.NONE);
////		viewer.setContentProvider(new MyContentProvider());
////		viewer.setLabelProvider(new MyLabelProvider());
////		viewer.setInput(new Object());
//		  viewer.setContentProvider(new NodeContentProvider());
//        viewer.setLabelProvider(new FlowLabelProvider());
//        NodeModelContentProvider model = new NodeModelContentProvider();
//        viewer.setInput(model.getNodes());
//		LayoutAlgorithm layout = setLayout();
//        viewer.setLayoutAlgorithm(layout, true);
//        viewer.applyLayout();
//		fillToolBar();
//		
//	}
//
//	private LayoutAlgorithm setLayout() {
//        LayoutAlgorithm layout;
////         layout = new SpringLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
//        layout = new TreeLayoutAlgorithm(LayoutStyles.NO_LAYOUT_NODE_RESIZING);
//        return layout;
//	}
//	
//	
//@Override
//public void setFocus() {
//}
//
//private void fillToolBar() {
//    ZoomContributionViewItem toolbarZoomContributionViewItem = new ZoomContributionViewItem(this);
//    IActionBars bars = getViewSite().getActionBars();
//    bars.getMenuManager().add(toolbarZoomContributionViewItem);
//}
//
//@Override
//public AbstractZoomableViewer getZoomableViewer() {
//    return viewer;
//}
//
//}