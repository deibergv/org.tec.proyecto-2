package org.tec.proyecto2.flowchart;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.tec.proyecto2.flowchart.handlers.BreakpointActionDelegate;

import static org.eclipse.jdt.debug.core.JDIDebugModel.addJavaBreakpointListener;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		addJavaBreakpointListener(new BreakpointActionDelegate());
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
