<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@page import="java.sql.*"%>
     <%@page import="java.util.*"%>
<%@page import="fi.aps.CurrencyConversion"%>
<%@page import="fi.aps.GetCurrentStockPriceFromYahoo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/prettyProfile.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<div class="welcome">
	<%
	if (session.getAttribute("userName") == null) {
		response.sendRedirect("index.jsp");
	} 
%>
	Welcome
	<%=session.getAttribute("userName")%>
	<a href="logout.jsp">Logout</a>
</div>
<body>
<div id="cash">Cash</div>
	<div>
		<a href="#" id="depositCash">Deposit Cash</a> <input id="deposit"
			autocomplete="off" />
	</div>



	<table>
		<tr>

			<div class='inline'>
				<tr><td>Choose Stock Index:</td>
				<td><select id="indexval"
					onchange="indexSelect(this,document.getElementById('stockname'))">
						<option>Choose an Index</option>

				</select></td>
			</div>





			<div class='inline'>
				<td>Choose Company:</td>
				<td><select id="stockname" onchange="getStockName()">
				</select></td></tr>
			</div>




			<div class='inline'>
				<td>Current Stock Price (USD):</td>
				<td><input type="text" id="stockprice" readonly
					autocomplete="off"></td>
			</div>
			<div class='inline'>
				<td>Current Stock Price(LOCAL Currency):</td>
				<td><input type="text" id="stockpriceLocalCurrency" readonly
					autocomplete="off"></td>
			</div>




			<div class='inline'>
				<td>Enter the number of stocks :</td>
				<td><input id=noofstocks autocomplete="off"></td>
			</div>



			<td>
				<div>
					<input type="button" id="addtocart" name="addToCart" value="Buy"
						onclick="addToCart()">
				</div>
			</td>
		<tr>
			<td>
				<!-- <div>Check if This is your first buy <input type = "checkbox" name = "firstBuy" id = "firstBuy" ></div> -->

				<div>
					<a href="viewYourTransaction.jsp">View your Buy transactions</a>
				</div>
			</td>
			<td>

				<div>
					<!-- <a href="#" id = "downloadcsv">Download CSV</a> -->
					<a href="<%=request.getContextPath()%>/DownloadCsvSellTransactions">Download
						Sell History</a>
				</div>
			</td>
			<td>

				<div>
					<!-- <a href="#" id = "downloadcsv">Download CSV</a> -->
					<a href="<%=request.getContextPath()%>/DownloadCsvBuyTransactions">Download
						Buy History</a>
				</div>
			</td>

			<td>
				<!-- <div>Check if This is your first buy <input type = "checkbox" name = "firstBuy" id = "firstBuy" ></div> -->

				<div>
					<a href="viewSellHistory.jsp">View your Selling history</a>
				</div>
			</td>

			<td>

				<div>
					<a href="viewBuyHistory.jsp" id="viewbuyhistory">View your
						purchase history</a>
				</div>
			</td>

		</tr>





		<input type="button" id = "testValidate" value ="validate"  onclick = "testValidate()"/>
	</table>

	<% Connection connection = null;
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
	stSelect = connection.prepareStatement("select * from addtocart ");
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
	

<section>
  
 
  <div class="tbl-header">
    <table cellpadding="0" cellspacing="0" border="0">
      <thead>
        <tr>
       <th>Stock Index</th>
        <th>Stock Index</th>
		<th>Stock Name</th>
		<th>Total Price When Purchased(USD)</th>
		<th>Stock Price When Purchased(USD)</th>
		<th>No of Stocks Purchased</th>
		<th>Purchase Transaction Date</th>
		<th>TotalPrice when purchased(Local)</th>
		<th>Stock Price when purchased(Local)</th>
		<th>Current Total of Stocks(USD)</th>
		<th>Current Total of Stocks(Local)</th>
        </tr>
      </thead>
    </table>
  </div>
  <div class="tbl-content">
    <table cellpadding="0" cellspacing="0" border="0">
      <tbody>
      <% String sname;
