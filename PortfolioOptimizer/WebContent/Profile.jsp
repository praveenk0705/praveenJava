<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Portfolio Optimizer</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link href="/PortfolioOptimizer/css/profileCss.css" rel="stylesheet" type="text/css">
<%-- <link rel="stylesheet" href="${pageContext.request.contextPath}/XXX.css" /> --%>
</head>
<div class = "welcome">
<%
	if (!session.isNew()) {

	} else {
		response.sendRedirect("index.jsp");
	}
%>
Welcome
<%=session.getAttribute("userName")%>
<a href="logout.jsp">Logout</a>
</div>

<body>

	<!-- <div >
		<input type="radio" name="buysell" value="buy"> BUY<br> <input
			type="radio" name="buysell" value="sell"> SELL<br>

	</div>
 -->
 <div  id = "cash">
  Cash
</div>
 <div><a href="#" id = "depositCash">Deposit Cash</a>
 <input id = "deposit"  autocomplete="off"/>
 </div>
 
 

<table border = "1">
<tr>

	<div class='inline'>
		<td>Choose Stock Index:</td>
		<td><select id="indexval"
			onchange="indexSelect(this,document.getElementById('stockname'))">
			<option>Choose an Index</option>
			
		</select></td>
	</div>
	




	<div class='inline'>
		<td>Choose Company:</td>
		<td><select id="stockname" onchange="getStockName()">
		</select>
		</td>
	</div>
	



	<div class='inline'>
		<td>Current Stock Price:</td>
		<td><input type="text" id="stockprice" readonly autocomplete="off"></td>
	</div>




	<div class='inline'>
	<td>	Enter the number of stocks : </td>
	<td><input id=noofstocks autocomplete="off"></td>
	</div>



<td>
	<div >
		<input type="button" id="addtocart" name="addToCart" value="Buy"
			onclick="addToCart()">
	</div>
</td>
	
<tr>
<td>
	<!-- <div>Check if This is your first buy <input type = "checkbox" name = "firstBuy" id = "firstBuy" ></div> -->
	
	<div><a href="viewYourTransaction.jsp">View your Buy transactions</a>
	</div>
	</td>
</tr>
	<!-- <input type="button" id = "testValidate" value ="validate"  onclick = "testValidate()"/> -->
	</table>

<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 --><% Connection connection = null;
ResultSet result = null;
PreparedStatement stSelect = null; 
PreparedStatement zeroNo = null;


////////////////

		
		
		int count = 0;
		ResultSet result2 = null;
		PreparedStatement stSelect2 = null;
	
		
		ResultSet result6 = null;
		PreparedStatement stSelect6 = null;
		
		ResultSet result7 = null;
		PreparedStatement stSelect7 = null;
		
		
		ResultSet result8 = null;
		PreparedStatement stSelect8 = null;
		
		ResultSet result9 = null;
		PreparedStatement stSelect9 = null;
		
		ResultSet result11 = null;
		PreparedStatement stSelect11 = null;
		
		ResultSet result12 = null;
		PreparedStatement stSelect12 = null;
/* ////////////////// */
try 
	{
	ServletContext context = getServletContext();
	connection = (Connection)context.getAttribute("globalConnection");
	stSelect = connection.prepareStatement("select * from transactions ");
	result = stSelect.executeQuery();
	/* //////////////////////////////////////////////////////
	 */stSelect2 = connection.prepareStatement("select count(distinct stockname) from transactions ");
	result2 = stSelect2.executeQuery();
	
	stSelect6 = connection.prepareStatement("select balance from person where uname = 'aps'");
	result6 = stSelect6.executeQuery();

	stSelect7 = connection.prepareStatement("select sum(totalprice) from transactions");
	result7 = stSelect7.executeQuery();
	
	stSelect8 = connection.prepareStatement("select sum(totalprice) from transactions where stockindex='Dow30' group by stockindex");
	result8 = stSelect8.executeQuery();
	
	stSelect9 = connection.prepareStatement("select sum(totalprice) from transactions where stockindex not in ('Dow30')");
	result9 = stSelect9.executeQuery();
	
	stSelect11 = connection.prepareStatement("select count(stockname) from  transactions");
	result11 = stSelect11.executeQuery();
	
	stSelect12 = connection.prepareStatement("select count(totalprice) from transactions where stockindex not in ('Dow30')");
	result12 = stSelect12.executeQuery();
	/* //////////////////////////////////////////////////////////
		 */
	}

	catch(NullPointerException e)
	{
		System.out.println("Database Connection Not Established");
	}

