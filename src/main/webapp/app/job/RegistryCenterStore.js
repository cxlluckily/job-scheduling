Ext.define('Dcs.job.RegistryCenterStore', {
	extend : 'Ext.data.Store',
	model : Ext.create('Dcs.job.RegistryCenterModel'),
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