Ext.define('App.main.HeaderView', {
	extend : 'Ext.container.Container',
	xtype : 'appHeader',
	id : 'app-header',
	title : '作业管理系统',
	height : 52,
	layout : {
		type : 'hbox',
		align : 'middle'
	},
	initComponent : function() {
		document.title = this.title;
		this.items = [ {
			xtype : 'component',
			id : 'app-header-logo'
		}, {
			xtype : 'component',
			id : 'app-header-title',
			html : this.title,
			flex : 1
		}, {
			xtype : 'profileSwitcher'
		}];
		this.callParent();
	}
});

