package org.tec.proyecto2.flowchart.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IStackFrame;

import static org.tec.proyecto2.flowchart.handlers.DebugHandler.input;
import static org.tec.proyecto2.flowchart.parts.SampleView.figureGen;
import static org.tec.proyecto2.flowchart.handlers.BreakpointActionDelegate.thread;

import java.util.ArrayList;

public class StepInHandler extends AbstractHandler {
	
	ArrayList<String> lista = new ArrayList<>();
	IStackFrame[] stackFrame = null;

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		try {
			if (thread.canStepInto()) {
				thread.stepInto();
				thread.suspend();
				stackFrame = (IStackFrame[])thread.getStackFrames();
				thread.resume();
				System.out.println(thread.getFrameCount());
				System.out.println(stackFrame[0].getLaunch().getSourceLocator().getSourceElement(stackFrame[0]).toString());
			} else {
				thread.stepOver();
			}
		} catch (DebugException e) {
			System.out.println("mal");
		}
		return null;
	}
}