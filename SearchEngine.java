/*
 * Name: Michael Ren
 * PID:  A16430317
 */

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
 import java.util.Scanner;
import java.util.Set;

import org.w3c.dom.Node;
 
 /**
  * Search Engine implementation.
  *
  * @author Michael Ren 
  * @since  8/29/2024
  */
 public class SearchEngine {
 
     /**
      * Populate BSTrees from a file
      *
      * @param movieTree  - BST to be populated with actors
      * @param studioTree - BST to be populated with studios
      * @param ratingTree - BST to be populated with ratings
      * @param fileName   - name of the input file
      * @returns false if file not found, true otherwise
      */
     public static boolean populateSearchTrees(
             BSTree<String> movieTree, BSTree<String> studioTree,
             BSTree<String> ratingTree, String fileName
     ) {
         // open and read file
         File file = new File(fileName);
         try {
             Scanner scanner = new Scanner(file);
             while (scanner.hasNextLine()) {
                 // read 5 lines per batch:
                 // movie, cast, studios, rating, trailing hyphen
                 String movie = scanner.nextLine().trim();
                 String cast[] = scanner.nextLine().split(" ");
                 String studios[] = scanner.nextLine().split(" ");
                 String rating = scanner.nextLine().trim();
                 scanner.nextLine();
 
                 //Populate tree
                for (String actor:cast)
                {
                    insertTree(movieTree,actor,movie);
                    insertTree(ratingTree,actor,rating);
                }
                for (String studio:studios)
                {
                    insertTree(studioTree,studio,movie);
                }

                 /* TODO */

                 // populate three trees with the information you just read
                 // hint: create a helper function and reuse it to build all three trees
 
             }
             scanner.close();
         } catch (FileNotFoundException e) {
             return false;
         }
         return true;
     }

     private static void insertTree(BSTree<String> tree, String key, String value) {
        if (value != null && !value.isEmpty()) {
            tree.insert(key);
            if (tree.findDataList(key).contains(value))
            {return;}
            else
            tree.insertData(key, value);
        }
    }
 
     /**
      * Search a query in a BST
      *
      * @param searchTree - BST to be searched
      * @param query      - query string
      */
     public static void searchMyQuery(BSTree<String> searchTree, String query) {
 
         /* TODO */
         // process query
         String[] keys = query.toLowerCase().split(" ");

         LinkedList<LinkedList<String>> result = new LinkedList<>();
         LinkedList<String> items = searchTree.findDataList(keys[0]);
         result.add(new LinkedList<>());
         for (String key:items)
         {
            boolean counter=false;
            for (String option: Arrays.copyOfRange(keys,1,keys.length))
            {
             if (searchTree.findDataList(option).contains(key))
                counter=true;
             else 
                counter=false;

            }
            if (counter==true)
            {result.get(0).add(key);}
            else
            {;}
         }

         for (int i=1;i<=result.size();i++)
         {
            result.add(new LinkedList<String>());
            
            for (int j=0;j<searchTree.findDataList(keys[i-1]).size();j++)
            {
                LinkedList<String>tempList=searchTree.findDataList(keys[i-1]);
                for (int k=0; k<result.size();k++)
                {
                    if (result.get(k).contains(tempList.get(j)))
                    {break;}
                }
                result.get(i).add(tempList.get(j));
            }

         }
        
         for (LinkedList<String >r:result)
         {print(query, r);}
         
 
         // search and output intersection results
         // hint: list's addAll() and retainAll() methods could be helpful
 
         // search and output individual results
         // hint: list's addAll() and removeAll() methods could be helpful
 
     }
 
     /**
      * Print output of query
      *
      * @param query     Query used to search tree
      * @param documents Output of documents from query
      */
     public static void print(String query, LinkedList<String> documents) {
         if (documents == null || documents.isEmpty())
             System.out.println("The search yielded no results for " + query);
         else {
             Object[] converted = documents.toArray();
             Arrays.sort(converted);
             System.out.println("Documents related to " + query
                     + " are: " + Arrays.toString(converted));
         }
     }
 
     /**
      * Main method that processes and query the given arguments
      *
      * @param args command line arguments
      */
     public static void main(String[] args) {
 
         /* TODO */
         // initialize search trees
         BSTree<String> movieTree=new BSTree<String>();
         BSTree<String> studioTree=new BSTree<String>();
         BSTree<String> ratingTree=new BSTree<String>();
         // process command line arguments
         String fileName = args[0];
         int searchKind = Integer.parseInt(args[1]);
         String[] keywords=Arrays.copyOfRange(args,2,args.length);
 
         // populate search trees

         populateSearchTrees(movieTree, studioTree, ratingTree, fileName);

         // choose the right tree to query
        if (searchKind==0)
        {searchMyQuery(movieTree, fileName);}
        else if (searchKind==1)
        {searchMyQuery(studioTree, fileName);}
        else {searchMyQuery(ratingTree, fileName);}
     }
 }