%>
<table border="1">
      <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Total Price</th>
        <th>No of Stocks</th>
        <th>Sell Stocks </th>
        
<% String sname;
    while(result.next()){
        %>
        <tr>
            <td><%=result.getString(1) %></td>
            <td><%=result.getString(2) %></td>
            <td><%=result.getString(3) %></td>
            <td><%=result.getString(4) %></td>
         
           
            	
            	 

<td>
			<input type="text" class="form-control" placeholder="No." id = "<%=result.getString(2) %>" autocomplete="off"  style="width: 40px">
			
			<input type="button" class="<%=result.getString(2) %>" value="Sell Stocks"  onclick="sellStocks('<%=result.getString(2) %>', '<%=result.getString(4) %>' , '<%=result.getString(1) %>')">
			</td>

<%-- 
			<td><input type="button" id="<%=result.getString(2) %>" value="Sell" onclick="sellStocks('<%=result.getString(2) %>')"/></td>
             --%>

        </tr>
        <%
    }
    result.close();
    %>
    </table>






<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 --><h3>	Validation:	</h3> 
	 	
	<% 
	while(result11.next())
	{
		if(Integer.parseInt(result11.getString(1)) !=0)
		{
	
	while(result2.next())
	{
		//checking minimum and maximum number of stocks
		
		System.out.println(result2.getString(1));
		
		if(Integer.parseInt(result2.getString(1)) < 7) 
		{
		%>
				<h4> Minimum 7 stocks should be there in Portfolio. Buy more stocks </h4>
		<%
		} 
		 
		else if(Integer.parseInt(result2.getString(1)) > 10)
		{
		%>
				<h4> Maximum 10 Stocks should be there in portfolio. Sell more stocks </h4>	
		<% 
		}
		else
		{
			System.out.println("1st validation successful");
			count++;
		}
	
	//	result2.close();
	}
	result2.close();
	%>
	
	<% 
		while(result6.next() && result7.next())
		{
			double a = Double.parseDouble(result6.getString(1));
			double b = Double.parseDouble(result7.getString(1));
			
			if(a > (a+b)/10)
			{	
			%>
				<h4> Cash more than 10%. Buy more Stocks</h4>
		<% 	
			}
			else
			{
				System.out.println("2nd validation successful");
				count++;
			}
			
		}
	result6.close();
	//result7.close();
		
		while(result8.next())
		{	
	
			double a = Double.parseDouble(result7.getString(1));
			double b = Double.parseDouble(result8.getString(1));
			
			if(a*0.7 > b)
			{ 
			%>
				<h4>DOW-30 Stocks less than 70% </h4>
			<%
			}
			else if(a*0.7 < b)
			{
			%>
				<h4>DOW-30 stocks more than 70%</h4>
			<%
			}
			else
			{
				System.out.println("3rd Validation sucessful");
				count++;
			}
			
		}
	
		result8.close();
		while(result12.next())
		{
			if(Integer.parseInt(result12.getString(1))!=0)
					{
		while(result9.next())
		{
			double a= Double.parseDouble(result7.getString(1));
			double b= Double.parseDouble(result9.getString(1));
			
			if(	a*0.3 >	b)
			{ %>
				<h4>Foreign stocks less than 30%.</h4>
				<%
			}
			else if (a*0.3 < b)
			{%>
				<h4>Foreign stocks more than 30%.</h4>
				<% 
			}
			
		}
		result7.close();
		result9.close();
		}
		}
		result12.close();
		}
		
	}
		%>
