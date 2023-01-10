package ru.archieve.generator.model;

public class ArchFile {
    private int level;
    private String fileName;
    private String filePath;
    private String dirName;
    private String dirPath;
    private String folderName;

    public ArchFile() {
    }
    public ArchFile(int level, String fileName, String filePath, String dirName, String dirPath, String folderName) {
        this.level = level;
        this.fileName = fileName;
        this.filePath = filePath;
        this.dirName = dirName;
        this.dirPath = dirPath;
        this.folderName = folderName;
    }
    @Override
    public String toString() {
        return "ArchFile{" +
                "level=" + level +
                ", fileName='" + fileName + '\'' +
                ", filePath='" + filePath + '\'' +
                ", dirName='" + dirName + '\'' +
                ", dirPath='" + dirPath + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
    public String getDirName() {
        return dirName;
    }

    public void setDirName(String dirName) {
        this.dirName = dirName;
    }
    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }
    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }
}
