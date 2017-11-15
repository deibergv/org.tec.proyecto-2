package org.tec.proyecto2.flowchart.handlers;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.DebugException;
import org.eclipse.debug.core.model.IDebugTarget;
import org.eclipse.debug.core.model.ISourceLocator;
import org.eclipse.debug.core.model.IStackFrame;
import org.eclipse.jdt.core.dom.Message;
import org.eclipse.jdt.debug.core.IJavaBreakpoint;
import org.eclipse.jdt.debug.core.IJavaBreakpointListener;
import org.eclipse.jdt.debug.core.IJavaDebugTarget;
import org.eclipse.jdt.debug.core.IJavaLineBreakpoint;
import org.eclipse.jdt.debug.core.IJavaThread;
import org.eclipse.jdt.debug.core.IJavaType;
import org.eclipse.jdt.internal.ui.IResourceLocator;
import org.eclipse.ui.actions.ActionDelegate;

public class BreakpointActionDelegate extends ActionDelegate implements IJavaBreakpointListener {
	
	public static IJavaThread thread;

	@Override
	public void addingBreakpoint(IJavaDebugTarget arg0, IJavaBreakpoint arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointHasCompilationErrors(IJavaLineBreakpoint arg0, Message[] arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointHasRuntimeException(IJavaLineBreakpoint arg0, DebugException arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int breakpointHit(IJavaThread thread, IJavaBreakpoint breakpoint) {
		
		this.thread = thread;
	    IStackFrame topStackFrame;
		try {
			topStackFrame = thread.getTopStackFrame();
			int debuggedLineNumber = topStackFrame.getLineNumber();
			String name = thread.getName();
//			breakpoint.getMarker().getResource().
			String resource = topStackFrame.getLaunch().getSourceLocator().getSourceElement(thread.getTopStackFrame()).toString();
			SourceAnalycer analycer = new SourceAnalycer(resource,name);
			analycer.encoder();
			
		} catch (DebugException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return 0;
	}
	
	@Override
	public void breakpointInstalled(IJavaDebugTarget arg0, IJavaBreakpoint arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void breakpointRemoved(IJavaDebugTarget arg0, IJavaBreakpoint arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int installingBreakpoint(IJavaDebugTarget arg0, IJavaBreakpoint arg1, IJavaType arg2) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
