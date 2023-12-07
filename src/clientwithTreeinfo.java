public class clientwithTreeinfo {
	private String clientID;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
	private String quoteID;
    private String requestID;
    private String treeinfoID;
    private String orderID;
    private double height;
    private int numberOfTrees;
    
    
    
	public clientwithTreeinfo(String clientID, String firstName, String lastName, String address, String phoneNumber, String email, 
			String quoteID, String requestID, String treeinfoID, String orderID, double height, int numberOfTrees) 
	{
		this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
		this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
		this.quoteID = quoteID;
        this.requestID = requestID;
        this.treeinfoID = treeinfoID;
		this.orderID = orderID;
        this.height = height;
        this.numberOfTrees = numberOfTrees;
	}
	public clientwithTreeinfo(String clientID, String firstName, String lastName, String address, String phoneNumber, String email) 
	{
		this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
		this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
	}
	public clientwithTreeinfo(String clientID, String firstName, String lastName, String address, String phoneNumber, String email, String quoteID) 
	{
		this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
		this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
		this.quoteID = quoteID;
	}
	public clientwithTreeinfo(String clientID, String firstName, String lastName, String address, String phoneNumber, String email, String quoteID, String requestID, String treeinfoID, String orderID, double height) 
	{
		this.clientID = clientID;
        this.firstName = firstName;
        this.lastName = lastName;
		this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
		this.quoteID = quoteID;
        this.requestID = requestID;
        this.treeinfoID = treeinfoID;
		this.orderID = orderID;
        this.height = height;
	}
	public String getClientID() {
		return clientID;
	}
	public void setClientID(String clientID) {
		this.clientID = clientID;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getQuoteID() {
		return quoteID;
	}
	public void setQuoteID(String quoteID) {
		this.quoteID = quoteID;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getTreeinfoID() {
		return treeinfoID;
	}
	public void setTreeinfoID(String treeinfoID) {
		this.treeinfoID = treeinfoID;
	}
	public String getOrderID() {
		return orderID;
	}
	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}
	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public int getNumberOfTrees() {
	        return numberOfTrees;
	}
	public void setNumberOfTrees(int numberOfTrees) {
	        this.numberOfTrees = numberOfTrees;
	}
	
	@Override
	public String toString() {
		return "clientwithTreeinfo [clientID=" + clientID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", quoteID=" + quoteID
				+ ", requestID=" + requestID + ", treeinfoID=" + treeinfoID + ", orderID=" + orderID + ", height="
				+ height + ", numberOfTrees=" + numberOfTrees + "]";
	}
	
}
