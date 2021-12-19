package lesson15.HomeWork15;

public class ClassTestApp {
    @BeforeSuite
    public void start() {
        System.out.println("start");
    }

    @Test(priority = 6)
    public void test1() {
        System.out.println("test1");
    }

    @Test
    public void test2() {
        System.out.println("test2");
    }

    @Test(priority = 4)
    public void test3() {
        System.out.println("test3");
    }

    @Test(priority = 3)
    public void test4() {
        System.out.println("test4");
    }

    @AfterSuite
    public void stop() {
        System.out.println("finish");
    }
}
