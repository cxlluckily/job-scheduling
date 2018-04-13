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
			    text: '作业管理',
			    id: 'job',
			    expanded: true,
			    //iconCls: 'fa-users',
			    iconCls: 'fa-user-plus',
			    children: [
			        { id: 'registryCenterList', iconCls: 'fa-user', text: '注册中心', leaf: true },
			        { id: 'job-source', iconCls: 'fa-user', text: '作业数据源管理', leaf: true },
			        { id: 'job-rule', iconCls: 'fa-user', text: '作业配置管理', leaf: true },
			        { id: 'job-instance', iconCls: 'fa-user', text: '作业服务管理', leaf: true },
			        { id: 'job-event-trace', iconCls: 'fa-user', text: '作业事件跟踪', leaf: true },
			        { id: 'job-node', iconCls: 'fa-user', text: '服务器配置管理', leaf: true }
			    ]
			},
            { 
                text: 'jar包管理',
                id: 'jar',
                expanded: true,
                //iconCls: 'fa-users',
                iconCls: 'fa-user-plus',
                children: [
                    { id: 'jar-list', iconCls: 'fa-user', text: 'jar包列表', leaf: true }
                ]
            },
            {
                text: '规则管理',
                id: 'regular',
                //iconCls: 'fa-cogs',
                iconCls: 'fa-user-plus',
                expanded: true,
                children: [
                    { id: 'regsource', iconCls: 'fa-male', text: '数据源管理', leaf: true},
                    { id: 'reglist', iconCls: 'fa-male', text: 'SQL规则管理', leaf: true }/*,
                    { id: 'regular-list', iconCls: 'fa-male', text: '规则管理', leaf: true}*/
                ]
            }
        ];
    }
});
