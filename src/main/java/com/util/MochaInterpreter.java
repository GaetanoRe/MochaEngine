package com.util;

import com.util.debug.MochaNotif;

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
    private MochaInputHandler inputHelper;
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
                setInputSettings(this.file); // edit input settings now
            if(splitName[0].equals("window"))
                setWindowSettings(this.file);
        }
        else {
            throw new IOException("This is not a mocha file");
        }
    }

    private void setWindowSettings(File input) throws FileNotFoundException{
        MochaNotif errNotif = new MochaNotif();
        String message;
        String title;

        String[] splitUp;
        scnr = new Scanner(input);
        String line = scnr.nextLine();

        while (scnr.hasNextLine()){
            if(line.charAt(0) == '#'){
                line = scnr.nextLine();
            }
            else{
                splitUp = line.split(" ");

            }
        }
    }

    public void setInputSettings(File input) throws FileNotFoundException {
        /* MochaNotif variables initialized */
        MochaNotif errNotif = new MochaNotif();
        String message;
        String title;

        /* processes input */
        String[] splitUp;
        scnr = new Scanner(input);
        String line = scnr.nextLine();

        /* loop to read the entire input file */
        while ( scnr.hasNextLine() ) {
            // skip and go to the next line if this line starts with # (comment)
            if ( line.charAt(0) == '#' ) {
                line = scnr.nextLine();
            }
            else {
                splitUp = line.split(" ");

                // if the line is not in the correct format, inform the user and don't set the command key - continue loop
                /* if ( splitUp.length != 2 ) {
                    String message = "An invalid input format was detected: " + line +
                            "\nThe accepted input format is: '(command) (key)', .";
                    String title = "Invalid Format";
                    errNotif.show(message, title);
                } */
                if ( splitUp.length >= 2 ) {
                    int newKey = Integer.parseInt(splitUp[1]);
                        inputHelper.setKey(splitUp[0], newKey);
                }
                line = scnr.nextLine();
            }
        }
    }
}
