/**
 * 
 */
var generalValidationRule = {
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
    	content: { type: "string"},
    	contentClob : { type: "string" }  
    }
});


function scrollToAnchor(aid){
    var aTag = $("#"+ aid);
    $('html,body').animate({scrollTop: aTag.offset().top},'slow');
}

function displaySuccessMsg(id, msg){
	var target = $('#'+id).html(
		'<div class="ui icon positive message">'+
			'<i class="checkmark icon"></i>'+
			'<div class="content">'+
				'<h3>'+ msg +'</h3>'+
			'</div>'+
		'</div>'			
	).fadeOut(3000, function(){ $(this).empty(); $(this).show(); });
}

function displayWarningMsg(id, msg){
	var target = $('#'+id).html(
		'<div class="ui icon warning message">'+
			'<i class="warning icon"></i>'+
			'<div class="content">'+
				'<h3>'+ msg +'</h3>'+
			'</div>'+
		'</div>'			
	).fadeOut(5000, function(){ $(this).empty(); $(this).show(); });
}

function displayFailMsg(id, msg){
	var target = $('#'+id).html(
		'<div class="ui icon negative message">'+
			'<i class="remove icon"></i>'+
			'<div class="content">'+
				'<h3>'+ msg +'</h3>'+
			'</div>'+
		'</div>'			
	).fadeOut(5000, function(){ $(this).empty(); $(this).show(); });
}


