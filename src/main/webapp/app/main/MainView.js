Ext.define('Dcs.main.MainView', {
	extend : 'Ext.container.Viewport',
	controller: 'main',
	viewModel: 'main',
	layout : 'border',
	stateful : true,
	stateId : 'dcs-viewport',
	requires : [
	            'Dcs.main.HeaderView',
	            'Dcs.main.NavStore',
	            'Dcs.main.NavTreeView',
	            'Dcs.main.ContentPanelView',
	            'Dcs.main.MainController'
	],
	items : [ {
		region : 'north',
		xtype : 'appHeader'
	}, {
		region : 'west',
		reference : 'tree',
		xtype : 'navigation-tree'
	} , {
	        region: 'center',
	        xtype: 'contentPanel',
	        reference: 'contentPanel',
	        ariaRole: 'main'
	    }],
	
	applyState : function(state) {
		this.getController().applyState(state);

	},

	getState : function() {
		return this.getController().getState();
	},
	
	initComponent : function() {
        Ext.create('Dcs.main.NavStore', {
            storeId: 'navigation'
        });		
		this.callParent();
	}
});

Ext.define('Dcs.main.MainModel', {
    extend: 'Ext.app.ViewModel',
    alias: 'viewmodel.main',

    data: {
        selectedView: false
    }
});