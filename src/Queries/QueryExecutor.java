
package Queries;

import JDBCUtil.JDBCUtil;

import java.sql.*;
import java.util.Scanner;

public class QueryExecutor {

    public static void main(String[] args)  {
        String query1 = "select paper.id,paper.title,paper.abstract,author.email,author.first_name,author.last_name\n" +
                "from author author,paper paper\n" +
                "where author.authorID=paper.authorID;";
        String query2 = "select review.* from review review, paper paper\n" +
                "where review.paper_id = paper.id and\n" +
                "review.recommendation = 'recommended';";
        String query3 = "select count(*) as submitted_paper_count from paper;";
        String query5 = "delete from author where authorID=1;";

        //Query1
        System.out.println("*****************************************************************************************************");
        System.out.println("Running Query 1 : " + query1);
        System.out.println("*****************************************************************************************************");
        ResultSet query1Result = null;
        try {
            query1Result = JDBCUtil.getInstance().executeQueryAndGetResult(query1);
            //Print Query1 Result
            while (query1Result.next()) {
                System.out.println(query1Result.getString("paper.id")+"\t"+
                        query1Result.getString("paper.title")+"\t"+
                        query1Result.getString("paper.abstract")+"\t"+
                        query1Result.getString("author.email")+"\t"+
                        query1Result.getString("author.first_name")+"\t"+
                        query1Result.getString("author.last_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("*****************************************************************************************************\n\n\n");



        //Query2
        System.out.println("*****************************************************************************************************");
        System.out.println("Running Query 2 : " + query2);
        System.out.println("*****************************************************************************************************");
        ResultSet query2Result = null;
        try {
            query2Result = JDBCUtil.getInstance().executeQueryAndGetResult(query2);
            //Print Query1 Result
            while (query2Result.next()) {
                System.out.println(query2Result.getString("id")+"\t"+
                        query2Result.getString("recommendation")+"\t"+
                        query2Result.getString("merit_score")+"\t"+
                        query2Result.getString("paper_id")+"\t"+
                        query2Result.getString("readability_score")+"\t"+
                        query2Result.getString("reviewer_id")+"\t"+
                        query2Result.getString("originality_score")+"\t"+
                        query2Result.getString("relevance_score"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("*****************************************************************************************************\n\n\n");
        //Query3

        System.out.println("*****************************************************************************************************");
        System.out.println("Running Query 3 : " + query3);
        System.out.println("*****************************************************************************************************");
        ResultSet query3Result = null;
        try {
            query3Result = JDBCUtil.getInstance().executeQueryAndGetResult(query3);
            //Print Query3 Result
            while (query3Result.next()) {
                System.out.println("Submitted paper count = " + query3Result.getString("submitted_paper_count"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("*****************************************************************************************************\n\n\n");

        //Query4


        System.out.println("*****************************************************************************************************");
        System.out.println("Running Query 4 : NULL");
        System.out.println("*****************************************************************************************************");
        ResultSet query4Result = null;
        try {
            Scanner inputScanner = new Scanner(System.in);
            System.out.println("Author Email : ");
            String authorEmail = inputScanner.nextLine();
            System.out.println("Author F name : ");
            String authorFname = inputScanner.nextLine();
            System.out.println("Author L name : ");
            String authorLname = inputScanner.nextLine();
            String query4_1 = "insert into author values (\""+authorEmail+"\", \""+authorFname+"\", \""+authorLname+"\",null);";
            int query4_1Result = JDBCUtil.getInstance().executeQueryAndreturnID(query4_1);
            System.out.println("New Auther ID Created : " + query4_1Result);
            System.out.println("Paper Title : ");
            String paperTitle = inputScanner.nextLine();
            System.out.println("Paper Abstract : ");
            String paperAbstract = inputScanner.nextLine();
            System.out.println("Paper File Name : ");
            String paperFileName = inputScanner.nextLine();
            String query4_2 = "insert into paper values (null, \""+paperTitle+"\", \""+paperAbstract+"\", \""+paperFileName+"\","+query4_1Result+");";
            int query4_2Result = JDBCUtil.getInstance().executeQueryAndreturnID(query4_2);
            System.out.println("New Auther ID Created : " + query4_2Result);
            //Print Query1 Result
            } catch (SQLException ex) {
            ex.printStackTrace();
        }
        System.out.println("*****************************************************************************************************\n\n\n");





        //Query5
        System.out.println("*****************************************************************************************************");
        System.out.println("Running Query 5 : " + query5);
        System.out.println("*****************************************************************************************************");
        try {
            boolean query5Result = JDBCUtil.getInstance().executeUpdateQueryAndGetResult(query5);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //Print Query5 Result
        System.out.println("*****************************************************************************************************");

        //Close Connection after Use
        try {
            JDBCUtil.getInstance().closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
