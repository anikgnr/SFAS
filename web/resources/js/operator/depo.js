$(function () {
	
	/********************************************************************************************************************
	 *										For DEPO List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-depo-grid").length > 0)
		loadDEPOGrid();

	function loadDEPOGrid() {

        if ($("#admin-depo-grid").html() == "") {
        	fields = ['name' , 'rsm.nameWithRegion', 'currentBalance', 'ordersLink', 'orderLink', 'damageLink', 'planLink', 'stockLink', 'depositLink', 'salesLink'];
            columns = [
                   {
                       text: '',
                       width: 15,
                       dataIndex: ''
                   },
                   {
                       text: 'DEPO Name',
                       width: 130,
                       dataIndex: 'name'                       
                   },
                  {
                      text: 'RSM',
                      width: 190,
                      dataIndex: 'rsm.nameWithRegion'
                  },
                  {
                      text: 'Current Balance',
                      width: 100,
                      dataIndex: 'currentBalance',
                      align: 'center',
                      renderer: function (value) { return value+" Tk"; }
                  },
                  {
                      text: '',
                      width: 55,
                      dataIndex: 'stockLink'
                  },
                  {
                      text: '',
                      width: 65,
                      dataIndex: 'damageLink'
                  },
                  {
                      text: '',
                      width: 50,
                      dataIndex: 'salesLink'
                  },
                  {
                      text: '',
                      width: 65,
                      dataIndex: 'depositLink'
                  },
                  {
                      text: '',
                      width: 80,
                      dataIndex: 'planLink'
                  },
                  {
                      text: '',
                      width: 55,
                      dataIndex: 'ordersLink'
                  },
                  {
                      text: '',
                      width: 80,
                      dataIndex: 'orderLink'
                  }                  
               ];
            loadGrid(fields, './activeDepoList.html?'+$("#searchForm").serialize(), 'depo',
				columns, 420, 900, 'admin-depo-grid');

        }

    }
	
	$("#searchBtn").click(function(){
		$("#admin-depo-grid").html('');
		loadDEPOGrid();
	});
	
	
	/********************************************************************************************************************
	 *										For Depo Current Stock List Page 										
	/********************************************************************************************************************/

	if($("#admin-stock-grid").length > 0)
		loadStockGrid();

	function loadStockGrid() {
		if ($("#admin-stock-grid").html() == "") {
        	fields = ['product.fullName', 'quantity', 'lastStockInDate', 'lastStockOutDate'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product',
                       width: 220,
                       dataIndex: 'product.fullName'                       
                   },
                   {
                       text: 'Current Stock Quantity',
                       width: 150,
                       dataIndex: 'quantity'
                   },                   
                   {
                       text: 'Last Stock In Date',
                       width: 150,
                       dataIndex: 'lastStockInDate',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y')
                   },                   
                   {
                       text: 'Last Stock Out Date',
                       width: 150,
                       dataIndex: 'lastStockOutDate',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y')
                   }                  
               ];            
            loadGrid(fields, './depoCurrentStockList.html?'+$("#searchForm").serialize(), 'stock',
				columns, 440, 720, 'admin-stock-grid');

        }

    }
		
	$("#stockSearchBtn").click(function(){
		$("#admin-stock-grid").html('');
		loadStockGrid();
	});
	
	$("#backToDepo").click(function(){
		window.location='./depoList.html';
	});
	
	/********************************************************************************************************************
	 *										For Depo Damage Stock List Page 										
	/********************************************************************************************************************/

	if($("#admin-damage-grid").length > 0)
		loadDamageGrid();

	function loadDamageGrid() {
		if ($("#admin-damage-grid").html() == "") {
        	fields = ['product.productName', 'product.bagSize', 'damageType', 'quantity'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product Name',
                       width: 220,
                       dataIndex: 'product.productName'                       
                   },
                   {
                       text: 'Product Bag Size',
                       width: 130,
                       dataIndex: 'product.bagSize',
                       align: 'center',
                       renderer: function (value) { return value+" pcs"; }
                   },                   
                   {
                       text: 'Damage Type',
                       width: 190,
                       dataIndex: 'damageType'
                   },                   
                   {
                       text: 'Quantity',
                       width: 130,
                       dataIndex: 'quantity'
                   }                  
               ];
            loadGrid(fields, './depoCurrentDamageList.html?'+$("#searchForm").serialize(), 'damage',
				columns, 440, 720, 'admin-damage-grid');

        }

    }
		
	$("#damageSearchBtn").click(function(){
		$("#admin-damage-grid").html('');
		loadDamageGrid();
	});

	
	/********************************************************************************************************************
	 *										For Depo Deposit List Page 										
	/********************************************************************************************************************/
	if ($("#depositFromDate").length > 0) {
		   $("#depositFromDate").datepicker();
	}
	if ($("#depositToDate").length > 0) {
	   $("#depositToDate").datepicker();
	}
	
	if($("#admin-deposit-grid").length > 0)
		loadDepositGrid();

	function loadDepositGrid() {
		if ($("#admin-deposit-grid").html() == "") {
			 fields = ['account.completeName', 'depositAmount', 'depositDate', 'accountApproved', 'accountApprovedBy', 'accountApprovedDate', 'editLink', 'deleteLink'];
        	 columns = [
                        {
                            text: '',
                            width: 25,
                            dataIndex: ''
                        },
                        {
                            text: 'Bank Account',
                            width: 200,
                            dataIndex: 'account.completeName'                       
                        },
                        {
                            text: 'Deposit Amount',
                            width: 130,
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
                            text: 'Is Approved',
                            width: 80,
                            dataIndex: 'accountApproved'
                        },
                        {
                            text: 'Approved By',
                            width: 100,
                            dataIndex: 'accountApprovedBy'
                        },
                        {
                            text: 'Approved Date',
                            width: 120,
                            dataIndex: 'accountApprovedDate',
                            renderer: Ext.util.Format.dateRenderer('m/d/Y')
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
            loadGrid(fields, './depoCompleteDepositList.html?'+$("#searchForm").serialize(), 'deposit',
				columns, 440, 900, 'admin-deposit-grid');

        }

    }
		
	$("#depositSearchBtn").click(function(){
		$("#admin-deposit-grid").html('');
		loadDepositGrid();
	});
	
	$("#createDepoDeposit").click(function(){
		window.location="./depoDeposit.html?did="+$("#depoId").val();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Depo Deposit Page 										
	/********************************************************************************************************************/
	
	if ($("#depositDate").length > 0) {
		   $("#depositDate").datepicker({ maxDate: new Date() });
	}
	$("#saveDepositBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#accountId").val() == '') {
            flag = addError("#accountId", '');
        }
		if ($("#depositAmount").val() == '' || $("#depositAmount").val() == '0' || $("#depositAmount").val() == '0.0') {			
            flag = addError("#depositAmount", '');        
		}else if (isNotNumber($("#depositAmount").val())) {
        	flag = addError("#depositAmount", 'Invalid Amount !!');      
        }
		
		if ($("#depositDate").val() == '') {
            flag = addError("#depositDate", '');
        }else if (!isValidDate($("#depositDate").val())) {
            flag = addError("#depositDate", 'Invalid Deposit Date');            
        }
		
		
		if(flag)
			return false;
	});
	
	$("#depositBackBtn").click(function(){
		window.location="./depoDepositList.html?id="+$("#depoId").val();
	});
	
	/********************************************************************************************************************
	 *										For Depo Sell Summary List Page 										
	/********************************************************************************************************************/

	if($("#admin-sell-grid").length > 0)
		loadSellGrid();

	function loadSellGrid() {
		if ($("#admin-sell-grid").html() == "") {
        	fields = ['product.fullName', 'quantity', 'lastSellDate'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product',
                       width: 230,
                       dataIndex: 'product.fullName'                       
                   },
                   {
                       text: 'Sold Quantity',
                       width: 180,
                       dataIndex: 'quantity',
                       align: 'center'
                   },                   
                   {
                       text: 'Last Sell Date',
                       width: 180,
                       dataIndex: 'lastSellDate',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y'),
                       align: 'center'
                   }                  
               ];            
            loadGrid(fields, './depoCompleteSellList.html?'+$("#searchForm").serialize(), 'stock',
				columns, 440, 640, 'admin-sell-grid');

        }

    }
		
	$("#sellSearchBtn").click(function(){
		$("#admin-sell-grid").html('');
		loadSellGrid();
	});
	

});
