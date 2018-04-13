
var regForm = Ext.create('Ext.form.Panel', {
	fullscreen : true,
	layout : 'column',
	xtype: 'registryCenterForm',
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
			name : 'zklist',
			fieldLabel : 'zookeeper地址'
		}, {
			xtype : 'textfield',
			grow      : false,
			name : 'namespace',
			fieldLabel : '命名空间'
		}, {
			xtype : 'textareafield',
			grow      : false,
			name : 'digest',
			fieldLabel : '概要说明'
		}, {
			xtype : 'hiddenfield',
			name : 'id'
		}]
	}]
});

Ext.define('Dcs.job.RegistryCenterWindow', {
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
	xtype:'registryCenterWin',
	controller: 'registryCenterController',
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