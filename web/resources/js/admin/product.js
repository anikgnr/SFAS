$(function () {
	
	/********************************************************************************************************************
	 *										For Product List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-product-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-product-grid").html() == "") {
        	fields = ['productName', 'bagSize', 'active', 'editLink', 'deleteLink', 'rateLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product Name',
                       width: 220,
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
                      text: 'Is Active',
                      width: 80,
                      dataIndex: 'active',
                      align: "center"
                  },
                  {
                      text: '',
                      width: 60,
                      dataIndex: 'editLink',
                      align: "center"
                  },
                  {
                      text: '',
                      width: 60,
                      dataIndex: 'deleteLink',
                      align: "center"
                  },
                  {
                      text: '',
                      width: 100,
                      dataIndex: 'rateLink',
                      align: "center"
                  }                  
               ];
            loadGrid(fields, './completeProductList.html?'+$("#searchForm").serialize(), 'product',
				columns, 400, 700, 'admin-product-grid');

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
        }else if (isNotNumber($("#bagSize").val())) {
        	flag = addError("#bagSize", '');      
        }
		
		/*if ($("#rate").val() == '') {
            flag = addError("#rate", '');
        }else if (isNotNumber($("#rate").val())) {
        	flag = addError("#rate", '');      
        }
		
		if ($("#profitMargin").val() == '') {			
            flag = addError("#profitMargin", '');        
        }else if (isNotNumber($("#profitMargin").val())) {
        	flag = addError("#profitMargin", '');      
        }*/
		
		if(flag)
			return false;
	});
	
	$("#backToProduct").click(function(){
		window.location = './productList.html';
	});
		
});
