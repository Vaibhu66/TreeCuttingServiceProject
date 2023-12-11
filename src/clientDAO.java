import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 * Servlet implementation class Connect
 */
@WebServlet("/clientDAO")
public class clientDAO 
{
	private static final long serialVersionUID = 1L;
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
	public clientDAO(){}
	
	/** 
	 * @see HttpServlet#HttpServlet()
     */
    protected void connect_func() throws SQLException {
    	//uses default connection to the database
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/treecuttingdb?allowPublicKeyRetrieval=true&useSSL=false&user=root&password=Root*1234");
            System.out.println(connect);
            System.out.println("Connection successfull");
        }
    }

	//connect to the database 
    public void connect_func(String username, String password) throws SQLException {
        if (connect == null || connect.isClosed()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                throw new SQLException(e);
            }
            connect = (Connection) DriverManager
  			      .getConnection("jdbc:mysql://127.0.0.1:3306/treecuttingdb?"
  			          + "useSSL=false&user=" + username + "&password=" + password);
            System.out.println(connect);
            System.out.println("Connection successfull");
        }
    }
    
    public List<client> listAllClient() throws SQLException {
    	//System.out.println("inside admin");
        List<client> listClient = new ArrayList<client>();        
        String sql = "SELECT * FROM client";      
        connect_func("root","Root*1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String creditCardInfo = resultSet.getString("CreditCardInfo");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");

        	client clients = new client(clientID, firstName, lastName, address, creditCardInfo, phoneNumber, email);

            listClient.add(clients);
        }
        System.out.println(listClient);
        resultSet.close();
        disconnect();        
        return listClient;
    }
    
	
	
	public List<client> listGoodClients() throws SQLException {
    	//System.out.println("inside admin");
        List<client> listGoodClients = new ArrayList<client>();        
        String sql = "SELECT c.ClientID, c.FirstName, c.LastName, c.Address, c.CreditCardInfo, c.PhoneNumber, c.Email\n"
        		+ "FROM Client c\n"
        		+ "WHERE EXISTS (\n"
        		+ "    SELECT 1\n"
        		+ "    FROM BillDetails bd\n"
        		+ "    JOIN OrderDetails od ON bd.OrderID = od.OrderID\n"
        		+ "    JOIN Quote q ON od.QuoteID = q.QuoteID\n"
        		+ "    JOIN TreeRequest tr ON q.RequestID = tr.RequestID\n"
        		+ "    WHERE tr.ClientID = c.ClientID\n"
        		+ "      AND bd.Status = 'Paid'\n"
        		+ "      AND bd.BilledDate BETWEEN tr.RequestDate AND DATE_ADD(tr.RequestDate, INTERVAL 1 DAY)\n"
        		+ ")\n";    
				connect_func("root","Root*1234");      
				statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String creditCardInfo = resultSet.getString("CreditCardInfo");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");

        	client clients = new client(clientID, firstName, lastName, address, creditCardInfo, phoneNumber, email);

        	listGoodClients.add(clients);
        }
        System.out.println(listGoodClients);
        resultSet.close();
        disconnect();        
        return listGoodClients;
    }
    
    public List<client> listBadClients() throws SQLException {
    	//System.out.println("inside admin");
        List<client> listBadClients = new ArrayList<client>();        
        String sql = "SELECT c.ClientID, c.FirstName, c.LastName, c.Address, c.CreditCardInfo, c.PhoneNumber, c.Email\r\n"
        		+ "FROM Client c "
        		+ "WHERE NOT EXISTS ( "
        		+ "    SELECT 1 "
        		+ "    FROM BillDetails bd "
        		+ "    JOIN OrderDetails od ON bd.OrderID = od.OrderID "
        		+ "    JOIN Quote q ON od.QuoteID = q.QuoteID "
        		+ "    JOIN TreeRequest tr ON q.RequestID = tr.RequestID "
        		+ "    WHERE tr.ClientID = c.ClientID "
        		+ "      AND bd.Status <> 'Paid' "
        		+ "      AND bd.BilledDate <= DATE_SUB(NOW(), INTERVAL 1 WEEK) "
        		+ "); "
        		+ "";      
        connect_func("root","Root*1234");      
        statement = (Statement) connect.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String creditCardInfo = resultSet.getString("CreditCardInfo");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");

        	client clients = new client(clientID, firstName, lastName, address, creditCardInfo, phoneNumber, email);

        	listBadClients.add(clients);
        }
        System.out.println(listBadClients);
        resultSet.close();
        disconnect();        
        return listBadClients;
    }
   	
    public List<clientwithTreeinfo> listBigClient() throws SQLException {
    	//System.out.println("inside admin");
        List<clientwithTreeinfo> listbigClient = new ArrayList<>();        
        String sql = "SELECT\n"
        		+ "    c.ClientID,\n"
        		+ "    c.FirstName,\n"
        		+ "    c.LastName,\n"
        		+ "    c.Address,\n"
        		+ "    c.PhoneNumber,\n"
        		+ "    c.Email,\n"
        		+ "    MAX(q.QuoteID) AS QuoteID,\n"
        		+ "    MAX(tr.RequestID) AS RequestID,\n"
        		+ "    MAX(t.TreeinfoID) AS TreeinfoID,\n"
        		+ "    MAX(od.OrderID) AS OrderID,\n"
        		+ "    MAX(t.Height) AS Height,\n"
        		+ "    MAX(nt.NumberOfTrees) AS NumberOfTrees\n"
        		+ "FROM\n"
        		+ "    client c\n"
        		+ "JOIN\n"
        		+ "    treerequest tr ON c.ClientID = tr.ClientID\n"
        		+ "JOIN\n"
        		+ "    quote q ON tr.RequestID = q.RequestID\n"
        		+ "JOIN\n"
        		+ "    treeinformation t ON q.RequestID = t.RequestID\n"
        		+ "LEFT JOIN\n"
        		+ "    orderdetails od ON q.QuoteID = od.QuoteID\n"
        		+ "JOIN\n"
        		+ "    (\n"
        		+ "        SELECT\n"
        		+ "            tr.ClientID,\n"
        		+ "            COUNT(DISTINCT tr.RequestID) AS NumberOfTrees\n"
        		+ "        FROM\n"
        		+ "            treerequest tr\n"
        		+ "        GROUP BY\n"
        		+ "            tr.ClientID\n"
        		+ "        HAVING\n"
        		+ "            NumberOfTrees > 0\n"
        		+ "    ) nt ON c.ClientID = nt.ClientID\n"
        		+ "WHERE\n"
        		+ "    nt.NumberOfTrees = (\n"
        		+ "        SELECT\n"
        		+ "            MAX(NumberOfTrees)\n"
        		+ "        FROM\n"
        		+ "            (\n"
        		+ "                SELECT\n"
        		+ "                    COUNT(DISTINCT tr.RequestID) AS NumberOfTrees\n"
        		+ "                FROM\n"
        		+ "                    treerequest tr\n"
        		+ "                GROUP BY\n"
        		+ "                    tr.ClientID\n"
        		+ "                HAVING\n"
        		+ "                    NumberOfTrees > 0\n"
        		+ "            ) subquery\n"
        		+ "    )\n"
        		+ "GROUP BY\n"
        		+ "    c.ClientID, c.FirstName, c.LastName, c.Address, c.PhoneNumber, c.Email\n"
        		+ "ORDER BY\n"
        		+ "    NumberOfTrees DESC\n";
        
      try  {
    	  connect_func("root","Root*1234");      
    	  statement = (Statement) connect.createStatement();
    	  ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");
        	String quoteID = resultSet.getString("QuoteID");
        	String requestID = resultSet.getString("RequestID");
        	String treeinfoID = resultSet.getString("TreeinfoID");
        	String orderID = resultSet.getString("OrderID");
            double height = resultSet.getDouble("Height");
            int numberOfTrees = resultSet.getInt("NumberOfTrees");
        	
        	clientwithTreeinfo bigclients = new clientwithTreeinfo(clientID, firstName, lastName, address, phoneNumber, email, quoteID, requestID, treeinfoID, orderID, height, numberOfTrees);

            listbigClient.add(bigclients);
        }
        System.out.println(listbigClient);
        resultSet.close();
      } catch(SQLException e) {
    	  e.printStackTrace();
      }
        disconnect();        
        return listbigClient;
    }
    
    public List<clientwithTreeinfo> oneTreeQuote() throws SQLException {
    	//System.out.println("inside admin");
        List<clientwithTreeinfo> onetreequote = new ArrayList<>();        
        String sql = "SELECT\n"
        		+ "    c.ClientID,\n"
        		+ "    c.FirstName,\n"
        		+ "    c.LastName,\n"
        		+ "    c.Address,\n"
        		+ "    c.PhoneNumber,\n"
        		+ "    c.Email,\n"
        		+ "    MAX(q.QuoteID) AS QuoteID,\n"
        		+ "    MAX(tr.RequestID) AS RequestID,\n"
        		+ "    MAX(t.TreeinfoID) AS TreeinfoID,\n"
        		+ "    MAX(od.OrderID) AS OrderID,\n"
        		+ "    MAX(t.Height) AS Height,\n"
        		+ "    MAX(nt.NumberOfTrees) AS NumberOfTrees\n"
        		+ "FROM\n"
        		+ "    client c\n"
        		+ "JOIN\n"
        		+ "    treerequest tr ON c.ClientID = tr.ClientID\n"
        		+ "JOIN\n"
        		+ "    quote q ON tr.RequestID = q.RequestID\n"
        		+ "JOIN\n"
        		+ "    treeinformation t ON q.RequestID = t.RequestID\n"
        		+ "LEFT JOIN\n"
        		+ "    orderdetails od ON q.QuoteID = od.QuoteID\n"
        		+ "JOIN\n"
        		+ "    (\n"
        		+ "        SELECT\n"
        		+ "            tr.ClientID,\n"
        		+ "            COUNT(DISTINCT tr.RequestID) AS NumberOfTrees\n"
        		+ "        FROM\n"
        		+ "            treerequest tr\n"
        		+ "        GROUP BY\n"
        		+ "            tr.ClientID\n"
        		+ "        HAVING\n"
        		+ "            NumberOfTrees = 1  -- Only one tree involved\n"
        		+ "    ) nt ON c.ClientID = nt.ClientID\n"
        		+ "WHERE\n"
        		+ "    q.Status IN ('Approved', 'Accepted')  -- Consider both Approved and Accepted quotes\n"
        		+ "GROUP BY\n"
        		+ "    c.ClientID, c.FirstName, c.LastName, c.Address, c.PhoneNumber, c.Email\n"
        		+ "ORDER BY\n"
        		+ "    NumberOfTrees DESC\n";
        
      try  {
    	  connect_func("root","Root*1234");      
    	  statement = (Statement) connect.createStatement();
    	  ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");
        	String quoteID = resultSet.getString("QuoteID");
        	String requestID = resultSet.getString("RequestID");
        	String treeinfoID = resultSet.getString("TreeinfoID");
        	String orderID = resultSet.getString("OrderID");
            double height = resultSet.getDouble("Height");
            int numberOfTrees = resultSet.getInt("NumberOfTrees");
        	
        	clientwithTreeinfo onetreequoteclients = new clientwithTreeinfo(clientID, firstName, lastName, address, phoneNumber, email, quoteID, requestID, treeinfoID, orderID, height, numberOfTrees);

        	onetreequote.add(onetreequoteclients);
        }
        System.out.println(onetreequote);
        resultSet.close();
      } catch(SQLException e) {
    	  e.printStackTrace();
      }
        disconnect();        
        return onetreequote;
    }
    
    public List<clientwithTreeinfo> listEasyClient() throws SQLException {
    	//System.out.println("inside admin");
        List<clientwithTreeinfo> listeasyClient = new ArrayList<>();        
        String sql = "SELECT DISTINCT c.ClientID, c.FirstName, c.LastName, c.Address, c.PhoneNumber, c.Email\n"
        		+ "FROM client c\n"
        		+ "JOIN treerequest tr ON c.ClientID = tr.ClientID\n"
        		+ "JOIN quote q ON tr.RequestID = q.RequestID\n"
        		+ "WHERE q.Status IN ('accepted', 'approved')\n"
        		+ "  AND q.Note = 'Initial quote' -- Additional criteria for the 'Note' field\n"
        		+ "  AND c.ClientID NOT IN (\n"
        		+ "    SELECT c2.ClientID\n"
        		+ "    FROM client c2\n"
        		+ "    JOIN treerequest tr2 ON c2.ClientID = tr2.ClientID\n"
        		+ "    JOIN quote q2 ON tr2.RequestID = q2.RequestID\n"
        		+ "    WHERE q2.Status = 'negotiating'\n"
        		+ "  )\n";
        
      try  {
    	  connect_func("root","Root*1234");      
    	  statement = (Statement) connect.createStatement();
    	  ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");
        	
        	clientwithTreeinfo easyclients = new clientwithTreeinfo(clientID, firstName, lastName, address, phoneNumber, email);

            listeasyClient.add(easyclients);
        }
        System.out.println(listeasyClient);
        resultSet.close();
      } catch(SQLException e) {
    	  e.printStackTrace();
      }
        disconnect();        
        return listeasyClient;
    }
    
    public List<clientwithTreeinfo> listProspectiveClient() throws SQLException {
    	//System.out.println("inside admin");
        List<clientwithTreeinfo> listprospectiveClient = new ArrayList<>();        
        String sql = "SELECT\n"
        		+ "    c.ClientID,\n"
        		+ "    c.FirstName,\n"
        		+ "    c.LastName,\n"
        		+ "    c.Address,\n"
        		+ "    c.PhoneNumber,\n"
        		+ "    c.Email,\n"
        		+ "    MAX(q.QuoteID) AS QuoteID\n"
        		+ "FROM\n"
        		+ "    client c\n"
        		+ "JOIN\n"
        		+ "    treerequest tr ON c.ClientID = tr.ClientID\n"
        		+ "JOIN\n"
        		+ "    quote q ON tr.RequestID = q.RequestID\n"
        		+ "LEFT JOIN\n"
        		+ "    orderdetails od ON q.QuoteID = od.QuoteID\n"
        		+ "WHERE\n"
        		+ "    od.QuoteID IS NULL\n"
        		+ "GROUP BY\n"
        		+ "    c.ClientID, c.FirstName, c.LastName, c.Address, c.PhoneNumber, c.Email\n"
        		+ "ORDER BY\n"
        		+ "    QuoteID DESC\n";
        
      try  {
    	  connect_func("root","Root*1234");      
    	  statement = (Statement) connect.createStatement();
    	  ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");
        	String quoteID = resultSet.getString("QuoteID");
        	
        	clientwithTreeinfo prosclients = new clientwithTreeinfo(clientID, firstName, lastName, address, phoneNumber, email, quoteID);

        	listprospectiveClient.add(prosclients);
        }
        System.out.println(listprospectiveClient);
        resultSet.close();
      } catch(SQLException e) {
    	  e.printStackTrace();
      }
        disconnect();        
        return listprospectiveClient;
    }
    
    public List<clientwithTreeinfo> listHighestTree() throws SQLException {
    	//System.out.println("inside admin");
        List<clientwithTreeinfo> listhighestTree = new ArrayList<>();        
        String sql = "WITH TreeData AS (\n"
        		+ "    SELECT\n"
        		+ "        c.ClientID,\n"
        		+ "        c.FirstName,\n"
        		+ "        c.LastName,\n"
        		+ "        c.Address,\n"
        		+ "        c.PhoneNumber,\n"
        		+ "        c.Email,\n"
        		+ "        q.QuoteID,\n"
        		+ "        tr.RequestID,\n"
        		+ "        t.TreeinfoID,\n"
        		+ "        od.OrderID,\n"
        		+ "        t.Height,\n"
        		+ "        ROW_NUMBER() OVER (PARTITION BY c.ClientID ORDER BY t.Height DESC) AS RowNum\n"
        		+ "    FROM\n"
        		+ "        client c\n"
        		+ "    JOIN\n"
        		+ "        treerequest tr ON c.ClientID = tr.ClientID\n"
        		+ "    JOIN\n"
        		+ "        quote q ON tr.RequestID = q.RequestID\n"
        		+ "    JOIN\n"
        		+ "        treeinformation t ON q.RequestID = t.RequestID\n"
        		+ "    LEFT JOIN\n"
        		+ "        orderdetails od ON q.QuoteID = od.QuoteID\n"
        		+ "    WHERE\n"
        		+ "        od.Status = 'Completed'\n"
        		+ ")\n"
        		+ ", MaxHeight AS (\n"
        		+ "    SELECT\n"
        		+ "        MAX(Height) AS MaxHeight\n"
        		+ "    FROM\n"
        		+ "        TreeData\n"
        		+ ")\n"
        		+ "SELECT\n"
        		+ "    ClientID,\n"
        		+ "    FirstName,\n"
        		+ "    LastName,\n"
        		+ "    Address,\n"
        		+ "    PhoneNumber,\n"
        		+ "    Email,\n"
        		+ "    QuoteID,\n"
        		+ "    RequestID,\n"
        		+ "    TreeinfoID,\n"
        		+ "    OrderID,\n"
        		+ "    Height\n"
        		+ "FROM\n"
        		+ "    TreeData\n"
        		+ "WHERE\n"
        		+ "    RowNum = 1\n"
        		+ "    AND Height = (SELECT MaxHeight FROM MaxHeight)\n";
        
      try  {
    	  connect_func("root","Root*1234");      
    	  statement = (Statement) connect.createStatement();
    	  ResultSet resultSet = statement.executeQuery(sql);
         
        while (resultSet.next()) {
        	String clientID = resultSet.getString("ClientID");
        	String firstName = resultSet.getString("FirstName");
        	String lastName = resultSet.getString("LastName");
        	String address = resultSet.getString("Address");
        	String phoneNumber = resultSet.getString("PhoneNumber");
        	String email = resultSet.getString("Email");
        	String quoteID = resultSet.getString("QuoteID");
        	String requestID = resultSet.getString("RequestID");
        	String treeinfoID = resultSet.getString("TreeinfoID");
        	String orderID = resultSet.getString("OrderID");
            double height = resultSet.getDouble("Height");
        	
        	clientwithTreeinfo highT = new clientwithTreeinfo(clientID, firstName, lastName, address, phoneNumber, email, quoteID, requestID, treeinfoID, orderID, height);

        	listhighestTree.add(highT);
        }
        System.out.println(listhighestTree);
        resultSet.close();
      } catch(SQLException e) {
    	  e.printStackTrace();
      }
        disconnect();        
        return listhighestTree;
    }
    
    public void insert(client client) throws SQLException {
    	System.out.println("Inside insert client");
    	connect_func("root","Root*1234");
    	
		String sql = "insert into client(ClientID, FirstName, LastName, Address, CreditCardInfo, PhoneNumber, Email) values (?, ?, ?, ?, ?, ?, ?)";
		preparedStatement = (PreparedStatement) connect.prepareStatement(sql);
			preparedStatement.setString(1, client.getClientID());
			preparedStatement.setString(2, client.getFirstName());
			preparedStatement.setString(3, client.getLastName());
			preparedStatement.setString(4, client.getAddress());
			preparedStatement.setString(5, client.getCreditCardInfo());
			preparedStatement.setString(6, client.getPhoneNumber());
			preparedStatement.setString(7, client.getEmail());
			
		preparedStatement.executeUpdate();
        preparedStatement.close();
    }
   
    protected void disconnect() throws SQLException {
        if (connect != null && !connect.isClosed()) {
        	connect.close();
        }
    }

}
