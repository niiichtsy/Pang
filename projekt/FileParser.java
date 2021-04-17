package Projekt.proze21l_bojke_sulkowski.projekt;

import java.io.File; 
import java.io.FileNotFoundException;  
import java.util.Scanner; 
import java.util.List;
import java.util.ArrayList;


class FileParser {
  static int noOfBalls; /*zmienna określająca liczbę piłek*/
  static float yvelocity; /*zmienna określająca maksymalną prędkość wertykalną piłek*/
  static float xvelocity; /*zmienna określająca maksymalną prędkość horyzontalną piłek*/
  
  static void parse(){
    List<Float> parseList = new ArrayList<Float>(); 
    try {
      File config = new File("config.txt");
      Scanner parser = new Scanner(config);
      noOfBalls = Integer.parseInt(parser.nextLine());
      while (parser.hasNextLine()) {
        parseList.add(Float.parseFloat(parser.nextLine()));
      }
      parser.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
    yvelocity = parseList.get(0); 
    xvelocity = parseList.get(1); 
    float[] xpositions = new float[noOfBalls];    /*tablica pozycji x piłek*/
    float[] ypositions = new float[noOfBalls];    /*tablica pozycji y piłek*/
    for (int i=0; i<noOfBalls; i++)
    {
      int x = 2+i;
      int y = 2+i+noOfBalls;   
      xpositions[i] = parseList.get(x);
      ypositions[i] = parseList.get(y);
    }
  }
}

