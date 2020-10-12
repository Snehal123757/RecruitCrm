package org.assignment.utility;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CommonUtils {

    public static void renameFileInSameDirectory(String oldFile, String newFileName) {
        String homeDir = System.getProperty("user.home");
        try {
            Path oldFilePath = Paths.get(homeDir + "/Downloads/" + oldFile);
            System.out.println(oldFilePath);
            Files.move(oldFilePath, oldFilePath.resolveSibling(newFileName));
        } catch (IOException io) {
            System.out.println(io.getLocalizedMessage());
        }
    }

    public static void moveFileInDifferentDirectory(String fileName, String newFileDirectory) {
        String homeDir = System.getProperty("user.home");
        try {
            Path oldFilePath = Paths.get(homeDir, "Downloads", fileName);
            System.out.println(oldFilePath);
            Path newFilePath = Paths.get(homeDir, newFileDirectory, fileName);
            System.out.println(newFilePath);
            Files.move(oldFilePath, newFilePath);
        } catch (IOException io) {
            System.out.println(io.getLocalizedMessage());
        }
    }

    public static void renameFileInDifferentDirectory(String oldFile, String newFileName, String newFileDirectory) {
        String homeDir = System.getProperty("user.home");
        try {
            Path oldFilePath = Paths.get(homeDir, "Downloads", oldFile);
            System.out.println(oldFilePath);
            Path newFilePath = Paths.get(homeDir, newFileDirectory, newFileName);
            System.out.println(newFilePath);
            Files.move(oldFilePath, newFilePath);
        } catch (IOException io) {
            System.out.println(io.getLocalizedMessage());
        }
    }

    public static void main(String[] args) throws IOException {

        //moveFileInDifferentDirectory("attachment.pdf", "IdeaProjects/gmail/downloads");

        String data = ExcelUtils.readExcelData("RENAMED.pdf");
        System.out.println(data);
        renameFileInSameDirectory("attachment.pdf", data);
        moveFileInDifferentDirectory("RENAMED.pdf", "IdeaProjects/gmail/downloads");
        //renameFileInDifferentDirectory("renamed.pdf", "attachment.pdf", "Desktop");
    }

}
