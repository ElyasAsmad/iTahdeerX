package com.group10.itahdeerx;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class UsersDB {

    String FILE_PATH = "users.dat";

    public UsersDB() {

        File file = new File(FILE_PATH);

        try {

            if (file.createNewFile()) {
                System.out.println("File is created!");
            }
            else {
                System.out.println("File already exists!");
            }

        } catch (IOException e) {

            e.printStackTrace();

        }


    }

    public void saveArr(UsersModel data) {

        try {

            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<UsersModel> _list = (List<UsersModel>) objectInputStream.readObject();

            _list.add(data);

            FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(_list);
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("Data successfully saved into database!");

        } catch (EOFException e) {

            try {

                // No list yet, so we create new
                List<UsersModel> _list = new ArrayList<>();
                _list.add(data);

                FileOutputStream fileOutputStream = new FileOutputStream(FILE_PATH);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                objectOutputStream.writeObject(_list);
                objectOutputStream.flush();
                objectOutputStream.close();
                System.out.println("Data successfully saved into database!");

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<UsersModel> loadArr() {

        try {

            FileInputStream fileInputStream = new FileInputStream(FILE_PATH);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<UsersModel> users = (List<UsersModel>) objectInputStream.readObject();
            fileInputStream.close();

            return users;

        } catch (EOFException e) {

            System.out.println("File is empty!");
            return null;

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }

    }

}
