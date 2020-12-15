package com;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.caucho.hessian.io.SerializerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Map;

/**
 * Hessian 3.2.1 Test
 * @author liushun
 * @date 2020/11/20
 */
public class HessianTest {
    public static void main(String[] args) throws IOException {
        Map<String, String> envs = System.getenv();
        envs.forEach((key, val) -> {
            System.out.println(key + "----" + val);
        });

        // test(1024 * 32); // OK
        // test(1024 * 32 + 1); // OK
        // test(1024 * 32 + 31); // OK
        // test(1024 * 32 + 32); // ERROR
        // test(1024 * 32 + 512); // ERROR
        // test(1024 * 32 + 1023); // ERROR
        // test(1024 * 33); // OK
        // test(1024 * 33 + 1023); // OK
    }

    public static void test(int size) throws IOException {
        SerializerFactory reponseSerializerFactory = new SerializerFactory();

        StringBuilder buf = new StringBuilder();
        for(int i = 0; i < size; i++) {
            buf.append('A');
        }

        String str = buf.toString();

        System.out.println("length: " + str.getBytes().length);

        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream(2048);
        Hessian2Output hessianOutput = new Hessian2Output(byteBuffer);
        hessianOutput.setSerializerFactory(reponseSerializerFactory);
        hessianOutput.writeObject(str);
        hessianOutput.flush();

        byte[] bytes = byteBuffer.toByteArray();

        ByteArrayInputStream input = new ByteArrayInputStream(bytes);
        Hessian2Input hessianInput = new Hessian2Input(input);
        hessianInput.setSerializerFactory(reponseSerializerFactory);
        String result = (String) hessianInput.readObject(String.class);
        System.out.println("result: " + result);
    }
}
