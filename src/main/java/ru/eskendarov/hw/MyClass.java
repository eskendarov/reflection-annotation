package ru.eskendarov.hw;

public class MyClass {
    
    @BeforeSuite
    public void start() {
        System.out.println("start");
    }
    /*
    * java.lang.RuntimeException: Больше одного метода с аннотацией BeforeSuite
    * */
//    @BeforeSuite
//    public void doIt() {
//        System.out.println("Do it before!");
//    }
    @Test
    public void defaultValueTest() {
        System.out.println("defaultValueTest: default priority = 5");
    }
    @Test(priority = 3)
    public void test1() {
        System.out.println("test1: priority = 3");
    }
    @Test(priority = 7)
    public void test2() {
        System.out.println("test2: priority = 7");
    }
    @Test(priority = 1)
    public void test3() {
        System.out.println("test3: priority = 1");
    }
    @Test(priority = 4)
    public void test4() {
        System.out.println("test4: priority = 4");
    }
    public void method() {
        System.out.println("method");
    }
    @AfterSuite
    public void stop() {
        System.out.println("stop");
    }
    /*
    * java.lang.RuntimeException: Больше одного метода с аннотацией AfterSuite
    * */
//    @AfterSuite
//    public void doItAfter(){
//        System.out.println("Do it after!");
//    }
}