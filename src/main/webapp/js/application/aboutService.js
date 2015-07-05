/**
 * 
 */
var contentSelectDataSource = new kendo.data.DataSource({
    transport: {
        read: {
            url: "content_readAll",
        	dataType: "json",
        	type:"POST"
        }
    },
    schema: {
    	parse: function(response) {
            var parsed = $.parseJSON(response); //要先parse
    		return parsed;
    	},
    	data: "results",
        model: contentModel
    }          
});
               
var contentSingleSelect = $("#content_select").kendoComboBox().data("kendoComboBox");
var verNoSingleSelect = $("#ver_no_select").kendoComboBox().data("kendoComboBox");
var envSingleSelect = $("#env_select").kendoComboBox().data("kendoComboBox");
var serviceViewModel = kendo.observable({
	targetIp: "localhost",             			
	targetPort: "8080",
	targetContext: "pdis-rest/rest/contents/serviceCheck",
	targetQueryUrl: "",
	selectedPageType: {},
	selectedVerNo: "",
	selectedEnv: "",
	queryResp:"",
	contentSelectDataSource: contentSelectDataSource,
	onEnvSelectChange: function(){
		this.changeTargetUrl();
	},
    onContentSelectChange: function(){
    	//kendo的cascade沒有用，只有硬幹了
    	console.log("in onContentSelectChange, selectedPageType: "+ this.get("selectedPageType").pageType);
		var dataSource = this.get("contentSelectDataSource").data();
		var targetPageType = this.get("selectedPageType").pageType;
		dataSource = jQuery.grep(dataSource, function( element, idx ) {
			//console.log("in onContentSelectChange, element.pageType: "+ element.pageType);
			//console.log("in onContentSelectChange, targetPageType: "+ targetPageType);
			return ( element.pageType === targetPageType);
		});
		verNoSingleSelect.setDataSource(dataSource);                   		
		verNoSingleSelect.enable(true);
		this.changeTargetUrl();
	},
	onVerNoSelectChange: function(){
		//console.log("in onVerNoSelectChange: selectedVerNo:"+ this.get("selectedVerNo"));
		this.changeTargetUrl();
	},
	changeTargetUrl: function(){
			var schema = "http://";
			//var ip = this.get("targetIp");
			var ip = this.get("selectedEnv");
			var camma = ":";
			var port = this.get("targetPort");
			var slash = "/"
			var context = this.get("targetContext");
			
			var temp ="".concat(schema,ip,camma,port,slash,context);
			this.set("targetQueryUrl",temp);
			//console.log("targetQueryUrl:"+ this.get("targetQueryUrl"));
	},
	sendTestQuery: function(){
		this.changeTargetUrl();
		$.ajax({                		    
		 	url: serviceViewModel.get("targetQueryUrl"),
			async: false,
		    jsonp: "callback",
		    dataType: "JSONP",                   
		    data: {
		    	pageType: serviceViewModel.get("selectedPageType").pageType || "PDIS",
		    	verNo: serviceViewModel.get("selectedVerNo") || 2
		        //pageType: queryPageType,
		        //verNo: queryVerNo
		    },
		    success: function( response ) {
		        console.log("success: "+ response.contentClob ); // server response
		        //queryResponse = response.contentClob;
		        serviceViewModel.set("queryResp",response.contentClob || response.content);
		    }
		});
	}
});

$(document).ready(function() {
	//envSingleSelect.select(1);
	//envSingleSelect.select(envSingleSelect.ul.children().eq(0));
	//envSingleSelect.suggest("驗證-10.89.8.160");
   	kendo.bind($("#service_pusher"), serviceViewModel);          
});
