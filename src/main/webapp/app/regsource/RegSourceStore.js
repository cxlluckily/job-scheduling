Ext.define('Dcs.regsource.RegSourceStore', {
	extend : 'Ext.data.Store',
	model : Ext.create('Dcs.regsource.RegSourceModel'),
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