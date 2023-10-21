package com.jdbc.practice;
abstract class DataStructure{
        abstract String getUrl();
        abstract String getUser();
        abstract String getPassword();
}
public class Data extends DataStructure{
        private final String url= "jdbc:mysql://127.0.0.1:3306/jdbcpractice";
        private final String user="root";
        private final String password="password";

        @Override
        protected String getUrl() {return url;}
        @Override
        protected String getUser() {return user;}
        @Override
        protected String getPassword() {return password;}
}
