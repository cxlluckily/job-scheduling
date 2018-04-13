Ext.define('Dcs.reg.RegListView', {
	extend : 'Ext.grid.Panel',
	title : 'SQL规则管理',
	store : Ext.create('Dcs.reg.RegListStore'),
	controller : 'regController',
	xtype : 'reglist',
	id : 'reglist',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : 'ID',
		dataIndex : 'id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : 'SOURCE_ID',
		dataIndex : 'source_id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '规则描述',
		dataIndex : 'name',
		align : 'left',
		flex : 1
	}, {
		text : '执行语句',
		dataIndex : 'sql_txt',
		align : 'left',
		flex : 1
	}, {
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