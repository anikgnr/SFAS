$(function () {
	
	if ($("#orderDate").length > 0) {
	   $("#orderDate").datepicker({ maxDate: new Date() });
	}
	
	$(".qty").change(function() {
		idx = $(this).attr('id').split('-')[1];
		amount = $("#qty-"+idx).val() * $("#rate-"+idx).val();
		totalAmount = $("#orderAmount").val() - $("#amount-"+idx).val() + amount;
		$("#amount-"+idx).val(amount);
		$("#orderAmount").val(totalAmount);
		$("#amountDiv-"+idx).html(amount);
		$("#totalAmount").html(totalAmount);		
	});
	
	$("#submitDepoOrder").click(function(){
		flag = false;
		clearForm();
		message = "";
		
		if ($("#orderDate").val() == '' || !isValidDate($("#orderDate").val())) {
            flag = true;
            $("#orderDate").parent().css('background','#ED8080');
            message += "* Please provide a valid Order Date.<br/>";
        }
		
		if($("#orderAmount").val() == "0.0" || $("#orderAmount").val() == "0"){
			flag = true;
			$("#totalAmount").css('background','#ED8080');
			message += "* Please add some item on your Order.<br/>";
			
		}else if($("#orderAmount").val() * 1.0 > $("#currentBalance").val()){
			flag = true;
			$("#totalAmount").css('background','#ED8080');
			message += "* Payable Amount exceeds Current Balance. Please refactor your Order.<br/>";
		}
		
		if(flag){
			$("#orderErrorBlock").html(message);
			$("#orderErrorBlock").show();
			return false;
		}
	});
	
	function clearForm(){
		$("#orderErrorBlock").hide();
		$("#orderErrorBlock").html("");
		$("#orderDate").parent().css('background','white');
		$("#totalAmount").css('background','white');
	}
});

