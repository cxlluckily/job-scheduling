Ext.define('App.regsource.RegSourceController', {
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
		if(!record) {
			Ext.Msg.show({
			    title:'提示',
			    message: '请选择一条记录进行删除！',
			    buttons: Ext.Msg.OK,
			    icon: Ext.Msg.INFO
			});
			return;
		}
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
							var regsource = Ext.ComponentQuery.query('regsource');
							regsource[0].getStore().reload();
							Ext.Msg.show({
							    title:'提示',
							    message: '删除成功！',
							    buttons: Ext.Msg.OK,
							    icon: Ext.Msg.INFO
							});
						}else{
							Ext.Msg.show({
							    title:'警告',
							    message: result.msg,
							    buttons: Ext.Msg.OK,
							    icon: Ext.Msg.WARNING
							});
						}
					},

					failure : function(response, opts) {
						console.log('server-side failure with status code '
								+ response.status);
						Ext.Msg.show({
						    title:'错误',
						    message: response,
						    buttons: Ext.Msg.OK,
						    icon: Ext.Msg.error
						});
					}
				});
			}
		});
		
	},
	saveRegSource : function(btn) {
		var me = this;
		var form = btn.up('window').down('form');
		if(!form.getForm().isValid()) { // 验证表单 , 如果为空, 不让发送请求
			return;
		}
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
						Ext.Msg.show({
						    title:'提示',
						    message: '保存成功！',
						    buttons: Ext.Msg.OK,
						    icon: Ext.Msg.INFO
						});
					}
					if(viewAction == 'update'){
						Ext.Msg.show({
						    title:'提示',
						    message: '修改成功！',
						    buttons: Ext.Msg.OK,
						    icon: Ext.Msg.INFO
						});
					}
					form.up('window').close();
					var regsource = Ext.ComponentQuery.query('regsource');
					regsource[0].getStore().reload();
				}else{
					Ext.Msg.show({
					    title:'提示',
					    message: result.msg,
					    buttons: Ext.Msg.OK,
					    icon: Ext.Msg.INFO
					});
				}
			},

			failure : function(response, opts) {
				console.log('server-side failure with status code '
						+ response.status);
				Ext.Msg.show({
				    title:'错误',
				    message: response,
				    buttons: Ext.Msg.OK,
				    icon: Ext.Msg.error
				});
			}
		});
	},
	clickAddBtnSource : function(btn) {
		var window = Ext.create('App.regsource.RegSourceWindow',{title:'新增', viewAction:'add'});
		window.show();
	},
	
	clickUpdateBtnSource : function(btn) {
		var record = Ext.ComponentQuery.query('regsource')[0].getSelection()[0];
		if(record){
			var window = Ext.create('App.regsource.RegSourceWindow',{title:'修改', viewAction:'update'});
			window.down('form').getForm().setValues(record.data);
			window.show();
		}else{
			Ext.Msg.show({
			    title:'提示',
			    message: '请选择一条记录进行修改！',
			    buttons: Ext.Msg.OK,
			    icon: Ext.Msg.INFO
			});
		}
	}
	
});