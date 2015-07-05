<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML>
<html lang="zh-TW">
<head>

</head>
<body>
	<div class="ui inverted fixed top menu">
		<div class="header item">PDIS HELPDESK</div>	
					
		<div class="header item">
	    	<i class="browser icon"></i> 
	    	檢視記錄查詢
	  	</div>
	  	<a class="item" href="#simple_query_div">
	  		<i class="list icon"></i>
	  		簡易查詢
	  	</a>
	  	<a class="item" href="#complex_query_div">
	  		<i class="search icon"></i>
	  		複雜查詢	    	
	  	</a>
	  	
	</div>

	<div id="log_pusher" class="pusher">
		<div class="ui secondary green inverted segment" style="padding: 60px 130px;">			
			<div class="ui huge header" style="font-size:3em">檢視記錄查詢</div>
			<p class="ui header">歡迎進入檢視記錄查詢功能，您可以在這裡查詢使用者的檢視紀錄。</p>			
		</div>
		
		<div class="ui page grid" style="padding-top: 10px;">
			<div class="row">
				<div class="column">
					<div class="ui segment">
					    <div id="simple_query_div">
					    	<a class="ui large red right ribbon label ap_help"
								data-variation="huge very wide inverted"
								data-html='<ol>
											<li>點選"內容種類"下拉選單，系統會載入選項。</li>
											<li>選單中的選項為"內容種類-版號"，點選後會在下方的Grid顯示該內容的檢視紀錄。</li>
											<li>Grid中的資料僅供檢視，不能新增與修改。</li>
											<li>*請特別注意"建立時間"的內容值。由於資料庫中的資料被建立時沒有指定時區，所以java與資料庫皆以+0000來處理。但是瀏覽器卻會抓取電腦的時區，所以看到的時間內容會比實際的值少八小時。</li>
											</ol>'
							>
								<i class="large help circle icon" ></i>
								簡易查詢說明
							</a>
							<div class="ui huge header" style="font-size:2.5em">簡易查詢
							</div>
							<div id="simple_query_condition">							
									<label for="page_type">內容種類:</label>									
					                <select id="page_type_select" data-placeholder="選擇內容種類..."
					                	data-auto-bind="false"
					                   	data-text-field="pageType"
					                   	data-value-field="id"
					                   	data-bind="value: selectedPageType,
					                   		source: contentSelectDataSource,
					                   		events: {
					                   			change: onPageTypeSelectChange
					                   		}" 
					                >
					        		</select>
							</div>
							<div id="simple_query_msg"></div>
							<br>
					    	<div id="simple_query_grid"></div>
					    
					    </div>
					    
					    <div class="ui divider"></div>
					    
					    <div id="complex_query_div">
					    	<a class="ui large red right ribbon label ap_help"
								data-variation="huge very wide inverted"
								data-html='<ol>
											<li>。</li>
											<li>。</li>
											<li>。</li>
											<li>。</li>
											<li>。</li>
											</ol>'
							>
								<i class="large help circle icon" ></i>
								複雜查詢說明
							</a>
							<div class="ui huge header" style="font-size:2.5em">複雜查詢
							</div>
							<div id="complex_query_msg"></div>
							<br>
					    	<div id="complex_query">
					    		Under Construction...
					    	
					    	</div>
					    	
					    </div>
					</div>
				</div>
			
			</div>
		
		
		</div>
	</div>
	
	<script src='<c:url value="/js/application/apCommonBefore.js" />'></script>
	<script src='<c:url value="/js/application/aboutLog.js" />'></script>
	<script src='<c:url value="/js/application/apCommonAfter.js" />'></script>
</body>
</html>