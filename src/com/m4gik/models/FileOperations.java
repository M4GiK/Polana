package com.m4gik.models;

import com.m4gik.interfaces.FileIO;
import com.m4gik.models.table.Column;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by m4gik on 6/25/17.
 */
public class FileOperations implements FileIO {

    private FileInputStream inFile = null;
    private FileOutputStream outFile = null;
    private File file;

    public FileOperations(String fileName) {
        try {
            this.file = new File(fileName);
            this.outFile = new FileOutputStream(file, false);

            if (!this.file.exists()) {
                this.file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public FileOutputStream addFileContent(List<DayInformation> dayInformations) throws IOException {
        PrintWriter printWriter = new PrintWriter(this.outFile);
        Set<String> uniqueColumns = new LinkedHashSet<>();
        for (DayInformation dayInformation : dayInformations) {
            createUniqueHeader(uniqueColumns, dayInformation);
        }

        for(String uniqueColumn: uniqueColumns ) {
            printWriter.print(" | " + uniqueColumn);
            System.out.print(" | " + uniqueColumn);
        }

//        for (DayInformation dayInformation : dayInformations) {
//            printWriter.println(" | " + dayInformation.getTable().getRows());
//            System.out.println(" | " + uniqueColumn);
//        }

        printWriter.close();

        return this.outFile;
    }

    private void createUniqueHeader(Set<String> uniqueColumns, DayInformation dayInformation) {
        for (Column column : dayInformation.getTable().getColums()) {
            if(!uniqueColumns.contains(column.getColumnName())) {
                uniqueColumns.add(column.getColumnName());
            }
        }
    }

    @Override
    public boolean saveFile(FileOutputStream fileOutputStream) throws FileNotFoundException {
        boolean saveStatus = false;

        try {
            this.outFile.flush();
            this.outFile.close();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.outFile != null) {
                    this.outFile.close();
                    saveStatus = true;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return saveStatus;
    }

    @Override
    public FileInputStream loadFile(File filepath) throws FileNotFoundException {
        return this.inFile = new FileInputStream(filepath);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
