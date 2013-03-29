$(function () {
	
	/********************************************************************************************************************
	 *										For Product List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-account-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-account-grid").html() == "") {
        	fields = ['bankName', 'bankAccount', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Bank Name',
                       width: 300,
                       dataIndex: 'bankName'                       
                   },                   
                   {
                       text: 'Account Number',
                       width: 260,
                       dataIndex: 'bankAccount'
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
            loadGrid(fields, './completeAccountList.html?'+$("#searchForm").serialize(), 'account',
				columns, 220, 835, 'admin-account-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './bankaccount.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-account-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Product Page 										
	/********************************************************************************************************************/
	
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#bankName").val() == '') {
            flag = addError("#bankName", '');
        }
		if ($("#bankAccount").val() == '') {
            flag = addError("#bankAccount", '');
        }
		
		if(flag)
			return false;
	});
});
