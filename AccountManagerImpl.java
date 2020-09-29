package lesson4;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountManagerImpl implements MailAccountManager{

    private static void writeText(ArrayList<String> lst) {
        File file = new File("file.txt");
        FileWriter fr = null;
        try {
            fr = new FileWriter(file);
            for (String x : lst) {
                fr.write(x + System.getProperty("line.separator"));
            }
        } catch (IOException e) { e.printStackTrace(); }
        finally {
            try { fr.close();}
            catch(IOException e) { e.printStackTrace(); }
        }
    }

    private static ArrayList<String> readText(String filename) {
        ArrayList<String> lst = new ArrayList<>();
        try {
            FileReader fr = new FileReader(filename);
            Scanner scan = new Scanner(fr);
            try {
                while (scan.hasNextLine()) {
                    lst.add(scan.nextLine());
                }
            } catch (Exception e) {
                System.out.println("При чтении файла возникла ошибка");
            }
            fr.close();
        }
        catch (Exception e) { System.out.println("Файл не существует"); }
        return lst;
    }

    @Override
    public void registerNewAccount(String email, String password, Person person) throws DuplicateAccountException {
        String str = person.str() + " " + email + " " + password;
        ArrayList<String> lst = readText("file.txt");
        int c = 0;
        for (String x : lst) {
            if (str.equals(x)) { c = 1; }
        }
        if (c != 0) {
            throw new DuplicateAccountException();
        }
        else {
            lst.add(str);
            writeText(lst);
        }

    }

    @Override
    public void removeAccount(String email, String password) {

    }

    @Override
    public boolean hasAccount(String email) {
        return false;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException {
        return null;
    }

    @Override
    public int numOfAccounts() {
        return 0;
    }
}
