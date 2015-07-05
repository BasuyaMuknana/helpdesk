/**
 * 
 */
var contentDataSource = new kendo.data.DataSource({
    transport: {
        create: { 
        	url: "content_create",
        	dataType: "json",
        	type:"POST"
        },
        destroy: {
        	url: "content_destroy",
        	type:"POST"
        },
        update: {
            url: "content_update",
            type:"GET"
        },
        read: {
            url: "content_readAll",
        	dataType: "json",
        	type:"GET"
        },
        parameterMap: function(options, operation) {
        	console.log("options.models:" + options.models);
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
    batch: true,  //告知內容不用批次作業，但是官方文件說要在parameterMap中看到Models就必須加上此選項!!!
    schema: {
    	parse: function(response) {
            var parsed = $.parseJSON(response); //要先parse
    		return parsed;
    	},
    	total: function(parsed){  //加tatol才會讓grid的pager work，且這裡帶進來的參數是parsed過的
    		return parsed.results.length;
    	},
    	data: "results",
        model: contentModel
    },      
    pageSize: 20,
    
   /* requestStart: function() {
    	$('#content_grid_msg').empty();
    },*/
    requestEnd: function(e) {
    	//因為使用function方式執行CRUD會有錯誤(沒解)，所以只能在這裡處理server錯誤訊息
    	var msgTargetDomId = "content_grid_msg";
   		if (e.type !== undefined && e.type !== "read"){
   			
   			var response = $.parseJSON(e.response);
   			
   			if(response.results === undefined){
   				displayWarningMsg(msgTargetDomId, response.errors);			
   			}else{
   				displaySuccessMsg(msgTargetDomId, "您的操作已成功執行!");
   			}
   			
   		}
   		
	}
});
var contentGrid = $("#content_grid").kendoGrid({
    dataSource: contentDataSource,
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
    editable: "popup",  //batch mode不能用popup
    //editable: true,
    resizable: true,
    //toolbar:["create"],
    columns: [
		{
			command: { text: "預覽內容", click: showContents }, 
			title: " ", 
			width: "55px"
		}, 
		{
			command: { text: "載入內容在下方編輯", click: loadAndEditContents }, 
			title: " ", 
			width: "90px"
		},
        {
        	command:["destroy"],
        	width: "60px"
        },
        {
            field:"id",
            filterable: false,
            width: "100px"
        },
        {
            field: "pageType",
            title: "內容種類",
            width: "100px"
        }, 
        {
            field: "verNo",
            title: "版號",
            width: "100px"
        }
    ]
});
var contentViewModel = kendo.observable({
	editor_id: null,
	editor_pageType: null,
	editor_verNo: null,
	rawHtmlValue: null,
	zhtwEditorValue: null,
	enEditorValue: null,
	zhcnEditorValue: null,
    
    isVisible: true,
    wrapLangTagForCreate: function(){
    	var tmpZhtw="", tmpEn="", tmpZhcn="";
    	tmpZhtw = tmpZhtw.concat("<p lang=\"zh-tw\">",this.get("zhtwEditorValue"),"</p>");
    	this.set("zhtwEditorValue",tmpZhtw);
    	tmpEn = tmpEn.concat("<p lang=\"en\">",this.get("enEditorValue"),"</p>");
    	this.set("enEditorValue",tmpEn);
    	tmpZhcn = tmpZhcn.concat("<p lang=\"zh-cn\">",this.get("zhcnEditorValue"),"</p>");
    	this.set("zhcnEditorValue",tmpZhcn);
    },
    combineRawHtml: function(){
    	var tmpZhtw="", tmpEn="", tmpZhcn="", tmpHtml="";
    	tmpZhtw = this.get("zhtwEditorValue"); console.log("tmpZhtw:"+tmpZhtw);
    	tmpEn = this.get("enEditorValue"); console.log("tmpEn:"+tmpEn);
    	tmpZhcn = this.get("zhcnEditorValue"); console.log("tmpZhcn:"+tmpZhcn);
    	tmpHtml = tmpZhtw.concat("|",tmpEn,"|",tmpZhcn);
    	
    	this.set("rawHtmlValue",tmpHtml);
    },
    onChange: function() {
        //console.log("event :: change (" + kendo.htmlEncode(this.get("html")) + ")");
    	//this.set("rawHtmlValue",zhtwEditorValue+"|"+enEditorValue+"|"+zhcnEditorValue);
    	//this.combineRawHtml();
    },
    clearEditor: function(){
    	//console.log("clearEditor");
    	this.set("editor_id",null);
    	this.set("editor_pageType",null);
    	this.set("editor_verNo",0);
    	this.set("zhtwEditorValue",null);
    	this.set("enEditorValue",null);
    	this.set("zhcnEditorValue",null);
    	this.set("rawHtmlValue",null);
    },
    sendModify: function(){
    	//這裡的做法是直接更新datasource的資料(設定dirty)並call sync()，
    	//如此可透過datasource原本定義好的create與update進行變更
    	var target, newData={};
    	//先取得結合的結果
    	if (contentEditorValidator.validate()) {
        	if(this.get("editor_id") === null){  //case of create
        		this.wrapLangTagForCreate();
        		this.combineRawHtml();
        		//console.log("call datasouce create");
        		newData.pageType = this.get("editor_pageType");
        		newData.verNo = this.get("editor_verNo");
        		//newData.content = null;
        		newData.contentClob = this.get("rawHtmlValue");
        		contentDataSource.add(newData);
        	}else{  //case of update
        		//console.log("call datasouce update"); 
        		this.combineRawHtml();
            	//用id取得要變更的那筆資料
            	target =  contentDataSource.get(this.get("editor_id"));            	
            	//設定變更值給target

            	target.set("pageType",this.get("editor_pageType"));
            	target.set("verNo",this.get("editor_verNo"));
            	var oldApi = isOldApi(target);
            	if(oldApi){
            		target.set("content",this.get("rawHtmlValue"));
            	}else{
            		target.set("contentClob",this.get("rawHtmlValue"));
            	}
            	target.dirty = true;
        	}
        	//進行變更，滾回清單畫面，清除編輯區資料
        	//console.log("contentDataSource.hasChanges():"+contentDataSource.hasChanges());
        	contentDataSource.sync();
        	scrollToAnchor("content_grid_div");
        	this.clearEditor();
    	} else {
    		console.log("some input is wrong!!");
    	}
    	
    }
});

var contentPreviewTemplate;

var contentPreviewWindow = $("#content_preview_window")
.kendoWindow({
    title: "告知內容預覽",
    modal: true, //設true dimmer會關不掉
    visible: false,
    resizable: false,
    width: "85%",
    height: 500,
    open: function() {
    	$('.tabular.menu .item').tab();
    	//disable the window scrollbar
    	//$('.k-window  div#show_contents_window.k-window-content').css("overflow","hidden");
    },
    close: function(e) {
        $(".k-overlay").removeClass("k-overlay");
    }
}).data("kendoWindow");

var contentEditorValidator = $("#content_edit").kendoValidator(generalValidationRule).data("kendoValidator");

function splitContent(combinedHtmlString){
	if (typeof String.prototype.startsWith != 'function') {
	   // see below for better implementation!
	   String.prototype.startsWith = function (str){
	     return this.indexOf(str) == 0;
	   };
	 }
	//console.log("contents:"+ combinedHtmlString);
       var contents = combinedHtmlString.split("|");
       var rtn={}, index;
       for	(index = 0; index < contents.length; index++) {
    	   if(contents[index].startsWith("<p lang=\"en\">")){
    		   rtn.en = contents[index];
    		   //console.log("en:"+rtn.en);
    	   }else if (contents[index].startsWith("<p lang=\"zh-cn\">")){
    		   rtn.zhcn = contents[index];
    		   //console.log("zhcn:"+rtn.zhcn);
    	   }else{
    		   rtn.zhtw = contents[index];
    		   //console.log("zhtw:"+rtn.zhtw);
    	   }
       }
       return rtn;
}

function isOldApi(dataItem){
	if(dataItem.contentClob === null){
		return true;
	}else{
		return false;
	}
}

function showContents(e) {
   e.preventDefault();
   var showContent, oldApi = false;
   var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
   
   oldApi = isOldApi(dataItem);
   if(oldApi){
	   showContent = dataItem.content;   
	   //console.log("Old Api content");
   }else{
	   showContent = dataItem.contentClob;
	   //console.log("New Api content");
   }

   var rtn = splitContent(showContent);
   dataItem.en = rtn.en;
   dataItem.zhtw = rtn.zhtw;
   dataItem.zhcn = rtn.zhcn;
   contentPreviewWindow.content(contentPreviewTemplate(dataItem));
   if(oldApi){  //要先將template reder好後才能操作裡面的元素
	   $("#this_for_old_api").show();
   }else{
	   $("#this_for_old_api").hide();
   }
   contentPreviewWindow.center().open();
}

function loadAndEditContents(e) {
   e.preventDefault();
   var showContent, oldApi = false;
   var dataItem = this.dataItem($(e.currentTarget).closest("tr"));
   oldApi = isOldApi(dataItem);
   if(oldApi){
	   showContent = dataItem.content;   
	   //console.log("Old Api content");
   }else{
	   showContent = dataItem.contentClob;
	   //console.log("New Api content");
   }
   var rtn = splitContent(showContent);
   contentViewModel.set("editor_id",dataItem.id);
   contentViewModel.set("editor_pageType",dataItem.pageType);
   contentViewModel.set("editor_verNo",dataItem.verNo);
   //contentViewModel.set("rawHtmlEditorValue",dataItem.rawHtmlEditorValue);
   contentViewModel.set("zhtwEditorValue",rtn.zhtw);
   contentViewModel.set("enEditorValue",rtn.en);
   contentViewModel.set("zhcnEditorValue",rtn.zhcn);  
   scrollToAnchor("content_edit_div");
}

$(document).ready(function(){
	
	//template初始依定要放在ready里
	contentPreviewTemplate = kendo.template($("#content_preview_template").html());
		
    kendo.bind($("#content_pusher"), contentViewModel);

    
});
