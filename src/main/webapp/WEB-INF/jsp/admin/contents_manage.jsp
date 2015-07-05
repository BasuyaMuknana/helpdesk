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
	    	<i class="calendar outline icon"></i> 
	    	告知內容管理功能
	  	</div>
	  	<a class="item" href="#content_grid_div">
	  		<i class="grid layout icon"></i>
	  		告知內容清單
	  	</a>
	  	<a class="item" href="#content_edit_div">
	  		<i class="edit icon"></i>
	  		告知內容編輯	    	
	  	</a>
	  	<!-- <a class="item" href="#user_role_bind_div">
	  		<i class="compress icon"></i>
    		使用者-角色單綁		
    	</a>
    	<a class="item" href="#role_user_bind_div">
    		<i class="exchange icon"></i>
    		角色-使用者群綁
    	</a> -->
	  	
	</div>
	<div id="content_pusher" class="pusher">  <!-- 使用sidebar 必加 -->
		<div class="ui secondary green inverted segment" style="padding: 60px 130px;">
			
			<div class="ui huge header" style="font-size:3em">告知內容管理</div>
			<p class="ui header">歡迎進入告知內容管理功能，您可以在這裡管理各個系統所使用的告知內容。</p>
			
		</div>
		
		<div class="ui page grid" style="padding-top: 10px;">
			
			<div class="row">
				<div class="column">
					<div class="ui segment">
					    <div id="content_grid_div">
							<a class="ui large red right ribbon label ap_help"
								data-variation="huge very wide inverted"
								data-html='<ol>
											<li>本告知內容清單僅顯示該內容項目之基本資料，欲詳該項內容請按"預覽內容"按鈕，會彈出預覽視窗供檢視。</li>
											<li>預覽視窗會將內容拆分系統支援的三種語言來顯示，請點選個別頁籤檢視內容。檢視完畢可按視窗右上角的"X"關閉視窗。</li>
											<li>使用"Delete"按鈕並確認後即會刪除該資料。</li>
											<li>欲新增資料請直接使用下方的<a href="#content_edit_div">告知內容編輯</a>功能。</li>
											<li>欲修改某項目內容請使用"載入內容在下方編輯"按鈕，該項內容資料會載入編輯區，畫面也會自動下拉至編輯區。</li>
											</ol>'
							>
								<i class="large help circle icon" ></i>
								告知內容清單使用說明
							</a>
							<div class="ui huge header" style="font-size:2.5em">告知內容清單
							</div>
							<div id="content_grid_msg"></div>
							<br>
					    	<div id="content_grid"></div>
					    </div>
					    
					    <div id="content_preview_window"></div>
						
						<div class="ui divider"></div>
						
						<div id="content_edit_div">
							<a class="ui large red right ribbon label ap_help"
								data-variation="huge very wide inverted"
								data-html='<ol>
											<li>本編輯區提供告知內容清單中的新增與修改功能。</li>
											<li>欲新增內容項目請直接編輯內容(id不可編輯且為空值)，完成後按下"送出變更"按鈕即可，畫面會上拉至清單。</li>
											<li>欲修改內容，請先透過告知內容清單中的"載入內容在下方編輯"按鈕載入內容之後再行編輯(id不可編輯但有值)，完成後按下"送出變更"按鈕即可，畫面會上拉至清單。</li>
											<li>告知內容種類與版本不可為空值，但告知內容部分可以為空。</li>
											<li>欲手動清空編輯區內容請使用"清除編輯區資料"按鈕。</li>
											<li>任何變更送出後，編輯區資料會自動清空。</li>
											</ol>'
							>
								<i class="large help circle icon" ></i>
								告知內容編輯使用說明
							</a>
							<div class="ui huge header" style="font-size:2.5em">告知內容編輯
							</div>
							<div id="content_edit_msg"></div>
							<br>
					    	<div id="content_edit">
					    		
				    			<div class="ui form">
				    				<div class="three fields">
				    					<div class="field">
				    						<label >ID</label>
											<input type="text" data-bind="value: editor_id" disabled/>
				    					</div>
				    					<div class="field">
				    						<label >內容種類</label>
											<input name="editor_pageType" type="text" data-bind="value: editor_pageType" required pattern="[A-Z]{1,10}" data-pattern-msg="僅接受1至10位大寫字母組合"/>
											<span data-for='editor_pageType' class='k-invalid-msg'></span>
				    					</div>
				    					<div class="field">
				    						<label >版本</label>
											<input name="editor_verNo" type="number" data-bind="value: editor_verNo" required pattern="[0-9]{1,10}" data-pattern-msg="僅接受1至10位數字組合"/>
											<span data-for='editor_verNo' class='k-invalid-msg'></span>
										</div>
				    				</div>
				    			</div>

								<div class="ui top attached tabular menu">
									<!-- <a class="active item" data-tab="raw_html_editor_tab" style="font-size: medium;">原始HTML(三合一)</a> -->
									<a class="active item" data-tab="zhtw_editor_tab" style="font-size: medium;">繁體中文</a>
									<a class="item" data-tab="en_editor_tab" style="font-size: medium;">英文</a>
									<a class="item" data-tab="zhcn_editor_tab" style="font-size: medium;">簡體中文</a>
								</div>
								<!-- <div class="ui bottom attached active tab segment" data-tab="raw_html_editor_tab" style="width: inherit;font-size: medium;">
									<textarea id="raw_html_editor" data-role="editor"
				                      data-tools="[]"	
				                      data-bind="visible: isVisible,
				                                 value: rawHtmlEditorValue,
				                                 events: { change: onChange }"
				                ></textarea>
								</div> -->
								<div class="ui bottom attached active tab segment" data-tab="zhtw_editor_tab" style="width: inherit;font-size: medium;">
									<textarea id="zhtw_editor_editor" data-role="editor" style="font-size: large;"
				                      data-tools="['bold',
				                                   'italic',
				                                   'underline',
				                                   'strikethrough',
				                                   'justifyLeft',
				                                   'justifyCenter',
				                                   'justifyRight',
				                                   'justifyFull',
				                                   'fontSize']"
				                      data-bind="visible: isVisible,
				                                 value: zhtwEditorValue,
				                                 events: { change: onChange }"
				               	 	></textarea>
								</div>
								<div class="ui bottom attached tab segment" data-tab="en_editor_tab" style="width: inherit;font-size: medium;">
									<textarea id="en_editor_editor" data-role="editor" style="font-size: medium;"
				                      data-tools="['bold',
				                                   'italic',
				                                   'underline',
				                                   'strikethrough',
				                                   'justifyLeft',
				                                   'justifyCenter',
				                                   'justifyRight',
				                                   'justifyFull',
				                                   'fontSize']"
				                      data-bind="visible: isVisible,
				                                 value: enEditorValue,
				                                 events: { change: onChange }"
				                	></textarea>
								</div>
								<div class="ui bottom attached tab segment" data-tab="zhcn_editor_tab" style="width: inherit;font-size: medium;">
									<textarea id="zhcn_editor_editor" data-role="editor" style="font-size: medium;"
				                      data-tools="['bold',
				                                   'italic',
				                                   'underline',
				                                   'strikethrough',
				                                   'justifyLeft',
				                                   'justifyCenter',
				                                   'justifyRight',
				                                   'justifyFull',
				                                   'fontSize']"
				                      data-bind="visible: isVisible,
				                                 value: zhcnEditorValue,
				                                 events: { change: onChange }"
				                	></textarea>
								</div>
								
								<div class="ui buttons" style="padding-top:10px">
								  	<div class="ui large animated fade button" data-bind="events:{ click: clearEditor}">
									  	<div class="visible content">清除編輯區資料</div>
									  	<div class="hidden content">Sure?</div>
									</div>
								  	<div class="or"></div>
								  	<div class="ui large positive animated fade button" data-bind="events:{ click: sendModify">
									  	<div class="visible content">送出變更</div>
									  	<div class="hidden content">GO!</div>
									</div>
								</div>

					    	</div>
						
						
						</div>
						
					</div>
				
				</div>
				
			</div>
		
		</div>
	</div>
	<script type="text/x-kendo-template" id="content_preview_template">
    	<div id="details-container">
			
            <div class="ui center aligned header">
				<div class="ui huge black label">
					告知內容種類
					<div class="detail" style="font-size: medium">#= pageType #</div>
				</div>
				
				<div class="ui huge black label">
					版本
					<div class="detail" style="font-size: medium">#= verNo #</div>
				</div>
				<div id="this_for_old_api" class="ui huge red label">
					此為舊版API內容
				</div>
			</div>
			<div class="ui top attached tabular menu">
				<a class="active item" data-tab="first" style="font-size: medium;">繁體中文</a>
				<a class="item" data-tab="second" style="font-size: medium;">英文</a>
				<a class="item" data-tab="third" style="font-size: medium;">簡體中文</a>
			</div>
			<div class="ui bottom attached active tab segment" data-tab="first" style="width: inherit;font-size: medium;">
				#= zhtw #
			</div>
			<div class="ui bottom attached tab segment" data-tab="second" style="width: inherit;font-size: medium;">
				#= en #
			</div>
			<div class="ui bottom attached tab segment" data-tab="third" style="width: inherit;font-size: medium;">
				#= zhcn #
			</div>      
    	</div>
    </script>
	<script src='<c:url value="/js/application/apCommonBefore.js" />'></script>
	<script src='<c:url value="/js/application/aboutContent.js" />'></script>
	<script src='<c:url value="/js/application/apCommonAfter.js" />'></script>
	
	
   
</body>


</html>