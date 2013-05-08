$(function () {
	
	/********************************************************************************************************************
	 *										For Area List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-area-grid").length > 0)
		loadASMGrid();

	function loadASMGrid() {

        if ($("#admin-area-grid").html() == "") {
        	fields = ['id', 'name', 'region.name', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Area ID',
                       width: 100,
                       dataIndex: 'id',
                       align: 'center'
                   },                   
                   {
                       text: 'Area Name',
                       width: 250,
                       dataIndex: 'name'
                   },                   
                   {
                       text: 'Region',
                       width: 250,
                       dataIndex: 'region.name'
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
            loadGrid(fields, './completeAreaList.html?'+$("#searchForm").serialize(), 'area',
				columns, 320, 770, 'admin-area-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './area.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-area-grid").html('');
		loadASMGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Area Page 										
	/********************************************************************************************************************/
	
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#name").val() == '') {
            flag = addError("#name", '');
        }
		if ($("#regionId").val() == '') {			
            flag = addError("#regionId", '');        
        }

		if(flag)
			return false;
	});
});
