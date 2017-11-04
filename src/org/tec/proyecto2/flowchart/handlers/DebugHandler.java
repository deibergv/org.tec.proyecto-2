package org.tec.proyecto2.flowchart.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.debug.core.JDIDebugModel;
import org.eclipse.jdt.internal.debug.core.model.*;
import java.util.ArrayList;
import java.util.List;

public class DebugHandler extends AbstractHandler {

    private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";
    private static ArrayList<ArrayList<String>> input = new ArrayList<>();
    
    @Override
    public Object execute(ExecutionEvent event) throws ExecutionException {
        IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        // Get all projects in the workspace
        IProject[] projects = root.getProjects();
        // Loop over all projects
        for (IProject project : projects) {
            try {
                if (project.isNatureEnabled(JDT_NATURE)) {
                    analyseMethods(project);
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private void analyseMethods(IProject project) throws JavaModelException {
        IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
        // parse(JavaCore.create(project));
        for (IPackageFragment mypackage : packages) {
            if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE) {
                createAST(mypackage);
            }
        }
    }

    private void createAST (IPackageFragment mypackage) throws JavaModelException {
        for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
            // now create the AST for the ICompilationUnits
            CompilationUnit parse = parse(unit);
//            CompilationUnit parseStatement = parseStatement(unit);
            MethodVisitor visitor = new MethodVisitor();
            parse.accept(visitor);
            
//            parseStatement.accept(new ASTVisitor() { 
//    			public boolean visit (Statement node) {
//    				System.out.println("Type: " + node.getNodeType());
//    				return true;
//    				}
//    			});
            
            for (MethodDeclaration method : visitor.getMethods()) {
            	List<Statement> names = method.getBody().statements();
        		ArrayList<String> temp = new ArrayList<>();
            	
            	for (Statement statement : names) {            		
            		if (statement.getNodeType() == 24) {
            			temp.add("for");
            		} else if (statement.getNodeType() == 25) {
            			IfStatement state = (IfStatement)statement;
            			System.out.println(state.getExpression());
            			System.out.println(state.getThenStatement());
            			System.out.println(state.getElseStatement());
            			temp.add("if");
            		} else if( statement.getNodeType() == 61) {
            			temp.add("while");
            		} else if( statement.getNodeType() == 21){
            			temp.add("exp");
            		} else if( statement.getNodeType() == 60){
            			temp.add("var");
            		} else {
            			temp.add(statement.toString());
            		}
            	}
            	input.add(temp);
            }
            System.out.println(input);
            input.clear();

        }
    }

    /**
     * Reads a ICompilationUnit and creates the AST DOM for manipulating the
     * Java source file
     *
     * @param unit
     * @return
     */

    @SuppressWarnings("deprecation")
	private static CompilationUnit parse(ICompilationUnit unit) {
        ASTParser parser = ASTParser.newParser(AST.JLS4);
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        parser.setSource(unit);
        parser.setResolveBindings(true);
        return (CompilationUnit) parser.createAST(null); // parse
    }
}

