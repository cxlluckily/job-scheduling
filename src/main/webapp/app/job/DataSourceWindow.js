
var regForm = Ext.create('Ext.form.Panel', {
	fullscreen : true,
	layout : 'column',
	xtype: 'dataSourceForm',
	items : [{
		xtype: 'fieldset',
		columnWidth : 1,
		defaultType : 'textfield',
		defaults : {
			anchor : '100%',
			labelAlign:'right'
		},
		fullscreen : true,
		border: 1,
		layout : 'anchor',
		items : [{
			xtype : 'textfield',
			name : 'name',
			fieldLabel : '名称',
			allowBlank : false
		}, {
			xtype : 'textfield',
			grow      : false,
			name : 'driver',
			fieldLabel : 'driver'
		}, {
			xtype : 'textfield',
			grow      : false,
			name : 'url',
			fieldLabel : 'url'
		}, {
			xtype : 'textareafield',
			grow      : false,
			name : 'username',
			fieldLabel : '用户名'
		}, {
			xtype : 'textareafield',
			grow      : false,
			name : 'password',
			fieldLabel : '密码'
		}, {
			xtype : 'hiddenfield',
			name : 'id'
		}]
	}]
});

Ext.define('Dcs.job.DataSourceWindow', {
	extend : 'Ext.window.Window',
	title : '{title}',
	width : 660,
	plain : false,
	iconCls : "addicon",
	resizable : true,
	draggable : true,
	collapsible : true, 
	closeAction : 'close',
	closable : true,
	modal : true,
	autoRender : true,
	buttonAlign : "center",
	xtype:'dataSourceWin',
	controller: 'dataSourceController',
	viewAction: '{viewAction}',
	items : [ regForm ],
	buttons : [ {
		text : "保 存",
		minWidth : 70,
		action: 'save'
	}, {
		text : "关 闭",
		minWidth : 70,
		handler : function(a) {
			this.up('window').close();
		}
	} ]
});