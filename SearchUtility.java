//David Isti
import java.util.ArrayList;
import java.io.*;

/*
 *This Java Program is a text search utlity which searches all text files in a directory hierarchy for a string
 *by using recursion.
 */


public class SearchUtility {
    private static String search;
    private static String extention;
    private static ArrayList<String> paths;

    public TextSearchUtility(String search, String extention){

        this.search = search;
        this.extention = extention;
        paths = new ArrayList<>();
    }


    public static void main(String[] args) {

        String dir = null;
        String extensions = null;
        String search = null;

        for(String arg : args){
            if(arg.contains("dir=")){
                dir = arg.substring(arg.indexOf("=") + 1).trim();
            }
            else if(arg.contains("string=")){
                search = arg.substring(arg.indexOf("=") + 1).trim();
            }
            else if(arg.contains("extension=")){
                extensions = arg.substring(arg.indexOf("=") + 1).trim();
            }
        }
        if(dir == null || extensions == null || search == null){
            throw new IllegalArgumentException("Misssing command line arguments");
        }
        File startingDir = new File(dir);
        TextSearchUtility reader = new TextSearchUtility(search, extensions);
        reader.searchDirectory(startingDir);
        reader.printPaths();

    }
    /*
    Recursive method
    Use listFiles method to get the pathnames of the files and directories
    Check if it is a directory, if so recurse on the directory to retrieve the contents
    Check if the current pathname has the same extension and call searchFile.
    @param File to be searched
    */
    public void searchDirectory(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    searchDirectory(file);
                } else if (file.getName().substring(file.getName().lastIndexOf(".") + 1).equals(extention)) {
                    searchFile(file);
                }


            }
        } catch (Exception e) {
            // if any error occurs
            e.printStackTrace();
        }
    }
    /*
    Set variable path to the pathname string
    Use a buffered reader to read the file and keep track of line number with lineCount
    If the line contains the search string assign the line number, use indexOf method to retrieve char spaces
    and set path to the contain the pathname, lineCount, and index; add path to arraylist of mathched pathnames
    @param File to be searched
    */

    private void searchFile(File file){
        boolean isFound = false;
        String path = file.getAbsolutePath();
        try {
            BufferedReader f = new BufferedReader(new FileReader(file));
            int lineCount = 1;
            String line = f.readLine();
            while (line != null){
                if(line.contains(search)){
                    isFound = true;
                    int index = line.indexOf(search);
                    path = path + " : " + " line " + lineCount + ", character " + (index + 1);
                }
                line = f.readLine();
                lineCount++;
            }
            if(isFound){
                paths.add(path);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
    public void printPaths(){
        for(String path: paths){
            System.out.println(path);
        }
    }
}