package org.tec.proyecto2.flowchart.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
//import org.eclipse.jface.dialogs.MessageDialog;
//import org.eclipse.ui.IWorkbenchWindow;
//import org.eclipse.ui.handlers.HandlerUtil;
import static org.tec.proyecto2.flowchart.handlers.DebugHandler.input;
import static org.tec.proyecto2.flowchart.parts.SampleView.figureGen;

import java.util.ArrayList;

public class StepInHandler extends AbstractHandler {
	
	ArrayList<String> lista = new ArrayList<>();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		lista = input.get(1);
		figureGen(lista);
		return null;
	}
}