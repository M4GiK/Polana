package com.m4gik.models;

import com.m4gik.interfaces.FileIO;

import java.io.*;
import java.nio.file.Path;

/**
 * Created by m4gik on 6/25/17.
 */
public class FileOperations implements FileIO {

    FileInputStream inFile = null;
    FileOutputStream outFile = null;

    @Override
    public FileOutputStream saveFile(File filepath) throws FileNotFoundException {
        return this.outFile = new FileOutputStream(filepath);
    }

    @Override
    public FileInputStream loadFile(File filepath) throws FileNotFoundException {
        return this.inFile = new FileInputStream(filepath);
    }
}
