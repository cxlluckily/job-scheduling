Ext.define('Dcs.job.DataSourceStore', {
	extend : 'Ext.data.Store',
	model : Ext.create('Dcs.job.DataSourceModel'),
	proxy : {
		type : 'ajax',
		url : 'console/registry-center',
		reader : {
			type : 'json',
			rootProperty : ''
		}
	},
	autoLoad : true

});