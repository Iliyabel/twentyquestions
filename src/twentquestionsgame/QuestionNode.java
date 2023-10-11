package twentquestionsgame;
/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Class             QuestionNode.java
 * Description  A class that represents a place in memory where we can store a questions data and
 *                      the location of the data to the left and right.
 *                      Also includes a toString method for printing data and isAnswer Boolean method 
 *                      to find out if the question node is a leaf.
 * Project          20 Questions
 * Platform       jdk 1.8.0_214; NetBeans IDE 16; Windows 10
 * Date             6/16/2023
 * @author	   Iliya Belyak
 * @version    1.0.0
 *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class QuestionNode
{
        public String data;
        public QuestionNode yes;
        public QuestionNode no;
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        QuestionNode() 
         * Description  Initializes a new QuestionNode with a string for data.
         * @param       data String
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public QuestionNode(String data) 
        {
                this(data, null, null);
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        QuestionNode() 
         * Description  Initializes a new QuestionNode with the .yes and .no locations.
         * @param       data String
         * @param       yes QuestionNode
         * @param       no QuestionNode
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public QuestionNode(String data, QuestionNode yes, QuestionNode no) 
        {
               this.data = data;
               this.yes = yes;
               this.no = no;
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        isAnswer() 
         * Description  checks if root.yes and .no are null, if they are then that means it is the answer.
         * @return       true or false Boolean
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public boolean isAnswer()
        {
                return yes == null && no == null;
        }
        /**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
         * Method        toString() 
         * Description  Gets the data of a QuestionNode.
         * @return       data String
         * @author      Iliya Belyak 
         * Date              6/16/2023 
         * History Log   
        *~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
        public String toString() 
        {
                return data;
        }
}
