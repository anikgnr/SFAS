$(function () {
	/********************************************************************************************************************
	 *										For Depo Deposit List Page 										
	/********************************************************************************************************************/
	if ($("#depositFromDate").length > 0) {
		   $("#depositFromDate").datepicker();
	}
	if ($("#depositToDate").length > 0) {
	   $("#depositToDate").datepicker();
	}

	if($("#admin-depo-deposit-grid").length > 0)
		loadDepositGrid();

	function loadDepositGrid() {
		if ($("#admin-depo-deposit-grid").html() == "") {
			 fields = ['depo.fullName', 'account.bankName', 'account.bankAccount', 'depositAmount', 'depositDate', 'approveLink'];
        	 columns = [
                        {
                            text: '',
                            width: 25,
                            dataIndex: ''
                        },
                        {
                            text: 'DEPO',
                            width: 170,
                            dataIndex: 'depo.fullName'                       
                        },
                        {
                            text: 'Bank Name',
                            width: 200,
                            dataIndex: 'account.bankName'                       
                        },
                        {
                            text: 'Account Number',
                            width: 150,
                            dataIndex: 'account.bankAccount'                       
                        },
                        {
                            text: 'Deposit Amount',
                            width: 150,
                            dataIndex: 'depositAmount',
                            align: "center",
                            renderer: function (value) { return value+" Tk"; }
                        },                   
                        {
                            text: 'Deposit Date',
                            width: 120,
                            dataIndex: 'depositDate',
                            renderer: Ext.util.Format.dateRenderer('m/d/Y')
                        },
                        {
                            text: '',
                            width: 60,
                            dataIndex: 'approveLink'
                        }                           
                    ];            
            loadGrid(fields, './depoCompleteDepositList.html?'+$("#searchForm").serialize(), 'deposit',
				columns, 440, 900, 'admin-depo-deposit-grid');

        }

    }
	
	$("#depositSearchBtn").click(function(){
		$("#admin-depo-deposit-grid").html('');
		loadDepositGrid();
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
        	fields = ['depo.name', 'depo.rsm.region.name', 'orderDate', 'orderAmount', 'lastModifiedBy', 'lastApprovedBy', 'detailLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },                   
                   {
                       text: 'DEPO Name',
                       width: 150,
                       dataIndex: 'depo.name'
                   },                   
                   {
                       text: 'DEPO Region',
                       width: 120,
                       dataIndex: 'depo.rsm.region.name'
                   },                   
                   {
                       text: 'Order Date',
                       width: 120,
                       dataIndex: 'orderDate',
                       align: 'center',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y')
                   },
                   {
                       text: 'Order Amount',
                       width: 130,
                       dataIndex: 'orderAmount',
                       align: 'center',
                       renderer: function (value) { return value+" Tk"; }                       
                   },
                   {
                       text: 'Prepared By',
                       width: 150,
                       dataIndex: 'lastModifiedBy',
                       align: 'center'
                   },
                   {
                       text: '',
                       width: 50,
                       dataIndex: 'detailLink'
                   }                   
               ];            
            loadGrid(fields, './depoCompleteOrderList.html?'+$("#searchForm").serialize(), 'order',
				columns, 440, 770, 'admin-order-grid');

        }

    }
		
	$("#orderSearchBtn").click(function(){
		$("#admin-order-grid").html('');
		loadStockGrid();
	});
	
});

function doApprove(url){
	
	if(confirm("Are you sure ?")){
		window.location = url;
	}
}