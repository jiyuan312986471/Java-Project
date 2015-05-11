package compiler;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Arrays;  
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;  
import javax.tools.JavaCompiler.CompilationTask;  
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;  
import javax.tools.ToolProvider;

import model.Exercise;
import model.User;

public class DynamicCompiler {
	
	private static String ROOT = "E:\\Lernen\\ESIGELEC\\2eme_annee\\J2EE\\Projet\\CodeEvaluation\\javaFiles\\";
	private static JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
	private static DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
	private static StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(diagnostics, null, null);
	
	private static String result = "";
	
	public static String dynamicCompile(Exercise exo, String code, User u) throws IOException {
        // get classname
        String classname = getClassName(exo);
        String filename = classname + ".java";
        
        // get username
        String username = u.getAttachedUserName();
        
        // get current time
        String currentTime = getCurrentTime();
        
        // generate source code to compile        
        String source = setSrcCode(username, classname, currentTime, code, exo);
        
        // create file path
        String rootExo = ROOT + username + "\\" + classname;
        String rootClass = rootExo + "\\" + currentTime;
        File dir = new File(rootClass);
        
        if ( !dir.exists() )
        	dir.mkdirs();
        
        // write code into file
        writeFile(dir, filename, source);
        
        // compile
		boolean success = compile(dir, filename);
		
		// return compilation result
		if ( !success ) {
			result = setFailMsg(diagnostics);
			javaFileManager.close();
			return result;
		}
		else {
			javaFileManager.close();

			// check if user has cheated
			boolean cheated = hasCheated(exo, code);
			
			if ( cheated ) {
				result = "Please do not cheat.";
			}
			else {
				// prepare commands
				String commandArray = getCommandArray(ROOT, username, classname, currentTime);
				String batName = classname + ".bat";
				writeFile(dir, batName, commandArray);

				// run program
				result = run(rootClass, batName);

				// get system output as result
				result = getOutputResult(result, classname);
			}
			return result;
		}
	}
	
	
	private static String getClassName(Exercise exo) {
		String[] subs = exo.getContentHead().split("\\{")[0].split("class");
        return subs[subs.length - 1].trim();
	}
	
	
	private static String getCurrentTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        return "Exo" + df.format(new Date());
	}
	
	
	private static String setSrcCode(String username, String classname, String currentTime, String code, Exercise exo) {
		return "package " + username + "." + classname + "." + currentTime + ";\n\n" + exo.getContentHead() + "\n" + code + "\n" + exo.getContentFoot();
	}
	
	
	private static void writeFile(File dir, String filename, String src) throws IOException {
		FileWriter writer = new FileWriter(new File(dir,filename));  
        writer.write(src);
        writer.flush();  
        writer.close();
	}
	
	
	private static boolean compile(File dir, String filename) {        
        // associate file manager with file
        Iterable<? extends JavaFileObject> it = javaFileManager.getJavaFileObjects(new File(dir,filename));  
        
        // generate compilation task
        CompilationTask task = javaCompiler.getTask(null, javaFileManager, diagnostics, Arrays.asList("-d", ROOT), null, it);
        
        // compile
		return task.call();
	}
	
	
	private static String setFailMsg(DiagnosticCollector<JavaFileObject> diagnostics) {
		String result = "Compilation Failed:\n";
		for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
			result += "    " + diagnostic.getKind() + "!\n";
			result += "    At: " + diagnostic.getSource() + "\n";
			result += "    Message: " + diagnostic.getMessage(null);
		}
		return result;
	}
	
	
	private static String getCommandArray(String root, String username, String classname, String currentTime) {
		return "pushd " + root + "\n" + "java -cp . " + username + "." + classname + "." + currentTime + "." + classname;
	}
	
	
	private static String getExeCommand(String rootClass, String batName) {
		return "cmd /c " + rootClass + "\\" + batName;
	}
	
	
	private static String run(String rootClass, String batName) throws IOException {
		Runtime run = Runtime.getRuntime();
		Process process = run.exec( getExeCommand(rootClass, batName) );

		InputStream in = process.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		
		String info = "";
		String result = "";
		while ((info = reader.readLine()) != null) {
			result += info + "\n";
		}
		return result;
	}
	
	
	private static String getOutputResult(String result, String classname) {
		String[] s = result.split(classname);
		result = s[s.length - 1];
		return "Compilation Success.\n" + "Result:" + result;
	}
	
	
	private static boolean hasCheated(Exercise exo, String code) {
		switch (exo.getTitle()) {
			case "Value Switch":
				String regex = "\\d+";
				Pattern pattern = Pattern.compile(regex);
				Matcher match = pattern.matcher(code);
				return match.find();
			default:
				return false;
		}
	}
}
