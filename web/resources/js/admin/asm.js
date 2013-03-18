$(function () {
	
	/********************************************************************************************************************
	 *										For ASM List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-asm-grid").length > 0)
		loadASMGrid();

	function loadASMGrid() {

        if ($("#admin-asm-grid").html() == "") {
        	fields = ['name', 'rsm.name', 'area.name', 'mobileNumber', 'active', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Full Name',
                       width: 190,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'Area',
                       width: 160,
                       dataIndex: 'area.name'
                   },                   
                   {
                       text: 'RSM',
                       width: 170,
                       dataIndex: 'rsm.name'
                   },
                  {
                      text: 'Cell Number',
                      width: 110,
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
            loadGrid(fields, './completeASMList.html?'+$("#searchForm").serialize(), 'asm',
				columns, 220, 835, 'admin-asm-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './asm.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-asm-grid").html('');
		loadASMGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit ASM Page 										
	/********************************************************************************************************************/
	
	$("#rsmId").bind('change', function () {	
		loadAreaList($(this).val());
	});
	
	function loadAreaList(rsmId){
		 $("#areaId").attr("disabled", "disabled");
	     $("#areaId option").remove();
	     $("#areaId").append("<option value=''></option>");
	     $.getJSON("./areaListByRSM.html?rsm_id=" + rsmId, function (json) {
	          $.each(json.results, function (i, area) {
	              $("#areaId").append("<option value='" + area.id + "'>" + area.name + "</option>");
	          });
	     });
         $("#areaId").removeAttr("disabled");
	}       
	        
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
		if ($("#areaId").val() == '') {			
            flag = addError("#areaId", '');        
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
