Ext.define('Dcs.regsource.RegSourceView', {
	extend : 'Ext.grid.Panel',
	title : '数据库管理',
	store : Ext.create('Dcs.regsource.RegSourceStore'),
	controller : 'regSourceController',
	xtype : 'regsource',
	id : 'regsource',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : 'ID',
		dataIndex : 'id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '主库地址',
		dataIndex : 'master_url',
		align : 'left',
		flex : 1,
	}, {
		text : '主库用户名',
		dataIndex : 'master_username',
		align : 'left',
		flex : 1
	}, {
		text : '主库密码',
		dataIndex : 'master_password',
		align : 'left',
		flex : 1
	}, {
		text : '从库地址',
		dataIndex : 'slave_url',
		align : 'left',
		flex : 1
	},{
		text : '从库用户名',
		dataIndex : 'slave_username',
		align : 'left',
		flex : 1
	},{
		text : '从库密码',
		dataIndex : 'slave_password',
		align : 'left',
		flex : 1
	},{
		text : '创建时间',
		dataIndex : 'create_time',
		align : 'left',
		flex : 1,
		renderer : function(value, record) {
			if (value) {
				return Ext.Date.format(value, 'Y-m-d H:i:s')
			}
			return '';
		}

	} ],
	fullscreen : true,
	renderTo : Ext.getBody(),
	selModel : {
		selType : 'checkboxmodel',
		mode : 'SINGLE'

	},
	tbar : [ {
		xtype : 'button',
		text : '新增',
		action : 'add',
		icon : 'resources/images/new_list.png'
	}, {
		xtype : 'button',
		text : '修改',
		action : 'update',
		icon: 'resources/images/edit_task.png'
	}, {
		xtype : 'button',
		text : '删除',
		action : 'delete',
		icon: 'resources/images/delete.png'
	} ]

});