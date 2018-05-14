Ext.define('App.regsource.RegSourceTypeStore', {
	extend : "Ext.data.Store",
	fields : [ "Name", "Value" ],
	data : [ {
		Name : "历史数据源",
		Value : 0
	}, {
		Name : "清理数据源",
		Value : 1
	} ]
});

Ext.define('App.regsource.RegSourceForm', {
	extend : 'Ext.form.Panel',
	fullscreen : true,
	layout : 'column',
	xtype : 'regsourceform',
	items : [ {
		xtype : 'fieldset',
		columnWidth : 1,
		defaultType : 'textfield',
		defaults : {
			anchor : '100%'
		},
		fullscreen : true,
		border : 1,
		layout : 'anchor',
		items : [ {
			xtype : 'combobox',
			fieldLabel : "数据源类型",
			store : {
				xclass : 'App.regsource.RegSourceTypeStore'
			},
			editable : false,
			name : 'source_type',
			displayField : "Name",
			valueField : "Value",
			queryMode : "local",
			allowBlank : false,
			blankText : '请输入数据源类型!',
			mode : 'remote'
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'source_name',
			fieldLabel : '数据源名称',
			allowBlank : false
		}, {
			xtype : 'textfield',
			name : 'master_url',
			fieldLabel : '主库地址',
			allowBlank : false
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'master_username',
			fieldLabel : '主库用户名'
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'master_password',
			fieldLabel : '主库密码'
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'slave_url',
			fieldLabel : '从库地址'
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'slave_username',
			fieldLabel : '从库用户名'
		}, {
			xtype : 'textfield',
			grow : false,
			name : 'slave_password',
			fieldLabel : '从库密码'
		}, {
			xtype : 'hiddenfield',
			name : 'id'
		} ]
	} ]
});

Ext.define('App.regsource.RegSourceWindow', {
	extend : 'Ext.window.Window',
	title : '{title}',
	width : 660,
	plain : false,
	iconCls : "addicon",
	resizable : true,
	draggable : true,
	collapsible : true,
	closeAction : 'destroy',
	closable : true,
	modal : true,
	// autoRender : true,
	buttonAlign : "center",
	xtype : 'regsourcewin',
	controller : 'regSourceController',
	viewAction : '{viewAction}',
	items : [ {
		xtype : 'regsourceform'
	} ],
	buttons : [ {
		text : "保 存",
		minWidth : 70,
		action : 'save'
	}, {
		text : "关 闭",
		minWidth : 70,
		handler : function(a) {
			this.up('window').close();
		}
	} ]
});