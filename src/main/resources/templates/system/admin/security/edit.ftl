<div class="row">
	<div class="col-md-12">
		<form id="securityEditForm">
			<input type="hidden" id="id" name="id" value=${bean.id}>
			<div class="box-body">
				<div class="form-group">
					<label id="userNoLabel">账号</label>
					<input type="text" class="form-control" name="userNo" id="userNo" value=${bean.userNo!} disabled placeholder="输入账号...">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">昵称</label>
					<input type="text" class="form-control" name="nickName" id="nickName" value=${bean.nickName!} placeholder="输入昵称...">
				</div>
				<div class="form-group">
					<label>性别</label> 
					<select name="sex" class="form-control select2" style="width: 100%;">
						<option <#if bean.sex == 0>selected="selected"</#if> value="0">女</option>
						<option <#if bean.sex == 1>selected="selected"</#if> value="1">男</option>
					</select>
				</div>
				<div class="form-group">
					<label>角色：</label>
					<label>
						<input type="checkbox" id="allCheckbox" class="flat-red" onClick="onClickCheckbox('allCheckbox','role')">全选
					</label>
					<br/>
					<#list roles as role>
						<#if bean.roleList??>
							<#if role.roleValue == 'superAdmin'>
								<@shiro.hasPermission name="super:update">
									<label>
					                  <input type="checkbox" name="role" class="flat-red" value="${role.id}"<#list bean.roleList as beanRole> <#if beanRole.roleValue == role.roleValue>checked</#if></#list>> ${role.roleName}
					                </label>
				                </@shiro.hasPermission>
			                <#else>
			                	<label>
				                  <input type="checkbox" name="role" class="flat-red" value="${role.id}"<#list bean.roleList as beanRole> <#if beanRole.roleValue == role.roleValue>checked</#if></#list>> ${role.roleName}
				                </label>
			                </#if>
						<#else>
							<#if role.roleValue == 'superAdmin'>
								<@shiro.hasPermission name="super:update">
									<label>
					                  <input type="checkbox" name="role" class="flat-red" value="${role.id}"> ${role.roleName}
					                </label>
			                	 </@shiro.hasPermission>
			                <#else>
			                	<label>
				                  <input type="checkbox" name="role" class="flat-red" value="${role.id}"> ${role.roleName}
				                </label>
			                </#if>
						</#if>
					</#list>
				</div>
             </div>
			<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button onclick="securityUpdate();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function securityUpdate(){
	$.ajax({
		url: '${ctx}/admin/security/update',
	    type: 'post',
	    dataType: 'text',
	    data: $("#securityEditForm").serialize(),
	    success: function (data) {
	    	$("#lgModal").modal('hide');
	    	alertMsg("更新成功","success");
	    	reloadTable(list_ajax,"#securityTime","#securityPremise");
	    }
	});
}

</script>