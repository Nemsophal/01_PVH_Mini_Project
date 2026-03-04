package controllers;

import models.dao.ProductDaoI;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class BackupController {
    private ProductDaoI dao;
    public BackupController(ProductDaoI dao){
        this.dao = dao;
    }
    public void backup() {

        String pathInSrc = "src/backup";
        File dir = new File(pathInSrc);

        //create dir
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // get next version
        int version = (dir.list() != null) ? dir.list().length + 1 : 1;
        String date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //combine full path
        String fileName = pathInSrc + "/Version" + version + "-products-backup-" + date + ".sql";

        System.out.println("Saving to src/backup...");
        dao.backup(fileName);
    }

    public void restore() {
        File dir = new File("src/backup");
        File[] files = dir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("No backups found in src/backup.");
            return;
        }
        // List files
        for (int i = 0; i < files.length; i++) {
            long kb = files[i].length() / 1024;
            System.out.println((i + 1) + ". " + files[i].getName() + " (" + kb + " KB)");
        }
        int choice;
        while (true) {
            choice = util.InputUtil.readInt("Select version: ");
            if (choice >= 1 && choice <= files.length) {
                break;
            }
            System.out.println("Invalid choice.");
        }
        dao.restore(files[choice - 1].getPath());
    }
}
