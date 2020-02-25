package testpy4j;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FolderFileReader {
	
	public static void listFilesFolder(final File folder) {

		for(final File fileEntry : folder.listFiles()) {
			/*
	        if (fileEntry.isDirectory()) {
	            listFilesFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	        }
	        */
			if (!fileEntry.isDirectory()) 
				System.out.println(fileEntry.getName());
		
		}
	}
	
	
	public static void streamFilesFolder(String path) {
		
		try (Stream<Path> paths = Files.walk(Paths.get(path))) {
		    paths
		        .filter(Files::isRegularFile)
		        .forEach(System.out::println);
		} catch(IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		
		/*
		final File folder = new File("/root/bigFatWorkspace/M2/DAAR/DAAR_Online_Project_Django/database");
		listFilesFolder(folder);
	    */
		streamFilesFolder("/root/bigFatWorkspace/M2/DAAR/DAAR_Online_Project_Django/database");
	}

}
