Ext.define('Dcs.regsource.RegSourceController', {
	extend : 'Ext.app.ViewController',
	alias : 'controller.regSourceController',
	control : {
		'regsourcewin button[action="save"]' : {
			click : 'saveRegSource'
		},
		'regsource button[action="add"]' : {
			click : 'clickAddBtnSource'
		},
		'regsource button[action="update"]' : {
			click : 'clickUpdateBtnSource'
		},
		'regsource button[action="delete"]' : {
			click : 'deleteRegSource'
		}
	},
	
	deleteRegSource : function(btn){
		var record = Ext.ComponentQuery.query('regsource')[0].getSelection()[0];
		var id = record.data.id;
		Ext.Msg.confirm('提示信息', '确定要删除吗？', function(c){
			if(c == 'yes'){
				Ext.Ajax.request({
					method : 'POST',
					url : 'dataSource/delete.do',
					params : {'id':id},
					success : function(response, opts) {
						var result = Ext.decode(response.responseText);
						if(result.success){
							Ext.Msg.alert('提示信息：', '删除成功！');
						}
						var regsource = Ext.ComponentQuery.query('regsource');
						regsource[0].getStore().reload();
					},

					failure : function(response, opts) {
						console.log('server-side failure with status code '
								+ response.status);
					}
				});
			}
		});
		
	},
	saveRegSource : function(btn) {
		var me = this;
		var form = btn.up('window').down('form');
		var values = form.getValues();
		var params = {params:Ext.encode(values)};
		var url = '';
		var viewAction = btn.up('window').viewAction;
		if(viewAction == 'add'){
			url = 'dataSource/insert.do';
		} 
		if(viewAction == 'update'){
			url = 'dataSource/update.do';
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
				var regsource = Ext.ComponentQuery.query('regsource');
				regsource[0].getStore().reload();
				form.reset();
			},

			failure : function(response, opts) {
				console.log('server-side failure with status code '
						+ response.status);
			}
		});
	},
	clickAddBtnSource : function(btn) {
		var window = Ext.create('Dcs.regsource.RegSourceWindow',{title:'新增', viewAction:'add'});
		window.down('form').reset();
		window.show();
	},
	
	clickUpdateBtnSource : function(btn) {
		var record = Ext.ComponentQuery.query('regsource')[0].getSelection()[0];
		var window = Ext.create('Dcs.regsource.RegSourceWindow',{title:'修改', viewAction:'update'});
		window.down('form').getForm().setValues(record.data);
		window.show();
	}
	
});