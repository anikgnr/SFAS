$(function () {
	
	/********************************************************************************************************************
	 *										For Outlet List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-outlet-grid").length > 0)
		loadDEPOGrid();

	function loadDEPOGrid() {

        if ($("#admin-outlet-grid").html() == "") {
        	fields = ['pointName', 'distributor.fullName', 'route.name', 'route.territory.area.name' ];
            columns = [
                   {
                       text: '',
                       width: 15,
                       dataIndex: ''
                   },
                   {
                       text: 'Outlet Name',
                       width: 160,
                       dataIndex: 'pointName'                       
                   },
                   {
                       text: 'Distributor (Territory)',
                       width: 180,
                       dataIndex: 'distributor.fullName'                       
                   },
                   {
                       text: 'Route',
                       width: 100,
                       dataIndex: 'route.name'                       
                   },
                   {
                       text: 'Area',
                       width: 100,
                       dataIndex: 'route.territory.area.name'                       
                   }
                 ];
            loadGrid(fields, './activeOutletList.html?'+$("#searchForm").serialize(), 'outlet',
				columns, 420, 900, 'admin-outlet-grid');

        }

    }
	
	$("#searchBtn").click(function(){
		$("#admin-outlet-grid").html('');
		loadDEPOGrid();
	});
	

});
