import java.util.Scanner;
public class Main
{
    static String[] numbersA = {"1","2","3","4","5","6","7","8","9","10"};
    static String[] numbersR1 = {"I","II","III","IV","V","VI","VII","VIII","IX","X"};
    static String[] numbersR2 = {"X","XX","XXX","XL","L","LX","LXX","LXXX","XC","C"};
    static boolean isArab = false;

    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String inputStr = scan.nextLine();
        verificator(inputStr);
        if (isArab)
        {
            calcArab(inputStr);
        }
        else calcRim(inputStr);
    }
    public static void calcRim(String inputStr)
    {
        int a = 0;
        int b = 0;
        String[] values = inputStr.split(" ");
        String strA = values[0];
        String strB = values[2];
        String operation = values[1];
        for (int i = 0; i < 10; i++) {
            if (numbersR1[i].equals(strA)) a = i + 1;
            if (numbersR1[i].equals(strB)) b = i + 1;
        }

        System.out.println(convertArabToRim(calc(a,b,operation)));
     }

     public static String convertArabToRim(int number)
     {
         try {
             if (number < 1) {
                 throw new Exception("в римской системе нет отрицательных чисел");
             }
         } catch (Exception e) {
             System.out.println(e.getMessage());
             System.exit(0);
         }
         if (number <= 10) return numbersR1[number-1];
         else if (number <= 99) return numbersR2[(number / 10) - 1] + numbersR1[(number % 10) - 1];
         else return numbersR2[9];
     }
    public static void calcArab(String inputStr)
    {
        final String[] elements = inputStr.split(" ");
        int a = Integer.parseInt(elements[0]);
        int b = Integer.parseInt(elements[2]);
        String operation = inputStr.split(" ")[1];
        System.out.println(calc(a,b,operation));
    }
    public static void verificator(String inputStrCheck)
    {
        final String[] elements = inputStrCheck.split(" ");
        int countArab = 0;
        int countRim = 0;
        try {
            if (elements.length != 3){
                throw new Exception("формат математической операции не удовлетворяет заданию");
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
            System.exit(0);
        }

        for (int i = 0; i < 10; i++) {
            if (elements[0].equals(numbersA[i]) || elements[2].equals(numbersA[i])) countArab++;
            else if (elements[0].equals(numbersR1[i]) || elements[2].equals(numbersR1[i])) countRim++;
        }

        try {
            if (countRim <= 1 && countArab <= 1) {
                throw new Exception("формат математической операции не удовлетворяет заданию");
            }
            if (countArab >= 1 && countRim >= 1) {
                throw new Exception("используются одновременно разные системы счисления ");
            }
            isArab = countArab >= 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.exit(0);
        }
    }

    static int calc(int a, int b, String operation)
    {
        int result = 0;
        switch (operation)
        {
            case "+": result = a + b;
                break;
            case "-": result = a - b;
                break;
            case "/": result = a / b;
                break;
            case "*": result = a * b;
                break;
        }
        return result;
    }

}