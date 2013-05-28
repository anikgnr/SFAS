$(function () {
	
	/********************************************************************************************************************
	 *										For Factory List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-factory-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-factory-grid").html() == "") {
        	fields = ['name', 'address', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Factory Name',
                       width: 220,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'Factory Address',
                       width: 340,
                       dataIndex: 'address'
                   },                   
                  {
                      text: 'Is Active',
                      width: 100,
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
            loadGrid(fields, './completeFactoryList.html?'+$("#searchForm").serialize(), 'factory',
				columns, 320, 835, 'admin-factory-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './companyfactory.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-factory-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Factory Page 										
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
