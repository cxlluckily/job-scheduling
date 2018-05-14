Ext.define('App.reg.RegListView', {
	extend : 'Ext.grid.Panel',
	store : {
		xclass : "App.reg.RegStore"
	},
	controller : {
		xclass : "App.reg.RegController"
	},
	xtype : 'reglist',
	id : 'reglist',
	requires : [
		"App.reg.RegStore",
		"App.reg.RegController",
		"App.reg.RegWindow"
    ],
	columns : [ {
		text: '序号',
		xtype : 'rownumberer',
		width: '10px',
		align : 'center'
	}, {
		text : 'ID',
		dataIndex : 'id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '清理数据源id',
		dataIndex : 'source_id',
		align : 'left',
		flex : 1,
		hidden : true
	},  {
		text : '清理数据库名称',
		dataIndex : 'db_name',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '历史数据源id',
		dataIndex : 'history_source_id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '历史数据库名称',
		dataIndex : 'history_db_name',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '规则名称',
		dataIndex : 'name',
		align : 'left',
		flex : 1
	}, {
		text : '清理源校验SQL',
		dataIndex : 'check_sql',
		align : 'left',
		flex : 1
	}, {
		text : '历史源校验SQL',
		dataIndex : 'check_history_sql',
		align : 'left',
		flex : 1
	}, {
		text : '删除规则SQL',
		dataIndex : 'sql_txt',
		align : 'left',
		flex : 1
	}, {
		text : '作业名称',
		dataIndex : 'job_name',
		align : 'left',
		flex : 1
	},{
		text : '修改时间',
		dataIndex : 'modify_time',
		align : 'left',
		flex : 1,
		renderer : function(value, record) {
			if (value) {
				return Ext.Date.format(value, 'Y-m-d H:i:s')
			}
			return '';
		}

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
	selModel : {
		selType : 'checkboxmodel',
		mode : 'SINGLE',
		allowDeselect: true
	},
	bbar: {
        xtype: 'pagingtoolbar',
        displayInfo: true,
        plugins: 'ux-progressbarpager'
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