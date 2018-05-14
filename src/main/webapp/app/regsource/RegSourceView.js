Ext.define('App.regsource.RegSourceView', {
	extend : 'Ext.grid.Panel',
	requires : [
		"App.regsource.RegSourceStore",
		"App.regsource.RegSourceWindow",
		"App.regsource.RegSourceController"
    ],
    controller : {
    	xclass : "App.regsource.RegSourceController",
    },
	store : {
		xclass : "App.regsource.RegSourceStore"
	},
	xtype : 'regsource',
	id : 'regsource',
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
		text : '数据源名称',
		dataIndex : 'source_name',
		align : 'left',
		flex : 1
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
		text : '数据源类型',
		dataIndex : 'source_type',
		align : 'left',
		flex : 1,
		renderer:function(value,record){
			if(value==0){
				return "历史数据源";
			}
			if(value==1){
				return "清理数据源";
			}
		}
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