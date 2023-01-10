package ru.archieve.generator.service;

import ru.archieve.generator.model.ArchFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
public class ArchiveService {
    public static final List<ArchFile> archive = new ArrayList<>();
    public static List<ArchFile> getListOfArchFile(String path, int n, String dirName){
        File file = new File(path);
        if (file.isDirectory()){
            n++;
            if (dirName == null){
                dirName = file.getName();
            }else {
                dirName = dirName+'/'+file.getName();
            }
            String finalDirName = dirName;
            int finalN = n;
            for (File file1:getDirMass(file.getPath())){
                getListOfArchFile(file1.getPath(), finalN, finalDirName);
            }
        }else {
            String fileName = file.getName();
            ArchFile archFile = new ArchFile(n, fileName, file.getPath(), dirName, file.getParent(), file.getParentFile().getName());
            archive.add(archFile);
        }
        return archive;
    }
    public static File[] getDirMass(String path){
        File[] dirMass = new File(path).listFiles();
        return dirMass;
    }
}
