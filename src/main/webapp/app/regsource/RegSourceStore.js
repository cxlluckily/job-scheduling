Ext.define('App.regsource.RegSourceModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'master_url',
		type : 'string'
	}, {
		name : 'master_username',
		type : 'string'
	}, {
		name : 'master_password',
		type : 'string'
	}, {
		name : 'slave_url',
		type : 'string'
	}, {
		name : 'slave_username',
		type : 'string'
	}, {
		name : 'slave_password',
		type : 'string'
	}, {
		name : 'source_name',
		type : 'string'
	}, {
		name : 'create_time',
		type : 'date'
	} ]
});

Ext.define('App.regsource.RegSourceStore', {
	extend : 'Ext.data.Store',
	model : "App.regsource.RegSourceModel",
	xtype : 'regSourceStore',
	pageSize: 20,
	proxy : {
		type : 'ajax',
		url : 'dataSource/list.do',
		reader : {
			type : 'json',
			rootProperty : 'list',
			totalProperty : 'totalCount'
		}
	},
	autoLoad : true
});