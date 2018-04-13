Ext.define('Dcs.job.DataSourceController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.dataSourceController',
	control : {
		'dataSourceWin button[action="save"]' : {
			click : 'saveReg'
		},
		'dataSourceList button[action="add"]' : {
			click : 'clickAddBtn'
		},
		'dataSourceList button[action="delete"]' : {
			click : 'deleteFn'
		}
	},
	
	deleteFn : function(btn){
		var record = Ext.ComponentQuery.query('dataSourceList')[0].getSelection()[0];
		var id = record.data.id;
		Ext.Msg.confirm('提示信息', '确定要删除吗？', function(c){
			if(c == 'yes'){
				Ext.Ajax.request({
					method: 'POST',
					url : 'console/data-source/delete',
					params : Ext.encode({'id':id}),
					success : function(response, opts) {
						var result = Ext.decode(response.responseText);
						if(result.success){
							Ext.Msg.alert('提示信息：', '删除成功！');
						}
						var dataSourceList = Ext.ComponentQuery.query('dataSourceList');
						dataSourceList[0].getStore().reload();
					},

					failure : function(response, opts) {
						console.log('server-side failure with status code '
								+ response.status);
					}
				});
			}
		});
		
	},
	saveReg : function(btn) {
		var me = this;
		var form = btn.up('window').down('form');
		var values = form.getValues();
		var params = Ext.encode(values);
		var url = 'console/data-source';
		Ext.Ajax.request({
			method : 'POST',
			url : url,
			params : params,
			success : function(response, opts) {
				var result = Ext.decode(response.responseText);
				if(result.success){
					Ext.Msg.alert('提示信息：', '保存成功！');
				}
				form.up('window').close();
				var dataSourceList = Ext.ComponentQuery.query('dataSourceList');
				dataSourceList[0].getStore().reload();
				form.reset();
			},

			failure : function(response, opts) {
				console.log('server-side failure with status code '
						+ response.status);
			}
		});
	},
	clickAddBtn : function(btn) {
		var window = Ext.create('Dcs.job.DataSourceWindow',{title:'新增', viewAction:'add'});
		window.down('form').reset();
		window.show();
	},
	
	clickUpdateBtn : function(btn) {
		var record = Ext.ComponentQuery.query('dataSourceList')[0].getSelection()[0];
		var window = Ext.create('Dcs.job.DataSourceWindow',{title:'修改', viewAction:'update'});
		window.down('form').getForm().setValues(record.data);
		window.show();
	}
	
});