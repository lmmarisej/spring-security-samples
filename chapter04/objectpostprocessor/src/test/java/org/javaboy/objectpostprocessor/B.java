package org.javaboy.objectpostprocessor;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/9/16 5:05 下午
 */
public class B extends A {
    public void b() {
        System.out.println("B#b");
    }

    public static void main(String[] args) {
        new B().a();
    }
}
