Ext.define('Dcs.main.NavStore', {
    extend: 'Ext.data.TreeStore',
    alias: 'NavStore',

    constructor: function(config) {
        var me = this;
        me.callParent([Ext.apply({
            root: {
                text: '功能概览',
                id: 'all',
                expanded: true,
                iconCls: 'fa-home',
                children: me.getNavItems()
            }
        }, config)]);
    },

    getNavItems: function() {
        return [
            { 
                text: '任务管理',
                id: 'job',
                expanded: true,
                iconCls: 'fa-users',
                children: [
                    { id: 'job-list', iconCls: 'fa-user', text: '任务列表', leaf: true }
                    /*,
                    { id: 'user-my1', viewClass: 'Dcs.user.MyView', iconCls: 'fa-user', text: '我的信息一', leaf: true },
                    { id: 'user-manager', iconCls: 'fa-user-plus', text: '用户管理', leaf: true }*/
                ]
            },
            {
                text: '规则管理',
                id: 'regular',
                iconCls: 'fa-cogs',
                expanded: true,
                children: [
                    { id: 'regular-source', iconCls: 'fa-male', text: '数据源管理', leaf: true },
                    { id: 'regular-table', iconCls: 'fa-male', text: '数据表管理', leaf: true },
                    { id: 'regular-role', iconCls: 'fa-male', text: '清理规则管理', leaf: true }
                ]
            }
        ];
    }
});
