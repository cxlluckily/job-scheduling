//The data store containing the list of states
/*var sources = Ext.create('Ext.data.Store', {
	fields : [ 'name', 'value' ],
	data : [ {
		"value" : "1",
		"name" : "db1"
	}, {
		"value" : "2",
		"name" : "db2"
	}, {
		"value" : "3",
		"name" : "db3"
	} ]
});*/

Ext.define('Dcs.reg.RegStore', {
	extend : 'Ext.data.Store',
	model : Ext.create('Ext.data.Model',{
		fields : [{
			name : 'id',
			type : 'string'
		}, {
			name : 'slave_url',
			type : 'string'
		}]
	}),
	proxy : {
		type : 'ajax',
		url : 'dataSource/list.do',
		reader : {
			type : 'json',
			rootProperty : ''
		}
	},
	autoLoad : true

});

// Create the combo box, attached to the states data store
var sourceCombo = Ext.create('Ext.form.ComboBox', {
	fieldLabel : '数据源',
	store : Ext.create('Dcs.reg.RegStore'),
	queryMode : 'local',
	name : 'source_id',
	editable : false,
	displayField : 'slave_url',
	valueField : 'id',
	allowBlank : false
		
});

var regForm = Ext.create('Ext.form.Panel', {
	fullscreen : true,
	layout : 'column',
	xtype: 'regform',
	items : [ {
		xtype: 'fieldset',
		columnWidth : 1,
		defaultType : 'textfield',
		defaults : {
			anchor : '100%'
		},
		fullscreen : true,
		border: 1,
		layout : 'anchor',
		items : [ sourceCombo, {
			xtype : 'textfield',
			name : 'name',
			fieldLabel : '规则描述',
			allowBlank : false
		}, {
			xtype : 'textareafield',
			grow      : false,
			name : 'sql_txt',
			fieldLabel : 'SQL语句'
		}, {
			xtype : 'hiddenfield',
			name : 'id'
		}]
	}]
});

Ext.define('Dcs.reg.RegWindow', {
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
	xtype:'regwin',
	controller: 'regController',
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