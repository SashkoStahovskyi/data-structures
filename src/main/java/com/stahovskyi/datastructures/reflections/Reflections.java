package com.stahovskyi.datastructures.reflections;

public class Reflections {

    // Метод принимает класс и возвращает созданный объект этого класса

    public  Object createObject(Class clazz) {
        return clazz.getConstructors();
    }


    public void methodsWithoutParameters(Object value) {

    }


    public void printFinalSignature(Object value) {

    }


    public void printNoPublic(Class clazz) {

    }


    public void printRootAndInterface(Class clazz) {

    }


    public void changePrivateParameters(Object value) {

    }
}
