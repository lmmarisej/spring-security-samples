package org.javaboy.objectpostprocessor;

/**
 * @author lmmarise.j@gmail.com
 * @since 2021/9/16 5:05 下午
 */
public class A {
    public void a() {
        System.out.println("A#a");
        b();
        this.b();
    }
    public void b() {
        System.out.println("A#b");
    }
}
