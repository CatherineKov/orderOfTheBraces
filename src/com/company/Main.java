package com.company;
import java.util.Arrays;

public class Main {

    /**
     * Проверка соответсвия парных скобок: {}()[]
     * @param bracesOp - открывающая скобка
     * @param bracesCl - закрываюшая скобка
     * @return - признак парности/непарности
     */
    public static boolean matchingBraces (char bracesOp, char bracesCl) {

        if ((bracesOp == '[' && bracesCl == ']')
                || (bracesOp == '(' && bracesCl == ')')
                || (bracesOp == '{' && bracesCl == '}')
                || (bracesOp == '0' && bracesCl == '0')) {
            return true;
        }
        else
            return false;
    }

    /**
     * Определение открыввающейся или закрывающейся скобки
     * @param brc - скобка
     * @return - true (открывающая скбка) / false (закрывающая скобка)
     */
    public static boolean determineTypeBrace (char brc){
        String openingBraces = "{([";
        String closingBraces = "})]";
        if (openingBraces.indexOf(brc)>=0) { // открывающая скобка
            return true;
        } else { // закрывающая скобка
            return false;
        }
    }

    /**
     * Определение правильности порядка расположения открывающихся и закрывающихся скобок
     * @param line - строка с распоожением скобок
     * @return - true (порядок правильный) / false (порядок неправильный)
     */
   public static boolean сheckingBracesString (String line){
        char arrayOp [] = new char[line.length()]; // массив с набором открытых скобок
        char arrayCl[] = new char [line.length()]; // массив с набором закрытых скобок
        int numberClosing = 0;
        int numberOpening = 0;
        Arrays.fill(arrayCl,'0');
        Arrays.fill(arrayOp,'0');
        for (int i = 0; i < line.length(); i++){
            if (determineTypeBrace(line.charAt(i))){ // если скобка открытая
                arrayOp[i-numberClosing] = line.charAt(i);
                numberOpening++;
            }
            else { // если скобка закрытая
                for (int k = numberOpening-1; k>=0;k--){ // с конца массива пока не пустое место
                    if (arrayCl[k] == '0'){ // если пустое место
                        arrayCl[k] = line.charAt(i);
                        numberClosing++;
                        break;
                    }
                }
            }
        }
        if (numberClosing!=numberOpening 
                || numberClosing == 0
                || numberOpening == 0) {
            return false;
        }
       for (int i = 0; i < line.length(); i++) { // Проведение соответсвия скобок
           if (!(matchingBraces(arrayOp[i],arrayCl[i])) ){
               return false;
           }
       }
       return true;
    }

    public static void main(String[] args) {

        String a = "{}()";
        String b = "([)]";
        String c = "[({}({}[]))]";
        String d = "[(){]}";
        String e = "[[[";
        String f = "))))";
        String g = "{([()])}";
        String h = "{([()])";

        System.out.println(сheckingBracesString(a)); // TRUE
        System.out.println(сheckingBracesString(b)); // FALSE
        System.out.println(сheckingBracesString(c)); // TRUE
        System.out.println(сheckingBracesString(d)); // FALSE
        System.out.println(сheckingBracesString(e)); // FALSE
        System.out.println(сheckingBracesString(g)); // TRUE
        System.out.println(сheckingBracesString(h)); // FALSE
    }
}
