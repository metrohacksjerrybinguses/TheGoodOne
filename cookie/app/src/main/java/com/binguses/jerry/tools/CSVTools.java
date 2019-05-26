package com.binguses.jerry.tools;

import com.opencsv.CSVWriter;

import java.io.File;

public class CSVTools {
    String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
    String fileName = "AnalysisData.csv";
    String filePath = baseDir + File.separator + fileName;
    File f = new File(filePath);
    CSVWriter writer;
    public CSVTools() {

    }
}
