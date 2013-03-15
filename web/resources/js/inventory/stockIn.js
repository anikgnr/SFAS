$(function () {
	/********************************************************************************************************************
	 *										For Today's Stock In List Page 										
	/********************************************************************************************************************/
	if ($("#stockInFromDate").length > 0) {
		   $("#stockInFromDate").datepicker();
	}
	if ($("#stockInToDate").length > 0) {
	   $("#stockInToDate").datepicker();
	}
	
	if($("#admin-stockin-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-stockin-grid").html() == "") {
        	fields = ['product.fullName', 'stockInDate', 'manufactureDate', 'expireDate', 'quantity', 'createdBy', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Product',
                       width: 170,
                       dataIndex: 'product.fullName'                       
                   },                   
                   {
                       text: 'Stock In Date',
                       width: 100,
                       dataIndex: 'stockInDate',
                       renderer: Ext.util.Format.dateRenderer('m/d/Y')
                   },                   
                  {
                      text: 'Manufacture Date',
                      width: 100,
                      dataIndex: 'manufactureDate',
                      renderer: Ext.util.Format.dateRenderer('m/d/Y')
                  },
                  {
                      text: 'Expire Date',
                      width: 100,
                      dataIndex: 'expireDate',
                      renderer: Ext.util.Format.dateRenderer('m/d/Y')
                  },
                  {
                      text: 'Stock Quantity',
                      width: 100,
                      dataIndex: 'quantity'
                  },
                  {
                      text: 'Stock Entry By',
                      width: 120,
                      dataIndex: 'createdBy'
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
            loadGrid(fields, './todayStockInList.html?'+$("#searchForm").serialize(), 'stockin',
				columns, 220, 835, 'admin-stockin-grid');

        }

    }
		
	$("#searchBtn").click(function(){
		$("#admin-stockin-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Stock In Page 										
	/********************************************************************************************************************/
	
	if ($("#stockInDate").length > 0) {
		   $("#stockInDate").datepicker({ maxDate: new Date() });
	}
	if ($("#manufactureDate").length > 0) {
	   $("#manufactureDate").datepicker({ maxDate: new Date() });
	}
	if ($("#expireDate").length > 0) {
	   $("#expireDate").datepicker({ minDate: new Date() });
	}
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#productId").val() == '') {
            flag = addError("#productId", '');
        }
		if ($("#stockInDate").val() == '') {
            flag = addError("#stockInDate", '');
        }else if (!isValidDate($("#stockInDate").val())) {
            flag = addError("#stockInDate", 'Invalid Stock In Date');            
        }
		if ($("#manufactureDate").val() == '') {
            flag = addError("#manufactureDate", '');
        }else if (!isValidDate($("#manufactureDate").val())) {
            flag = addError("#manufactureDate", 'Invalid Manufacture Date');            
        }
		if ($("#expireDate").val() == '') {
            flag = addError("#expireDate", '');
        }else if (!isValidDate($("#expireDate").val())) {
            flag = addError("#expireDate", 'Invalid Expire Date');            
        }
		
		if ($("#quantity").val() == '' || $("#quantity").val() == '0') {			
            flag = addError("#quantity", '');        
		}else if (isNotNumber($("#quantity").val())) {
        	flag = addError("#quantity", 'Invalid Quantity !!');      
        }
		
		if(flag)
			return false;
	});
});
