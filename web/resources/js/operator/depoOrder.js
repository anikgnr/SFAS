$(function () {
	
	/********************************************************************************************************************
	 *										For Depo Order Form 										
	/********************************************************************************************************************/
	if ($("#orderDate").length > 0) {
	   $("#orderDate").datepicker({ maxDate: new Date() });
	}
	
	$(".qty").change(function() {
		idx = $(this).attr('id').split('-')[1];
		amount = $("#qty-"+idx).val() * $("#rate-"+idx).val();
		totalAmount = $("#orderAmount").val() - $("#amount-"+idx).val() + amount;
		$("#amount-"+idx).val(amount);
		$("#orderAmount").val(totalAmount);
		$("#amountDiv-"+idx).html(amount);
		$("#totalAmount").html(totalAmount);		
	});
	
	$("#submitDepoOrder").click(function(){
		flag = false;
		clearForm();
		message = "";
		
		if ($("#orderDate").val() == '' || !isValidDate($("#orderDate").val())) {
            flag = true;
            $("#orderDate").parent().css('background','#ED8080');
            message += "* Please provide a valid Order Date.<br/>";
        }
		
		if($("#orderAmount").val() == "0.0" || $("#orderAmount").val() == "0"){
			flag = true;
			$("#totalAmount").css('background','#ED8080');
			message += "* Please add atleast one item on your Order.<br/>";
			
		}else if($("#orderAmount").val() * 1.0 > $("#currentBalance").val()){
			flag = true;
			$("#totalAmount").css('background','#ED8080');
			message += "* Payable Amount exceeds Current Balance. Please refactor your Order.<br/>";
		}
		
		if(flag){
			$("#orderErrorBlock").html(message);
			$("#orderErrorBlock").show();
			return false;
		}
	});
	
	function clearForm(){
		$("#orderErrorBlock").hide();
		$("#orderErrorBlock").html("");
		$("#orderDate").parent().css('background','white');
		$("#totalAmount").css('background','white');
	}
	
	$("#backToDepoOrderList").click(function(){
		window.location="./depoOrderList.html?id="+$("#depoId").val();
	});
		
	$("#approveDepoOrder").click(function(){
		if(confirm("Are you sure ?")){
			window.location="./depoOrderApprove.html?id="+$("#id").val()+"&type="+$("#approveType").val();
		}
	});
	
	$("#denyDepoOrder").click(function(){
		if(confirm("Are you sure ?")){
			window.location="./depoOrderUnApprove.html?id="+$("#id").val()+"&type="+$("#approveType").val();
		}
	});
	
	/********************************************************************************************************************
	 *										For Depo Order List Page 										
	/********************************************************************************************************************/
	
	if ($("#orderFromDate").length > 0) {
		   $("#orderFromDate").datepicker();
	}
	if ($("#orderToDate").length > 0) {
	   $("#orderToDate").datepicker();
	}
	
	if($("#admin-order-grid").length > 0)
		loadStockGrid();

	function loadStockGrid() {
		if ($("#admin-order-grid").html() == "") {
        	fields = ['orderDate', 'orderAmount', 'mdApproved', 'lastApprovedBy', 'delivered', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },                   
                   {
                       text: 'Order Date',
                       width: 150,
                       dataIndex: 'orderDate',
                       align: 'center',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y')
                   },
                   {
                       text: 'Order Amount',
                       width: 150,
                       dataIndex: 'orderAmount',
                       align: 'center',
                       renderer: function (value) { return value+" Tk"; }                       
                   },
                   {
                       text: 'Is Fully Approved',
                       width: 100,
                       dataIndex: 'mdApproved',
                       align: 'center'
                   },
                   {
                       text: 'Last Approved By',
                       width: 100,
                       dataIndex: 'lastApprovedBy',
                       align: 'center'                       
                   },
                   {
                       text: 'Is Delivered',
                       width: 100,
                       dataIndex: 'delivered',
                       align: 'center'
                   },
                   {
                       text: '',
                       width: 50,
                       dataIndex: 'editLink'
                   },
                   {
                       text: '',
                       width: 50,
                       dataIndex: 'deleteLink'
                   }                   
               ];            
            loadGrid(fields, './depoCompleteOrderList.html?'+$("#searchForm").serialize(), 'order',
				columns, 440, 750, 'admin-order-grid');

        }

    }
		
	$("#orderSearchBtn").click(function(){
		$("#admin-order-grid").html('');
		loadStockGrid();
	});
	
	$("#backToDepo").click(function(){
		window.location='./depoList.html';
	});
	
	$("#createDepoOrder").click(function(){
		window.location="./depoOrder.html?did="+$("#depoId").val();
	});
});

