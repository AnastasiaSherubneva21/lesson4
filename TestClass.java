package lesson4;

public class TestClass {

    public static void main(String[] args) throws Exception {
        Person p = new Person("Elena", "27.08.1998");
        Person p1 = new Person("Elena", "27.08.1999");
        Person p2 = new Person("Elena", "27.08.2000");

        AccountManagerImpl am = new AccountManagerImpl();

        am.registerNewAccount("el1@mail.ru", "12345", p);
        am.registerNewAccount("el2@mail.ru", "123456", p1);
        am.registerNewAccount("el3@mail.ru", "1234567", p2);

        try {
            am.registerNewAccount("el3@mail.ru", "1234567", p2);
        }
        catch (DuplicateAccountException e) {
            e.printStackTrace("Аккаунт уже существует");
        }

        am.removeAccount("el3@mail.ru", "1234567");

        try {
            am.removeAccount("el11@mail.ru", "12345");
        }
        catch (WrongCredentialsException e) {
            e.printStackTrace("Аккаунт не существует");
        }

        System.out.println(am.hasAccount("el2@mail.ru"));
        System.out.println(am.hasAccount("el22@mail.ru"));

        System.out.println(am.numOfAccounts());

        System.out.println(am.getPerson("el1@mail.ru", "12345").str());

        for (int x : new int[]{1, 2, 3, 4, 5}) {
            try {
                System.out.println(am.getPerson("el1@mail.ru", "123").str());
            }
            catch (WrongCredentialsException e) {
                e.printStackTrace("Аккаунт не существует");
            }
        }

        try {
            System.out.println(am.getPerson("el1@mail.ru", "123").str());
        }
        catch (TooManyLoginAttemptsException e) {
            e.printStackTrace("Число неудачных попыток входа превышает 5. Аккаунт заблокирован.");
        }
    }
}
