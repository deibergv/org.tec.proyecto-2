package org.tec.proyecto2.flowchart.handlers;

import java.util.ArrayList;
import java.util.List;

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
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.swt.widgets.Display;

import static org.tec.proyecto2.flowchart.parts.SampleView.figureGen;

public class SourceAnalycer {
	
	private static final String JDT_NATURE = "org.eclipse.jdt.core.javanature";
	private String[] resourceList;
	private IProject[] projects;
	private String methodName;
	
	SourceAnalycer(String code,String method) {
		this.methodName = method;
		resourceList = code.split("/");
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
        IWorkspaceRoot root = workspace.getRoot();
        // Get all projects in the workspace
        projects = root.getProjects();
        // Loop over all projects
        
	}
		
	public void encoder () {
		for (IProject project : projects) {
            try {
                if (project.isNatureEnabled(JDT_NATURE) && project.getName().equals(resourceList[1])) {
                	IPackageFragment[] packages = JavaCore.create(project).getPackageFragments();
                    // parse(JavaCore.create(project));
                    for (IPackageFragment mypackage : packages) {
                        if (mypackage.getKind() == IPackageFragmentRoot.K_SOURCE && 
                        mypackage.getElementName().toString().equals(resourceList[3])) {
                        	for (ICompilationUnit unit : mypackage.getCompilationUnits()) {
                        		if (unit.getElementName().toString().
                        				equals(resourceList[resourceList.length-1])) {
                        			CompilationUnit parse = parse(unit);
                                    MethodVisitor visitor = new MethodVisitor();
                                    parse.accept(visitor);
                                    createAST (visitor);                        		
                                }
                        	}
                        }
                    }
                 }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }
	}
	
	public void createAST (MethodVisitor visitor) throws JavaModelException {
		ArrayList<String> temp = new ArrayList<>();
		for (MethodDeclaration method : visitor.getMethods()) {
//			if (method.getName().toString().contains(methodName) || method.getName().toString().compareTo(methodName) == 0) {
				List<Statement> names = method.getBody().statements();

				for (Statement statement : names) {
					statementVerify(statement,temp);
				}
//			} else {
//			}
        }  
		
		Display.getDefault().syncExec(new Runnable() {
			public void run() {
				figureGen(temp);
			}
		});
	}
	
	public void statementVerify (Statement statement, ArrayList<String> temp) {
    	if (statement.getNodeType() == 24) {
			ForStatement state = (ForStatement)statement;
			temp.add("for~"+state.getExpression());
			temp.add("for~start");
			statementVerify(state.getBody(),temp);
			temp.add("for~end");
		} else if (statement.getNodeType() == 25) {
			IfStatement state = (IfStatement)statement;
			temp.add("if~"+state.getExpression());
			temp.add("if~start");
			statementVerify(state.getThenStatement(),temp);
			temp.add("if~end");
			temp.add("if~else");
			statementVerify(state.getElseStatement(),temp);
			temp.add("if~endelse");
		} else if( statement.getNodeType() == 61) {
			WhileStatement state = (WhileStatement)statement;  
			temp.add("while~"+state.getExpression());
			temp.add("while~start");
			statementVerify(state.getBody(),temp);
			temp.add("while~end");
		} else if( statement.getNodeType() == 21){
			ExpressionStatement state = (ExpressionStatement)statement;
			temp.add("exp~"+state.getExpression());
		} else if( statement.getNodeType() == 60){
			VariableDeclarationStatement state = (VariableDeclarationStatement)statement;
			temp.add("var~"+state.fragments().toString());
		} else if( statement.getNodeType() == 8) {
			Block state = (Block)statement;
			List<Statement> lista = state.statements();
			for (Statement sta:lista) {
				statementVerify(sta,temp);
			}
		} else {
			temp.add("indf~"+statement.getNodeType());
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
