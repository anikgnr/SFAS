$(function () {
	
	/********************************************************************************************************************
	 *										For Add/Edit Stock In Page 										
	/********************************************************************************************************************/
	
	if ($("#manufactureDate").length > 0) {
	   $("#manufactureDate").datepicker({ maxDate: new Date() });
	}
	if ($("#expireDate").length > 0) {
	   $("#expireDate").datepicker({ minDate: new Date() });
	}
	$("#saveBtn").click(function(){
		clearErrors();
		flag = false;
		
		if ($("#productId").val() == '') {
            flag = addError("#productId", '');
        }
		if ($("#manufactureDate").val() == '') {
            flag = addError("#manufactureDate", '');
        }else if (!isValidDate($("#manufactureDate").val())) {
            flag = addError("#manufactureDate", 'Invalid Manufacture Date');            
        }
		if ($("#expireDate").val() == '') {
            flag = addError("#expireDate", '');
        }else if (!isValidDate($("#expireDate").val())) {
            flag = addError("#expireDate", 'Invalid Expire Date');            
        }
		
		if ($("#quantity").val() == '' || $("#quantity").val() == '0') {			
            flag = addError("#quantity", '');        
		}else if (isNotNumber($("#quantity").val())) {
        	flag = addError("#quantity", 'Invalid Quantity !!');      
        }
		
		if(flag)
			return false;
	});
});
