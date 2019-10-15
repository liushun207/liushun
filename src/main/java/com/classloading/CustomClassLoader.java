package com.classloading;

import java.io.FileNotFoundException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 自定义类加载器.
 * @author liushun
 */
public class CustomClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        try {
            byte[] result = getClassFromCustomPath(name);
            if(result == null) {
                throw new FileNotFoundException(name);
            } else {
                return defineClass(name, result, 0, result.length);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }

        throw new ClassNotFoundException(name);
    }

    private byte[] getClassFromCustomPath(String name) {
        // 从自定义路径中加载指定类
        String pathStr = "file:///E:/workspace/liushun/target/classes/com/classloading/" + name + ".class";

        byte[] cLassBytes = null;
        Path path = null;
        try {
            path = Paths.get(new URI(pathStr));
            cLassBytes = Files.readAllBytes(path);
            return cLassBytes;
        } catch(Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void main(String[] args) {
        CustomClassLoader customClassLoader = new CustomClassLoader();
        try {
            Class<?> clazz = Class.forName("com.classloading.SubClass", true, customClassLoader);
            Object obj = clazz.newInstance();
            System.out.println(obj.getClass().getClassLoader());
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
