Ext.define('Dcs.reg.RegListStore', {
	extend : 'Ext.data.Store',
	model : Ext.create('Dcs.reg.RegModel'),
	proxy : {
		type : 'ajax',
		url : 'regulation/list.do',
		reader : {
			type : 'json',
			rootProperty : ''
		}
	},
	autoLoad : true

});