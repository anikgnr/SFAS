$(function () {
	
	/********************************************************************************************************************
	 *										For Region List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-region-grid").length > 0)
		loadRSMGrid();

	function loadRSMGrid() {

        if ($("#admin-region-grid").html() == "") {
        	fields = ['id', 'name', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Region ID',
                       width: 100,
                       dataIndex: 'id',
                       align: 'center'
                   },                   
                   {
                       text: 'Region Name',
                       width: 300,
                       dataIndex: 'name'
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
            loadGrid(fields, './completeRegionList.html?'+$("#searchForm").serialize(), 'region',
				columns, 220, 570, 'admin-region-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './region.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-region-grid").html('');
		loadRSMGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Region Page 										
	/********************************************************************************************************************/
	
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#name").val() == '') {
            flag = addError("#name", '');
        }
		
		if(flag)
			return false;
	});
});
