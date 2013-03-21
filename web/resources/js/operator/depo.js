$(function () {
	
	/********************************************************************************************************************
	 *										For DEPO List Page 										
	/********************************************************************************************************************/
	
	if($("#admin-depo-grid").length > 0)
		loadDEPOGrid();

	function loadDEPOGrid() {

        if ($("#admin-depo-grid").html() == "") {
        	fields = ['name', 'address', 'rsm.region.name', 'rsm.name', 'ordersLink', 'orderLink', 'damageLink', 'planLink'];
            columns = [
                   {
                       text: '',
                       width: 15,
                       dataIndex: ''
                   },
                   {
                       text: 'DEPO Name',
                       width: 130,
                       dataIndex: 'name'                       
                   },                   
                   {
                       text: 'DEPO Location',
                       width: 150,
                       dataIndex: 'address'
                   },                   
                  {
                      text: 'Region',
                      width: 120,
                      dataIndex: 'rsm.region.name'
                  },
                  {
                      text: 'RSM',
                      width: 120,
                      dataIndex: 'rsm.name'
                  },
                  {
                      text: '',
                      width: 60,
                      dataIndex: 'ordersLink'
                  },
                  {
                      text: '',
                      width: 80,
                      dataIndex: 'orderLink'
                  },
                  {
                      text: '',
                      width: 65,
                      dataIndex: 'damageLink'
                  },
                  {
                      text: '',
                      width: 80,
                      dataIndex: 'planLink'
                  }                  
               ];
            loadGrid(fields, './activeDepoList.html?'+$("#searchForm").serialize(), 'depo',
				columns, 420, 835, 'admin-depo-grid');

        }

    }
	
	$("#searchBtn").click(function(){
		$("#admin-depo-grid").html('');
		loadDEPOGrid();
	});
	
});
