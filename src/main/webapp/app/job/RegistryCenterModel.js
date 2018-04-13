Ext.define('Dcs.job.RegistryCenterModel', {
	extend : 'Ext.data.Model',
	fields : [ {
		name : 'id',
		type : 'string'
	}, {
		name : 'name',
		type : 'string'
	}, {
		name : 'zklist',
		type : 'string'
	}, {
		name : 'namespace',
		type : 'string'
	}, {
		name : 'digest',
		type : 'string'
	}, {
		name : 'activated',
		type : 'boolean'
	}]
});