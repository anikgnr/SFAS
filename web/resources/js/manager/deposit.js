$(function () {
	/********************************************************************************************************************
	 *										For Depo Deposit List Page 										
	/********************************************************************************************************************/
	if ($("#depositFromDate").length > 0) {
		   $("#depositFromDate").datepicker();
	}
	if ($("#depositToDate").length > 0) {
	   $("#depositToDate").datepicker();
	}

	if($("#admin-depo-deposit-grid").length > 0)
		loadDepositGrid();

	function loadDepositGrid() {
		if ($("#admin-depo-deposit-grid").html() == "") {
			 fields = ['depo.fullName', 'account.bankName', 'account.bankAccount', 'depositAmount', 'depositDate', 'approveLink'];
        	 columns = [
                        {
                            text: '',
                            width: 25,
                            dataIndex: ''
                        },
                        {
                            text: 'DEPO',
                            width: 170,
                            dataIndex: 'depo.fullName'                       
                        },
                        {
                            text: 'Bank Name',
                            width: 200,
                            dataIndex: 'account.bankName'                       
                        },
                        {
                            text: 'Account Number',
                            width: 150,
                            dataIndex: 'account.bankAccount'                       
                        },
                        {
                            text: 'Deposit Amount',
                            width: 150,
                            dataIndex: 'depositAmount',
                            align: "center",
                            renderer: function (value) { return value+" Tk"; }
                        },                   
                        {
                            text: 'Deposit Date',
                            width: 120,
                            dataIndex: 'depositDate',
                            renderer: Ext.util.Format.dateRenderer('m/d/Y')
                        },
                        {
                            text: '',
                            width: 60,
                            dataIndex: 'approveLink'
                        }                           
                    ];            
            loadGrid(fields, './depoCompleteDepositList.html?'+$("#searchForm").serialize(), 'deposit',
				columns, 440, 900, 'admin-depo-deposit-grid');

        }

    }
	
	$("#depositSearchBtn").click(function(){
		$("#admin-depo-deposit-grid").html('');
		loadDepositGrid();
	});
	
});

function doApprove(url){
	
	if(confirm("Are you sure ?")){
		window.location = url;
	}
}