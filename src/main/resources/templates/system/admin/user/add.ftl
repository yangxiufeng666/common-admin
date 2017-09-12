<div class="row">
	<div class="col-md-12">
		<form id="securityAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label id="userNoLabel">账号</label>
					<input type="text" class="form-control" name="username" id="username" placeholder="输入账号...">
				</div>
				<div class="form-group">
					<label id="passwordLabel">密码</label>
					<input type="password" class="form-control" name="password" id="password" placeholder="输入密码...">
				</div>
				<div class="form-group">
					<label id="nickNameLabel">昵称</label>
					<input type="text" class="form-control" name="name" id="name" placeholder="输入昵称...">
				</div>
				<div class="form-group">
					<label>性别</label> 
					<select name="sex" class="form-control select2" style="width: 100%;">
						<option value="1">男</option>
						<option value="0">女</option>
					</select>
				</div>
				<#--<div class="form-group">-->
					<#--<label>角色：</label>-->
					<#--<br/>-->
					<#--<#list roles as role>-->
						<#--<#if role.value == 'super'>-->
							<#--<@shiro.hasRole name="super">-->
							<#--<label>-->
			                  <#--<input type="radio" name="roleId" class="flat-red" value="${role.id}"> ${role.name}-->
			                <#--</label>-->
			                <#--</@shiro.hasRole>-->
		                <#--<#else>-->
			                <#--<label>-->
			                  <#--<input type="radio" name="roleId" class="flat-red" value="${role.id}"> ${role.name}-->
			                <#--</label>-->
		                <#--</#if>-->
					<#--</#list>-->
				<#--</div>-->
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="securitySave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function securitySave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#username").val()==""){
		$("#userNoLabel").prepend('<span class="errorClass" style="color:red">*账号不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if($("#password").val()==""){
		$("#passwordLabel").prepend('<span class="errorClass" style="color:red">*密码不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '/user/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#securityAddForm").serialize(),
	        success: function (data) {
                var json = JSON.parse(data);
                if (json.status){
                    $("#lgModal").modal('hide');
                    alertMsg("添加成功","success");
                    reloadTable(list_ajax,"#roleTime","#rolePremise");
                }else{
                    alertMsg("添加失败:"+json.msg,"success");
                }
	        }
		});
	}
}
</script>