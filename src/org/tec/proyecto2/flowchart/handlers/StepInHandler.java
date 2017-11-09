package org.tec.proyecto2.flowchart.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugException;

import static org.tec.proyecto2.flowchart.handlers.DebugHandler.input;
import static org.tec.proyecto2.flowchart.parts.SampleView.figureGen;
import static org.tec.proyecto2.flowchart.handlers.BreakpointActionDelegate.thread;

import java.util.ArrayList;

public class StepInHandler extends AbstractHandler {
	
	ArrayList<String> lista = new ArrayList<>();

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			thread.stepInto();
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}