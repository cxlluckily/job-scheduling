/*Ext.enableAriaButtons = false;
Ext.Loader.setPath('Dcs', 'app');
Ext.Loader.setConfig({
	disableCaching: false
});
*/
Ext.define('Dcs.Application', {
	extend : 'Ext.app.Application',
    controllers: [
	      'Dcs.main.GlobalController'
	  ],
    name: 'Dcs',         
	init : function() {
		this.setDefaultToken('all');
		Ext.tip.QuickTipManager.init();
		Ext.state.Manager.setProvider(Ext.create('Ext.state.CookieProvider'));
	},

	launch : function() {
		Ext.get("loading").hide();
		Ext.create('Dcs.Main');
	}
});

Ext.application({
	extend : 'Dcs.Application',
	name : 'Dcs'
});