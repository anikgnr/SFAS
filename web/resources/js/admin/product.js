$(function () {
	
	/********************************************************************************************************************
	 *										For Product List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-product-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-product-grid").html() == "") {
        	fields = ['productName', 'bagSize', 'rate', 'profitMargin', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product Name',
                       width: 190,
                       dataIndex: 'productName'                       
                   },                   
                   {
                       text: 'Bag Size',
                       width: 130,
                       dataIndex: 'bagSize',
                       align: "center",
                       renderer: function (value) { return value+" pcs"; }
                   },                   
                  {
                      text: 'Sell Price',
                      width: 130,
                      dataIndex: 'rate',
                      align: "center",
                      renderer: function (value) { return value+" Tk"; }
                  },
                  {
                      text: 'Profit Margin',
                      width: 130,
                      dataIndex: 'profitMargin',
                      align: "center",
	                  renderer: function (value) { return value+" %"; }
                  },
                  {
                      text: 'Is Active',
                      width: 80,
                      dataIndex: 'active'
                  },
                  {
                      text: '',
                      width: 60,
                      dataIndex: 'editLink'
                  },
                  {
                      text: '',
                      width: 60,
                      dataIndex: 'deleteLink'
                  }                  
               ];
            loadGrid(fields, './completeProductList.html?'+$("#searchForm").serialize(), 'product',
				columns, 220, 835, 'admin-product-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './product.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-product-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Product Page 										
	/********************************************************************************************************************/
	
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#productName").val() == '') {
            flag = addError("#productName", '');
        }
		if ($("#bagSize").val() == '') {
            flag = addError("#bagSize", '');
        }
		if ($("#rate").val() == '') {
            flag = addError("#rate", '');
        }
		if ($("#profitMargin").val() == '') {			
            flag = addError("#profitMargin", '');        
        }
		
		if(flag)
			return false;
	});
});
