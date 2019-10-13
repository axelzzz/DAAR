package TME2;

import java.io.*;
import java.util.*;

public class KMP {

  public static void main(String arg[]) throws Exception {

    long startTime = System.currentTimeMillis();

    String filename = "testbeds/9.txt";
    ArrayList<String> strList = readFile(filename);

    String fact = "test";
    int[] retenue = get_retenue(fact);
    // for (int i : retenue){
    // System.out.print(i + " ");
    // }
    // System.out.println();

    // ArrayList<String> result = new ArrayList<String>();
    for (String str : strList) {
      if (matchingAlgo(fact, retenue, str)) {
        // result.add(str);
        System.out.println(str);
        // System.out.println();
      }
    }

    // System.out.println(result.size());
    // for (String s : result) {
    // System.out.println(s);
    // System.out.println();
    // }

    long endTime = System.currentTimeMillis();
    System.out.println(" Temps utilise : " + (endTime - startTime) + "ms");

  }

  private static ArrayList<String> readFile(String filename) throws Exception {
    ArrayList<String> strList = new ArrayList<String>();
    BufferedReader buffered_inputstreamreader = new BufferedReader(
        new InputStreamReader(new FileInputStream(filename)));
    String line;
    while ((line = buffered_inputstreamreader.readLine()) != null) {
      strList.add(line);
    }
    buffered_inputstreamreader.close();
    return strList;
  }

  public static int[] get_retenue(String facteur) {

    int[] retenue = new int[facteur.length() + 1];
    retenue[0] = -1;
    retenue[1] = 0;

    int i = 2;
    int l = 0;

    while (i < facteur.length()) {

      if (facteur.charAt(i) == facteur.charAt(0)) {
        retenue[i] = -1;
        l++;
        i++;
      } else if (facteur.charAt(i - 1) == facteur.charAt(l)) {
        l++;
        retenue[i] = l;
        if (facteur.charAt(i) == facteur.charAt(l)) {
          retenue[i] = 0;
        }
        i++;
      } else if (l != 0) {
        l = retenue[l];
      } else {
        retenue[i] = l;
        i++;
      }
    }

    return retenue;
  }

  public static boolean matchingAlgo(String facteur, int[] retenue, String texte) {

    int i = 0; // indice du texte
    int j = 0; // indice du facteur

    while (i < texte.length()) {

      if (j == facteur.length()) {
        return true;
      }
      if (texte.charAt(i) == facteur.charAt(j)) {
        i++;
        j++;
      } else {
        if (retenue[j] == -1) {
          i++;
          j = 0;
        } else {
          j = retenue[j];
        }
      }

    }

    if (j == facteur.length()) {
      return true;
    } else {
      return false;
    }
  }

}
