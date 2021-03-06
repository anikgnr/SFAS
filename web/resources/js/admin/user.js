$(function () {
	
	/********************************************************************************************************************
	 *										For User List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-user-grid").length > 0)
		loadUserGrid();

	function loadUserGrid() {

        if ($("#admin-user-grid").html() == "") {
        	fields = ['fullName', 'userName', 'roleName', 'mobileNumber', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Full Name',
                       width: 210,
                       dataIndex: 'fullName'                       
                   },                   
                   {
                       text: 'User Name',
                       width: 130,
                       dataIndex: 'userName'
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
				columns, 320, 835, 'admin-user-grid');

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

	if($("#sameUserName").length > 0 && $("#sameUserName").val()=="true"){
		addError("#userName", 'Username already used. Please try another one.');
	}
		
	$("#role").bind('change', function () {
		$("#deptBlock").hide();
		$("#factoryBlock").hide();
		
		if($("#managerRole").val()==$(this).val()){
			$("#deptBlock").show();
		}else if($("#factoryMgrRole").val()==$(this).val() || $("#factoryOprRole").val()==$(this).val()){
			$("#factoryBlock").show();
		}
	});
	
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
        }else if($("#role").val() ==  $("#managerRole").val() && $("#department").val() == ''){
        	flag = addError("#department", '');        
        }else if(($("#role").val() ==  $("#factoryMgrRole").val() || $("#role").val() ==  $("#factoryOprRole").val()) && $("#factoryId").val() == ''){
        	flag = addError("#factoryId", '');
        }
		if ($("#mobileNumber").val() == '') {
            flag = addError("#mobileNumber", '');
        }		
		if ($("#email").val() == '') {
            flag = addError("#email", '');
        }else if (!isValidEmail($("#email").val())) {
        	flag = addError("#email", 'invalid email !!');
        }
		
		if(flag)
			return false;
	});
});
