$(function () {
	
	/********************************************************************************************************************
	 *										For Outlet List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-outlet-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-outlet-grid").html() == "") {
        	fields = ['pointName', 'address', 'distributor.tso.territory.name', 'distributor.pointName', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Outlet Name',
                       width: 180,
                       dataIndex: 'pointName'                       
                   },                   
                   {
                       text: 'Address',
                       width: 170,
                       dataIndex: 'address'
                   },
                   {
                      text: 'Territory',
                      width: 170,
                      dataIndex: 'distributor.tso.territory.name'
                  },
                  {
                      text: 'Distributor',
                      width: 170,
                      dataIndex: 'distributor.pointName'
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
            loadGrid(fields, './completeOutletList.html?'+$("#searchForm").serialize(), 'outlet',
				columns, 220, 835, 'admin-outlet-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './outlet.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-outlet-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Outlet Page 										
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
		 $("#territoryId").attr("disabled", "disabled");
	     $("#territoryId option").remove();
	     $("#territoryId").append("<option value=''></option>");
	     $.getJSON("./territoryListByArea.html?area_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, territory) {	              
	              $("#territoryId").append("<option value='" + territory.id + "'>" + territory.name +"</option>");
	          });
	     });
         $("#territoryId").removeAttr("disabled");
         $("#territoryId").trigger('change');
	});

	$("#territoryId").bind('change', function () {	
		 $("#distributorId").attr("disabled", "disabled");
	     $("#distributorId option").remove();
	     $("#distributorId").append("<option value=''></option>");
	     $.getJSON("./distributorListByTerritory.html?territory_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, distributor) {	              
	              $("#distributorId").append("<option value='" + distributor.id + "'>" + distributor.pointName +"</option>");
	          });
	     });
        $("#distributorId").removeAttr("disabled");
        
        $("#routeId").attr("disabled", "disabled");
	     $("#routeId option").remove();
	     $("#routeId").append("<option value=''></option>");
	     $.getJSON("./routeListByTerritory.html?territory_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, route) {	              
	              $("#routeId").append("<option value='" + route.id + "'>" + route.name +"</option>");
	          });
	     });
       $("#routeId").removeAttr("disabled");
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
		if ($("#regionId").val() == '') {			
            flag = addError("#regionId", '');        
        }
		if ($("#areaId").val() == '') {			
            flag = addError("#areaId", '');        
        }
		if ($("#territoryId").val() == '') {			
            flag = addError("#territoryId", '');        
        }
		if ($("#routeId").val() == '') {			
            flag = addError("#routeId", '');        
        }
		if ($("#distributorId").val() == '') {			
            flag = addError("#distributorId", '');        
        }
		
		if(flag)
			return false;
	});
});
