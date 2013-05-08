$(function () {
	
	/********************************************************************************************************************
	 *										For Territory List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-territory-grid").length > 0)
		loadEntityGrid();

	function loadEntityGrid() {

        if ($("#admin-territory-grid").html() == "") {
        	fields = ['id', 'name', 'area.name', 'area.region.name', 'editLink', 'deleteLink'];
            columns = [
                   {
                       text: '',
                       width: 25,
                       dataIndex: ''
                   },
                   {
                       text: 'Territory ID',
                       width: 80,
                       dataIndex: 'id',
                       align: 'center'
                   },
                   {
                       text: 'Territory Name',
                       width: 250,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'Area Name',
                       width: 170,
                       dataIndex: 'area.name'
                   },
                   {
                       text: 'Region Name',
                       width: 160,
                       dataIndex: 'area.region.name'
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
            loadGrid(fields, './completeTerritoryList.html?'+$("#searchForm").serialize(), 'territory',
				columns, 420, 830, 'admin-territory-grid');

        }

    }
	
	$("#createNew").click(function(){
		window.location = './territory.html';
	});
	
	$("#searchBtn").click(function(){
		$("#admin-territory-grid").html('');
		loadEntityGrid();
	});
	
	/********************************************************************************************************************
	 *										For Add/Edit Territory Page 										
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
		
		if(flag)
			return false;
	});
});
