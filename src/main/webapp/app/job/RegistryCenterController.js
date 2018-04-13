Ext.define('Dcs.job.RegistryCenterController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.registryCenterController',
	control : {
		'registryCenterWin button[action="save"]' : {
			click : 'saveReg'
		},
		'registryCenterList button[action="add"]' : {
			click : 'clickAddBtn'
		},
		'registryCenterList button[action="delete"]' : {
			click : 'deleteReg'
		},
		'registryCenterList [actioncolumn]' : {
			click : 'actionFn'
		}
	},
	
	actionFn : function(grid, rowIndex, colIndex, item, e, record){
		var flag = item.flag;
		var tip = item.tooltip;
		var viewId = 'registryCenterList';
		var me = this;
		if(flag == 'delete'){
			Ext.Msg.confirm('提示信息', '确定要删除吗？', function(c){
				if(c == 'yes'){
					me.remoteAction(viewId, 'POST', 'console/registry-center/delete', record.data, tip, function(result){
						if(result){
							Ext.Msg.alert('提示信息：', tip + '成功！');
						} else {
							Ext.Msg.alert('提示信息：', tip + '失败！');
						}
						var grids = Ext.ComponentQuery.query(viewId);
						grids[0].getStore().reload();
					});
				}
			});
		} 
		if(flag == 'connect'){
			this.remoteAction(viewId, 'POST', 'console/registry-center/connect', record.data, tip, function(result){
				if(result){
					Ext.Msg.alert('提示信息：', tip + '成功！');
					var grids = Ext.ComponentQuery.query(viewId);
					grids[0].getStore().reload();
				} else {
					Ext.Msg.alert('提示信息：', tip + '失败！');
				}
			});
		}
	},
	
	remoteAction : function(el, method, url, params, tip, fun) {
		Ext.Ajax.request({
			method: method,
			url : url,
			params : Ext.encode(params),
			success : function(response, opts) {
				var result = Ext.decode(response.responseText);
				fun(result.success);
			},
			failure : function(response, opts) {
				console.log(response);
				console.log(opts);
				console.log('server-side failure with status code '
						+ response.status);
			}
		});
	},
	
	deleteReg : function(btn){
		var record = Ext.ComponentQuery.query('registryCenterList')[0].getSelection()[0];
		var id = record.data.id;
		Ext.Msg.confirm('提示信息', '确定要删除吗？', function(c){
			if(c == 'yes'){
				Ext.Ajax.request({
					method: 'POST',
					url : 'console/registry-center/delete',
					params : Ext.encode({'id':id}),
					success : function(response, opts) {
						var result = Ext.decode(response.responseText);
						if(result.success){
							Ext.Msg.alert('提示信息：', '删除成功！');
						}
						var registryCenterList = Ext.ComponentQuery.query('registryCenterList');
						registryCenterList[0].getStore().reload();
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
		var url = 'console/registry-center';
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
				var registryCenterList = Ext.ComponentQuery.query('registryCenterList');
				registryCenterList[0].getStore().reload();
				form.reset();
			},

			failure : function(response, opts) {
				console.log('server-side failure with status code '
						+ response.status);
			}
		});
	},
	clickAddBtn : function(btn) {
		var window = Ext.create('Dcs.job.RegistryCenterWindow',{title:'新增', viewAction:'add'});
		window.down('form').reset();
		window.show();
	},
	
	clickUpdateBtn : function(btn) {
		var record = Ext.ComponentQuery.query('registryCenterList')[0].getSelection()[0];
		var window = Ext.create('Dcs.job.RegistryCenterWindow',{title:'修改', viewAction:'update'});
		window.down('form').getForm().setValues(record.data);
		window.show();
	}
	
});