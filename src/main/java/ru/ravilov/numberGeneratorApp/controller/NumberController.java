package ru.ravilov.numberGeneratorApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@RestController
@RequestMapping("/number")
public class NumberController {
    String s;
    //генерируем случайный номер
    public  String randomNumber(){
        String letters = "АЕТОРНУКХСВМ";
        char[] charArray = letters.toCharArray();
        char[] oneArray = new char[1];
        char[] twoArray = new char[2];
        Random random = new Random();
        String REGION_OF_NUMBER = " 116 RUS";
        for (int i = 0; i < oneArray.length; i++) {
            oneArray[i] = charArray[random.nextInt(12)];
        }
        for (int i = 0; i < twoArray.length; i++) {
            twoArray[i] = charArray[random.nextInt(12)];
        }
        String one = new String(oneArray);
        String three = new String(twoArray);
        String number = String.format("%d%d%d",generationOfFiguresOfNumber(),generationOfFiguresOfNumber(),generationOfFiguresOfNumber());
        String sum = one + number + three + REGION_OF_NUMBER;
        return sum;
    }
    //генерация случайного трехзначного числа
    public int generationOfFiguresOfNumber()
    {
        int number = 0;
        int minValueNumber = 0;
        int maxValueNumber = 10;
        number = (int) ((Math.random() * (maxValueNumber - minValueNumber)) + minValueNumber);
        return number;
    }
    //итерация
    public String countNumber(String source){
        int extractNumbers = Integer.parseInt(source.substring(1,4));
        StringBuilder replaceNumber;
        StringBuilder builderTrans;
        String valueNumberFive = source.substring(5,6);
            if (extractNumbers != 1000) {
                extractNumbers++;
                replaceNumber = new StringBuilder(source);
                builderTrans = replaceNumber.replace(1, 4, String.valueOf(extractNumbers));
            } else {
                String zeroNumbers = "000";
                replaceNumber = new StringBuilder(source);
                builderTrans = replaceNumber.replace(1, 4, zeroNumbers);
                builderTrans = replaceNumber.replace(5, 6, letterConversion(valueNumberFive));
            }
        System.out.println(builderTrans);
        return builderTrans.toString();
    }

    public String letterConversion(String value){
        switch (value){
            case "А":
                value = "Е";
                break;
            case "Е":
                value = "Т";
                break;
            case "Т":
                value = "О";
                break;
            case "О":
                value = "Р";
                break;
            case "Р":
                value = "Н";
                break;
            case "Н":
                value = "У";
                break;
            case "У":
                value = "К";
                break;
            case "К":
                value = "Х";
                break;
            case "Х":
                value = "С";
                break;
            case "С":
                value = "В";
                break;
            case "В":
                value = "М";
                break;
            case "М":
                value = "А";
                break;
        }
        return value;
    }

    @GetMapping("/random")
    public String getRandomMethod(){
        ArrayList<String> numberCheckerList = new ArrayList<>();
         s = randomNumber();
         numberCheckerList.add(s);
         if (!Arrays.asList(numberCheckerList).contains(s)){
             return s;
         } else {
             return "Номер уже существует";
         }
    }

    @GetMapping("/next")
    public String getNextMethod()
    {
        return countNumber(s);
    }
}
