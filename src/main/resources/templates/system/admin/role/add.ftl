<div class="row">
	<div class="col-md-12">
		<form id="roleAddForm">
				<div class="modal-body">
					<div class="form-group">
						<label id="roleNameLabel">角色</label>
						<input type="text" class="form-control" name="roleName" id="roleName" placeholder="输入角色...">
					</div>
					<div class="form-group">
						<label id="roleValueLabel">角色值</label>
						<input type="text" class="form-control" name="roleValue" id="roleValue" placeholder="输入角色值...">
					</div>
					<div id="checkbox" class="form-group">
						<label>角色：</label>
						<label>
							<input type="checkbox" id="allCheckbox" class="flat-red" onClick="onClickCheckbox('allCheckbox','permission')">全选
						</label>
						<br/>
						<#list permissions as permission>
							<label>
			                  <input type="checkbox" name="permission" class="flat-red" value="${permission.id}"> ${permission.permissionsName}
			                </label>
						</#list>
					</div>
				</div>
				<div class="modal-footer">
					<div class="pull-right">
						<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>取消</button>
						<button type="button" class="btn btn-primary btn-sm" onclick="roleAdd();"><i class="fa fa-save"></i>保存</button>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function roleAdd(){
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
		$.ajax({
			url: '${ctx}/admin/role/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#roleAddForm").serialize(),
	        success: function (data) {
	        	debugger;
	        	$("#lgModal").modal('hide');
	        	alertMsg("添加成功","success");
	        	reloadTable(list_ajax,"#roleTime","#rolePremise");
	        }
		})
	}
}
</script>