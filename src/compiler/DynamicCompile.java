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

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;  
import javax.tools.JavaCompiler.CompilationTask;  
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;  
import javax.tools.ToolProvider;

import model.Exercise;
import model.User;

public class DynamicCompile {
	
	public static String compile(Exercise exo, String code, User u) throws IOException {
        // get classname
        String[] subs = exo.getContentHead().split("\\{")[0].split("class");
        String classname = subs[subs.length - 1].trim();
        String filename = classname + ".java";
        
        // get username
        String username = u.getFirstName() + u.getLastName();
        
        // get time
        SimpleDateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String time = "Exo" + df.format(new Date());
        
        // generate code to compile        
        String source = "package " + u.getFirstName() + u.getLastName() + "." + classname + "." + time + ";\n\n" + exo.getContentHead() + "\n" + code + "\n" + exo.getContentFoot();
        
        // write code into file
        String root = "E:\\Lernen\\ESIGELEC\\2eme_annee\\J2EE\\Projet\\CodeEvaluation\\javaFiles\\";
        String rootExo = root + username + "\\" + classname;
        String rootClass = rootExo + "\\" + time;
        File dir = new File(rootClass);
        
        if ( !dir.exists() ) {  
            dir.mkdirs();  
        }
        
        FileWriter writer = new FileWriter(new File(dir,filename));  
        writer.write(source);  
        writer.flush();  
        writer.close();  
          
        // get compiler
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        
        // use diagnostic listener
        DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
        
        // get file manager
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(diagnostics, null, null); 
        
        // associate file manager with file
        Iterable<? extends JavaFileObject> it = javaFileManager.getJavaFileObjects(new File(dir,filename));  
        
        // generate compilation task
        CompilationTask task = javaCompiler.getTask(null, javaFileManager, diagnostics, Arrays.asList("-d", root), null, it);
        
        // compile
        String result = "";
		boolean success = task.call();
		if ( !success ) {
			result += "Compilation Failed:\n";
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				result += "    " + diagnostic.getKind() + "!\n";
				result += "    At: " + diagnostic.getSource() + "\n";
				result += "    Message: " + diagnostic.getMessage(null);
			}
			javaFileManager.close();
			System.out.println(result);
			return result;
		}
		else {
			javaFileManager.close();

			// prepare commands
			String command = "pushd " + root + "\n" + "java -cp . " + username
					+ "." + classname + "." + time + "." + classname;
			String exeName = classname + ".bat";
			FileWriter cmd = new FileWriter(new File(dir, exeName));
			cmd.write(command);
			cmd.flush();
			cmd.close();

			// run program
			Runtime run = Runtime.getRuntime();
			Process process = run.exec("cmd /c " + rootClass + "\\" + exeName);

			InputStream in = process.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			System.out.println(reader.toString());
			String info = "";

			while ((info = reader.readLine()) != null) {
				result += info + "\n";
			}

			// get system output as result
			String[] s = result.split(classname);
			result = s[s.length - 1];
			result = "Compilation Success.\n" + "Result:" + result;
			System.out.println(result);
			return result;
		}
	}
}
