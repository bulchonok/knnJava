
/*
Online Java - IDE, Code Editor, Compiler

Online Java is a quick and easy tool that helps you to build, compile, test your programs online.
*/
import java.util.List;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args) {
        System.out.println("enter k:");
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        String train_set = "iris.data";
        String test_set = "iris.test.data";
        knn.train(train_set);
        System.out.println(knn.test(k,test_set));

    }
}