<!-- ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 -->

	
	

	
	
	
</body>

<script type="text/javascript">
	////////////////////////////////////////////////////////////////////////////////////
	//Function to populate the Indices dropdown
	/////////////////////////////////////////////////////////////////////////////////////
	var select = document.getElementById("indexval");

	var options = [ "Nifty50", "Dow30", "Straits_Times_Index" ];
	for (var i = 0; i < options.length; i++) {
		var opt = options[i];
		var el = document.createElement("option");
		el.textContent = opt;
		el.value = opt;
		select.appendChild(el);
	}
	///////////////////////////////////////////////////////////////////////////////////

	//Function to populate the Stock list bases on the indice value
	/////////////////////////////////////////////////////////////////////////////////////
	function indexSelect(ddl1, stockname) {
		var stockIndex = document.getElementById('indexval').options[document
				.getElementById('indexval').selectedIndex].text;
		//window.alert(stockIndex);

		var Nifty50 =[" ", 'TATASTEEL','TATAMTRDVR','ZEEL','TATAPOWER','TATAMOTORS','BAJAJ-AUTO','GRASIM','ADANIPORTS','LUPIN','HCLTECH','ITC','AXISBANK','SBIN','AMBUJACEM','INDUSINDBK','LT','COALINDIA','IDEA','BOSCHLTD','TECHM','HDFCBANK','HEROMOTOCO','KOTAKBANK','ICICIBANK','M&M','AUROPHARMA','WIPRO','POWERGRID','HINDALCO','RELIANCE','ACC','SUNPHARMA','ONGC','GAIL','YESBANK','MARUTI','BANKBARODA','HINDUNILVR','ULTRACEMCO','NTPC','DRREDDY','TCS','BHEL','BHARTIARTL','BPCL','CIPLA','INFY','EICHERMOT','HDFC','ASIANPAINT','INFRATEL']; 
			
			var Dow30 = [" " ,'AXP','AAPL','BA','CAT','CSCO','CVX','DD','XOM','GE','GS','HD','IBM','INTC','JNJ','KO','JPM','MCD','MMM','MRK','MSFT','NKE','PFE','PG','TRV','UNH','UTX','VZ', 'V', 'WMT', 'DIS']; 
				
				var Straits_Times_Index = [" ", 'NS8U', 'Y92', 'A17U', 'O39', 'BN4', 'C6L', 'D05', 'S58', 'C09', 'S59', 'U11', 'MC0', 'F34', 'C31', 'S51', 'U96', 'C38U', 'S68', 'S63', 'C52', 'C07', 'BS6', 'G13', 'T39', 'H78', 'E5H', 'Z74', 'U14', 'CC3', 'C61U'];
		switch (stockIndex) {
		case 'Nifty50':
			stockname.options.length = 0;
			for (i = 0; i < Nifty50.length; i++) {
				createOption(stockname, Nifty50[i], Nifty50[i]);
			}
			break;
		case 'Dow30':
			stockname.options.length = 0;
			for (i = 0; i < Dow30.length; i++) {
				createOption(stockname, Dow30[i], Dow30[i]);
			}
			break;
		case 'Straits_Times_Index':
			stockname.options.length = 0;
			for (i = 0; i < Straits_Times_Index.length; i++) {
				createOption(stockname, Straits_Times_Index[i],
						Straits_Times_Index[i]);
			}
			break;
		default:
			stockname.options.length = 0;
			break;
		}

	}
	///////////////////////////////////////////////////////////////////////////////////////////////

	function createOption(dd2, text, value) {
		var opt = document.createElement('option');
		opt.value = value;
		opt.text = text;
		dd2.options.add(opt);
	}
	///////////////////////////////////////////////////////////////////////////////////////////////
	
</script>


