import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlServlet extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    private userDAO userDAO = new userDAO();
	    private adminDAO adminDAO = new adminDAO();
	    private clientDAO clientDAO = new clientDAO();
	    private billDetailsDAO billDetailsDAO = new billDetailsDAO();
	    private orderdetailsDAO orderdetailsDAO = new orderdetailsDAO();
	    private quoteDAO quoteDAO = new quoteDAO();
	    private treeInformationDAO treeInformationDAO = new treeInformationDAO();
	    private treePictureDAO treePictureDAO = new treePictureDAO();
	    private treeRequestDAO treeRequestDAO = new treeRequestDAO();
	    private clientSummaryDAO clientSummaryDAO = new clientSummaryDAO();
	    
	    private String currentUser;
	    private HttpSession session=null;
	    
	    public ControlServlet()
	    {
	    	
	    }
	    
	    public void init()
	    {
	    	userDAO = new userDAO();
	    	adminDAO = new adminDAO();
	    	billDetailsDAO = new billDetailsDAO();
	    	orderdetailsDAO = new orderdetailsDAO();
	    	quoteDAO = new quoteDAO();
	    	treeInformationDAO = new treeInformationDAO();
	    	treePictureDAO = new treePictureDAO();
	    	treeRequestDAO = new treeRequestDAO();
	    	clientSummaryDAO = new clientSummaryDAO();
	    	currentUser= "";
	    }
	    
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        doGet(request, response);
	    }
	    
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String action = request.getServletPath();
	       // System.out.println(action);
	    
	    try {
      	switch(action) {  
        	case "/login":
        		login(request,response);
        		break;
        	case "/register":
        		register(request, response);
        		break;
        	case "/initialize":
        		userDAO.init();
        		request.setAttribute("listAdmin", adminDAO.listAllAdmins());
            	request.setAttribute("listBillDetails", billDetailsDAO.listAllBillDetails());
            	request.setAttribute("listClient", clientDAO.listAllClient());
            	request.setAttribute("listOrderDetails", orderdetailsDAO.listAllOrderDetails());
            	request.setAttribute("listQuote", quoteDAO.listAllQuote());
            	request.setAttribute("listTreeInformation", treeInformationDAO.listAllTreeInformation());
            	request.setAttribute("listTreePicture", treePictureDAO.listAllTreePicture());
            	request.setAttribute("listTreeRequest", treeRequestDAO.listAllTreeRequest());
            	request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
        		System.out.println("Database successfully initialized!");
        		break;
        	case "/submitQuote":
        		submitQuote(request,response);
        		break;
//        	case "/editQuote":
//        		System.out.println("Inside edit quote");
//        	    editQuote(request, response);
//        	    break;
        	case "/logout":
        		logout(request,response);
        		break;
        	case "/QuoteListforDavid":
        		QuoteListforDavid(request, response);
        		break;
        	case "/OverDueBills":
        		System.out.println("listOverDueBills-cs");
        		request.setAttribute("listOverDueBills", billDetailsDAO.listOverDueBills());
        		request.getRequestDispatcher("OverDueBills.jsp").forward(request, response);
        		//OverDueBills(request, response);
        		break;
        	case "/BadClients":
        		System.out.println("listBadClients-cs");
        		request.setAttribute("listBadClients", clientDAO.listBadClients());
        		request.getRequestDispatcher("BadClients.jsp").forward(request, response);
        		//OverDueBills(request, response);
        		break;
        	case "/GoodClients":
        		System.out.println("listGoodClients-cs");
        		request.setAttribute("listGoodClients", clientDAO.listGoodClients());
        		request.getRequestDispatcher("GoodClients.jsp").forward(request, response);
        		//OverDueBills(request, response);
        		break;
        	case "/ClientSummary":
        		System.out.println("listClientSummary-cs");
        		request.setAttribute("listClientSummary", clientSummaryDAO.listClientSummary());
        		request.getRequestDispatcher("Statistics.jsp").forward(request, response);
        		//OverDueBills(request, response);
        		break;
        	case "/editQuoteDavid":
        		editQuoteDavid(request, response);
        		break;
        	case "/editQuoteClient":
        		editQuoteClient(request, response);
        		break;
        	case "/ApprovedQuoteDavid":
        		ApprovedQuoteDavid(request, response);
        		break;
        	case "/ApprovedQuoteClient":
        		ApprovedQuoteClient(request, response);
        		break;
        	case "/DenyQuoteDavid":
        		DenyQuoteDavid(request, response);
        		break;
        	case "/DenyQuoteClient":
        		DenyQuoteClient(request, response);
        		break;
        	case "/listBigClient":
        		listBigClients(request, response);
        		break;
        	case "/listEasyClient":
        		listEasyClients(request, response);
        		break;
        	case "/oneTreeQuote":
        		oneTreeQuotes(request, response);
        		break;
        	case "/listProspectiveClient":
        		listProspectiveClients(request, response);
        		break;
        	case "/listHighestTree":
        		listHighestTrees(request, response);
        		break;
        	 case "/backToAdminDashboard":
                 backToAdminDashboard(request, response);
                 break;
	    	}
	    }
	    catch(Exception ex) {
        	System.out.println(ex.getMessage());
	    	}
	    }
        	
	    private void QuoteListforDavid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listQuoteDavid", quoteDAO.listAllQuoteforDavid());
	            	System.out.println("returned");
                	request.getRequestDispatcher("david_smith_dashboard.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }	
	private void OverDueBills(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
		{
          
	    	 try {
		            // Call the method to update the quote in your DAO
	    		 System.out.println("gome here");
	            	request.setAttribute("listOverDueBills", billDetailsDAO.listOverDueBills());
	            	System.out.println("returned");
                	request.getRequestDispatcher("OverDueBills.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }
	    }

	 private void oneTreeQuotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listOneTreeQuote", clientDAO.oneTreeQuote());
	            	System.out.println("returned");
                	request.getRequestDispatcher("listOneTreeQuotes.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }
	    
	    private void listProspectiveClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listProspectiveClient", clientDAO.listProspectiveClient());
	            	System.out.println("returned");
                	request.getRequestDispatcher("listProspectiveClients.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }
	    
	    private void listHighestTrees(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listHighestTree", clientDAO.listHighestTree());
	            	System.out.println("returned");
                	request.getRequestDispatcher("listHighestTree.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }
	    
	    private void listBigClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listBigClient", clientDAO.listBigClient());
	            	System.out.println("returned");
                	request.getRequestDispatcher("listBigClients.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }
	    
	    private void listEasyClients(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException  
	    {
          
	    	 try {
		            // Call the method to update the quote in your DAO
	            	request.setAttribute("listEasyClient", clientDAO.listEasyClient());
	            	System.out.println("returned");
                	request.getRequestDispatcher("listeasyClients.jsp").forward(request, response);
		        } catch (SQLException e) {
		            e.printStackTrace(); // Handle the exception appropriately
		        }

	    }

		protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	 String username = request.getParameter("username");
	    	 String password = request.getParameter("password");
	    	 
	    	 if (username.equals("root") && password.equals("Root*1234")) {
				 System.out.println("Login Successful! Redirecting to root");
				 session = request.getSession();
				 session.setAttribute("username", username);
				// rootPage(request, response, "");
	    	 }
	    	 else if(userDAO.isValid(username, password) != null) 
	    	 {
			 	 String role = userDAO.isValid(username, password);
			 	 currentUser = username;
				 System.out.println("Login Successful! Redirecting");
				 if ("David Smith".equals(role)) {
					 request.getRequestDispatcher("david_smith_dashboard.jsp").forward(request, response);
	                } else if ("Client".equals(role)) {
	                	String loginID = username; // Replace this with your actual login ID
	                	HttpSession session = request.getSession();
	                	session.setAttribute("loginID", loginID);
	                	session.setAttribute("listQuote", quoteDAO.listParticularQuoteRequest(loginID));
	                	session.setAttribute("listOrderDetails", orderdetailsDAO.listParticularOrder(loginID));
	                	request.getRequestDispatcher("client_dashboard.jsp").forward(request, response);
	                } else if  ("Admin Root".equals(role)) {
	                	request.setAttribute("listAdmin", adminDAO.listAllAdmins());
	                	request.setAttribute("listBillDetails", billDetailsDAO.listAllBillDetails());
	                	request.setAttribute("listClient", clientDAO.listAllClient());
	                	request.setAttribute("listOrderDetails", orderdetailsDAO.listAllOrderDetails());
	                	request.setAttribute("listQuote", quoteDAO.listAllQuote());
	                	request.setAttribute("listTreeInformation", treeInformationDAO.listAllTreeInformation());
	                	request.setAttribute("listTreePicture", treePictureDAO.listAllTreePicture());
	                	request.setAttribute("listTreeRequest", treeRequestDAO.listAllTreeRequest());
	                	request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
	                }
			 			 			 			 
	    	 }
	    	 else {
	    		 request.setAttribute("loginStr","Login Failed: Please check your credentials.");
	    		 request.getRequestDispatcher("login.jsp").forward(request, response);
	    	 }
	    }
	           
	    private void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
	    	String username = request.getParameter("username");
	   	 	String password = request.getParameter("password");
	   	 	String role = request.getParameter("role");	   	 	
	   	 	String confirm = request.getParameter("confirmation");
	   	 	
	   	 	if (password.equals(confirm)) {
	   	 		if (!userDAO.checkUsername(username)) {
		   	 		System.out.println("Registration Successful! Added to database");
		            user users = new user(username, password, role);
		            System.out.println(role);
		            if(role.equalsIgnoreCase("Admin Root")) {
		            	System.out.println("Inside admin root");
		            	long millis=System.currentTimeMillis();  
		            	admin admin = new admin(username,password,"Account created",new java.sql.Date(millis));
		            	adminDAO.insert(admin);
		            }
		            if(role.equalsIgnoreCase("Client")) {
		            	System.out.println("Inside client root");
		            	  
		            	client client = new client(username,username,"","","","","");
		            	clientDAO.insert(client);
		            }
		   	 		userDAO.insert(users);
		   	 		response.sendRedirect("login.jsp");
	   	 		}
		   	 	else {
		   	 		System.out.println("Username taken, please enter new username");
		    		 request.setAttribute("errorOne","Registration failed: Username taken, please enter a new username.");
		    		 request.getRequestDispatcher("register.jsp").forward(request, response);
		   	 	}
	   	 	}
	   	 	else {
	   	 		System.out.println("Password and Password Confirmation do not match");
	   		 request.setAttribute("errorTwo","Registration failed: Password and Password Confirmation do not match.");
	   		 request.getRequestDispatcher("register.jsp").forward(request, response);
	   	 	}
	    }
	    protected void backToAdminDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException{
	        // You may need to perform additional logic if needed before redirection
	        String username = "adminroot"; // Set the desired username
	        String password = "adminroot";

	        // Set the session attributes
	        if (username.equals("adminroot") && password.equals("adminroot")) {
	        HttpSession session = request.getSession();
	        session.setAttribute("username", username);

	        // Redirect to the admin_dashboard.jsp
	        request.setAttribute("listAdmin", adminDAO.listAllAdmins());
        	request.setAttribute("listBillDetails", billDetailsDAO.listAllBillDetails());
        	request.setAttribute("listClient", clientDAO.listAllClient());
        	request.setAttribute("listOrderDetails", orderdetailsDAO.listAllOrderDetails());
        	request.setAttribute("listQuote", quoteDAO.listAllQuote());
        	request.setAttribute("listTreeInformation", treeInformationDAO.listAllTreeInformation());
        	request.setAttribute("listTreePicture", treePictureDAO.listAllTreePicture());
        	request.setAttribute("listTreeRequest", treeRequestDAO.listAllTreeRequest());
        	request.getRequestDispatcher("admin_dashboard.jsp").forward(request, response);
	        }
	    }
	    
	    private int generateRandom(int min, int max) {
	        Random random = new Random();
	        return random.nextInt(max - min) + min;
	    }
	    private String generateRandomReqID() {
		    // Generate a random order ID as per your requirements
		    // For example, you can concatenate a prefix with a random number
		    String prefix = "REQ";
		    int randomSuffix = generateRandom(1000, 9999);
		    return prefix + randomSuffix;
		}
	    private String generateRandomQuoID() {
		    // Generate a random order ID as per your requirements
		    // For example, you can concatenate a prefix with a random number
		    String prefix = "Quo";
		    int randomSuffix = generateRandom(1000, 9999);
		    return prefix + randomSuffix;
		}
	    private String generateRandomInfoID() {
		    // Generate a random order ID as per your requirements
		    // For example, you can concatenate a prefix with a random number
		    String prefix = "Info";
		    int randomSuffix = generateRandom(1000, 9999);
		    return prefix + randomSuffix;
		}
	    private String generateRandomPicID() {
		    // Generate a random order ID as per your requirements
		    // For example, you can concatenate a prefix with a random number
		    String prefix = "Pic";
		    int randomSuffix = generateRandom(1000, 9999);
		    return prefix + randomSuffix;
		}
	    
	    private void submitQuote(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, NumberFormatException {
	    	String clientID = request.getParameter("clientID");
	    	String requestDateStr = request.getParameter("requestDate");
	    	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); // The format matches the input type="date" in HTML
	    	Date requestDate1 = null;
	    	try {
				requestDate1 = dateFormat.parse(requestDateStr);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	String note = request.getParameter("note");
	    	String size = request.getParameter("size");
	    	String height = request.getParameter("height");
	    	String location = request.getParameter("location");
	    	String nearHouse = request.getParameter("nearHouse");
	    	String pictureURL = request.getParameter("pictureURL");
	    	String timeWindow = request.getParameter("timeWindow");
	    	String price = request.getParameter("price");

	   	 	//Create new tree request
	    	String requestId = generateRandomReqID();
	    	java.sql.Date requestDate = new java.sql.Date(requestDate1.getTime());
	    	treeRequest treeRequest = new treeRequest(requestId, clientID, requestDate , "Quote Requested" , note);
	    	treeRequestDAO.insert(treeRequest);
	    	
	    	//Create new quote request
	    	String quoteId = generateRandomQuoID();
	    	quote quote = new quote(quoteId,requestId,requestDate,Double.parseDouble(price),timeWindow,"Quote submitted -client",note);
	    	quoteDAO.insert(quote); 
	    	
	    	//Create new tree information
	    	System.out.println("here");
	    	String treeInfoID = generateRandomInfoID();
	    	System.out.println(requestId);
	    	System.out.println(location);
	    	System.out.println(nearHouse);
	    	System.out.println(Double.parseDouble(size));
	    	System.out.println(Double.parseDouble(height));
	    	String requestID = requestId;
	    	treeInformation treeInformation = new treeInformation(treeInfoID,requestID,Double.parseDouble(size),Double.parseDouble(height),location,nearHouse);
	    	treeInformationDAO.insert(treeInformation);
	    	
	    	//Create new tree picture 
	    	String pictureID = generateRandomPicID();
	    	treePicture treePicture = new treePicture(pictureID,pictureURL,treeInfoID);
	    	treePictureDAO.insert(treePicture);
	    	response.sendRedirect("client_dashboard.jsp");
	    }   
	    
	    
	    private void editQuoteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String quoteID = request.getParameter("quoteID");
	        System.out.println(quoteID);
	        double updatedPrice = Double.parseDouble(request.getParameter("price"));
	        String updatedStatus = request.getParameter("status");
	        String updatedNote = request.getParameter("note");

	        try {
	            // Call the method to update the quote in your DAO
	            quoteDAO.updateQuote(quoteID, updatedPrice, updatedStatus, updatedNote);

	            // Set an attribute in the session to store the success message
	            HttpSession session = request.getSession();
	            session.setAttribute("quoteUpdated", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("Negotiate-client.jsp");
	        } catch (SQLException e) {
	            e.printStackTrace(); // Handle the exception appropriately
	        }
	    }

	    private void editQuoteDavid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String quoteID = request.getParameter("quoteID");
	    	double updatedPrice = Double.parseDouble(request.getParameter("price"));
	    	String updatedStatus = request.getParameter("status");
	    	String updatedNote = request.getParameter("note");

	    	try {
	    	    // Call the method to update the quote in your DAO
	    	    quoteDAO.updateQuoteDavid(quoteID, updatedPrice, updatedStatus, updatedNote);

	    	    // Set an attribute in the session to store the success message
	    	    HttpSession session = request.getSession();
	    	    session.setAttribute("quoteUpdated", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("Negotiate.jsp");
	    	} catch (SQLException e) {
	    	    e.printStackTrace(); // Handle the exception appropriately
	    	}
	    }
	    
	    private void ApprovedQuoteDavid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String quoteID = request.getParameter("quoteID");

	    	try {
	    	    // Call the method to update the quote in your DAO
	    	    quoteDAO.updateQuoteStatusApproved(quoteID);

	    	    // Set an attribute in the session to store the success message
	    	    HttpSession session = request.getSession();
	    	    session.setAttribute("quoteApproved", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("ApprovedQuote.jsp");
	    	} catch (SQLException e) {
	    	    e.printStackTrace(); // Handle the exception appropriately
	    	}
	    }
	    
	    private void ApprovedQuoteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String quoteID = request.getParameter("quoteID");

	    	try {
	    	    // Call the method to update the quote in your DAO
	    	    quoteDAO.updateQuoteStatusApproved(quoteID);

	    	    // Set an attribute in the session to store the success message
	    	    HttpSession session = request.getSession();
	    	    session.setAttribute("quoteApproved", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("ApprovedQuote-client.jsp");
	    	} catch (SQLException e) {
	    	    e.printStackTrace(); // Handle the exception appropriately
	    	}
	    }
	    
	    private void DenyQuoteDavid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String quoteID = request.getParameter("quoteID");

	    	try {
	    	    // Call the method to update the quote in your DAO
	    	    quoteDAO.updateQuoteStatusDenied(quoteID);

	    	    // Set an attribute in the session to store the success message
	    	    HttpSession session = request.getSession();
	    	    session.setAttribute("quoteDenied", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("DeniedQuote.jsp");
	    	} catch (SQLException e) {
	    	    e.printStackTrace(); // Handle the exception appropriately
	    	}
	    }
	    
	    private void DenyQuoteClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	String quoteID = request.getParameter("quoteID");

	    	try {
	    	    // Call the method to update the quote in your DAO
	    	    quoteDAO.updateQuoteStatusDenied(quoteID);

	    	    // Set an attribute in the session to store the success message
	    	    HttpSession session = request.getSession();
	    	    session.setAttribute("quoteDenied", true);

	    	    // Redirect back to the Negotiate.jsp or wherever needed
	    	    response.sendRedirect("DeniedQuote-client.jsp");
	    	} catch (SQLException e) {
	    	    e.printStackTrace(); // Handle the exception appropriately
	    	}
	    }
	    
	    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
	    	currentUser = "";
        		response.sendRedirect("login.jsp");
        	}
	
	    

	     
        
	    
	    
	    
	    
	    
}
	        
	        
	    
	        
	        
	        
	    


