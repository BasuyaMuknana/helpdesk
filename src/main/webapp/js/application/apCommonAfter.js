/**
 * 
 */
/*var generalValidationRule = {
	messages: {
        required: "此欄位必填"
    }
};
var userModel = kendo.data.Model.define({
	id: "id",
    fields: {
    	id:{editable: false, nullable: true},
    	userName: { type: "string" ,validation: { required:true }},
    	apId: { type: "string" ,validation: { required:true }},
    	enabled: { type: "number",validation: { min:"0", max:"1", required:true } },
    	email: { type: "string" },
        password: { type: "string" ,validation: { required:true } }  
    }
});
var roleModel = kendo.data.Model.define({
	id: "id",
    fields: {
    	id:{editable: false, nullable: true},
    	roleName: { type: "string" },
        roleDesc: { type: "string" }
    }
});
var logModel =  kendo.data.Model.define({
	id: "id",
    fields: {
    	id:{editable: false, nullable: true},
    	apId: { type: "string" },
    	createdTime: {type: "date" },
    	userId: { type: "string" },
    	userIp : { type: "string" },
    	contentId : { type: "number" },
    	description : {type: "string"}
    }
});
var contentModel = kendo.data.Model.define({
	id: "id",
    fields: {
    	id:{editable: false, nullable: true},
    	pageType: { type: "string" },
    	verNo: {type: "long" },
    	content: { type: "string", editable: false },
    	contentClob : { type: "string" }  
    }
});


function scrollToAnchor(aid){
    var aTag = $("#"+ aid);
    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
}*/

$(document).ready(function(){
	
	//left main menu
	$('.ui.vertical.inverted.sidebar.menu.left').first()
	  .sidebar('attach events', '.fixed.button','show')
	;

	//make all .ap_help popup tooltip 
	$('.ap_help')
	  .popup({
	    inline   : true,
	    hoverable: true,
	    position : 'bottom center',
	    delay: {
	      show: 300,
	      hide: 300
	    }
	});
	
	//enable all semantic tab enable,except those in modals
	$('.tabular.menu .item').tab();

	
	//make all form could use "enter" key to submit
	$(".ui.form input").keypress(function(event) {
		var keycode = (event.keyCode ? event.keyCode : event.which);
		if (keycode == 13) {
			event.preventDefault();
			$(this).closest("form").find('input[type=submit]').trigger('click');
		}
	});
	
	$("input.k-input.ui.dropdown").removeClass("k-input");
	
	
});
