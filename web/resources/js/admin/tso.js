$(function () {
	
	/********************************************************************************************************************
	 *										For TSO List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-tso-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-tso-grid").html() == "") {
        	fields = ['firstName', 'lastName', 'mobileNumber', 'territory.name', 'active', 'editLink', 'deleteLink'];
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
                       text: 'Cell Number',
                       width: 130,
                       dataIndex: 'mobileNumber'
                   },
                   {
                      text: 'Territory',
                      width: 160,
                      dataIndex: 'territory.name'
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
            loadGrid(fields, './completeTSOList.html?'+$("#searchForm").serialize(), 'tso',
				columns, 220, 835, 'admin-tso-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './tso.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-tso-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit TSO Page 										
	/********************************************************************************************************************/
	$("#rsmId").bind('change', function () {
		 $("#asmId").attr("disabled", "disabled");
	     $("#asmId option").remove();
	     $("#asmId").append("<option value=''>Please Select One</option>");
	     $.getJSON("./asmListByRSM.html?rsm_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, asm) {
	              $("#asmId").append("<option value='" + asm.id + "'>" + asm.firstName +" "+asm.lastName+"-"+asm.area.name + "</option>");
	          });
	     });
        $("#asmId").removeAttr("disabled");	
        $("#asmId").trigger('change');
	});
	
	$("#asmId").bind('change', function () {	
		 $("#territoryId").attr("disabled", "disabled");
	     $("#territoryId option").remove();
	     $("#territoryId").append("<option value=''>Please Select One</option>");
	     $.getJSON("./territoryListByASM.html?asm_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, area) {
	              $("#territoryId").append("<option value='" + area.id + "'>" + area.name + "</option>");
	          });
	     });
         $("#territoryId").removeAttr("disabled");	
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
		if ($("#mobileNumber").val() == '') {
            flag = addError("#mobileNumber", '');
        }
		if ($("#rsmId").val() == '') {			
            flag = addError("#rsmId", '');        
        }
		if ($("#asmId").val() == '') {			
            flag = addError("#asmId", '');        
        }
		if ($("#territoryId").val() == '') {			
            flag = addError("#territoryId", '');        
        }
		
		if(flag)
			return false;
	});
});
