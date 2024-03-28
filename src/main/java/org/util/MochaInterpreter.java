package org.util;

import java.io.*;
import java.util.Scanner;

/**
 * <p>Name: MochaInterpreter Class</p>
 * <p>Description: Reads .mocha files and utilizes them based on their names</p>
 */
public class MochaInterpreter {
    private String filepath = "org/sys/settings/";
    private String filename;
    private File file;
    private InputHandler inputHelper;
    private Scanner scnr;
    public MochaInterpreter(String filename) {
        this.filepath += filename + ".mocha";
        this.file = new File(this.filepath);
        this.filename = file.getName();
    }

    /**
     * Interpret Method - takes the file loaded into the Mocha Interpreter and creates the data structure assigned
     * with the class
     * @throws IOException
     */
    public void Interpret() throws IOException {
        String[] splitName = filename.split(".");
        if(splitName[1].equals("mocha")) {
            // Now, read the mocha file and if the splitname[0] says input, use the InputHandler class and add them to
            // InputHandler LinkedList (might change it to a heap but it is a linked list for now)
            if ( splitName[0].equals("input") )
                setInputSettings(this.file);
        }
        else {
            throw new IOException("This is not a mocha file");
        }
    }

    public void setInputSettings(File input) throws FileNotFoundException {
        scnr = new Scanner(input);
        String line = scnr.nextLine();

        while ( scnr.hasNextLine() ) {
            // skip and go to the next line if this line starts with # (comment)
            if ( line.charAt(0) == '#' ) {
                scnr.nextLine();
            }

        }
    }
}
