/**
 * 
 */

var pageTypeSingleSelect = $("#page_type_select").kendoComboBox().data("kendoComboBox");

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
    },
    change: function(){
    	//console.log("response:"+ response);
        //console.log("parsed:"+ parsed);
    	var datas = this.data();
        console.log("datas:"+ datas);
        var i;
        for(i=0; i<datas.length; i++){
        	datas[i].pageType = datas[i].pageType+"-"+datas[i].verNo;
        }
    },
    sort: { field: "pageType", dir: "asc" }
});

var logDataSource = new kendo.data.DataSource({
    transport: {
        read: {
            url: "log_readAll",
        	dataType: "json",
        	type: "GET",
        }
    },
    schema: {
    	parse: function(response) {
            var parsed = $.parseJSON(response); //要先parse
    		return parsed;
    	},
    	total: function(parsed){  //加tatol才會讓grid的pager work，且這裡帶進來的參數是parsed過的
    		return parsed.results.length;
    	},
    	data: "results",
        model: logModel
    },      
    pageSize: 20
});
var logGrid = $("#simple_query_grid").kendoGrid({
    dataSource: logDataSource,
    height: 350,
    filterable: true,
    navigatable: true, //enable keyboard navigation
    sortable: true,
    pageable: {
    	//page: 1,
    	pageSize: 20,
    	//refresh: true,
    	messages: {
    	      empty: "No data",
    	      display: "Showing {0}-{1} from {2} data items"
    	}
    },
    //editable: "popup",  //batch mode不能用popup
    editable: false,
    resizable: true,
    //toolbar:["create", "save", "cancel"],
    autoBind: false,
    columns: [
        {
            field:"id",
            filterable: false,
            width: "40px"
        },
        {
            field: "apId",
            title: "系統代碼",
            width: "100px"
        }, {
            field: "userId",
            title: "使用者帳號",
            width: "100px"
        }, {
            field: "createdTime",
            title: "建立時間",
            width: "150px"
        },{
            field: "userIp",
            title: "使用者IP",
            width: "150px"
        }
    ]
}).data('kendoGrid');

var logViewModel = kendo.observable({
	selectedPageType: null,
	contentSelectDataSource: contentSelectDataSource,
	onPageTypeSelectChange: function(){
		console.log("pageType: "+this.get("selectedPageType").pageType+" is selected!");
		//動態帶入查詢條件
		logDataSource.read({filterField: "content_id",
							filterValue: logViewModel.get("selectedPageType").id});
		//logDataSource.page(1);
		//logGrid.setDataSource(logDataSource);
		logGrid.refresh();
		this.selectedPageType = null;
	}
});


$(document).ready(function(){
	kendo.bind($("#log_pusher"), logViewModel);

});
