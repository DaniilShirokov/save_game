package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        GameProgress progress1 = new GameProgress(100,0,1, 1000);
        GameProgress progress2 = new GameProgress(70,2,5,500);
        GameProgress progress3 = new GameProgress( 50,5,10,150);
        progress1.Save("F:\\Work\\JavaNetology\\Games\\savegames\\save1.dat", progress1);
        progress2.Save("F:\\Work\\JavaNetology\\Games\\savegames\\save2.dat", progress2);
        progress3.Save("F:\\Work\\JavaNetology\\Games\\savegames\\save3.dat", progress3);

        List<String> pathFiles = new ArrayList<>();
        pathFiles.add("F:\\Work\\JavaNetology\\Games\\savegames\\save1.dat");
        pathFiles.add("F:\\Work\\JavaNetology\\Games\\savegames\\save2.dat");
        pathFiles.add("F:\\Work\\JavaNetology\\Games\\savegames\\save3.dat");

        zipFiles("F:\\Work\\JavaNetology\\Games\\savegames\\zip.zip",pathFiles);
        deleteFiles(pathFiles);
    }


    public static void zipFiles(String nameZip, List<String> pathFiles) throws IOException {

        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(nameZip))) {

            for (String filesName : pathFiles) {
                FileInputStream fis = new FileInputStream(filesName);
                ZipEntry entry = new ZipEntry(filesName);
                zout.putNextEntry(entry);
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                zout.write(buffer);
                zout.closeEntry();
            };
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void deleteFiles ( List<String> pathFiles) throws IOException {
        for (String filesName : pathFiles) {
            File file = new File(filesName);
            System.out.println(file.getName());
            if (file.delete()) {
                System.out.println(file.getName() + " deleted");
            } else {
                System.out.println(file.getName() + " not deleted");
            }
        }
    }
}