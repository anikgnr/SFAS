$(function () {
	
	/********************************************************************************************************************
	 *										For Distributor List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-distributor-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-distributor-grid").html() == "") {
        	fields = ['pointName', 'address', 'tso.name', 'tso.territory.name', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Point Name',
                       width: 180,
                       dataIndex: 'pointName'                       
                   },                   
                   {
                       text: 'Address',
                       width: 170,
                       dataIndex: 'address'
                   },
                   {
                      text: 'TSO',
                      width: 170,
                      dataIndex: 'tso.name'
                  },
                  {
                      text: 'Territory',
                      width: 170,
                      dataIndex: 'tso.territory.name'
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
            loadGrid(fields, './completeDistributorList.html?'+$("#searchForm").serialize(), 'distributor',
				columns, 220, 835, 'admin-distributor-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './distributor.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-distributor-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Distributor Page 										
	/********************************************************************************************************************/
	
	$("#regionId").bind('change', function () {
		 $("#areaId").attr("disabled", "disabled");
	     $("#areaId option").remove();
	     $("#areaId").append("<option value=''></option>");
	     $.getJSON("./areaListByRegion.html?region_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, area) {
	        	  $("#areaId").append("<option value='" + area.id + "'>" + area.name + "</option>");
	          });
	     });
        $("#areaId").removeAttr("disabled");	
        $("#areaId").trigger('change');
	});
	
	$("#areaId").bind('change', function () {	
		 $("#tsoId").attr("disabled", "disabled");
	     $("#tsoId option").remove();
	     $("#tsoId").append("<option value=''></option>");
	     $.getJSON("./tsoListByArea.html?area_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, tso) {	              
	              $("#tsoId").append("<option value='" + tso.id + "'>" + tso.firstName +" "+tso.lastName+"-("+tso.territory.name +")</option>");
	          });
	     });
         $("#tsoId").removeAttr("disabled");	
	});
	
	        
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#pointName").val() == '') {
            flag = addError("#pointName", '');
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
		if ($("#regionId").val() == '') {			
            flag = addError("#regionId", '');        
        }
		if ($("#areaId").val() == '') {			
            flag = addError("#areaId", '');        
        }
		if ($("#tsoId").val() == '') {			
            flag = addError("#tsoId", '');        
        }
		
		if(flag)
			return false;
	});
});
