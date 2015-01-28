package wfx.utils.reflection;

import wfx.utils.pojo.Book;

import java.lang.reflect.Field;

/**
 * Created by admin on 14-4-29.
 */
public class FieldPractice {
    public void checkFieldType(){
        try {
            Field idField = Book.class.getDeclaredField("id");
//            idField.getA
            Field pageField = Book.class.getDeclaredField("page");
            System.out.println("id type is:" + idField.getType().getName());
            System.out.println("page type is:" + pageField.getType().getName());
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        FieldPractice fieldPractice = new FieldPractice();
        fieldPractice.checkFieldType();
    }
}
