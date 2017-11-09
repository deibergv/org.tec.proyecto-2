package org.tec.proyecto2.flowchart.handlers;

import static org.tec.proyecto2.flowchart.handlers.BreakpointActionDelegate.thread;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugException;

public class StepOverHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			thread.stepOver();
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}