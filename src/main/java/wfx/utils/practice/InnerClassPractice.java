package wfx.utils.practice;

/**
 * Created by admin on 14-4-27.
 */
public class InnerClassPractice {
    public class OriginClass{
        public void print(){
            System.out.println("This is OriginClass");
        }
    }

    public static void main(String[] args){
        int a = 1;
        Integer b= 1;
        Object c = a;
        System.out.println(c instanceof Integer);
        c = b;
        System.out.println(c instanceof Integer);
//        OriginClass cls = new InnerClassPractice().new OriginClass(){
//            public void print(){
//                System.out.println("This is InnerClass");
//            }
//        };
//        cls.print();
//        cls = new InnerClassPractice().new OriginClass();
//        cls.print();
    }
}
