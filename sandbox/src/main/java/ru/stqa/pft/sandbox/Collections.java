package ru.stqa.pft.sandbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Denys on 4/30/2017.
 */
public class Collections {

  public static void main (String[] args){
    String[] langs = {"java","C#","Python","php"};

    List<String> languages = Arrays.asList("java","C#","Python","php");
//    languages.add("Java");
//    languages.add("C#");
//    languages.add("Pyhton");
//    languages.add("PHP");

    for (String lang : languages) {
      System.out.println("Я хочу выучить " + lang);
    }

  }
}
