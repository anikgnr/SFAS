$(function () {
	
	/********************************************************************************************************************
	 *										For RSM List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-rsm-grid").length > 0)
		loadRSMGrid();

	function loadRSMGrid() {

        if ($("#admin-rsm-grid").html() == "") {
        	fields = ['firstName', 'lastName', 'region.name', 'mobileNumber', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'First Name',
                       width: 170,
                       dataIndex: 'firstName'                       
                   },                   
                   {
                       text: 'Last Name',
                       width: 170,
                       dataIndex: 'lastName'
                   },                   
                  {
                      text: 'Region',
                      width: 160,
                      dataIndex: 'region.name'
                  },
                  {
                      text: 'Cell Number',
                      width: 130,
                      dataIndex: 'mobileNumber'
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
            loadGrid(fields, './completeRSMList.html?'+$("#searchForm").serialize(), 'rsm',
				columns, 220, 835, 'admin-rsm-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './rsm.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-rsm-grid").html('');
		loadRSMGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit RSM Page 										
	/********************************************************************************************************************/
	
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#firstName").val() == '') {
            flag = addError("#firstName", '');
        }
		if ($("#lastName").val() == '') {
            flag = addError("#lastName", '');
        }
		if ($("#mobileNumber").val() == '') {
            flag = addError("#mobileNumber", '');
        }
		if ($("#regionId").val() == '') {			
            flag = addError("#regionId", '');        
        }
		if ($("#monthlyGross").val() == '') {			
            flag = addError("#monthlyGross", '');        
        }else if (isNotNumber($("#monthlyGross").val())) {
        	flag = addError("#monthlyGross", 'Invalid Amount !!');      
        }
        	
		if(flag)
			return false;
	});
});
