package wfx.utils.practice;

import wfx.utils.annotation.Table;
import wfx.utils.pojo.Book;
import wfx.utils.pojo.SpecialBook;

import java.lang.reflect.Field;

/**
 * Created by admin on 14-4-27.
 */
public class AnnotationPractice {
    /**
     * 结论：annotation不能继承，在父类中定义了的annotation，不能在子类中直接使用。
     */
    public void classAnnotationExtend(){
        Table table = (Table)Book.class.getAnnotation(Table.class);
        Table table2 = (Table)SpecialBook.class.getAnnotation(Table.class);
        System.out.println(table.tableName());
        System.out.println(table2.tableName());
    }

    public void fieldValue(SpecialBook specialBook) throws IllegalAccessException {
        Field[] fields = SpecialBook.class.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(specialBook));
        }
        Field[] parentFields = SpecialBook.class.getSuperclass().getDeclaredFields();
        System.out.println("show parents fields ........");
        for(Field field : parentFields){
            field.setAccessible(true);
            System.out.println(field.getName() + ":" + field.get(specialBook));
        }
    }
    public static void main(String[] args){
        AnnotationPractice annotationPratic = new AnnotationPractice();
        SpecialBook specialBook = new SpecialBook();
        specialBook.setName("specialBookName");
        specialBook.setSpecialType(33);
        try {
            annotationPratic.fieldValue(specialBook);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
