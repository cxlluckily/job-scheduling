Ext.define('App.reg.RegCleanSourceModel', {
	extend : 'Ext.data.Model',
	fields: [{
		name: 'id',
		type: 'string'
	}, {
		name: 'slave_url',
		type: 'string'
	}]
});
Ext.define('App.reg.RegCleanSourceStore', {
	extend: 'Ext.data.Store',
	model: 'App.reg.RegCleanSourceModel',
	proxy: {
		type: 'ajax',
		url: 'dataSource/cleanList.do',
		reader: {
			type: 'json',
			rootProperty: ''
		}
	},
	autoLoad: true
});

Ext.define('App.reg.RegHistoryModel', {
	extend: 'Ext.data.Model',
	fields: [{
		name: 'id',
		type: 'string'
	}, {
		name: 'slave_url',
		type: 'string'
	}]
});
Ext.define('App.reg.RegHistoryStore', {
	extend: 'Ext.data.Store',
	model: 'App.reg.RegHistoryModel',
	proxy: {
		type: 'ajax',
		url: 'dataSource/historyList.do',
		reader: {
			type: 'json',
			rootProperty: ''
		}
	},
	autoLoad: true
});

Ext.define('App.reg.JobSettingModel', {
	extend : 'Ext.data.Model',
	fields: [{
		name: 'id',
		type: 'string'
	}, {
		name: 'jobName',
		type: 'string'
	}]
});
Ext.define('App.reg.JobSettingStore', {
	extend: 'Ext.data.Store',
	model: 'App.reg.JobSettingModel',
	pageSize: 13,
	proxy: {
		type: 'ajax',
		url: 'console/jobs',
		reader: {
			type: 'json',
			rootProperty: 'list',
			totalProperty: 'totalCount'
		}
	},
	autoLoad: true
});


Ext.define('App.reg.RegForm', {
	extend: 'Ext.form.Panel',
	fullscreen: true,
	layout: 'column',
	xtype: 'regform',
	items: [{
		xtype: 'fieldset',
		columnWidth: 1,
		defaultType: 'textfield',
		defaults: {
			anchor: '100%'
		},
		fullscreen: true,
		border: 1,
		layout: 'anchor',
		items: [{
			xtype: 'textfield',
			name : 'id',
			hidden: true
		},
		        
			{
				xtype : 'combo', 
				fieldLabel: '清理数据源',
				store: {
					xclass : 'App.reg.RegCleanSourceStore'
				},
				queryMode: 'local',
				name: 'source_id',
				editable: false,
				displayField: 'master_url',
				valueField: 'id',
				allowBlank: false,
				blankText: '请输入清理数据源内容!'
			},
			{
				xtype: 'textfield',
				grow: false,
				name: 'db_name',
				fieldLabel: '清理数据库名称'
			},
			{
				xtype : 'combo',
				fieldLabel: '历史数据源',
				store: {
					xclass: 'App.reg.RegHistoryStore'
				},
				queryMode: 'local',
				name: 'history_source_id',
				editable: false,
				displayField: 'master_url',
				valueField: 'id',
				allowBlank: false,
				blankText: '请输入历史数据源内容!'

			},
			{
				xtype: 'textfield',
				grow: false,
				name: 'history_db_name',
				fieldLabel: '历史数据库名称'
			},
			{
				xtype: 'textfield',
				name: 'name',
				fieldLabel: '规则描述',
				allowBlank: false
			}, {
				xtype: 'textareafield',
				grow: false,
				name: 'sql_txt',
				fieldLabel: '删除规则SQL'
			}, {
				xtype: 'textareafield',
				grow: false,
				name: 'check_sql',
				fieldLabel: '清理源校验SQL'
			}, {
				xtype: 'textareafield',
				grow: false,
				name: 'check_history_sql',
				fieldLabel: '历史源校验SQL'
			},
			{
				xtype : 'combo',
				fieldLabel: '作业',
				store: {
					xclass : 'App.reg.JobSettingStore'
				},
				queryMode: 'local',
				name: 'id_job_setting',
				editable: false,
				displayField: 'jobName',
				valueField: 'id',
				allowBlank: false,
				blankText: '请输入作业内容!'
			}
		]
	}]
});

Ext.define('App.reg.RegWindow', {
	extend: 'Ext.window.Window',
	requires : [
		"App.reg.RegCleanSourceModel",
		"App.reg.RegCleanSourceStore",
		"App.reg.RegHistoryModel",
		"App.reg.RegHistoryStore",
		"App.reg.JobSettingModel",
		"App.reg.JobSettingStore"
    ],
	title: '{title}',
	width: 660,
	plain: false,
	iconCls: "addicon",
	resizable: true,
	draggable: true,
	collapsible: true,
	closeAction : 'destroy',
	closable: true,
	modal: true,
	autoRender: true,
	buttonAlign: "center",
	xtype: 'regwin',
	controller: 'regController',
	viewAction: '{viewAction}',
	items: [{xtype : 'regform'}],
	buttons: [{
		text: "保 存",
		minWidth: 70,
		action: 'save'
	}, {
		text: "关 闭",
		minWidth: 70,
		handler: function (a) {
			this.up('window').close();
		}
	}]
});