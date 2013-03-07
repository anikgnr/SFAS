$(function () {
	
	/********************************************************************************************************************
	 *										For SR List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-sr-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-sr-grid").html() == "") {
        	fields = ['firstName', 'lastName', 'mobileNumber', 'tso.name', 'active', 'editLink', 'deleteLink'];
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
                      text: 'TSO',
                      width: 160,
                      dataIndex: 'tso.name'
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
            loadGrid(fields, './completeSRList.html?'+$("#searchForm").serialize(), 'sr',
				columns, 220, 835, 'admin-sr-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './sr.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-sr-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit SR Page 										
	/********************************************************************************************************************/
	$("#rsmId").bind('change', function () {
		 $("#asmId").attr("disabled", "disabled");
	     $("#asmId option").remove();
	     $("#asmId").append("<option value=''></option>");
	     $.getJSON("./asmListByRSM.html?rsm_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, asm) {
	              $("#asmId").append("<option value='" + asm.id + "'>" + asm.firstName +" "+asm.lastName+"-("+asm.area.name + ")</option>");
	          });
	     });
        $("#asmId").removeAttr("disabled");	
        $("#asmId").trigger('change');
	});
	
	$("#asmId").bind('change', function () {
		 $("#tsoId").attr("disabled", "disabled");
	     $("#tsoId option").remove();
	     $("#tsoId").append("<option value=''></option>");
	     $.getJSON("./tsoListByASM.html?asm_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, tso) {
	              $("#tsoId").append("<option value='" + tso.id + "'>" + tso.firstName +" "+tso.lastName+"-("+tso.territory.name + ")</option>");
	          });
	     });
       $("#tsoId").removeAttr("disabled");       
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
		if ($("#tsoId").val() == '') {			
            flag = addError("#tsoId", '');        
        }
		
		if(flag)
			return false;
	});
});