Double i = 0.0;
    while(result.next()){
        %>
		<tr>
			<td><%=result.getString(1) %></td>
			<td><%=result.getString(2) %></td>
			<td><%=result.getString(3) %></td>
			<td><%=result.getString(4) %></td>
			<td><%=result.getString(5) %></td>
			<td><%=result.getString(6) %></td>
			<td><%=result.getString(7) %></td>
			<td><%=result.getString(8) %></td>
			<td>
				<% String Stockname =  result.getString(1);
            	String tickerName = result.getString(2);
            	Double LocalPrice = 0.0;
            if (Stockname.equals("Nifty50")) {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
    			LocalPrice=	currentPrice.InrStockPrice(tickerName + ".BO");
    			CurrencyConversion currency = new CurrencyConversion();
				Double oneInrInUsd = currency.InrConvertor(tickerName);

				Double y = oneInrInUsd * LocalPrice;
				int no = Integer.parseInt(result.getString(5));
				Double total = no * y;
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
				%>
			
			<td>
				<%out.println("Rs " + LocalPrice*no); %>
			</td>

			<% 
            	
            }
            else if (Stockname.equals("Dow30")) {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
    			LocalPrice=	currentPrice.InrStockPrice(tickerName);
    			int no = Integer.parseInt(result.getString(5));
				Double total = no * LocalPrice;
				i = i + total;
				out.println(total);
				%>
			<td>
				<%out.println(LocalPrice*no + " $"); %>
			</td>

			<% 
            	
            }
            else {
            	GetCurrentStockPriceFromYahoo currentPrice = new GetCurrentStockPriceFromYahoo();
				LocalPrice=	currentPrice.InrStockPrice(tickerName + ".SI");
				
				//get uds equivalent of SGD
				CurrencyConversion currency = new CurrencyConversion();
				Double oneSGDInUsd = currency.SgdConvertor(tickerName);//will have usd equivalent of inr here
				
				//converting to usd
				Double x = oneSGDInUsd * LocalPrice;
				int no = Integer.parseInt(result.getString(5));
				Double total = no * x;
				i = i + total;
				/* //PrintWriter out = response.getWriter(); */
				out.println(total);
				%>
			<td>
				<%out.println("SGD " + LocalPrice*no); %>
			</td>

			<% 
            	
            }
            
           
            %>

			</td>

			<% 
            Random rand = new Random(); 
            int value = rand.nextInt(500);
            %>





			<td><input type="text" class="form-control" placeholder="No."
				id="<%=value %>" autocomplete="off" style="width: 40px"> <input
				type="button" class="<%=result.getString(6) %>" value="Sell Stocks"
				onclick="sellStocks('<%=value %>' ,'<%=result.getString(6) %>' ,'<%=result.getString(2) %>' , '<%=result.getString(5) %>' , '<%=result.getString(1) %>')">
			</td>

			<%-- 
			<td><input type="button" id="<%=result.getString(2) %>" value="Sell" onclick="sellStocks('<%=result.getString(2) %>')"/></td>
             --%>

		</tr>
		<%
    }
    %>

		<tr>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td></td>
			<td>Total value of all the stocks(Current) <%
    out.println(i);	
    %>
			</td>
		</tr>
		<%
    result.close();
    %>
        
      </tbody>
    </table>
  </div>
</section>
<%
out.println(i);
    %>