<!-- <input type = text value = "date" id = "date">sfg</input> -->
<script type="text/javascript">
function getStockName() {
	var firstBuy;
	var stockName = document.getElementById('stockname').options[document
			.getElementById('stockname').selectedIndex].text;
	var indexName = document.getElementById('indexval').options[document
	                                                 			.getElementById('indexval').selectedIndex].text;
	 /* if(document.getElementById('firstBuy').checked) {
		firstBuy ="true";
	}
	else{
		firstBuy = "false";
	}
	window.alert(firstbuy);  */


var n = stockName;

$.post("GetPrice",
		  { name: n, indexName: indexName },
		  function(json){
		    //alert("Data Loaded: " + data);
		   // $("#stockprice").val(data);
		    $("#stockprice").val(json.price);
		    document.getElementById("demo").innerHTML = json.message;
		   // $("#json").val(json.message);
		    //$("#date").val(data);
		    
		  }
		);
		
		} //end of function getStockName
</script>



<h3>
<p class="demo"  id ="demo"></p> <!-- to display user messages  -->
</h3>

<script type="text/javascript">
function addToCart(){
	//alert("sf");
	 var x, text, noOfSt, y;
	 x = document.getElementById("noofstocks").value;
	 y = document.getElementById("stockprice").value;
	 var stockIndex = document.getElementById('indexval').options[document
	                                              				.getElementById('indexval').selectedIndex].text;
	                                              		
	 noOfSt = parseInt(x);
	 stockprice = parseInt(y);
	
	 if(isNaN(noOfSt) || noOfSt < 1 ||noOfSt > 10000000 || y == 0){
		text = "Invalid input or the API may be down";
		window.location.reload();
	}
	
	else{
		//alert("else part");
		text = "Processing your transaction" ;
	
		$.post("AddToCart",
				{stockIndex: $("#indexval").val() , stockName:$("#stockname").val() ,
			stockPrice:$("#stockprice").val() , noOfStocks:$("#noofstocks").val() },
				function(data){
					//alert(data);
					getBalance();
					window.location.reload();
				}
			);
		//alert("Value: " + $("#stockprice").val() );

		
	
	
	}//end of else
	
		
		document.getElementById("demo").innerHTML = text;
	
} //end of function addtocart

</script>


<script type="text/javascript">
$(document).ready(function() {

	getBalance();
	
	//$('#cash').text("ertyeryt");
    
    $('#depositCash').click(function () { 
    	var cash = parseInt($("#deposit").val());
    	//alert(cash);
    	if(isNaN(cash) || cash < 1){
    		text = "Input not valid";
    	}
    	//alert($("#deposit").val());
    	else{
    	$.post("DepositCash",
    			{Amount: $("#deposit").val() },
    			function(data){
    				text = " money deposited" ; //alert("Cash balance " + data);
    				getBalance();
    			}
    		);}
    	document.getElementById("demo").innerHTML = text;
    	});
});
</script>

<script>
function getBalance(){
	$.post("GetBalance",
			  
			  function(data){
			    //alert("Data Loaded: " + data);
			    $('#cash').text(data);
			  }
			);
}
</script>

<script type="text/javascript">
function sellStocks(s , noOfStoccksinDB, indexName) {
	var noOfSt = parseInt(noOfStoccksinDB);
	//alert(noOfSt);
	 var x , text;
	 x = $("#"+ s).val();
	 //alert(x);
	
	 if(isNaN(x) || x > noOfSt || x < 1){
		text = "Input not valid";
	}
	else{
		text = "Processing your transaction" ;
		//alert($("#"+ s).val());
		var answer = confirm("Are you sure you want to sell these stocks");
		if (answer){
			 $.post("SellStocks",
						{stockName : s , noOfStock : $("#"+ s).val() , indexName : indexName},
				 function(data) {
					 //alert(data);
					 window.location.reload();
				 });
		}
		else{
		        //some code
		}
	}//end of else
	document.getElementById("demo").innerHTML = text;
};
</script>

<div id = "jsonresp" ></div>

<script type="text/javascript">
function testValidate(){
	//alert("button clicked");
	$.post("testValidate" ,
			function(json){
		alert(json.b + json.a + json.c + json.d );
		//document.getElementById("jsonresp").innerHTML = data;
	}
			
	);
}

</script>


</html>