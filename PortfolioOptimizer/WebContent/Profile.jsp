<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@page import="java.sql.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

<style type="text/css">
.inline { 
    display: inline-block; 
   /*  border: 1px solid black;  */
    margin:10px;
    }
    
    .boxed {
  border: 1px solid green ;
}
</style>
</head>

<%
	if (!session.isNew()) {

	} else {
		response.sendRedirect("index.jsp");
	}
%>
Welcome
<%=session.getAttribute("userName")%>

<body>

	<!-- <div >
		<input type="radio" name="buysell" value="buy"> BUY<br> <input
			type="radio" name="buysell" value="sell"> SELL<br>

	</div>
 -->
 
 <div><a href="#" id = "depositCash">Deposit Cash</a>
 <input id = "deposit" />
 </div>
 
 <div class="boxed" id = "cash">
  Cash
</div>

	<div class='inline'>
		Choose Stock Index:<select id="indexval"
			onchange="indexSelect(this,document.getElementById('stockname'))">
			<option>Choose an Index</option>
		</select>
	</div>

	<div class='inline'>
		Choose Company:<select id="stockname" onchange="getStockName()">
		</select>
	</div>

	<div class='inline'>
		Current Stock Price:<input type="text" id="stockprice">
	</div>

	<div class='inline'>
		Enter the number of stocks : <input id=noofstocks>
	</div>

	<div >
		<input type="button" id="addtocart" name="addToCart" value="Buy"
			onclick="addToCart()">
	</div>






	////////////////////////////////////////////////////////////////////////////////////////////////
	<% Connection connection = null;
ResultSet result = null;
PreparedStatement stSelect = null; 
try 
	{
	ServletContext context = getServletContext();
	connection = (Connection)context.getAttribute("globalConnection");
	stSelect = connection.prepareStatement("select * from addtocart ");
	result = stSelect.executeQuery();
	/* System.out.println(result);
	while(result.next()){
		System.out.println(result.getString(1));
		
	}  */
		
	}

	catch(NullPointerException e)
	{
		System.out.println("Database Connection Not Established");
	}

%>
<table>
      <th>Stock Index</th>
        <th>Stock Name</th>
        <th>Total Price</th>
        <th>Stock Price</th>
        <th>No of Stocks</th>
<%
    while(result.next()){
        %>
        <tr>
            <td><%=result.getString(1) %></td>
            <td><%=result.getString(2) %></td>
            <td><%=result.getString(3) %></td>
            <td><%=result.getString(4) %></td>
            <td><%=result.getString(5) %></td>
            
            <TD> <a href="#">Sell</a> </TD>
            

        </tr>
        <%
    }
    result.close();
    %>
    </table>
////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
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

		var Nifty50 = [" ", 'ABB', 'IOC', 'VEDL' ];
		var Dow30 = [" ", 'AAPL', 'CSCO', 'INTC' , 'IBM' ];
		var Straits_Times_Index = [ " ",'Z74', 'S58', 'U14' ];

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

<script type="text/javascript">
function getStockName() {
	var stockName = document.getElementById('stockname').options[document
			.getElementById('stockname').selectedIndex].text;
	//window.alert(stockName);


var n = stockName;
$.post("GetPrice",
		  { name: n, time: "2pm" },
		  function(data){
		    //alert("Data Loaded: " + data);
		    $("#stockprice").val(data);
		  }
		);
		
		} //end of function getStockName
</script>

<script type="text/javascript">
function addToCart(){
	window.location.reload();
	$(document).ready(function(){

		
		//alert("Value: " + $("#stockprice").val() );

		});
	
	$.post("AddToCart",
			{stockIndex: $("#indexval").val() , stockName:$("#stockname").val() ,
		stockPrice:$("#stockprice").val() , noOfStocks:$("#noofstocks").val() },
			function(data){
				alert(data);
				getBalance();
			}
		);
	
} //end of function addtocart

</script>

<script type="text/javascript">
$(document).ready(function() {

	getBalance();
	
	//$('#cash').text("ertyeryt");
    
    $('#depositCash').click(function () { 
    	//alert($("#deposit").val());
    	$.post("DepositCash",
    			{Amount: $("#deposit").val() },
    			function(data){
    				//alert("Cash balance " + data);
    				getBalance();
    			}
    		);
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


</html>