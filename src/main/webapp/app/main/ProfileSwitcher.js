Ext.define('App.main.ProfileSwitcher', {
    extend: 'Ext.Component',
    xtype: 'profileSwitcher',
    cls: [ 'ks-profile-switcher', 'x-fa', 'fa-bars' ],

    initComponent: function() {
        var me = this,
            menuItems = [],
            classicProfiles = {
        		crisp: 'Crisp',
        		'crisp-touch': 'Crisp Touch',
                neptune: 'Neptune',
                'neptune-touch': 'Neptune Touch',
                triton: 'Triton',
                classic: 'Classic',
                gray: 'Gray'
            },
            menu;

        function makeItem(value, text, paramName) {
        	var profileCookie = Ext.util.Cookies.get("profile");
            return {
            	profile : value,
                text: text,
                checked : (profileCookie == value),
                group: 'profile',
                handler: function () {
                	Ext.util.Cookies.set("profile", value, new Date(9999, 11));
                	window.location.reload();
                }
            };
        }

        for (profileId in classicProfiles) {
            menuItems.push(makeItem(profileId, classicProfiles[profileId]));
        }

        menu = new Ext.menu.Menu({
            items: menuItems
        });

        this.on({
            scope: this,
            click: function (e) {
                menu.showBy(this);
            },
            element: 'el'
        });

        this.callParent();
    }
});