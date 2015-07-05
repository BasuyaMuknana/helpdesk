/**
 * 
 */
var roleDataSource = new kendo.data.DataSource({
    transport: {
        create: { 
        	url: "role_create",
        	type:"POST"
        },
        destroy: { 
        	url: "role_destroy",
        	type:"POST"
        },
        update: { 
        	url: "role_update",
        	type:"POST"
        },
        read: {
        	url: "role_readAll",
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
    		//alert(response);
    		//console.log("ori resp:"+response);
            var parsed = $.parseJSON(response); //因struts json plugin回傳會加上跳脫符號所以先parse
    		//console.log("parsed resp:"+parsed);
    		return parsed;
    	},
    	total: function(parsed){  //加tatol才會讓grid的pager work，且這裡帶進來的參數是parsed過的
    		return parsed.results.length;
    	},
    	data: "results",
        model: roleModel
    },        
    pageSize: 20,
    
    //sync:function(e){}
    /*requestStart: function() {
    	$('#role_grid_msg').empty();
    },*/
    requestEnd: function(e) {
    	//因為使用function方式執行CRUD會有錯誤(沒解)，所以只能在這裡處理server錯誤訊息	
    	var msgTargetDomId = "role_grid_msg";
   		if (e.type !== undefined && e.type !== "read"){
   			var response = $.parseJSON(e.response);
   			if(response.results === undefined){
   				//清除batch action
   				$('#role_grid a.k-button.k-button-icontext.k-grid-cancel-changes').first().trigger('click');   				
   				displayWarningMsg(msgTargetDomId, response.errors);
   			}else{  				
   				displaySuccessMsg(msgTargetDomId, "您的操作已成功執行!");
   			}
   			
   		}
           
	}
    //change: function() {
    	//console.log("schema.data="+roleDataSource.schema);
        // $("#products").html(kendo.render(template, this.view()));
    //},
    
});
var roleGrid = $("#role_grid").kendoGrid({
    dataSource: roleDataSource,
    height: 300,
    filterable: true,
    navigatable: true, //enable keyboard navigation
    sortable: true,
    pageable:  {
    	//page: 1,
    	pageSize: 20,
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
            field: "roleName",
            title: "角色名稱",
            width: "200px"
        }, {
            field: "roleDesc",
            title: "角色描述"
        }
    ]
});
var roleUrbViewModel = kendo.observable({
	roleDataSource: roleDataSource,
	selectedRoles1: [],
    selectedRoles2: []
    
});
var roleMultiSelect1 = $("#select_roles_1").kendoMultiSelect().data("kendoMultiSelect");
var roleMultiSelect2 = $("#select_roles_2").kendoMultiSelect().data("kendoMultiSelect");

$(document).ready(function(){

	
	kendo.bind($("#select_roles_1"), roleUrbViewModel);
	kendo.bind($("#select_roles_2"), roleUrbViewModel);	
//	kendo.bind($("#user_role_pusher"), roleUrbViewModel); //一個DOM只能bind一個vm，所以不能bind太大範圍
	
	
});
