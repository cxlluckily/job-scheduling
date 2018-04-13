Ext.define('Dcs.regsource.RegSourceModel', {
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
		name : 'create_time',
		type : 'date'
	} ]
});