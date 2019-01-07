/**
 * @author:
 * @Description:
 * @Data: 2019/1/4 17:07
 **/
package com.classloading;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类加载器与instanceof关键字演示.
 * 不是同一个类加载器，做对象所有类型检查时为false
 */
public class ClassLoaderTest
{
    public static void main(String[] args) throws Exception
    {
        // 自定义类加载器
        ClassLoader myLoader = new ClassLoader()
        {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException
            {
                try
                {
                    String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";

                    System.out.println("文件名称：" + fileName);

                    InputStream is = getClass().getResourceAsStream(fileName);

                    if(is == null)
                    {
                        return super.loadClass(name);
                    }

                    byte[] b = new byte[is.available()];
                    is.read(b);

                    return defineClass(name, b, 0, b.length);
                }
                catch(IOException e)
                {
                    throw new ClassNotFoundException(name);
                }
            }
        };

        Object obj = myLoader.loadClass("com.classloading.ClassLoaderTest").newInstance();

        // 输出
        // class com.classloading.ClassLoaderTest
        // false
        System.out.println(obj.getClass());
        System.out.println(obj instanceof com.classloading.ClassLoaderTest);
    }
}
