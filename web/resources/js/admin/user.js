$(function () {
	
	/********************************************************************************************************************
	 *										For User List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-user-grid").length > 0)
		loadUserGrid();

	function loadUserGrid() {

        if ($("#admin-user-grid").html() == "") {
        	fields = ['firstName', 'lastName', 'roleName', 'mobileNumber', 'active', 'editLink', 'deleteLink'];
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
                      text: 'Role',
                      width: 130,
                      dataIndex: 'roleName'
                  },
                  {
                      text: 'Cell Number',
                      width: 150,
                      dataIndex: 'mobileNumber'
                  },
                  {
                      text: 'Is Active',
                      width: 60,
                      dataIndex: 'active'
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
            loadGrid(fields, './completeUserList.html?'+$("#searchForm").serialize(), 'user',
				columns, 220, 835, 'admin-user-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './user.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-user-grid").html('');
		loadUserGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit User Page 										
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
		if ($("#userName").val() == '') {
            flag = addError("#userName", '');
        }
		if ($("#password").val() == '') {
            flag = addError("#password", '');
        }else if($("#password").val() != $("#confirmPassword").val()){
        	flag = addError("#confirmPassword", 'Password Mismatch !!');
        }
		if ($("#role").val() == '') {
            flag = addError("#role", '');
        }
		if ($("#mobileNumber").val() == '') {
            flag = addError("#mobileNumber", '');
        }
		
		if(flag)
			return false;
	});
});
