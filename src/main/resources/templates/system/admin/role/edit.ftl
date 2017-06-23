<div class="row">
	<div class="col-md-12">
		<form id="roleEditForm">
			<input type="hidden" id="id" name="id" value=${role.id}>
			<div class="box-body">
				<div class="form-group">
					<label id="roleNameLabel">角色名</label>
					<input type="text" class="form-control" name="name" id="name" value=${role.name} placeholder="角色名...">
				</div>
				<div class="form-group">
					<label id="roleValueLabel">角色值</label>
					<input type="text" class="form-control" name="value" id="value" value=${role.value} placeholder="角色值...">
				</div>
				<div class="form-group">
					<label>权限：</label>
					<label>
						<input type="checkbox" id="allCheckbox" class="flat-red" onClick="onClickCheckbox('allCheckbox','permission')">全选
					</label>
					<br/>
					<#list permissions as permission>
						<#if role.permissionList??>
							<label>
			                  <input type="checkbox" name="permission" class="flat-red" value="${permission.id}"<#list role.permissionList as beanPermission> <#if beanPermission.permissionsValue == permission.permissionsValue>checked</#if></#list>> ${permission.permissionsName}
			                </label>
						<#else>
							<label>
			                  <input type="checkbox" name="permission" class="flat-red" value="${permission.id}"> ${permission.permissionsName}
			                </label>
						</#if>
					</#list>
				</div>
             </div>
			<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
               		<button onclick="roleUpdate();" type="button" class="btn btn-primary btn-sm"><i class="fa fa-paste"></i>更新</button>
				</div>
          	</div>
		</form>
	</div>
</div>
<script type="text/javascript">
	function roleUpdate(){
		$("span").remove(".errorClass");
		$("br").remove(".errorClass");
		var status = 1;
		if($("#roleName").val()==""){
			$("#roleNameLabel").prepend('<span class="errorClass" style="color:red">*角色名不能为空</span><br class="errorClass"/>');
			status = 0;
		}
		if($("#roleValue").val()==""){
			$("#roleValueLabel").prepend('<span class="errorClass" style="color:red">*角色值不能为空</span><br class="errorClass"/>');
			status = 0;
		}
		if(status == 0){
			return false;
		}else{
			ajaxPost();
		}
	}
		
	function ajaxPost() {
		var options = {
	        url: '/role/update',
	        type: 'post',
	        dataType: 'text',
	        data: $("#roleEditForm").serialize(),
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
		};
	$.ajax(options);
	}
</script>