Ext.ariaWarn = Ext.emptyFn;
Ext.Loader.setPath('App', 'app');

Ext.define('App.Application', {
	extend : 'Ext.app.Application',
    controllers: [
	    'App.main.GlobalController'
	],
    name: 'App',
	requires : [ 
		"App.main.MainView",
		'App.main.GlobalController'
    ],
	mainView : "App.main.MainView",
	init : function() {
		this.setDefaultToken('all');
		Ext.tip.QuickTipManager.init();
		Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	},

	launch : function() {
		Ext.get("loading").hide();
	}
});

Ext.application("App.Application");