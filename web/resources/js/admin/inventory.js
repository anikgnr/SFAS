$(function () {
	
	/********************************************************************************************************************
	 *										For Inventory List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-inventory-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-inventory-grid").html() == "") {
        	fields = ['name', 'address', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Inventory Name',
                       width: 220,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'Inventory Location',
                       width: 270,
                       dataIndex: 'address'
                   },
                  {
                      text: 'Is Active',
                      width: 120,
                      dataIndex: 'active'
                  },
                  {
                      text: '',
                      width: 70,
                      dataIndex: 'editLink'
                  },
                  {
                      text: '',
                      width: 70,
                      dataIndex: 'deleteLink'
                  }                  
               ];
            loadGrid(fields, './completeInventoryList.html?'+$("#searchForm").serialize(), 'inventory',
				columns, 220, 800, 'admin-inventory-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './inventory.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-inventory-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Inventory Page 										
	/********************************************************************************************************************/
	
	        
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#name").val() == '') {
            flag = addError("#name", '');
        }
		if ($("#address").val() == '') {
            flag = addError("#address", '');
        }
		
		if(flag)
			return false;
	});
});
