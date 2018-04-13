Ext.define('Dcs.job.DataSourceModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'driver',
		type : 'string'
	}, {
		name : 'url',
		type : 'string'
	}, {
		name : 'username',
		type : 'string'
	}, {
		name : 'password',
		type : 'string'
	}, {
		name : 'activated',
		type : 'boolean'
	}]
});