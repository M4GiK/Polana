package com.m4gik.interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * Created by m4gik on 6/25/17.
 */
public interface FileIO {

    boolean saveFile(FileOutputStream fileOutputStream) throws FileNotFoundException;

    FileInputStream loadFile(File filepath) throws FileNotFoundException;
}
