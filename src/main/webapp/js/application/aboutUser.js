/**
 * 
 */
var userDataSource = new kendo.data.DataSource({
    transport: {
        create: { 
            //url: "<s:url action='user_create'/>",
        	url: "user_create",
        	type:"POST"
        },
        destroy: { 
            //url: "<s:url action='user_destroy'/>",
        	url: "user_destroy",
        	type:"POST"
        },
        update: { 
            //url: "<s:url action='user_update'/>",
            url: "user_update",
        	type:"POST"
        },
        read: {
            //url: "<s:url action='user_readAll'/>",
            url: "user_readAll",
        	dataType: "json",
        	type:"POST"
        },
        parameterMap: function(options, operation) {
            if ((operation === "destroy" || operation === "update") && options.models) {
                return {models: kendo.stringify(options.models)};
            }else if(operation === "create" && options.models){
            	//找來找去找步道jackson deserialize怎麼忽略null欄位只好在這裡處理
            	//console.log("do create param");
            	var param = options.models;
            	for(var i=0;i<param.length;i++){
            		delete param[i].id;
            		//console.log("delete id");
            	}
            	return {models: kendo.stringify(param)};
            }
        }
    },
    batch: true,
    schema: {
    	parse: function(response) {
            var parsed = $.parseJSON(response); //要先parse
    		return parsed;
    	},
    	total: function(parsed){  //加tatol才會讓grid的pager work，且這裡帶進來的參數是parsed過的
    		return parsed.results.length;
    	},
    	data: "results",
        model: userModel
    },        
    pageSize: 5,
    
   /* requestStart: function() {
    	$('#user_grid_msg').empty();
    },*/
    requestEnd: function(e) {
    	//因為使用function方式執行CRUD會有錯誤(沒解)，所以只能在這裡處理server錯誤訊息	
    	var msgTargetDomId = "user_grid_msg";
   		if (e.type !== undefined && e.type !== "read"){
   			var response = $.parseJSON(e.response);   			
   			if(response.results === undefined){
   				//清除batch action
   				$('#user_grid a.k-button.k-button-icontext.k-grid-cancel-changes').first().trigger('click');    				
   				displayWarningMsg(msgTargetDomId, response.errors);
   			}else{
   				displaySuccessMsg(msgTargetDomId, "您的操作已成功執行!");
   			}
   			
   		}
           
	}
});
var userGrid = $("#user_grid").kendoGrid({
    dataSource: userDataSource,
    height: 300,
    filterable: true,
    navigatable: true, //enable keyboard navigation
    sortable: true,
    pageable:  {
    	//page: 1,
    	pageSize: 5,
    	//refresh: true,
    	messages: {
    	      empty: "No data",
    	      display: "Showing {0}-{1} from {2} data items"
    	}
    },
    //editable: "popup",  //batch mode不能用popup
    editable: true,
    resizable: true,
    toolbar:["create", "save", "cancel"],
    columns: [
        {
        	command:["destroy"],
        	width: "120px"
        },
        {
            field:"id",
            filterable: false,
            width: "40px"
        },
        {
            field: "userName",
            title: "使用者名稱",
            width: "150px"
        }, {
            field: "apId",
            title: "系統代碼",
            width: "100px"
        }, {
            field: "enabled",
            title: "啟用狀態",
            width: "100px"
        },{
            field: "email",
            title: "電子郵件",
            width: "250px"
        },{
            field: "password",
            title: "使用者密碼(新增及修改用)",
            filterable: false,
            width: "150px"
        }
    ]
});
var userUrbViewModel = kendo.observable({
	userDataSource: userDataSource,
	selectedUsers: [],
    selectedUser: "",
    onSingleSelectChange: function(){
    	//console.log("in onSingleSelectChange()");
    	//$("#user_role_bind").addClass("ui dimmer");
    	//$("#dimmer").addClass("active");
		var userSingleSelectData = userSingleSelect.dataItem();
		var msgTargetDomId = "user_role_bind_msg";
		$.ajax({
			url: "user_queryRoles",
			type: "POST",
			dataType: "json",
			data: {
				models: kendo.stringify({user: userSingleSelectData})
			}
		})
		.done( function(response){
			var parsed = $.parseJSON(response);
			if(parsed.results === undefined){
   				displayWarningMsg(msgTargetDomId, response.errors);
   			}else{      				
   				//var oriDIs = roleUrbViewModel.get("selectedRoles1");
   				//var temp = $.merge($.merge([], oriDIs), parsed.results);
                //temp = $.unique(temp);
                roleUrbViewModel.set("selectedRoles1",parsed.results);
   				roleMultiSelect1.refresh();
   			}
		})
		.fail( function(){
			displayFailMsg(msgTargetDomId, "與server的溝通似乎出了問題...");
		});    	
    }
    
});

var userMultiSelect = $("#select_users").kendoMultiSelect().data("kendoMultiSelect");
var userSingleSelect = $("#select_user").kendoComboBox().data("kendoComboBox");

$(document).ready(function(){
		
	kendo.bind($("#select_users"), userUrbViewModel);
	kendo.bind($("#select_user"), userUrbViewModel);
//	kendo.bind($("#user_role_pusher"), userUrbViewModel);  //一個DOM只能bind一個vm，所以不能bind太大範圍
});
