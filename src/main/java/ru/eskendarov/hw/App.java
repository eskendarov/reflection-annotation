package ru.eskendarov.hw;

import java.lang.reflect.Method;
import java.util.ArrayList;

/*
 *
 * Создать класс, который может выполнять «тесты».
 * В качестве тестов выступают классы с наборами методов с аннотациями @Test.
 * Для этого у него должен быть статический метод start(), которому в качестве параметра
 * передается или объект типа Class, или имя класса.
 * Из «класса-теста» вначале должен быть запущен метод с аннотацией @BeforeSuite, если такой имеется.
 * Далее запущены методы с аннотациями @Test, а по завершении всех тестов – метод с аннотацией @AfterSuite.
 * К каждому тесту необходимо добавить приоритеты (int числа от 1 до 10),
 * в соответствии с которыми будет выбираться порядок их выполнения.
 * Если приоритет одинаковый, то порядок не имеет значения.
 * Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном экземпляре,
 * иначе необходимо бросить RuntimeException при запуске «тестирования».
 *
 * */

public class App {
    
    public static void main(final String[] args) throws Exception {
        final Class c = MyClass.class;
        final Object testObj = c.newInstance();
        final Method[] methods = c.getDeclaredMethods();
        final ArrayList<Method> methodArrayList = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        for (final Method o : c.getDeclaredMethods()) {
            if (o.isAnnotationPresent(Test.class)) {
                methodArrayList.add(o);
            }
            if (o.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) beforeMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией BeforeSuite");
            }
            if (o.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) afterMethod = o;
                else throw new RuntimeException("Больше одного метода с аннотацией AfterSuite");
            }
            methodArrayList.sort(
                    (o1, o2) -> o2.getAnnotation(Test.class).priority() - o1.getAnnotation(Test.class).priority());
        }
        if (beforeMethod != null) {
            beforeMethod.invoke(testObj, null);
        }
        for (final Method o : methodArrayList) {
            o.invoke(testObj, null);
        }
        if (afterMethod != null) {
            afterMethod.invoke(testObj, null);
        }
    }
    
}