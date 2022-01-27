package lesson15.HomeWork15;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) throws Exception {
        Class classTest = ClassTestApp.class;

        start(classTest);
    }

    public static void start(Class classTestApp) throws InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchMethodException {

        Constructor constructor = classTestApp.getDeclaredConstructor();
        Object test = constructor.newInstance();
        ArrayList<Method> arrayList = new ArrayList<>();
        Method beforeMethod = null;
        Method afterMethod = null;
        for (Method method : classTestApp.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                arrayList.add(method);
            }
            if (method.isAnnotationPresent(BeforeSuite.class)) {
                if (beforeMethod == null) {
                    beforeMethod = method;
                } else {
                    throw new RuntimeException("Метод с аннотацией @BeforeSuite должен присутствовать в единственном экземпляре");
                }
            }
            if (method.isAnnotationPresent(AfterSuite.class)) {
                if (afterMethod == null) {
                    afterMethod = method;
                } else {
                    throw new RuntimeException("Метод с аннотацией @AfterSuite должен присутствовать в единственном экземпляре");
                }
            }
            arrayList.sort(new Comparator<Method>() {
                @Override
                public int compare(Method method1, Method method2) {
                    return method2.getAnnotation(Test.class).priority() - method1.getAnnotation(Test.class).priority();
                }
            });
        }
        if (beforeMethod != null) {
            beforeMethod.invoke(test, null);
        }
        for (Method method : arrayList) {
            method.invoke(test, null);
        }
        if (afterMethod != null){
            afterMethod.invoke(test, null);
        }
    }
}
