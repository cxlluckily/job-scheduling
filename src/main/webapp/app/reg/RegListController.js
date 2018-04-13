Ext.define('Dcs.reg.RegListController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.regController',
	control : {
		'regwin button[action="save"]' : {
			click : 'saveReg'
		},
		'reglist button[action="add"]' : {
			click : 'clickAddBtn'
		},
		'reglist button[action="update"]' : {
			click : 'clickUpdateBtn'
		},
		'reglist button[action="delete"]' : {
			click : 'deleteReg'
		}
	},
	
	deleteReg : function(btn){
		var record = Ext.ComponentQuery.query('reglist')[0].getSelection()[0];
		var id = record.data.id;
		Ext.Msg.confirm('提示信息', '确定要删除吗？', function(c){
			if(c == 'yes'){
				Ext.Ajax.request({
					method : 'POST',
					url : 'regulation/delete.do',
					params : {'id':id},
					success : function(response, opts) {
						var result = Ext.decode(response.responseText);
						if(result.success){
							Ext.Msg.alert('提示信息：', '删除成功！');
						}
						var reglist = Ext.ComponentQuery.query('reglist');
						reglist[0].getStore().reload();
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
		var params = {params:Ext.encode(values)};
		var url = '';
		var viewAction = btn.up('window').viewAction;
		if(viewAction == 'add'){
			url = 'regulation/insert.do';
		} 
		if(viewAction == 'update'){
			url = 'regulation/update.do';
		}
		Ext.Ajax.request({
			method : 'POST',
			url : url,
			params : params,
			success : function(response, opts) {
				var result = Ext.decode(response.responseText);
				if(result.success){
					if(viewAction == 'add'){
						Ext.Msg.alert('提示信息：', '保存成功！');
					}
					if(viewAction == 'update'){
						Ext.Msg.alert('提示信息：', '修改成功！');
					}
					
				}
				form.up('window').close();
				var reglist = Ext.ComponentQuery.query('reglist');
				reglist[0].getStore().reload();
				form.reset();
			},

			failure : function(response, opts) {
				console.log('server-side failure with status code '
						+ response.status);
			}
		});
	},
	clickAddBtn : function(btn) {
		var window = Ext.create('Dcs.reg.RegWindow',{title:'新增', viewAction:'add'});
		window.down('form').reset();
		window.show();
	},
	
	clickUpdateBtn : function(btn) {
		var record = Ext.ComponentQuery.query('reglist')[0].getSelection()[0];
		var window = Ext.create('Dcs.reg.RegWindow',{title:'修改', viewAction:'update'});
		window.down('form').getForm().setValues(record.data);
		window.show();
	}
	
});