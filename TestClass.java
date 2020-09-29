package lesson4;

public class TestClass {

    public static void main(String[] args) throws Exception {
        Person p = new Person("Elena", "27.08.1998");
        Person p1 = new Person("Elena", "27.08.1999");
        Person p2 = new Person("Elena", "27.08.2000");
        AccountManagerImpl am = new AccountManagerImpl();
        am.registerNewAccount("e@mail.ru", "12345", p);
        am.registerNewAccount("e@mail.ru", "12345", p1);
        am.registerNewAccount("e@mail.ru", "12345", p2);
    }


}
