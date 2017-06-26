<div class="row">
	<div class="col-md-12">
		<div class="box-body  no-padding">
			<table class="table table-striped">
                <tr>
                	<td>角色：</td>
                	<td style="width: 90%">${role.name}</td>
                </tr>
                <tr>
                	<td>角色值：</td>
                	<td>${role.value}</td>
                </tr>
                <tr>
                	<td>拥有权限：</td>
                	<td>
						<#if role.permissionList??>
							<#list role.permissionList as permission>
							${permission_index+1}.${permission.permissionsName}
							</#list>
						</#if>
                	</td>
                </tr>
                <tr>
                	<td>创建时间：</td>
                	<td>${role.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
                </tr>
                <tr>
                	<td>更新时间：</td>
					<#if role.updateTime??>
                        <td>${role.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
					</#if>
                </tr>
         	</table>
         	<div class="box-footer">
				<div class="pull-right">
					<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
				</div>
			</div>
    	</div>
	</div>
</div>