<!-- ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
 -->
	<h3>Validation:</h3>

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
	<h4>Minimum 7 stocks should be there in Portfolio. Buy more stocks
	</h4>
	<%
		} 
		 
		else if(Integer.parseInt(result2.getString(1)) > 10)
		{
		%>
	<h4>Maximum 10 Stocks should be there in portfolio. Sell more
		stocks</h4>
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
	<h4>Cash more than 10%. Buy more Stocks</h4>
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
	<h4>DOW-30 Stocks less than 70%</h4>
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

		var Nifty50 =[" ", 'TATASTEEL','TATAMTRDVR','ZEEL','TATAPOWER','TATAMOTORS','GRASIM','ADANIPORTS','LUPIN','HCLTECH','ITC','AXISBANK','SBIN','AMBUJACEM','INDUSINDBK','LT','COALINDIA','IDEA','BOSCHLTD','TECHM','HDFCBANK','HEROMOTOCO','KOTAKBANK','ICICIBANK','M&M','AUROPHARMA','WIPRO','POWERGRID','HINDALCO','RELIANCE','ACC','SUNPHARMA','ONGC','GAIL','YESBANK','MARUTI','BANKBARODA','HINDUNILVR','ULTRACEMCO','NTPC','DRREDDY','TCS','BHEL','BHARTIARTL','BPCL','CIPLA','INFY','EICHERMOT','HDFC','ASIANPAINT','INFRATEL']; 
			
			var Dow30 = [" " ,'AXP','AAPL','BA','CAT','CSCO','CVX','DD','XOM','GE','GS','HD','IBM','INTC','JNJ','KO','JPM','MCD','MMM','MRK','MSFT','NKE','PFE','PG','TRV','UNH','UTX','VZ', 'V', 'WMT', 'DIS']; 
				
				var Straits_Times_Index = [" ", 'Y92', 'A17U', 'O39', 'BN4', 'C6L', 'D05', 'S58', 'C09', 'S59', 'U11', 'MC0', 'F34', 'C31', 'S51', 'U96', 'C38U', 'S68', 'S63', 'C52', 'C07', 'BS6', 'G13', 'T39', 'H78', 'E5H', 'Z74', 'U14', 'CC3', 'C61U'];
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
		    $("#stockpriceLocalCurrency").val(json.Localprice);
		    document.getElementById("demo").innerHTML = json.message;
		   // $("#json").val(json.message);
		    //$("#date").val(data);
		    
		  }
		);
		
		} //end of function getStockName
</script>



<h3>
	<p class="demo" id="demo"></p>
	<!-- to display user messages  -->
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
			stockPrice:$("#stockprice").val() , noOfStocks:$("#noofstocks").val() , localPrice : $("#stockpriceLocalCurrency").val()},
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
    		document.getElementById("demo").innerHTML = text;
    	}
    	//alert($("#deposit").val());
    	else{
    	$.post("DepositCash",
    			{Amount: $("#deposit").val() },
    			function(data){
    				text = " money deposited" ; //alert("Cash balance " + data);
    				document.getElementById("demo").innerHTML = text;
    				getBalance();
    			}
    		);}//end of the click function
    	
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
$(document).ready(function() {
	
	$('#downloadcsv').click(function () { 
		
		$.post("DownloadCsvSellTransactions",
				  
				  function(data){
				    alert("csv Downloaded");
				    //$('#cash').text(data); // add data after the class here...stopped here
				  }
				);//end of post function
		
	});
	
	
});

</script>

<script type="text/javascript">
function sellStocks(val ,s ,sname, noOfStoccksinDB, indexName) {
	var noOfSt = parseInt(noOfStoccksinDB);
	//alert(val);
	 var x , text;
	 x = $("#"+ val).val();
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
						{date : s , stockName : sname , noOfStock : $("#"+ val).val() , indexName : indexName},
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

<div id="jsonresp"></div>

<script type="text/javascript">
function testValidate(){
	//alert("button clicked");
	$.post("testValidate" ,
			function(json){
		alert(json.a + json.b + json.c + json.d+ json.e + json.f + json.g + json.h  );
		//document.getElementById("jsonresp").innerHTML = data;
	}
			
	);
}

</script>

<script type="text/javascript">
$(window).on("load resize ", function() {
	  var scrollWidth = $('.tbl-content').width() - $('.tbl-content table').width();
	  $('.tbl-header').css({'padding-right':scrollWidth});
	}).resize();
</script>
</html>