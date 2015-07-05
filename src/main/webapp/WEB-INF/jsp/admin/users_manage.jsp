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
	    	<i class="users icon"></i> 
	    	使用者管理功能
	  	</div>
	  	<a class="item" href="#user_grid_div">
	  		<i class="grid layout icon"></i>
	  		使用者清單
	  	</a>
	  	<a class="item" href="#role_grid_div">
	  		<i class="doctor icon"></i>
	  		角色清單	    	
	  	</a>
	  	<a class="item" href="#user_role_bind_div">
	  		<i class="compress icon"></i>
    		使用者-角色單綁		
    	</a>
    	<a class="item" href="#role_user_bind_div">
    		<i class="exchange icon"></i>
    		角色-使用者群綁
    	</a>
	  	
	  	<!-- <div class="ui dropdown item">
	    	<i class="dropdown icon"></i>
	   		 使用者-角色綁定
	    	<div class="menu">
	      		<a class="item" href="#user_role_bind_div">
	      			使用者-角色單綁
	      			<i class="compress icon"></i>
	      		</a>
	      		<a class="item" href="#role_user_bind_div">
	      			角色-使用者群綁
	      			<i class="exchange icon"></i>
	      		</a>
	    	</div>
	  	</div> -->
	</div>
	<div id="user_role_pusher" class="pusher">  <!-- 使用sidebar 必加 -->
	<div class="ui secondary green inverted segment" style="padding: 60px 130px;">
		
		<div class="ui huge header" style="font-size:3em">使用者管理</div>
		<p class="ui header">歡迎進入使用者管理功能，您可以透過"使用者清單"與"角色清單"兩個Grid分別進行使用者與角色的基本資料管理。</p>
		<p class="ui header">一旦設定好資本資料後，可以再透過"使用者-角色單綁"與"角色-使用者群綁"功能進行使用者與角色之間的綁定。</p>
	</div>
	
	<div class="ui page grid" style="padding-top: 10px;"><!-- <div class="ui page grid" style="padding: 55px;"> -->
		<!-- <div class="row">
			<div class="column">
				<div class="ui segment">
					<div class="ui huge header">使用者管理</div>
				</div>
			</div>
		</div> -->
		<div class="row">
			<!-- <div class="four wide column"></div> -->
			<!-- <div class="twelve wide column"> -->
			<div class="column">
				<div class="ui segment">
					<!-- <div class="ui left very close rail">
					    
					    	<div class="ui large fixed vertical menu">
						
								<div class="header item">
							    	<i class="users icon"></i> 使用者管理功能
							  	</div>
							  	<a class="item" href="#user_grid_div">
							  		使用者清單
							    	<i class="user icon"></i>
							  	</a>
							  	<a class="item" href="#role_grid_div">
							  		角色清單
							    	<i class="doctor icon"></i>
							  	</a>
							  	<div class="ui dropdown item">
							    	<i class="dropdown icon"></i>
							   		 使用者-角色綁定
							    	<div class="menu">
							      		<a class="item" href="#user_role_bind_div">
							      			使用者-角色單綁
							      			<i class="compress icon"></i>
							      		</a>
							      		<a class="item" href="#role_user_bind_div">
							      			角色-使用者群綁
							      			<i class="exchange icon"></i>
							      		</a>
							    	</div>
							  	</div>
							</div>
					    	  	
					    
				    </div> -->
				    
				    
				    <div id="user_grid_div">
				    	
						
						
						<!-- <div class="ui huge header" style="padding-top:50px">使用者清單
						</div> -->
						
						
						
						<a class="ui large red right ribbon label ap_help"
							data-variation="huge very wide inverted"
							data-html='<ol>
										<li>使用者清單的Grid採用批次作業，執行任何增刪改(一項或多項)的動作後，最後皆必須按下"Save changes"按鈕才會跟後端系統同步。</li>
										<li>使用者清單所有操作與角色無關，僅維護使用者的基本資訊。欲定義使用者與角色關係，請使用"使用者-角色綁定"功能。</li>
										<li>承1.，執行任何增刪改(一項或多項)的動作後，都可以透過"Cancel changes"按鈕來取消。</li>
										<li>欲新增使用者，請使用"Add new record"按鈕。新增的使用者將預設擁有"ROLE_USER"角色。</li>
										<li>欲刪除使用者，請使用第一行的"Delete"按鈕刪除該列使用者。</li>
										<li>欲修改使用者資料，請直接點選欄位即可編輯。</li>
										<li>使用者密碼一欄，僅供新增與修改(重設)時使用。輸入明文密碼，經HTTP POST至後端，再經過hash後存入DB。</li>
										
										</ol>'
						
						>
							<i class="large help circle icon" ></i>
							使用者清單使用說明
						</a>
						<div class="ui huge header" style="font-size:2.5em">使用者清單
						</div>
						<div id="user_grid_msg"></div>
						<br>
				    	<div id="user_grid"></div>
				    </div>
					
					<div class="ui divider"></div>
					
					<div id="role_grid_div">
						<!-- <div class="ui huge header" style="padding-top:50px">角色清單</div> -->
						
						<a class="ui large red right ribbon label ap_help" data-variation="huge very wide inverted"
							data-html='<ol>
										<li>角色清單的Grid採用批次作業，執行任何增刪改(一項或多項)的動作後，最後皆必須按下"Save changes"按鈕才會跟後端系統同步。</li>
										<li>角色清單所有操作與使用者無關，僅維護角色的基本資訊。欲定義使用者與角色關係，請使用"使用者-角色綁定"功能。</li>
										<li>承1.，執行任何增刪改(一項或多項)的動作後，都可以透過"Cancel changes"按鈕來取消。</li>
										<li>欲新增角色，請使用"Add new record"按鈕。新增的角色名稱須與Spring Security中的出現的角色一致。</li>
										<li>欲刪除角色，請使用第一行的"Delete"按鈕刪除該列角色。</li>
										<li>欲修改角色資料，請直接點選欄位即可編輯。修改的角色名稱須與Spring Security中的出現的角色一致。</li>
										
										</ol>'
						>
							<i class="large help circle icon" ></i>
							角色清單使用說明
						</a>
						<div class="ui huge header" style="font-size:2.5em">角色清單</div>
						<div id="role_grid_msg"></div>
						<br>
						<div id="role_grid"></div>
					</div>
					
					<div class="ui divider"></div>
					
					<div id="user_role_bind_div">
						<!-- <div class="ui huge header" style="padding-top:50px">使用者-角色單綁</div> -->
						
						<a class="ui large red right ribbon label ap_help" data-variation="huge very wide inverted"
							data-html='<ol>
										<li>使用者-角色單綁提供從單一使用者的觀點來設定其所屬的角色。適合對已存在的使用者進行個別變更。</li>
										<li>透過下拉選單選擇要變更角色的使用者，該下拉選單會下載系統已有的使用者供您選擇，若沒有您要變更的使用者請先新增使用者。</li>
										<li>選定使用者後，會在下方角色多選器中顯示該使用者已有的角色。可再依需求刪除或增加。</li>
										<li>角色多選器可以透過點選或打字的方式來新增角色選項(有選單彈出)。如果已經選擇全部角色選項，則不再出現選單。</li>
										<li>已經選擇的角色，可以透過"X"來取消選擇。</li>
										<li>確定角色選項後，可透過"設定使用者角色"按鈕送出變更。</li>
										<li>*送出的變更會以新的角色選項清單覆蓋之前的角色清單，所以務必確定預先載入的角色選項是否要保留。</li>
										</ol>'
						>
							<i class="large help circle icon" ></i>
							使用者-角色單綁使用說明
						</a>
						<div class="ui huge header" style="font-size:2.5em">使用者-角色單綁</div>
						<div id="user_role_bind_msg"></div>
						<br>
						<div id="user_role_bind" >
							<!-- <div id="dimmer" class="ui dimmer">
							    <div class="ui text loader">Loading</div>
							 </div> -->
							<div class="ui form">
								<div class="two fields">
									<div class="field">
										<label for="select_user">選擇要操作的使用者(單選):</label>
								        <select id="select_user" data-placeholder="選擇使用者..." class="ui dropdown" style="width: 100%"
								        	data-text-field="userName"
			                   				data-value-field="id"
			                   				data-bind="value: selectedUser,
			                              				source: userDataSource,
			                              				events: { change: onSingleSelectChange
			                              				}">
			
								        </select>
							        </div>
							
									<div class="field">
										<label for="select_roles_1">選擇使用者要綁定的角色(可複選):</label>
								        <select id="select_roles_1" multiple="multiple" data-placeholder="選擇或輸入角色..." 
								        	data-text-field="roleName"
			                   				data-value-field="id"
			                   				data-bind="value: selectedRoles1,
			                              				source: roleDataSource">
								            
								        </select>
							        </div>
					        
								</div>
								<div class="ui large buttons">
									<div id="btn_urb_cancel" class="ui button">清除</div>
									<div class="or"></div>
									<div id="btn_urb_submit" class="ui positive button">設定使用者角色</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="ui divider"></div>
					
							
					<div id="role_user_bind_div">
						<!-- <div class="ui huge header" style="padding-top:50px">角色-使用者群綁</div> -->
						
						<a class="ui large red right ribbon label ap_help" data-variation="huge very wide inverted"
							data-html='<ol>
										<li>角色-使用者群綁提供從角色觀點來設定其所擁有的使用者。適合有新增使用者或角色時使用。</li>
										<li>使用者多選器與角色多選器可以選擇複數選項。多選器會下載系統中已有的使用者與角色。</li>
										<li>多選器可以透過點選或打字的方式來新增角色選項(有選單彈出)。如果已經選擇全部選項，則不再出現選單。</li>
										<li>多選器中已經選擇的選項，可以透過"X"來取消選擇。</li>
										<li>決定角色選項後，透過下拉選單選擇動作。"增加使用者進去"會讓選擇的使用者擁有選擇的角色；而"刪除裡面的使用者"則是讓選擇的使用者失去選擇的角色。</li>
										<li>確定所有選項後，可透過"變更角色使用者綁定"按鈕送出變更。</li>
										<li>*送出的變更不會涉及選項外的使用者-角色關聯，僅變更(新增或刪除)與選項相關的關聯。</li>
										</ol>'
						>
							<i class="large help circle icon" ></i>
							角色-使用者群綁使用說明
						</a>
						<div class="ui huge header" style="font-size:2.5em">角色-使用者群綁</div>
						<div id="role_user_bind_msg"></div>
						<br>
						<div id="role_user_bind">
							<div class="ui form">
								<div class="field">
									<label for="select_roles_2">選擇要操作的角色(可複選):</label>
							        <select id="select_roles_2" multiple="multiple" data-placeholder="選擇或輸入角色..."
							        	data-text-field="roleName"
		                   				data-value-field="id"
		                   				data-bind="value: selectedRoles2,
		                              				source: roleDataSource">
							            
							        </select>
								</div>
								<div class="field">
									<label for=bind_users_op>你要已選擇的角色... </label>
									<select id="bind_users_op" data-placeholder="選擇一項動作..." class="ui dropdown" style="width: 50%">
								        <option value="add">增加使用者進去</option>
								        <option value="remove">刪除裡面的使用者</option>
								        
							        </select>
								</div>
								<div class="field">
									<label for="select_users">選擇要加入群組的使用者(可複選):</label>
							        <select id="select_users" multiple="multiple" data-placeholder="選擇或輸入使用者..."
							        	data-text-field="userName"
		                   				data-value-field="id"
		                   				data-bind="value: selectedUsers,
		                              				source: userDataSource">
							            
							        </select>
						        </div>
								
								<div class="ui large buttons">
									<div id="btn_rub_cancel" class="ui button">清除</div>
									<div class="or"></div>
									<div id="btn_rub_submit" class="ui positive button">變更角色使用者綁定</div>
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
	<script src='<c:url value="/js/application/aboutUser.js" />'></script>
	<script src='<c:url value="/js/application/aboutRole.js" />'></script>
	
	<script>
		var bindUsersOpSelect;

		
		function cleanUserBindRole(){
			userSingleSelect.value("");
			roleMultiSelect1.value([]);
			userSingleSelect.refresh();
			roleMultiSelect1.refresh();
		}
		
		function cleanRoleBindUser(){
			userMultiSelect.value([]);
			roleMultiSelect2.value([]);
			userMultiSelect.refresh();
			roleMultiSelect2.refresh();
		}
		
		$(document).ready(function(){

			bindUsersOpSelect = $("#bind_users_op").kendoComboBox().data("kendoComboBox");
			$("#btn_urb_cancel").click(function(){
				cleanUserBindRole();
			});
			$("#btn_rub_cancel").click(function(){
				cleanRoleBindUser();
			});
			
			$("#btn_urb_submit").click(function(){
				var userSingleSelectData = userSingleSelect.dataItem();
				var roleMultiSelect1Data = roleMultiSelect1.dataItems();
				var msgTargetDomId = "user_role_bind_msg";
				$.ajax({
					url: "user_bindRoles",
					type: "POST",
					dataType: "json",
					data: {
						models: kendo.stringify({users: userSingleSelectData, 
													roles: roleMultiSelect1Data})
					}
				})
				.done( function(response){
					var parsed = $.parseJSON(response);
					if(parsed.results === undefined){
	       				displayWarningMsg(msgTargetDomId, response.errors);
	       			}else{
	       				displaySuccessMsg(msgTargetDomId, "您的操作已成功執行!");
	       				cleanUserBindRole();
	       			}
				})
				.fail( function(){
					displayFailMsg(msgTargetDomId, "與server的溝通似乎出了問題...");
				});
				
			});
			
			
			
			$("#btn_rub_submit").click(function(){
				var userMultiSelectData = userMultiSelect.dataItems();
				var roleMultiSelectData = roleMultiSelect2.dataItems();
				//console.log("bindUsersOpSelectData="+$("#bind_users_op").val());
				var bindUsersOpSelectData = $("#bind_users_op").val();
				var msgTargetDomId = "role_user_bind_msg";
				$.ajax({
					url: "role_bindUsers",
					type: "POST",
					dataType: "json",
					data: {
						models: kendo.stringify({users: userMultiSelectData, 
													roles: roleMultiSelectData,
													op: bindUsersOpSelectData})
					}
				})
				.done( function(response){
					var parsed = $.parseJSON(response);
					if(parsed.results === undefined){
						displayWarningMsg(msgTargetDomId, response.errors);
	       			}else{
	       				displaySuccessMsg(msgTargetDomId, "您的操作已成功執行!");
	       				cleanRoleBindUser();
	       			}
				})
				.fail( function(){
					displayFailMsg(msgTargetDomId, "與server的溝通似乎出了問題...");
				});
				
			});
			
			
			
			
		});
		
		
	
	</script>
	<script src='<c:url value="/js/application/apCommonAfter.js" />'></script>
</body>


</html>