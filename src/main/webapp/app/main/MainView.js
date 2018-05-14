Ext.define('App.main.MainView', {
	extend : 'Ext.container.Viewport',
	controller : {
		xclass : "App.main.MainController"
	},
	viewModel : {
		xclass : "App.main.MainModel"
	},
	layout : 'border',
	stateful : true,
	stateId : 'App-viewport',
	requires : [
		'App.main.MainController',
	    'App.main.HeaderView',
	    'App.main.ProfileSwitcher',
	    'App.main.NavStore',
		'App.main.NavTreeView', 
		'App.main.ContentPanelView'
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
		Ext.create('App.main.NavStore', {
			storeId : 'navigation'
		});
		this.callParent();
	}
});

Ext.define('App.main.MainModel', {
	extend : 'Ext.app.ViewModel',
	data : {
		selectedView : false
	}
});