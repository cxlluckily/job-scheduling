Ext.define('Dcs.Main', {
	extend : 'Ext.container.Viewport',
	controller : 'main',
	viewModel : 'main',
	layout : 'border',
	stateful : true,
	stateId : 'dcs-viewport',
	requires : [ 
	        'Dcs.main.ProfileSwitcher',
	        'Dcs.main.HeaderView', 
	        'Dcs.main.NavStore',
			'Dcs.main.NavTreeView', 
			'Dcs.main.ContentPanelView',
			'Dcs.main.MainController', 
			'Dcs.reg.RegListController',
			'Dcs.reg.RegModel',
			'Dcs.reg.RegListStore', 
			'Dcs.reg.RegListView',
			'Dcs.reg.RegWindow',
			'Dcs.job.RegistryCenterController',
			'Dcs.job.RegistryCenterModel',
			'Dcs.job.RegistryCenterStore',
			'Dcs.job.RegistryCenterListView',
			'Dcs.job.RegistryCenterWindow',
			'Dcs.job.DataSourceController',
			'Dcs.job.DataSourceModel',
			'Dcs.job.DataSourceStore',
			'Dcs.job.DataSourceListView',
			'Dcs.job.DataSourceWindow',
			'Dcs.regsource.RegSourceController',
			'Dcs.regsource.RegSourceModel',
			'Dcs.regsource.RegSourceStore', 
			'Dcs.regsource.RegSourceView',
			'Dcs.regsource.RegSourceWindow'
	],
	items : [ {
		region : 'north',
		xtype : 'appHeader'
	}, {
		region : 'west',
		reference : 'tree',
		xtype : 'navigation-tree'
	}, {
		region : 'center',
		xtype : 'contentPanel',
		reference : 'contentPanel',
		ariaRole : 'main'
	} ],

	applyState : function(state) {
		this.getController().applyState(state);

	},

	getState : function() {
		return this.getController().getState();
	},

	initComponent : function() {
		Ext.create('Dcs.main.NavStore', {
			storeId : 'navigation'
		});
		this.callParent();
	}
});

Ext.define('Dcs.main.MainModel', {
	extend : 'Ext.app.ViewModel',
	alias : 'viewmodel.main',

	data : {
		selectedView : false
	}
});