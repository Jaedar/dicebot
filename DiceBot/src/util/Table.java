package util;

import java.util.*;
import java.io.*;


// Table class
public class Table {
  // Map could be good for mapping, but not implemented
  //private Map<String, Interval> variables; 
  ArrayList<String> rows; //A list of the rows, is list so table size can be dynamic

  //These shud be private, but I am lazy
  Scanner scan;
  int size;
  String name;
  
  // Constructor, this gets a file from higher up in program
  public Table(File input)  throws IOException{
    size=0;
    // Initialize list, make sure input exists
    rows = new ArrayList<String> ();
    if (!input.exists()) {
      System.out.println("The file '" + input + "' does not exist");
      return;
    }
    
    // regular expression, no idea how it works, but it removes the .txt suffix
    name=(input.getName()).replaceFirst("[.][^.]+$", "");;
    scan = new Scanner(input);
    
    // Scans while there is stuff to scan, adds the lines to the list
    while(scan.hasNext()){
      rows.add(scan.nextLine());
      size++;
    }
  }
  // fetches the line on row n (arraylist starts at 1, not 0)
  public String get(int n){
    if (n<0)
      return rows.get(1);
    if (n>rows.size())
      return rows.get((rows.size()));
    
    return rows.get(n);
  }
  // gets a random line weighted equally
  public String get(){
   
    return get((int)(Math.random()*size+1));
  }

  
  // returns amount of rows
  public int length(){
    return rows.size();
  }
  // returns name of table
  public String getName(){
    return name;
  }
  
}