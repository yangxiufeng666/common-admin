<div class="row">
	<div class="box-body  no-padding">
		<table class="table table-striped">
			<tr>
				<td>角色：</td>
				<td style="width: 90%">${bean.permissionsName!}</td>
			</tr>
			<tr>
				<td>角色值：</td>
				<td>${bean.permissionsValue!}</td>
			</tr>
			<tr>
				<td>用户状态：</td>
				<td>
					<#if bean.statusId == '0'>不可用</#if>
					<#if bean.statusId == '1'>可用</#if>
				</td>
			</tr>
			<tr>
				<td>创建时间：</td>
				<td>${bean.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
			<tr>
				<td>更新时间：</td>
				<td>${bean.updateTime?string('yyyy-MM-dd HH:mm:ss')}</td>
			</tr>
		</table>
		<div class="box-footer">
			<div class="pull-right">
				<button type="button" class="btn btn-default btn-sm" id="close" data-dismiss="modal"><i class="fa fa-close"></i>关闭</button>
			</div>
		</div>
	</div>
</div>