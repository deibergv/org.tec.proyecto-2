package org.tec.proyecto2.flowchart.parts;

import javax.annotation.PostConstruct;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

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