$(function () {
	
	/********************************************************************************************************************
	 *										For Route List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-route-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-route-grid").html() == "") {
        	fields = ['name', 'territory.name', 'territory.area.name', 'territory.area.region.name', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Route Name',
                       width: 200,
                       dataIndex: 'name'                       
                   },
                   {
                       text: 'Territory Name',
                       width: 165,
                       dataIndex: 'territory.name'                       
                   },                   
                   {
                       text: 'Area Name',
                       width: 150,
                       dataIndex: 'territory.area.name'
                   },
                   {
                       text: 'Region Name',
                       width: 150,
                       dataIndex: 'territory.area.region.name'
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
            loadGrid(fields, './completeRouteList.html?'+$("#searchForm").serialize(), 'route',
				columns, 420, 835, 'admin-route-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './route.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-route-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Route Page 										
	/********************************************************************************************************************/
	$("#regionId").bind('change', function () {
		 $("#areaId").attr("disabled", "disabled");
	     $("#areaId option").remove();
	     $("#areaId").append("<option value=''></option>");
	     $.getJSON("./areaListByRegionId.html?region_id=" + $(this).val(), function (json) {
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
	     $.getJSON("./territoriesListByAreaId.html?area_id=" + $(this).val(), function (json) {
	          $.each(json.results, function (i, territory) {
	              $("#territoryId").append("<option value='" + territory.id + "'>" + territory.name + "</option>");
	          });
	     });
       $("#territoryId").removeAttr("disabled");	
       $("#territoryId").trigger('change');
	});
	
	        
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#name").val() == '') {
            flag = addError("#name", '');
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
		
		if(flag)
			return false;
	});
});
