package com.jdbc.practice;
import java.sql.*;
import java.util.Scanner;
/*
1. import --->java.sql package
2.load and register the driver -->4 types of java: ms access= jdbc1(jdbcodbc bridge -java7 or 8), mysql:mysql driver com.mysql.jdbc.Driver package
3.create connection --->object of connection interface
4.create a statement --->object of statement interface
5.execute the query --->execute query
6.process the result
7.close
*/

public class MainClass extends Data {
    public static void printData(ResultSet rs) throws SQLException {
        System.out.println("Sno\t\tName\t\tRollNo");
        while(rs.next()) {
            String name = rs.getInt("Sno")+"\t\t"+rs.getString("Name")+"\t\t"+rs.getInt("RollNo");
            System.out.println(name);
        }
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Do you want to output or insert query?");
        Scanner sc = new Scanner(System.in);

        String whtToDo = sc.nextLine();
        //Class.forName("com.mysql.jdbc.Driver"); it is now not useful loaded automatically.
        //Connection is an interface so to use it we need some method that implements connection class. so the class is getConnection in DriverManager
        Data data = new Data();
        Connection con = DriverManager.getConnection(data.getUrl(),data.getUser(), data.getPassword());
        Statement st = con.createStatement();
        if(whtToDo.equalsIgnoreCase("output")) {
            try {
                System.out.println("Enter query below:");
                String query = sc.nextLine();
                ResultSet rs = st.executeQuery(query);
                printData(rs);
            }catch (Exception e){
                System.out.println("!!!!check code or query!!!!");
            }
        }else if(whtToDo.equalsIgnoreCase("insert")){
            try {
                System.out.println("Enter values below:");
                System.out.print("Enter sno:");
                int n= sc.nextInt();
                System.out.print("Enter rollno:");
                int rollno= sc.nextInt();
                System.out.print("Enter name:");
                String name= sc.next();
                //String query = sc.nextLine();
                String query = "insert into students values ("+n+","+rollno+",\""+name+"\")";
                int count = st.executeUpdate(query);
                System.out.println(count+" row/s affected.");
            }catch (Exception e){
                System.out.println("!!!!check code or query!!!!");
            }
        }
/*
DDL(Changing structure) - execute
DML(insertion, updation, deletion) - executeUpdate() returns no of rows affected
DQL(parse query to fetch data)- executeQuery returns table data as string
TCL(control grant permission/revoke permission
 */


        st.close();
        con.close();
    }
}
