$(function () {
	alert('test');
	//if($("#admin-user-grid").length > 0)
		loadUserGrid();
	
	
	
    function loadUserGrid() {

        if ($("#admin-user-grid").html() == "") {
        	alert('test');
            fields = ['firstName', 'lastName', 'role', 'mobileNumber', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'First Name',
                       width: 70,
                       dataIndex: 'firstName'                       
                   },
                   
                   {
                       text: 'Last Name',
                       width: 70,
                       dataIndex: 'lastName'
                   },
                   
                  {
                      text: 'Role',
                      width: 50,
                      dataIndex: 'role'
                  },
                  {
                      text: 'Mobile Number',
                      width: 70,
                      dataIndex: 'mobileNumber'
                  },
                  {
                      text: 'Is Active',
                      width: 60,
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
            loadGrid(fields, './completeUserList.html' , 'user',
				columns, 220, 635, 'admin-user-grid');

        }

    }

}
