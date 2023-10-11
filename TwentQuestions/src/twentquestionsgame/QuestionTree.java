package twentquestionsgame; 

import java.io.PrintStream;
import java.util.Scanner;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class             QuestionTree.java
 * Description  A class that includes methods to construct, save, load, and play with QuestionTrees
 * Project          20 Questions
 * Platform       jdk 1.8.0_214; NetBeans IDE 16; Windows 10
 * Date             6/16/2023
 * @author	   Iliya Belyak
 * @version    1.0.0
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class QuestionTree
{
        private QuestionNode overallRoot;
        private UserInterface ui;
        private int totalGames;
        private int gamesWon;;
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        QuestionTree() 
         * Description  Initializes the new question tree. Assigns the overall root to hold value computer.
         * @param       ui UserInterface
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // precondition: The ui is not null.
        //                        throws an IllegalArgument exception if not.
        // postcondition: Initializes new question tree.
        public QuestionTree(UserInterface ui)
        {
              // check if ui is null and throw exception if not
              if (ui == null)
                      throw new IllegalArgumentException();
              this.ui = ui;
              // create a single leaf node "computer"
              overallRoot = new QuestionNode("computer");
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        play() 
         * Description  Method that plays one complete guessing game with the user, asking yes/ no questions.
         *                      Sends root to recursive play method. Also adds 1 to totalGames variable.
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public void play()
        {
                play(overallRoot);
                totalGames++;
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        play() - recursive
         * Description  Method that plays one complete guessing game with the user, asking yes/ no questions.
         *                      Adds to gamesWon variable if won.
         * @param       root QuestionNode
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private void play(QuestionNode root)
        {
                if (root == null)
                        return;
                // if root is not a leaf
                 if (!root.isAnswer()) {
                        ui.print(root.data);
                        Boolean answer = ui.nextBoolean();
                        if (answer)
                                play(root.yes);
                         else 
                                play(root.no);
                        // if root is a leaf
                 } else {
                         ui.print("Would your object happen to be " + root.data +  "?");
                         // if guessed correctly
                         if (ui.nextBoolean()) {
                                 ui.println("I win!");
                                 gamesWon++;
                                 //else the guess was incorrect
                         } else {
                                 ui.print("I lose. What is your object?");
                                 String newObject = ui.nextLine();
                                 ui.print("Type a yes/no question to distinguish your\n" +
                                        "item from " + root.data + ": ");
                                 String newQuestion = ui.nextLine();
                                 ui.print("And what is the answer for your object?");
                                 // insert new question and object into tree
                                 if (ui.nextBoolean()) {
                                         // move current root(guessed) to right node
                                        root.no = new QuestionNode(root.data);
                                        // move new root(object) to left node
                                        root.yes = new QuestionNode(newObject);
                                        root.data = newQuestion;
                                 } else {
                                         // move current root(guessed) to left node
                                        root.yes = new QuestionNode(root.data);
                                        // move new root(object) to right node
                                        root.no = new QuestionNode(newObject);
                                        root.data = newQuestion;
                                 }
                         } 
                 }
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        save() 
         * Description  Stores the current tree state to a given printStream.
         *                      Sends root to recursive save method.
         * @param       output PrintStream
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // precondition: The output is not null 
        //                        throws an IllegalArgument exception if not.
        // postcondition: Uses recusive backtracking to save the tree to a printStream.
        //                          Sends root to recursive method.
        public void save(PrintStream output)
        {
                if (output == null)
                      throw new IllegalArgumentException();
                save(overallRoot, output);
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        save() - recursive 
         * Description  Stores the current tree state to a given printStream.
         * @param       output PrintStream
         * @param       root QuestionNode
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private void save(QuestionNode root, PrintStream output)
        {
                if (root != null) {
                      String type = "";
                      // if root.yes is not null, it means type is an answer
                      if (root.isAnswer())
                              type = "A";
                      // if not null then it is a question
                      else 
                              type = "Q";
                     // Add root to printstream
                     output.println(type + ": " + root.data);
                     save(root.yes, output); 
                     save(root.no, output);
                }
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        load() 
         * Description  Method that replaces the current tree by reading another tree from a file.
         *                      Sends root to recursive save method.
         * @param       input Scanner
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        // precondition: The input is not null 
        //                        throws an IllegalArgument exception if not.
        // postcondition: Uses recusive methods to load another tree.
        public void load(Scanner input)
        {
                if (input == null)
                      throw new IllegalArgumentException();
                overallRoot = load(input, null);               
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        load() - recursive
         * Description  Method that replaces the current tree by reading another tree from a file.
         * @param       input Scanner
         * @return       root QuestionNode
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        private QuestionNode load(Scanner input, QuestionNode root)
        {
                if (input == null || !input.hasNext())
                         return null;
                String data = input.nextLine();
                if (data.startsWith("A: ")) {
                        // create new root node without "A: "
                        root = new QuestionNode(data.substring(3));
                } else {
                        // create new root node without "Q: "
                        root= new QuestionNode(data.substring(3));
                        // go through all roots
                        root.yes = load(input, root);
                        root.no = load(input, root);
                }
                return root;
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        totalGames()
         * Description  Method that returns the totalGames variable.
         * @return       totalGames int
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public int totalGames() 
        { 
                return totalGames;
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        gamesWon()
         * Description  Method that returns the gamesWon variable.
         * @return       gamesWon int
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public int gamesWon()
        {
                return gamesWon;
        }
}
