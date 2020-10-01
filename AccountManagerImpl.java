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
    public void removeAccount(String email, String password) throws WrongCredentialsException{
        ArrayList<String> acclst = readText("file.txt");
        ArrayList<String> newlst = new ArrayList<String>();
        String str = email + " " + password;
        String strx = "";
        int c = 0;
        for (String x : acclst) {
            if (c == 0) {
                if (!x.equals("")) {
                    strx = x.split(" ")[2] + " " + x.split(" ")[3];
                    if (strx.equals(str)) {
                        newlst.add(x);
                        c = 1;
                    }
                }
            }
        }
        if (c == 1) {
            for (String x : newlst) {
                acclst.remove(x);
                writeText(acclst);
            }
        }
        else {
            throw new WrongCredentialsException();
        }
    }

    @Override
    public boolean hasAccount(String email) {
        ArrayList<String> lst = readText("file.txt");
        String strx = "";
        int c = 0;
        for (String x : lst) {
            if (!x.equals("")) {
                strx = x.split(" ")[2];
                if (strx.equals(email)) {
                    c = 1;
                }
            }
        }
        return c == 1;
    }

    @Override
    public Person getPerson(String email, String password) throws TooManyLoginAttemptsException, WrongCredentialsException {
        ArrayList<String> lst = readText("file.txt");
        String str = email + " " + password;
        String persname = "";
        String persbd = "";
        int c = 0;
        for (String x : lst) {
            if (!x.equals("")) {
                String strx = x.split(" ")[2] + " " + x.split(" ")[3];
                if (strx.equals(str)) {
                    c = 1;
                    persname = x.split(" ")[0];
                    persbd = x.split(" ")[1];
                }
            }
        }
        if (c == 0) {
            if (AttemptCounter.INSTANCE.checkAttempts(str)) {
                throw new WrongCredentialsException();
            }
            else {
                throw new TooManyLoginAttemptsException();
            }
        }
        else {
            return new Person(persname, persbd);
        }
    }

    @Override
    public int numOfAccounts() {
        ArrayList<String> lst = readText("file.txt");
        int c = 0;
        for (String x : lst) {
            if (!x.equals("")) {
                c += 1;
            }
        }
        return c;
    }
}
