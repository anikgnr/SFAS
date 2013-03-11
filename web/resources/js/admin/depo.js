$(function () {
	
	/********************************************************************************************************************
	 *										For DEPO List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-depo-grid").length > 0)
		loadDEPOGrid();

	function loadDEPOGrid() {

        if ($("#admin-depo-grid").html() == "") {
        	fields = ['name', 'address', 'rsm.region.name', 'rsm.name', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'DEPO Name',
                       width: 170,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'DEPO Location',
                       width: 170,
                       dataIndex: 'address'
                   },                   
                  {
                      text: 'Region',
                      width: 120,
                      dataIndex: 'rsm.region.name'
                  },
                  {
                      text: 'RSM',
                      width: 170,
                      dataIndex: 'rsm.name'
                  },
                  {
                      text: 'Is Active',
                      width: 60,
                      dataIndex: 'active'
                  },
                  {
                      text: '',
                      width: 45,
                      dataIndex: 'editLink'
                  },
                  {
                      text: '',
                      width: 50,
                      dataIndex: 'deleteLink'
                  }                  
               ];
            loadGrid(fields, './completeDepoList.html?'+$("#searchForm").serialize(), 'depo',
				columns, 220, 835, 'admin-depo-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './depo.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-depo-grid").html('');
		loadDEPOGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit DEPO Page 										
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
		if ($("#contactName").val() == '') {
            flag = addError("#contactName", '');
        }
		if ($("#mobileNumber").val() == '') {			
            flag = addError("#mobileNumber", '');        
        }
		if ($("#rsmId").val() == '') {			
            flag = addError("#rsmId", '');        
        }
		
		if(flag)
			return false;
	});
});
