package ru.archieve.generator.service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PrintService {
    public static final List<String> archive = new ArrayList<>();
    public static List<String> getListFilesInfo(String path, String indent){
        File file = new File(path);
        if (file.isDirectory()){
            String dirName = indent + "\"" + file.getName()+"\":";
            archive.add(dirName);
            for (File attachedFile : ArchiveService.getDirMass(file.getPath())){
                getListFilesInfo(attachedFile.getPath(),indent + "_ _ _ ");
            }
        }else {
            String fileName = indent + file.getName();
            archive.add(fileName);
        }
        return archive;
    }
}
