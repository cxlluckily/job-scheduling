var registryCenterStore = Ext.create('Dcs.job.RegistryCenterStore');

Ext.define('Dcs.job.RegistryCenterListView', {
	extend : 'Ext.grid.Panel',
	title : '注册中心',
	store : registryCenterStore,
	xtype: 'registryCenterList',
	id: 'registryCenterList',
	controller: 'registryCenterController',
	reference:'registryCenterController',
	columns : [ {
		xtype : 'rownumberer'
	}, {
		text : 'ID',
		dataIndex : 'id',
		align : 'left',
		hidden : true
	}, {
		text : '名称',
		dataIndex : 'name',
		align : 'left'
	}, {
		text : 'zookeeper地址',
		dataIndex : 'zklist',
		align : 'left',
		flex:1
	}, {
		text : '命名空间',
		dataIndex : 'namespace',
		align : 'left',
		flex:1
	}, {
		text : '概要说明',
		dataIndex : 'digest',
		flex:1,
		align : 'left'
	}, {
	    text: '操作',
	    xtype: 'actioncolumn',
	    align:"center",
	    items: [
	        {
	            tooltip: '连接',
	            icon: "resources/images/edit_task.png",
	            flag:'connect',
	            handler: 'actionFn',
	            getClass : function(v,metadata,r,rowIndex,colIndex,store){
	            	if(r.data.activated == 1){
	            		return 'x-hidden';
	            	}
	            }
	        }, { 
	        	xtype: 'tbspacer' ,
            	getClass : function(v,metadata,r,rowIndex,colIndex,store){
	            	if(r.data.activated == 1){
	            		return 'x-hidden';
	            	}
	            }
	        }, {
	            tooltip: '删除',
	            icon: "resources/images/delete_list.png",
	            flag:'delete',
	            handler: 'actionFn',
            	getClass : function(v,metadata,r,rowIndex,colIndex,store){
	            	if(r.data.activated == 1){
	            		return 'x-hidden';
	            	}
	            }
	        }, {
	            tooltip: '已连接',
	            icon: "resources/images/show_complete.png",
	            flag:'delete',
	            getClass : function(v,metadata,r,rowIndex,colIndex,store){
	            	if(r.data.activated == 0){
	            		return 'x-hidden';
	            	}
	            }
	        }
	    ]
	}],
	fullscreen : true,
	renderTo : Ext.getBody(),
	selModel : {
		selType : 'checkboxmodel',
		mode : 'SINGLE',
		allowDeselect: true

	},
	bbar : [Ext.widget('pagingtoolbar', {
        store      : registryCenterStore,
        displayInfo: true,
        displayMsg : 'Displaying topics {0} - {1} of {2}'
    })],
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