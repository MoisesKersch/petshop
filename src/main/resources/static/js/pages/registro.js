function send()
{
	if ($("#registrationForm").valid()) 
	{
		$('#registrationForm').submit();
	}
}

$("#registrationForm").validate({
	rules : {
		senha : "required",
		passwordConfirm : {
			equalTo : "#senha"
		},
		cpf : {
			required : true,
			remote : {
				url : "public/iscpfcnpjvalido",
				type : "POST",
				data : {
					"entrada" : function() {
						return $("#cpf").val()
					}
				},
				dataFilter : function(response)
				{
					var response = jQuery.parseJSON(response);
					currentMessage = response.Message;
					
					if (response) {
						return true;
					}
					
					return false;
				}
			}
		},
		login : {
			required : true,
			remote : {
				url : "public/isusuarioexiste",
				type : "POST",
				data : {
					"entrada" : function() {
						return $("#login").val()
					}
				},
				dataFilter : function(response)
				{
					var response = jQuery.parseJSON(response);
					currentMessage = response.Message;
					
					if (response) {
						return false;
					}
					return true;
				}
			}
		}
	},
	
	messages: {
		login: "Usuário já existente no sistema!", 
		cpf: "CPF inválido!"
    },
    
	errorElement : "div",
	errorPlacement : function(error, element) {
		var er = error.insertAfter(element.next());

		if (er == null)
			er.insertAfter(element.next());

	}
});