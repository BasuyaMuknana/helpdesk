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
	    	<i class="treatment icon"></i> 
	    	系統狀態測試
	  	</div>
	  	<a class="item" href="#simple_query_div">
	  		<i class="download icon"></i>
	  		查詢告知內容測試
	  	</a>
	  	<!-- <a class="item" href="#complex_query_div">
	  		<i class="upload icon"></i>
	  		新增檢視紀錄測試	    	
	  	</a> -->
	  	
	</div>

	<div id="service_pusher" class="pusher">
		<div class="ui secondary green inverted segment" style="padding: 60px 130px;">			
			<div class="ui huge header" style="font-size:3em">系統狀態測試</div>
			<p class="ui header">歡迎進入系統狀態測試功能，您可以在這裡測試Restful API的功能是否正常。</p>
						
		</div>
		
		<div class="ui page grid" style="padding-top: 10px;">
			<div class="row">
				<div class="column">
					<div class="ui segment">
					    <div id="simple_query_div">
					    	<a class="ui large red right ribbon label ap_help"
								data-variation="huge very wide inverted"
								data-html='<ol>
											<li>Helpdesk將透過ajax直接發送JSONP要求以避免pdis-api會保留前次查詢的結果而造成誤判。</li>
											<li>如果是使用預設的測試網址，可以直接按下"按此測試"按鈕。 </li>
											<li>預設的測試網址為"http://localhost:8080/pdis-rest/rest/contents/serviceCheck"。 預設會查回PDIS第二版的內容。 </li>
											<li>除了"測試回應"之外，其他的輸入框都可以修改，而"測試網址"的值會跟著變動，且"測試網址"也可以直接編輯。</li>
											<li>系統會在第一次點選"內容種類"選單時載入系統既有的內容種類，但是因本人懶惰的關係沒有作distinct過濾...就交給你了。</li>
											<li>選擇"內容種類"後，請確認再次選擇"版號"的選擇，因為"版號"的選項是依據"內容種類"的選擇而決定。</li>
											<li>決定"內容種類"與"版號"的值之後，並不會影響"測試網址"的值。</li>
											<li>"測試回應"區塊將會顯示Restful API回傳的內容，內容為資料庫中content_clob中的欄位內容。</li>
											</ol>'
							>
								<i class="large help circle icon" ></i>
								查詢告知內容測試說明
							</a>
							<div class="ui huge header" style="font-size:2.5em">查詢告知內容測試
							</div>
							
							<div class="ui basic grid">
								<div class="row">
									<div class="column">
										<div id="simple_query_condition" class="ui form">
											<div class="three fields">
												<div class="field">
													<label for="service_ip">服務IP:</label>
													<select id="env_select" data-placeholder="選擇環境..." class="ui dropdown" style="width: 100%" 
									                   	data-bind="value: selectedEnv,
									                   		events: {
									                   			change: onEnvSelectChange
									                   		}" 
									                >
									                	<option value="10.89.8.160">驗證-10.89.8.160</option>
									                   	<option value="10.88.37.51">營運-10.88.37.51</option>
									        		</select>
													
													
												</div>
												<div class="field">
													<label for="service_port">服務埠號:</label>
													<input id="service_port" type="text" 
														data-bind="value: targetPort, events: {
									                   			change: changeTargetUrl
									                   		}"></input>
								            	</div>
								            	<div class="field">
													<label for="context_root">資源路徑:</label>
													<input id="context_root" type="text"
														data-bind="value: targetContext, events: {
									                   			change: changeTargetUrl
									                   		}"></input> 
												</div>
											</div>
											<div class="two fields">	
												<div class="field">
													<label for="content_select">內容種類:</label>
																					
									                <select id="content_select" data-placeholder="選擇內容種類..." class="ui dropdown" style="width: 100%" 
									                	data-auto-bind="false"
									                   	data-text-field="pageType"
									                   	data-value-field="id"
									                   	data-bind="value: selectedPageType,
									                   		source: contentSelectDataSource,
									                   		events: {
									                   			change: onContentSelectChange
									                   		}" 
									                >
									        		</select>
									        		
												</div>
												<div class="field">
													<label for="ver_no_select">版號:</label>									
									                <select id="ver_no_select" data-placeholder="選擇版號..." disabled class="ui dropdown" style="width: 100%"
								                		data-text-field="verNo"
									                   	data-value-field="verNo"
									                   	data-bind="value: selectedVerNo,
									                   		events: {
									                   			change: onVerNoSelectChange
									                   		}" 
									                >
									        		</select>
												</div>
											</div>
											
											<div class="field">					
								        		<label for="final_url">測試網址:</label>
												<div class="ui action input">
													<input id="final_url" type="text" 
														data-bind="value: targetQueryUrl" style="width:100%"></input>
													<div id="query_test_btn" class="ui teal right labeled icon button" style="width:20%"
															data-bind="
									                   		events: {
									                   			click: sendTestQuery
									                   		}"
													>
														<i class="dashboard icon"></i>
														按此測試
													</div>
												</div>
											</div>
											
											<div class="field">					
								        		<label for="queryResp">測試回應:</label>
												<div class="ui action input">
													<textarea id="queryResp"   data-bind="value: queryResp">
													</textarea>

												</div>
											</div>
										</div>
									</div>

								</div>
							</div>
					    
					    </div>

					</div>
				</div>
			
			</div>
		
		
		</div>
	</div>	
	<script src='<c:url value="/js/application/apCommonBefore.js" />'></script>
	<script src='<c:url value="/js/application/aboutService.js" />'></script>	 
	<script src='<c:url value="/js/application/apCommonAfter.js" />'></script>
</body>
</html>