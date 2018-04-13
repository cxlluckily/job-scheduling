Ext.define('Dcs.job.DataSourceListView', {
	extend : 'Ext.grid.Panel',
	title : '注册中心',
	store : Ext.create('Dcs.job.DataSourceStore'),
	xtype: 'dataSourceList',
	id: 'dataSourceList',
	controller: 'dataSourceController',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : 'ID',
		dataIndex : 'id',
		align : 'left',
		flex : 1,
		hidden : true
	}, {
		text : '名称',
		dataIndex : 'name',
		align : 'left',
		flex : 1
	}, {
		text : 'driver',
		dataIndex : 'driver',
		align : 'left',
		flex : 1
	}, {
		text : 'url',
		dataIndex : 'url',
		align : 'left',
		flex : 1
	}, {
		text : '用户名',
		dataIndex : 'username',
		align : 'left',
		flex : 1
	}, {
		text : '密码',
		dataIndex : 'password',
		align : 'left',
		flex : 1
	}, {
		text : '状态',
		dataIndex : 'activated',
		align : 'left',
		flex : 1
	}, {
	    text: '操作',
	    xtype: 'actioncolumn',
	    align:"center",
	    width: 100,
	    items: [
	        {
	            tooltip: '连接',
	            icon: "resources/images/edit_task.png",
	            handler: function(grid, rowIndex, colIndex) {
	                alert(1);
	            }
	        }, {
	            tooltip: '删除',
	            icon: "resources/images/delete.png",
	            handler: function(grid, rowIndex, colIndex) {
	                alert(1);
	            }
	        }
	    ]
	}],
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
		text : '删除',
		action : 'delete',
		icon: 'resources/images/delete.png'
	} ]

});