Ext.define('Dcs.main.ProfileSwitcher', {
    extend: 'Ext.Component',
    xtype: 'profileSwitcher',
    cls: 'ks-profile-switcher',

    initComponent: function() {
        var me = this,
            menuItems = [],
            classicProfiles = {
                triton: 'Triton',
                neptune: 'Neptune',
                'neptune-touch': 'Neptune Touch',
                crisp: 'Crisp',
                'crisp-touch': 'Crisp Touch',
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