Ext.define('App.main.NavStore', {
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
			        { id: 'jarManagementList', xclass:"App.jar.JarManagementListView", iconCls: 'fa-user', text: '程序管理', leaf: true },
			        { id: 'jobList', xclass:"App.job.JobListView", iconCls: 'fa-user', text: '作业配置管理', leaf: true },
			        { id: 'jobnoderelation', xclass:"App.jobNodeRelation.JobNodeRelationView", iconCls: 'fa-user', text: '作业服务管理', leaf: true },
			        { id: 'serverDimensionList', xclass:"App.serverDimension.ServerDimensionListView", iconCls: 'fa-user', text: '服务器维度管理', leaf: true },
                    { id: 'traceList', xclass:"App.jobtrace.TraceListView", iconCls: 'fa-user', text: '作业事件跟踪', leaf: true },
                    { id: 'historyList', xclass:"App.jobhistory.HistoryListView", iconCls: 'fa-user', text: '作业事件历史', leaf: true },
			        { id: 'jobexecnodes', xclass:"App.jobExecNodes.JobExecNodesView", iconCls: 'fa-user', text: '服务器配置管理', leaf: true },
			        { id: 'registryCenterList', xclass:"App.regcenter.RegistryCenterListView", iconCls: 'fa-user', text: '注册中心', leaf: true },
			        { id: 'dataSourceList', xclass:"App.datasource.DataSourceListView", iconCls: 'fa-user', text: '作业数据源管理', leaf: true }
			    ]
			},
            {
                text: '规则管理',
                id: 'regular',
                //iconCls: 'fa-cogs',
                iconCls: 'fa-user-plus',
                expanded: true,
                children: [
                    { id: 'regsource', xclass:"App.regsource.RegSourceView", iconCls: 'fa-male', text: '数据源管理', leaf: true},
                    { id: 'reglist', xclass:"App.reg.RegListView", iconCls: 'fa-male', text: 'SQL规则管理', leaf: true }/*,
                    { id: 'regular-list', iconCls: 'fa-male', text: '规则管理', leaf: true}*/
                ]
            }
        ];
    }
});
