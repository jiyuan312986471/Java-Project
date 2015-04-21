package compiler;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileWriter;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.InputStreamReader;  
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;  

import javax.tools.JavaCompiler;  
import javax.tools.JavaCompiler.CompilationTask;  
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;  
import javax.tools.ToolProvider;

import model.Exercise;
import model.User;

public class DynamicCompile {
	
	public static String compile(Exercise exo, String code, User u) throws IOException, ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// generate code to compile        
        String source = "package " + u.getFirstName() + u.getLastName() + ";\n\n" + exo.getContentHead() + "\n" + code + "\n" + exo.getContentFoot();
        
        // get classname
        String[] subs = exo.getContentHead().split("\\{")[0].split("class");
        String classname = subs[subs.length - 1].trim();
        String filename = classname + ".java";
        
        // get username
        String username = u.getFirstName() + u.getLastName();
        
        // write code into file
        String root = "E:\\Lernen\\ESIGELEC\\2eme_annee\\J2EE\\Projet\\CodeEvaluation\\javaFiles\\";
        String rootPath = root + username;
        File dir = new File(rootPath);
        
        if ( !dir.exists() ) {  
            dir.mkdir();  
        }
        
        FileWriter writer = new FileWriter(new File(dir,filename));  
        writer.write(source);  
        writer.flush();  
        writer.close();  
          
        // get compiler
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        
        // get file manager
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null); 
        
        // associate file manager with file
        Iterable<? extends JavaFileObject> it = javaFileManager.getJavaFileObjects(new File(dir,filename));  
        
        // generate compilation task
        CompilationTask task = javaCompiler.getTask(null, javaFileManager, null, Arrays.asList("-d", root), null, it);
        
        // compile
        task.call();  
        javaFileManager.close();  
          
        // prepare commands
        String command = "java -cp ../ " + username + "/" + classname;
        String[] cmdArray = { 
        	"cmd /k cd\\",
        	"cmd /k cd " + rootPath,
        	command
        };
        
        // run program
        Runtime run = Runtime.getRuntime();
        Process process = run.exec(cmdArray);
        System.out.println(command);
//        Process process = run.exec("java -cp ./temp temp/" + classname);
        
        InputStream in = process.getInputStream();  
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));  
        String info  = "";
        String result = "";
        while ( (info = reader.readLine()) != null ) {
            System.out.println(info);
            result += info;
        }
        System.out.println("result: " + result);
        return result;
        
	}
}
