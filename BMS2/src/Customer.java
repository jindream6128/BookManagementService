import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
//서버 SQL데이터 베이스와 연결하는거
public class Customer {
	public static void main(String[] args) {
		createTable();
	}
	
//	  public static void main(String[] args) { createTable();
//	  createCustomer("Danny","123456789","Male","23","Random Note....");
//	  ArrayList<String> list = getCustomers(); for(String item: list){
//	  System.out.println(item); }
//	  createCustomer("Daivd","4441122211","Female","25","Important Customer"); list
//	  = getCustomers(); for(String item: list){ System.out.println(item); } }
	 //SQL문법에 맞추어 입력되는 값들을 DB로 전송한다
	public static String [][] getCustomers(){
		try{
			Connection con = getConnection();
			PreparedStatement statement = con.prepareStatement("Select name, author, booknumber, publisher, category, introduction FROM customer");
			ResultSet results = statement.executeQuery();
			ArrayList<String[]> list = new ArrayList<String[]>();
			while(results.next()){
				list.add(new String[] {
						results.getString("name"),
						results.getString("author"),
						results.getString("booknumber"),
						results.getString("publisher"),
						results.getString("category"),
						results.getString("introduction"),
				});
			}
			System.out.println("The data has been fetched");
			String[][] arr = new String[list.size()][5];
			return list.toArray(arr);
			
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	// 테이블에 Header추가
	public static void createCustomer(String name, String author, String booknumber, String publisher, String category, String introduction){
		try{
			Connection con = getConnection();
			PreparedStatement insert = con.prepareStatement(""
					+ "INSERT INTO customer"
					+ "(name, author, booknumber, publisher, category, introduction) "
					+ "VALUE "
					+ "('"+name+"','"+author+"','"+booknumber+"','"+publisher+"','"+category+"','"+introduction+"')");
			insert.executeUpdate();
			System.out.println("The data has been saved!");
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	//Table 생성
	public static void createTable(){
		try{
			Connection con = getConnection();
			PreparedStatement create = con.prepareStatement(
					"CREATE TABLE IF NOT EXISTS "
					+ "customer(id int NOT NULL AUTO_INCREMENT,"
					+ "name varChar(255),"
					+ "author varChar(255),"
					+ "booknumber varChar(255),"
					+ "publisher varChar(255),"
					+ "category varChar(255),"
					+ "introduction varChar(255),"
					+ "PRIMARY KEY(id))");
			create.execute();
		}catch(Exception e){
			System.out.println(e.getMessage());
		}finally{
			System.out.println("Table successfully created");
		}
	}
	
	//MYSQL 연결 //dbconnection 정보
	public static Connection getConnection() {
		try {
			String driver = ""; 
			String url = "";
			String user = "";
			String pass ="";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url,user,pass);
			System.out.println("The Connection Succesful");
			return con;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	} 


}
