<div class="row">
	<div class="col-md-12">
		<form id="permissionAddForm">
			<div class="modal-body">
				<div class="form-group">
					<label for="exampleInput" id="permissionNameLabel">权限名</label>
					<input type="text" class="form-control" name="permissionsName" id="permissionsName" placeholder="输入权限名...">
				</div>
				<div class="form-group">
					<label for="exampleInput" id="permissionValueLabel">权限值</label>
					<input type="text" class="form-control" name="permissionsValue" id="permissionsValue" placeholder="输入权限值...">
				</div>
			</div>
			<div class="modal-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" data-dismiss="modal"><i class="fa fa-close"></i>取消</button>
					<button type="button" class="btn btn-primary btn-sm" onclick="permissionSave();"><i class="fa fa-save"></i>保存</button>
				</div>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript">
function permissionSave(){
	$("span").remove(".errorClass");
	$("br").remove(".errorClass");
	var status = 1;
	if($("#permissionsName").val()==""){
		$("#permissionNameLabel").prepend('<span class="errorClass" style="color:red">*权限名不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if($("#permissionsValue").val()==""){
		$("#permissionValueLabel").prepend('<span class="errorClass" style="color:red">*权限名不能为空</span><br class="errorClass"/>');
		status = 0;
	}
	if(status == 0){
		return false;
	}else{
		$.ajax({
			url: '${ctx}/admin/permission/save',
	        type: 'post',
	        dataType: 'text',
	        data: $("#permissionAddForm").serialize(),
	        success: function (data) {
	        	$("#lgModal").modal('hide');
	        	alertMsg("添加成功","success");
	        	reloadTable(list_ajax,"#permissionTime","#permissionPremise");
	        }
		});
	}
}
</script>