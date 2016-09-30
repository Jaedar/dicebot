package application;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
//import java.*;
//import org.jibble.pircbot.*;
import java.io.*;
//import pircbot.jar;
import java.net.URLClassLoader;
import java.net.URL;

public class SturnnGUI extends JFrame implements ActionListener {
  private JButton exitButton;
  private JTextField inputField;
 private JButton startButton;
 private JTextArea textArea;
 
    
  public SturnnGUI() {
       redirectSystemStreams();
   /* URLClassLoader loader = (URLClassLoader)ClassLoader.getSystemClassLoader();  
    MyClassLoader l = new MyClassLoader(loader.getURLs());
    try {
    l.addURL(new URL("file:D:\\DrJava\\borealekiller\\pircbot.jar")); 
        
    Class c = l.loadClass("org.jibble.pircbot.PircBot");  
    System.out.println(c.getName());  
    }
    catch (Exception x){System.out.println(x);}
*/
 
    setUpGUI();
    
  }
  private void setUpGUI() {
    exitButton = new JButton("Exit");
    startButton= new JButton("Start");
    textArea= new JTextArea();
    setLayout(new GridLayout(3,1));
    JScrollPane scroll = new JScrollPane (textArea);
    scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    JPanel p1 = new JPanel();
    
   
    
    
    add(exitButton);
    add(startButton);
    //add(textArea,2);
    add(scroll);
    exitButton.addActionListener(this);
    startButton.addActionListener(this);
    setSize(400,300);
    setVisible(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
  }
  public void actionPerformed(ActionEvent e ) {
    if (e.getSource() == exitButton) {
      System.exit(0);
    } else if (e.getSource() == startButton) {
      String name="General_Sturnn";
      // Now start our bot up.
      MyBot bot = new MyBot(name);
      
      // Enable debugging output.
      bot.setVerbose(true);
      
      boolean b=false;
      // Connect to the IRC server.
      while(!b){
        try {
      bot.connect("irc.gamesurge.net", 6667);
      b=true;
        }
        catch(Exception x){
        System.out.println("timed out, trying again"+e.toString());
        }
      }
      
      // Join the #pircbot channel.
      bot.joinChannel("#codexdh");
      bot.joinChannel("#codexdl");
      
      // Load table from txts
      bot.load("D:/DrJava/ircbot");
     /* try {
      //MyBotMain.main(null);
      }
      catch (Exception x){
      System.out.println(x.toString());
      }*/
     // inputField.setText("");
    } else {
      System.out.println("Can not handle yet");
    }
  }
  
  /*  private void performCompute() {
    Stokenizer st = new Stokenizer(inputField.getText());
    Parser p = new Parser(st);
    Sexpr parsed = new Variable("*ERROR*");
    Sexpr evaluated;
    try {
      st.nextToken();
      parsed = p.assignment();
    } catch (SyntaxException e) {
      
    }
   // try {
      evaluated = parsed.eval(variables);
      resultLabel.setText(evaluated.toString());
    //} catch (EvaluationException e) {
      
    //}
  }*/
  public static void main(String[] args) {
    new SturnnGUI();
  }
  
  
  private void updateTextArea(final String text) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        textArea.append(text);
      }
    });
  }

  private void redirectSystemStreams() {
    OutputStream out = new OutputStream() {
      @Override
      public void write(int b) throws IOException {
        updateTextArea(String.valueOf((char) b));
      }

      @Override
      public void write(byte[] b, int off, int len) throws IOException {
        updateTextArea(new String(b, off, len));
      }

      @Override
      public void write(byte[] b) throws IOException {
        write(b, 0, b.length);
      }
    };

    System.setOut(new PrintStream(out, true));
    System.setErr(new PrintStream(out, true));
  }
  
  
}